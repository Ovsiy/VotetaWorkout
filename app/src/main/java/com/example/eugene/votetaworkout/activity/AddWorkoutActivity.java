package com.example.eugene.votetaworkout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.eugene.votetaworkout.R;

public class AddWorkoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        Button addExerciseButton = (Button) findViewById(R.id.button_add_exercise);
        addExerciseButton.setOnClickListener(addExerciseListener);

    }

    private View.OnClickListener addExerciseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(), AddExerciseActivity.class);
            startActivity(in);
        }
    };
}
