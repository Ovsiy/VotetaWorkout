package com.example.eugene.votetaworkout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.WorkoutAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Workout;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Workouts list activity class
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
public class MyWorkoutsActivity extends BaseActivity {
    @BindView(R.id.workouts_list)
    ListView workoutsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);
        ButterKnife.bind(this);

        List<Workout> workoutList = new ArrayList<>();
        try {
            Dao<Workout, Integer> dao = DatabaseHelper.getDatabaseHelper(this).getWorkoutDao();
            workoutList = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        WorkoutAdapter adapter = new WorkoutAdapter(this, workoutList);
        workoutsListView.setAdapter(adapter);
    }

    @OnClick(R.id.button_new_workout)
    void setNewWorkoutListener() {
        Intent in = new Intent(getApplicationContext(), AddWorkoutActivity.class);
        startActivity(in);
    }
}
