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

public class UpdateKM extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_back,img_anh,img_camera,img_fodel;
    private EditText edt_gia,edt_mota,edt_uudai,edt_kichthuoc,edt_chatlieu;
    private AppCompatButton bnt_capnhat,bnt_ht;
    CreateDatabase database;
    int REQUEST_CODE_FODEL = 456;
    LayItemXT itemXT;
    int so = 0;
    int vt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_km);
              anhxa();
        img_camera.setOnClickListener(view -> {
            Intent maychup = new Intent(ACTION_IMAGE_CAPTURE);
            if(ActivityCompat.checkSelfPermission(UpdateKM.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(UpdateKM.this,new String[]{Manifest.permission.CAMERA},1);
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
        database = new CreateDatabase(this);
        so = intent.getIntExtra("so",1000);
        if(so == 1){
            vt = intent.getIntExtra("vitri",10000);
            itemXT = database.laytheoid(vt);
            byte[] img = itemXT.getHinh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            img_anh.setImageBitmap(bitmap);
            edt_gia.setText(itemXT.getGia()+"");
            edt_mota.setText(itemXT.getMota());
            edt_uudai.setText(itemXT.getThongtin());
        }
        if(so == 2){
            vt = intent.getIntExtra("vitri2",10000);
            itemXT = database.laytheoidxt(vt);
            byte[] img = itemXT.getHinh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            img_anh.setImageBitmap(bitmap);
            edt_gia.setText(itemXT.getGia()+"");
            edt_mota.setText(itemXT.getMota());
            edt_uudai.setText(itemXT.getThongtin());
        }
        bnt_capnhat.setOnClickListener(this);
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
    img_back = findViewById(R.id.cnkm_back);
    img_anh = findViewById(R.id.cnkm_img);
    img_camera = findViewById(R.id.cnkm_camera);
    img_fodel = findViewById(R.id.cnkm_fodel);
    edt_gia = findViewById(R.id.edt_cnprice);
    edt_mota = findViewById(R.id.edt_cnmota);
    edt_uudai = findViewById(R.id.edt_cnuudai);
    edt_kichthuoc = findViewById(R.id.edt_cnkichthuoc);
    edt_chatlieu = findViewById(R.id.edt_cnchatlieu);
    bnt_capnhat = findViewById(R.id.cnkm_bnt_save);
    bnt_ht = findViewById(R.id.cnkm_bnt_hienthi);
      }


    @Override
    public void onClick(View view) {
        if (view == bnt_capnhat) {
            if (so == 1) {
                LayItemXT layItemXT2 = new LayItemXT();
                String s = "Kích Thước: " + edt_kichthuoc.getText().toString() + "\n" +
                        "Chất Liệu: " + edt_chatlieu.getText().toString();
                layItemXT2.setHinh(ImageView_To2(img_anh));
                layItemXT2.setMota(edt_mota.getText().toString());
                layItemXT2.setGia(Double.parseDouble(edt_gia.getText().toString()));
                layItemXT2.setThongtin(edt_uudai.getText().toString());
                layItemXT2.setHienthi(s);
                database.updateKMid(layItemXT2,vt);
                Toast.makeText(this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateKM.this, MainActivity.class));
            }
            if (so == 2) {
                LayItemXT layItemXT3 = new LayItemXT();
                String s = "Kích Thước: " + edt_kichthuoc.getText().toString() + "\n" +
                        "Chất Liệu: " + edt_chatlieu.getText().toString();
                layItemXT3.setHinh(ImageView_To2(img_anh));
                layItemXT3.setMota(edt_mota.getText().toString());
                layItemXT3.setGia(Double.parseDouble(edt_gia.getText().toString()));
                layItemXT3.setThongtin(edt_uudai.getText().toString());
                layItemXT3.setHienthi(s);
                database.updateXTid(layItemXT3,vt);
                Toast.makeText(this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
            }

        }
        if(view == bnt_ht){
            startActivity(new Intent(UpdateKM.this, MainActivity.class));
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