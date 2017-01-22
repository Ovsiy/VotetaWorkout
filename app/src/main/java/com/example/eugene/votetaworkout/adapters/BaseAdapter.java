package com.example.eugene.votetaworkout.adapters;

import android.content.Context;
import com.example.eugene.votetaworkout.model.Model;

import java.util.List;

/**
 * @author Eugene
 * @since 1/21/2017.
 */
public abstract class BaseAdapter<T extends Model> extends android.widget.BaseAdapter {
    protected Context context;
    protected List<T> items;

    public BaseAdapter(Context context, List<T> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
