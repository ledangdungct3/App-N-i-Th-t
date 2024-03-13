package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;


import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.LayItemSP;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.InterfaceCLick.ItemClickView;
import com.example.do_an_ket_thuc_hoc_phan.R;
import com.ramotion.foldingcell.FoldingCell;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHoldel>{
    private  List<ItemSanPham> list;
    private Context context;
    private ItemClickView itemClickView;
    private ItemYT itemYT;
    private LayItemSP itemSP;
    private int rd = 0;
    private CreateDatabase createDatabase;
    public DonHangAdapter(Context context, ItemClickView itemClickView) {
        this.context = context;
        this.itemClickView = itemClickView;
    }

    public void setData(List<ItemSanPham> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DonHangViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donhang_list,parent,false);
        return new DonHangViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHoldel holder, int position) {
        ItemSanPham itemSanPham = list.get(position);
        if(itemSanPham == null) return;
        rd = (int)position;
        byte[] img = itemSanPham.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        holder.imageView.setImageBitmap(bitmap);
        holder.txt_phong.setText(itemSanPham.getTxt_phong());
        createDatabase = new CreateDatabase(context);

    }
    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class DonHangViewHoldel extends RecyclerView.ViewHolder{
        private  ImageView imageView;
        private  AppCompatButton button;
        private  TextView txt_phong;
        private FoldingCell foldingCell;
        private TextView txt_ten,txt_gia,txt_uudai,txt_cl,txt_tt;
        public DonHangViewHoldel(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.donhang_img);
            txt_phong = itemView.findViewById(R.id.donhang_txtphong);
            button = itemView.findViewById(R.id.donhang_bnt);
            foldingCell = itemView.findViewById(R.id.foldingcell);
            txt_ten = itemView.findViewById(R.id.inclu_tensp);
            txt_gia = itemView.findViewById(R.id.inclu_gia);
            txt_uudai = itemView.findViewById(R.id.inclu_uudai);
            txt_cl = itemView.findViewById(R.id.inclu_chatlieu);
            txt_tt = itemView.findViewById(R.id.inclu_thongtin);

            button.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.Q)
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(button.getContext(), button);
                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                    popupMenu.setForceShowIcon(true);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getItemId() == R.id.menu_datnhanh) {
                                if (itemClickView != null){
                                    itemClickView.onclick(getAdapterPosition());
                                }
                            } else if (menuItem.getItemId() == R.id.menu_hienthinhanh) {
                                foldingCell.toggle(false);
                                itemSP = createDatabase.laytheoidsp(rd+1);
                                txt_ten.setText("Tên Sản Phẩm: "+itemSP.getMota());
                                txt_gia.setText("Giá: " + itemSP.getGia());
                                txt_uudai.setText("Ưu Đãi: " + itemSP.getUudai());
                                txt_cl.setText(itemSP.getHienthi());
                                txt_tt.setText(itemSP.getThongtin());
                                foldingCell.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        foldingCell.toggle(false);
                                    }
                                });
                            } else if (menuItem.getItemId() == R.id.menu_yeuthich) {
                                    createDatabase.addItemYT(ImageView_To2(imageView),txt_phong.getText().toString());
                            }
                            return true;
                        }
                    });
                }
            });

        }
        public byte[] ImageView_To2(ImageView h){
            BitmapDrawable drawable = (BitmapDrawable) h.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.anh2);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] bytearray = stream.toByteArray();
            return bytearray;
        }
    }




}
