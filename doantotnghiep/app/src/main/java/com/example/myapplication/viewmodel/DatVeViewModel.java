package com.example.myapplication.viewmodel;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.myapplication.BR;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.DataLocalManager;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.config.VariableGlobal;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;
import com.example.myapplication.model.DAO.DatVeDAO;
import com.example.myapplication.model.DAO.LoaiXeDAO;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.DatVe;
import com.example.myapplication.model.ThanhVien;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatVeViewModel extends BaseObservable {
    private String hoTen;
    private String email;
    private String soDienThoai;
    private String tenChuyenXe;
    private String giaVe;
    private String diaDiemDi;
    private String diaDiemDen;
    private String thoiGianXuatPhat;
    private String thoiGianDen;
    private String soLuongVe;
    private String ngayDi;
    private boolean khuHoi;
    private String ngayVe;
    private String tongTien;
    // Add these as class members
    private Handler handler = new Handler();
    private Runnable updateStatusRunnable;

    private Calendar selectedCalendarDi;

    public void xulyThongTin(ChuyenXe chuyenXe, Context context){
        ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
        ThanhVien thanhVien = thanhVienDAO.getThanhVienByUserName(DataLocalManager.getNameUser());

        setHoTen(thanhVien.getHoTen());
        setEmail(thanhVien.getEmail());
        setSoDienThoai(thanhVien.getSoDienThoai());

        setTenChuyenXe(chuyenXe.getTenChuyen());
        setGiaVe(FunctionPublic.formatMoney( chuyenXe.getGiaTien()));
        setDiaDiemDi(chuyenXe.getDiaDiemDi());
        setDiaDiemDen(chuyenXe.getDiaDiemDen());
        setThoiGianXuatPhat(chuyenXe.getThoiGianBatDau());
        setThoiGianDen(chuyenXe.getThoiGianKetThuc());
    }

    public void luuDatVe(Context context, ChuyenXe chuyenXe){
        if (getSoLuongVe() == null){
            Toast.makeText(context, "Vui lòng nhập số lượng vé", Toast.LENGTH_LONG).show();
            return;
        }
        else if (getNgayDi()==null || getNgayDi().isEmpty()){
            Toast.makeText(context, "Vui lòng nhập ngày đi", Toast.LENGTH_LONG).show();
            return;
        }
        else {

            DatVeDAO datVeDAO = AppDatabase.getInstance(context).getVeXeDAO();
            ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
            ThanhVien thanhVien = thanhVienDAO.getThanhVienByUserName(DataLocalManager.getNameUser());
            String date = VariableGlobal.dateFormat.format(new Date());
            DatVe datVe = new DatVe(thanhVien.getId(), chuyenXe.getIdChuyenXe(),Integer.parseInt(getSoLuongVe()),date,getNgayDi(), getNgayVe(),null,1);

            Log.d("TAGa", "luuDatVe: "+datVe.getId());
            datVeDAO.insert(datVe);
            scheduleUpdateStatus(context, datVe.getId());

        }


    }


    public List<Integer> setSpinner(Context context, ChuyenXe chuyenXe){
        DatVeDAO datVeDAO = AppDatabase.getInstance(context).getVeXeDAO();
        int tongSlVe = datVeDAO.tongSoLuongVe(chuyenXe.getIdChuyenXe());

        LoaiXeDAO loaiXeDAO = AppDatabase.getInstance(context).getLoaiXeDAO();
        int soLuongGhe = loaiXeDAO.getSoLuongGheByID(chuyenXe.getIdLoaiXe());
        int gheTrong = soLuongGhe - tongSlVe;

        List<Integer> list = new ArrayList<>();
        for (int i =1 ; i <= gheTrong; i++){
            list.add(i);
        }

        return list;
    }
    private void scheduleUpdateStatus(Context context, String datVeId) {
        // Cancel any previously scheduled updates
        if (updateStatusRunnable != null) {
            handler.removeCallbacks(updateStatusRunnable);
        }

        // Create a new Runnable to update the status
        updateStatusRunnable = new Runnable() {
            @Override
            public void run() {
                // Update the id trang thai to 2
                DatVeDAO datVeDAO = AppDatabase.getInstance(context).getVeXeDAO();
                DatVe datVe = datVeDAO.getDatVeById(datVeId);
                if (datVe != null) {
                    datVe.setIdTrangThai(2);
                    datVeDAO.update(datVe);
                }
            }
        };

        // Schedule the runnable to run after 1 minute (60000 milliseconds)
        handler.postDelayed(updateStatusRunnable, 600000);
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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
        notifyPropertyChanged(BR.soDienThoai);
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
    public String getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(String giaVe) {
        this.giaVe = giaVe;
        notifyPropertyChanged(BR.giaVe);
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
    public String getThoiGianXuatPhat() {
        return thoiGianXuatPhat;
    }

    public void setThoiGianXuatPhat(String thoiGianXuatPhat) {
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        notifyPropertyChanged(BR.thoiGianXuatPhat);
    }
    @Bindable
    public String getThoiGianDen() {
        return thoiGianDen;
    }

    public void setThoiGianDen(String thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
        notifyPropertyChanged(BR.thoiGianDen);
    }
    @Bindable
    public String getSoLuongVe() {
        return soLuongVe;
    }

    public void setSoLuongVe(String soLuongVe) {
        this.soLuongVe = soLuongVe;
        notifyPropertyChanged(BR.soLuongVe);
    }
    @Bindable
    public String getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(String ngayDi) {
        this.ngayDi = ngayDi;
        notifyPropertyChanged(BR.ngayDi);
    }
    @Bindable
    public boolean isKhuHoi() {
        return khuHoi;
    }

    public void setKhuHoi(boolean khuHoi) {
        this.khuHoi = khuHoi;
        notifyPropertyChanged(BR.khuHoi);
    }
    @Bindable
    public String getNgayVe() {
        return ngayVe;
    }

    public void setNgayVe(String ngayVe) {
        this.ngayVe = ngayVe;
        notifyPropertyChanged(BR.ngayVe);
    }
    @Bindable
    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
        notifyPropertyChanged(BR.tongTien);
    }



    public Calendar getSelectedCalendarDi() {
        return selectedCalendarDi;
    }

    public void setSelectedCalendarDi(Calendar selectedCalendarDi) {
        this.selectedCalendarDi = selectedCalendarDi;
    }
}
