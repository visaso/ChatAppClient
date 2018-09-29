package com.example.visa.chatappclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread(Runnable {
            ChatHistory.initSocket(this)
        }).start()


       // ChatHistory.out.println("XDDDDDDDDDDDDD")

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, UserListFragment()).commit()

        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.action_chat -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, ChatFragment()).commit()
                }
                R.id.action_users -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, UserListFragment()).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        /*
        var textBox: EditText = findViewById(R.id.latestMessage)
        var sendButton = findViewById<Button>(R.id.button)
        sendButton.setOnClickListener {
            Log.println(Log.DEBUG,"MainActivity", "Button pressed")
                Thread(Runnable {
                    ChatHistory.sendMessage(textBox.text.toString())
                }).start()
            }
            */
    }
}
