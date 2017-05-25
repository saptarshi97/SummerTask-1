package saptarshi.com.summertask_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<AddData> list;


    public DataAdapter(List<AddData> list) {
        this.list = list;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, details;
        private final Context context;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            title = (TextView) view.findViewById(R.id.title);
            details = (TextView) view.findViewById(R.id.details);

        }
    }
}
