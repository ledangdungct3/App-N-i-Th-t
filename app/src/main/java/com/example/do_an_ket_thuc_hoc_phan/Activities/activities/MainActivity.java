package com.example.do_an_ket_thuc_hoc_phan.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.FragmentAdapter;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.Photo;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.PhotoAdapter;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_home,img_donhang,img_uudai,img_them;
    private FragmentAdapter adapter;
    private ViewPager2 pager2;
    private FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        adapter = new FragmentAdapter(this);
        pager2.setAdapter(adapter);
        img_home.setOnClickListener(this);
        img_donhang.setOnClickListener(this);
        img_uudai.setOnClickListener(this);
        img_them.setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HoaDon.class));
            }
        });

    }

    private void anhxa() {
        pager2 = findViewById(R.id.viewpager2);
        img_home = findViewById(R.id.navi_home);
        img_donhang = findViewById(R.id.navi_dondat);
        img_uudai = findViewById(R.id.navi_sale);
        img_them = findViewById(R.id.navi_profile);
        button = findViewById(R.id.floating);
    }

    @Override
    public void onClick(View view) {
        if(view == img_home){
            pager2.setCurrentItem(0);
        }
        if(view == img_donhang){
            pager2.setCurrentItem(1);
        }
        if(view == img_uudai){
            pager2.setCurrentItem(2);
        } if(view == img_them){
            pager2.setCurrentItem(3);
        }


    }
}