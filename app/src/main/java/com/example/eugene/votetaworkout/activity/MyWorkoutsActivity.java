package com.example.eugene.votetaworkout.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

        String[] values = new String[] { "Workout1", "Workout2", "Workout3",
                "Workout4", "Workout5", "Workout6", "Workout7", "Workout 8",
                "Workout 9", "Workout 10" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               R.layout.workout_listview, values);

        ListView listView = getListView();
        listView.setAdapter(adapter);

        Button addExerciseBytton = (Button) findViewById(R.id.button_new_workout);
        addExerciseBytton.setOnClickListener(newWorkoutListener);
    }

    private View.OnClickListener newWorkoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(), AddWorkoutActivity.class);
            startActivity(in);
        }
    };
}
