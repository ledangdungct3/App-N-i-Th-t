package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.InterfaceCLick.ItemClickView;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MuaHang;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.List;

public class KMAdapter extends RecyclerView.Adapter<KMAdapter.KMAdapterViewHolder>{
    private List<ItemKM> itemKMList;
    Context context;
    private ItemClickView itemClickView;

    public KMAdapter(ItemClickView itemClickView) {
        this.itemClickView = itemClickView;
    }

    private int rd = 0;
    public void setData(List<ItemKM> list){
        this.itemKMList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public KMAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khuyenmai,parent,false);
        return new KMAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KMAdapterViewHolder holder, int position) {
        ItemKM itemKM = itemKMList.get(position);
        rd = (int)position;
        holder.layout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),getbackg()));
        byte[] img = itemKM.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        holder.img.setImageBitmap(bitmap);
        holder.txt_title.setText(itemKM.getName_title());
        holder.txt_cate.setText("Sale "+itemKM.getName_cate()+"%");

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
        if (itemKMList != null) return itemKMList.size();
        return 0;
    }

    public class KMAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt_title,txt_cate;
        private AppCompatButton click;
        private ConstraintLayout layout;
        public KMAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.khuyenmai_img);
            txt_title = itemView.findViewById(R.id.khuyenmain_txt);
            txt_cate = itemView.findViewById(R.id.khuyenmai_txt2);
            click = itemView.findViewById(R.id.khuyenmai_bnt);
            layout = itemView.findViewById(R.id.layout);
            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickView != null){
                        itemClickView.onclick(getAdapterPosition());
                    }
                }
            });
        }



    }

}
