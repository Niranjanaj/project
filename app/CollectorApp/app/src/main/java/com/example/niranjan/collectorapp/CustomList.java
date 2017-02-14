package com.example.niranjan.collectorapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Belal on 7/22/2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private String[] names;
    private Activity context;

    public CustomList(Activity context, String[] names) {
        super(context, R.layout.simplerow, names);
        this.context = context;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.simplerow, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.rowTextView);
        textViewName.setText(names[position]);
        return  listViewItem;
    }
}
