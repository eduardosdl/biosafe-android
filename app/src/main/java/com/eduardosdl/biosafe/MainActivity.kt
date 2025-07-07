package com.eduardosdl.biosafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.eduardosdl.biosafe.presentation.home.UsersScreen
import com.eduardosdl.biosafe.theme.BiosafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BiosafeTheme {
                Surface {
                    UsersScreen()
                }
            }
        }
    }
}