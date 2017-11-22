package com.alone.navigationview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import com.google.android.gms.maps.MapFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import com.google.android.gms.maps.OnMapReadyCallback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbarの設定
        setSupportActionBar(toolbar)
        //ハンバーガーアイコンのアニメーション設定
        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        if (savedInstanceState == null) {
            val mapFragment = MapFragment.newInstance()


            fragmentManager.beginTransaction()
                    .add(R.id.content, mapFragment)
                    .commit()
        }
    }
}
