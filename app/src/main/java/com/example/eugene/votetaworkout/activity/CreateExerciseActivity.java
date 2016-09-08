package com.example.eugene.votetaworkout.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.CategoryAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.fragment.CategoryFragment;
import com.example.eugene.votetaworkout.fragment.CreateExerciseFormFragment;
import com.example.eugene.votetaworkout.fragment.ExerciseFragment;
import com.example.eugene.votetaworkout.model.Category;
import com.example.eugene.votetaworkout.model.Exercise;

import java.sql.SQLException;
import java.util.List;

public class CreateExerciseActivity extends Activity implements CategoryFragment.OnCategorySelectedListener, ExerciseFragment.OnExerciseSelected {

    private CategoryFragment categoryFragment;
    private ExerciseFragment exerciseFragment;
    private CreateExerciseFormFragment createExerciseFormFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        categoryFragment = new CategoryFragment();

        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.fragmentPicker, categoryFragment);
        fTrans.commit();
    }

    @Override
    public void onCategoryItemPicked(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("categoryId", id);

        exerciseFragment = new ExerciseFragment();
        exerciseFragment.setArguments(bundle);

        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.detach(categoryFragment);
        fTrans.add(R.id.fragmentPicker, exerciseFragment);
        fTrans.commit();
    }

    @Override
    public void onExerciseSelected(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("exerciseId", id);

        createExerciseFormFragment = new CreateExerciseFormFragment();
        createExerciseFormFragment.setArguments(bundle);

        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.detach(exerciseFragment);
        fTrans.add(R.id.fragmentPicker, createExerciseFormFragment);
        fTrans.commit();
    }
}
