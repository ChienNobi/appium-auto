package com.example.myapplication.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.myapplication.BR;
import com.example.myapplication.adapter.DanhGiaAdapter;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.DataLocalManager;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.config.VariableGlobal;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.DanhGiaDAO;
import com.example.myapplication.model.DAO.DatVeDAO;
import com.example.myapplication.model.DAO.LoaiXeDAO;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.DanhGia;
import com.example.myapplication.model.ThanhVien;

import java.util.Date;

public class ADetailChuyenXeViewModel extends BaseObservable {
    private String tenChuyenXe;
    private String diaDiemDi;
    private String moTa;
    private String giaChuyenXe;
    private String hinhAnh;
    private String nhanXet;
    private String diemDanhGia;
    private String themDiemDanhGia;
    private String choTrong;
    private DanhGiaAdapter danhGiaAdapter;
    private int gheTrong;

    public void layThongTinChuyenXe(ChuyenXe chuyenXe, Context context){
        DanhGiaDAO danhGiaDAO = AppDatabase.getInstance(context).getDanhGiaDAO();
        double diem = danhGiaDAO.tinhDiemDanhGiaTrungBinhTheoChuyenXe(chuyenXe.getIdChuyenXe());

        DatVeDAO datVeDAO = AppDatabase.getInstance(context).getVeXeDAO();
        int tongSlVe = datVeDAO.tongSoLuongVe(chuyenXe.getIdChuyenXe());

        LoaiXeDAO loaiXeDAO = AppDatabase.getInstance(context).getLoaiXeDAO();
        int soLuongGhe = loaiXeDAO.getSoLuongGheByID(chuyenXe.getIdLoaiXe());
        setGheTrong(soLuongGhe - tongSlVe);
        if (getGheTrong() <=0 ){
            setChoTrong("Số vé còn lại: hết vé");
        }
        else{
            setChoTrong("Số vé còn lại: " + getGheTrong());
        }
        setDiemDanhGia(FunctionPublic.formatDouble(diem));
        setTenChuyenXe(chuyenXe.getTenChuyen());
        setDiaDiemDi("Địa điểm: " + chuyenXe.getDiaDiemDi());
        setMoTa("Mô tả: "+ chuyenXe.getMoTa());
        setGiaChuyenXe("Giá: " + FunctionPublic.formatMoney(chuyenXe.getGiaTien())  + "/vé");
        setHinhAnh(chuyenXe.getHinhAnh());
    }

    public void luuDanhGia(ChuyenXe chuyenXe,Context context){
        ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
        Date date = new Date();
        String thoiGianDanhGia = VariableGlobal.dateFormat.format(date);
        DanhGia danhGia = new DanhGia(thanhVienDAO.getThanhVienByUserName(DataLocalManager.getNameUser()).getId(),
                chuyenXe.getIdChuyenXe(),
                Integer.parseInt(getThemDiemDanhGia()),
                getNhanXet(),
                thoiGianDanhGia);

        DanhGiaDAO danhGiaDAO = AppDatabase.getInstance(context).getDanhGiaDAO();
        danhGiaDAO.insert(danhGia);

        setNhanXet("");
    }

    public void renderAdapter(Context context, int id){
        danhGiaAdapter = new DanhGiaAdapter(context);
        DanhGiaDAO danhGiaDAO = AppDatabase.getInstance(context).getDanhGiaDAO();
        ;
        danhGiaAdapter.setData(danhGiaDAO.layDanhSachDanhGiaTheoChuyenXe(id));
        setDanhGiaAdapter(danhGiaAdapter);

    }

    public DanhGiaAdapter getDanhGiaAdapter() {
        return danhGiaAdapter;
    }

    public void setDanhGiaAdapter(DanhGiaAdapter danhGiaAdapter) {
        this.danhGiaAdapter = danhGiaAdapter;
    }

    public int getGheTrong() {
        return gheTrong;
    }

    public void setGheTrong(int gheTrong) {
        this.gheTrong = gheTrong;
    }

    @Bindable
    public String getGiaChuyenXe() {
        return giaChuyenXe;
    }

    public void setGiaChuyenXe(String giaChuyenXe) {
        this.giaChuyenXe = giaChuyenXe;
        notifyPropertyChanged(BR.giaChuyenXe);
    }

    public String getThemDiemDanhGia() {
        return themDiemDanhGia;
    }

    public void setThemDiemDanhGia(String themDiemDanhGia) {
        this.themDiemDanhGia = themDiemDanhGia;
    }

    @Bindable
    public String getDiemDanhGia() {
        return diemDanhGia;
    }

    public void setDiemDanhGia(String diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
        notifyPropertyChanged(BR.diemDanhGia);
    }

    @Bindable
    public String getNhanXet() {
        return nhanXet;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
        notifyPropertyChanged(BR.nhanXet);
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
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
        notifyPropertyChanged(BR.moTa);
    }

    @Bindable
    public String getChoTrong() {
        return choTrong;
    }

    public void setChoTrong(String choTrong) {
        this.choTrong = choTrong;
        notifyPropertyChanged(BR.choTrong);
    }
}
