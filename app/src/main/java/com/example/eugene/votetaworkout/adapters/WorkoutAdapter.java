package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.Workout;

import java.util.List;

/**
 * @author Eugene
 * @since 1/9/2017.
 */
public class WorkoutAdapter extends BaseAdapter<Workout> {
    public WorkoutAdapter(Context context, List<Workout> workouts) {
        super(context, workouts);
    }

    static class ViewHolder {
        @BindView(R.id.workout_label)
        TextView workoutLabel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.workout_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Workout workout = getItem(position);
        viewHolder.workoutLabel.setText(workout.getName());

        return convertView;
    }
}
