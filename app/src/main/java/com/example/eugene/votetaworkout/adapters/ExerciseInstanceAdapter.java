package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.ExerciseInstance;

import java.util.List;

/**
 * Exercise Instance Adapter
 *
 * @author Eugene Ovsiy
 * @since 5.09.2016
 */
public class ExerciseInstanceAdapter extends BaseAdapter {

    private Context context;
    private List<ExerciseInstance> exerciseInstances;
    private LayoutInflater layoutInflater;

    public ExerciseInstanceAdapter(Context context, List<ExerciseInstance> exerciseInstances) {
        this.exerciseInstances = exerciseInstances;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return exerciseInstances.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseInstances.get(position);
    }

    @Override
    public long getItemId(int position) {
        return exerciseInstances.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.exercise_listview, parent, false);
        }

        return view;
    }
}
