package com.example.myapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.DatVe;
import com.example.myapplication.view.DetailVeFragment;

import java.util.List;

public class VeAdapter  extends RecyclerView.Adapter<VeAdapter.VeViewHolder>{
    private List<DatVe> datVes;
    private Context context;
    public void setData(List<DatVe> list){
        this.datVes = list  ;
    }

    public VeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ve,parent,false);
        return new VeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeViewHolder holder, int position) {
        DatVe datVe = datVes.get(position);
        holder.maVe.setText("Mã vé: " + datVe.getId());

        holder.ve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailVeFragment veDetailFragment = new DetailVeFragment();
                showDetail(datVe,veDetailFragment);
            }
        });

        holder.soLuongVe.setText("Số lượng vé: " + datVe.getSoLuongVe());
        holder.ngayDat.setText("Ngày đặt: "+ datVe.getNgayGioDat());
    }

    private void showDetail(DatVe datVe , Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putSerializable("ve", datVe);

        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerDatVe, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        if (datVes != null){
            return datVes.size();
        }
        return 0;
    }

    public class VeViewHolder extends RecyclerView.ViewHolder{
        private TextView maVe;
        private CardView ve;
        private TextView soLuongVe;
        private TextView ngayDat;

        public VeViewHolder(@NonNull View itemView) {
            super(itemView);
            maVe = itemView.findViewById(R.id.maVe);
            ve = itemView.findViewById(R.id.cvVe);
            soLuongVe = itemView.findViewById(R.id.soLuongVe);
            ngayDat = itemView.findViewById(R.id.ngayDat);
        }
    }
}
