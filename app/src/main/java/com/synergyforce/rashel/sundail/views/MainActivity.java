package com.synergyforce.rashel.sundail.views;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.synergyforce.rashel.sundail.R;
import com.synergyforce.rashel.sundail.extras.Constants;
import com.synergyforce.rashel.sundail.extras.MySharedPreference;
import com.synergyforce.rashel.sundail.extras.SundialBroadcustReceiver;
import com.synergyforce.rashel.sundail.extras.TimerService;
import com.synergyforce.rashel.sundail.extras.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Estique Ahmed Rashel
 *         This in the Main && Opening Activity of Sundail App.
 */

public class MainActivity extends Activity {

    private TextView tvSetHeaderAppName, tvTimer;
    private Spinner spinnerSetTime;
    private String stEndTime;
    private Button btnStartGlassClock, btnViewPrevNotes;
    private ProgressBar progressBar;
    private ObjectAnimator animation;

    private MySharedPreference mySharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySharedPreference = new MySharedPreference(MainActivity.this);
//        mySharedPreference.clearSharedPreferenceData();

        setMainScreenHeader();
        setEndTime();
        startButton();
        viewPreviousNotes();
    }

    @Override
    public void onBackPressed() {
        MainActivity.this.finish();
        if (!Constants.APP_CLOSED && Constants.ALARMMANAGER_STARTED) {
            if(progressBar != null) {
                progressBar.clearAnimation();
            }
            Constants.APP_CLOSED = true;
        }
    }

    private void setTimer() {
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvTimer.setVisibility(View.VISIBLE);

        tvTimer.setText(Constants.TIME_PASSED_SECCONDS);
    }

    /**
     * MainActivity Private method
     * it will set the MainScreen Header text.
     * text font will be custom. and the text will be the app name
     */
    private void setMainScreenHeader() {
        tvSetHeaderAppName = (TextView) findViewById(R.id.tvHeader);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/f_header.ttf");
        tvSetHeaderAppName.setTypeface(custom_font);
    }

    private void viewPreviousNotes() {
        btnViewPrevNotes = (Button) findViewById(R.id.viewHistory);
        btnViewPrevNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteHistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * MainActivity Private method
     * Add End time in spinner
     */
    private void setEndTime() {
        spinnerSetTime = (Spinner) findViewById(R.id.spinnerEndTime);

        //time options in spinner end time
        List<String> time = new ArrayList<>();
        time.add("1");
        time.add("10");
        time.add("15");
        time.add("20");
        time.add("25");
        time.add("30");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                time);
        spinnerSetTime.setAdapter(adapter);

        //setting the stEndTime
        spinnerSetTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stEndTime = spinnerSetTime.getSelectedItem().toString();
                Constants.SELECTED_END_TIME = stEndTime;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * MainActivity private function
     * This will listen button click event and start the alarm manager
     */
    private void startButton() {
        btnStartGlassClock = (Button) findViewById(R.id.startGlassClock);
        btnStartGlassClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the glass clock is already started ot not
                if (!mySharedPreference.getSPBooleanifAppStarted()) {
                    //setting the started time
                    Constants.START_TIME = Utils.getTheCurrentDateAndTime();
                    //setting the start time in shared preference
                    mySharedPreference.setStartTimeInSP();
                    //if glass clock is'nt started , then start the glass clock
                    startAlarmManager();
                    //starting the progress bar
                    startProgressbar();
                    Log.i("End Time: ", ""+Constants.SELECTED_END_TIME);
                    //starting the forground service
                    startService(new Intent(MainActivity.this, TimerService.class));
                    Log.i("MainActivity: ", "Started service");

                    Constants.ALARMMANAGER_STARTED = true;


                } else {
                    //if a glass clock is already started , toast a message to user about it.
                    Toast.makeText(MainActivity.this, "GLASS CLOCK is already started", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * MainActivity private function
     * this function will start progress bar when the user start the glass clock
     * it will show the time counts
     */
    private void startProgressbar() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //setting different setProgressDrawable according to api level
        if (Build.VERSION.SDK_INT < 21) {
            progressBar.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.circle_pre_lollipop));
        }
        if (Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.circle_lollipop_and_greater));

        }

        animation = ObjectAnimator.ofInt(progressBar, "progress",
                0, 500);
        //converting the end time into milliseconds. 1m = 60000 mils
        Constants.ProgressBarDuration = Integer.parseInt(stEndTime) * 60000;

        animation.setDuration(Constants.ProgressBarDuration);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }


    /**
     * Main Activity private method
     * this will start the alarm manager
     */
    private void startAlarmManager() {
        int minute = Integer.parseInt(stEndTime);
        Intent intent = new Intent(getBaseContext(), SundialBroadcustReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, minute * 60);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);

        Toast.makeText(MainActivity.this, " Glass Clock Started", Toast.LENGTH_SHORT).show();
    }


}
