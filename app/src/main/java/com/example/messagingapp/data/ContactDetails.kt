package com.example.messagingapp.data

data class ContactDetails(
    var name: String,
    var lastMessage: String,
    var messages: MutableList<Pair<String,MessageType>>
)

enum class MessageType{
    Sent,
    Received
}