package com.kaskin.manager.Controllers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class ConnectionChecker(context: Context) {
    private var appContext: Context
    private var toastController: ToastController

    init {
        appContext = context
        toastController = ToastController()
    }

    fun CheckConnection(): Boolean {
        var result = false
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    toastController.ToastFeedBackShort(appContext, "Wifi")
                    return true
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    toastController.ToastFeedBackShort(appContext, "Rede Móvel")
                    return true
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    toastController.ToastFeedBackShort(appContext, "Cabo")
                    return true
                }
                else -> {
                    toastController.ToastFeedBackShort(appContext, "Sem Conexão")
                    return false
                }
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> {
                            toastController.ToastFeedBackShort(appContext, "Wifi")
                            return true
                        }
                        ConnectivityManager.TYPE_MOBILE -> {
                            toastController.ToastFeedBackShort(appContext, "Rede Móvel")
                            return true
                        }
                        ConnectivityManager.TYPE_ETHERNET -> {
                            toastController.ToastFeedBackShort(appContext, "Cabo")
                            return true
                        }
                        else -> {
                            toastController.ToastFeedBackShort(appContext, "Sem Conexão")
                            return false
                        }
                    }

                }
            }
        }

        return result
    }
}