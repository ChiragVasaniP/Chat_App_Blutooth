package com.blutht.chatapp.ui.ui.composeItems.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews
import com.blutht.chatapp.ui.theme.robotoFamily

@Composable
fun UserVerticalContacts() {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(start = 13.dp, end = 13.dp, top = 6.dp, bottom = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = "User Profile",
            modifier = Modifier
                .clip(CircleShape)
                .size(56.dp)
        )

        Text(
            text = "Bryan",
            fontSize = 14.sp,
            letterSpacing = 0.25.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W400,
            fontFamily = robotoFamily,
            maxLines = 1,
            modifier = Modifier
                .padding(top = 5.dp)
                .wrapContentWidth()
        )
    }
}

@ThemePreviews
@Composable
fun PreviewUserVerticalContacts() {
    ChatAppTheme() {
        Surface {
            UserVerticalContacts()
        }
    }
}