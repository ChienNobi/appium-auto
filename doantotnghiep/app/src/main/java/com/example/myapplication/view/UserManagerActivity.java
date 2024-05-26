package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityUserManagerBinding;
import com.example.myapplication.viewmodel.UserManagerViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class UserManagerActivity extends AppCompatActivity {
    ActivityUserManagerBinding activityUserManagerBinding;
    UserManagerViewModel userManagerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUserManagerBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_manager);
        activityUserManagerBinding.setUserManagerViewModel(userManagerViewModel);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(activityUserManagerBinding.fragmentAdmin.getId(), new HomeUserFragment());
        fragmentTransaction.commit();

        activityUserManagerBinding.containerBtn.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityUserManagerBinding.fragmentAdmin.getId(),new HomeUserFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                } else if (itemId == R.id.action_account) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityUserManagerBinding.fragmentAdmin.getId(),new AccountFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                }else if (itemId == R.id.action_filter) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityUserManagerBinding.fragmentAdmin.getId(),new FilterFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                }
                else if (itemId == R.id.action_history) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityUserManagerBinding.fragmentAdmin.getId(),new HistoryBookFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                }

                return false;
            }
        });
    }

}