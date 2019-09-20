package com.example.ultimateproject.RecyclerView;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ultimateproject.R;

import java.util.ArrayList;


/**
 * Created by Jaison on 08/10/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    public ArrayList<RecyclerViewModel> usersList = new ArrayList<>();

    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subTitleTv, titleTv;
        ImageView imageView;
        public CardView ll_listitem;

        public MyViewHolder(View view) {
            super(view);
            subTitleTv = (TextView) view.findViewById(R.id.tv_posting);
            titleTv = (TextView) view.findViewById(R.id.tv_user_name);
            imageView = (ImageView) view.findViewById(R.id.thumbnail);
            ll_listitem = (CardView) view.findViewById(R.id.ll_listitem);

        }
    }


    public RecyclerViewAdapter(Context context, ArrayList<RecyclerViewModel> userList) {
        this.mContext = context;
        this.usersList = userList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecyclerViewModel movie = usersList.get(position);
        holder.titleTv.setText(movie.getName());
        holder.subTitleTv.setText(movie.getPosting());

//        holder.imageView.setImageBitmap(movie.getBitmap());

//        if(selected_usersList.contains(usersList.get(position)))
//            holder.ll_listitem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_selected_state));
//        else
//            holder.ll_listitem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_normal_state));

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}

