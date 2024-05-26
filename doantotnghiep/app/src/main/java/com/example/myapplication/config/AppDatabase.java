package com.example.myapplication.config;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;
import com.example.myapplication.model.DAO.DanhGiaDAO;
import com.example.myapplication.model.DAO.DatVeDAO;
import com.example.myapplication.model.DAO.LoaiXeDAO;
import com.example.myapplication.model.DAO.QuyenDao;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.DAO.TrangThaiDAO;
import com.example.myapplication.model.DanhGia;
import com.example.myapplication.model.LoaiXe;
import com.example.myapplication.model.Quyen;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.model.DatVe;
import com.example.myapplication.model.TrangThai;

@Database(version = 1,
    entities = {
            ThanhVien.class,
            Quyen.class,
            ChuyenXe.class,
            LoaiXe.class,
            DanhGia.class,
            DatVe.class,
            TrangThai.class,
    }
    , exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {


    public abstract ThanhVienDAO getThanhVienDAO();
    public abstract QuyenDao getQuyenDAO();
    public abstract ChuyenXeDAO getChuyenXeDAO();
    public abstract LoaiXeDAO getLoaiXeDAO();
    public abstract DanhGiaDAO getDanhGiaDAO();
    public abstract DatVeDAO getVeXeDAO();
    public abstract TrangThaiDAO getTrangThaiDAO();
    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "doanandroid.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
