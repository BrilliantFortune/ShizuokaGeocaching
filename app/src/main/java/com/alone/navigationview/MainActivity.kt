package com.alone.navigationview

import android.app.Fragment
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

        //toolbarタイトルの設定
        toolbar.title = getString(R.string.menu_map)
        //toolbarの設定
        setSupportActionBar(toolbar)

        //ハンバーガーアイコンのアニメーション設定
        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        if (savedInstanceState == null) {
            val mapFragment = GoogleMapFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.content, mapFragment)
                    .commit()
        }

        nvView.setNavigationItemSelectedListener {
            var fragment: android.support.v4.app.Fragment? = null

            when (it.itemId) {
                R.id.menu_map -> {
                    fragment = GoogleMapFragment()
                    toolbar.title = getString(R.string.menu_map)
                }
                R.id.menu_ar -> {
                    fragment = ARFragment()
                    toolbar.title = getString(R.string.menu_ar)
                }
                R.id.menu_log -> {
                    fragment = LogFragment()
                    toolbar.title = getString(R.string.menu_log)
                }
                R.id.menu_other -> {
                    fragment = OtherFragment()
                    toolbar.title = getString(R.string.menu_other)
                }
                R.id.menu_about -> {
                    fragment = AboutFragment()
                    toolbar.title = getString(R.string.menu_about)
                }
            }

            supportFragmentManager.beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit()

            //ドロワーを閉じる
            drawer_layout.closeDrawers()

            true
        }
    }
}
