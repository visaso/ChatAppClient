package com.example.visa.chatappclient

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.loginscreen.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginscreen)
    }

    fun configureUserName(v: View) {
        var i: Intent = Intent(this, MainActivity::class.java)
        i.putExtra("EXTRANAME",findViewById<EditText>(R.id.namefield).text.toString())
        startActivity(i)
    }


}
