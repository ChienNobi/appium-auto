package com.example.myapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.myapplication.BR;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.DAO.QuyenDao;
import com.example.myapplication.model.ThanhVien;

public class DetailThanhVienViewModel extends BaseObservable {
    private String id;
    private String tenDangNhap;
    private String ho;
    private String ten;
    private String matKhau;
    private String quyen;
    private String soDienThoai;
    private String email;
    private String ngayTao;
    private String ngayCapNhat;
    private String ngaySinh;
    private String avatar;

    public void setDetailThanhVien(ThanhVien thanhVien, Context context){
        QuyenDao quyenDao = AppDatabase.getInstance(context).getQuyenDAO();

        String tenQuyen = quyenDao.chuyenDoiQuyenThanhVien(thanhVien.getIdQuyenThanhVien());

        this.setId(String.valueOf(thanhVien.getId()));
        this.setTenDangNhap(thanhVien.getTenDangNhap());
        this.setHo(thanhVien.getHo());
        this.setTen(thanhVien.getTen());
        this.setMatKhau(thanhVien.getMatKhau());
        this.setQuyen(tenQuyen);
        this.setSoDienThoai(thanhVien.getSoDienThoai());
        this.setEmail(thanhVien.getEmail());
        this.setNgayTao(thanhVien.getNgayTao());
        this.setNgayCapNhat(thanhVien.getNgayCapNhat());
        this.setNgaySinh(thanhVien.getNgaySinh());
        this.setAvatar(thanhVien.getAvatar());
    }

    @Bindable
    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
        notifyPropertyChanged(BR.quyen);
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
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
    @Bindable
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
        notifyPropertyChanged(BR.matKhau);
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
    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
        notifyPropertyChanged(BR.ngaySinh);
    }
    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }
}
