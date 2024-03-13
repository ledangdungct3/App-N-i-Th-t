package com.example.do_an_ket_thuc_hoc_phan.Activities.activities.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment.DonHangFragment;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment.HomeFragment;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment.ThemFragment;
import com.example.do_an_ket_thuc_hoc_phan.Activities.activities.fragment.UuDaiFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new HomeFragment();
            case 1:return new DonHangFragment();
            case 2: return new UuDaiFragment();
            case 3: return new ThemFragment();
            default: return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
