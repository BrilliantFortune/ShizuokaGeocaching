package com.alone.navigationview

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_googlemap.*

/**
 * Created by right on 2017/11/22.
 */
class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    private val ACCESS_FINE_LOCATION = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_googlemap, container, false)
        map.getMapAsync(this)
        return v
    }

    override fun onMapReady(map: GoogleMap?) {
        if (PermissionChecker.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map?.isMyLocationEnabled = true
        } else {
               requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)
        }
    }
}