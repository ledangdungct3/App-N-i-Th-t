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
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment.HomeFragment;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class QLyUuDai extends AppCompatActivity {
    private ImageView img_back,img_anh_dl,img_camera,img_fodel;
    private EditText edt_uudai,edt_mota;
    private AppCompatButton bnt_luu,bntht;
    int REQUEST_CODE_FODEL = 456;
    private CreateDatabase createDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDatabase = new CreateDatabase(this);
        setContentView(R.layout.activity_qly_uu_dai);
        anhxa();
        img_back.setOnClickListener(view -> startActivity(new Intent(QLyUuDai.this,QLAdmin.class)));
        img_camera.setOnClickListener(view -> {
            Intent maychup = new Intent(ACTION_IMAGE_CAPTURE);
            if(ActivityCompat.checkSelfPermission(QLyUuDai.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(QLyUuDai.this,new String[]{Manifest.permission.CAMERA},1);
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
                Intent intent = new Intent(QLyUuDai.this, MainActivity.class);
                startActivity(intent);
            }
        });
        bnt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    createDatabase.addItemud(ImageView_To2(img_anh_dl),edt_uudai.getText().toString(),edt_mota.getText().toString());
                    Toast.makeText(QLyUuDai.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(QLyUuDai.this, "Ko Ket Noi", Toast.LENGTH_SHORT).show();
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
        img_back = findViewById(R.id.ud_back);
        img_anh_dl = findViewById(R.id.ud_img);
        img_camera = findViewById(R.id.ud_camera);
        img_fodel = findViewById(R.id.ud_fodel);
        edt_uudai = findViewById(R.id.ud_edt_sale);
        edt_mota = findViewById(R.id.ud_edt_mota);
        bnt_luu = findViewById(R.id.ud_bnt_save);
        bntht = findViewById(R.id.ud_bnt_hienthi);
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