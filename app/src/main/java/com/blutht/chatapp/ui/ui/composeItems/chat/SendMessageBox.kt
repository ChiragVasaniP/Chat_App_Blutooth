package com.blutht.chatapp.ui.ui.composeItems.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews


@Composable
fun SendMessageBox() {
    var myTextRemember by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = "Capture Image Camera",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1.9f).scale(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_gallery),
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = "Image Choose",
            modifier = Modifier.weight(1.9f).scale(1f)
        )

        TextField(
            value = myTextRemember,
            placeholder = { Text(text = "Write Message") },
            onValueChange = { myTextRemember = it },
            modifier = Modifier
                .weight(8f)
                .background(shape = RoundedCornerShape(30.dp),  color = MaterialTheme.colorScheme.surface),
            singleLine = false,
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
}

@ThemePreviews
@Composable
fun PreviewSendMessageBox() {
    ChatAppTheme {
        Surface {
            SendMessageBox()
        }
    }
}