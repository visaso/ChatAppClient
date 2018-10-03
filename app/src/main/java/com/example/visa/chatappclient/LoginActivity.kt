package com.example.visa.chatappclient

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.loginscreen.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginscreen)

        var constLayout: ConstraintLayout = findViewById(R.id.loginlayout)
        var anim: AnimationDrawable = constLayout.background as AnimationDrawable
        anim.setEnterFadeDuration(2000)
        anim.setExitFadeDuration(4000)
        anim.start()
    }

    fun configureUserName(v: View) {
        var i: Intent = Intent(this, MainActivity::class.java)
        i.putExtra("EXTRANAME",findViewById<EditText>(R.id.namefield).text.toString())
        startActivity(i)
    }


}
