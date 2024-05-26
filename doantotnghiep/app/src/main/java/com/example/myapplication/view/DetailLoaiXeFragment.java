package com.example.myapplication.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.databinding.FragmentDetailLoaiXeBinding;
import com.example.myapplication.model.LoaiXe;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.viewmodel.DetailLoaiXeViewModel;


public class DetailLoaiXeFragment extends Fragment {
    FragmentDetailLoaiXeBinding fragmentDetailLoaiXeBinding;
    DetailLoaiXeViewModel detailLoaiXeViewModel = new DetailLoaiXeViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDetailLoaiXeBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_detail_loai_xe,container,false);
        fragmentDetailLoaiXeBinding.setDetailLoaiXeViewModel(detailLoaiXeViewModel);

        Bundle bundle = getArguments();
        if (bundle != null) {
            LoaiXe loaiXe = (LoaiXe) bundle.getSerializable("loaiXe");
            detailLoaiXeViewModel.setDetailLoaiXe(loaiXe, getContext());
        }
        return fragmentDetailLoaiXeBinding.getRoot();

     }
}