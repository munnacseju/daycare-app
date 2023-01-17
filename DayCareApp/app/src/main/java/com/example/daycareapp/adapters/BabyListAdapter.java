package com.example.daycareapp.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daycareapp.R;
import com.example.daycareapp.listeners.BabyClickListener;
import com.example.daycareapp.models.Baby;

import java.util.List;

public class BabyListAdapter extends RecyclerView.Adapter<BabyListViewHolder> {
    private final Context context;
    private final List<Baby> babyList;
    private final BabyClickListener babyClickListener;

    public BabyListAdapter(Context context, List<Baby> babyList, BabyClickListener babyClickListener) {
        this.context = context;
        this.babyList = babyList;
        this.babyClickListener = babyClickListener;
    }

    @NonNull
    @Override
    public BabyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.baby_list_layout, parent, false);
        return new BabyListViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull BabyListViewHolder holder, int position) {
        Baby baby = babyList.get(position);
        holder.setData(baby);
        holder.babyNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                babyClickListener.onClick(baby);
            }
        });

        holder.babyNameTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                babyClickListener.onLongClick(baby, holder.babyNameTv);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return babyList.size();
    }

}

class BabyListViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    public TextView babyNameTv;
    public TextView babyLocationTv;
    public ImageView babyImageView;
    public BabyListViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        babyNameTv = itemView.findViewById(R.id.babyName);
        babyLocationTv = itemView.findViewById(R.id.babyLocation);
        babyImageView = itemView.findViewById(R.id.babyImage);
    }

    public void setData(Baby baby) {
        babyNameTv.setText("Baby Name: "+baby.getBabyName());
        babyLocationTv.setText("Address: " + baby.getAddress());
        String PACKAGE_NAME = context.getPackageName();
        int imageId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + "img4z", null, null);
        babyImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imageId));
    }
}