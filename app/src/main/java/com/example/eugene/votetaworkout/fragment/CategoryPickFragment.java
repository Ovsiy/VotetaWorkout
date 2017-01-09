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
import com.example.eugene.votetaworkout.adapters.CategoryAdapter;
import com.example.eugene.votetaworkout.database.DatabaseHelper;
import com.example.eugene.votetaworkout.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene
 * @since 1/4/2017.
 */
public class CategoryPickFragment extends Fragment {
    private OnCategorySelected listener;

    @BindView(R.id.categoriesGrid)
    GridView categoriseGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnCategorySelected) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_pick, container, false);
        ButterKnife.bind(this, view);

        return view;
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

        CategoryAdapter adapter = new CategoryAdapter(categories, getActivity());

        categoriseGridView.setAdapter(adapter);
    }

    @OnItemClick(R.id.categoriesGrid)
    void onCategoryClick(AdapterView<?> adapterView, View view, int position, long id) {
        listener.onCategorySelected(id);
    }

    public interface OnCategorySelected {
        void onCategorySelected(long id);
    }
}
