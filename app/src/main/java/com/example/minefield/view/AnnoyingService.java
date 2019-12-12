package com.example.minefield.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.minefield.R;

import java.util.Timer;
import java.util.TimerTask;

public class AnnoyingService extends Service {
    private static Timer timer = new Timer();
    private Context ctx;
    boolean isAppRunning = true;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MineSweeperAnnoyer";//getString(R.string.channel_name);
            String description = "It's goal is to annoy every player into playing the game.";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("MineSweeperAnnoyer", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = this;
        createNotificationChannel();
        startService();
    }

    private void startService() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new mainTask(), 5000, 5000);
    }

    private class mainTask extends TimerTask {
        int notificationId = 0;
        Notification notification;
        mainTask() {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx,"MineSweeperAnnoyer")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Hi!")
                    .setContentText("Why are you not playing?")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            notification = builder.build();
        }

        @Override
        public void run() {
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);
                notificationManager.notify(notificationId, notification);


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
