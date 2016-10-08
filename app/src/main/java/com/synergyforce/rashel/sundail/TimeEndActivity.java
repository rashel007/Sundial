package com.synergyforce.rashel.sundail;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author Estique Ahmed Rashel
 */

public class TimeEndActivity extends Activity {

    TextView tvSetStartTime, tvSetEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.time_end_layout);
        openMainActivityFromSleep();
        setStartAndEndTime();


    }

    /**
     * TimeEndActivity private function
     * this function will set the start and end time in the user UI
     */
    private void setStartAndEndTime(){
        tvSetStartTime = (TextView) findViewById(R.id.started);
        tvSetEndTime = (TextView) findViewById(R.id.ends);

        tvSetStartTime.setText(Constants.START_TIME);
        tvSetEndTime.setText(Constants.END_TIME);
    }


    /**
     * This function will open the EndTimeActivity even if the phone is in sleep mode.
     */
    private void openMainActivityFromSleep(){
        if(getIntent().hasExtra(SundialBroadcustReceiver.WAKE) && getIntent().getExtras().getBoolean(SundialBroadcustReceiver.WAKE)){
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
    }


}
