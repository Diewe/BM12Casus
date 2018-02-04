package com.example.diewevg.bm12applicatie.LeeractiviteitDocent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diewevg.bm12applicatie.Models.Activity;
import com.example.diewevg.bm12applicatie.R;
import com.example.diewevg.bm12applicatie.Resultaten.ResultaatModel;

import java.util.ArrayList;

/**
 * Created by Diewevg on 30-1-2018.
 */

public class LeeractiviteitDocentAdapter extends ArrayAdapter<Activity> {
    // View lookup cache
    private static class ViewHolder {
        TextView cursus;
        TextView soort;
    }

    public LeeractiviteitDocentAdapter(Context context, ArrayList<Activity> users) {
        super(context, R.layout.item_leeractiviteit_docent, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Activity leeractiviteitModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocentAdapter.ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocentAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_leeractiviteit_docent, parent, false);
            viewHolder.cursus = (TextView) convertView.findViewById(R.id.leeractiviteitCursus);
            viewHolder.soort = (TextView) convertView.findViewById(R.id.leeractiviteitSoort);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (com.example.diewevg.bm12applicatie.LeeractiviteitDocent.LeeractiviteitDocentAdapter.ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.cursus.setText(leeractiviteitModel.getActivityType());
        //viewHolder.soort.setText(leeractiviteitModel.soort);

        // Return the completed view to render on screen
        return convertView;
    }
}
