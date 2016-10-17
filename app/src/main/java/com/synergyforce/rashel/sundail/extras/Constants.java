package com.synergyforce.rashel.sundail.extras;

import android.os.Vibrator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Estique Ahmed Rashel
 */

public class Constants {
    public static String START_TIME = "";
    public static String END_TIME = "";
    public static boolean ALARMMANAGER_STARTED = false;
    public static boolean APP_CLOSED = false;
    public static boolean APP_STARTED_FIRST_TIME = false;
    public static Vibrator v;

    public static int ProgressBarProgress = 0;
    public static int ProgressBarDuration = 0;
    public static final int id_primary_key = 1;

    // shared_preference name
    public static final String MyPREFERENCES = "MyPrefsNew" ;
    public static final String PRIMARY_KEY = "PrimaryKeyNew" ;
    public static final String SP_START_TIME = "startTimeNew" ;
    public static final String SP_ALARMMANAGER_STARTED = "alarmStartedNew" ;
    public static final String APP_FIRST_RUN = "firstRun" ;

    //shared preference key
    public static final String SP_Primary_key = "idPrimaryKeyNew" ;
    public static final String App_First_Key = "firstRun" ;
    //shared preference value
    public static final int id_primary_key_value = 1;
    public static final boolean App_First_value = true;


}
