package net.vortex.discordbot.commands

import java.util.*

object CommandStore {

    var commands: TreeMap<String, Command> = TreeMap()

    fun register(c: Command): Command?{
        commands.put(c.aliases.get(0), c)
        return c
    }
}