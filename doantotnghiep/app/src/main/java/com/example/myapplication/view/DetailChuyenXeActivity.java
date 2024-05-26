package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.databinding.ActivityDetailChuyenXeBinding;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.viewmodel.ADetailChuyenXeViewModel;

public class DetailChuyenXeActivity extends AppCompatActivity {
    ActivityDetailChuyenXeBinding detailThanhVienBinding;
    ADetailChuyenXeViewModel aDetailChuyenXeViewModel= new ADetailChuyenXeViewModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailThanhVienBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_chuyen_xe);

        detailThanhVienBinding.setADetailChuyenXeViewModel(aDetailChuyenXeViewModel);

        if (getIntent().getExtras() != null) {
            ChuyenXe chuyenXe = (ChuyenXe) getIntent().getExtras().getSerializable("chuyen_xe");

            aDetailChuyenXeViewModel.layThongTinChuyenXe(chuyenXe, getApplicationContext());

            FunctionPublic.loadImage(aDetailChuyenXeViewModel.getHinhAnh(), detailThanhVienBinding.hinhAnhChuyenXe, getApplicationContext());
            detailThanhVienBinding.danhgiaGroup.setOnCheckedChangeListener((radioGroup, i) -> {
                RadioButton radioButton = findViewById(i);
                if (radioButton != null && radioButton.isChecked()) {
                    // Xử lý sự kiện khi người dùng chọn một Radio Button
                    int selectedOption = Integer.parseInt(radioButton.getHint().toString());
                    aDetailChuyenXeViewModel.setThemDiemDanhGia(String.valueOf(selectedOption));

                }
            });

            detailThanhVienBinding.btnGui.setOnClickListener(view -> {
                aDetailChuyenXeViewModel.luuDanhGia(chuyenXe, getApplicationContext());
                setAdapterForDanhGia(chuyenXe.getIdChuyenXe());
                anHienNhanXet();
            });
            setAdapterForDanhGia(chuyenXe.getIdChuyenXe());

            detailThanhVienBinding.btnDatVe.setOnClickListener(view -> {
                if (aDetailChuyenXeViewModel.getGheTrong() <=0 ){
                    Toast.makeText(getApplicationContext(), "Bạn không thể đặt chuyến xe này vì hết vế", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i = new Intent(getApplicationContext(), DatVeActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("chuyen_xe", chuyenXe);
                i.putExtras(bundle);
                startActivity(i);
            });
        }
        detailThanhVienBinding.btnAddNhanXet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anHienNhanXet();

            }
        });
    }
    private void anHienNhanXet() {
        if (detailThanhVienBinding.layoutNhanXet.getVisibility() == View.VISIBLE) {
            detailThanhVienBinding.layoutNhanXet.setVisibility(View.GONE);
        } else {
            detailThanhVienBinding.layoutNhanXet.setVisibility(View.VISIBLE);
        }
    }
    public void setAdapterForDanhGia(int id ){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        detailThanhVienBinding.rcvDanhGia.setLayoutManager(linearLayoutManager);
        aDetailChuyenXeViewModel.renderAdapter(getApplicationContext(),id);
        detailThanhVienBinding.rcvDanhGia.setAdapter(aDetailChuyenXeViewModel.getDanhGiaAdapter());
    }
}