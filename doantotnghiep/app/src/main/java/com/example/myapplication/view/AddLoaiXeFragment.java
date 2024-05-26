package com.example.myapplication.view;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddLoaiXeBinding;
import com.example.myapplication.viewmodel.AddLoaiXeViewModel;


public class AddLoaiXeFragment extends Fragment {
    FragmentAddLoaiXeBinding fragmentAddLoaiXeBinding;
    AddLoaiXeViewModel addLoaiXeViewModel = new AddLoaiXeViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAddLoaiXeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_loai_xe,container,false);
        fragmentAddLoaiXeBinding.setAddLoaiXeViewModel(addLoaiXeViewModel);

        fragmentAddLoaiXeBinding.btnThemLoaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLoaiXeViewModel.handleAddLoaiXe(getContext());
            }
        });
        // Inflate the layout for this fragment
        return fragmentAddLoaiXeBinding.getRoot();
    }
}