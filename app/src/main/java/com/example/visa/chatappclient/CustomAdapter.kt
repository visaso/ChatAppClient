package com.example.visa.chatappclient

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.visa.chatappclient.R.id.timeStamp
import com.example.visa.chatappclient.R.layout.*


class CustomAdapter(val context: Context, var list: MutableList<Message>): BaseAdapter() {



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
        return when {
            list[position].type == TYPE.INCOMING -> 0
            list[position].type == TYPE.OUTGOING -> 1
            list[position].type == TYPE.SERVER -> 2
            else -> {
                return 0
            }
        }
    }

    override fun getViewTypeCount(): Int {
        return 3
    }

    fun add(timeStamp: String, user: String, message:String, type:TYPE) {
        list.add(Message(timeStamp, user, message,type))
        notifyDataSetChanged()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var m = getItem(p0) as Message
        var v :View? = p1

        if (v == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = when (m.type) {
                TYPE.INCOMING -> inflater.inflate(listitem,null) as ConstraintLayout
                TYPE.SERVER -> inflater.inflate(servermessage,null) as ConstraintLayout
                TYPE.OUTGOING -> inflater.inflate(receivedmessage,null) as ConstraintLayout
            }
        }

        val teksti: TextView = v.findViewById(R.id.sentMessage)


        try {
            val username: TextView = v.findViewById(R.id.nameinmessage)
            username.text = m.user
        } catch (e: IllegalStateException) {
            println("Server")
        }

        try {
            val timeStamp: TextView = v.findViewById(R.id.timeStamp)
            timeStamp.text = m.timeStamp
        } catch (e: IllegalStateException) {
            println("Server")
        }
        teksti.text = m.message
        return v
    }


}