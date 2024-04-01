package com.blutht.chatapp.ui.ui.composeItems.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.navHost.Screens
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews
import com.blutht.chatapp.ui.theme.robotoFamily
import com.blutht.chatapp.ui.theme.unseenColor
import com.blutht.chatapp.ui.ui.screens.chat.ChatsMessage
import com.blutht.chatapp.ui.ui.screens.chat.dummyChats


@Composable
fun ChatListItem(message: ChatsMessage, navController: NavHostController) {


    ConstraintLayout(
        modifier = Modifier
            .clickable { navController.navigate(Screens.Messages.route) }
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background))
    {
        val (userImage, userName, lastMessage, time, unseenCount) = createRefs()
        val startGuideline = createGuidelineFromStart(0.03f)
        val endGuideLine = createGuidelineFromEnd(0.03f)
        val topGuideLine = createGuidelineFromTop(0.1f)
        val bottomGuideLine = createGuidelineFromBottom(0.1f)

        createVerticalChain(userName, lastMessage, chainStyle = ChainStyle.Packed(0.5f))

        Image(painter = painterResource(id = R.drawable.ic_user),
            contentDescription = "User Profile",
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp)
                .constrainAs(userImage) {
                    top.linkTo(topGuideLine)
                    start.linkTo(startGuideline)
                    bottom.linkTo(bottomGuideLine)
                    width = Dimension.fillToConstraints
                })

        Text(text = message.userName,
            fontSize = 16.sp,
            fontFamily = robotoFamily,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(userName) {
                    top.linkTo(userImage.top)
                    top.linkTo(topGuideLine)
                    start.linkTo(userImage.end)
                    bottom.linkTo(lastMessage.top)
                    end.linkTo(time.start)
                    width = Dimension.fillToConstraints
                })

        Text(text = message.lastMessage,
            fontSize = 11.sp,
            maxLines = 1,
            fontFamily = robotoFamily,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(lastMessage) {
                    top.linkTo(userName.bottom)
                    start.linkTo(userImage.end)
                    bottom.linkTo(userImage.bottom)
                    bottom.linkTo(bottomGuideLine)
                    end.linkTo(time.start)
                    width = Dimension.fillToConstraints
                })

        Text(text = message.time,
            fontSize = 11.sp,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = robotoFamily,
            modifier = Modifier.constrainAs(time) {
                top.linkTo(topGuideLine)
                end.linkTo(endGuideLine)
            })

        if (message.badgeCount !=null && message.badgeCount > 0) {
            Box(modifier = Modifier
                .padding(top = 10.dp)
                .size(25.dp)
                .clip(CircleShape)
                .background(unseenColor)
                .constrainAs(unseenCount) {
                    start.linkTo(time.start)
                    top.linkTo(time.bottom)
                    end.linkTo(time.end)
                    top.linkTo(topGuideLine)
                    bottom.linkTo(bottomGuideLine)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }) {
                Text(
                    text = message.badgeCount.toString(),
                    color = Color.White,
                    fontSize = 10.sp,
                    maxLines = 1,
                    fontFamily = robotoFamily,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

        }
    }

}


@ThemePreviews
@Composable
fun PreviewChatList(){
    ChatAppTheme() {
        Surface {
            ChatListItem(dummyChats[1], rememberNavController())
        }
    }
}