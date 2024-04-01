package com.blutht.chatapp

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.blutht.chatapp.ui.navHost.NavHostChatApp
import com.blutht.chatapp.ui.navHost.Screens
import com.blutht.chatapp.ui.theme.ChatAppTheme
import com.blutht.chatapp.ui.theme.ThemePreviews
import com.blutht.chatapp.ui.ui.composeItems.BottomBarView
import com.blutht.chatapp.ui.ui.composeItems.BottomBarViewHome
import com.blutht.chatapp.ui.ui.composeItems.FloatingActionButtonDefaults
import com.blutht.chatapp.ui.ui.composeItems.SearchBarViewHome
import com.blutht.chatapp.ui.ui.composeItems.SendMessageBoxHome
import com.blutht.chatapp.ui.ui.composeItems.UserChatViewTopViewHome
import com.blutht.chatapp.ui.ui.composeItems.chat.SearchBar
import com.blutht.chatapp.ui.ui.composeItems.chat.SendMessageBox
import com.blutht.chatapp.ui.ui.composeItems.chat.UserChatTopBar
import com.blutht.chatapp.utils.BluetoothManager
import com.blutht.chatapp.utils.BluetoothManagerCallback
import com.blutht.chatapp.utils.getPermissionList

class MainActivity : ComponentActivity(), BluetoothManagerCallback {

    private lateinit var bluetoothManager: BluetoothManager

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        )
        { permissions ->
            // Handle Permission granted/rejected
            val isGranted = permissions.all { it.value }
            if (isGranted) {
                bluetoothManager.enableBluetooth()
            } else {

            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothManager = BluetoothManager(this, this)
        bluetoothManager.enableBluetooth()
        setContent {
            ChatAppTheme {
                Main(rememberNavController())
            }
        }
    }

    override fun onBluetoothEnabled() {
    }

    override fun onBluetoothEnableFailed() {
    }

    override fun onPermissionGranted() {
    }

    override fun onPermissionDenied() {
        activityResultLauncher.launch(getPermissionList().toTypedArray())
    }

    override fun onBluetoothDevicesFound(devices: List<BluetoothDevice>) {
    }
}

@ThemePreviews
@Composable
fun Main(navController: NavHostController = rememberNavController()) {
    val navyControllerBackStackEntry by navController.currentBackStackEntryAsState()


    Scaffold(
        bottomBar = {
            var isVisibleHomeBottomBar by remember { mutableStateOf(true) }
            var isVisibleMessageBottomBar by remember { mutableStateOf(false) }
            when (navyControllerBackStackEntry?.destination?.route) {
                Screens.ChatsList.route, Screens.Contacts.route, Screens.Settings.route -> {
                    isVisibleMessageBottomBar = false
                    isVisibleHomeBottomBar = true
                }

                Screens.Messages.route -> {
                    isVisibleMessageBottomBar = true
                    isVisibleHomeBottomBar = false
                }
                Screens.SendProfile.route->{
                    isVisibleMessageBottomBar = false
                    isVisibleHomeBottomBar = false
                }
            }
            BottomBarViewHome(
                navController = navController,
                isVisibleHomeBottomBar = isVisibleHomeBottomBar
            )
            SendMessageBoxHome(isVisibleMessageBottomBar = isVisibleMessageBottomBar)
        },
        topBar = {
            var isVisibleSearchBar by remember { mutableStateOf(true) }
            var isVisibleUserChatTopBar by remember { mutableStateOf(false) }
            when (navyControllerBackStackEntry?.destination?.route) {
                Screens.ChatsList.route, Screens.Contacts.route -> {
                    isVisibleSearchBar = true
                    isVisibleUserChatTopBar = false
                }
                Screens.Settings.route->{
                    isVisibleSearchBar = false
                    isVisibleUserChatTopBar = false
                }

                Screens.Messages.route -> {
                    isVisibleSearchBar = false
                    isVisibleUserChatTopBar = true
                }
                Screens.SendProfile.route->{
                    isVisibleSearchBar = false
                    isVisibleUserChatTopBar = false
                }
            }
            SearchBarViewHome(isVisibleSearchBar = isVisibleSearchBar)
            UserChatViewTopViewHome(navController = navController, isVisibleMessageTopBar = isVisibleUserChatTopBar)

        },
        floatingActionButton = {
            var isVisibleFloatingButton by remember { mutableStateOf(true) }
            isVisibleFloatingButton=   when (navyControllerBackStackEntry?.destination?.route) {
                Screens.ChatsList.route, Screens.Contacts.route-> {
                     true
                }
                Screens.Messages.route,Screens.Settings.route,Screens.SendProfile.route -> {
                     false
                }
                else -> true
            }
            FloatingActionButtonDefaults(isVisibleFloatingButton)
        },
    ) {

        NavHostChatApp(navController = navController, it)
    }
}





