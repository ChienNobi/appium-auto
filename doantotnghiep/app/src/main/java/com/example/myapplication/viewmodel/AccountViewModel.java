package com.example.myapplication.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.myapplication.BR;

import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.VariableGlobal;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.ThanhVien;

import java.util.Date;

public class AccountViewModel extends BaseObservable {
    private String ho;
    private String ten;
    private String tenDangNhap;
    private String soDienThoai;
    private String email;
    private String ngayTao;
    private String ngayCapNhat;
    private String avatar;

    public void showAccount(ThanhVien thanhVien, Context context){

        setHo(thanhVien.getHo());
        setTen(thanhVien.getTen());
        setTenDangNhap(thanhVien.getTenDangNhap());
        setSoDienThoai(thanhVien.getSoDienThoai());
        setEmail(thanhVien.getEmail());
        setNgayTao(thanhVien.getNgayTao());
        setNgayCapNhat(thanhVien.getNgayCapNhat());
        setAvatar(thanhVien.getAvatar());

    }

    public void updateAccount(Context context){
        ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
        ThanhVien thanhVien = thanhVienDAO.getThanhVienByUserName(tenDangNhap);
        Date dataNow = new Date();
        thanhVien.setNgayCapNhat(VariableGlobal.dateFormat.format(dataNow));

        thanhVien.setHo(ho);
        thanhVien.setTen(ten);
        thanhVien.setSoDienThoai(soDienThoai);
        thanhVien.setEmail(email);
        thanhVien.setAvatar(avatar);

        try {
            thanhVienDAO.update(thanhVien);
            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }



    }

    




    @Bindable
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
        notifyPropertyChanged(BR.tenDangNhap);
    }
    @Bindable
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
        notifyPropertyChanged(BR.soDienThoai);
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
    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
        notifyPropertyChanged(BR.ngayTao);
    }
    @Bindable
    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
        notifyPropertyChanged(BR.ngayCapNhat);
    }
    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
        notifyPropertyChanged(BR.ho);
    }

    @Bindable
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
        notifyPropertyChanged(BR.ten);
    }
}
