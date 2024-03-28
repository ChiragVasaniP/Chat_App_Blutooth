package com.blutht.chatapp.ui.ui.composeItems

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blutht.chatapp.R
import com.blutht.chatapp.ui.navHost.Screens
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews

data class TabBarItem(
    val tabId: String,
    val tabName: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val badgeAmount: Int? = null
)

val tabBarItems: List<TabBarItem>
    get() = listOf(
        TabBarItem(
            tabId = Screens.ChatsList.route,
            tabName = Screens.ChatsList.name,
            selectedIcon = R.drawable.ic_home,
            unselectedIcon = R.drawable.ic_home
        ),
        TabBarItem(
            tabId = Screens.Contacts.route,
            tabName = Screens.Contacts.name,
            selectedIcon = R.drawable.ic_contacts,
            unselectedIcon = R.drawable.ic_contacts
        ),
        TabBarItem(
            tabId = Screens.Settings.route,
            tabName = Screens.Settings.name,
            selectedIcon = R.drawable.ic_settings,
            unselectedIcon = R.drawable.ic_settings
        )
    )

@Composable
fun BottomBarView(navController: NavController) {

    val navyControllerBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        tabBarItems.forEachIndexed { index, tabBarItem ->
            Log.e("TAG_check", "BottomBarView: ${navyControllerBackStackEntry?.destination?.route}")
            NavigationBarItem(
                selected = navyControllerBackStackEntry?.destination?.route.equals(tabBarItem.tabId),
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                ),
                onClick = {
  /*                  navController.navigate(tabBarItem.tabId) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }*/


                },
                icon = {
                    TabBarIconView(
                        isSelected = navyControllerBackStackEntry?.destination?.route.equals(
                            tabBarItem.tabId
                        ),
                        selectedIcon = tabBarItem.selectedIcon,
                        unselectedIcon = tabBarItem.unselectedIcon,
                        tabId = tabBarItem.tabId,
                        badgeAmount = tabBarItem.badgeAmount
                    )
                },
                label = { Text(tabBarItem.tabName) })
        }
    }
}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: Int,
    unselectedIcon: Int,
    tabId: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            modifier = Modifier.padding(vertical = 3.dp, horizontal = 3.dp),
            painter = if (isSelected) {
                painterResource(id = selectedIcon)
            } else {
                painterResource(id = unselectedIcon)
            },
            contentDescription = tabId
        )
    }
}

@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}

@ThemePreviews
@Composable
fun PreviewBottomBarView() {
    ChatAppTheme {
        Surface {
            BottomBarView(
                navController = NavController(LocalContext.current)
            )
        }
    }
}

