package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Admin;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.LayItemSP;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.LayItemXT;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MainActivity;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateSP extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back,img_anh,img_camera,img_fodel;
    private EditText edt_gia,edt_mota,edt_uudai,edt_kichthuoc,edt_chatlieu,edt_themthongtin;
    private AppCompatButton bnt_capnhat,bnt_ht;
    CreateDatabase database;
    int REQUEST_CODE_FODEL = 456;
    int vt = 0;
    int so = 0;
    LayItemSP itemSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sp);
        anhxa();
        database = new CreateDatabase(this);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateSP.this,QLAdmin.class));
            }
        });
        img_camera.setOnClickListener(view -> {
            Intent maychup = new Intent(ACTION_IMAGE_CAPTURE);
            if(ActivityCompat.checkSelfPermission(UpdateSP.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(UpdateSP.this,new String[]{Manifest.permission.CAMERA},1);
                return;
            }
            startActivityForResult(maychup,99);
        });
        img_fodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FODEL);
            }
        });
        Intent intent = getIntent();
        so = intent.getIntExtra("so55",10000);
        if(so == 1){
            vt = intent.getIntExtra("vitri55",10000);
            itemSP = new LayItemSP();
            itemSP = database.laytheoidsp(vt);
            byte[] img = itemSP.getHinh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            img_anh.setImageBitmap(bitmap);
            edt_gia.setText(itemSP.getGia()+"");
            edt_mota.setText(itemSP.getMota());
            edt_uudai.setText(itemSP.getThongtin());
            edt_themthongtin.setText(itemSP.getThongtin());
        }

        bnt_capnhat.setOnClickListener(this);
        bnt_ht.setOnClickListener(this);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img_anh.setImageBitmap(photo);
        }
        if(requestCode == REQUEST_CODE_FODEL && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_anh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void anhxa() {
        img_back = findViewById(R.id.cnsp_back);
        img_anh = findViewById(R.id.cnsp_img);
        img_camera = findViewById(R.id.cnsp_camera);
        img_fodel = findViewById(R.id.cnsp_fodel);
        edt_gia = findViewById(R.id.cnsp_edt_price);
        edt_mota = findViewById(R.id.cnsp_edt_mota);
        edt_uudai = findViewById(R.id.cnsp_edt_sale);
        edt_kichthuoc = findViewById(R.id.cnsp_edt_kichthuoc);
        edt_chatlieu = findViewById(R.id.cnsp_edt_chatlieu);
        edt_themthongtin = findViewById(R.id.cnsp_edt_thongtin);
        bnt_capnhat = findViewById(R.id.cnsp_bnt_save);
        bnt_ht = findViewById(R.id.cnsp_bnt_hienthi);
    }

    @Override
    public void onClick(View view) {
        if(view == bnt_capnhat){
            LayItemSP layItemSP2 = new LayItemSP();
            String s = "Kích Thước: " + edt_kichthuoc.getText().toString() + "\n" +
                    "Chất Liệu: " + edt_chatlieu.getText().toString();
            layItemSP2.setHinh(ImageView_To2(img_anh));
            layItemSP2.setMota(edt_mota.getText().toString());
            layItemSP2.setGia(Double.parseDouble(edt_gia.getText().toString()));
            layItemSP2.setUudai(edt_uudai.getText().toString());
            layItemSP2.setThongtin(edt_themthongtin.getText().toString());
            layItemSP2.setHienthi(s);
            database.updateSPid(layItemSP2,vt);
            Toast.makeText(this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
        }
        if(view == bnt_ht){
            startActivity(new Intent(UpdateSP.this, MainActivity.class));
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
}