package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.Category;

import java.util.List;

/**
 * @author Eugene Ovsiy
 * @since 29.08.2016
 */
public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categories;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.get(position).getId();
    }

    private Category getCategory(int position) {
        return (Category) getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.category_listview, parent, false);
        }
        Category category = getCategory(position);
        ((TextView) view.findViewById(R.id.name)).setText(category.getName());

        return view;
    }
}
