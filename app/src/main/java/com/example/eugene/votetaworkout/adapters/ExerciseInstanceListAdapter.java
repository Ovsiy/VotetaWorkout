package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.ExerciseInstance;

import java.util.List;

/**
 * Created by Eugene on 1/13/2017.
 */
public class ExerciseInstanceListAdapter extends BaseAdapter {
    private Context context;
    private List<ExerciseInstance> instances;

    public ExerciseInstanceListAdapter(Context context, List<ExerciseInstance> instances) {
        this.context = context;
        this.instances = instances;
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView exerciseInstanceLabel;

        @BindView(R.id.sets)
        TextView sets;

        @BindView(R.id.reps)
        TextView reps;

        @BindView(R.id.weight)
        TextView weight;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_exercise_instance_selector, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ExerciseInstance instance = getItem(position);

        viewHolder.exerciseInstanceLabel.setText(instance.getExercise().getName());

        viewHolder.sets.setText(String.valueOf(instance.getSets()));
        viewHolder.reps.setText(String.valueOf(instance.getReps()));
        viewHolder.weight.setText(String.valueOf(instance.getWeight()));

        return convertView;
    }

    public int getCount() {
        return instances.size();
    }

    @Override
    public ExerciseInstance getItem(int position) {
        return instances.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
