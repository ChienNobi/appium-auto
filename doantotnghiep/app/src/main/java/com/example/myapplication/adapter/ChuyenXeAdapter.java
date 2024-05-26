package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DatVe;
import com.example.myapplication.view.DetailChuyenXeFragment;
import com.example.myapplication.view.UpdateChuyenXeFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChuyenXeAdapter extends RecyclerView.Adapter<ChuyenXeAdapter.ChuyenXeHolder> implements Filterable {

    private List<ChuyenXe> chuyenXes;
    private List<ChuyenXe> chuyenXeSearch;
    private Context context;

    public void setData(List<ChuyenXe> list){
        this.chuyenXes = list;
        this.chuyenXeSearch = list;
        notifyDataSetChanged();
    }

    public ChuyenXeAdapter(Context context) {
        this.context = context;
    }

    public void release(){
        this.context= null;
    }

    @NonNull
    @Override
    public ChuyenXeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_chuyen_xe, parent,false);
        return new ChuyenXeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuyenXeHolder holder, @SuppressLint("RecyclerView") int position) {
        ChuyenXe chuyenXe = chuyenXes.get(position);

        holder.tenChuyenXe.setText(chuyenXe.getTenChuyen());
        holder.thoiGianBatDau.setText(chuyenXe.getThoiGianBatDau());
        holder.diaDiemDi.setText(String.valueOf(chuyenXe.getDiaDiemDi()));

        holder.itemChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailChuyenXeFragment detailChuyenXeFragment = new DetailChuyenXeFragment();
                showDetail(chuyenXe,detailChuyenXeFragment);
            }
        });
        holder.imgDeleteChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog(chuyenXe,position);
            }
        });
        holder.imgEditChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateChuyenXeFragment updateChuyenXeFragment = new UpdateChuyenXeFragment();
                showDetail(chuyenXe,updateChuyenXeFragment );
            }
        });
        String imageUrl =  chuyenXe.getHinhAnh();
        FunctionPublic.loadImage(imageUrl, holder.imgChuyenXe,context);

    }

    private void showConfirmationDialog(ChuyenXe chuyenXe, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có muốn xóa chuyến xe này?");
        builder.setPositiveButton("Đồng ý", (dialogInterface, i) -> {
            List<Integer> listIdXe = new ArrayList<>();
            List<DatVe> listVe1 = AppDatabase.getInstance(context).getVeXeDAO().getAll();
            if(listVe1 == null || listVe1.isEmpty()){

                chuyenXes.remove(position);
                AppDatabase.getInstance(context).getChuyenXeDAO().delete(chuyenXe);
                notifyDataSetChanged(); // Cập nhật giao diện sau khi xóa
                dialogInterface.dismiss();
            }
            for (int j = 0;j < listVe1.size(); j++){
                listIdXe.add(listVe1.get(j).getIdChuyenXeVeXe());
            }
            if(listIdXe.isEmpty()){
                chuyenXes.remove(position);
                AppDatabase.getInstance(context).getChuyenXeDAO().delete(chuyenXe);
                notifyDataSetChanged(); // Cập nhật giao diện sau khi xóa
            } else {
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

    private void showDetail(ChuyenXe chuyenXe , Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("chuyenXe", chuyenXe);

        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerChuyenXeManager, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        if (chuyenXes != null){
            return  chuyenXes.size();
        }
        return 0;
    }

    public static class ChuyenXeHolder extends RecyclerView.ViewHolder{
        TextView tenChuyenXe;
        TextView thoiGianBatDau;
        TextView diaDiemDi;
        CardView itemChuyenXe;
        ImageView imgDeleteChuyenXe;
        ImageView imgEditChuyenXe;
        CircleImageView imgChuyenXe;
        public ChuyenXeHolder(@NonNull View itemView) {
            super(itemView);
            tenChuyenXe = itemView.findViewById(R.id.tenChuyenXe);
            thoiGianBatDau = itemView.findViewById(R.id.thoiGianBatDau);
            diaDiemDi = itemView.findViewById(R.id.diaDiemDi);
            itemChuyenXe = itemView.findViewById(R.id.itemChuyenXe);
            imgDeleteChuyenXe = itemView.findViewById(R.id.btnDeleteChuyenXe);
            imgEditChuyenXe = itemView.findViewById(R.id.btnEditChuyenXe);
            imgChuyenXe = itemView.findViewById(R.id.img_chuyenXe);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strChuyenXeSearch = charSequence.toString();
                if (strChuyenXeSearch.isEmpty()){
                    chuyenXes = chuyenXeSearch;
                }
                else {
                    List<ChuyenXe> list = new ArrayList<>();
                    for (ChuyenXe chuyenXe : chuyenXeSearch){
                        if (chuyenXe.getTenChuyen().toLowerCase().contains(strChuyenXeSearch.toLowerCase())){
                            list.add(chuyenXe);
                        }
                    }

                    chuyenXes = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = chuyenXes;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                chuyenXes = (List<ChuyenXe>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
