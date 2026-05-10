package com.example.auth.data.remote.oauth

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import javax.inject.Inject

class GoogleOAuthLauncherImpl @Inject constructor(
    private val context: Context
): GoogleOAuthLauncher {

    override fun launch() {
        val authUrl = "http://msk-w2xx4pwpx7.tail89cf13.ts.net:8080/api/1/auth/google/start"

        val customTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .build()

        customTabsIntent.launchUrl(
            context,
            authUrl.toUri()
        )
    }
}
