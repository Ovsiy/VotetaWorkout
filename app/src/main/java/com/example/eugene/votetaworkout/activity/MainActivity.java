package com.example.eugene.votetaworkout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.eugene.votetaworkout.R;

/**
 * Main Activity
 *
 * @author Eugene Ovsiy
 * @since 24.08.2016
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.goButton)
    void onGoButtonClick() {
        Intent in = new Intent(getApplicationContext(), GoActivity.class);
        startActivity(in);
    }

    @OnClick(R.id.myWorkoutsButton)
    void onMyWorkoutsButtonClick() {
        Intent in = new Intent(getApplicationContext(), MyWorkoutsActivity.class);
        startActivity(in);
    }

    @OnClick(R.id.exitButton)
    void onExitButtonClick() {
        finish();
    }
}
