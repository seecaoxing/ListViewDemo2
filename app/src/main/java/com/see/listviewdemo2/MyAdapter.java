package com.see.listviewdemo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by see on 2016/9/12.
 */
public class MyAdapter extends BaseAdapter {

    List<String> data;
    Context context;

    public MyAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_item);
            holder.textView = (TextView) convertView.findViewById(R.id.text_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        holder.textView.setText(data.get(position));
        return convertView;
    }

    public void onDateChange(List<String> data) {

        this.data = data;
        this.notifyDataSetChanged();
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView textView;


    }
}
