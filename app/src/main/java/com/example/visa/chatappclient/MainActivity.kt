package com.example.visa.chatappclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, UserListFragment()).commit()
        var chatFragment = ChatFragment()
        var userlistFragment = UserListFragment()
        supportActionBar!!.setTitle("QckCht")

        val self = this
        var username = intent.getStringExtra("EXTRANAME")
        Thread(Runnable {
            ChatHistory.initSocket(username, object:ChatHistory.ChatListener{
                override fun onConnected() {

                }

                override fun onMessage(message: String) {
                    self.runOnUiThread{
                        chatFragment.add(message)
                    }
                }
            })
            Thread.sleep(50)
        }).start()

        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.action_chat -> {

                    val ft = getSupportFragmentManager().beginTransaction()
                    ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out)
                            ft.replace(R.id.fragment_container, chatFragment).commit()
                }
                R.id.action_users -> {
                    getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out)
                            .replace(R.id.fragment_container, UserListFragment()).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
