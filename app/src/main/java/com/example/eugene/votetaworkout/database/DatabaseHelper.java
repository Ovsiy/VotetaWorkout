package com.example.eugene.votetaworkout.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.eugene.votetaworkout.ExerciseInstanceDao;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.*;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.*;
import java.sql.SQLException;

/**
 * Database helper
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME    = "workout.db";
    private static final int    DATABASE_VERSION = 1;

    private static DatabaseHelper databaseHelper;

    private Dao<Exercise, Integer> exerciseDao = null;
    private Dao<Category, Integer> categoryDao = null;
    private ExerciseInstanceDao exerciseInstanceDao = null;
    private Dao<Workout, Integer> workoutDao = null;
    private Dao<WorkoutExerciseInstance, Integer> workoutExerciseInstanceDao = null;

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DatabaseHelper getDatabaseHelper(Context context) {
        if (databaseHelper == null) {
            return new DatabaseHelper(context);
        }

        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Exercise.class);
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, ExerciseInstance.class);
            TableUtils.createTable(connectionSource, Workout.class);
            TableUtils.createTable(connectionSource, WorkoutExerciseInstance.class);

            InputStream is = context.getResources().openRawResource(R.raw.exercises);
            DataInputStream in = new DataInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            try {
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    database.execSQL(strLine);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    public Dao<Exercise, Integer> getExerciseDao() throws SQLException {
        if (exerciseDao == null) {
            exerciseDao = getDao(Exercise.class);
        }

        return exerciseDao;
    }

    public Dao<Category, Integer> getCategoryDao() throws SQLException {
        if (categoryDao == null) {
            categoryDao = getDao(Category.class);
        }

        return categoryDao;
    }

    public ExerciseInstanceDao getExerciseInstanceDao() throws SQLException {
        if (exerciseInstanceDao == null) {
//            exerciseInstanceDao = getDao(ExerciseInstance.class);
            exerciseInstanceDao = new ExerciseInstanceDao(connectionSource, ExerciseInstance.class);
        }

        return exerciseInstanceDao;
    }

    public Dao<Workout, Integer> getWorkoutDao() throws SQLException {
        if (workoutDao == null) {
            workoutDao = getDao(Workout.class);
        }

        return workoutDao;
    }

    public Dao<WorkoutExerciseInstance, Integer> getWorkoutExerciseInstanceDao() throws SQLException {
        if (workoutExerciseInstanceDao == null) {
            workoutExerciseInstanceDao = getDao(WorkoutExerciseInstance.class);
        }

        return workoutExerciseInstanceDao;
    }
}
