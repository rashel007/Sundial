package com.synergyforce.rashel.sundail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;

/**
 * @author Estique Ahmed Rashel
 */

public class SundialBroadcustReceiver extends BroadcastReceiver {

    public static final String WAKE = "Wake up";
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {


        //start alarm
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);

        //setting the end time
        Constants.END_TIME = Utils.getTheCurrentDateAndTime();

        // start TimeEndActivity
        Intent mIntent = new Intent(context, TimeEndActivity.class);
        mIntent.putExtra(WAKE, true);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mIntent);

        //start tone
//        mp = MediaPlayer.create(context, R.raw.my_tone);
//        mp.start();


    }
}
