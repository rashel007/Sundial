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
    static boolean APP_STARTED_FIRST_TIME = false;
    static Vibrator v;

    static int ProgressBarProgress = 0;
    static int ProgressBarDuration = 0;
    static final int id_primary_key = 1;

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
    static final int id_primary_key_value = 1;
    static final boolean App_First_value = true;


}
