package com.example.messagingapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.messagingapp.data.ContactDetails
import com.example.messagingapp.data.Datasource.ContactList
import com.example.messagingapp.data.MessageType
import com.example.messagingapp.model.AppViewModel
import com.example.messagingapp.model.IDLE_MESSAGE_STATE
import com.example.messagingapp.ui.ChatScreen
import com.example.messagingapp.ui.HomePage

enum class MainAppScreen{
    Home,
    Chat,
    New,
}

@Composable
fun MainAppScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = AppViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainAppScreen.Home.name,
        modifier = modifier
    ) {
        composable(route = MainAppScreen.Home.name) {
            HomePage(
                onItemClick = {
                    viewModel.setContact(it)
                    navController.navigate(MainAppScreen.Chat.name)
                },
                onMessageClick = {
                    val contact = ContactDetails(it,"", mutableListOf(Pair("",MessageType.Sent)))
                    viewModel.setContact(contact)
                    ContactList.add(contact)
                    navController.navigate(MainAppScreen.Chat.name)
                }
            )
        }
        composable(route = MainAppScreen.Chat.name) {
            viewModel.onIdleMessage()
            ChatScreen(
                contact = uiState.contactState,
                navController = navController,
                onSendClick = {
                    val size = uiState.contactState.messages.size
                    uiState.contactState.messages.add(Pair(it,MessageType.Sent))
                    viewModel.onSentMessage()
                },
                onReceiveClick = {
                    uiState.contactState.messages.add(Pair("asdf",MessageType.Received))
                    viewModel.onReceivedMessage()
                }
            )
        }
    }
}