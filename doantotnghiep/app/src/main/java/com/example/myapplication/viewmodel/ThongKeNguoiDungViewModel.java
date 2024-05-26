package com.example.myapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.example.myapplication.adapter.ThongKeNguoiDungAdapter;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.ThanhVien;

import java.util.List;

public class ThongKeNguoiDungViewModel extends BaseObservable {
    private ThongKeNguoiDungAdapter thongKeNguoiDungAdapter;

    public void renderAdapter(Context context){
        thongKeNguoiDungAdapter = new ThongKeNguoiDungAdapter(context);
        ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
        List<ThanhVien> list = thanhVienDAO.getThanhVienByIdQuyen(3);
        thongKeNguoiDungAdapter.setData(list);
        setThongKeNguoiDungAdapter(thongKeNguoiDungAdapter);

    }

    public ThongKeNguoiDungAdapter getThongKeNguoiDungAdapter() {
        return thongKeNguoiDungAdapter;
    }

    public void setThongKeNguoiDungAdapter(ThongKeNguoiDungAdapter thongKeNguoiDungAdapter) {
        this.thongKeNguoiDungAdapter = thongKeNguoiDungAdapter;
    }
}
