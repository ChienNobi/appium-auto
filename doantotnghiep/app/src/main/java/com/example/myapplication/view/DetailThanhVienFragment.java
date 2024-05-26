package com.example.myapplication.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.config.VariableGlobal;
import com.example.myapplication.databinding.FragmentDetailThanhVienBinding;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.viewmodel.DetailThanhVienViewModel;


public class DetailThanhVienFragment extends Fragment {

    FragmentDetailThanhVienBinding fragmentDetailThanhVienBinding;
    DetailThanhVienViewModel detailThanhVienViewModel = new DetailThanhVienViewModel();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDetailThanhVienBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_thanh_vien, container, false);
        fragmentDetailThanhVienBinding.setDetailThanhVienViewModel(detailThanhVienViewModel);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ThanhVien thanhVien = (ThanhVien) bundle.getSerializable("thanhVien");
            detailThanhVienViewModel.setDetailThanhVien(thanhVien, getContext());
        }

        FunctionPublic.loadImage(detailThanhVienViewModel.getAvatar(),fragmentDetailThanhVienBinding.avatarImg,getContext());


        // Inflate the layout for this fragment
        return fragmentDetailThanhVienBinding.getRoot();
    }
}