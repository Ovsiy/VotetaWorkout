package com.example.eugene.votetaworkout.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.eugene.votetaworkout.R;

/**
 * Workouts list activity class
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
public class MyWorkoutsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_new_workout)
    void setNewWorkoutListener() {
        Intent in = new Intent(getApplicationContext(), AddWorkoutActivity.class);
        startActivity(in);
    }
}
