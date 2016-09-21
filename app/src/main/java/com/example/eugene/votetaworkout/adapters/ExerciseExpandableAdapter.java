package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.Category;
import com.example.eugene.votetaworkout.model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Ovsiy
 * @since 20.09.2016
 */
public class ExerciseExpandableAdapter extends BaseExpandableListAdapter {
    private final List<Category> categories;
    private final LayoutInflater layoutInflater;

    public ExerciseExpandableAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categories.get(groupPosition).getExercises().size();
    }

    @Override
    public Category getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public Exercise getChild(int groupPosition, int childPosition) {
        return new ArrayList<>(categories.get(groupPosition).getExercises()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return getGroup(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getChild(groupPosition, childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.listview_category, parent, false);
        }

        Category category = getGroup(groupPosition);
        ((TextView) view.findViewById(R.id.name)).setText(category.getName());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.listview_exercise, parent, false);
        }
        Exercise exercise = getChild(groupPosition, childPosition);

        ((TextView) view.findViewById(R.id.name)).setText(exercise.getName());

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
