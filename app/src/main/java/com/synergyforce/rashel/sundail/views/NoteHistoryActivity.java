package com.synergyforce.rashel.sundail.views;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.synergyforce.rashel.sundail.extras.Constants;
import com.synergyforce.rashel.sundail.extras.HistoryModel;
import com.synergyforce.rashel.sundail.R;
import com.synergyforce.rashel.sundail.extras.RealmController;
import com.synergyforce.rashel.sundail.adapters.NotesAdapter;
import com.synergyforce.rashel.sundail.adapters.RealmNotesAdapter;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.synergyforce.rashel.sundail.extras.Constants.SP_Primary_key;

/**
 * @author Estique Ahmed Rashel
 */

public class NoteHistoryActivity extends Activity {

    private NotesAdapter adapter;
    private Realm realm;
    private LayoutInflater inflater;
    private RecyclerView recycler;
    int id;

    SharedPreferences sharedpreferences, spCheckFirst;
    SharedPreferences.Editor editor, editorCheckFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_history_layout);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        spCheckFirst = NoteHistoryActivity.this.getSharedPreferences(Constants.APP_FIRST_RUN, Context.MODE_PRIVATE);
        editorCheckFirst = spCheckFirst.edit();

        Toast.makeText(this, "Long Press To Delete Note" , Toast.LENGTH_LONG).show();

        if(spCheckFirst.getBoolean(Constants.App_First_Key, false ) == false){
            sharedpreferences = NoteHistoryActivity.this.getSharedPreferences(Constants.PRIMARY_KEY, Context.MODE_PRIVATE);
            editor = sharedpreferences.edit();
            editor.putInt(SP_Primary_key, Constants.id_primary_key_value);
            editor.commit();

            editorCheckFirst.putBoolean(Constants.App_First_Key, Constants.App_First_value);
            editorCheckFirst.commit();
           // Toast.makeText(this, "First: " +spCheckFirst.getBoolean(Constants.App_First_Key, false), Toast.LENGTH_SHORT).show();

        }




        //get realm instance
        this.realm = RealmController.with(this).getRealm();

        setupRecycler();

        // setRealmData();

        // refresh the realm instance
        RealmController.with(this).refresh();
        // get all persisted objects
        // create the helper adapter and notify data set changes
        // changes will be reflected automatically
        setRealmAdapter(RealmController.with(this).getNoteHistories());
    }

    public void setRealmAdapter(RealmResults<HistoryModel> books) {

        RealmNotesAdapter realmAdapter = new RealmNotesAdapter(this.getApplicationContext(), books, true);
        // Set the data and tell the RecyclerView to draw
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recycler.setHasFixedSize(true);

        // use a linear layout manager since the cards are vertically scrollable
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        // create an empty adapter and add it to the recycler view
        adapter = new NotesAdapter(NoteHistoryActivity.this);
        recycler.setAdapter(adapter);
    }

    public void setRealmData(SharedPreferences spID ,String start, String end, String not) {

        this.realm = RealmController.with(this).getRealm();
        SharedPreferences.Editor editID = spID.edit();
        HistoryModel note = new HistoryModel();

        id = spID.getInt(SP_Primary_key, 0);
        note.set_id(id);
        //clear the previous id
        editID.clear();
        editID.commit();
        // add the new id
        id++;
        editID.putInt(Constants.SP_Primary_key, id);
        editID.commit();
      //  Toast.makeText(NoteHistoryActivity.this, "ID: " +  spID.getInt(SP_Primary_key,0), Toast.LENGTH_LONG).show();

        note.set_startTime(start);
        note.set_endTime(end);
        note.set_note(not);

        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }
}
