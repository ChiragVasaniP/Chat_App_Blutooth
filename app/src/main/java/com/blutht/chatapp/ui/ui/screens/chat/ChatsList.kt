package com.blutht.chatapp.ui.ui.screens.chat

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blutht.chatapp.ui.ui.composeItems.chat.ChatListItem


val dummyChats: List<ChatsMessage>
    @ReadOnlyComposable
    get() = listOf(
        ChatsMessage(userName = "Bryan", lastMessage = "What do you think?", time = "4:30 PM", badgeCount = 1),
        ChatsMessage(userName = "Kari", lastMessage = "Looks great!", time = "4:23 PM", badgeCount = 2),
        ChatsMessage(userName = "Diana", lastMessage = "Lunch on Monday?", time = "4:12 PM", badgeCount = 5),
        ChatsMessage(userName = "Ben", lastMessage = "You sent a photo.", time = "3:58 PM", badgeCount = 8),
        ChatsMessage(userName = "Naomi", lastMessage = "Naomi sent a photo.", time = "3:23 PM", badgeCount = 0),
        ChatsMessage(userName = "Alicia", lastMessage = "See you at 8.", time = "9:23 PM", badgeCount = 0),
    )


data class ChatsMessage(
    val userName: String,
    val lastMessage: String,
    val time: String,
    val badgeCount: Int?,
)

@Preview
@Composable
fun ChatsList(navController: NavHostController= rememberNavController()) {
    LazyColumn {
        itemsIndexed(dummyChats) { _, item ->
            ChatListItem(item,navController)
        }
    }
}

