package com.example.myapplication.viewmodel;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

import com.example.myapplication.adapter.ChuyenXeFilterAdapter;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;
import com.example.myapplication.model.DAO.LoaiXeDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class FilterViewModel extends BaseObservable {
    private ChuyenXeFilterAdapter chuyenXeFilterAdapter;
    private String diaDiemDi;
    private String diaDiemDen;
    private String loaiXe;
    private String gioDi;
    public void renderAdapter(Context context){
        chuyenXeFilterAdapter = new ChuyenXeFilterAdapter(context);
        ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(context).getChuyenXeDAO();
        chuyenXeFilterAdapter.setData(chuyenXeDAO.getAll());
        setChuyenXeFilterAdapter(chuyenXeFilterAdapter);
    }



    public ChuyenXeFilterAdapter getChuyenXeFilterAdapter() {
        return chuyenXeFilterAdapter;
    }

    public void setChuyenXeFilterAdapter(ChuyenXeFilterAdapter chuyenXeFilterAdapter) {
        this.chuyenXeFilterAdapter = chuyenXeFilterAdapter;
    }

    public List<String> getListTenLoaiXe(Context context){

        List<String> tenLoaiXe = AppDatabase.getInstance(context).getLoaiXeDAO().getTenLoaiXe();
        return tenLoaiXe;
    }
    public List<String> getListDiaDiemDi(Context context){

        List<String> diaDiemDi = AppDatabase.getInstance(context).getChuyenXeDAO().getListDiaDiemDi();
        Set<String> uniqueDiaDiemDiSet = new HashSet<>(diaDiemDi);
        return new ArrayList<>(uniqueDiaDiemDiSet);
    }
    public List<String> getListDiaDiemDen(Context context){

        List<String> diaDiemDen = AppDatabase.getInstance(context).getChuyenXeDAO().getListDiaDiemDen();
        Set<String> uniqueDiaDiemDenSet = new HashSet<>(diaDiemDen);
        return new ArrayList<>(uniqueDiaDiemDenSet);
    }

    public int getIdLoaiXeByName(Context context,String tenLoaiXe){
        LoaiXeDAO loaiXeDAO = AppDatabase.getInstance(context).getLoaiXeDAO();
        int idLoaiXe =loaiXeDAO.getIDLoaiXe(tenLoaiXe);
        return  idLoaiXe;
    }

    public List<ChuyenXe> listFilterChuyenXe(Context context){
        ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(context).getChuyenXeDAO();
        List<ChuyenXe> filterChuyenXe = chuyenXeDAO.filterChuyenXe(diaDiemDi,diaDiemDen,getIdLoaiXeByName(context,loaiXe),gioDi);
        return filterChuyenXe;
    }

    public void showTimePickerDialog(Context context) {
        // Lấy thời gian hiện tại để hiển thị trong TimePicker
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Xử lý thời gian được chọn bởi người dùng khi thời gian hợp lệ
                        String selectedTime = String.format(Locale.getDefault(), "%dh%02d", hourOfDay, minute);
                        // Hiển thị thời gian đã chọn trong TextView
                        setGioDi(selectedTime);
                    }
                },
                hour,
                minute,
                true
        );

        // Hiển thị TimePickerDialog
        timePickerDialog.show();
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
    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
        notifyPropertyChanged(BR.tenLoaiXe);
    }

    @Bindable
    public String getGioDi() {
        return gioDi;
    }

    public void setGioDi(String gioDi) {
        this.gioDi = gioDi;
        notifyPropertyChanged(BR.gioDi);
    }
}
