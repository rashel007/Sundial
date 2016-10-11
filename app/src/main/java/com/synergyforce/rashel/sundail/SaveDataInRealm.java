package com.synergyforce.rashel.sundail;

import android.app.Activity;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Rashel on 10/11/2016.
 */

public class SaveDataInRealm {

    private Realm realm;
    private String stStartTime;
    private String stEndTime;
    private String stNote;

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
