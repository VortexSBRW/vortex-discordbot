package net.vortex.discordbot.commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.vortex.discordbot.util.ConfigUtil
import net.vortex.discordbot.util.SQLUtil
import java.util.*

class TicketCommand : Command("!ticket", listOf("!play"), "Generates a ticket for playing") {

    val sqlUtil = SQLUtil()
    val configUtil = ConfigUtil()

    override fun onCommand(e: MessageReceivedEvent, args: List<String>) {
        val name = e.author.name
        val stmt = sqlUtil.getConnection()?.prepareStatement("SELECT * FROM SOAPBOX.INVITE_TICKET WHERE DISCORD_NAME = ?")
        stmt?.setString(1, name)
        val checkQuery = stmt?.executeQuery()

        if (checkQuery?.next() == false){
            val ticket = "${configUtil.getProperty("ticket.prefix")}-${Date().time}"
            val ticketStmt = sqlUtil.getConnection()?.prepareStatement("INSERT INTO SOAPBOX.INVITE_TICKET (DISCORD_NAME, TICKET) VALUES (?, ?)")
            ticketStmt?.setString(1, name)
            ticketStmt?.setString(2, ticket)
            val ticketQuery = ticketStmt?.executeQuery()
            e.textChannel.sendMessage("${e.author.asMention} Hey there! I've sent you a private message with your ticket.").queue()
            e.member.user.openPrivateChannel().
                    queue({channel -> channel.sendMessage("Welcome to Vortex! \nYour ticket is $ticket.").queue()})
        } else {
            e.textChannel.sendMessage("${e.author.asMention} Sorry, you have already received a ticket! I've sent it to you again.").queue()
            e.member.user.openPrivateChannel()
                    .queue({channel -> channel.sendMessage("Your ticket is: ${checkQuery?.getString("TICKET")}").queue()})
        }
    }

}