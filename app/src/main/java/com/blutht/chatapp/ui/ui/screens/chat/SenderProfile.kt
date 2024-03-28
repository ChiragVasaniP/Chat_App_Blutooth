package com.blutht.chatapp.ui.ui.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews
import com.blutht.chatapp.ui.theme.robotoFamily
import com.blutht.chatapp.ui.theme.unseenColor
import com.blutht.chatapp.ui.ui.screens.settings.SettingList
import com.blutht.chatapp.ui.ui.screens.settings.SettingsItem


val getUserEditProfileList: ArrayList<UserEditProfile>
    @ReadOnlyComposable get() {
        return arrayListOf(
            UserEditProfile(R.drawable.ic_edit, "Message"),
            UserEditProfile(R.drawable.ic_call, "Call"),
            UserEditProfile(R.drawable.ic_notifications, "Mute"),
        )
    }

val moreActionList: ArrayList<SettingsItem>
    @ReadOnlyComposable
    get() = arrayListOf(
        SettingsItem(title = "View Media", icon = R.drawable.ic_image),
        SettingsItem(title = "Search Conversation", icon = R.drawable.ic_search_users),
        SettingsItem(title = "Notification", icon = R.drawable.ic_notifications)
    )

data class UserEditProfile(
    val image: Int, val title: String
)

@Composable
fun SenderProfile(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
        ) {
            Image(
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .size(24.dp)
                    .padding(2.5.dp)
                    .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Search"
            )

            Image(
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .padding(start = 30.dp)
                    .size(24.dp)
                    .padding(2.5.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = "More"
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = "User Profile",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)

        )

        Text(
            text = "Bryan",
            fontSize = 32.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.W400,
            modifier = Modifier.padding(top = 10.dp),
            color = MaterialTheme.colorScheme.onSurface
        )


        Text(
            text = "+14844533842",
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            modifier = Modifier.padding(top = 10.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .fillMaxWidth()
                .width(intrinsicSize = IntrinsicSize.Max)
        ) {

            repeat(getUserEditProfileList.size) {
                UserSettingItem(getUserEditProfileList[it], modifier = Modifier.weight(1f))
            }
        }

        Text(
            text = "More Action",
            fontSize = 14.sp,
            lineHeight = 28.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .wrapContentWidth()
                .align(Alignment.Start)
        )

        repeat(moreActionList.size) {
            SettingList(item = moreActionList[it])
        }
    }
}


@Composable
fun UserSettingItem(item: UserEditProfile, modifier: Modifier) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = "User Profile",
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(unseenColor)
            )
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 16.sp,
                letterSpacing = 0.25.sp,
                modifier = Modifier.padding(top = 6.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

}

@ThemePreviews
@Composable
fun PreviewSenderProfile() {
    ChatAppTheme {
        Surface {
            SenderProfile(rememberNavController())
        }
    }

}

