package com.bis.lafefny.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bis.lafefny.Model.RecomededModel;
import com.bis.lafefny.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecomendedAdapter extends RecyclerView.Adapter<RecomendedAdapter.RecomendedViewHolder> {

    private ArrayList<RecomededModel> RecomendedList ;

    public RecomendedAdapter(final ArrayList<RecomededModel> recomendedList) {
        this.RecomendedList = recomendedList;
    }

    @NonNull
    @Override
    public RecomendedAdapter.RecomendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecomendedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recomended_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendedAdapter.RecomendedViewHolder holder, int position) {
        RecomededModel recomededModel = RecomendedList.get(position);

        holder.placeName.setText(recomededModel.getName());
        holder.placeCategory.setText(recomededModel.getCategory());
        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/lafefny.appspot.com/o/placesImages%2Ftest.jpg?alt=media&token=6ad8dd34-0b71-4ed3-b275-76a35e29acc8")
                .placeholder(R.drawable.familyplan)
                .into(holder.placeImg);
    }

    @Override
    public int getItemCount() {
        return RecomendedList.size();
    }

    public class RecomendedViewHolder extends RecyclerView.ViewHolder {
        TextView placeCategory;
        TextView placeName;
        ImageView placeImg;


        public RecomendedViewHolder(@NonNull View itemView) {
            super(itemView);

            placeCategory = itemView.findViewById(R.id.place_category);
            placeName = itemView.findViewById(R.id.place_name);
            placeImg = itemView.findViewById(R.id.place_image);

        }


    }
}
