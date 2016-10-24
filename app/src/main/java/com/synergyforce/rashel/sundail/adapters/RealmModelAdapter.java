package com.synergyforce.rashel.sundail.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * @author Estique Ahmed Rashel
 */

public class RealmModelAdapter <T extends RealmObject> extends RealmBaseAdapter<T> {

    public RealmModelAdapter(Context context, RealmResults<T> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
