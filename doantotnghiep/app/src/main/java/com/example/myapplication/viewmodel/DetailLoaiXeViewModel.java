package com.example.myapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.DAO.QuyenDao;
import com.example.myapplication.model.LoaiXe;
import com.example.myapplication.model.Quyen;

public class DetailLoaiXeViewModel extends BaseObservable {
    private String id;


    private String tenLoaiXe;
    private String soLuongGhe;
    private String quyen;

    public void setDetailLoaiXe(LoaiXe loaiXe , Context context){
        QuyenDao quyenDao = AppDatabase.getInstance(context).getQuyenDAO();

        this.setId(String.valueOf(loaiXe.getIdLoaiXe()));
        this.setTenLoaiXe(String.valueOf(loaiXe.getTenLoaiXe()));
        this.setSoLuongGhe(String.valueOf(loaiXe.getSoLuongGhe()));
      }
     @Bindable
    public String getId(){
        return id;
     }
    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);

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
    public String getSoLuongGhe() {
        return soLuongGhe;

    }

    public void setSoLuongGhe(String soLuongGhe) {
        this.soLuongGhe = soLuongGhe;
        notifyPropertyChanged(BR.soLuongGhe);
    }
}
