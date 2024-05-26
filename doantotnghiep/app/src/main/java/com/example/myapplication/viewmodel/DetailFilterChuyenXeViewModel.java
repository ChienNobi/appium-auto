package com.example.myapplication.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.myapplication.BR;
import com.example.myapplication.model.ChuyenXe;

public class DetailFilterChuyenXeViewModel extends BaseObservable {
    private String tenChuyenXe;
    private String diaDiemDi;
    private String diaDiemDen;
    private String ngayDi;
    private String gioDi;
    private String moTa;
    private String hinhAnh;
    private String tenLoaiXe;

    public void getThongTinChuyenXe(ChuyenXe chuyenXe){
        setTenChuyenXe(getTenChuyenXe());
        setDiaDiemDi(getDiaDiemDi());
        setDiaDiemDen(getDiaDiemDen());
        setNgayDi(getNgayDi());
        setGioDi(getGioDi());
        setMoTa(getMoTa());
        setHinhAnh(getHinhAnh());
//        setTenLoaiXe();
    }

    @Bindable
    public String getTenChuyenXe() {
        return tenChuyenXe;
    }

    public void setTenChuyenXe(String tenChuyenXe) {
        this.tenChuyenXe = tenChuyenXe;
        notifyPropertyChanged(BR.tenChuyenXe);
    }
    @Bindable
    public String getDiaDiemDi() {
        return diaDiemDi;
    }

    public void setDiaDiemDi(String diaDiemDi) {
        this.diaDiemDi = diaDiemDi;
        notifyPropertyChanged(BR.diaDiemDi);
    }
    @Bindable
    public String getDiaDiemDen() {
        return diaDiemDen;
    }

    public void setDiaDiemDen(String diaDiemDen) {
        this.diaDiemDen = diaDiemDen;
        notifyPropertyChanged(BR.diaDiemDen);
    }
    @Bindable
    public String getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(String ngayDi) {
        this.ngayDi = ngayDi;
        notifyPropertyChanged(BR.ngayDi);
    }
    @Bindable
    public String getGioDi() {
        return gioDi;
    }

    public void setGioDi(String gioDi) {
        this.gioDi = gioDi;
        notifyPropertyChanged(BR.gioDi);
    }
    @Bindable
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
        notifyPropertyChanged(BR.moTa);
    }
    @Bindable
    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
        notifyPropertyChanged(BR.hinhAnh);
    }

    @Bindable
    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
        notifyPropertyChanged(BR.tenLoaiXe);
    }
}
