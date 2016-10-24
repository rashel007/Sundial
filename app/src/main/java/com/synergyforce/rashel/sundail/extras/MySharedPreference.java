package com.synergyforce.rashel.sundail.extras;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Estique Ahmed Rashel
 */

public class MySharedPreference {

    private final SharedPreferences sharedpreferences;
    private final SharedPreferences.Editor editor;

    public MySharedPreference(Context c){
        sharedpreferences = c.getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    /**
     * Utils public function
     * this will save GlassClock start time in the SharedPreference
     */
    public void setStartTimeInSP(){
        editor.putString(Constants.SP_START_TIME, Constants.START_TIME);
        editor.putBoolean(Constants.SP_ALARMMANAGER_STARTED, true);
        editor.commit();
    }

    /**
     * MySharedPreference public function
     * @return string value of glassclock started time
     */
    public String getSetTimeFromSP(){
        return sharedpreferences.getString(Constants.SP_START_TIME, "");
    }

    /**
     * public function of MySharedPreference
     * @return boolean value if the glassclock is started or not
     */
    public boolean getSPBooleanifAppStarted(){
        return sharedpreferences.getBoolean(Constants.SP_ALARMMANAGER_STARTED, false);
    }

    /**
     * Utils public function
     * clear the sharedpreference when new glassclick starts
     */
    public void clearSharedPreferenceData(){
        editor.clear();
        editor.commit();
    }
}
