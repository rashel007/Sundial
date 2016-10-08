package com.synergyforce.rashel.sundail;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Estique Ahmed Rashel
 */

public class Utils {
    /**
     * public function of Utils
     * this will return the current date and time
     */
    public static String getTheCurrentDateAndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    /**
     * public function of Utils
     */
    public static void setDefaultsValues(){
        Constants.START_TIME = "";
        Constants.END_TIME = "";
    }
}
