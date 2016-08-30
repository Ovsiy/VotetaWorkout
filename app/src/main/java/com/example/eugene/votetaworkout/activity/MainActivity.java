package com.example.eugene.votetaworkout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.eugene.votetaworkout.R;

/**
 * Main Activity
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button exitButton = (Button) findViewById(R.id.exitButton);
        Button myWorkoutsButton = (Button) findViewById(R.id.myWorkoutsButton);

        exitButton.setOnClickListener(exitListener);
        myWorkoutsButton.setOnClickListener(myWorkoutsListener);
    }

    private View.OnClickListener myWorkoutsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(), MyWorkoutsActivity.class);
            startActivity(in);
        }
    };

    private View.OnClickListener exitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
