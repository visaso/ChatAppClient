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

    interface ChatListener {
        fun onConnected()
        fun onMessage(s: String)
    }

    fun initSocket(chatListener: ChatListener) {
        //Thread(Runnable {
            try {
                //val host = "10.0.2.2"
                val host = "192.168.43.94"

                socket = Socket(host, 60805)
                out = PrintWriter(socket.getOutputStream(), true)
                input = BufferedReader(InputStreamReader(socket.getInputStream()))
                scanner = Scanner(input)
                chatListener.onConnected()

                //val stdIn = BufferedReader(InputStreamReader(System.`in`))
                Log.e(host, "Input Success")
                System.out.println("Input Success")
                var username = "xdlsd20"
                //var username = intent.getStringExtra("EXTRANAME")
                out.println(":user $username")
                while (true) {
                    val message = scanner.nextLine()
                    chatListener.onMessage(message)

                }
            } catch (e: Exception) {
                Log.e("xd", "FAIL ${e.message}")
            }
       // }).start()
    }

    fun receiveMessage(activity: AppCompatActivity) {

    }

    fun sendMessage(s: String) {
        out.println(s)
    }
}