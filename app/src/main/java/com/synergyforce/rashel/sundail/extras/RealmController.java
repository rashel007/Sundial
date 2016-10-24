package com.synergyforce.rashel.sundail.extras;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author Estique Ahmed Rashel
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    private RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(HistoryModel.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<HistoryModel> getNoteHistories() {

        return realm.where(HistoryModel.class).findAll();
    }

    //query a single item with the given id
    public HistoryModel getNote(String id) {

        return realm.where(HistoryModel.class).equalTo("id", id).findFirst();
    }

    //check if Book.class is empty
    public boolean hasNots() {

        return !realm.allObjects(HistoryModel.class).isEmpty();
    }

    //query example
    public RealmResults<HistoryModel> queryedNotes() {

        return realm.where(HistoryModel.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();

    }

}
