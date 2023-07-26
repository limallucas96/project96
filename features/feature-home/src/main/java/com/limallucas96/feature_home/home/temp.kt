//package com.limallucas96.feature_home.home
//
//import android.content.Context
//import android.content.pm.PackageManager
//import android.os.Build
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import java.util.jar.Manifest
//
//inline fun Fragment.requestPermission(
//    permission: String,
//    crossinline granted: (permission: String) -> Unit = {},
//    crossinline denied: (permission: String) -> Unit = {},
//    crossinline explained: (permission: String) -> Unit = {}
//
//) {
//    registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
//        when {
//            result -> granted.invoke(permission)
//            shouldShowRequestPermissionRationale(permission) -> denied.invoke(permission)
//            else -> explained.invoke(permission)
//        }
//    }.launch(permission)
//}
//
//
//inline fun Fragment.requestMultiplePermissions(
//    vararg permissions: String,
//    crossinline allGranted: () -> Unit = {},
//    crossinline denied: (List<String>) -> Unit = {},
//    crossinline explained: (List<String>) -> Unit = {}
//) {
//    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result: MutableMap<String, Boolean> ->
//
//        val deniedList = result.filter { !it.value }.map { it.key }
//        when {
//            deniedList.isNotEmpty() -> {
//
//                val map = deniedList.groupBy { permission ->
//                    if (shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
//                }
//
//                map[DENIED]?.let { denied.invoke(it) }
//
//                map[EXPLAINED]?.let { explained.invoke(it) }
//            }
//            else -> allGranted.invoke()
//        }
//    }.launch(permissions)
//}
//
//inline fun Fragment.storagePermissions(
//    context: Context,
//    crossinline actionOnGranted: () -> Unit,
//    crossinline actionOnDeclined: () -> Unit,
//    crossinline actionRepeat: () -> Unit
//) {
//    when {
//        Build.VERSION.SDK_INT < Build.VERSION_CODES.Q -> {
//
//            if (
//                ContextCompat.checkSelfPermission(
//                    context, Manifest.permission.READ_EXTERNAL_STORAGE
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                actionOnGranted()
//            } else {
//                android.Manifest.permission(
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//                ) {
//                    granted = {
//                        actionOnGranted()
//                    }
//                    denied = {
//                        actionRepeat()
//                    }
//                    explained = {
//                        actionOnDeclined()
//                    }
//                }
//            }
//        }
//
//        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
//            if (
//                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_MEDIA_LOCATION
//                ) == PackageManager.PERMISSION_GRANTED) {
//                Log.d("Storage Permission", "Permission already granted.")
//                actionOnGranted()
//            } else {
//                Log.d("Storage Permission", "No Permission Yet -> Ask for it!")
//                permissions(
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_MEDIA_LOCATION
//                ) {
//                    allGranted = {
//                        actionOnGranted()
//                    }
//                    denied = {
//                        Log.d("Storage Permission", "Denied")
//                        actionRepeat()
//                    }
//                    explained = {
//                        Log.d("Storage Permission", "Permanently Denied")
//                        actionOnDeclined()
//                    }
//                }
//            }
//        }
//    }
//}