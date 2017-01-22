package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.eugene.votetaworkout.R;
import com.example.eugene.votetaworkout.model.Category;

import java.util.List;

/**
 * @author Eugene
 * @since 1/5/2017.
 */
public class CategoryAdapter extends BaseAdapter<Category> {

    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, categories);
    }

    static class ViewHolder {
        @BindView(R.id.category_item_label)
        TextView categoryLabel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.category_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Category category = getItem(position);
        viewHolder.categoryLabel.setText(category.getName());

        return convertView;
    }
}
