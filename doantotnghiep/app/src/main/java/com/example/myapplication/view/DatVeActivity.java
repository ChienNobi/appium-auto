package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.config.VariableGlobal;
import com.example.myapplication.databinding.ActivityDatVeBinding;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.viewmodel.DatVeViewModel;

import java.text.ParseException;
import java.util.Calendar;

public class DatVeActivity extends AppCompatActivity {

    ActivityDatVeBinding activityDatVeBinding;
    DatVeViewModel datVeViewModel = new DatVeViewModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDatVeBinding = DataBindingUtil.setContentView(this, R.layout.activity_dat_ve);
        activityDatVeBinding.setDatVeViewModel(datVeViewModel);

        if (getIntent()!= null){
            ChuyenXe chuyenXe = (ChuyenXe) getIntent().getExtras().getSerializable("chuyen_xe");

            datVeViewModel.xulyThongTin(chuyenXe,getApplicationContext());

            activityDatVeBinding.xacNhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showAlert(chuyenXe);
                }
            });

            ArrayAdapter<Integer> listSoLuongVe = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,datVeViewModel.setSpinner(getBaseContext(),chuyenXe));

            activityDatVeBinding.soLuongVe.setAdapter(listSoLuongVe);

            activityDatVeBinding.soLuongVe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    datVeViewModel.setSoLuongVe(String.valueOf(i+1));

                    double tongTien = FunctionPublic.tinhTongTien(Integer.parseInt(datVeViewModel.getSoLuongVe()) , chuyenXe.getGiaTien());

                    datVeViewModel.setTongTien(FunctionPublic.formatMoney(tongTien));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }



        activityDatVeBinding.edtNgayDi.setOnClickListener(view -> {

            showDatePickerDialog();
        });

        activityDatVeBinding.khuHoi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    activityDatVeBinding.edtNgayVe.setVisibility(View.VISIBLE);
                }
                else {
                    activityDatVeBinding.edtNgayVe.setVisibility(View.GONE);
                }
            }
        });

        activityDatVeBinding.edtNgayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    showNgayVePickerDialog();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void showDatePickerDialog() {
        // Lấy ngày hiện tại để hiển thị trong DatePicker
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Giới hạn ngày tối đa là ngày hiện tại
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                DatVeActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datVeViewModel.setSelectedCalendarDi(Calendar.getInstance());
                        // Xử lý ngày được chọn bởi người dùng ở đây
                        datVeViewModel.getSelectedCalendarDi().set(year, month, dayOfMonth);
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        // Hiển thị ngày đã chọn trong TextView
                        datVeViewModel.setNgayDi(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Giới hạn DatePickerDialog không cho phép chọn các ngày trong tương lai
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

    public void showNgayVePickerDialog() throws ParseException {
        // Lấy ngày hiện tại để hiển thị trong DatePicker
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Giới hạn ngày tối đa là ngày hiện tại
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                DatVeActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Xử lý ngày được chọn bởi người dùng ở đây
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        // Hiển thị ngày đã chọn trong TextView
                        datVeViewModel.setNgayVe(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        if (datVeViewModel.getNgayDi() != null && !datVeViewModel.getNgayDi().isEmpty()){
            //Giới hạn DatePickerDialog không cho phép chọn các ngày trước ngày hôm nay
            datePickerDialog.getDatePicker().setMinDate(datVeViewModel.getSelectedCalendarDi().getTimeInMillis());
            // Hiển thị DatePickerDialog
            datePickerDialog.show();
        }
        else {
            Toast.makeText(this, "Vui lòng chọn ngày đi" , Toast.LENGTH_SHORT).show();
        }


    }

    public void goToHistoryBook(){
        Intent i = new Intent(DatVeActivity.this, UserManagerActivity.class);
        startActivity(i);

    }

    public void showAlert(ChuyenXe chuyenXe)
    {
        String thongTinLuongDi = "";
        String thongTinLuongVe = "";
        if (datVeViewModel.getNgayDi()==null){
            thongTinLuongDi = "Thông tin lượt đi: \nChưa đặt ngày đi";
        }
        else {
            thongTinLuongDi = "Thông tin lượt đi \nTên chuyến xe: " + chuyenXe.getTenChuyen() +
                    "\nĐịa điểm đi: " + chuyenXe.getDiaDiemDi() + "\nĐịa điểm đến: " + chuyenXe.getDiaDiemDen() +
                    "\nNgày đi: " + datVeViewModel.getNgayDi() + "\nGiờ bắt đầu: " + chuyenXe.getThoiGianBatDau()+
                    "\nSố lượng vé: " + datVeViewModel.getSoLuongVe() + "\nTổng tiền: " + datVeViewModel.getTongTien();
        }
        if (datVeViewModel.getNgayVe()==null){
            thongTinLuongVe = "\n\n\nKhông có khứ hồi";
        }
        else {
            thongTinLuongVe= "\n\n\nThông tin lượt về \nTên chuyến xe: " + chuyenXe.getTenChuyen() +
                    "\nĐịa điểm đi: " + chuyenXe.getDiaDiemDen() + "\nĐịa điểm đến: " + chuyenXe.getDiaDiemDi() +
                    "\nNgày đi: " + datVeViewModel.getNgayVe() + "\nGiờ bắt đầu: " + chuyenXe.getThoiGianKetThuc()+
                    "\nSố lượng vé: " + datVeViewModel.getSoLuongVe() + "\nTổng tiền: " + datVeViewModel.getTongTien();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(DatVeActivity.this);
        builder.setTitle("Xác nhận thông tin");
        builder.setMessage(thongTinLuongDi + thongTinLuongVe);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (datVeViewModel.getNgayDi() == null){
                    Toast.makeText(DatVeActivity.this, "Vui lòng nhập thông tin vé", Toast.LENGTH_LONG).show();
                    dialogInterface.dismiss();
                }
                else {
                    datVeViewModel.luuDatVe(getApplicationContext(), chuyenXe);
                    goToHistoryBook();
                    Toast.makeText(DatVeActivity.this, "Đặt vé thành công, vui lòng chuyển sang tab lịch sử để xem", Toast.LENGTH_LONG).show();
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
}