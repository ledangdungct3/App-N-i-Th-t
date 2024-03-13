package com.example.do_an_ket_thuc_hoc_phan.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout edt_dangnhap,edt_mk;
    private ImageView img_facebook,img_google,img_twitter;
    private AppCompatButton bnt_skip,bnt_login,bnt_signup;
    private TextView txtpassword;
    private CheckBox checkbook;
    SharedPreferences sharedPreferences;
    CreateDatabase database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        database = new CreateDatabase(this);
        sharedPreferences = getSharedPreferences("datalogin", Context.MODE_PRIVATE);
        edt_dangnhap.getEditText().setText(sharedPreferences.getString("user",""));
        edt_mk.getEditText().setText(sharedPreferences.getString("password",""));
        checkbook.setChecked(sharedPreferences.getBoolean("check",false));

        bnt_skip.setOnClickListener(this);
        bnt_signup.setOnClickListener(this);
        bnt_login.setOnClickListener(this);
    }

    private void anhxa() {
        edt_dangnhap = (TextInputLayout)findViewById(R.id.edt_dn);
        edt_mk = (TextInputLayout)findViewById(R.id.edt_password);
        img_facebook = findViewById(R.id.bnt_fa);
        img_google = findViewById(R.id.bnt_go);
        img_twitter = findViewById(R.id.bnt_ti);
        bnt_skip = findViewById(R.id.bnt_skip);
        bnt_login = findViewById(R.id.bnt_login);
        bnt_signup =  findViewById(R.id.bnt_sinup);
        txtpassword = findViewById(R.id.txt_forget);
        checkbook = findViewById(R.id.checkbox);
    }
    @Override
    public void onClick(View view) {
        // Kiểm tra xem người dùng đã nhấn nút đăng nhập hay không
        if(view == bnt_login){
            // Kiểm tra tính hợp lệ của thông tin đăng nhập và mật khẩu
            if(!checkDN() | !checkMK()){
                return;
            }
            // Lấy thông tin từ EditText và kiểm tra đăng nhập từ cơ sở dữ liệu
            String s1 = edt_dangnhap.getEditText().getText().toString();
            String s2 = edt_mk.getEditText().getText().toString();
            int ktra = database.KiemTraDN(s1,s2);
            // Xử lý
            if(s1.equals("") && s2.equals("")){
                if (checkbook.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", s1);
                    editor.putString("password", s2);
                    editor.putBoolean("check", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", "");
                    editor.putString("password", "");
                    editor.putBoolean("check", false);
                    editor.commit();
                }
                startActivity(new Intent(Login.this, MainActivity.class));
                return;
            }
             else if (ktra != 0 ) {
                    Application.username = s1;
                    if (checkbook.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user", s1);
                        editor.putString("password", s2);
                        editor.putBoolean("check", true);
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user", "");
                        editor.putString("password", "");
                        editor.putBoolean("check", false);
                        editor.commit();
                    }
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Sai Thong Tin DN", Toast.LENGTH_SHORT).show();
                }
            }
        if(view == bnt_skip){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
        // Xử lý khi người dùng nhấn nút "bnt_signup"
        if(view == bnt_signup){
            Intent intent = new Intent(Login.this,Register.class);
            startActivity(intent);
        }

    }

    private boolean checkDN() {
        // Lấy chuỗi từ EditText và loại bỏ các khoảng trắng thừa
        String var = edt_dangnhap.getEditText().getText().toString().trim();
        // Kiểm tra xem chuỗi có rỗng không
        if(var.isEmpty()){
            edt_dangnhap.setError(getResources().getString(R.string.dntrong));
            return false;
        }
        else{
            edt_dangnhap.setError(null);
            edt_dangnhap.setErrorEnabled(false);
            return true;
        }
    }
    private boolean checkMK() {
        String var = edt_mk.getEditText().getText().toString().trim();
        if(var.isEmpty()){
            edt_mk.setError(getResources().getString(R.string.dntrong));
            return false;
        }
        // Nếu chuỗi không rỗng, xóa thông báo lỗi và trả về true để thông báo rằng dữ liệu hợp lệ
        else{
            edt_mk.setError(null);
            edt_mk.setErrorEnabled(false);
            return true;
        }
    }
}