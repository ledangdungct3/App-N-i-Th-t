package com.example.do_an_ket_thuc_hoc_phan.Activities.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.LayItemSP;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.LayItemXT;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MuaHang extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_name,txt_gia,txt_sl,txt_ht,txt_end;
    private CircleImageView cirimg;
    private ImageView img_plus,img_minus,mh_back,view_animation,img_mua;
    private AppCompatButton bnt_mua;
    private CreateDatabase database;
    private LayItemXT itemXT;
    private LayItemSP itemSP;
    LinearLayout bnt_cart;
    int solo = 1;
    double gia = 1;
    int so;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_hang);
        database = new CreateDatabase(this);
        anhxa();

        Intent intent = getIntent();

        so = intent.getIntExtra("so",1000);
        if(so == 1){
            int vt = intent.getIntExtra("vitri",10000);
            itemXT = database.laytheoid(vt+1);
            txt_name.setText(itemXT.getMota());
            txt_gia.setText(itemXT.getGia()+" nVND");
            txt_ht.setText("Sale: "+itemXT.getThongtin()+"\n"+itemXT.getHienthi());
            byte[] img = itemXT.getHinh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            cirimg.setImageBitmap(bitmap);
        }
        if(so == 2){
            int vt = intent.getIntExtra("vitri2",10000);
            itemXT = database.laytheoidxt(vt+1);
            txt_name.setText(itemXT.getMota());
            txt_gia.setText(itemXT.getGia()+" nVND");
            txt_ht.setText(itemXT.getHienthi()+"\n"+"Thông Tin "+itemXT.getThongtin());
            byte[] img = itemXT.getHinh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            cirimg.setImageBitmap(bitmap);

        }
        if(so == 3){
            int vt = intent.getIntExtra("vitri3",10000);
            itemSP = database.laytheoidsp(vt+1);
            txt_name.setText(itemSP.getMota());
            txt_gia.setText(itemSP.getGia()+" nVND");
            txt_ht.setText("Ưu Đãi: "+itemSP.getUudai()+"\n"+itemSP.getHienthi()+"\n"+"Thông Tin "+itemSP.getThongtin());
            byte[] img = itemSP.getHinh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            cirimg.setImageBitmap(bitmap);
        }

        img_plus.setOnClickListener(this);
        img_minus.setOnClickListener(this);
        bnt_cart.setOnClickListener(this);

    }


    private void anhxa() {
        txt_name = findViewById(R.id.mh_tensp);
        txt_gia = findViewById(R.id.mh_gia);
        txt_sl = findViewById(R.id.mh_demso);
        txt_ht = findViewById(R.id.mh_ht);
        img_minus = findViewById(R.id.sp_minus);
        img_plus = findViewById(R.id.sp_plus);
        cirimg = findViewById(R.id.sp_anh);
        bnt_cart = findViewById(R.id.sp_bnt);
        mh_back = findViewById(R.id.muahang_back);
        view_animation = findViewById(R.id.viewanimation);
        txt_end = findViewById(R.id.txt_end);
        img_mua = findViewById(R.id.bnt_mua);
    }

    @Override
    public void onClick(View view) {
        if(view == img_minus){
            if(solo > 1){
                solo--;
                txt_sl.setText(solo+"");
               if(so == 1 || so == 2){
                   gia = itemXT.getGia()*solo;
               }
               else if( so == 3){
                   gia = itemSP.getGia()*solo;
               }

                txt_gia.setText(gia+" nVND");

            }
            else{
                Toast.makeText(this, "loi", Toast.LENGTH_SHORT).show();
            }
        }
        if(view == img_plus){
            solo++;
            txt_sl.setText(solo+"");
            if(so == 1 || so == 2){
                gia = itemXT.getGia()*solo;
            }
            else if( so == 3){
                gia = itemSP.getGia()*solo;
            }
            txt_gia.setText(gia+" nVND");
        }
        if(view == bnt_cart){
            String s = "Đã Mua "+txt_name.getText().toString() + " sl : "+txt_sl.getText().toString();
            database.addItemhoadon(ImageView_To2(cirimg),s,txt_gia.getText().toString());
            clickme();
            AnimationUtil.translate(view_animation, img_mua, txt_end, new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MuaHang.this,MainActivity.class);
                    startActivity(intent);
                }
            },1000);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();



    }
    private void clickme() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);
        Notification notification = new NotificationCompat.Builder(this,MyAplycation.CHANNEL_ID)
                .setContentTitle("Thong Bao")
                .setContentText("Bạn Đã Thanh Toán 1 Đơn Hàng")
                .setSmallIcon(R.drawable.ic_noti)
                .setColor(getResources().getColor(R.color.purple_500))
                .setLargeIcon(bitmap)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(manager != null){
            manager.notify(getdate(),notification);
        }
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
    private int getdate() {
        return (int) new Date().getTime();
    }
}