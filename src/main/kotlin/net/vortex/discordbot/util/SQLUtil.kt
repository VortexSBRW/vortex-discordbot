package net.vortex.discordbot.util

import java.sql.DriverManager
import java.sql.ResultSet

class SQLUtil {

    val configUtil = ConfigUtil()

    fun executeQuery(query: String): ResultSet? {
        val connection = DriverManager.getConnection(configUtil.getProperty("db.address"), configUtil.getProperty("db.username"), configUtil.getProperty("db.password"))
        val statement = connection.createStatement()
        return statement.executeQuery(query)
    }
}