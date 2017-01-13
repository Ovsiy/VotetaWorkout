package com.example.eugene.votetaworkout.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.eugene.votetaworkout.adapters.ExerciseInstanceListAdapter;
import com.example.eugene.votetaworkout.dao.ExerciseInstanceDao;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.ExerciseInstance;
import com.example.eugene.votetaworkout.model.Workout;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Eugene
 * @since 1/9/2017.
 */
public class WorkoutActivity extends AppCompatActivity {

    @BindView(R.id.workouts_list)
    ListView workoutsListView;

    @BindView(R.id.timerValue)
    TextView timerValue;

    @BindView(R.id.start_button)
    ImageButton startButton;

    private Handler customHandler = new Handler();
    private long timeInMilliseconds = 0L;
    private long startTime = 0L;
    private boolean exerciseStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);
        ButterKnife.bind(this);
        long workoutId = getIntent().getExtras().getLong("workoutId");

        try {
            Workout workout = DatabaseHelper.getDatabaseHelper(this).getWorkoutDao().queryForId((int)workoutId);
            ExerciseInstanceDao exerciseInstanceDao = DatabaseHelper.getDatabaseHelper(this).getExerciseInstanceDao();
            List<ExerciseInstance> instances = exerciseInstanceDao.getExerciseInstancesByWorkout(workout, getApplicationContext());

            ExerciseInstanceListAdapter adapter = new ExerciseInstanceListAdapter(this, instances);
            workoutsListView.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.start_button)
    void onStartButtonClick(View view) {
        if (!exerciseStarted) {
            startTime = SystemClock.uptimeMillis();
            exerciseStarted = true;
            customHandler.postDelayed(updateTimerThread, 0);
            startButton.setImageResource(R.drawable.ic_stop_black_48dp);
            Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
            startButton.startAnimation(pulse);
        } else {
            customHandler.removeCallbacks(updateTimerThread);
            exerciseStarted = false;
            startButton.setImageResource(R.drawable.ic_play_arrow_black_48dp);
        }
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            int secs = (int) (timeInMilliseconds / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (timeInMilliseconds % 1000);
            timerValue.setText("" + mins + ":"
                            + String.format("%02d", secs) + ":"
                            + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };
}
