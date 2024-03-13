package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.ArrayList;
import java.util.List;

public class UuDaiAdapter extends RecyclerView.Adapter<UuDaiAdapter.UuDaiViewholder>{
    private List<ItemUuDai> list;
    private int rd;
    public void setData(List<ItemUuDai> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UuDaiViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uudai_item,parent,false);
        return new UuDaiViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UuDaiViewholder holder, int position) {
        ItemUuDai uuDai = list.get(position);
        if(uuDai == null) return;
        rd = (int)position;
        holder.layout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),getbackg()));
        byte[] img = uuDai.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        holder.img.setImageBitmap(bitmap);
        holder.txt_title.setText(uuDai.getTxt_title());
        holder.txt_des.setText(uuDai.getTxt_des());
    }

    private int getbackg() {
        int k = rd%4;
        if(k == 0)
            return R.drawable.background_cat1;
        if(k == 1)
            return R.drawable.background_cat2;
        if(k == 2)
            return R.drawable.background_cat3;
        if(k == 3)
            return R.drawable.background_cat4;
        return R.drawable.background_cat1;
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class UuDaiViewholder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txt_title,txt_des,txtgetponup;
        private ConstraintLayout layout;
        public UuDaiViewholder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            img = itemView.findViewById(R.id.img);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_des = itemView.findViewById(R.id.txt_des);
            txtgetponup = itemView.findViewById(R.id.txt_getpon);
        }
    }
}
