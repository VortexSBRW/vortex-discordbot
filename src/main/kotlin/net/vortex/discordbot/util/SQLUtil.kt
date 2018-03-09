package net.vortex.discordbot.util

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class SQLUtil {

    val configUtil = ConfigUtil()

    fun executeQuery(query: String): ResultSet? {
        val statement = getConnection()?.createStatement()
        return statement?.executeQuery(query)
    }

    fun getConnection(): Connection?{
        val connection = DriverManager.getConnection(configUtil.getProperty("db.address"), configUtil.getProperty("db.username"), configUtil.getProperty("db.password"))
        return connection
    }
}