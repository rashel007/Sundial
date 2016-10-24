package com.synergyforce.rashel.sundail.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.synergyforce.rashel.sundail.extras.HistoryModel;
import com.synergyforce.rashel.sundail.R;
import com.synergyforce.rashel.sundail.extras.RealmController;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author Estique Ahmed Rashel
 */

public class NotesAdapter extends RealmRecyclerViewAdapater<HistoryModel>  {

    private final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public NotesAdapter(Context context) {
        this.context = context;
    }
    // create new views (invoked by the layout manager)
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_single_note, parent, false);
        return new CardViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        // get the article
        final HistoryModel note = getItem(position);
        // cast the generic view holder to our specific one
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        // set the title and the snippet
        holder.tvStartTime.setText(note.get_startTime());
        holder.tvEndTime.setText(note.get_endTime());
        holder.tvNote.setText(note.get_note());

        //remove single match from realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                RealmResults<HistoryModel> results = realm.where(HistoryModel.class).findAll();

                // All changes to data must happen in a transaction
                realm.beginTransaction();

                // remove single match
                results.remove(position);
                realm.commitTransaction();

//                if (results.size() == 0) {
//                   // Prefs.with(context).setPreLoad(false);
//                }

                notifyDataSetChanged();

                Toast.makeText(context, "Note is removed from Realm", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    // return the size of your data set (invoked by the layout manager)
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public final CardView card;
        public final TextView tvStartTime;
        public final TextView tvEndTime;
        public final TextView tvNote;

        public CardViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_notes);
            tvStartTime = (TextView) itemView.findViewById(R.id.started);
            tvEndTime = (TextView) itemView.findViewById(R.id.ends);
            tvNote = (TextView) itemView.findViewById(R.id.note);
        }
    }

}
