package com.alone.navigationview

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import kotlinx.android.synthetic.main.fragment_googlemap.*
import kotlinx.android.synthetic.main.toolbar.*
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import java.io.*
import java.util.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import android.support.v7.app.AppCompatActivity




/**
 * Created by right on 2017/11/22.
 */
class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    private val ACCESS_FINE_LOCATION = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_googlemap, container, false)
        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {

        val su = LatLng(34.724770, 137.717708)
        //とりあえず静大
        map?.addMarker(MarkerOptions().position(su).title("Marker in Shizuoka University"))
        map?.moveCamera(CameraUpdateFactory.newLatLng(su))
        map?.addCircle(CircleOptions().center(su).radius(200.0).strokeWidth(3.0f).strokeColor(-16711681).fillColor(2013331455))


        val assetmanager : AssetManager = this.context.resources.assets
        val subfiles = assetmanager.list("")
        //main\\assets内のファイル読み込み
        try {
            for (i in subfiles!!.indices) {
                val inputstream = this.context.resources.assets.open(subfiles[i])
                val inputstreamreader = InputStreamReader(inputstream)
                val bufferedreader = BufferedReader(inputstreamreader)
                var line: String?
                while (true) {
                    line = bufferedreader.readLine()
                    if (line == null)
                        break
                    val st = StringTokenizer(line, ",")
                    val color = Mapcolor(st.nextToken())
                    val tit:String = st.nextToken()
                    val lat = LatLng(st.nextToken().toDouble(), st.nextToken().toDouble())
                    //マーカーの作成
                    map?.addMarker(MarkerOptions().position(lat).title(tit))
                    //円の作成
                    map?.addCircle(CircleOptions().center(lat).radius(200.0).strokeWidth(3.0f).strokeColor(color.sc).fillColor(color.fc))
                }
                bufferedreader.close()
            }
        } catch (e: IOException) {
            println(e)
        }

        //パーミッションの確認
        if (PermissionChecker.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //現在地レイヤーを有効化
            map?.isMyLocationEnabled = true
        } else {
            //パーミッション有効化のダイアログを表示
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION)
        }

    }

}