package com.example.myapplication.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.myapplication.BR;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;
import com.example.myapplication.model.DAO.DatVeDAO;
import com.example.myapplication.model.DAO.TrangThaiDAO;
import com.example.myapplication.model.DatVe;

import java.util.List;

public class DetailVeViewModel extends BaseObservable {
    private String maVe;
    private String diaDiemDi;
    private String diaDiemDen;
    private String diaDiemDiNgayVe;
    private String diaDiemDenNgayVe;
    private String soLuongVe;
    private String ngayDi;
    private String ngayVe;
    private int idTrangThai;

    public void setDetailVe(DatVe datVe, Context context){
        ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(context).getChuyenXeDAO();
        ChuyenXe chuyenXe = chuyenXeDAO.getChuyenXeById(datVe.getIdChuyenXeVeXe());
        setMaVe("Mã vé: " + datVe.getId());
        setDiaDiemDi("Địa điểm đi: " + chuyenXe.getDiaDiemDi());
        setDiaDiemDen("Địa điểm đến: " + chuyenXe.getDiaDiemDen());
        setSoLuongVe("Số lượng vé: " + datVe.getSoLuongVe());
        setNgayDi("Ngày đi: "+ datVe.getNgayGioDi() );


            setDiaDiemDiNgayVe("Địa điểm đi: "+chuyenXe.getDiaDiemDen());
            setDiaDiemDenNgayVe("Địa điểm đến: "+chuyenXe.getDiaDiemDi());
            setNgayVe("Ngày về: " + datVe.getNgayGioVe());



    }
    public List<String> listTrangThai(Context context){
        TrangThaiDAO trangThaiDAO = AppDatabase.getInstance(context).getTrangThaiDAO();
        List<String > list = trangThaiDAO.getListTenTrangThai();
        return list;
    }
    public void updateTrangThai(DatVe datVe, Context context){
        DatVeDAO datVeDAO = AppDatabase.getInstance(context).getVeXeDAO();
        datVe.setIdTrangThai(getIdTrangThai());
        datVeDAO.update(datVe);
    }
    public void kiemTraNgayVe(View v, String ngayVe){
        if (ngayVe==null || ngayVe.isEmpty()){
            v.setVisibility(View.GONE);
        }
        else {
            v.setVisibility(View.VISIBLE);
        }
    }

    public int getIdTrangThai() {
        return idTrangThai;
    }

    public void setIdTrangThai(int idTrangThai) {
        this.idTrangThai = idTrangThai;
    }

    @Bindable
    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
        notifyPropertyChanged(BR.maVe);
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
    public String getNgayVe() {
        return ngayVe;
    }

    public void setNgayVe(String ngayVe) {
        this.ngayVe = ngayVe;
        notifyPropertyChanged(BR.ngayVe);
    }
    @Bindable
    public String getDiaDiemDiNgayVe() {
        return diaDiemDiNgayVe;
    }

    public void setDiaDiemDiNgayVe(String diaDiemDiNgayVe) {
        this.diaDiemDiNgayVe = diaDiemDiNgayVe;
        notifyPropertyChanged(BR.diaDiemDiNgayVe);
    }
    @Bindable
    public String getDiaDiemDenNgayVe() {
        return diaDiemDenNgayVe;

    }

    public void setDiaDiemDenNgayVe(String diaDiemDenNgayVe) {
        this.diaDiemDenNgayVe = diaDiemDenNgayVe;
        notifyPropertyChanged(BR.diaDiemDenNgayVe);
    }
}
