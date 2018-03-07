package net.vortex.discordbot

import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import net.vortex.discordbot.commands.HelpCommand
import net.vortex.discordbot.commands.IamCommand
import net.vortex.discordbot.commands.StatusCommand
import net.vortex.discordbot.commands.TicketCommand
import net.vortex.discordbot.util.ConfigUtil

class Application {

    val util = ConfigUtil()
    val helpCommand = HelpCommand()

    companion object {
        @JvmStatic fun main(args: Array<String>){
            Application().initialize()
        }
    }

    fun initialize(){
        JDABuilder(AccountType.BOT)
                .setToken(util.getProperty("discord.token"))
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.ONLINE)
                .setGame(Game.playing(util.getProperty("discord.game")))
                .addEventListener(helpCommand.register(HelpCommand()))
                .addEventListener(helpCommand.register(StatusCommand()))
                .addEventListener(helpCommand.register(TicketCommand()))
                .addEventListener(helpCommand.register(IamCommand()))
                .buildBlocking()
    }

}