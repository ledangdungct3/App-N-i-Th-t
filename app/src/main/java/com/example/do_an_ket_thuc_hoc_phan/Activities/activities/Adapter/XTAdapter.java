package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.InterfaceCLick.ItemClickView;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MuaHang;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.List;

public class XTAdapter extends RecyclerView.Adapter<XTAdapter.Xtviewholdel>{
    private List<ItemXemThem> list;
    Context context;
    private int rd;
    private ItemClickView itemClickView;

    public XTAdapter(ItemClickView itemClickView) {
        this.itemClickView = itemClickView;
    }

    public void setData(List<ItemXemThem> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Xtviewholdel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xemthem_item,parent,false);
        return new Xtviewholdel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Xtviewholdel holder, int position) {
        ItemXemThem itemXemThem = list.get(position);
        if(itemXemThem == null) return;
        rd = (int)position;
        byte[] img = itemXemThem.getXt_img();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        holder.img.setImageBitmap(bitmap);
        holder.txt_price.setText(itemXemThem.getTxt_price()+" nVND");
        holder.txt_loai.setText(itemXemThem.getTxt_loai());

    }


    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public class Xtviewholdel extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txt_price,txt_loai;
        private AppCompatButton click;
        public Xtviewholdel(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.xemthem_img);
            txt_price = itemView.findViewById(R.id.xemthem_txt);
            txt_loai = itemView.findViewById(R.id.xemthem_txt2);
            click = itemView.findViewById(R.id.xemthem_bnt);
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
