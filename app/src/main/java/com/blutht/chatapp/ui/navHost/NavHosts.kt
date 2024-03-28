package com.blutht.chatapp.ui.navHost

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blutht.chatapp.ui.ui.screens.chat.ChatsList
import com.blutht.chatapp.ui.ui.screens.chat.Messages
import com.blutht.chatapp.ui.ui.screens.chat.SenderProfile
import com.blutht.chatapp.ui.ui.screens.contact.Contact
import com.blutht.chatapp.ui.ui.screens.settings.Settings


val LocalNavController =
    compositionLocalOf<NavHostController> { error("Local Host Not Provider Found") }

@Composable
fun NavHostChatApp(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {


    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = Screens.ChatsList.route,
        enterTransition = { fadeIn(tween(1000)) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn(tween(1000)) },
        popExitTransition = { fadeOut(tween(500)) }
    ) {
        homeScreenView(navController)
        chatGraph(navController)
    }
}

fun NavGraphBuilder.homeScreenView(navController: NavHostController) {
    composable(
        route = Screens.ChatsList.route,
        enterTransition = { fadeIn(tween(1000)) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn(tween(1000)) },
        popExitTransition = { fadeOut(tween(500)) }
    ) {
        ChatsList(navController)
    }

    composable(
        route = Screens.Contacts.route,
        enterTransition = { fadeIn(tween(1000)) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn(tween(1000)) },
        popExitTransition = { fadeOut(tween(500)) }
    ) {
        Contact(navController)
    }
    composable(
        route = Screens.Settings.route,
        enterTransition = { fadeIn(tween(1000)) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn(tween(1000)) },
        popExitTransition = { fadeOut(tween(500)) }
    ) {
        Settings(navController)
    }

}

fun NavGraphBuilder.chatGraph(navController: NavController) {
    composable(
        route = Screens.Messages.route,
        enterTransition = { fadeIn(tween(1000)) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn(tween(1000)) },
        popExitTransition = { fadeOut(tween(500)) },

        ) {
        Messages(navController)
    }

    composable(
        route = Screens.SendProfile.route,
        enterTransition = { fadeIn(tween(1000)) },
        exitTransition = { fadeOut(tween(500)) },
        popEnterTransition = { fadeIn(tween(1000)) },
        popExitTransition = { fadeOut(tween(500)) },

        ) {
        SenderProfile(navController)
    }

}


