package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.HienThiAcTy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment.ThemFragment;
import com.example.do_an_ket_thuc_hoc_phan.R;

public class Them_Hien_Thi extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_back,img_more;
    private ImageView img_profile;
    private TextView tenht;
    private AppCompatButton button;
    private TextView txt_them_ten,ngaysinh,sdt,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hien_thi);
        anhxa();
        img_back.setOnClickListener(this);
        img_more.setOnClickListener(this);
        button.setOnClickListener(this);
        Intent intent = getIntent();
        txt_them_ten.setText(intent.getStringExtra("key_ten2"));
        ngaysinh.setText(intent.getStringExtra("key_ngaysinh2"));
        sdt.setText(intent.getStringExtra("key_sdt"));
        email.setText(intent.getStringExtra("key_email"));

    }

    private void anhxa() {
        img_back = findViewById(R.id.ht_back);
        img_more = findViewById(R.id.ht_more);
        img_profile = findViewById(R.id.them_img_profile);
        tenht = findViewById(R.id.them_ten);
        button = findViewById(R.id.bntchinhsua);
        txt_them_ten = findViewById(R.id.them_ten);
        ngaysinh = findViewById(R.id.them_chon_gt);
        sdt = findViewById(R.id.them_chon_sdt);
        email = findViewById(R.id.them_chon_email);
    }

    @Override
    public void onClick(View view) {
        if(view == img_back){
            startActivity(new Intent(Them_Hien_Thi.this, ThemFragment.class));
        }
        if(view == img_more){
            PopupMenu popupMenu = new PopupMenu(img_more.getContext(), img_more);
            popupMenu.getMenuInflater().inflate(R.menu.doi_anh_menu, popupMenu.getMenu());
            popupMenu.show();
        }
        if(view == button){
            Intent intent = new Intent(Them_Hien_Thi.this,ChinhSuaActi.class);
            intent.putExtra("key_ten",txt_them_ten.getText().toString());
            intent.putExtra("key_ngaysinh",ngaysinh.getText().toString());
            intent.putExtra("key_sdt",sdt.getText().toString());
            intent.putExtra("key_email",email.getText().toString());
            startActivity(intent);

        }
    }
}