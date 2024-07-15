package com.blutht.chatapp.ui.ui.screens.contact

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun Contact(navController: NavHostController) {

    val context = LocalContext.current
     val contactRepository = ContactRepository(context)
    var contacts by remember { mutableStateOf(emptyList<Contact>()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            contactRepository.getContacts().collect {
                contacts = it
            }
        }
    }

    contacts.forEach { data ->
        Log.e("TAG_log", "Contact: ${data.name}")
    }

}
