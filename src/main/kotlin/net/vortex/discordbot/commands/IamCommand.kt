package net.vortex.discordbot.commands

import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.exceptions.HierarchyException

class IamCommand: Command("!iam", listOf("!role"), "Assigns a certain role to an user.") {

    override fun onCommand(e: MessageReceivedEvent, args: List<String>) {
        if (args.isNotEmpty()){
            val role = args[1].split("(")[0]
            try{
                e.guild.controller.addSingleRoleToMember(e.member, e.guild.getRolesByName(role, true)[0]).queue()
                e.textChannel.sendMessage("${e.author.asMention} You have been given the **$role** role.").queue()
            } catch (ex: IndexOutOfBoundsException){
                e.textChannel.sendMessage("${e.author.asMention} I'm sorry, the role **$role** does not exist.").queue()
            } catch (ex: HierarchyException){
                e.textChannel.sendMessage("${e.author.asMention} You don't have permission to get this role.").queue()
            }
        } else {
            e.textChannel.sendMessage("${e.author.asMention} Invalid number of arguments.").queue()
        }
    }
}