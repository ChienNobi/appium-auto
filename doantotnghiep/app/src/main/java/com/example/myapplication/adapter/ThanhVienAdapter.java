package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.DataLocalManager;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.view.DetailThanhVienFragment;
import com.example.myapplication.view.UpdateThanhVienFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienHolder> implements Filterable {

    private List<ThanhVien> thanhViens;
    private List<ThanhVien> thanhViensOld;
    private Context context;

    public void setData(List<ThanhVien> list){
        this.thanhViens = list;
        this.thanhViensOld = list;
        notifyDataSetChanged();
    }

    public ThanhVienAdapter(Context context) {
        this.context = context;
    }

    public void release(){
        this.context= null;
    }

    @NonNull
    @Override
    public ThanhVienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_thanh_vien, parent,false);
        return new ThanhVienHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienHolder holder, @SuppressLint("RecyclerView") int position) {
        ThanhVien thanhVien = thanhViens.get(position);

        holder.id.setText(String.valueOf(thanhVien.getId()));
        holder.tenDangNhap.setText(thanhVien.getTenDangNhap());
        holder.email.setText(thanhVien.getEmail());

        holder.itemThanhVien.setOnClickListener(view -> {


            DetailThanhVienFragment detailThanhVienFragment = new DetailThanhVienFragment();
            showDetail(thanhVien,detailThanhVienFragment );

        });

        holder.btnDelete.setOnClickListener(view -> showConfirmationDialog(thanhVien, position));

        holder.btnEdit.setOnClickListener(view -> {
            UpdateThanhVienFragment updateThanhVienFragment = new UpdateThanhVienFragment();
            showDetail(thanhVien,updateThanhVienFragment );
        });
        String imageUrl =  thanhVien.getAvatar();
        FunctionPublic.loadImage(imageUrl,holder.avt,this.context);
    }

    private void showConfirmationDialog(ThanhVien thanhVien, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có muốn xóa thành viên này?");
        builder.setPositiveButton("Đồng ý", (dialogInterface, i) -> {
            if (thanhVien.getTenDangNhap().equals(DataLocalManager.getNameUser())){
                Toast.makeText(context, "Bạn không thể xóa chính mình :)", Toast.LENGTH_LONG).show();
                return;
            }
            else if (DataLocalManager.getIdRole() == 2){
                Toast.makeText(context, "Bạn không có quyền xóa người khác", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                // Xóa thành viên ở vị trí position
                thanhViens.remove(position);
                AppDatabase.getInstance(context).getThanhVienDAO().delete(thanhVien);
                notifyDataSetChanged(); // Cập nhật giao diện sau khi xóa
            }

        });
        builder.setNegativeButton("Hủy", (dialogInterface, i) -> {
            // Đóng hộp thoại khi người dùng không đồng ý xóa
            dialogInterface.dismiss();
        });
        builder.create().show();
    }


    private void showDetail(ThanhVien thanhVien, Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("thanhVien", thanhVien);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerThanhVienManager, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        if (thanhViens != null){
            return  thanhViens.size();
        }
        return 0;
    }


    public static class ThanhVienHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView tenDangNhap;
        TextView email;
        CardView itemThanhVien;
        ImageView btnEdit;
        ImageView btnDelete;
        CircleImageView avt;
        public ThanhVienHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idThanhVien);
            tenDangNhap = itemView.findViewById(R.id.tenDangNhap);
            email = itemView.findViewById(R.id.email);
            itemThanhVien = itemView.findViewById(R.id.itemThanhVien);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            avt = itemView.findViewById(R.id.avt_thanhVien);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()){
                    thanhViens = thanhViensOld;
                }
                else {
                    List<ThanhVien> list = new ArrayList<>();
                    for (ThanhVien thanhVien : thanhViensOld){
                        if (thanhVien.getTenDangNhap().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(thanhVien);
                        }
                    }

                    thanhViens = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = thanhViens;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                thanhViens = (List<ThanhVien>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
