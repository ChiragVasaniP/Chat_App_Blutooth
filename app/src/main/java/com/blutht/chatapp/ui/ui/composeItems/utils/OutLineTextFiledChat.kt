package com.blutht.chatapp.ui.ui.composeItems.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews


@Composable
fun TextWithImageFiledChatApp() {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(Modifier.padding(start = 5.dp,end = 5.dp).size(45.dp)) {
            if (true) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(AssistChipDefaults.IconSize * 1.15f),
                    painter = painterResource(id = R.drawable.ic_search_app),
                    contentDescription = "Search"
                )
            }

        }




        OutlinedTextField(
            value = text,
            singleLine = true,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        )
    }


}


@Composable
@ThemePreviews
fun PreviewTextFiledChatApp() {
    ChatAppTheme {
        Surface {
            TextWithImageFiledChatApp()
        }
    }

}