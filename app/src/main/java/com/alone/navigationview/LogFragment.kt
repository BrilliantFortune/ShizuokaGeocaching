package com.alone.navigationview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_log.*
import android.app.Fragment

/**
 * Created by right on 2017/11/23.
 */
class LogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf(
                LogData("雲見浅間神社", "賀茂郡松崎町雲見386-2", "2017/11/15"),
                LogData("久能山東照宮", "静岡市駿河区根古屋390", "2017/11/20")
        )

        val adapter = MyAdapter()
        adapter.logList = list
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)

        rv.setHasFixedSize(true)
    }
}