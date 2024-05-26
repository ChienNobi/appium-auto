package com.example.myapplication.viewmodel;



import android.content.Context;

import androidx.databinding.BaseObservable;

import com.example.myapplication.adapter.ThanhVienAdapter;
import com.example.myapplication.config.AppDatabase;


public class ListThanhVienViewModel extends BaseObservable {
    private ThanhVienAdapter thanhVienAdapter;
    public void renderAdapter(Context context){
        thanhVienAdapter = new ThanhVienAdapter(context);
        thanhVienAdapter.setData(AppDatabase.getInstance(context).getThanhVienDAO().getAll());
        this.setThanhVienAdapter(thanhVienAdapter);
    }

    public ThanhVienAdapter getThanhVienAdapter() {
        return thanhVienAdapter;
    }

    public void setThanhVienAdapter(ThanhVienAdapter thanhVienAdapter) {
        this.thanhVienAdapter = thanhVienAdapter;
    }
}
