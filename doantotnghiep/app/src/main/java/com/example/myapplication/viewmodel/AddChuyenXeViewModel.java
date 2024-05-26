package com.example.myapplication.viewmodel;


import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;
import com.example.myapplication.model.DAO.LoaiXeDAO;
import com.example.myapplication.view.ListChuyenXeFragment;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddChuyenXeViewModel extends BaseObservable {
    private String tenChuyenXe;
    private String diaDiemDi;
    private String diaDiemDen;
    private String thoiGianDi;
    private String thoiGianDen;
    private int idLoaiXe;
    private String hinhAnh;
    private String giaTien;
    private String moTa;

    private Calendar selectedGioDi;


    public void showTimePickerDialogDi(Context context) {
        // Lấy thời gian hiện tại để hiển thị trong TimePicker
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo TimePickerDialog
        TimePickerDialog timePickerDialogDi = new TimePickerDialog(
                context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedGioDi = Calendar.getInstance();
                        selectedGioDi.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        selectedGioDi.set(Calendar.MINUTE,minute);
                        // Xử lý thời gian được chọn bởi người dùng khi thời gian hợp lệ
                        String selectedTime = String.format(Locale.getDefault(), "%dh%02d", hourOfDay, minute);                        // Hiển thị thời gian đã chọn trong TextView
                        setThoiGianDi(selectedTime);
                    }
                },
                hour,
                minute,
                true
        );
        // Hiển thị TimePickerDialog
        timePickerDialogDi.show();
    }
    public void showTimePickerDialogDen(Context context) {
        // Lấy thời gian hiện tại để hiển thị trong TimePicker
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo TimePickerDialog
        TimePickerDialog timePickerDialogVe = new TimePickerDialog(
                context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Xử lý thời gian được chọn bởi người dùng khi thời gian hợp lệ

                        if (selectedGioDi == null){
                            Toast.makeText(context, "Vui lòng nhập giờ đi", Toast.LENGTH_SHORT ).show();
                        }
                        else {

                            // Xử lý thời gian được chọn bởi người dùng khi thời gian hợp lệ
                            if(hourOfDay > selectedGioDi.get(Calendar.HOUR_OF_DAY) ||
                                    (hourOfDay == selectedGioDi.get(Calendar.HOUR_OF_DAY) && minute > selectedGioDi.get(Calendar.MINUTE))){
                                String selectedTime = String.format(Locale.getDefault(), "%dh%02d", hourOfDay, minute);                            // Hiển thị thời gian đã chọn trong TextView
                                setThoiGianDen(selectedTime);
                            }else
                                Toast.makeText(context,"Vui lòng nhập lại giờ về",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                hour,
                minute,
                true
        );
        // Hiển thị TimePickerDialog
        timePickerDialogVe.show();
    }

    public List<String> getListTenLoaiXe(Context context){

        List<String> tenLoaiXe = AppDatabase.getInstance(context).getLoaiXeDAO().getTenLoaiXe();
        return tenLoaiXe;
    }

    public int getIdLoaiXeByName(Context context,String tenLoaiXe){
        LoaiXeDAO loaiXeDAO = AppDatabase.getInstance(context).getLoaiXeDAO();
        idLoaiXe =loaiXeDAO.getIDLoaiXe(tenLoaiXe);
        return  idLoaiXe;
    }

    public void AddChuyenXe(Context context){
        if (kiemTraNhap(tenChuyenXe,diaDiemDi,diaDiemDen,thoiGianDi,thoiGianDen)==false) {
            Toast.makeText(context,"Vui lòng nhập đầy đủ dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }
        ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(context).getChuyenXeDAO();
        ChuyenXe chuyenXe = new ChuyenXe();
        chuyenXe.setIdLoaiXe(getIdLoaiXe());
        chuyenXe.setTenChuyen(getTenChuyenXe());
        chuyenXe.setHinhAnh(getHinhAnh());
        chuyenXe.setThoiGianBatDau(getThoiGianDi());
        chuyenXe.setThoiGianKetThuc(getThoiGianDen());
        chuyenXe.setDiaDiemDi(getDiaDiemDi());
        chuyenXe.setDiaDiemDen(getDiaDiemDen());
        chuyenXe.setGiaTien(Double.parseDouble(getGiaTien()));
        chuyenXe.setMoTa(getMoTa());
        chuyenXeDAO.insert(chuyenXe);

        this.setTenChuyenXe("");
        this.setThoiGianDi("");
        this.setThoiGianDen("");
        this.setDiaDiemDi("");
        this.setDiaDiemDen("");
        this.setGiaTien("0");
        this.setMoTa("");


        Toast.makeText(context,"Thêm chuyến xe thành công",Toast.LENGTH_SHORT).show();
        goToListFragment(context);
    }

    public void goToListFragment(Context context){

        ListChuyenXeFragment listChuyenXeFragment = new ListChuyenXeFragment();
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerChuyenXeManager, listChuyenXeFragment);
        fragmentTransaction.commit();
    }


    public boolean kiemTraNhap(String tenChuyenXe, String diaDiemDi, String diaDiemDen,String thoiGianDi,String thoiGianDen) {
        //Tên đăng nhập và mật khẩu trống
        if (TextUtils.isEmpty(tenChuyenXe) || TextUtils.isEmpty(diaDiemDi) || TextUtils.isEmpty(diaDiemDen)|| TextUtils.isEmpty(thoiGianDi)|| TextUtils.isEmpty(thoiGianDen)) {
            return false;
        }
        return true;
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
    public String getThoiGianDi() {
        return thoiGianDi;
    }

    public void setThoiGianDi(String thoiGianDi) {
        this.thoiGianDi = thoiGianDi;
        notifyPropertyChanged(BR.thoiGianDi);
    }

    @Bindable
    public String getThoiGianDen() {
        return thoiGianDen;
    }

    public void setThoiGianDen(String thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
        notifyPropertyChanged(BR.thoiGianDen);
    }

//    @Bindable
//    public String getNgayDi() {
//        return ngayDi;
//    }
//
//    public void setNgayDi(String ngayDi) {
//        this.ngayDi = ngayDi;
//        notifyPropertyChanged(BR.ngayDi);
//    }
//
//    @Bindable
//    public String getNgayVe() {
//        return ngayVe;
//    }
//
//    public void setNgayVe(String ngayVe) {
//        this.ngayVe = ngayVe;
//        notifyPropertyChanged(BR.ngayVe);
//    }

    @Bindable
    public int getIdLoaiXe() {
        return idLoaiXe;
    }

    public void setIdLoaiXe(int idLoaiXe) {
        this.idLoaiXe = idLoaiXe;
        notifyPropertyChanged(BR.idLoaiXe);
    }

    @Bindable
    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

//    @Bindable
//    public Calendar getSelectedCalendarDi() {
//        return selectedCalendarDi;
//    }
//
//    public void setSelectedCalendarDi(Calendar selectedCalendarDi) {
//        this.selectedCalendarDi = selectedCalendarDi;
//    }
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
}