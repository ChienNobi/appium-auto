package com.example.myapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.example.myapplication.adapter.ThongKeChuyenXeAdapter;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;

import java.util.List;

public class ThongKeChuyenXeViewModel extends BaseObservable {
    private ThongKeChuyenXeAdapter thongKeChuyenXeAdapter;

    public void renderAdapter(Context context){
        thongKeChuyenXeAdapter = new ThongKeChuyenXeAdapter(context);
        ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(context).getChuyenXeDAO();
        List<ChuyenXe> listChuyenXe = chuyenXeDAO.getAll();
        thongKeChuyenXeAdapter.setData(listChuyenXe);
        setThongKeChuyenXeAdapter(thongKeChuyenXeAdapter);
    }

    public ThongKeChuyenXeAdapter getThongKeChuyenXeAdapter() {
        return thongKeChuyenXeAdapter;
    }

    public void setThongKeChuyenXeAdapter(ThongKeChuyenXeAdapter thongKeChuyenXeAdapter) {
        this.thongKeChuyenXeAdapter = thongKeChuyenXeAdapter;
    }
}
