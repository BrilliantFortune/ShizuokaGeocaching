package com.alone.navigationview

import android.content.Context
import android.util.Log
import java.io.*

/**
 * Created by right on 2017/11/30.
 */
class DataUtil(val context: Context) {

    fun write(logData: MutableList<LogData>) {
        try {
            val out = ObjectOutputStream(context.openFileOutput("log_data.dat", Context.MODE_PRIVATE))
            out.writeObject(logData)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun read() :MutableList<LogData> {
        var logData: MutableList<LogData> = mutableListOf()
        try {
            val input = ObjectInputStream(context.openFileInput("log_data.dat"))
            @Suppress("UNCHECKED_CAST")
            logData = input.readObject() as MutableList<LogData>
        } catch (e: IOException) {

        } catch (e: ClassNotFoundException) {

        }
        return logData
    }
}