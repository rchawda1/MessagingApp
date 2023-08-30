package com.example.messagingapp.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.messagingapp.data.ContactDetails
import com.example.messagingapp.data.MessageType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    contact: ContactDetails,
    navController: NavController,
    modifier: Modifier = Modifier,
    onSendClick: (String) -> Unit,
    onReceiveClick: () -> Unit
) {
    var newMessage by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(
                        text = contact.name,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onReceiveClick) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    }
                    /*
                    Text(
                        text = "Movius",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                    )*/
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.LightGray),
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.Bottom,
                    //horizontalAlignment = Alignment.Start,
                    reverseLayout = true,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)
                ) {
                    items(contact.messages.asReversed()) { message ->
                        if (message.first != "") {
                            ChatBubble(
                                message = message,
                                modifier = Modifier.padding(vertical = 5.dp).testTag(message.first),
                                onChatClick = {

                                }
                            )
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .weight(0.2f)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = newMessage,
                        onValueChange = {
                            newMessage = it
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        label = {
                            Text(
                                text = "Type your message",
                                fontSize = 15.sp
                            )
                        },
                        textStyle = TextStyle(
                            color = Color.Black
                        )
                    )
                    IconButton(
                        onClick = {
                            onSendClick(newMessage)
                            newMessage = ""
                        },
                        modifier = Modifier.weight(0.1f)
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(
    message: Pair<String,MessageType>,
    modifier: Modifier = Modifier,
    onChatClick: () -> Unit
) {
    Row(
        horizontalArrangement = if(MessageType.Sent == message.second) Arrangement.End else Arrangement.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.clickable(onClick = onChatClick)
        ) {
            Text(
                text = message.first,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    state: MutableState<String>,
    onCallClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    //var localState by remember { mutableStateOf(state) }
    OutlinedTextField(
        value = state.value,
        onValueChange = {value ->
            state.value = value
        },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp),
                tint = Color.Black
            )
        },
        trailingIcon = {
            if (state.value != "") {
                Row() {
                    IconButton(
                        onClick = {
                            onCallClick(state.value)
                            Log.d("Rahul", state.value)
                        }
                    ) {
                        Icon(
                            Icons.Default.Call,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp),
                            tint = Color.Black
                        )
                    }
                    IconButton(
                        onClick = {
                            state.value = "" // Remove text from TextField when you press the 'X' icon
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.secondary, fontSize = 20.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}