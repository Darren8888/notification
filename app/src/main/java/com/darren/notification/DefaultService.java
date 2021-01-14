package com.darren.notification;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class DefaultService extends Service {

    private static final int TARGET_ID = 0x01;

    public static final String CMD_TAG = "cmd";
    public static final int SHOW = 1;
    public static final int HIDE = 2;

    private boolean isCreate = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle target = intent.getExtras();
        if (null != target) {
            int tag = target.getInt(CMD_TAG);
            showNotification(SHOW == tag);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        showNotification(false);
        super.onDestroy();
    }

    private void showNotification(boolean show) {
        if (show) {
            if (!isCreate) {
                Notification notification = NotificationUtil.createNotification(this, "darren1", "test1",
                        R.drawable.ic_launcher_background, null, 1010, NotificationUtil.CHANNEL_ID_1);
                startForeground(TARGET_ID, notification);
                isCreate = true;
            }
        } else {
            if (isCreate) {
                stopForeground(true);
                isCreate = false;
            }
        }
    }
}
