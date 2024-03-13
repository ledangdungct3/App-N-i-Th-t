package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemKM;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemXemThem;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.KMAdapter;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.Photo;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.PhotoAdapter;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.XTAdapter;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.InterfaceCLick.ItemClickView;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MuaHang;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment implements ItemClickView{

    private ViewPager2 viewPager;
    private CircleIndicator3 indicator3;
    private PhotoAdapter adapter;
    private List<Photo> list;
    private Timer timer;
    private KMAdapter recyAdapter;
    private XTAdapter xtAdapter;
    private RecyclerView recy1,recy2;
    private CreateDatabase database;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        anhxa();
        recy1();
        recy2();
        list = setData();
        adapter = new PhotoAdapter(list);
        viewPager.setAdapter(adapter);
        indicator3.setViewPager(viewPager);
        autotime();
        return view;
    }

    private void recy2() {
        xtAdapter = new XTAdapter(new ItemClickView() {
            @Override
            public void onclick(int posi) {
                Intent intent = new Intent(getContext(), MuaHang.class);
                intent.putExtra("vitri2",posi);
                intent.putExtra("so",2);
                startActivity(intent);
            }
        });
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recy2.setLayoutManager(manager);
        database = new CreateDatabase(getContext());
        recy2.setAdapter(xtAdapter);
    }



    private void recy1() {
        recyAdapter = new KMAdapter(new ItemClickView() {
            @Override
            public void onclick(int posi) {
                Intent intent = new Intent(getContext(), MuaHang.class);
                intent.putExtra("vitri",posi);
                intent.putExtra("so",1);
                startActivity(intent);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,
                false);
        recy1.setLayoutManager(manager);

        database = new CreateDatabase(getContext());
        recy1.setAdapter(recyAdapter);


    }
    private void anhxa() {
        viewPager = view.findViewById(R.id.viewpager);
        indicator3 = view.findViewById(R.id.circle);
        recy1 = view.findViewById(R.id.recy1);
        recy2 = view.findViewById(R.id.recy2);
    }
    private List<Photo> setData() {
        List<Photo> list1 = new ArrayList<>();
        list1.add(new Photo(R.drawable.solde_nt4));
        list1.add(new Photo(R.drawable.slide_nt2));
        list1.add(new Photo(R.drawable.slide_nt5));
        list1.add(new Photo(R.drawable.slident1));
        list1.add(new Photo(R.drawable.slide_nt3));
        return list1;
    }
    private void autotime() {
        if (list == null || list.isEmpty() || viewPager == null) {
            return;
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int current = viewPager.getCurrentItem();
                        int totalcurent = list.size() - 1;
                        if (current < totalcurent) {
                            current++;
                            viewPager.setCurrentItem(current);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 3000, 5000);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }


    }
    @Override
    public void onclick(int posi) {

    }
    @Override
    public void onResume() {
        super.onResume();
        List<ItemKM> list = database.getallkm();
        recyAdapter.setData(list);
        List<ItemXemThem> list2 = database.getallxt();
        xtAdapter.setData(list2);
    }
}