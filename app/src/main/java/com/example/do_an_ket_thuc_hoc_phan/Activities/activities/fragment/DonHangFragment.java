package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.DonHangAdapter;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemDonHang;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemKM;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter.ItemSanPham;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Database.CreateDatabase;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.InterfaceCLick.ItemClickView;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.MuaHang;
import com.example.do_an_ket_thuc_hoc_phan.R;

import java.util.List;


public class DonHangFragment extends Fragment implements ItemClickView {

    private RecyclerView recy1;
    private  LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    DonHangAdapter donHangAdapter;
    private CreateDatabase createDatabase;
    SearchView searchView;
    ImageButton imgbuttom;
    List<ItemSanPham> mlist;
    String s;
    private int current = ItemSanPham.TYPEList;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_don_hang, container, false);
        anhxa();
        Recy1();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.isEmpty()){// Xử lý sự kiện khi nội dung của SearchView thay đổi
                    // Nếu không rỗng, truy vấn cơ sở dữ liệu để lấy danh sách sản phẩm theo tên
                    List<ItemSanPham> mlistt = createDatabase.getallsptheoten(s);
                    donHangAdapter.setData(mlistt);// Cập nhật dữ liệu của Adapter với danh sách sản phẩm mới
                }
                return true;
            }
        });
        return view;
    }
    private void Recy1() {
        createDatabase = new CreateDatabase(getContext());
        donHangAdapter = new DonHangAdapter(getContext(),new ItemClickView() {
            @Override
            public void onclick(int posi) {
                // Xử lý khi một item trong RecyclerView được click
                Intent intent = new Intent(getContext(), MuaHang.class);
                intent.putExtra("vitri3",posi);// Gửi thông tin vị trí được click đến activity MuaHang
                intent.putExtra("so",3);
                startActivity(intent);// Khởi chạy
            }
        });
        // Tạo và thiết lập
        linearLayoutManager = new LinearLayoutManager(getContext());
        recy1.setLayoutManager(linearLayoutManager);// Đặt
        recy1.setAdapter(donHangAdapter);
        recy1.setFocusable(false);
    }

    private void anhxa() {
        recy1 = view.findViewById(R.id.donhang_recy);
        searchView = view.findViewById(R.id.donhang_search);
        imgbuttom = view.findViewById(R.id.donhang_butomimg);
    }
    @Override
    public void onResume() {
        super.onResume();
        mlist = createDatabase.getallsp();
        donHangAdapter.setData(mlist);
    }


    @Override
    public void onclick(int posi) {
    }
}