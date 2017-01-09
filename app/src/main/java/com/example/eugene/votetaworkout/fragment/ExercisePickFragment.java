package com.example.eugene.votetaworkout.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.adapters.ExerciseAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Exercise;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExercisePickFragment extends Fragment {
    private OnExerciseSelected listener;

    @BindView(R.id.exercisesGrid)
    GridView exerciseGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_pick, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Exercise> exercises = new ArrayList<>();

        try {
            exercises = DatabaseHelper.getDatabaseHelper((getActivity())).getExerciseDao().queryForEq("category_id", getArguments().get("categoryId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ExerciseAdapter adapter = new ExerciseAdapter(exercises, getActivity());

        exerciseGridView.setAdapter(adapter);
    }

    @OnItemClick(R.id.exercisesGrid)
    void onExerciseClick(AdapterView<?> adapterView, View view, int position, long id) {
        listener.onExerciseSelected(id);
    }

    private GridView.OnItemClickListener onExerciseClickListener = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            listener.onExerciseSelected(id);
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
