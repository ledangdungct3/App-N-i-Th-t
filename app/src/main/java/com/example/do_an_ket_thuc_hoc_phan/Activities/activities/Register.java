package com.example.do_an_ket_thuc_hoc_phan.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.DTO.ThongTinDN;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText edt_fullname,edt_user,edt_pass,edt_email,edt_phone;
    AppCompatButton button;
    CreateDatabase database;
    ThongTinDN thongTinDN;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = new CreateDatabase(this);
        thongTinDN = new ThongTinDN();
        anhxa();
// sử lí sk khi bấm nút đk
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            // ktra thông tin đn vào hợp lệ hay ko nếu hợp lệ sẽ gán vào đối tượng đn
            public void onClick(View view) {
                if(!HoVaTen()  | !UserName() | !Email() | !Phone() | !password()) {
                    return;
                }
                    thongTinDN.setFull_name(edt_fullname.getText().toString());
                    thongTinDN.setEmail(edt_email.getText().toString());
                    thongTinDN.setUer_name(edt_user.getText().toString());
                    thongTinDN.setNumber(edt_phone.getText().toString());
                    thongTinDN.setPass_word(edt_pass.getText().toString());
                    database.addND(thongTinDN);
                    Toast.makeText(Register.this, "Dang Ky Thanh Cong \n " , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

    }

    private void anhxa() {
        edt_fullname = findViewById(R.id.edt_fullname);
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pass);
        edt_user = findViewById(R.id.edt_user);
        edt_phone = findViewById(R.id.edt_number);
        button = findViewById(R.id.edt_dangnhap);
        img_back= findViewById(R.id.dk_thoat);
    }
    private boolean HoVaTen(){
        String var = edt_fullname.getText().toString().trim();
        if(var.isEmpty()){
            edt_fullname.setError(getResources().getString(R.string.dntrong));
            return false;
        }else{
            return true;
        }
    }
    private boolean UserName(){
        String var = edt_user.getText().toString().trim(); // Lấy chuỗi nhập vào từ EditText edt_user và loại bỏ khoảng trắng
        String checkspaces = "\\A\\w{1,50}\\z";// Mẫu để kiểm tra khoảng trắng
        if(var.isEmpty()){
            edt_user.setError(getResources().getString(R.string.dntrong));
            return false;
        }
        else if(var.length()>50){
            edt_user.setError("Phải nhỏ hơn 50 ký tự");
        return false;
        }
        else if(!var.matches(checkspaces)){
        edt_user.setError("Không được cách chữ!");
        return false;
      }
        else
        return true;
    }
    private boolean Email(){
        String val = edt_email.getText().toString().trim();
        String checkspaces = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        //Sreing res = "^\\w+[a-z0-9]*@{1}\\[a-z]+.+"com"
        if(val.isEmpty()){
            edt_email.setError(getResources().getString(R.string.dntrong));
            return false;
        }
        else if(!val.matches(checkspaces)){
            edt_email.setError("Email không hợp lệ!");
            return false;
        }
        else {
            return true;
        }
    }
    private boolean Phone(){
        String val = edt_phone.getText().toString().trim();

        if(val.isEmpty()){
            edt_phone.setError(getResources().getString(R.string.dntrong));
            return false;
        }
        // Kiểm tra nếu độ dài chuỗi khác 10 ký tự
        else if(val.length() != 10){
            edt_phone.setError("Số điện thoại không hợp lệ!");
            return false;
        }
        else {
            return true;
        }
    }
    private boolean password(){
         Pattern s =  Pattern.compile("^" +
                 //"(?=.*[@#$%^&+=])" +     // at least 1 special character
                 "[a-z0-9]" +            // Không có khoảng trắng và chỉ chứa ký tự thường và số
                 ".{6,}" +                // at least 7 characters
                 "$");
        String val = edt_pass.getText().toString().trim();
        if(val.isEmpty()) {
            edt_pass.setError(getResources().getString(R.string.dntrong));
            return false;
        }else if(!s.matcher(val).matches()){
            edt_pass.setError("Mật khẩu ít nhất 7 ký tự!");
            return false;
        }
        else {
            return true;
        }
    }
}