package com.example.myapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.DataLocalManager;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.LoaiXe;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.view.DetailLoaiXeFragment;
import com.example.myapplication.view.DetailThanhVienFragment;
import com.example.myapplication.view.UpdateLoaiXeFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoaiXeAdapter extends RecyclerView.Adapter<LoaiXeAdapter.LoaiXeHolder> implements Filterable {
    private List<LoaiXe> loaiXeOld;

    private List<LoaiXe> loaixes;
    private Context context;

    public void setData(List<LoaiXe> list){
        this.loaiXeOld = list;
        this.loaixes = list;
        notifyDataSetChanged();
    }
    public LoaiXeAdapter(Context context) {
        this.context = context;
    }

    public void release(){
        this.context= null;
    }

    @NonNull
    @Override
    public LoaiXeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_loaixe, parent,false);
        return new LoaiXeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiXeHolder holder, int position) {
        LoaiXe loaiXe = loaixes.get(position);

        holder.maSoLoaiXe.setText(String.valueOf(loaiXe.getIdLoaiXe()));
        holder.tenLoaiXe.setText(loaiXe.getTenLoaiXe());
        holder.itemLoaiXe.setOnClickListener(view -> {

        });
        holder.btnDelete.setOnClickListener(view -> showDiaLog(loaiXe, position));
        holder.btnEdit.setOnClickListener(view -> {
            UpdateLoaiXeFragment updateLoaiXeFragment = new UpdateLoaiXeFragment();
            showDetail(loaiXe,updateLoaiXeFragment);
        });
        holder.itemLoaiXe.setOnClickListener(view -> {
            DetailLoaiXeFragment detailLoaiXeFragment = new DetailLoaiXeFragment();
            ShowDetail(loaiXe,detailLoaiXeFragment);
        });
    }
    private void showDetail(LoaiXe loaiXe, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("loaiXe", loaiXe);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerLoaiXeManager, fragment);
        fragmentTransaction.commit();
    }
    private void showDiaLog(LoaiXe loaiXe, int posiotion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có muốn xóa loại xe này");
        builder.setPositiveButton("Đồng ý",(dialogInterface, i) -> {
            List<ChuyenXe> chuyenXeList = AppDatabase.getInstance(context).getChuyenXeDAO().getChuyenXeByIdLoaiXe(loaiXe.getIdLoaiXe());
            if(chuyenXeList == null || chuyenXeList.isEmpty()){
                loaixes.remove(posiotion);
                AppDatabase.getInstance(context).getLoaiXeDAO().delete(loaiXe);
                notifyDataSetChanged(); // cập nhật giao diện sao khi xóa
            } else  {
                Toast.makeText(context, "Đang có khách hàng đặt vé xe của hãng này", Toast.LENGTH_SHORT).show();
            }
            dialogInterface.dismiss();
        });
        builder.setNegativeButton("Hủy", (dialogInterface, i) -> {
            // Đóng hộp thoại khi người dùng không đồng ý xóa
            dialogInterface.dismiss();
        });
        builder.create().show();
     }
    @Override
    public int getItemCount() {
        if (loaixes != null){
            return  loaixes.size();
        }
        return 0;
    }
    private  void ShowDetail(LoaiXe loaiXe, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putSerializable("loaiXe",  loaiXe);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerLoaiXeManager,fragment);
        fragmentTransaction.commit();
    }
    public static class LoaiXeHolder extends RecyclerView.ViewHolder{
        TextView tenLoaiXe;
        TextView maSoLoaiXe;
        ImageView btnDelete;
        ImageView btnEdit;
        CardView itemLoaiXe;
        public LoaiXeHolder(@NonNull View itemView) {
            super(itemView);
            itemLoaiXe = itemView.findViewById(R.id.itemLoaiXe);
            tenLoaiXe = itemView.findViewById(R.id.tenLoaiXe);
            maSoLoaiXe = itemView.findViewById(R.id.idLoaiXe);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()){
                    loaixes = loaiXeOld;
                }
                else {
                    List<LoaiXe> list = new ArrayList<>();
                    for (LoaiXe loaiXe : loaiXeOld){
                        if (loaiXe.getTenLoaiXe().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(loaiXe);
                        }
                    }

                    loaixes = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = loaixes;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                loaixes= (List<LoaiXe>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
