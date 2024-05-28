package com.ricky.controle_pet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ricky.controle_pet.navigation.AppNav
import com.ricky.controle_pet.ui.theme.Controle_petTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Controle_petTheme {
                AppNav()
            }
        }
    }
}
