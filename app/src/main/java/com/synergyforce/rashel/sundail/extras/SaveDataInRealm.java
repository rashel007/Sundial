package com.synergyforce.rashel.sundail.extras;

import android.app.Activity;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * @author Estique Ahmed Rashel
 */

public class SaveDataInRealm {

    private final Realm realm;
    private final String stStartTime;
    private final String stEndTime;
    private final String stNote;

    public SaveDataInRealm(Activity activity, String start, String end,String note){
        realm = RealmController.with(activity).getRealm();
        stStartTime = start;
        stEndTime = end;
        stNote = note;

    }

    public void setRealmData() {

        int key;
        try {
            key = realm.where(RealmObject.class).max("id").intValue() + 100;
        } catch(ArrayIndexOutOfBoundsException ex) {
            key = 0;
        }

        HistoryModel note = new HistoryModel();
        note.set_id(key);
        note.set_startTime(stStartTime);
        note.set_endTime(stEndTime);
        note.set_note(stNote);

        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }
}
