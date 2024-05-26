package com.example.myapplication.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.myapplication.BR;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.ThanhVien;

public class ResetPasswordViewModel extends BaseObservable {
    private String email;
    private String matKhau;
    private String resetMatKhau;
    public boolean check = false;

    public void handleResetPassword(Context context) {
        if (kiemTraNhap(email,matKhau,resetMatKhau)==false) {
            Toast.makeText(context,"Vui lòng nhập đầy đủ dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }

        ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
        ThanhVien thanhVien = thanhVienDAO.getThanhVienByEmail(email);
        if (thanhVien == null){
            Toast.makeText(context, "Không tìm thấy thông tin người dùng", Toast.LENGTH_SHORT).show();
            check = false;
            return ;
        }
        if(matKhau.equals(resetMatKhau)) {
            thanhVien.setMatKhau(matKhau);
            if (thanhVienDAO.updateThanhVien(thanhVien) == 1){
                Toast.makeText(context, "Đặt lại mật khẩu thành công", Toast.LENGTH_SHORT).show();
                check = true;
            }
            else {
                Toast.makeText(context, "Đặt lại mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                check = false;
                return ;
            }
        }else {
            Toast.makeText(context, "Kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
            check = false;
            return ;
        }
    }


    //Phương thức ẩn hiện password
    public boolean showPass(EditText editText) {
        if (editText.getTransformationMethod() instanceof PasswordTransformationMethod) {
            // Nếu đang ẩn mật khẩu, hiển thị văn bản
            editText.setTransformationMethod(null);
            editText.setSelection(editText.getText().length());//Di chuyển con trỏ về cuối dòng
            return true;
        } else {
            // Nếu đang hiển thị văn bản, ẩn mật khẩu
            editText.setTransformationMethod(new PasswordTransformationMethod());
            editText.setSelection(editText.getText().length());//Di chuyển con trỏ về cuối dòng
            return false;
        }

    }
    //Kiểm tra nhập
    public boolean kiemTraNhap(String tenDangNhap, String password, String resetPassword) {
        //Tên đăng nhập và mật khẩu trống
        if (TextUtils.isEmpty(tenDangNhap) || TextUtils.isEmpty(password) || TextUtils.isEmpty(resetPassword)) {
            return false;
        }
        return true;
    }
    @Bindable
    public boolean isCheck() {
        return check;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
        notifyPropertyChanged(BR.matKhau);
    }

    @Bindable
    public String getResetMatKhau() {
        return resetMatKhau;
    }

    public void setResetMatKhau(String resetMatKhau) {
        this.resetMatKhau = resetMatKhau;
        notifyPropertyChanged(BR.resetMatKhau);
    }
}
