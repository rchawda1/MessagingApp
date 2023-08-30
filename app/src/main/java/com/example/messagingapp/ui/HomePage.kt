package com.example.messagingapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messagingapp.data.ContactDetails
import com.example.messagingapp.data.Datasource.ContactList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    onItemClick: (ContactDetails) -> Unit,
    onMessageClick: (String) -> Unit
) {
    val textState = remember { mutableStateOf("") }

    Scaffold (
        topBar = {
            TopAppBar (
                title = {
                    Text(
                        text = "Movius",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    /*
                    Text(
                        text = "Movius",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                    )*/
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.LightGray)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column (
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Chat",
                    fontSize = 35.sp,
                    modifier = Modifier.weight(0.3f),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                SearchBar(
                    state = textState,
                    onCallClick = onMessageClick,
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(5.dp)
                )
                Divider(
                    thickness = 2.dp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                )
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.weight(2f)
                ) {
                    ChatList(
                        contacts = ContactList,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}

@Composable
fun ChatList(
    contacts: MutableList<ContactDetails>,
    onItemClick: (ContactDetails) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        items(contacts) {contact ->
            ChatItem(
                contact = contact,
                onItemClick = { onItemClick(contact) },
                read = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(contact.name)
                    //.padding(5.dp)
            )
            Divider(
                thickness = 2.dp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun ChatItem(
    contact: ContactDetails,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
    read: Boolean
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.clickable(onClick = onItemClick)
    ) {
        Image(
            imageVector = Icons.Default.Face,
            contentDescription = null,
            modifier = Modifier
                .weight(0.3f)
                .height(50.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
            contentScale = ContentScale.Fit
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = contact.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                //modifier = Modifier.weight(1f),
                color = Color.Black
            )
            val last = contact.messages.last()
            Text(
                text = last.first,
                fontSize = 20.sp,
                //modifier = Modifier.weight(1f),
                color = Color.Black
            )
        }
        if(!read) {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = null,
                modifier = Modifier.weight(0.3f)
            )
        }
    }
}