package saptarshi.com.summertask_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<AddData> list;
    private Realm mDatabase;


    public DataAdapter(List<AddData> list, Realm mDatabase) {

        this.list = list;
        this.mDatabase=mDatabase;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(list.get(i).title);
        viewHolder.details.setText(list.get(i).details);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, details;
        private final Context context;
        private ImageButton button;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            title = (TextView) view.findViewById(R.id.title);
            details = (TextView) view.findViewById(R.id.details);
            button= (ImageButton) view.findViewById(R.id.button);
            button.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            int position= getPosition();
            list.remove(position);
            notifyDataSetChanged();
            if(mDatabase==null)
                return;
            else {
                mDatabase.beginTransaction();
                final RealmResults<AddData> results = mDatabase.where(AddData.class).findAll();
                results.get(position).deleteFromRealm();
                mDatabase.commitTransaction();
            }
        }
    }
}
