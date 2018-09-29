package com.example.visa.chatappclient

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.io.BufferedReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import com.example.visa.chatappclient.R.id.*

class ChatFragment: Fragment() {

    //lateinit var list: MutableList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var list = ChatHistory.getHistory()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragmentlayout, container,false)
        var list = ChatHistory.getHistory()
        var listView: ListView = view.findViewById(R.id.listView)
        var adapter = CustomAdapter(listView.context,list)
        listView.adapter = adapter
        var button: Button = view.findViewById(R.id.button)
        button.isEnabled
        return view
        }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    */



}