package com.example.messagingapp.data

object Datasource {
    val ContactList = mutableListOf(
        ContactDetails("First", "Second", mutableListOf(Pair("hello",MessageType.Sent), Pair("how are you",MessageType.Sent), Pair("sjfwo",MessageType.Received))),
        ContactDetails("Third", "fourth", mutableListOf(Pair("",MessageType.Received))),
        ContactDetails("fifth", "sixth", mutableListOf(Pair("",MessageType.Received))),
        ContactDetails("seventh", "eighth", mutableListOf(Pair("",MessageType.Received))),
    )
}