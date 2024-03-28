package com.blutht.chatapp.ui.ui.screens.settings

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews
import com.blutht.chatapp.ui.theme.robotoFamily


data class SettingsItem(
    val title: String,
    val icon: Int
)

val settingItems: ArrayList<SettingsItem>
    @ReadOnlyComposable
    get() = arrayListOf(
        SettingsItem(title = "Notifications", icon = R.drawable.ic_notifications),
        SettingsItem(title = "Appearance", icon = R.drawable.ic_apperances),
        SettingsItem(title = "Privacy", icon = R.drawable.ic_settings),
        SettingsItem(title = "Storage & Data", icon = R.drawable.ic_storage_data),
        SettingsItem(title = "About", icon = R.drawable.ic_about),

        )

@Composable
fun Settings(navController: NavHostController = rememberNavController()) {

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(2.5.dp),
                    painter = painterResource(id = R.drawable.ic_search_users),
                    contentDescription = "Search"
                )

                Image(
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .size(24.dp)
                        .padding(2.5.dp),
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "More"
                )
            }

            Text(
                text = "Settings",
                fontSize = 24.sp,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        item {
            UserSettingItem()
        }

        item {
            Text(
                text = "General",
                fontSize = 14.sp,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(vertical = 15.dp)
            )
        }

        itemsIndexed(settingItems) { _, item ->
            SettingList(item = item)
        }
    }
}


@Composable
fun SettingList(item: SettingsItem) {

    Row(
        modifier = Modifier
            .clickable {}
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier
                .size(24.dp)
                .padding(2.5.dp),
            painter = painterResource(id = item.icon),
            contentDescription = "Search"
        )

        Text(
            text = item.title,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(start = 12.dp)
        )

    }
}


@Composable
fun UserSettingItem(){
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp)) {

        val (imgUser, txtUserName,
            txtMobileNumber, editTag) = createRefs()

        createVerticalChain(txtUserName, txtMobileNumber, chainStyle = ChainStyle.Packed)

        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = "User Profile",
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .constrainAs(imgUser) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
        )


        Text(
            text = "John Doe",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(txtUserName) {
                    start.linkTo(imgUser.end, 15.dp)
                    top.linkTo(imgUser.top)
                    bottom.linkTo(imgUser.bottom)
                    end.linkTo(editTag.start)
                    bottom.linkTo(txtMobileNumber.top)
                    width = Dimension.fillToConstraints
                }
        )


        Text(
            text = "+91 9876543210",
            fontSize = 14.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(txtMobileNumber) {
                    start.linkTo(imgUser.end, 15.dp)
                    top.linkTo(txtUserName.bottom)
                    top.linkTo(txtUserName.top)
                    bottom.linkTo(imgUser.bottom)
                    end.linkTo(editTag.start)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = "Edit",
            fontSize = 14.sp,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(100))
                .padding(vertical = 5.dp, horizontal = 15.dp)
                .constrainAs(editTag) {
                    end.linkTo(parent.end)
                    start.linkTo(txtMobileNumber.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}


@ThemePreviews
@Composable
fun PreviewSettings() {
    ChatAppTheme {
        Surface {
            Settings()
        }
    }
}