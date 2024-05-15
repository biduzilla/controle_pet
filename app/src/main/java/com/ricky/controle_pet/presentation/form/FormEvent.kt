package com.ricky.controle_pet.presentation.form

import android.net.Uri

sealed interface FormEvent {

    data class SelectPhoto(val uri: Uri?) : FormEvent
}