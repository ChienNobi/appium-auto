package com.example.myapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ChuyenXeUserAdapter;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.DataLocalManager;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class HomeUserViewModel extends BaseObservable {


    private String hoTen;
    private String avatar;
    private ChuyenXeUserAdapter chuyenXeUserAdapter;
    public void renderAdapter(Context context){
        chuyenXeUserAdapter = new ChuyenXeUserAdapter(context);
        ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(context).getChuyenXeDAO();
        chuyenXeUserAdapter.setData(chuyenXeDAO.getAll());
        setChuyenXeUserAdapter(chuyenXeUserAdapter);
    }

    public void getWelcome(Context context){
        ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
        ThanhVien thanhVien = thanhVienDAO.getThanhVienByUserName(DataLocalManager.getNameUser());
        setHoTen(thanhVien.getHoTen());
        setAvatar(thanhVien.getAvatar());
    }

    public List<SlideModel> listImage(){
        List<SlideModel> list = new ArrayList<SlideModel>();
        list.add(new SlideModel(R.drawable.slide2, ScaleTypes.CENTER_CROP));
        list.add(new SlideModel(R.drawable.slide, ScaleTypes.CENTER_CROP));
        list.add(new SlideModel(R.drawable.slide1, ScaleTypes.CENTER_CROP));
        return list;
    }

    public ChuyenXeUserAdapter getChuyenXeUserAdapter() {
        return chuyenXeUserAdapter;
    }

    public void setChuyenXeUserAdapter(ChuyenXeUserAdapter chuyenXeUserAdapter) {
        this.chuyenXeUserAdapter = chuyenXeUserAdapter;
    }



    @Bindable
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
        notifyPropertyChanged(BR.hoTen);
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
