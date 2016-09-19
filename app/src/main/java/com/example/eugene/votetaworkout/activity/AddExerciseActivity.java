package com.example.eugene.votetaworkout.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.fragment.ExerciseFormFragment;
import com.example.eugene.votetaworkout.fragment.ExercisePickFragment;

public class AddExerciseActivity extends Activity implements ExercisePickFragment.OnExerciseSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        ExercisePickFragment exerciseFragment = new ExercisePickFragment();
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.add(R.id.fragmentPicker, exerciseFragment);
        ftrans.commit();
    }

    @Override
    public void onExerciseSelected(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("exerciseId", id);

        ExerciseFormFragment exerciseFormFragment = new ExerciseFormFragment();
        exerciseFormFragment.setArguments(bundle);

        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentPicker, exerciseFormFragment);
        fTrans.commit();
    }
}
