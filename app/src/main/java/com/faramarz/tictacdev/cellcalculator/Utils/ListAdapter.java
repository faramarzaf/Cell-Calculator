package com.faramarz.tictacdev.cellcalculator.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.faramarz.tictacdev.cellcalculator.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<HistoryModel> historyModels;

    public ListAdapter(Context mContext, List<HistoryModel> historyModels) {
        this.mContext = mContext;
        this.historyModels = historyModels;
    }

    @Override
    public int getCount() {
        return historyModels.size();
    }

    @Override
    public Object getItem(int position) {
        return historyModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        TextView descr = convertView.findViewById(R.id.historyDescription);
        TextView title = convertView.findViewById(R.id.historyTitle);
        TextView date = convertView.findViewById(R.id.historyDate);

        descr.setText(historyModels.get(position).getDescription());
        title.setText(historyModels.get(position).getTitle());
        date.setText(historyModels.get(position).getDate());
        return convertView;

    }



}
