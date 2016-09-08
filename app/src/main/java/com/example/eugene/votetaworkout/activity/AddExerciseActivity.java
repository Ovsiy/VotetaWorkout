package com.example.eugene.votetaworkout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.CategoryAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Category;

import java.sql.SQLException;
import java.util.List;

public class AddExerciseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        Button createExcerciseButton = (Button) findViewById(R.id.button_create_exercise);
        createExcerciseButton.setOnClickListener(createExerciseListener);
    }

    private View.OnClickListener createExerciseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(), CreateExerciseActivity.class);
            startActivity(in);
        }
    };
}
