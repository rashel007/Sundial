package com.synergyforce.rashel.sundail;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Estique Ahmed Rashel
 * This in the Main && Opening Activity of Sundail App.
 */

public class MainActivity extends Activity {

    TextView tvSetHeaderAppName;
    Spinner spinnerSetTime;
    String stEndTime;
    Button btnStartGlassClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMainScreenHeader();
        setEndTime();
        startButton();
    }

    /**
     * MainActivity Private method
     * it will set the MainScreen Header text.
     * text font will be custom. and the text will be the app name
     */
    private void setMainScreenHeader(){
        tvSetHeaderAppName = (TextView) findViewById(R.id.tvHeader);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/f_header.ttf");
        tvSetHeaderAppName.setTypeface(custom_font);
    }

    /**
     * MainActivity Private method
     * Add End time in spinner
     */
    private void setEndTime(){
        spinnerSetTime = (Spinner) findViewById(R.id.spinnerEndTime);

        //time options in spinner end time
        List<String> time = new ArrayList<String>();
        time.add("1");
        time.add("10");
        time.add("15");
        time.add("20");
        time.add("25");
        time.add("30");

        ArrayAdapter adapter =new ArrayAdapter(MainActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                time);
        spinnerSetTime.setAdapter(adapter);

        //setting the stEndTime
        spinnerSetTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stEndTime = spinnerSetTime.getSelectedItem().toString();
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
    private void startButton(){
        btnStartGlassClock = (Button) findViewById(R.id.startGlassClock);
        btnStartGlassClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the glass clock is already started ot not
                if(!Constants.ALARMMANAGER_STARTED) {
                    //if glass clock is'nt started , then start the glass clock
                    startAlarmManager();
                    Constants.ALARMMANAGER_STARTED = true;
                    //setting the started time
                    Constants.START_TIME = Utils.getTheCurrentDateAndTime();

                }else{
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
    private void startProgressbar(){

    }


    /**
     * Mian Activity private method
     * this will start the alarm manager
     */
    private void startAlarmManager(){
        int minute = Integer.parseInt(stEndTime);
        Intent intent = new Intent(getBaseContext(), SundialBroadcustReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, minute*60);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);

        Toast.makeText(MainActivity.this, " Glass Clock Started", Toast.LENGTH_SHORT).show();
    }










}
