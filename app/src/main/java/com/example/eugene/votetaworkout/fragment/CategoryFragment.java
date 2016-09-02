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
import com.example.eugene.votetaworkout.adapters.CategoryAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryFragment extends Fragment {

    private OnCategorySelectedListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnCategorySelectedListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Category> categories = null;
        try {
            categories = DatabaseHelper.getDatabaseHelper(getActivity()).getCategoryDao().queryForAll();
            CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categories);

            ListView categoriesListView = (ListView) getActivity().findViewById(R.id.categoriesList);
            categoriesListView.setAdapter(categoryAdapter);
            categoriesListView.setOnItemClickListener(onCategoryItemClickListener);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ListView.OnItemClickListener onCategoryItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            listener.onCategoryItemPicked(id);
        }
    };

    public interface OnCategorySelectedListener{
        void onCategoryItemPicked(long id);
    }
}
