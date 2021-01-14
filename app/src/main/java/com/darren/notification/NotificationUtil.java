package com.darren.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

public class NotificationUtil {

    public static final String CHANNEL_ID_1 = "darren_test_1";
    public static final String CHANNEL_ID_2 = "darren_test_2";

    public static void showNotification(Context context, String title, String content, int smallRes, PendingIntent pendingIntent, int notificationId, String channelId) {
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(smallRes)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent);

        Notification notification = buildNotification(context, builder, channelId);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }

    public static Notification createNotification(Context context, String title, String content, int smallRes, PendingIntent pendingIntent, int notificationId, String channelId) {
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(smallRes)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent);

        return buildNotification(context, builder, channelId);
    }

    private static Notification buildNotification(Context context, Notification.Builder builder, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
            if (null == notificationChannel) {
                String channelName = "darren_channel";
                notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(notificationChannel);
            }

            builder.setChannelId(channelId);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return builder.getNotification();
        } else {
            return builder.build();
        }
    }
}
