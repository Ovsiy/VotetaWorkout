package com.example.eugene.votetaworkout;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    }
}
