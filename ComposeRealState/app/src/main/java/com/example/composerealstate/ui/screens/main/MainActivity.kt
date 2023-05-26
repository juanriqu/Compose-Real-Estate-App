package com.example.composerealstate.ui.screens.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.composerealstate.ui.common.NavGraph
import com.example.composerealstate.ui.theme.ComposeRealStateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configSplashScreen()
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1)
        }
        setContent {
            ComposeRealStateTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                )
            }
        }
    }

    private fun configSplashScreen() {
        val splashScreen = installSplashScreen()
        Thread.sleep(2000)
        splashScreen.setKeepOnScreenCondition {
            false
        }
    }
}