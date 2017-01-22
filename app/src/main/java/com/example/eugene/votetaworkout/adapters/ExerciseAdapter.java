package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.Exercise;

import java.util.List;

/**
 * @author Eugene
 * @since 1/4/2017.
 */
public class ExerciseAdapter extends BaseAdapter<Exercise> {

    public ExerciseAdapter(Context context, List<Exercise> exercises) {
        super(context, exercises);
    }

     static class ViewHolder {
        @BindView(R.id.exercise_label)
        TextView exerciseLabel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.exercise_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Exercise exercise = getItem(position);
        viewHolder.exerciseLabel.setText(exercise.getName());

        return convertView;
    }
}
