package com.vagrant.usefultest.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vagrant.usefultest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerMenuItemAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    @Override
    public int getCount() {
        return 0;
    }

    public DrawerMenuItemAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
            holder.image = (ImageView)convertView.findViewById(R.id.drawer_menu_list_image);
            holder.title = (TextView)convertView.findViewById(R.id.drawer_menu_list_title);
            convertView.setTag(holder);
        } else  {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.image.setBackgroundResource((Integer)getData().get(position).get("image"));
        holder.title.setText((String)getData().get(position).get("title"));
        return convertView;
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", R.drawable.ic_launcher);
        map.put("title", R.string.homepage);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("image", R.drawable.ic_launcher);
        map.put("title", R.string.find);
        list.add(map);

        return list;
    }
    private static class ViewHolder {
        private ImageView image;
        private TextView title;
    }
}
