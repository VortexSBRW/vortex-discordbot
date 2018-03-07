package net.vortex.discordbot.commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import java.util.*

class HelpCommand : Command("!help", listOf("!hlp"), "Lists possible commands and usage.") {

    var commands: TreeMap<String, Command> = TreeMap()

    override fun onCommand(e: MessageReceivedEvent, args: List<String>) {
        val sb = StringBuilder()

        for (c: Command in commands.values){
            sb.append("${c.name} (Aliases: ${c.aliases}) - ${c.help}")
            sb.append("\n")
            println(c.name)
        }

        e.textChannel.sendMessage("debug ${sb.toString()}").queue()
    }

    fun register(c: Command): Command? {
        commands.put(c.aliases.get(0), c)
        println(c.aliases.get(0))
        return c
    }

}