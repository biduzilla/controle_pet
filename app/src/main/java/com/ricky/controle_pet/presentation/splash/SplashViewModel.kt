package com.ricky.controle_pet.presentation.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SplashViewModel : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    init {
        tempoEspera()
    }

    private fun tempoEspera() {
        Handler(Looper.getMainLooper()).postDelayed({
            _state.value = _state.value.copy(
                isLoading = true
            )
        }, 3000)
    }
}