package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;
import com.example.myapplication.model.DAO.TrangThaiDAO;
import com.example.myapplication.model.DatVe;

import java.util.List;

public class HistoryBookAdapter extends RecyclerView.Adapter<HistoryBookAdapter.HistoryBookViewHolder> {
    private List<DatVe> datVes;
    private Context context;

    public HistoryBookAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<DatVe> list){
        this.datVes = list  ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_book, parent, false );
        return new HistoryBookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryBookViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DatVe datVe = datVes.get(position);

        ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(context).getChuyenXeDAO();
        TrangThaiDAO trangThaiDAO = AppDatabase.getInstance(context).getTrangThaiDAO();
        String tenChuyenXe = chuyenXeDAO.getTenChuyenById(datVe.getIdChuyenXeVeXe());
        double giaVe = chuyenXeDAO.getGiaVeById(datVe.getIdChuyenXeVeXe());
        holder.tenChuyenXe.setText("Tên chuyến xe: " + tenChuyenXe);
        holder.ngayDi.setText("Ngày đi: " +  datVe.getNgayGioDi());
        holder.ngayVe.setText("Ngày về: " + datVe.getNgayGioVe());
        holder.soLuongVe.setText("Số lượng: " + datVe.getSoLuongVe());

        double tongTien = FunctionPublic.tinhTongTien(datVe.getSoLuongVe(), giaVe);
        holder.tongTien.setText("Tổng tiền: " + FunctionPublic.formatMoney(tongTien));
        String tenTrangThai = trangThaiDAO.getTenTrangThaiById(datVe.getIdTrangThai());
        holder.trangThai.setText("Trạng Thái: " +  tenTrangThai);
        holder.maVe.setText("Mã vé: " + datVe.getId());

        holder.huyVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTrangThai(datVe ,position);
            }
        });
    }

    private void handleTrangThai(DatVe datVe, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận hủy");
        builder.setMessage("Bạn có chắc muốn hủy  vé");
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (datVe.getIdTrangThai() == 1){
                    datVes.remove(position);
                    AppDatabase.getInstance(context).getVeXeDAO().delete(datVe);
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Bạn không thể hủy vé khi vé đang được duyệt bởi admin", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        if (datVes != null){
            return datVes.size();
        }
        return 0;
    }

    public class HistoryBookViewHolder extends RecyclerView.ViewHolder{
        private TextView tenChuyenXe;
        private TextView ngayDi;
        private TextView ngayVe;
        private TextView soLuongVe;
        private TextView tongTien;
        private Button huyVe;
        private TextView trangThai;
        private TextView maVe;
        public HistoryBookViewHolder(@NonNull View itemView) {
            super(itemView);
            tenChuyenXe = itemView.findViewById(R.id.tenChuyenXe);
            ngayDi = itemView.findViewById(R.id.ngayDi);
            ngayVe = itemView.findViewById(R.id.ngayVe);
            soLuongVe = itemView.findViewById(R.id.soLuongVe);
            tongTien = itemView.findViewById(R.id.tongTien);
            huyVe = itemView.findViewById(R.id.huyVe);
            trangThai = itemView.findViewById(R.id.trangThai);
            maVe =itemView.findViewById(R.id.idVeXe);
        }
    }
}
