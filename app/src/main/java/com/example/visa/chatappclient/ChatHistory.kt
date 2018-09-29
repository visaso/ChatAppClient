package com.example.visa.chatappclient

import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.example.visa.chatappclient.R.id.listView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*

object ChatHistory {

    var messages = mutableListOf<Message>()
    lateinit var adapter: CustomAdapter
    lateinit var socket: Socket
    lateinit var out: PrintWriter
    lateinit var input: BufferedReader
    lateinit var scanner: Scanner


    fun getHistory(): MutableList<Message> {
        return messages
    }

    fun initSocket(activity: AppCompatActivity) {
        //Thread(Runnable {
            try {
                //val host = "10.0.2.2"
                val host = "192.168.1.6"
                var listView = activity.findViewById<ListView>(R.id.listView)
                socket = Socket(host, 60123)
                out = PrintWriter(socket.getOutputStream(), true)
                input = BufferedReader(InputStreamReader(socket.getInputStream()))
                scanner = Scanner(input)
                adapter = CustomAdapter(activity, messages)
                listView.adapter = adapter
                //val stdIn = BufferedReader(InputStreamReader(System.`in`))
                Log.e(host, "Input Success")
                System.out.println("Input Success")
                var username = "xdlsd14"
                //var username = intent.getStringExtra("EXTRANAME")
                out.println(":user $username")
                while (true) {
                    val message = scanner.nextLine()
                    activity.runOnUiThread{
                        var json = JSONObject(message)
                        if (json.has("name")) {
                            if (json.getString("name") == "xdlsd3") {
                                adapter.add(json.getString("timeSent"),json.getString("name"),json.getString("text"),TYPE.OUTGOING)
                            } else {
                                adapter.add(json.getString("timeSent"),json.getString("name"),json.getString("text"),TYPE.INCOMING)
                            }
                        } else {
                            adapter.add(json.getString("timeSent"),"Server" ,json.getString("text"),TYPE.SERVER)
                        }
                        listView.setSelection(adapter.count - 1)
                    }
                }
            } catch (e: Exception) {
                Log.e("xd", "FAIL")
            }
       // }).start()
    }

    fun receiveMessage(activity: AppCompatActivity) {

    }

    fun sendMessage(s: String) {
        out.println(s)
    }
}