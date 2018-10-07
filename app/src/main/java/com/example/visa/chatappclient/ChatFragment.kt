package com.example.visa.chatappclient

import android.animation.Animator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import java.io.BufferedReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import com.example.visa.chatappclient.R.id.*
import org.json.JSONObject

class ChatFragment: Fragment() {
    //lateinit var list: MutableList<Message>
    lateinit var adapter: CustomAdapter
    var initialized : Boolean = false
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragmentlayout, container,false)
        listView = view.findViewById(R.id.listView)
        val textBox: EditText = view.findViewById(R.id.latestMessage)
        adapter = CustomAdapter(listView.context,ChatHistory.messages)
        listView.adapter = adapter
        var button: ImageButton = view.findViewById(R.id.button)
        button.setOnClickListener {
            sendMessage(textBox.text.toString())
            textBox.text.clear()
        }
        button.isEnabled
        initialized = true
        return view
    }

    fun add(message: String) {
        if (initialized) {
            var json = JSONObject(message)
            if (json.has("name")) {
                if (json.getString("name") == ChatHistory.getName()) {
                    adapter.add(json.getString("timeSent"), json.getString("name"), json.getString("text"), TYPE.OUTGOING)
                } else {
                    adapter.add(json.getString("timeSent"), json.getString("name"), json.getString("text"), TYPE.INCOMING)
                }
            } else {
                adapter.add(json.getString("timeSent"), "Server", json.getString("text"), TYPE.SERVER)
            }
            listView.setSelection(adapter.count - 1)
        }
    }

    fun sendMessage(message: String) {
        Thread {
            ChatHistory.sendMessage(message)
        }.start()
    }
}