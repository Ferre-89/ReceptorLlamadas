package com.example.receptorllamadas;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Objects;

public class ReceptorLlamadas extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Obtenemos información de la intención
        Log.d("Rodrigo", "ReceptorLlamadas");

        String action = intent.getAction();

        // Check if the action string matches the expected value for PHONE_STATE
        if (TelephonyManager.ACTION_PHONE_STATE_CHANGED.equals(action)) {

            String estado, numero;
            Bundle extras = intent.getExtras();
            Log.d("Rodrigo", "ReceptorLlamadas - IF, extras: " + extras);
            if (extras != null) {
                estado = extras.getString(TelephonyManager.EXTRA_STATE);

                if (Objects.equals(estado, TelephonyManager.EXTRA_STATE_RINGING)) {
                    numero = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    String info = estado + " " + numero;

                    Log.d("Rodrigo", "estado: " + estado + ", numero: " + numero + ", info:" + info + ", intent: " + intent);

                    NotificationManager notificationManager;
                    notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

                    NotificationChannel notificacionChannel = new NotificationChannel("1", "Mis notificaciones",
                            NotificationManager.IMPORTANCE_DEFAULT);

                    notificacionChannel.setDescription("Descripción del canal");
                    notificationManager.createNotificationChannel(notificacionChannel);

                    PendingIntent intentMain = PendingIntent.getActivity(context, 0,
                            new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder notificacion = new NotificationCompat.Builder(context, "1")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentIntent(intentMain)
                            .setContentText(numero)
                            .setWhen(System.currentTimeMillis() + 1000 * 60 * 60)
                            .setContentInfo("Mas info")
                            .setTicker("Texto en barra de estado");


                    notificationManager.notify(1, notificacion.build());
                }
            }
        }
    }
}
