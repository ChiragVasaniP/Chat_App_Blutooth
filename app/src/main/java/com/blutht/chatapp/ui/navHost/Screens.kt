package com.blutht.chatapp.ui.navHost

sealed class Screens(val route: String,val name:String) {
    data object ChatsList : Screens("ChatsList","Chat")
    data object Contacts : Screens("Contacts","Contacts")
    data object Settings : Screens("Settings","Settings")
    data object Messages : Screens("Messages","Messages")
    data object SendProfile : Screens("SendProfile","SendProfile")


}
