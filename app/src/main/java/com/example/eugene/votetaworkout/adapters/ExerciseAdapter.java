package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.Exercise;

import java.util.List;

/**
 * Created by eugene on 9/2/16.
 */
public class ExerciseAdapter extends BaseAdapter {
    private Context context;
    private List<Exercise> exercises;
    private LayoutInflater layoutInflater;

    public ExerciseAdapter(Context context, List<Exercise> exercises) {
        this.exercises = exercises;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return exercises.get(position).getId();
    }

    private Exercise getExercise(int position) {
        return (Exercise) getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.exercise_listview, parent, false);
        }
        Exercise exercise = getExercise(position);
        ((TextView) view.findViewById(R.id.name)).setText(exercise.getName());

        return view;
    }
}
