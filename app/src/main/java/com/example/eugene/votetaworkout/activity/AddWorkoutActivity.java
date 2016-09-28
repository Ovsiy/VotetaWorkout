package com.example.eugene.votetaworkout.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
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

public class AddWorkoutActivity extends AppCompatActivity {

    private Set<ExerciseInstance> instances = new HashSet<>();
    private int backgroundColor;

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

        instancesListView.setOnScrollListener(onScrollListener);

        FloatingActionButton addExerciseButton = (FloatingActionButton) findViewById(R.id.button_add_exercise);
        addExerciseButton.setOnClickListener(addExerciseListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {
            FloatingActionButton addExerciseButton = (FloatingActionButton) absListView.getRootView().findViewById(R.id.button_add_exercise);
//            View buttonsPlaceholder = absListView.getRootView().findViewById(R.id.buttons_placeholder);

            switch (i) {
                case SCROLL_STATE_IDLE:
                    addExerciseButton.show();
//                    buttonsPlaceholder.setBackgroundColor(getBackgroundColor());
//                    buttonsPlaceholder.setVisibility(View.VISIBLE);
                    break;
                case SCROLL_STATE_FLING: case SCROLL_STATE_TOUCH_SCROLL:
//                    buttonsPlaceholder.setVisibility(View.INVISIBLE);
//                    buttonsPlaceholder.setBackgroundColor(Color.TRANSPARENT);
                    addExerciseButton.hide();
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int i, int i1, int i2) {

        }
    };

    private ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            int index = parent.getFlatListPosition(ExpandableListView
                    .getPackedPositionForChild(groupPosition, childPosition));
            ExerciseInstanceExpandableAdapter adapter = (ExerciseInstanceExpandableAdapter) parent.getExpandableListAdapter();

            ExerciseInstance instance = adapter.getChild(groupPosition, childPosition);

            if(parent.isItemChecked(index)) {
                parent.setItemChecked(index, false);
                instances.remove(instance);
            } else {
                parent.setItemChecked(index, true);
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

    private int getBackgroundColor() {
        if (backgroundColor == 0) {
            TypedArray array = getTheme().obtainStyledAttributes(new int[] {
                    android.R.attr.colorBackground,
            });

            backgroundColor = array.getColor(0, 0xFF00FF);

            return backgroundColor;
        } else return backgroundColor;
    }
}
