package com.vagrant.usefultest.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.vagrant.usefultest.R;
import com.vagrant.usefultest.base.MyApplication;
import com.vagrant.usefultest.entity.ActionBean;

import java.util.List;

/**
 * Created by Administrator on 2015/6/22.
 */
public class ActionListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    List<ActionBean.Actions> actions;
    public ActionListAdapter(Context context, List<ActionBean.Actions> actions) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.actions = actions;
    }
    @Override
    public int getCount() {
        return actions.size();
    }

    @Override
    public Object getItem(int position) {
        return actions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.action_item, null);
            holder.logo = (ImageView)convertView.findViewById(R.id.profile_image);
            holder.username = (TextView)convertView.findViewById(R.id.username);
            holder.image = (SimpleDraweeView)convertView.findViewById(R.id.action_image);
            holder.title = (TextView)convertView.findViewById(R.id.action_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        initView(position, holder, actions);

        return convertView;
    }

    private void initView(int position, ViewHolder holder, List<ActionBean.Actions> actions) {
        ActionBean.Actions actionItem = actions.get(position);
        String users = actionItem.getUsers();
        String[] usersArray = users.split("\\|");
        String creator = usersArray[0];
        String[] creatorInfo = creator.split(":");
        String creatorName = creatorInfo[0];
        String creatorSex = creatorInfo[2];
        String creatorLogo = creatorInfo[3];

        String logoUrl = MyApplication.BASE_URL + creatorLogo;
        String title = actionItem.getName();
        String imageUri;
        if (actionItem.getAddtext() != null) {
            imageUri = actionItem.getAddtext();
        } else {
            imageUri = "https://www.baidu.com/img/bdlogo.png";
        }
        Picasso.with(context).load(logoUrl).into(holder.logo);
        holder.username.setText(creatorName);
        holder.title.setText(title);
        Uri uri;
        if (imageUri.startsWith("http"))
            uri = Uri.parse(imageUri);
        else
            uri = Uri.parse(MyApplication.BASE_URL + imageUri);

        holder.image.setImageURI(uri);
    }

    static class ViewHolder {
        ImageView logo;
        TextView username;
        TextView title;
        SimpleDraweeView image;
    }
}
