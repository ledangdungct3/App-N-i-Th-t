package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.HienThiAcTy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemYT;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.YTadapter;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MainActivity;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.List;

public class Them_Danh_Sach_YT extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView imageView;
    YTadapter yTadapter;
    CreateDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_danh_sach_yt);
        anhxa();
        yTadapter = new YTadapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(yTadapter);
        database = new CreateDatabase(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Them_Danh_Sach_YT.this, MainActivity.class));
            }
        });

    }

    private void anhxa() {
        imageView = findViewById(R.id.ytimg_back);
        recyclerView = findViewById(R.id.yt_recy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<ItemYT> list = database.getallYT();
        yTadapter.setData(list);
    }
}