package com.example.eugene.votetaworkout.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.eugene.votetaworkout.ExerciseInstanceDao;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.ExerciseInstance;
import com.example.eugene.votetaworkout.model.Workout;
import com.example.eugene.votetaworkout.model.WorkoutExerciseInstance;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Eugene
 * @since 1/9/2017.
 */
public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);
        long workoutId = getIntent().getExtras().getLong("workoutId");

        try {
            Workout workout = DatabaseHelper.getDatabaseHelper(this).getWorkoutDao().queryForId((int)workoutId);

//            Dao<WorkoutExerciseInstance, Integer> workoutExerciseInstanceDao = DatabaseHelper.getDatabaseHelper(this).getWorkoutExerciseInstanceDao();
//
//            QueryBuilder<WorkoutExerciseInstance, Integer> queryBuilder = workoutExerciseInstanceDao.queryBuilder();
//            queryBuilder.selectColumns("exercise_instance_id");
//            SelectArg userSelectArg = new SelectArg();
//            queryBuilder.where().eq("workout_id", userSelectArg);
//
//
//            Dao<ExerciseInstance, Integer> exerciseInstanceDao = DatabaseHelper.getDatabaseHelper(this).getExerciseInstanceDao();
//            QueryBuilder<ExerciseInstance, Integer> queryBuilder2 = exerciseInstanceDao.queryBuilder();
//            queryBuilder2.where().in("id", queryBuilder);
//            PreparedQuery<ExerciseInstance> query = queryBuilder2.prepare();
//            query.setArgumentHolderValue(0, workout);
//
//            List<ExerciseInstance> shit = exerciseInstanceDao.query(query);
//            System.out.println(shit.size());

            ExerciseInstanceDao exerciseInstanceDao = DatabaseHelper.getDatabaseHelper(this).getExerciseInstanceDao();

            List<ExerciseInstance> shit = exerciseInstanceDao.getExerciseInstancesByWorkout(workout, getApplicationContext());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
