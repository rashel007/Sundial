package com.synergyforce.rashel.sundail.adapters;

import android.content.Context;

import com.synergyforce.rashel.sundail.extras.HistoryModel;

import io.realm.RealmResults;
/**
 * @author Estique Ahmed Rashel
 */

public class RealmNotesAdapter  extends RealmModelAdapter<HistoryModel>{

    public RealmNotesAdapter(Context context, RealmResults<HistoryModel> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}
