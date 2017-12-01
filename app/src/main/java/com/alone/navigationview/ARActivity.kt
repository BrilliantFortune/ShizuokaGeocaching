package com.alone.navigationview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import eu.kudan.kudan.*
import eu.kudan.kudan.ARActivity
import kotlinx.android.synthetic.main.activity_ar.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sp
import org.jetbrains.anko.uiThread
import java.io.*
import java.net.Socket
import java.net.SocketTimeoutException
import java.nio.charset.Charset
import java.util.*
import java.util.regex.Pattern

class ARActivity : ARActivity(), ARImageTrackableListener {
    private val HOST = "192.168.3.20"
    private val PORT = 4869
    private var socket : Socket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        val key = ARAPIKey.getInstance()
        key.setAPIKey("AdQmtKN3SgZgL6DZq0yVJuuFeWi41v3vDfH/fpJJbGRhPt5XcLWlJAuLQYeCTBstkg3PfjgAajTZaB80EgTkhZMjA1Wz6o7yLnMZ/E8gclZuWy0iPr5s668S91VYfJJ4yG8onq0tnmvtjVc//XRdRbFWE9Z0ZvDWivGiUCkw/fTzQbhV7fTp0bNbO1DjkRQO+CMz7EzGETg165WaXz817j5MpeirgXVp99f6IftfNveZyFdCikxQddSItuWhTtRfmtobufLKsSgkA453Df3CEKZFOnn03QX1OYTCzxgJe9ZZzF9nx8h9JlsBqIewtXhtLmMiknNDJFCsA0yyoM7xbJAwuVeNRPliiOqxBHk3F24Pkl7IRoRsFJOeQ2O2ppYo57sl1/4cW2AJYSct7oH3prLI6joZNmLXT1eVOOYLsw14C0a9enHCo/bLCQIqdDYKCp0TohuNMd3PBtb2XRXPpcKPV1nI5JMDgZMuypHTB5smGfVsRMv+uK2RNcO/trQEubV861miYRUcz1l4U8SjwdDl4RPdaC9CNC2jhgiM/UxU1pMaVfeTohkbYs3gQttIEhSZ/zGRwNqJOPMK6t7r/TRVYSTkv+NOE2GAzF09puTDckjfFGA+Hze76BqHg/YXSyIrN19BGCx2/txRmfNOElU4lHzXKTVi0upEtcKoJJU=")
    }

    override fun setup() {
        super.setup()

        val imageTrackable = ARImageTrackable("Marker")
        imageTrackable.loadFromAsset("images/marker.png")

        val imageTracker = ARImageTracker.getInstance()
        imageTracker.initialise()
        imageTracker.addTrackable(imageTrackable)

        val imageNode = ARImageNode("images/apper.png")

        imageTrackable.world.addChild(imageNode)

        imageTrackable.addListener(this)
    }

    override fun didDetect(p0: ARImageTrackable?) {
        Log.d("Marker", "見つけたぞ！！！！！")

        if (socket == null) {
            var result = ""
            doAsync {
                result = receiveMessage()
                uiThread {
                    //android.os.Debug.waitForDebugger()
                    val i = Intent(applicationContext, MainActivity::class.java)
                    val log = searchLogId(result)
                    i.putExtra("log", log)
                    startActivity(i)
                    socket = null
                }
            }
        }
    }

    override fun didLose(p0: ARImageTrackable?) {
        Log.d("Marker", "見失いました…")
    }

    override fun didTrack(p0: ARImageTrackable?) {
        //Log.d("Maker", "ストーキング中")
    }

    private fun receiveMessage() : String {
        socket = Socket(HOST, PORT)

        var result = ""

        try {
            val pw = PrintWriter(socket?.getOutputStream(), true)
            pw.println("Hello")

            val istream = socket?.getInputStream()
            val dis = DataInputStream(istream)
            val buff = ByteArray(1024)
            dis.read(buff)
            result = String(buff, Charset.forName("UTF-8"))
            istream?.close()

            socket?.close()
        } catch (e : IOException) {
            e.stackTrace
        }
        return result
    }

    private fun searchLogId(result : String) : LogData {

        val splited = result.split("\n")
        val id = splited[1]

        var log = LogData("", "", "")
        val assetManager = applicationContext.resources.assets
        try {
            val istream = assetManager.open("csv/id.csv")
            val br = BufferedReader(InputStreamReader(istream))
            var line = br.readLine()
            while (line != null) {
                val p = Pattern.compile(splited[1])
                val m = p.matcher(line)
                if (m.find()) {
                    val st = StringTokenizer(line, ",")
                    val t = st.nextToken()
                    val date = android.text.format.DateFormat.format("yyyy/MM/dd", Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN))
                    log = LogData(
                            title = st.nextToken(),
                            location = st.nextToken(),
                            time = date.toString()
                    )
                    break
                }
                line = br.readLine()
            }
            istream.close()
            br.close()

            Toast.makeText(applicationContext, log.title, Toast.LENGTH_SHORT).show()
        } catch (e : IOException) {
            e.stackTrace
        }
        return log
    }
}
