package com.alone.navigationview

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.cardview.view.*

/**
 * Created by right on 2017/11/23.
 */
class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var logList : List<LogData> = emptyList()

    override fun getItemCount(): Int {
        return logList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(logList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(layoutInflater.inflate(R.layout.cardview, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : LogData) {
            itemView.text_title.text = data.title
            itemView.text_location.text = data.location
            itemView.text_time.text = data.time
        }
    }
}