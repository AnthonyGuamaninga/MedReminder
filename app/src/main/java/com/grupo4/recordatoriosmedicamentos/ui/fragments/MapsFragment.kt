package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task

import com.google.android.material.snackbar.Snackbar
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.maps.MapsData


import com.grupo4.recordatoriosmedicamentos.databinding.FragmentMapsBinding

class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private final var Request_code = 101
    private var lat: Double? = null
    private var lng: Double? = null
    private var farmacia: ImageView?=null
    private var hospital: ImageView?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    //CHECK
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        farmacia = view.findViewById(R.id.imgFarmacia)
//        hospital = view.findViewById(R.id.imgHospital)
        //Iniciar los botones hospital y
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
            requireContext()
        )

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.imgFarmacia.setOnClickListener {
            val strB = StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
            strB.append("location=" + lat + "," + lng)
            strB.append("&radius=1000")
            strB.append("&type=pharmacy")
            strB.append("&sensor=true")
            strB.append("&key=${getString(R.string.maps_api_key)}")

            val url = strB.toString()

            val mapsData = MapsData(mMap)
            mapsData.execute(url)

            Log.d("Click Farmacia img", "se obtuvieron los recuersos")


        }
        binding.imgHospital.setOnClickListener {
            val strB = StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
            strB.append("location=" + lat + "," + lng)
            strB.append("&radius=1000")
            strB.append("&type=hospital")
            strB.append("&sensor=true")
            strB.append("&key=${getString(R.string.maps_api_key)}")

            val url = strB.toString()

            val mapsData = MapsData(mMap)
            mapsData.execute(url)

            Log.d("Click Hospital img", "se obtuvieron los recuersos")


        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        getCurrentLocation()
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                Request_code
            )
            return
        }

        val locationRequest = com.google.android.gms.location.LocationRequest.create().apply {
            interval = 60000L
            priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
            fastestInterval = 5000L
        }


        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                Snackbar.make(requireView(), "Location: $p0", Snackbar.LENGTH_LONG)
                    .show()

                if (p0 == null) {
                    Snackbar.make(
                        requireView(),
                        "No se pudo obtener su ubicación actual",
                        Snackbar.LENGTH_LONG
                    ).show()
                    return
                }

                for (location in p0.locations) {
                    if (location != null) {
                        lat = location.latitude
                        lng = location.longitude
                        Snackbar.make(
                            requireView(),
                            "Tu ubicación: ${location.longitude}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)

        val task: Task<Location> = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                lat = location.latitude
                lng = location.longitude

                val latLng = LatLng(lat!!, lng!!)
                mMap.addMarker(MarkerOptions().position(latLng).title("Estás aquí"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            Request_code -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation()
                }
            }
        }
    }


    private fun zoomOnMap(latLng: LatLng) {
        val newLatZoom = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
        mMap.animateCamera(newLatZoom)
    }

    override fun onMyLocationButtonClick(): Boolean {
        showToast("Tu ubicación actual")
        return false

    }

    override fun onMyLocationClick(p0: Location) {
        showToast("Estás en ${p0.latitude},${p0.longitude}")

    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}