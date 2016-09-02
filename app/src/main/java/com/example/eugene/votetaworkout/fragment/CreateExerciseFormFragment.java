package com.example.eugene.votetaworkout.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Exercise;

import java.sql.SQLException;

public class CreateExerciseFormFragment extends Fragment {
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
            Exercise exercise = DatabaseHelper.getDatabaseHelper(getActivity()).getExerciseDao().queryForId((int)exerciseId);
            setExerciseName(exercise.getName());
            setExerciseDescription(exercise.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setExerciseName(String name) {
       TextView textView = (TextView) getView().findViewById(R.id.name);
       textView.setText(name);
   }

   public void setExerciseDescription(String description) {
       TextView textView = (TextView) getView().findViewById(R.id.description);
       textView.setText(description);
   }
}
