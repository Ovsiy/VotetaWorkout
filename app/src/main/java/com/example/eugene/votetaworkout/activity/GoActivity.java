package com.example.eugene.votetaworkout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.WorkoutAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Workout;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene
 * @since 1/9/2017.
 */
public class GoActivity extends BaseActivity {
    @BindView(R.id.workouts_list)
    ListView workoutsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);
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

    @OnItemClick(R.id.workouts_list)
    void onWorkoutClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(getApplicationContext(), WorkoutActivity.class);
        in.putExtra("workoutId", id);
        startActivity(in);
    }
}
