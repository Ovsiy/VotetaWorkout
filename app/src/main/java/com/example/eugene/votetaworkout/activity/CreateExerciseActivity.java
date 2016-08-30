package com.example.eugene.votetaworkout.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.CategoryAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Category;
import com.example.eugene.votetaworkout.model.Exercise;

import java.sql.SQLException;
import java.util.List;

public class CreateExerciseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        try {
            List<Category> categories = DatabaseHelper.getDatabaseHelper(this).getCategoryDao().queryForAll();
            CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);

            ListView categoriesListView = (ListView) findViewById(R.id.categoriesList);
            categoriesListView.setAdapter(categoryAdapter);

            categoriesListView.setOnItemClickListener(onCategoryItemClickListener);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ListView.OnItemClickListener onCategoryItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                Exercise selectedExercise = DatabaseHelper.getDatabaseHelper(CreateExerciseActivity.this).getExerciseDao().queryForId((int)id);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };


}
