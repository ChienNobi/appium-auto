package com.example.myapplication.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentThongKeNguoiDungBinding;
import com.example.myapplication.viewmodel.ThongKeNguoiDungViewModel;


public class ThongKeNguoiDungFragment extends Fragment {

    FragmentThongKeNguoiDungBinding fragmentThongKeNguoiDungBinding ;
    ThongKeNguoiDungViewModel thongKeNguoiDungViewModel = new ThongKeNguoiDungViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentThongKeNguoiDungBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_thong_ke_nguoi_dung, container, false );
        fragmentThongKeNguoiDungBinding.setThongKeNguoiDungViewModel(thongKeNguoiDungViewModel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragmentThongKeNguoiDungBinding.rcvThongKeNguoiDung.setLayoutManager(linearLayoutManager);
        thongKeNguoiDungViewModel.renderAdapter(getContext());
        fragmentThongKeNguoiDungBinding.rcvThongKeNguoiDung.setAdapter(thongKeNguoiDungViewModel.getThongKeNguoiDungAdapter());
        // Inflate the layout for this fragment
        return fragmentThongKeNguoiDungBinding.getRoot();
    }
}