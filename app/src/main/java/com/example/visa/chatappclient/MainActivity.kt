package com.example.visa.chatappclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, UserListFragment()).commit()
        var chatFragment = ChatFragment()

        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.action_chat -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, chatFragment).commit()
                }
                R.id.action_users -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, UserListFragment()).commit()
                }

            }
            return@setOnNavigationItemSelectedListener true
        }

        val self = this
        Thread(Runnable {
            ChatHistory.initSocket(object:ChatHistory.ChatListener{
                override fun onConnected() {

                }

                override fun onMessage(message: String) {
                    self.runOnUiThread{
                       chatFragment.add(message)
                    }
                }
            })
        }).start()
    }
}
