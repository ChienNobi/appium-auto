package com.example.myapplication.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityResetPasswordBinding;
import com.example.myapplication.viewmodel.ResetPasswordViewModel;

public class ResetPasswordActivity extends AppCompatActivity {
    ActivityResetPasswordBinding activityResetPasswordBinding;
    ResetPasswordViewModel resetPasswordViewModel = new ResetPasswordViewModel();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        activityResetPasswordBinding = DataBindingUtil.setContentView(ResetPasswordActivity.this,R.layout.activity_reset_password);
        activityResetPasswordBinding.setResetPasswordViewModel(resetPasswordViewModel);


        // Xử lí button đặt lại
        activityResetPasswordBinding.btnResetPassword.setOnClickListener(v -> {
            String email = resetPasswordViewModel.getEmail();
            String matKhau = resetPasswordViewModel.getMatKhau();
            String resetMatKhau = resetPasswordViewModel.getResetMatKhau();

            resetPasswordViewModel.handleResetPassword(getApplicationContext());
            if(resetPasswordViewModel.isCheck()==true) {
                showDialogSignIn();
            }
        });



        // Set filter
        activityResetPasswordBinding.edtResetPassword.setFilters(new InputFilter[]{filter});
        activityResetPasswordBinding.edtResetAgainPassword.setFilters(new InputFilter[]{filter});

        //Xóa trống email
        activityResetPasswordBinding.imgClearEmail.setOnClickListener(v ->
                activityResetPasswordBinding.edtEmail.setText(""));

        //Ẩn hiên mật khẩu
        activityResetPasswordBinding.imgShowResetPassword.setOnClickListener(v ->
                resetPasswordViewModel.showPass(activityResetPasswordBinding.edtResetPassword));

        // Ẩn hiện nhập lại mật khẩu
        activityResetPasswordBinding.imgShowAgainPassword.setOnClickListener(v ->
                resetPasswordViewModel.showPass(activityResetPasswordBinding.edtResetAgainPassword));
    }

    // Hộp thoại xác nhận đăng nhập
    private void showDialogSignIn(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ResetPasswordActivity.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn cần đăng nhập lại");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Chuyển hướng đến trang đăng ký tài khoản
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }).setNegativeButton("Hủy", null);
        builder.show();
    }

        //Filter định dạng nội dung nhập
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {
            for (int i = start; i < end; i++) {
                char c = source.charAt(i);
                if (Character.isWhitespace(c) || c == ',' || c == '.') {
                    return "";
                }
            }
            return null;
    };
}