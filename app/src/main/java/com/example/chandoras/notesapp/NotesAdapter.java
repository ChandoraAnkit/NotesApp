package com.example.chandoras.notesapp;

import android.app.Activity;
import android.content.Context;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by chandoras on 9/11/17.
 */

public class NotesAdapter extends ArrayAdapter<Data> {
    Context context ;
    ArrayList<Data> noteList;
    LayoutInflater inflater = null;


    public NotesAdapter( Context context,int resource,  ArrayList<Data> noteList) {
        super(context, resource, noteList);
        try
        {
            this.context = context;
            this.noteList = noteList;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Nullable
    @Override
    public Data getItem(int position) {
        return super.getItem(position);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder{
        public TextView titleTv;
        public TextView descp_Tv;

    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        try {

            if (convertView == null) {

                view = inflater.inflate(R.layout.single_note, parent,false);
                holder = new ViewHolder();
                holder.titleTv = (TextView) view.findViewById(R.id.tv_title);
                holder.descp_Tv = (TextView) view.findViewById(R.id.tv_descp);


                view.setTag(holder);

            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.titleTv.setText(noteList.get(position).getTitle());
            holder.descp_Tv.setText(noteList.get(position).getDescp());
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }
}
