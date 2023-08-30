package com.example.messagingapp.model

import androidx.lifecycle.ViewModel
import com.example.messagingapp.data.ContactDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val MESSAGE_SENT = 0
const val MESSAGE_RECEIVED = 1
const val IDLE_MESSAGE_STATE = 10

class AppViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AppUIState())
    val uiState: StateFlow<AppUIState> = _uiState.asStateFlow()

    fun setContact(contact: ContactDetails) {
        _uiState.update { currentState ->
            currentState.copy(
                contactState = contact
            )
        }
    }

    fun setMessageStatus(mState: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                messageState = mState
            )
        }
    }

    fun onReceivedMessage() {
        setMessageStatus(MESSAGE_RECEIVED)
    }

    fun onSentMessage() {
        setMessageStatus(MESSAGE_SENT)
    }

    fun onIdleMessage() {
        setMessageStatus(IDLE_MESSAGE_STATE)
    }
}