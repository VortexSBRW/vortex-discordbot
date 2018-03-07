package net.vortex.discordbot.commands

import net.dv8tion.jda.core.MessageBuilder
import net.dv8tion.jda.core.entities.ChannelType
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

abstract class Command constructor(name: String, aliases: List<String>, help: String) : ListenerAdapter() {

    val name = name
    val aliases = aliases
    val help = help

    abstract fun onCommand(e: MessageReceivedEvent, args: List<String>)

    override fun onMessageReceived(e: MessageReceivedEvent){
        if(e.author.isBot){
            return
        } else if (contains(e.message)){
            onCommand(e, commandArgs(e.message.toString()))
        }
    }

    protected fun contains(m: Message): Boolean {
        return aliases.contains(commandArgs(m).get(0)) || name.contains(commandArgs(m).get(0))
    }

    protected fun commandArgs(s: String): List<String> {
        return s.split(" ")
    }

    protected fun commandArgs(m: Message): List<String> {
        return commandArgs(m.contentRaw)
    }

    protected fun sendMessage(e: MessageReceivedEvent, m: Message): Message? {
        return if (!(e.isFromType(ChannelType.PRIVATE))){
            e.textChannel.sendMessage(m).complete()
        } else {
            e.privateChannel.sendMessage(m).complete()
        }
    }

    protected fun sendMessage(e: MessageReceivedEvent, s: String): Message? {
        return (sendMessage(e, MessageBuilder().append(s).build()))
    }
}