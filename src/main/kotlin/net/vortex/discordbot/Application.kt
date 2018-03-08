package net.vortex.discordbot

import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import net.vortex.discordbot.commands.*
import net.vortex.discordbot.util.ConfigUtil

class Application {

    val util = ConfigUtil()

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
                .addEventListener(CommandStore.register(HelpCommand()))
                .addEventListener(CommandStore.register(StatusCommand()))
                .addEventListener(CommandStore.register(TicketCommand()))
                .addEventListener(CommandStore.register(IamCommand()))
                .buildBlocking()
    }

}