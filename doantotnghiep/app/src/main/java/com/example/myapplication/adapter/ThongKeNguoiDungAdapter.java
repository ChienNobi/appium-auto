package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.model.DAO.DatVeDAO;
import com.example.myapplication.model.DatVe;
import com.example.myapplication.model.ThanhVien;

import java.util.List;

public class ThongKeNguoiDungAdapter extends RecyclerView.Adapter<ThongKeNguoiDungAdapter.ThongKeNguoiDungViewHolder>{
    private List<ThanhVien> thanhViens;
    private Context context;
    public void setData(List<ThanhVien> list){
        this.thanhViens = list  ;
    }

    public ThongKeNguoiDungAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ThongKeNguoiDungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thong_ke_nguoi_dung, parent, false);

        return new ThongKeNguoiDungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeNguoiDungViewHolder holder, int position) {
        ThanhVien thanhVien = thanhViens.get(position);

        holder.tenDangNhap.setText("Tên đăng nhập: " + thanhVien.getTenDangNhap());

        DatVeDAO datVeDAO = AppDatabase.getInstance(context).getVeXeDAO();
        List<DatVe> listVe = datVeDAO.getVeXeById(thanhVien.getId());
        int tongSoLuongVe = datVeDAO.tongSoLuongVeTheoNguoiDung(thanhVien.getId());
        holder.soLuongVe.setText("Tổng số lượng vé đã đặt: " + tongSoLuongVe);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.rcvThongKeVe.setLayoutManager(linearLayoutManager);

        VeAdapter veAdapter = new VeAdapter(context);
        veAdapter.setData(listVe);
        holder.rcvThongKeVe.setAdapter(veAdapter);
    }

    @Override
    public int getItemCount() {
        if (thanhViens != null){
            return thanhViens.size();
        }
        return 0;
    }

    public class ThongKeNguoiDungViewHolder extends RecyclerView.ViewHolder{
        private TextView tenDangNhap;
        private RecyclerView rcvThongKeVe;
        private TextView soLuongVe;
        public ThongKeNguoiDungViewHolder(@NonNull View itemView) {
            super(itemView);
            tenDangNhap = itemView.findViewById(R.id.tenDangNhap);
            rcvThongKeVe = itemView.findViewById(R.id.rcvNguoiDung);
            soLuongVe = itemView.findViewById(R.id.soLuongVe);
        }
    }
}
