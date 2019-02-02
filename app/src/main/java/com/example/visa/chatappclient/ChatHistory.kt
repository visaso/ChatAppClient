package com.example.visa.chatappclient

import android.util.JsonReader
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import kotlin.collections.ArrayList

object ChatHistory {

    var messages = mutableListOf<Message>()
    var users = listOf<String>()
    lateinit var socket: Socket
    lateinit var out: PrintWriter
    lateinit var input: BufferedReader
    lateinit var scanner: Scanner
    lateinit var username: String
    var initialized: Boolean = false

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
                initialized = true
                //val stdIn = BufferedReader(InputStreamReader(System.`in`))
                Log.e(host, "Input Success")
                System.out.println("Input Success")
                out.println(":user $name")
                while (true) {
                    var message = scanner.nextLine()
                    if (!message.startsWith("accOn:")) {
                        chatListener.onMessage(message)
                    }

                    if(message.startsWith("accOn:")) {
                        updateUsers(message)
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "FAIL ${e.message}")
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

    fun updateUsers(message: String) {
        var temp = message
        if (temp.startsWith("accOn:")) {
            temp = message.removeRange(0..7).dropLast(1)
            users = temp.split(", ")
            Log.e("UserLIST", "$users")
        }
    }
}