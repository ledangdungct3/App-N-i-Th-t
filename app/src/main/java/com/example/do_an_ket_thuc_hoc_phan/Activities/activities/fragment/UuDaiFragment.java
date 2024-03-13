package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemUuDai;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemUuDai_2;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.UuDaiAdapter;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.UuDaiAdapter2;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.ArrayList;
import java.util.List;


public class UuDaiFragment extends Fragment {
    private RecyclerView recyclerView,recyclerView2;
    private UuDaiAdapter uuDaiAdapter;
    private UuDaiAdapter2 uuDaiAdapter2;
    private CreateDatabase db;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_uu_dai, container, false);
        anhxa();
        recy1();
        recy2();
        return view;
    }
    private void recy2() {
       LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(manager);
        uuDaiAdapter2 = new UuDaiAdapter2();
        recyclerView2.setAdapter(uuDaiAdapter2);
        uuDaiAdapter2.setData(setData2());
    }
    private List<ItemUuDai_2> setData2() {
        List<ItemUuDai_2> list2 = new ArrayList<>();
        list2.add(new ItemUuDai_2(R.drawable.km1));
        list2.add(new ItemUuDai_2(R.drawable.km2));
        list2.add(new ItemUuDai_2(R.drawable.km3));
        list2.add(new ItemUuDai_2(R.drawable.km5));
        list2.add(new ItemUuDai_2(R.drawable.km4));

        return list2;
    }
    private void recy1() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        uuDaiAdapter = new UuDaiAdapter();
        recyclerView.setAdapter(uuDaiAdapter);
        db = new CreateDatabase(getContext());
    }
    private void anhxa() {
        recyclerView = view.findViewById(R.id.donhang_recy);
        recyclerView2 = view.findViewById(R.id.donhang_recy2);
    }
    @Override
    public void onResume() {
        super.onResume();
        List<ItemUuDai> list = db.getallud();
        uuDaiAdapter.setData(list);
    }
}