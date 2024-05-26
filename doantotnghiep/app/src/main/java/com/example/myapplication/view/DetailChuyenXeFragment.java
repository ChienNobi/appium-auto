package com.example.myapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetailChuyenXeBinding;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.viewmodel.DetailChuyenXeViewModel;

public class DetailChuyenXeFragment extends Fragment {

    FragmentDetailChuyenXeBinding fragmentDetailChuyenXeBinding;
    DetailChuyenXeViewModel detailChuyenXeViewModel = new DetailChuyenXeViewModel();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentDetailChuyenXeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_chuyen_xe,container,false);
        fragmentDetailChuyenXeBinding.setDetailChuyenXeViewModel(detailChuyenXeViewModel);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ChuyenXe chuyenXe = (ChuyenXe) bundle.getSerializable("chuyenXe");
            detailChuyenXeViewModel.setDetailChuyenXe(chuyenXe, getContext());
            String imageUrl =  chuyenXe.getHinhAnh();
            Glide.with(this.getContext())
                    .load(imageUrl)
                    .into(fragmentDetailChuyenXeBinding.imgChuyenXe);
        }




        return fragmentDetailChuyenXeBinding.getRoot();
    }


}