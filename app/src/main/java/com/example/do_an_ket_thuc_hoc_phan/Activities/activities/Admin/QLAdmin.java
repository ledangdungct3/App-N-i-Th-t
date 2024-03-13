package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MainActivity;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MuaHang;
import com.example.do_an_ket_thuc_hoc_phan.R;

public class QLAdmin extends AppCompatActivity implements View.OnClickListener{
    private AppCompatButton bnt_km,bnt_xt,bnt_ud,bnt_dh;
    CreateDatabase database;
    private ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qladmin);
        database = new CreateDatabase(this);
        anhxa();
        bnt_km.setOnClickListener(this);
        bnt_xt.setOnClickListener(this);
        bnt_ud.setOnClickListener(this);
        bnt_dh.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    private void anhxa() {
        bnt_km = findViewById(R.id.bnt_km);
        bnt_xt = findViewById(R.id.bnt_xt);
        bnt_ud = findViewById(R.id.bnt_ud);
        bnt_dh = findViewById(R.id.bnt_dh);
        img_back = findViewById(R.id.admin_back);

    }

    @Override
    public void onClick(View view) {
        if(view == bnt_km){
            PopupMenu popupMenu = new PopupMenu(bnt_km.getContext(), bnt_km);
            popupMenu.getMenuInflater().inflate(R.menu.menu_them_sua_xoa, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                  if(menuItem.getItemId() == R.id.menu_them){
                      Toast.makeText(QLAdmin.this, "Them Danh Sach", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(QLAdmin.this,QLyKM.class);
                      startActivity(intent);
                  }
                  else if(menuItem.getItemId() == R.id.menu_sua){
                      final Dialog dialog = new Dialog(QLAdmin.this);
                      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                      dialog.setContentView(R.layout.dialog_xoa_danh_sach);
                      Window window = dialog.getWindow();
                      window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                      window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                      dialog.setCancelable(false);
                      dialog.show();
                      EditText editText = dialog.findViewById(R.id.xoa_edt);
                      AppCompatButton buttonchon = dialog.findViewById(R.id.bnt_xoa);
                      AppCompatButton buttonthoat = dialog.findViewById(R.id.bnt_xoa_can);
                      buttonthoat.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              dialog.dismiss();
                          }
                      });
                      buttonchon.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              String s = editText.getText().toString();
                              int i = Integer.parseInt(s);
                              Intent intent = new Intent(QLAdmin.this, UpdateKM.class);
                              intent.putExtra("vitri",i);
                              intent.putExtra("so",1);
                              startActivity(intent);

                          }
                      });
                  }
                  else if(menuItem.getItemId() == R.id.menu_xoa ){
                      final Dialog dialog = new Dialog(QLAdmin.this);
                      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                      dialog.setContentView(R.layout.dialog_xoa_danh_sach);
                      Window window = dialog.getWindow();
                      window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                      window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                      dialog.setCancelable(false);
                      dialog.show();
                      EditText editText = dialog.findViewById(R.id.xoa_edt);
                      AppCompatButton buttonchon = dialog.findViewById(R.id.bnt_xoa);
                      AppCompatButton buttonthoat = dialog.findViewById(R.id.bnt_xoa_can);
                      buttonthoat.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              dialog.dismiss();
                          }
                      });
                      buttonchon.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                                String s = editText.getText().toString();
                                int i = Integer.parseInt(s);
                                database.xoaKMtheoid(i);
                                Intent intent = new Intent(QLAdmin.this, MainActivity.class);
                                startActivity(intent);
                              Toast.makeText(QLAdmin.this, "Xóa Danh Sách", Toast.LENGTH_SHORT).show();
                          }
                      });
                  }
                    return true;
                }
            });
            popupMenu.show();
        }
        if(view == bnt_xt){
            PopupMenu popupMenu = new PopupMenu(bnt_xt.getContext(), bnt_xt);
            popupMenu.getMenuInflater().inflate(R.menu.menu_them_sua_xoa, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if(menuItem.getItemId() == R.id.menu_them){
                        Toast.makeText(QLAdmin.this, "Them Danh Sach", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QLAdmin.this,QLyXT.class);
                        startActivity(intent);
                    }
                    else if(menuItem.getItemId() == R.id.menu_sua){
                        final Dialog dialog = new Dialog(QLAdmin.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_xoa_danh_sach);
                        Window window = dialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setCancelable(false);
                        dialog.show();
                        EditText editText = dialog.findViewById(R.id.xoa_edt);
                        AppCompatButton buttonchon = dialog.findViewById(R.id.bnt_xoa);
                        AppCompatButton buttonthoat = dialog.findViewById(R.id.bnt_xoa_can);
                        buttonthoat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        buttonchon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String s = editText.getText().toString();
                                int i = Integer.parseInt(s);
                                Intent intent = new Intent(QLAdmin.this, UpdateKM.class);
                                intent.putExtra("vitri2",i);
                                intent.putExtra("so",2);
                                startActivity(intent);

                            }
                        });
                        Toast.makeText(QLAdmin.this, "Sua Danh Sach", Toast.LENGTH_SHORT).show();
                    }
                    else if(menuItem.getItemId() == R.id.menu_xoa ){
                        final Dialog dialog = new Dialog(QLAdmin.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_xoa_danh_sach);
                        Window window = dialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setCancelable(false);
                        dialog.show();
                        EditText editText = dialog.findViewById(R.id.xoa_edt);
                        AppCompatButton buttonchon = dialog.findViewById(R.id.bnt_xoa);
                        AppCompatButton buttonthoat = dialog.findViewById(R.id.bnt_xoa_can);
                        buttonthoat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        buttonchon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String s = editText.getText().toString();
                                int i = Integer.parseInt(s);
                                database.xoaXTtheoid(i);
                                Intent intent = new Intent(QLAdmin.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(QLAdmin.this, "Xoa Danh Sach", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    return true;
                }
            });
            popupMenu.show();
        }
        if(view == bnt_dh){
            PopupMenu popupMenu = new PopupMenu(bnt_dh.getContext(), bnt_dh);
            popupMenu.getMenuInflater().inflate(R.menu.menu_them_sua_xoa, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if(menuItem.getItemId() == R.id.menu_them){
                        Toast.makeText(QLAdmin.this, "Them Danh Sach", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QLAdmin.this,QLySanPham.class);
                        startActivity(intent);
                    }
                    else if(menuItem.getItemId() == R.id.menu_sua){
                        final Dialog dialog = new Dialog(QLAdmin.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_xoa_danh_sach);
                        Window window = dialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setCancelable(false);
                        dialog.show();
                        EditText editText = dialog.findViewById(R.id.xoa_edt);
                        AppCompatButton buttonchon = dialog.findViewById(R.id.bnt_xoa);
                        AppCompatButton buttonthoat = dialog.findViewById(R.id.bnt_xoa_can);
                        buttonthoat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        buttonchon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String s = editText.getText().toString();
                                int i = Integer.parseInt(s);
                                Intent intent = new Intent(QLAdmin.this, UpdateSP.class);
                                intent.putExtra("vitri55",i);
                                intent.putExtra("so55",1);
                                startActivity(intent);

                            }
                        });
                        Toast.makeText(QLAdmin.this, "Sua Danh Sach", Toast.LENGTH_SHORT).show();
                    }
                    else if(menuItem.getItemId() == R.id.menu_xoa ){
                        final Dialog dialog = new Dialog(QLAdmin.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_xoa_danh_sach);
                        Window window = dialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setCancelable(false);
                        dialog.show();
                        EditText editText = dialog.findViewById(R.id.xoa_edt);
                        AppCompatButton buttonchon = dialog.findViewById(R.id.bnt_xoa);
                        AppCompatButton buttonthoat = dialog.findViewById(R.id.bnt_xoa_can);
                        buttonthoat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        buttonchon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String s = editText.getText().toString();
                                int i = Integer.parseInt(s);
                                database.xoaSPtheoid(i);
                                Toast.makeText(QLAdmin.this, "Xoa Danh Sach", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(QLAdmin.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                    return true;
                }
            });
            popupMenu.show();
        }
        if(view == bnt_ud){
            PopupMenu popupMenu = new PopupMenu(bnt_ud.getContext(), bnt_ud);
            popupMenu.getMenuInflater().inflate(R.menu.menu_them_sua_xoa, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if(menuItem.getItemId() == R.id.menu_them){
                        Toast.makeText(QLAdmin.this, "Them Danh Sach", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QLAdmin.this,QLyUuDai.class);
                        startActivity(intent);
                    }
                    else if(menuItem.getItemId() == R.id.menu_sua){

                        Toast.makeText(QLAdmin.this, "Sua Danh Sach", Toast.LENGTH_SHORT).show();
                    }
                    else if(menuItem.getItemId() == R.id.menu_xoa ){
                        final Dialog dialog = new Dialog(QLAdmin.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_xoa_danh_sach);
                        Window window = dialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.setCancelable(false);
                        dialog.show();
                        EditText editText = dialog.findViewById(R.id.xoa_edt);
                        AppCompatButton buttonchon = dialog.findViewById(R.id.bnt_xoa);
                        AppCompatButton buttonthoat = dialog.findViewById(R.id.bnt_xoa_can);
                        buttonthoat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        buttonchon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String s = editText.getText().toString();
                                int i = Integer.parseInt(s);
                                database.xoaUDtheoid(i);
                                Toast.makeText(QLAdmin.this, "Xoa Danh Sach", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(QLAdmin.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                    return true;
                }
            });
            popupMenu.show();
        }
        if(view == img_back){
            startActivity(new Intent(QLAdmin.this,MainActivity.class));
        }
    }

}