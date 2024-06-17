package com.ricky.controle_pet.utils.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ricky.controle_pet.utils.Constants

class NotificacaoAgendamento : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val title = p1?.getStringExtra(Constants.titleExtra)
        val message = p1?.getStringExtra(Constants.messageExtra)

        if (title != null && p0 != null && message != null) {
            NotificationService(context = p0).basicNotification(title, message)
        }
    }
}