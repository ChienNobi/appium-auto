package com.example.myapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.example.myapplication.adapter.HistoryBookAdapter;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.DataLocalManager;
import com.example.myapplication.model.DAO.DatVeDAO;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.DatVe;
import com.example.myapplication.model.ThanhVien;

import java.util.List;

public class HistoryBookViewModel extends BaseObservable {
    private HistoryBookAdapter historyBookAdapter;

    public void renderAdapter(Context context){
        historyBookAdapter = new HistoryBookAdapter(context);
        DatVeDAO datVeDAO = AppDatabase.getInstance(context).getVeXeDAO();
        ThanhVienDAO thanhVienDAO= AppDatabase.getInstance(context).getThanhVienDAO();
        ThanhVien thanhVien = thanhVienDAO.getThanhVienByUserName(DataLocalManager.getNameUser());
        List<DatVe> datVes = datVeDAO.getVeXeById(thanhVien.getId());

        historyBookAdapter.setData(datVes);
        setHistoryBookAdapter(historyBookAdapter);
    }

    public HistoryBookAdapter getHistoryBookAdapter() {
        return historyBookAdapter;
    }

    public void setHistoryBookAdapter(HistoryBookAdapter historyBookAdapter) {
        this.historyBookAdapter = historyBookAdapter;
    }
}
