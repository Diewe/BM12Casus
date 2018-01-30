package com.example.diewevg.bm12applicatie.Resultaten;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diewevg.bm12applicatie.R;

import java.util.ArrayList;

/**
 * Created by Diewevg on 30-1-2018.
 */

public class ResultaatAdapter extends ArrayAdapter<ResultaatModel> {
    // View lookup cache
    private static class ViewHolder {
        TextView cursus;
        TextView cijfer;
    }

    public ResultaatAdapter(Context context, ArrayList<ResultaatModel> users) {
        super(context, R.layout.item_resultaat, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ResultaatModel resultaatModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_resultaat, parent, false);
            viewHolder.cursus = (TextView) convertView.findViewById(R.id.resultaatCursus);
            viewHolder.cijfer = (TextView) convertView.findViewById(R.id.resultaatCijfer);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.cursus.setText(resultaatModel.cursus);
        viewHolder.cijfer.setText(resultaatModel.cijfer);

        // Return the completed view to render on screen
        return convertView;
    }
}