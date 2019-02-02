package com.example.visa.chatappclient

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class UserListAdapter(val context: Context, var list: List<String>): BaseAdapter() {

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    fun add(user: String) {
        notifyDataSetChanged()
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var m = getItem(p0)
        var v : View? = p1

        if (v == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(R.layout.userlistlayout,null) as ConstraintLayout
        }
        val teksti: TextView = v.findViewById(R.id.userlistitem)
        teksti.text = m.toString()
        return v
    }
}