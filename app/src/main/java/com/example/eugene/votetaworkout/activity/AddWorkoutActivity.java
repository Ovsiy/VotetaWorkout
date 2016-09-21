package com.example.eugene.votetaworkout.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.ExerciseInstanceExpandableAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Category;
import com.example.eugene.votetaworkout.model.ExerciseInstance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddWorkoutActivity extends Activity {

    Set<ExerciseInstance> instances = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        List<Category> categories = new ArrayList<>();

        try {
            categories = DatabaseHelper.getDatabaseHelper(this).getCategoryDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ExpandableListView instancesListView = (ExpandableListView) findViewById(R.id.instancesExpandableList);
        instancesListView.setOnChildClickListener(onChildClickListener);
        ExerciseInstanceExpandableAdapter adapter = new ExerciseInstanceExpandableAdapter(this, categories);
        instancesListView.setAdapter(adapter);

        Button addExerciseButton = (Button) findViewById(R.id.button_add_exercise);
        addExerciseButton.setOnClickListener(addExerciseListener);
    }

    private ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            int index = parent.getFlatListPosition(ExpandableListView
                    .getPackedPositionForChild(groupPosition, childPosition));
            ExerciseInstanceExpandableAdapter adapter = (ExerciseInstanceExpandableAdapter) parent.getExpandableListAdapter();

            ExerciseInstance instance = adapter.getChild(groupPosition, childPosition);

            if(parent.isItemChecked(index)) {
                parent.setItemChecked(index, false);
                parent.getChildAt(index).setBackgroundColor(Color.TRANSPARENT);
                instances.remove(instance);
            } else {
                parent.setItemChecked(index, true);
                parent.getChildAt(index).setBackgroundColor(Color.RED);
                instances.add(instance);
            }

            return false;
        }
    };

    private View.OnClickListener addExerciseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(), AddExerciseActivity.class);
            startActivity(in);
        }
    };
}
