package net.vortex.discordbot.util

import java.io.FileInputStream
import java.util.*

class ConfigUtil {

    val properties = Properties()

    fun getProperty(property: String): String? {
        val input = FileInputStream(javaClass.classLoader.getResource("config.properties").file);
        properties.load(input)
        return properties.getProperty(property)
    }
}