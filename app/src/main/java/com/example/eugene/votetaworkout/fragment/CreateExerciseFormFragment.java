package com.example.eugene.votetaworkout.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.activity.AddExerciseActivity;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Exercise;
import com.example.eugene.votetaworkout.model.ExerciseInstance;

import java.sql.SQLException;

public class CreateExerciseFormFragment extends Fragment {
    private Exercise exercise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_exercise_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        long exerciseId = getArguments().getLong("exerciseId");
        try {
            exercise = DatabaseHelper.getDatabaseHelper(getActivity()).getExerciseDao().queryForId((int)exerciseId);
            setExerciseName(exercise.getName());
            setExerciseDescription(exercise.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Button saveButton = (Button) getView().findViewById(R.id.burron_save_exercise);
        saveButton.setOnClickListener(onSaveExerciseListener);

    }

    private View.OnClickListener onSaveExerciseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText sets = (EditText) getView().findViewById(R.id.setsInput);
            EditText reps = (EditText) getView().findViewById(R.id.repsInput);
            EditText weight = (EditText) getView().findViewById(R.id.weightInput);

            ExerciseInstance exerciseInstance = new ExerciseInstance();
            exerciseInstance.setExercise(exercise);
            exerciseInstance.setSets(Integer.parseInt(sets.getText().toString()));
            exerciseInstance.setReps(Integer.parseInt(reps.getText().toString()));
            exerciseInstance.setWeight(Integer.parseInt(weight.getText().toString()));

            try {
                DatabaseHelper.getDatabaseHelper(getActivity()).getExerciseInstanceDao().create(exerciseInstance);
                Intent in = new Intent(getActivity(), AddExerciseActivity.class);
                startActivity(in);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    public void setExerciseName(String name) {
       TextView textView = (TextView) getView().findViewById(R.id.name);
       textView.setText(name);
   }

   public void setExerciseDescription(String description) {
       TextView textView = (TextView) getView().findViewById(R.id.description);
       textView.setText(description);
   }
}
