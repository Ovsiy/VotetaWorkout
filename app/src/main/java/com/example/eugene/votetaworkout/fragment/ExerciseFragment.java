package com.example.eugene.votetaworkout.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.ExerciseAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Exercise;

import java.sql.SQLException;
import java.util.List;

public class ExerciseFragment extends Fragment {
    OnExerciseSelected listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Exercise> exercises = null;
        long categoryId = getArguments().getLong("categoryId");
        try {
            exercises = DatabaseHelper.getDatabaseHelper(getActivity()).getExerciseDao().queryForEq("category_id", categoryId);
            ExerciseAdapter exerciseAdapter = new ExerciseAdapter(getActivity(), exercises);

            ListView exercisesListView = (ListView) getActivity().findViewById(R.id.exercisesList);
            exercisesListView.setAdapter(exerciseAdapter);
            exercisesListView.setOnItemClickListener(onExerciseItemClickListener);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnExerciseSelected) context;
    }

    private ListView.OnItemClickListener onExerciseItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            listener.onExerciseSelected(id);
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnExerciseSelected {
        void onExerciseSelected(long id);
    }
}
