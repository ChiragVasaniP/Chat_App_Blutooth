package com.blutht.chatapp.ui.ui.composeItems

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.ui.composeItems.chat.SearchBar
import com.blutht.chatapp.ui.ui.composeItems.chat.SendMessageBox
import com.blutht.chatapp.ui.ui.composeItems.chat.UserChatTopBar


@Composable
fun FloatingActionButtonDefaults(isVisibleFloatingButton: Boolean) {
    AnimatedVisibility(visible = isVisibleFloatingButton,
        enter = slideInHorizontally(animationSpec = tween(durationMillis = 600)) { fullWidth ->
            -fullWidth / 1
        } + fadeIn(
            animationSpec = tween(durationMillis = 600)
        ),
        exit = slideOutHorizontally(animationSpec = tween(durationMillis = 600)) {
            200
        } + fadeOut(animationSpec = tween(durationMillis = 600))) {
        FloatingActionButton(
            onClick = { }, modifier = Modifier
                .padding(10.dp)
                .size(55.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Edit"
            )
        }
    }
}

@Composable
fun SendMessageBoxHome(isVisibleMessageBottomBar: Boolean) {
    AnimatedVisibility(visible = isVisibleMessageBottomBar, enter = fadeIn(), exit = fadeOut()) {
        SendMessageBox()
    }

}

@Composable
fun BottomBarViewHome(navController: NavController, isVisibleHomeBottomBar: Boolean) {
    AnimatedVisibility(visible = isVisibleHomeBottomBar, enter = fadeIn(), exit = fadeOut()) {
        BottomBarView(navController = navController)
    }
}

@Composable
fun SearchBarViewHome(isVisibleSearchBar: Boolean) {
    AnimatedVisibility(visible = isVisibleSearchBar, enter = slideInVertically(
        initialOffsetY = { -40 }
    ) + expandVertically(
        expandFrom = Alignment.Top
    ) + scaleIn(
        transformOrigin = TransformOrigin(0.5f, 0f)
    ) + fadeIn(initialAlpha = 0.3f), exit = slideOutVertically() + shrinkVertically() + fadeOut(tween(100)) + scaleOut(targetScale = 1.2f)) {
        SearchBar()
    }
}

@Composable
fun UserChatViewTopViewHome(navController: NavHostController, isVisibleMessageTopBar: Boolean) {
    AnimatedVisibility(visible = isVisibleMessageTopBar,
        enter = slideInVertically(
        tween(1000),
        initialOffsetY = { -40 })
                + expandVertically(   tween(1000),
        expandFrom = Alignment.Top)
                + scaleIn(transformOrigin = TransformOrigin(0.5f, 0f))
                + fadeIn(   tween(1000),initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically()
                + fadeOut(tween(1000))
                + scaleOut(targetScale = 1.2f))
    {
        UserChatTopBar(navController = navController)
    }
}


@Composable
@Preview(showBackground = true)
fun PreViewFloatingActionButtonDefaults() {
    FloatingActionButtonDefaults(isVisibleFloatingButton = true)
}

@Composable
@Preview(showBackground = true)
fun PreViewSendMessageBoxHome() {
    SendMessageBoxHome(isVisibleMessageBottomBar = true)
}

@Composable
@Preview(showBackground = true)
fun PreViewBottomBarViewHome() {
    BottomBarViewHome(isVisibleHomeBottomBar = true, navController = rememberNavController())
}

@Composable
@Preview(showBackground = true)
fun PreViewSearchBarViewHome() {
    SearchBarViewHome(isVisibleSearchBar = true)
}

@Composable
@Preview(showBackground = true)
fun PreViewUserChatViewTopViewHome() {
    UserChatViewTopViewHome(isVisibleMessageTopBar = true, navController = rememberNavController())
}
