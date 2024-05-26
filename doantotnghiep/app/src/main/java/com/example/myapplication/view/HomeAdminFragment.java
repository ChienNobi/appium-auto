package com.example.myapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeAdminBinding;
import com.example.myapplication.viewmodel.HomeAdminViewModel;


public class HomeAdminFragment extends Fragment {


    private FragmentHomeAdminBinding fragmentHomeAdminBinding;
    private HomeAdminViewModel homeAdminViewModel = new HomeAdminViewModel();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeAdminBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_admin, container,false);

        fragmentHomeAdminBinding.setHomeAdminViewModel(homeAdminViewModel);

        fragmentHomeAdminBinding.layoutManagerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeAdminViewModel.goToThanhVienManager(getContext());
            }
        });
        fragmentHomeAdminBinding.layoutManagerChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeAdminViewModel.goToChuyenXeManager(getContext());
            }
        });

        fragmentHomeAdminBinding.layoutManagerLoaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeAdminViewModel.quanLyLoaiXe(getContext());
            }
        });
        fragmentHomeAdminBinding.layoutManagerVeDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeAdminViewModel.goToDatVeManager(getContext());
            }
        });

        return fragmentHomeAdminBinding.getRoot();
    }

}