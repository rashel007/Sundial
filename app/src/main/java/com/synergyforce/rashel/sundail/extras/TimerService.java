package com.synergyforce.rashel.sundail.extras;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.synergyforce.rashel.sundail.R;
import com.synergyforce.rashel.sundail.views.MainActivity;

/**
 * @author Estique Ahmed Rashel
 */

public class TimerService extends Service {

    private final static String TAG = "TimerService";

    public static final String COUNTDOWN_BR = "com.synergyforce.rashel.sundail.views.MainActivity";
    Intent mIntent = new Intent(COUNTDOWN_BR);

    CountDownTimer cdt = null;
    private static boolean started = false;

    @Override
    public void onCreate() {
        super.onCreate();



        int counterEndAt = Integer.parseInt(Constants.SELECTED_END_TIME) * 60 *1000;
        Log.i(TAG, "Starting timer..." + counterEndAt);
        cdt = new CountDownTimer(counterEndAt, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Constants.TIME_PASSED_SECCONDS = String.valueOf( millisUntilFinished / 1000);
                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
                mIntent.putExtra("countdown", millisUntilFinished / 1000);
                sendBroadcast(mIntent);
                started = true;
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                cdt.cancel();
                started = false;
                stopSelf();
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!started) {
            cdt.start();
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.putExtra("Timer","Started");
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("GlassClock Started ")
                    .setTicker("")
                    .setContentText("")
                    .setWhen(System.currentTimeMillis())
                    .setUsesChronometer(true)
                    .setSmallIcon(R.mipmap.ic_sundail)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .build();

            startForeground(101, notification);
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
