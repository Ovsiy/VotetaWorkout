package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.Category;
import com.example.eugene.votetaworkout.model.ExerciseInstance;

import java.util.List;

/**
 * @author Eugene Ovsiy
 * @since 20.09.2016
 */
public class ExerciseInstanceExpandableAdapter extends BaseExpandableListAdapter {

    private final List<Category> categories;
    private final LayoutInflater layoutInflater;

    public ExerciseInstanceExpandableAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categories.get(groupPosition).getInstances().size();
    }

    @Override
    public Category getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public ExerciseInstance getChild(int groupPosition, int childPosition) {
        return categories.get(groupPosition).getInstances().get(childPosition);
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
            view = layoutInflater.inflate(R.layout.listview_exercise_instance, parent, false);
        }
        ExerciseInstance exerciseInstance = getChild(groupPosition, childPosition);

        ((TextView) view.findViewById(R.id.name)).setText(exerciseInstance.getExercise().getName());
        ((TextView) view.findViewById(R.id.sets)).setText(Integer.toString(exerciseInstance.getSets()) + "x");
        ((TextView) view.findViewById(R.id.reps)).setText(Integer.toString(exerciseInstance.getReps()));
        ((TextView) view.findViewById(R.id.weight)).setText(Integer.toString(exerciseInstance.getWeight()) + "kg");

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
