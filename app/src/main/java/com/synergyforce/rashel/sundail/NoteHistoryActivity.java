package com.synergyforce.rashel.sundail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.synergyforce.rashel.sundail.adapters.NotesAdapter;
import com.synergyforce.rashel.sundail.adapters.RealmNotesAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

import static com.synergyforce.rashel.sundail.R.id.note;

/**
 * @author Estique Ahmed Rashel
 */

public class NoteHistoryActivity extends Activity {

    private NotesAdapter adapter;
    private Realm realm;
    private LayoutInflater inflater;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_history_layout);

        recycler = (RecyclerView) findViewById(R.id.recycler);

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

    public void setRealmData(String start, String end,String not) {
        this.realm = RealmController.with(this).getRealm();
        int key;

        HistoryModel note = new HistoryModel();
        note.set_id(34);
        note.set_startTime(start);
        note.set_endTime(end);
        note.set_note(not);

        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }
}
