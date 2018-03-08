package net.vortex.discordbot.commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import java.util.*

class HelpCommand: Command("!help", listOf("!hlp"), "Lists possible commands and usage.") {

    val commands: TreeMap<String, Command> = CommandStore.commands

    override fun onCommand(e: MessageReceivedEvent, args: List<String>) {
        val sb = StringBuilder()
        sb.append("Hey ${e.author.asMention}! Here are some commands you can use.\n\n")

        for (c: Command in commands.values){
            sb.append("**${c.name}** (Aliases: ${c.aliases}) - ${c.help}")
            sb.append("\n")
        }

        e.textChannel.sendMessage(sb).queue()
    }


}