package com.grupo4.recordatoriosmedicamentos.data.network.entities.maps

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
class DownloadUrl {

    @Throws(IOException::class)
    fun retrieveUrl(url: String): String {
        var urlData = ""
        var httpUrlConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null

        try {
            val getUrl = URL(url)
            httpUrlConnection = getUrl.openConnection() as HttpURLConnection
            httpUrlConnection.connect()

            inputStream = httpUrlConnection.inputStream

            val buffer = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuilder()
            var line: String?

            while (buffer.readLine().also { line = it } != null) {
                sb.append(line)
            }
            urlData = sb.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        } finally {
            inputStream?.close()
            httpUrlConnection?.disconnect()
        }

        return urlData
    }
}
