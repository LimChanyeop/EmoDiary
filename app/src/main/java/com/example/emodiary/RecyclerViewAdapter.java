package com.example.emodiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView record_emotion;
        TextView record_center;
        TextView record_date;
        ViewHolder(View itemView) {
            super(itemView);
            record_emotion = itemView.findViewById(R.id.tv_record_emotion);
            record_center = itemView.findViewById(R.id.tv_record_center);
            record_date = itemView.findViewById(R.id.tv_record_date);
        }
    }
    RecyclerViewAdapter(ArrayList<String> list){ // 생성자
        mData = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.cardview, parent,false);
        RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(view);

       return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.record_emotion.setText(mData.get(position));
        holder.record_date.setText(mData.get(position));
        holder.record_center.setText(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}