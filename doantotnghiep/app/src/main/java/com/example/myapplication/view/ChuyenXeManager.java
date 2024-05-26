package com.example.myapplication.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityChuyenXeManagerBinding;
import com.example.myapplication.viewmodel.ChuyenXeManagerViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class ChuyenXeManager extends AppCompatActivity {
    private boolean isRootFragmentAdded = false;
    @Override
    public void onBackPressed() {
        // Xử lý sự kiện khi người dùng nhấn nút Back trên thiết bị
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChuyenXeManagerViewModel chuyenXeManagerViewModel = new ChuyenXeManagerViewModel();
        ActivityChuyenXeManagerBinding activityChuyenXeManagerBinding = DataBindingUtil.setContentView(this, R.layout.activity_chuyen_xe_manager);
        activityChuyenXeManagerBinding.setChuyenXeManagerViewModel(chuyenXeManagerViewModel);

        if (!isRootFragmentAdded) {
            // Nếu fragment gốc chưa được thêm vào, thêm nó vào container
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(activityChuyenXeManagerBinding.containerChuyenXeManager.getId(), ListChuyenXeFragment.class, null);

            fragmentTransaction.commit();

            // Đánh dấu rằng fragment gốc đã được thêm vào
            isRootFragmentAdded = true;
        }

        activityChuyenXeManagerBinding.menuChuyenXeManager.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_show_chuyen_xe){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityChuyenXeManagerBinding.containerChuyenXeManager.getId(), ListChuyenXeFragment.class, null);
                    fragmentTransaction.commit();
                    return true;
                }
                else if(item.getItemId() == R.id.action_add_chuyen_xe){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityChuyenXeManagerBinding.containerChuyenXeManager.getId(), AddChuyenXeFragment.class, null);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });
    }

}
