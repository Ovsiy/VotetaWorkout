package com.example.eugene.votetaworkout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.ExerciseInstanceAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.ExerciseInstance;
import com.example.eugene.votetaworkout.model.Workout;
import com.example.eugene.votetaworkout.model.WorkoutExerciseInstance;
import com.example.eugene.votetaworkout.utils.Utils;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddWorkoutActivity extends AppCompatActivity {

    private Set<ExerciseInstance> selectedInstances = new HashSet<>();

    @BindView(R.id.exercise_instances_grid)
    GridView instancesGridView;

    @BindView(R.id.button_add_exercise)
    FloatingActionButton addExerciseButton;

    @BindView(R.id.button_save_workout)
    FloatingActionButton saveWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);
        ButterKnife.bind(this);

        List<ExerciseInstance> instances = new ArrayList<>();

        try {
            instances = DatabaseHelper.getDatabaseHelper(this).getExerciseInstanceDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ExerciseInstanceAdapter adapter = new ExerciseInstanceAdapter(this, instances);
        instancesGridView.setAdapter(adapter);
        instancesGridView.setOnScrollListener(onScrollListener);
    }

    @OnItemClick(R.id.exercise_instances_grid)
    void onExerciseInstanceClick(AdapterView<?> adapterView, View view, int position, long id) {
        CheckBox checkbox = (CheckBox) view.findViewById(R.id.exercise_instance_checkbox);
        ExerciseInstance instance = (ExerciseInstance) adapterView.getItemAtPosition(position);

        if (!checkbox.isChecked()) {
            checkbox.toggle();
            selectedInstances.add(instance);
        } else {
            checkbox.toggle();
            selectedInstances.remove(instance);
        }
    }

    @OnClick(R.id.button_add_exercise)
    void onAddExerciseClick(View view) {
        Intent in = new Intent(getApplicationContext(), AddExerciseActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {
            switch (i) {
                case SCROLL_STATE_IDLE:
                    addExerciseButton.show();
                    saveWorkoutButton.show();
                    break;
                case SCROLL_STATE_FLING: case SCROLL_STATE_TOUCH_SCROLL:
                    addExerciseButton.hide();
                    saveWorkoutButton.hide();
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int i, int i1, int i2) {};
    };

    @OnClick(R.id.button_save_workout)
    void onAddWorkoutButtonClick(View view) {
        if (selectedInstances.size() == 0) {
            Utils.showAlertMessage(this, "Error", "You should pick at least one exercise");
            return;
        }

        Workout workout = new Workout();
        workout.setName("Workout1");

        try {
            Dao<Workout, Integer> workoutDao = DatabaseHelper.getDatabaseHelper(this).getWorkoutDao();
            Dao<WorkoutExerciseInstance, Integer> workoutExerciseInstanceDao = DatabaseHelper.getDatabaseHelper(this).getWorkoutExerciseInstanceDao();
            workoutDao.create(workout);

            for (ExerciseInstance instance : selectedInstances) {
                WorkoutExerciseInstance workoutExerciseInstance = new WorkoutExerciseInstance(workout, instance);
                workoutExerciseInstanceDao.create(workoutExerciseInstance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent in = new Intent(getApplicationContext(), GoActivity.class);
        startActivity(in);
    }
}
