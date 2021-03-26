package com.ranju.taskapp.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ranju.taskapp.R;
import com.ranju.taskapp.model.PicsModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ImgHolder>{
    private ArrayList<PicsModel> picsModels;
    private Context mctx;

    public ImgAdapter(ArrayList<PicsModel> picsModels, Context mctx) {
        this.picsModels = picsModels;
        this.mctx = mctx;
    }

    @NonNull
    @Override
    public ImgAdapter.ImgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mctx).inflate(R.layout.img_row,parent,false);
        return new ImgHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgHolder holder, int position) {
        if (picsModels!=null) {
            holder.aName.setText(picsModels.get(position).getAuthor());
            Picasso.get()
                    .load("https://picsum.photos/300/300?image=" + picsModels.get(position).getId())
                    .placeholder(R.drawable.image_loader)
                    .resize(300,300)
                    .centerCrop()
                    .into(holder.aImg);
        }
    }

    @Override
    public int getItemCount() {
        return picsModels.size();
    }

    public class ImgHolder extends RecyclerView.ViewHolder {
        private MyImageView aImg;
        private MyTextView aName;
        public ImgHolder(@NonNull View itemView) {
            super(itemView);
            aImg = itemView.findViewById(R.id.authImg);
            aName = itemView.findViewById(R.id.authName);
        }
    }
}
