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
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.DatVeDAO;
import com.example.myapplication.model.DatVe;
import com.example.myapplication.model.ThanhVien;

import java.util.List;

public class ThongKeChuyenXeAdapter extends RecyclerView.Adapter<ThongKeChuyenXeAdapter.ThongKeChuyenXeViewHolder> {
    private List<ChuyenXe> chuyenXes;
    private Context context;
    public void setData(List<ChuyenXe> list){
        this.chuyenXes = list  ;
    }

    public ThongKeChuyenXeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ThongKeChuyenXeAdapter.ThongKeChuyenXeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thong_ke_chuyen_xe, parent, false);

        return new ThongKeChuyenXeAdapter.ThongKeChuyenXeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeChuyenXeAdapter.ThongKeChuyenXeViewHolder holder, int position) {
        ChuyenXe chuyenXe = chuyenXes.get(position);

        holder.tenChuyenXe.setText("Tên chuyến xe: " + chuyenXe.getTenChuyen());

        DatVeDAO datVeDAO = AppDatabase.getInstance(context).getVeXeDAO();
        List<DatVe> listVe = datVeDAO.getVeByIdChuyenXe(chuyenXe.getIdChuyenXe());
        int tongSoLuongVe = datVeDAO.tongSoLuongVe(chuyenXe.getIdChuyenXe());
        holder.soLuongVe.setText("Tổng số lượng vé đã đặt: " + tongSoLuongVe);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.rcvThongKeVeChuyenXe.setLayoutManager(linearLayoutManager);

        VeAdapter veAdapter = new VeAdapter(context);
        veAdapter.setData(listVe);
        holder.rcvThongKeVeChuyenXe.setAdapter(veAdapter);
    }

    @Override
    public int getItemCount() {
        if (chuyenXes != null){
            return chuyenXes.size();
        }
        return 0;
    }

    public class ThongKeChuyenXeViewHolder extends RecyclerView.ViewHolder{
        private TextView tenChuyenXe;
        private RecyclerView rcvThongKeVeChuyenXe;
        private TextView soLuongVe;
        public ThongKeChuyenXeViewHolder(@NonNull View itemView) {
            super(itemView);
            tenChuyenXe = itemView.findViewById(R.id.tenChuyenXe);
            rcvThongKeVeChuyenXe = itemView.findViewById(R.id.rcvChuyenXe);
            soLuongVe = itemView.findViewById(R.id.soLuongVe);
        }
    }
}

