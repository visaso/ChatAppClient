package com.example.visa.chatappclient

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragmentlayout.*
import org.json.JSONObject

class UserListFragment : Fragment() {

    lateinit var adapter: UserListAdapter
    var initialized: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.userfragmentlayout, container, false)
        var listView: ListView = view.findViewById(R.id.userlist)
        adapter = UserListAdapter(listView.context, ChatHistory.users)
        listView.adapter = adapter
        initialized = true
        return view
    }
}