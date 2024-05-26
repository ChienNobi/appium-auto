package com.example.myapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.myapplication.BR;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.LoaiXeDAO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class DetailChuyenXeViewModel extends BaseObservable {
    private String idChuyenXe;
    private String tenChuyenXe;
    private String diaDiemDi;
    private String diaDiemDen;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;
    private String tenLoaiXe;
    private String giaTien;
    private String moTa;
    private String hinhAnh;

    public void setDetailChuyenXe(ChuyenXe chuyenXe, Context context){

        LoaiXeDAO loaiXeDAO = AppDatabase.getInstance(context).getLoaiXeDAO();

        String tenLoaiXe = loaiXeDAO.getTenLoaiXeByID(chuyenXe.getIdLoaiXe());

        this.setIdChuyenXe(String.valueOf(chuyenXe.getIdChuyenXe()));
        this.setTenChuyenXe(chuyenXe.getTenChuyen());
        this.setDiaDiemDi(chuyenXe.getDiaDiemDi());
        this.setDiaDiemDen(chuyenXe.getDiaDiemDen());
        this.setThoiGianBatDau(chuyenXe.getThoiGianBatDau());
        this.setThoiGianKetThuc(chuyenXe.getThoiGianKetThuc());
        this.setTenLoaiXe(tenLoaiXe);
        if(chuyenXe.getGiaTien()==null){
            this.setGiaTien("Chưa cập nhật");
        }else {


            this.setGiaTien(FunctionPublic.formatMoney(chuyenXe.getGiaTien()));
        }
        this.setMoTa(chuyenXe.getMoTa());
        this.setHinhAnh(getHinhAnh());
    }

    @Bindable
    public String getIdChuyenXe() {
        return idChuyenXe;
    }

    public void setIdChuyenXe(String idChuyenXe) {
        this.idChuyenXe = idChuyenXe;
        notifyPropertyChanged(BR.idChuyenXe);
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
    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
        notifyPropertyChanged(BR.thoiGianBatDau);
    }

    @Bindable
    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
        notifyPropertyChanged(BR.thoiGianKetThuc);
    }


    @Bindable
    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
        notifyPropertyChanged(BR.tenLoaiXe);
    }
    @Bindable
    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
        notifyPropertyChanged(BR.giaTien);
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
}