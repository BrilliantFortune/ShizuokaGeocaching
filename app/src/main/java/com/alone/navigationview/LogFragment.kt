package com.alone.navigationview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_log.*

/**
 * Created by right on 2017/11/23.
 */
class LogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val list = arrayListOf(
                LogData("雲見浅間神社", "賀茂郡松崎町雲見386-2", "2017/11/15"),
                LogData("久能山東照宮", "静岡市駿河区根古屋390", "2017/11/20"),
                LogData("てすと", "てすと", "てすと")
        )*/

        val dataUtil = DataUtil(context)
        val list = dataUtil.read()

        val adapter = MyAdapter()
        adapter.logList = list

        //スワイプで削除と並び替えの実装
        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
                return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val card = adapter.logList.removeAt(viewHolder.adapterPosition)
                adapter.logList.add(target.adapterPosition, card)
                adapter.notifyDataSetChanged()
                dataUtil.write(adapter.logList)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.logList.removeAt(viewHolder.adapterPosition)
                adapter.notifyDataSetChanged()
                dataUtil.write(adapter.logList)
            }
        })

        button.setOnClickListener {
            adapter.logList.add(
                    LogData("てすと", "てすと", list.size.toString())
            )
            adapter.notifyItemInserted(adapter.logList.size)
        }

        adapter.setHasStableIds(true)

        helper.attachToRecyclerView(rv)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)

        rv.setHasFixedSize(true)
    }
}