package com.grupo4.recordatoriosmedicamentos.data.network.entities.maps

import com.google.android.gms.maps.CameraUpdateFactory

import org.json.JSONObject

import android.os.AsyncTask
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import java.io.IOException
class MapsData(private val googleMap: GoogleMap) : AsyncTask<String, String, String>() {

    override fun onPostExecute(s: String) {
        try {
            Log.d("Marker", "EJECUTANDO...")
            val json = JSONObject(s)
            val jsonArray = json.getJSONArray("results")

            for (i in 0 until jsonArray.length()) {
                val jsonObject1 = jsonArray.getJSONObject(i)
                val location = jsonObject1.getJSONObject("geometry").getJSONObject("location")

                val lat = location.getDouble("lat")
                val lng = location.getDouble("lng")

                val name = jsonObject1.getString("name")
                val latLng = LatLng(lat, lng)

                val markerOptions = MarkerOptions()
                markerOptions.title(name)
                markerOptions.position(latLng)
                Log.d("Marker", "EJECUTANDo marcadores...")
                googleMap.addMarker(markerOptions)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun doInBackground(vararg urls: String): String {
        var result = ""
        try {
            val downloadUrl = DownloadUrl()
            result = downloadUrl.retrieveUrl(urls[0])
            Log.d("result", result.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
}
