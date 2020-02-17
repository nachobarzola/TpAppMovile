package com.example.appmovil.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.example.appmovil.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public static final String EVENTO_01="Evento creado";
    public static final String EVENTO_02="Invitación enviada";
    public static final String CHANNEL_ID="GENERAL";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder mBuilder=
                new NotificationCompat.Builder(context,CHANNEL_ID)
                        .setSmallIcon(R.drawable.add_icon_contact)
                .setContentTitle(intent.getExtras().getString("data1"))
                .setContentText(intent.getExtras().getString("data2"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);
        notificationManager.notify(99, mBuilder.build());
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.CHANNEL_NAME);
            String description = context.getString(R.string.CHANNEL_DESCRIPTION);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
