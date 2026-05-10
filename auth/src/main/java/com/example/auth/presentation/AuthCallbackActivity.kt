package com.example.auth.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity

class AuthCallbackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun handleIntent(uri: Uri?) {
        val intent = Intent(
            this,
            Class.forName("com.example.app.MainActivity")
        )

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

        uri?.let {
            intent.data = it
        }

        startActivity(intent)
        finish()
    }
}
