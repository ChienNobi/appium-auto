package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDatVeManagerBinding;
import com.example.myapplication.viewmodel.DatVeManagerViewModel;
import com.example.myapplication.viewmodel.DatVeViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class DatVeManagerActivity extends AppCompatActivity {
    ActivityDatVeManagerBinding activityDatVeManagerBinding;
    DatVeManagerViewModel datVeManagerViewModel = new DatVeManagerViewModel() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDatVeManagerBinding = DataBindingUtil.setContentView(DatVeManagerActivity.this, R.layout.activity_dat_ve_manager);
        activityDatVeManagerBinding.setDatVeManagerViewModel(datVeManagerViewModel);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(activityDatVeManagerBinding.containerDatVe.getId(), new ThongKeNguoiDungFragment());
        fragmentTransaction.commit();

        activityDatVeManagerBinding.tabAdmin.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_chuyen_xe){
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(activityDatVeManagerBinding.containerDatVe.getId(), new ThongKeChuyenXeFragment());
                fragmentTransaction1.commit();
                return true;
            }
            else if (itemId == R.id.action_user){
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(activityDatVeManagerBinding.containerDatVe.getId(), new ThongKeNguoiDungFragment());
                fragmentTransaction1.commit();
                return true;
            }
            return false;
        });
    }
}