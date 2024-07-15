package com.blutht.chatapp.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

private var storagePermissions = mutableListOf(
    android.Manifest.permission.BLUETOOTH,
    android.Manifest.permission.READ_CONTACTS,
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.BLUETOOTH_ADMIN
)

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
private var storagePermission33 = mutableListOf(
    android.Manifest.permission.BLUETOOTH,
    android.Manifest.permission.READ_CONTACTS,
    android.Manifest.permission.BLUETOOTH_SCAN,
    android.Manifest.permission.BLUETOOTH_ADVERTISE,
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
    android.Manifest.permission.ACCESS_FINE_LOCATION,
    android.Manifest.permission.BLUETOOTH_CONNECT,
)


fun getPermissionList(): MutableList<String> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        storagePermission33
    } else {
        storagePermissions
    }
}


fun Context.hasPermissions(vararg permissions: String) = permissions.all {
    this.isPermissionGranted(it)
}

fun Context.isPermissionGranted(permission: String) = ActivityCompat.checkSelfPermission(
    this,
    permission
) == PackageManager.PERMISSION_GRANTED