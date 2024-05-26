package com.example.myapplication.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentThongKeChuyenXeBinding;
import com.example.myapplication.viewmodel.ThongKeChuyenXeViewModel;

public class ThongKeChuyenXeFragment extends Fragment {


    FragmentThongKeChuyenXeBinding fragmentThongKeChuyenXeBinding;
    ThongKeChuyenXeViewModel thongKeChuyenXeViewModel = new ThongKeChuyenXeViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentThongKeChuyenXeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_thong_ke_chuyen_xe,container,false);
        fragmentThongKeChuyenXeBinding.setThongKeChuyenXeViewModel(thongKeChuyenXeViewModel);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragmentThongKeChuyenXeBinding.rcvThongKeChuyenXe.setLayoutManager(linearLayoutManager);
        thongKeChuyenXeViewModel.renderAdapter(getContext());
        fragmentThongKeChuyenXeBinding.rcvThongKeChuyenXe.setAdapter(thongKeChuyenXeViewModel.getThongKeChuyenXeAdapter());

        // Inflate the layout for this fragment
        return fragmentThongKeChuyenXeBinding.getRoot();
    }
}