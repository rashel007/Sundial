package com.synergyforce.rashel.sundail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


/**
 * @author Estique Ahmed Rashel
 */

public class TimeEndActivity extends Activity {

    TextView tvSetStartTime, tvSetEndTime;
    Button btnStartNewGlassClock;

    MySharedPreference mySharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.time_end_layout);

        mySharedPreference = new MySharedPreference(TimeEndActivity.this);
        //calling private functions
        openMainActivityFromSleep();
        showStartAndEndTime();
        startNewGlassClock();

    }

    /**
     * TimeEndActivity private function
     * this function will set the start and end time in the user UI
     */
    private void showStartAndEndTime(){
        tvSetStartTime = (TextView) findViewById(R.id.started);
        tvSetEndTime = (TextView) findViewById(R.id.ends);

        tvSetStartTime.setText(mySharedPreference.getSetTimeFromSP());
        tvSetEndTime.setText(Constants.END_TIME);
    }

    /**
     * TimeEndActivity private function
     * this will return to the main activity on button click
     */
    private void startNewGlassClock(){
        btnStartNewGlassClock = (Button) findViewById(R.id.startNewGlassClock);
        btnStartNewGlassClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeEndActivity.this, MainActivity.class);
                startActivity(intent);

                // settings the defaults values of start and end time
                Utils.setDefaultsValues();
                mySharedPreference.clearSharedPreferenceData();

                finish();
            }
        });
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

    // if the user press the back button, application will be closed
    @Override
    public void onBackPressed() {
        finish();
        //setting the default values of start and and time
        Utils.setDefaultsValues();
    }
}
