package com.alone.navigationview

import android.Manifest
import android.app.Fragment
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.PermissionChecker
import android.support.v7.app.ActionBarDrawerToggle
import android.widget.Toast
import com.google.android.gms.maps.MapFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity() {
    val PERMISSION_REQUEST_CODE = 1

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
            if (intent.hasExtra("log")) {
                val log = intent.getSerializableExtra("log")
                val fragment = LogFragment()
                val args = Bundle()
                args.putSerializable("log", log)
                //Toast.makeText(applicationContext, (log as LogData).title, Toast.LENGTH_SHORT).show()
                fragment.arguments = args
                fragmentManager.beginTransaction()
                        .add(R.id.content, fragment)
                        .commit()
                toolbar.title = getString(R.string.menu_log)
            } else {
                val mapFragment = GoogleMapFragment()
                fragmentManager.beginTransaction()
                        .add(R.id.content, mapFragment)
                        .commit()
            }

        }

        //ナビゲーションペインの設定
        nvView.setNavigationItemSelectedListener {
            var fragment: Fragment? = null

            when (it.itemId) {
                R.id.menu_map -> {
                    fragment = GoogleMapFragment()
                    toolbar.title = getString(R.string.menu_map)
                }
                R.id.menu_ar -> {
                    val intent = Intent(this, ARActivity::class.java)
                    startActivity(intent)
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

            if (fragment != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.content, fragment)
                        .commit()
            }

            //ドロワーを閉じる
            drawer_layout.closeDrawers()

            true
        }

        val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        )

        val requestPermissions = mutableListOf<String>()

        for (permission in permissions) {
            if (PermissionChecker.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions.add(permission)
            }
        }

        if (requestPermissions.isNotEmpty()) {
            if (Build.VERSION.SDK_INT > 23) {
                requestPermissions(requestPermissions.toTypedArray(), PERMISSION_REQUEST_CODE)
            }
        }
    }
}
