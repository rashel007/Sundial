package com.synergyforce.rashel.sundail.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.synergyforce.rashel.sundail.extras.Constants;
import com.synergyforce.rashel.sundail.extras.MySharedPreference;
import com.synergyforce.rashel.sundail.R;
import com.synergyforce.rashel.sundail.extras.SundialBroadcustReceiver;
import com.synergyforce.rashel.sundail.extras.Utils;


/**
 * @author Estique Ahmed Rashel
 */

public class TimeEndActivity extends Activity {

    private TextView tvSetStartTime, tvSetEndTime;
    private EditText etNote;
    private Button btnStartNewGlassClock, btnViewPrevNotes, btnSavaNote;

    private MySharedPreference mySharedPreference;
    private SharedPreferences spID;
    private NoteHistoryActivity noteHistoryActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.time_end_layout);

        noteHistoryActivity = new NoteHistoryActivity();

        mySharedPreference = new MySharedPreference(TimeEndActivity.this);
        //calling private functions
        openMainActivityFromSleep();
        showStartAndEndTime();
        startNewGlassClock();
        mySharedPreference.clearSharedPreferenceData();
        viewPreviousNotes();
        saveNote();
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

    private void saveNote(){

        spID = TimeEndActivity.this.getSharedPreferences(Constants.PRIMARY_KEY, Context.MODE_PRIVATE);
        btnSavaNote = (Button) findViewById(R.id.saveNote);
        btnSavaNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNote = (EditText) findViewById(R.id.etNote);
                if(!etNote.getText().toString().isEmpty()) {
                    noteHistoryActivity.setRealmData(
                            spID,
                            tvSetStartTime.getText().toString(),
                            tvSetEndTime.getText().toString(),
                            etNote.getText().toString());
                    btnSavaNote.setVisibility(View.GONE);
                    Toast.makeText(TimeEndActivity.this, "New Note Saved", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(TimeEndActivity.this, "Please Make a note 1st", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

                finish();
            }
        });
    }

    private void viewPreviousNotes(){
        btnViewPrevNotes = (Button) findViewById(R.id.viewHistory);
        btnViewPrevNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeEndActivity.this, NoteHistoryActivity.class);
                startActivity(intent);
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
