package com.example.visa.chatappclient

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*

object ChatHistory {

    var messages = mutableListOf<Message>()
    lateinit var socket: Socket
    lateinit var out: PrintWriter
    lateinit var input: BufferedReader
    lateinit var scanner: Scanner
    lateinit var username: String


    fun getHistory(): MutableList<Message> {
        return messages
    }

    interface ChatListener {
        fun onConnected()
        fun onMessage(s: String)
    }

    fun initSocket(name: String, chatListener: ChatListener) {
        username = name
            try {
                val host = "192.168.43.94"
                socket = Socket(host, 61511)
                out = PrintWriter(socket.getOutputStream(), true)
                input = BufferedReader(InputStreamReader(socket.getInputStream()))
                scanner = Scanner(input)
                chatListener.onConnected()
                //val stdIn = BufferedReader(InputStreamReader(System.`in`))
                Log.e(host, "Input Success")
                System.out.println("Input Success")
                out.println(":user $name")
                while (true) {
                    val message = scanner.nextLine()
                    chatListener.onMessage(message)

                }
            } catch (e: Exception) {
                Log.e("xd", "FAIL ${e.message}")
            }
    }

    fun getName():String {
        return username
    }

    fun sendMessage(s: String) {
        val userCommand = ":user "
        if (s.contains(userCommand)) {
            username = s.removePrefix(userCommand)
        }
        out.println(s)
    }
}