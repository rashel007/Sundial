package com.synergyforce.rashel.sundail;

import android.os.Vibrator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Estique Ahmed Rashel
 */

public class Constants {
    static String START_TIME = "";
    static String END_TIME = "";
    static boolean ALARMMANAGER_STARTED = false;
    static boolean APP_CLOSED = false;
    static Vibrator v;

    static int ProgressBarProgress = 0;
    static int ProgressBarDuration = 0;
    static final int id_primary_key = 1;

    //Start time shared_preference
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String PRIMARY_KEY = "PrimaryKey" ;
    public static final String SP_START_TIME = "startTime" ;
    public static final String SP_Primary_key = "idPrimaryKey" ;
    public static final String SP_ALARMMANAGER_STARTED = "alarmStarted" ;


}
