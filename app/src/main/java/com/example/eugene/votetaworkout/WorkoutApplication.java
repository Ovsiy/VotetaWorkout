package com.example.eugene.votetaworkout;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Category;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

/**
 * Application main class
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
public class WorkoutApplication extends Application {
    private DatabaseHelper databaseHelper = null;

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        boolean dbExists = this.getDatabasePath("workout.db").exists();
        getHelper().getWritableDatabase();
    }
}
