package com.example.eugene.votetaworkout.dao;

import android.content.Context;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.ExerciseInstance;
import com.example.eugene.votetaworkout.model.Workout;
import com.example.eugene.votetaworkout.model.WorkoutExerciseInstance;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Eugene
 * @since 1/9/2017.
 */
public class ExerciseInstanceDao extends BaseDaoImpl<ExerciseInstance, Integer> {
    public ExerciseInstanceDao(ConnectionSource connectionSource, Class<ExerciseInstance> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<ExerciseInstance> getExerciseInstancesByWorkout(Workout workout, Context context) throws SQLException {
        //ToDo: fix this with DI
        Dao<WorkoutExerciseInstance, Integer> workoutExerciseInstanceDao = DatabaseHelper.getDatabaseHelper(context).getDao(WorkoutExerciseInstance.class);
        QueryBuilder<WorkoutExerciseInstance, Integer> queryBuilder = workoutExerciseInstanceDao.queryBuilder();
        queryBuilder.selectColumns("exercise_instance_id");
        SelectArg userSelectArg = new SelectArg();
        queryBuilder.where().eq("workout_id", userSelectArg);

        QueryBuilder<ExerciseInstance, Integer> queryBuilder2 = this.queryBuilder();
        queryBuilder2.where().in("id", queryBuilder);
        PreparedQuery<ExerciseInstance> query = queryBuilder2.prepare();
        query.setArgumentHolderValue(0, workout);

        return this.query(query);
    }
}
