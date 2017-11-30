package com.alone.navigationview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import eu.kudan.kudan.*
import eu.kudan.kudan.ARActivity

class ARActivity : ARActivity(), ARImageTrackableListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        val key = ARAPIKey.getInstance()
        key.setAPIKey("AdQmtKN3SgZgL6DZq0yVJuuFeWi41v3vDfH/fpJJbGRhPt5XcLWlJAuLQYeCTBstkg3PfjgAajTZaB80EgTkhZMjA1Wz6o7yLnMZ/E8gclZuWy0iPr5s668S91VYfJJ4yG8onq0tnmvtjVc//XRdRbFWE9Z0ZvDWivGiUCkw/fTzQbhV7fTp0bNbO1DjkRQO+CMz7EzGETg165WaXz817j5MpeirgXVp99f6IftfNveZyFdCikxQddSItuWhTtRfmtobufLKsSgkA453Df3CEKZFOnn03QX1OYTCzxgJe9ZZzF9nx8h9JlsBqIewtXhtLmMiknNDJFCsA0yyoM7xbJAwuVeNRPliiOqxBHk3F24Pkl7IRoRsFJOeQ2O2ppYo57sl1/4cW2AJYSct7oH3prLI6joZNmLXT1eVOOYLsw14C0a9enHCo/bLCQIqdDYKCp0TohuNMd3PBtb2XRXPpcKPV1nI5JMDgZMuypHTB5smGfVsRMv+uK2RNcO/trQEubV861miYRUcz1l4U8SjwdDl4RPdaC9CNC2jhgiM/UxU1pMaVfeTohkbYs3gQttIEhSZ/zGRwNqJOPMK6t7r/TRVYSTkv+NOE2GAzF09puTDckjfFGA+Hze76BqHg/YXSyIrN19BGCx2/txRmfNOElU4lHzXKTVi0upEtcKoJJU=")
    }

    override fun setup() {
        super.setup()

        val imageTrackable = ARImageTrackable("Marker")
        imageTrackable.loadFromAsset("maker.png")

        val imageTracker = ARImageTracker.getInstance()
        imageTracker.initialise()
        imageTracker.addTrackable(imageTrackable)

        val imageNode = ARImageNode("apper.png")

        imageTrackable.world.addChild(imageNode)

        imageTrackable.addListener(this)
    }

    override fun didDetect(p0: ARImageTrackable?) {
        Log.d("Maker", "見つけたぞ！！！！！")
    }

    override fun didLose(p0: ARImageTrackable?) {
        Log.d("Maker", "見失いました…")
    }

    override fun didTrack(p0: ARImageTrackable?) {
        Log.d("Maker", "ストーキング中")
    }
}
