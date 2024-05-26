package com.example.myapplication.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityAdminManagerBinding;
import com.example.myapplication.viewmodel.AdminManagerViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class AdminManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAdminManagerBinding activityAdminManagerBinding = DataBindingUtil.setContentView(this,R.layout.activity_admin_manager);
        AdminManagerViewModel adminManagerViewModel = new AdminManagerViewModel();
        activityAdminManagerBinding.setAdminManagerViewModel(adminManagerViewModel);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(activityAdminManagerBinding.fragmentAdmin.getId(),new HomeAdminFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



        activityAdminManagerBinding.tabAdmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityAdminManagerBinding.fragmentAdmin.getId(),new HomeAdminFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                } else if (itemId == R.id.action_account) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityAdminManagerBinding.fragmentAdmin.getId(),new AccountFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                }
                return true;
            }
        });
    }
}