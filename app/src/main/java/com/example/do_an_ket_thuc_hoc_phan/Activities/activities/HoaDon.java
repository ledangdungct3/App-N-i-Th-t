package com.example.do_an_ket_thuc_hoc_phan.Activities.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.HoaDonAdapter2;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemDonHang;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemUuDai;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.InterfaceCLick.ItemClickView;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.ArrayList;
import java.util.List;

public class HoaDon extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private HoaDonAdapter2 hoaDonAdapter;
    private ImageView backto;
    CreateDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        anhxa();

        database = new CreateDatabase(getApplicationContext());
        hoaDonAdapter = new HoaDonAdapter2(this);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(hoaDonAdapter);
        backto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HoaDon.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<ItemDonHang> list = database.getallhoadon();
        hoaDonAdapter.setData(list);
    }

    private void anhxa() {
        recyclerView = findViewById(R.id.hoadon_recy);
        backto = findViewById(R.id.back_to);
    }


}