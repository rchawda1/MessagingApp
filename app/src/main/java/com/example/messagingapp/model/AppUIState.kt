package com.example.messagingapp.model

import com.example.messagingapp.data.ContactDetails
import com.example.messagingapp.data.MessageType

data class AppUIState(
    val contactState: ContactDetails = ContactDetails("","", mutableListOf(Pair("",MessageType.Sent))),
    val newMessage: Boolean = false,
    val messageState: Int = IDLE_MESSAGE_STATE
)