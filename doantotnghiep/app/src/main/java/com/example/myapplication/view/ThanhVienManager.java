package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityThanhVienManagerBinding;
import com.example.myapplication.viewmodel.ThanhVienManagerViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class ThanhVienManager extends AppCompatActivity {
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
        ThanhVienManagerViewModel thanhVienManagerViewModel = new ThanhVienManagerViewModel();
        ActivityThanhVienManagerBinding activityThanhVienManagerBinding = DataBindingUtil.setContentView(this, R.layout.activity_thanh_vien_manager);
        activityThanhVienManagerBinding.setThanhVienManagerViewModel(thanhVienManagerViewModel);

        if (!isRootFragmentAdded) {
            // Nếu fragment gốc chưa được thêm vào, thêm nó vào container
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(activityThanhVienManagerBinding.containerThanhVienManager.getId(), ListThanhVienFragment.class, null);

            fragmentTransaction.commit();

            // Đánh dấu rằng fragment gốc đã được thêm vào
            isRootFragmentAdded = true;
        }

        activityThanhVienManagerBinding.menuThanhVienManager.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_show){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityThanhVienManagerBinding.containerThanhVienManager.getId(), ListThanhVienFragment.class, null);
                    fragmentTransaction.commit();
                    return true;
                }
                else if(item.getItemId() == R.id.action_add){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(activityThanhVienManagerBinding.containerThanhVienManager.getId(), AddThanhVienFragment.class, null);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });
    }


}