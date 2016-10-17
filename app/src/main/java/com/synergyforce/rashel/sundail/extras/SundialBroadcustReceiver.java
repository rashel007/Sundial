package com.synergyforce.rashel.sundail.extras;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.synergyforce.rashel.sundail.R;
import com.synergyforce.rashel.sundail.views.TimeEndActivity;

import static com.synergyforce.rashel.sundail.extras.Constants.v;

/**
 * @author Estique Ahmed Rashel
 */

public class SundialBroadcustReceiver extends BroadcastReceiver {

    public static final String WAKE = "Wake up";
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {


        //start alarm
        long[] pattern = {0, 100, 1000, 300, 200, 100, 500, 200, 100};
        Constants.v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(pattern,-1);

        //setting the end time
        Constants.END_TIME = Utils.getTheCurrentDateAndTime();

        // start TimeEndActivity
        Intent mIntent = new Intent(context, TimeEndActivity.class);
        mIntent.putExtra(WAKE, true);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(mIntent);

        //start tone
        mp = MediaPlayer.create(context, R.raw.tone);
        mp.start();


    }
}
