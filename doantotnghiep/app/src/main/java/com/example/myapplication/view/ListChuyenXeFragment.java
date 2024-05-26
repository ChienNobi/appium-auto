package com.example.myapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentListChuyenXeBinding;
import com.example.myapplication.viewmodel.ListChuyenXeViewModel;

public class ListChuyenXeFragment extends Fragment {
    FragmentListChuyenXeBinding fragmentListChuyenXeBinding;
    ListChuyenXeViewModel listChuyenXeViewModel = new ListChuyenXeViewModel();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentListChuyenXeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_chuyen_xe,container,false);
        fragmentListChuyenXeBinding.setListChuyenXeViewModel(listChuyenXeViewModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());


        fragmentListChuyenXeBinding.rcvChuyenXe.setLayoutManager(linearLayoutManager);
        listChuyenXeViewModel.renderAdapter(getContext());
        fragmentListChuyenXeBinding.rcvChuyenXe.setAdapter(listChuyenXeViewModel.getChuyenXeAdapter());


        fragmentListChuyenXeBinding.searchChuyenXe.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listChuyenXeViewModel.getChuyenXeAdapter().getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listChuyenXeViewModel.getChuyenXeAdapter().getFilter().filter(newText);
                return false;
            }
        });

        return fragmentListChuyenXeBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listChuyenXeViewModel.getChuyenXeAdapter().release();
    }

}