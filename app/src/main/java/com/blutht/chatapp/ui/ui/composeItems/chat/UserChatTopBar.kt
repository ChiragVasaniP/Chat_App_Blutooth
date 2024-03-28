package com.blutht.chatapp.ui.ui.composeItems.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.navHost.Screens
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews
import com.blutht.chatapp.ui.theme.robotoFamily

@Composable
fun UserChatTopBar(navController: NavHostController) {

    var expanded by remember { mutableStateOf(false) }
    var offString by remember { mutableStateOf(Offset(0.0f, 0.0f)) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val (imgBack, userName, imgCall, imgSearch,
            imgMenu) = createRefs()

        val startGuideline = createGuidelineFromStart(0.03f)
        val endGuideLine = createGuidelineFromEnd(0.03f)
        val topGuideLine = createGuidelineFromTop(0.25f)
        val bottomGuideLine = createGuidelineFromBottom(0.25f)


        Image(painter = painterResource(id = R.drawable.ic_back),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
            contentDescription = "Back",
            modifier = Modifier
                .clickable { navController.navigateUp() }
                .size(24.dp)
                .padding(3.20.dp)
                .constrainAs(imgBack) {
                    top.linkTo(topGuideLine)
                    start.linkTo(startGuideline)
                    bottom.linkTo(bottomGuideLine)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                })

        Text(text = "Bryan",
            fontSize = 20.sp,
            lineHeight = 28.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Left,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .padding(start = 25.dp)
                .constrainAs(userName) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(bottomGuideLine)
                    start.linkTo(imgBack.end)
                    end.linkTo(imgCall.start)
                    width = Dimension.fillToConstraints
                })


        Image(painter = painterResource(id = R.drawable.ic_call),
            contentDescription = "Call",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
            modifier = Modifier
                .padding(start = 25.dp)
                .size(24.dp)
                .padding(3.20.dp)
                .constrainAs(imgCall) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(bottomGuideLine)
                    end.linkTo(imgSearch.start)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent

                })

        Image(painter = painterResource(id = R.drawable.ic_search_users),
            contentDescription = "Search",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
            modifier = Modifier
                .padding(start = 25.dp)
                .size(24.dp)
                .padding(3.20.dp)
                .constrainAs(imgSearch) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(bottomGuideLine)
                    end.linkTo(imgMenu.start)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent

                })

        Image(painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "Menu",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer),
            modifier = Modifier
                .padding(start = 25.dp)
                .onGloballyPositioned {
                    offString = it.positionInRoot()
                }
                .clickable {
                    expanded = expanded.not()
                }
                .size(24.dp)
                .padding(3.20.dp)
                .constrainAs(imgMenu) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(bottomGuideLine)
                    end.linkTo(endGuideLine)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                    horizontalChainWeight = 0.6f

                })
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        offset = DpOffset((offString.x).dp, 0.dp),
    ) {
        DropdownMenuItem(text = { Text(text = "Profile") }, onClick = {
            navController.navigate(Screens.SendProfile.route)
            expanded = false  })
        DropdownMenuItem(text = { Text(text = "Settings") }, onClick = { expanded = false })

    }
}


@ThemePreviews
@Composable
fun PreviewUserChatTopBar() {
    ChatAppTheme() {
        Surface {
            UserChatTopBar(rememberNavController())
        }
    }
}