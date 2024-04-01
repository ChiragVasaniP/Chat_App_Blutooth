package com.blutht.chatapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BluetoothManagerCallback {
    fun onBluetoothEnabled()
    fun onBluetoothEnableFailed()
    fun onPermissionGranted()
    fun onPermissionDenied()
    fun onBluetoothDevicesFound(devices: List<BluetoothDevice>)
}

@SuppressLint("MissingPermission")
class BluetoothManager(
    private val activity: Activity,
    private val callback: BluetoothManagerCallback
) {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun enableBluetooth() {
        if (!activity.hasPermissions(*getPermissionList().toTypedArray())) {
            callback.onPermissionDenied()
            return
        } else {
            if (bluetoothAdapter == null) {
                // Device doesn't support Bluetooth
                // Handle error or show message
                return
            }

            if (!bluetoothAdapter.isEnabled) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
                getAvailableBluetoothDevices()
            } else {
                callback.onBluetoothEnabled()
                getAvailableBluetoothDevices()
            }

        }

    }

    fun disableBluetooth() {
        bluetoothAdapter?.disable()
    }

    fun getAvailableBluetoothDevices() {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.let {
            callback.onBluetoothDevicesFound(it.toList())
        }
    }

    suspend fun getAvailableBluetoothDevicesStream(): List<BluetoothDevice>? {
        return withContext(Dispatchers.IO) {
            bluetoothAdapter?.bondedDevices?.toList()
        }
    }

    companion object {
        const val REQUEST_ENABLE_BT = 124
    }
}

