package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.HienThiAcTy.Them_Danh_Sach_YT;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.InterfaceCLick.ItemClickView;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MuaHang;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class HoaDonAdapter2 extends RecyclerView.Adapter<HoaDonAdapter2.HoaDonViewHodel>{
    private List<ItemDonHang> list;
    private Context context;
    int rd;
    private ItemClickView itemClickView;
    CreateDatabase database;

    public HoaDonAdapter2(Context context) {
        this.context = context;
    }

    public void setData(List<ItemDonHang> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HoaDonViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdonhang,parent,false);
        return new HoaDonViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHodel holder, int position) {
        ItemDonHang itemDonHang = list.get(position);
        if (itemDonHang == null) return;
        byte[] img = itemDonHang.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
        holder.img.setImageBitmap(bitmap);
        holder.txt_name.setText(itemDonHang.getMota());
        holder.txt_gia.setText(itemDonHang.getGia());
        database = new CreateDatabase(context);
    }


    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public class HoaDonViewHodel extends RecyclerView.ViewHolder{
        private CircleImageView img;
        private TextView txt_name,txt_gia;
        private AppCompatButton button;
        public HoaDonViewHodel(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.hoadon_img);
            txt_name = itemView.findViewById(R.id.hoadon_mota);
            txt_gia = itemView.findViewById(R.id.hoadon_gia);
            button = itemView.findViewById(R.id.hoadon_bnt);
            button.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.Q)
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(button.getContext(), button);
                    popupMenu.getMenuInflater().inflate(R.menu.popupyt, popupMenu.getMenu());
                    popupMenu.setForceShowIcon(true);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if(menuItem.getItemId() == R.id.menu_yeuthich2){
                                database.addItemYT(ImageView_To2(img),txt_name.getText().toString());
                                Intent intent = new Intent(context, Them_Danh_Sach_YT.class);
                                context.startActivity(intent);
                            }
                            else if(menuItem.getItemId() == R.id.menu_huydon){
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Thông Báo Xóa");
                                builder.setMessage("Bạn Có Chắc Xóa Không?");
                                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                         database.xoaHDtheoid(getAdapterPosition()+1);

                                    }
                                });
                                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
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
