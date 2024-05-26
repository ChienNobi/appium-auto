package com.example.myapplication.viewmodel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.DatePicker;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UpdateChuyenXeViewModel extends BaseObservable {
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
                        Calendar gioDi = FunctionPublic.convertStringToCalendar(getThoiGianDi());
                        if (gioDi == null){
                            Toast.makeText(context, "Vui lòng nhập giờ đi", Toast.LENGTH_SHORT ).show();
                        }
                        else {

                            // Xử lý thời gian được chọn bởi người dùng khi thời gian hợp lệ
                            if(hourOfDay > gioDi.get(Calendar.HOUR_OF_DAY) ||
                                    (hourOfDay == gioDi.get(Calendar.HOUR_OF_DAY) && minute > gioDi.get(Calendar.MINUTE))){
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


    public Calendar convertStringToTime(String timeString) {
        // Phân tích chuỗi để lấy giá trị giờ và phút
        String[] parts = timeString.split("h");
        if (parts.length != 2) {
            // Nếu định dạng không hợp lệ, trả về null hoặc xử lý lỗi tùy vào yêu cầu của ứng dụng
            return null;
        }

        try {
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            // Tạo đối tượng Calendar và đặt giá trị giờ và phút
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            return calendar;
        } catch (NumberFormatException e) {
            // Nếu không thể chuyển đổi giờ và phút thành số nguyên, trả về null hoặc xử lý lỗi tùy vào yêu cầu của ứng dụng
            e.printStackTrace();
            return null;
        }
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

    public void setDetailChuyenXe(ChuyenXe chuyenXe, Context context){

        this.setTenChuyenXe(chuyenXe.getTenChuyen());
        this.setTenChuyenXe(chuyenXe.getTenChuyen());
        this.setDiaDiemDi(chuyenXe.getDiaDiemDi());
        this.setDiaDiemDen(chuyenXe.getDiaDiemDen());
        this.setThoiGianDi(chuyenXe.getThoiGianBatDau());
        this.setThoiGianDen(chuyenXe.getThoiGianKetThuc());
        this.setGiaTien(String.valueOf(chuyenXe.getGiaTien()));
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        decimalFormat.applyPattern("#,###.##");
        String giaTienFormatted = decimalFormat.format(chuyenXe.getGiaTien());
        this.setGiaTien(giaTienFormatted);
        this.setMoTa(chuyenXe.getMoTa());
        this.setHinhAnh(chuyenXe.getHinhAnh());

    }

    public void UpdateChuyenXe(ChuyenXe chuyenXe,Context context){

        if (kiemTraNhap(tenChuyenXe,diaDiemDi,diaDiemDen,thoiGianDi,thoiGianDen,giaTien,moTa)==false) {
            Toast.makeText(context,"Vui lòng nhập đầy đủ dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }
        Calendar calendarDi = convertStringToTime(getThoiGianDi());
        Calendar calendarVe = convertStringToTime(getThoiGianDen());
        if(calendarDi.compareTo(calendarVe) <=0){
            ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(context).getChuyenXeDAO();
            chuyenXe.setIdLoaiXe(getIdLoaiXe());
            chuyenXe.setTenChuyen(getTenChuyenXe());
            chuyenXe.setHinhAnh(getHinhAnh());
            chuyenXe.setThoiGianBatDau(getThoiGianDi());
            chuyenXe.setThoiGianKetThuc(getThoiGianDen());
            chuyenXe.setDiaDiemDi(getDiaDiemDi());
            chuyenXe.setDiaDiemDen(getDiaDiemDen());
            chuyenXe.setGiaTien(Double.parseDouble(getGiaTien().replace(",","")));
            chuyenXe.setMoTa(getMoTa());
            chuyenXeDAO.updateChuyenXe(chuyenXe);


            Toast.makeText(context,"Cập nhật chuyến xe thành công",Toast.LENGTH_SHORT).show();
            goToListFragment(context);
        }else{
            Toast.makeText(context,"Kiểm tra lại ngày đi và ngày về",Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void goToListFragment(Context context){

        ListChuyenXeFragment listChuyenXeFragment = new ListChuyenXeFragment();
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerChuyenXeManager, listChuyenXeFragment);
        fragmentTransaction.commit();
    }


    public boolean kiemTraNhap(String tenChuyenXe, String diaDiemDi, String diaDiemDen,String thoiGianDi,String thoiGianDen,String giaTien,String moTa) {
        //Tên đăng nhập và mật khẩu trống
        if (TextUtils.isEmpty(tenChuyenXe) || TextUtils.isEmpty(diaDiemDi) || TextUtils.isEmpty(diaDiemDen)|| TextUtils.isEmpty(thoiGianDi)|| TextUtils.isEmpty(thoiGianDen)||
                 TextUtils.isEmpty(giaTien)|| TextUtils.isEmpty(moTa)) {
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
        notifyPropertyChanged(BR.diaDiemDen);
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
        notifyPropertyChanged(BR.hinhAnh);
    }
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