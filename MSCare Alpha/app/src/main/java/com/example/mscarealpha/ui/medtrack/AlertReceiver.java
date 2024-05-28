package com.example.mscarealpha.ui.medtrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;

import com.example.mscarealpha.R;

public class AlertReceiver extends BroadcastReceiver {

    private static final String TAG = "AlertReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String reminderMessage = intent.getStringExtra("EXTRA_REMINDER_MESSAGE");  // Retrieve the custom message
        Log.d(TAG, "Received reminder: " + reminderMessage);
        createNotification(context, reminderMessage);
    }

    private void createNotification(Context context, String reminderMessage) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "medication_reminder",
                    "Medication Reminder",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Channel for Medication Reminder");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                Log.d(TAG, "Notification channel created");
            } else {
                Log.e(TAG, "NotificationManager is null");
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "medication_reminder")
                .setSmallIcon(R.drawable.icon_small_notification)  // Ensure you have this icon in your drawables
                .setContentTitle("Medication Reminder")
                .setContentText(reminderMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
            Log.d(TAG, "Notification sent");
        } else {
            Log.e(TAG, "NotificationManager is null");
        }
    }
}
