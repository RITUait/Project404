package com.example.ritu.project404.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ritu.project404.R;
import com.example.ritu.project404.model.Contact;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by ritu on 1/19/2018.
 */

public class CustomAdapterRecycler extends RecyclerView.Adapter<CustomAdapterRecycler.MyViewHolder> {
    List<Contact> data ;
    Context context;

    public CustomAdapterRecycler(Context context,List<Contact> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_recycler, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items

        Contact contact = data.get(position);
        holder.name.setText(contact.getName());
        holder.mobile.setText(contact.getMobile());
       // holder.year.setText(contact.getYear());
        //holder.name.setText(data.get(position));

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                //Toast.makeText(context, data.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView mobile;// init the item view's
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.textViewName);
            mobile = (TextView) itemView.findViewById(R.id.textViewMobile);
        }
    }

}
