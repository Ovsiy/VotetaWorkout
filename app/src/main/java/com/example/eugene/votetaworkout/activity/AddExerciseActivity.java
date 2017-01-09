package com.example.eugene.votetaworkout.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.fragment.CategoryPickFragment;
import com.example.eugene.votetaworkout.fragment.ExerciseFormFragment;
import com.example.eugene.votetaworkout.fragment.ExercisePickFragment;

public class AddExerciseActivity extends AppCompatActivity implements ExercisePickFragment.OnExerciseSelected, CategoryPickFragment.OnCategorySelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        CategoryPickFragment categoryPickFragment = new CategoryPickFragment();
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.add(R.id.fragmentPicker, categoryPickFragment);
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

    @Override
    public void onCategorySelected(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("categoryId", id);

        ExercisePickFragment exercisePickFragment = new ExercisePickFragment();
        exercisePickFragment.setArguments(bundle);

        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentPicker, exercisePickFragment);
        fTrans.commit();
    }
}
