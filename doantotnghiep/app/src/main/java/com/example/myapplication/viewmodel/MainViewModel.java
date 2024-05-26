package com.example.myapplication.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.DataLocalManager;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.view.AdminManagerActivity;
import com.example.myapplication.view.UserManagerActivity;

public class MainViewModel extends BaseObservable {
    public void checkLogin(Context context){
        if (DataLocalManager.getIsLogin()){
            ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
            ThanhVien thanhVien =  thanhVienDAO.getThanhVienByUserName(DataLocalManager.getNameUser());
            chuyenTrangTheoQuyen(thanhVien.getIdQuyenThanhVien(), context);

        }
        else {
            Toast.makeText(context, "Hãy đăng nhập hoặc đăng ký để trải nghiệm ứng dụng này", Toast.LENGTH_LONG).show();

        }
    }

    public void chuyenTrangTheoQuyen(int quyen, Context context){
        if(quyen == 1 || quyen == 2){
            Intent intent = new Intent(context, AdminManagerActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
        else {
            Intent intent = new Intent(context, UserManagerActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
