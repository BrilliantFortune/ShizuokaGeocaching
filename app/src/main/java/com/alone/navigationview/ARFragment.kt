package com.alone.navigationview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.kudan.kudan.ARAPIKey
import eu.kudan.kudan.ARFragment

/**
 * Created by right on 2017/11/22.
 */
class ARFragment : ARFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_ar, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val key = ARAPIKey.getInstance()
        key.setAPIKey("AdQmtKN3SgZgL6DZq0yVJuuFeWi41v3vDfH/fpJJbGRhPt5XcLWlJAuLQYeCTBstkg3PfjgAajTZaB80EgTkhZMjA1Wz6o7yLnMZ/E8gclZuWy0iPr5s668S91VYfJJ4yG8onq0tnmvtjVc//XRdRbFWE9Z0ZvDWivGiUCkw/fTzQbhV7fTp0bNbO1DjkRQO+CMz7EzGETg165WaXz817j5MpeirgXVp99f6IftfNveZyFdCikxQddSItuWhTtRfmtobufLKsSgkA453Df3CEKZFOnn03QX1OYTCzxgJe9ZZzF9nx8h9JlsBqIewtXhtLmMiknNDJFCsA0yyoM7xbJAwuVeNRPliiOqxBHk3F24Pkl7IRoRsFJOeQ2O2ppYo57sl1/4cW2AJYSct7oH3prLI6joZNmLXT1eVOOYLsw14C0a9enHCo/bLCQIqdDYKCp0TohuNMd3PBtb2XRXPpcKPV1nI5JMDgZMuypHTB5smGfVsRMv+uK2RNcO/trQEubV861miYRUcz1l4U8SjwdDl4RPdaC9CNC2jhgiM/UxU1pMaVfeTohkbYs3gQttIEhSZ/zGRwNqJOPMK6t7r/TRVYSTkv+NOE2GAzF09puTDckjfFGA+Hze76BqHg/YXSyIrN19BGCx2/txRmfNOElU4lHzXKTVi0upEtcKoJJU=")
    }
}