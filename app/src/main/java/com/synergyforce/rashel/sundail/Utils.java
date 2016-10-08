package com.synergyforce.rashel.sundail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.synergyforce.rashel.sundail.Constants.APP_CLOSED;

/**
 * @author Estique Ahmed Rashel
 */

public class Utils {

    /**W
     * public function of Utils
     * this will return the current date and time
     */
    public static String getTheCurrentDateAndTime(){
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    /**
     * public function of Utils
     * this will set default static values
     */
    public static void setDefaultsValues(){
        Constants.START_TIME = "";
        Constants.END_TIME = "";
        Constants.ALARMMANAGER_STARTED = false;
        Constants.APP_CLOSED = false;

        Constants.ProgressBarDuration = 0;
        Constants.ProgressBarProgress = 0;
    }
}
