package com.example.visa.chatappclient

enum class TYPE(val s: String) {
    SERVER("Server"),
    OUTGOING("Outgoing"),
    INCOMING("Incoming")
}

class Message(val timeStamp: String, val user: String ,val message: String, val type: TYPE) {

}