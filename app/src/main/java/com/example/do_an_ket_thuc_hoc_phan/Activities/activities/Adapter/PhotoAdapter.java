package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewholder>{
    private List<Photo> list;

    public PhotoAdapter(List<Photo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PhotoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img,parent,false);
        return new PhotoViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewholder holder, int position) {
        Photo photo = list.get(position);
        if(list == null) return;
        holder.imageView.setImageResource(photo.getImg());
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class PhotoViewholder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public PhotoViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
