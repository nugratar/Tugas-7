package com.example.tugas7.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugas7.R;
import com.example.tugas7.helper.Cons;
import com.example.tugas7.helper.OnItemClickListener;
import com.example.tugas7.models.ModelAiringToday;

import java.util.List;

public class AdapterAiringToday extends RecyclerView.Adapter<AdapterAiringToday.ViewHolder> {

    private List<ModelAiringToday> airingToday;
    private OnItemClickListener<ModelAiringToday> clickListener;

    public AdapterAiringToday(List<ModelAiringToday> airingToday, OnItemClickListener<ModelAiringToday> clickListener) {
        this.airingToday = airingToday;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_television, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Cons.BASE_IMAGE_URL + airingToday.get(position).getImageURL())
                .into(holder.ivPoster);
        holder.tvTitle.setText(airingToday.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return airingToday.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivPoster;
        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(airingToday.get(getBindingAdapterPosition()));
        }
    }
}
