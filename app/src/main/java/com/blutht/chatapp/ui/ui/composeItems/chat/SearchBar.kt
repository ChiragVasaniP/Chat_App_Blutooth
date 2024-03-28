package com.blutht.chatapp.ui.ui.composeItems.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews

@Composable
fun SearchBar() {
    val modifier = Modifier.fillMaxWidth()
    var myTextRemember by remember { mutableStateOf("") }

    TextField(
        value = myTextRemember,
        placeholder = { Text(text = "Search") },
        onValueChange = { myTextRemember = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .background(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.surface
            ),
        singleLine = true,
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(24.dp)
                    .padding(start = 5.dp),
                painter = painterResource(id = R.drawable.ic_search_app),
                contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.colors(
            errorContainerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
        )
    )

}

@ThemePreviews
@Composable
fun PreviewSearchBar() {
    ChatAppTheme() {
        Surface {
            SearchBar()
        }
    }
}