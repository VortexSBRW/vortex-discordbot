package net.vortex.discordbot.commands

import com.codesnippets4all.json.parsers.JsonParserFactory
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.vortex.discordbot.util.ConfigUtil
import org.apache.commons.io.IOUtils
import java.net.HttpURLConnection
import java.net.URL

class StatusCommand: Command("!status", listOf("!serverstatus"), "Pings the server and prints results.") {

    val configUtil = ConfigUtil()

    override fun onCommand(e: MessageReceivedEvent, args: List<String>) {
        val url = URL(configUtil.getProperty("status.ip"))
        val connection = url.openConnection() as HttpURLConnection
        var message: String
        connection.connectTimeout = 2000
        connection.connect()

        if (connection.responseCode == 200){
            val urlStream = IOUtils.toString(url.openStream())
            val json = JsonParserFactory.getInstance().newJsonParser().parseJson(urlStream)
            message = "${e.author.asMention} \n**${json.getValue("serverName")} (${json.getValue("messageSrv")})**\n" +
                    "There are currently **${json.getValue("onlineNumber")} of ${json.getValue("numberOfRegistered")}** players online."
        } else {
            message = "${e.author.asMention} The server is currently offline. Check the announcements channel for updates!"
        }
        e.channel.sendMessage(message).queue()
    }
}