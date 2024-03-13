package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.HienThiAcTy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.do_an_ket_thuc_hoc_phan.R;

public class ChinhSuaActi extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_name,edt_ngaysinh,edt_std,edt_email;
    private AppCompatButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua);
        edt_name = findViewById(R.id.edt_ten_cs);
        edt_ngaysinh = findViewById(R.id.edt_ngaysinh_cs);
        edt_std = findViewById(R.id.edt_dienthoai_cs);
        edt_email = findViewById(R.id.edt_email_cs);
        button = findViewById(R.id.bntchinhsua);
        Intent intent = getIntent();
            edt_name.setText(intent.getStringExtra("key_ten"));
            edt_ngaysinh.setText(intent.getStringExtra("key_ngaysinh"));
            edt_std.setText(intent.getStringExtra("key_sdt"));
            edt_email.setText(intent.getStringExtra("key_email"));

    }
    @Override
    public void onClick(View view) {
        if(view == button){
            Intent intent = new Intent(ChinhSuaActi.this,Them_Hien_Thi.class);
            intent.putExtra("key_ten2",edt_name.getText().toString());
            intent.putExtra("key_ngaysinh2",edt_ngaysinh.getText().toString());
            intent.putExtra("key_sdt2",edt_std.getText().toString());
            intent.putExtra("key_email",edt_email.getText().toString());
            startActivity(intent);
        }
    }
}