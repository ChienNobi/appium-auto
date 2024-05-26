package com.example.myapplication.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.myapplication.BR;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.DAO.LoaiXeDAO;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.LoaiXe;

public class AddLoaiXeViewModel extends BaseObservable {
    private String tenLoaiXe;
    private String soLuongGhe;


    public void handleAddLoaiXe(Context context){
        if (TextUtils.isEmpty(tenLoaiXe) || TextUtils.isEmpty(soLuongGhe)){
            Toast.makeText(context,"Không được để trống số lượng xe",Toast.LENGTH_LONG).show();
            return;
        }
        else {
            LoaiXe loaiXe = new LoaiXe();
            loaiXe.setTenLoaiXe(getTenLoaiXe());
            loaiXe.setSoLuongGhe(Integer.parseInt(getSoLuongGhe()));
            LoaiXeDAO  loaiXeDAO = AppDatabase.getInstance(context).getLoaiXeDAO();


            if (loaiXeDAO.isLoaiXeExist(loaiXe.getTenLoaiXe())){
                Toast.makeText(context, "Loại xe này đã tồn tại", Toast.LENGTH_LONG).show();
                return;
            }

            loaiXeDAO.insert(loaiXe);
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG).show();

        }

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
