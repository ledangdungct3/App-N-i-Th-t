package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.List;

public class YTadapter extends RecyclerView.Adapter<YTadapter.YTadpterViewHodel>{
    private List<ItemYT> list;

    public  void setData(List<ItemYT> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public YTadpterViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yt,parent,false);
        return new YTadpterViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YTadpterViewHodel holder, int position) {
        ItemYT itemYT = list.get(position);
        if(itemYT  == null) return;
        byte[] img = itemYT.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        holder.img.setImageBitmap(bitmap);
        holder.txt.setText(itemYT.getTen());
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class YTadpterViewHodel extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txt;
        public YTadpterViewHodel(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.yt_img);
            txt = itemView.findViewById(R.id.yt_txt);
        }
    }

}
