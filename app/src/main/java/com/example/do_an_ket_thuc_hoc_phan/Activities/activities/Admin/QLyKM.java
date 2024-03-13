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

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MainActivity;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class QLyKM extends AppCompatActivity {
    private ImageView img_back,img_anh_dl,img_camera,img_fodel;
    private EditText edt_gia,edt_mota,edt_kt,edt_uudai,edt_cl;
    private AppCompatButton bnt_luu,bntht;
    int REQUEST_CODE_FODEL = 456;
    private CreateDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qly_km);
        anhxa();
        database = new CreateDatabase(this);
        img_back.setOnClickListener(view -> startActivity(new Intent(QLyKM.this,QLAdmin.class)));
        img_camera.setOnClickListener(view -> {
            Intent maychup = new Intent(ACTION_IMAGE_CAPTURE);
            if(ActivityCompat.checkSelfPermission(QLyKM.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(QLyKM.this,new String[]{Manifest.permission.CAMERA},
                        1);
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
        bntht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QLyKM.this, MainActivity.class);
                startActivity(intent);
            }
        });
        bnt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    String s = "Kích Thước: "+edt_kt.getText().toString()+"\n"+
                            "Chất Liệu: "+edt_cl.getText().toString();
                    database.addItemkm(ImageView_To2(img_anh_dl),edt_mota.getText().toString(),Double.parseDouble(edt_gia.getText().toString()),edt_uudai.getText().toString(),s);
                    Toast.makeText(QLyKM.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(QLyKM.this, "Ko Ket Noi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img_anh_dl.setImageBitmap(photo);
        }
        if(requestCode == REQUEST_CODE_FODEL && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_anh_dl.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void anhxa() {
        img_back = findViewById(R.id.km_back);
        img_anh_dl = findViewById(R.id.km_img);
        img_camera = findViewById(R.id.km_camera);
        img_fodel = findViewById(R.id.km_fodel);
        edt_gia = findViewById(R.id.edt_price);
        edt_mota = findViewById(R.id.edt_mota);
        edt_kt = findViewById(R.id.edt_kichthuoc);
        edt_uudai = findViewById(R.id.edt_uudai);
        edt_cl = findViewById(R.id.edt_chatlieu);
        bnt_luu = findViewById(R.id.km_bnt_save);
        bntht = findViewById(R.id.km_bnt_hienthi);
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