package com.example.eugene.votetaworkout.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.ExerciseExpandableAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExercisePickFragment extends Fragment {
    OnExerciseSelected listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_pick, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Category> categories = new ArrayList<>();

        try {
            categories = DatabaseHelper.getDatabaseHelper(getActivity()).getCategoryDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ExpandableListView instancesListView = (ExpandableListView) getActivity().findViewById(R.id.exercisesExpandableList);
        ExerciseExpandableAdapter adapter = new ExerciseExpandableAdapter(getActivity(), categories);
        instancesListView.setAdapter(adapter);

        instancesListView.setOnChildClickListener(onChildClickListener);
    }

    private ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            listener.onExerciseSelected(id);

            return false;
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnExerciseSelected) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnExerciseSelected {
        void onExerciseSelected(long id);
    }
}
