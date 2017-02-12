package com.example.eugene.votetaworkout.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.eugene.votetaworkout.dao.ExerciseInstanceDao;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.ExerciseInstance;
import com.example.eugene.votetaworkout.model.Workout;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Eugene
 * @since 1/9/2017.
 */
public class WorkoutActivity extends AppCompatActivity {
    @BindView(R.id.timer_value)
    TextView timerValue;

    @BindView(R.id.start_button)
    ImageButton startButton;

    @BindView(R.id.list_workouts)
    LinearLayout exercisesListLayout;

    @BindView(R.id.exercise_label)
    TextView exerciseLabel;

    @BindView(R.id.sets_left_value)
    TextView setsLeftValue;

    private Handler customHandler = new Handler();
    private long startTime = 0L;
    private boolean exerciseStarted;
    private boolean setEnded = false;
    private int sets;
    private ExerciseInstance instance = null;

    private ListIterator<ExerciseInstance> iterator;
    private ListIterator<ExerciseInstance> iterator2;

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
            iterator = instances.listIterator();
            iterator2 = instances.listIterator();
            iterator2.next();

            instance = iterator.next();

            exerciseLabel.setText(instance.getExercise().getName());
            setsLeftValue.setText(String.valueOf(instance.getSets()));
            sets = instance.getSets();

            addExerciseToLayout(instances.get(0));
            addExerciseToLayout(instances.get(1));
            addExerciseToLayout(instances.get(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.start_button)
    void onStartButtonClick(View view) {
        if (!exerciseStarted) {
            handleSetsLogic();

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
            startButton.clearAnimation();
            setsLeftValue.setText(String.valueOf(sets));
        }
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            long timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            int secs = (int) (timeInMilliseconds / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (timeInMilliseconds % 1000) / 10;

            timerValue.setText(String.format("%02d:%02d:%02d", mins, secs, milliseconds));
            customHandler.postDelayed(this, 10);
        }
    };

    private void handleSetsLogic() {
        if (setEnded && !iterator.hasNext()) {
            new AlertDialog.Builder(this)
                    .setTitle("Congratulations!")
                    .setMessage("You have finished your workout")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .show();

            return;
        }

        if (iterator.previousIndex() == 0) {
            exercisesListLayout.getChildAt(0).setBackgroundColor(Color.RED);
        }
        if (setEnded) {
            handleExercisesList();

            instance = iterator.next();
            exerciseLabel.setText(instance.getExercise().getName());
            sets = instance.getSets();
            setsLeftValue.setText(String.valueOf(sets));
            setEnded = false;
        }
        if (--sets == 0) setEnded = true;
    }

    private void handleExercisesList() {
        if (iterator.nextIndex() == 1) {
            exercisesListLayout.getChildAt(0).setBackgroundColor(Color.TRANSPARENT);
            exercisesListLayout.getChildAt(1).setBackgroundColor(Color.RED);
            iterator2.next();
            iterator2.next();
        } else if (iterator2.hasNext()) {
            final View previous = exercisesListLayout.getChildAt(0);
            final View current = exercisesListLayout.getChildAt(1);
            final View next = exercisesListLayout.getChildAt(2);

            TranslateAnimation moveUp = new TranslateAnimation(0, 0, 0, previous.getHeight() * -1);
            moveUp.setDuration(500);

            View newExercise = addExerciseToLayout(iterator2.next());

            previous.startAnimation(moveUp);
            current.startAnimation(moveUp);
            next.startAnimation(moveUp);
            newExercise.startAnimation(moveUp);

            moveUp.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    startButton.setEnabled(false);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    exercisesListLayout.removeView(previous);

                    current.setBackgroundColor(Color.TRANSPARENT);
                    next.setBackgroundColor(Color.RED);

                    startButton.setEnabled(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        } else {
                exercisesListLayout.getChildAt(1).setBackgroundColor(Color.TRANSPARENT);
                exercisesListLayout.getChildAt(2).setBackgroundColor(Color.RED);
        }
    }

    private View addExerciseToLayout(ExerciseInstance instance) {
        View view = LayoutInflater.from(this).inflate(R.layout.listview_exercise_instance_selector, exercisesListLayout, false);
        view.findViewById(R.id.sets);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView sets = (TextView) view.findViewById(R.id.sets);
        TextView reps = (TextView) view.findViewById(R.id.reps);
        TextView weight = (TextView) view.findViewById(R.id.weight);

        name.setText(String.valueOf(instance.getExercise().getName()));
        sets.setText(String.valueOf(instance.getSets()));
        reps.setText(String.valueOf(instance.getReps()));
        weight.setText(String.valueOf(instance.getWeight()));

        exercisesListLayout.addView(view);

        return view;
    }
}
