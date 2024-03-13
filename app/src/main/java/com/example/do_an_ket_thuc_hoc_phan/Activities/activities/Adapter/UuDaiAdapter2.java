package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.List;

public class UuDaiAdapter2 extends RecyclerView.Adapter<UuDaiAdapter2.UuDaiholview2>{
    private List<ItemUuDai_2> list;
    public void setData(List<ItemUuDai_2> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UuDaiholview2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uudai_item_2,parent,false);
        return new UuDaiholview2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UuDaiholview2 holder, int position) {
        ItemUuDai_2 uuDai_2 = list.get(position);
        if(uuDai_2 == null) return;
        holder.imageView.setImageResource(uuDai_2.getImg());
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class UuDaiholview2 extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public UuDaiholview2(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img2);

        }
    }
}
