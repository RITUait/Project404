package com.example.ritu.project404.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ritu.project404.R;
import com.example.ritu.project404.model.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ritu on 1/18/2018.
 */

public class CustomAdapterGrid extends ArrayAdapter {
    List<Contact> contactActivityList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public CustomAdapterGrid(Context context, List<Contact> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        contactActivityList = objects;
    }
    @Override
    public Contact getItem(int position) {
        return contactActivityList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CustomAdapterGrid.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_grid, parent, false);
            vh = CustomAdapterGrid.ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (CustomAdapterGrid.ViewHolder) convertView.getTag();
        }

        Contact item = getItem(position);

        vh.textViewName.setText(item.getName());
        vh.textViewMobile.setText(item.getMobile());
        Picasso.with(context).load(item.getProfile_imge()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewMobile;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName, TextView textViewMobile) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewMobile = textViewMobile;
        }

        public static CustomAdapterGrid.ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewMobile = (TextView) rootView.findViewById(R.id.textViewMobile);
            return new CustomAdapterGrid.ViewHolder(rootView, imageView, textViewName, textViewMobile);
        }
    }
}
