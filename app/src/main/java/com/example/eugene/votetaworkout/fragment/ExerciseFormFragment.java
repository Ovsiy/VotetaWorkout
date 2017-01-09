package com.example.eugene.votetaworkout.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.activity.AddWorkoutActivity;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Exercise;
import com.example.eugene.votetaworkout.model.ExerciseInstance;

import java.sql.SQLException;

public class ExerciseFormFragment extends Fragment {
    private Exercise exercise;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.burron_save_exercise)
    Button saveButton;

    @BindView(R.id.setsInput)
    EditText inputSets;

    @BindView(R.id.repsInput)
    EditText inputReps;

    @BindView(R.id.weightInput)
    EditText inputWeight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_exercise_form, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        long exerciseId = getArguments().getLong("exerciseId");
        try {
            exercise = DatabaseHelper.getDatabaseHelper(getActivity()).getExerciseDao().queryForId((int)exerciseId);
            name.setText(exercise.getName());
            description.setText(exercise.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.burron_save_exercise)
    void onSaveButtonClick(View v) {
        if (!inputValuesCorrect()) return;

        ExerciseInstance exerciseInstance = new ExerciseInstance();
        exerciseInstance.setExercise(exercise);
        exerciseInstance.setSets(Integer.parseInt(inputSets.getText().toString()));
        exerciseInstance.setReps(Integer.parseInt(inputReps.getText().toString()));
        exerciseInstance.setWeight(Integer.parseInt(inputWeight.getText().toString()));

        try {
            DatabaseHelper.getDatabaseHelper(getActivity()).getExerciseInstanceDao().create(exerciseInstance);
            Intent in = new Intent(getActivity(), AddWorkoutActivity.class);
            startActivity(in);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean inputValuesCorrect() {
        boolean isCorrect = true;
        if (TextUtils.isEmpty(inputSets.getText())) {
            inputSets.setError(getActivity().getString(R.string.sets_error_message));
            inputSets.requestFocus();
            isCorrect = false;
        }

        if (TextUtils.isEmpty(inputReps.getText())) {
            inputReps.setError(getActivity().getString(R.string.reps_error_message));
            inputReps.requestFocus();
            isCorrect = false;
        }

        if (TextUtils.isEmpty(inputWeight.getText())) {
            inputWeight.setError(getActivity().getString(R.string.weight_error_message));
            inputWeight.requestFocus();
            isCorrect = false;
        }

        return isCorrect;
    }
}
