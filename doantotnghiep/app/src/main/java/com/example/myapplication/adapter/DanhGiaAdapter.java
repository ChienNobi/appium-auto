package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.DanhGia;
import com.example.myapplication.model.ThanhVien;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.DanhGiaViewHolder> {

    private List<DanhGia> danhGias;
    private Context context;

    public void setData(List<DanhGia> list){
        this.danhGias =list ;
        notifyDataSetChanged();
    }
    public DanhGiaAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public DanhGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_gia, parent,false);
        return new DanhGiaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaViewHolder holder, int position) {
        DanhGia danhGia = danhGias.get(position);
        ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();

        ThanhVien t =thanhVienDAO.getThanhVienById(danhGia.getIdThanhVienDanhGia());
        holder.diemDanhGia.setText(FunctionPublic.formatDouble(danhGia.getDiemDanhGia()));
        holder.nhanXet.setText(danhGia.getNhanXet());
        FunctionPublic.loadImage(t.getAvatar(),holder.avatar,context);
        holder.tenThanhVien.setText(t.getHoTen());
        holder.thoiGianDanhGia.setText(danhGia.getThoiGianDanhGia());
    }

    @Override
    public int getItemCount() {
        if (danhGias != null){
            return danhGias.size();
        }
        return 0;
    }

    public class DanhGiaViewHolder extends RecyclerView.ViewHolder{
        private TextView tenThanhVien;
        private TextView nhanXet;
        private TextView diemDanhGia;
        private CircleImageView avatar;
        private TextView thoiGianDanhGia;
        public DanhGiaViewHolder(@NonNull View itemView) {
            super(itemView);

            tenThanhVien = itemView.findViewById(R.id.tenThanhVien);
            nhanXet = itemView.findViewById(R.id.nhanXet);
            diemDanhGia = itemView.findViewById(R.id.diemDanhGia);
            avatar = itemView.findViewById(R.id.avatarImg);
            thoiGianDanhGia = itemView.findViewById(R.id.thoiGianDanhGia);
        }
    }
}
