package com.example.myapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.databinding.FragmentFilterBinding;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;
import com.example.myapplication.model.DAO.ChuyenXeDAO_Impl;
import com.example.myapplication.viewmodel.FilterViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterFragment extends Fragment {
    FragmentFilterBinding fragmentFilterBinding;
    FilterViewModel filterViewModel = new FilterViewModel();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFilterBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_filter,container,false);
        fragmentFilterBinding.setFilterViewModel(filterViewModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        fragmentFilterBinding.rcvFilterChuyenXe.setLayoutManager(linearLayoutManager);
        filterViewModel.renderAdapter(getContext());
        fragmentFilterBinding.rcvFilterChuyenXe.setAdapter(filterViewModel.getChuyenXeFilterAdapter());

        ArrayAdapter<String> adapterLoaiXe = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, filterViewModel.getListTenLoaiXe(getContext()));
        fragmentFilterBinding.spinnerLoaiXe.setAdapter(adapterLoaiXe);

        // Lắng nghe sự kiện khi người dùng chọn một mục trong Spinner LoaiXe
        fragmentFilterBinding.spinnerLoaiXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy giá trị loaiXe từ Spinner sau khi người dùng chọn
                String loaiXe = parent.getItemAtPosition(position).toString();
               filterViewModel.setLoaiXe(loaiXe);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có mục nào được chọn (nếu cần)
            }
        });

        // Tạo adapter và thiết lập cho Spinner DiaDiemDi
        ArrayAdapter<String> adapterDiaDiemDi = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, filterViewModel.getListDiaDiemDi(getContext()));
        fragmentFilterBinding.spinnerDiaDiemDi.setAdapter(adapterDiaDiemDi);

        // Lắng nghe sự kiện khi người dùng chọn một mục trong Spinner DiaDiemDi
        fragmentFilterBinding.spinnerDiaDiemDi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy giá trị diaDiemDi từ Spinner sau khi người dùng chọn
                String diaDiemDi = parent.getItemAtPosition(position).toString();
                filterViewModel.setDiaDiemDi(diaDiemDi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có mục nào được chọn (nếu cần)
            }
        });
        // Tạo adapter và thiết lập cho Spinner DiaDiemDen
        ArrayAdapter<String> adapterDiaDiemDen = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, filterViewModel.getListDiaDiemDen(getContext()));
        fragmentFilterBinding.spinnerDiaDiemDen.setAdapter(adapterDiaDiemDen);

        // Lắng nghe sự kiện khi người dùng chọn một mục trong Spinner DiaDiemDen
        fragmentFilterBinding.spinnerDiaDiemDen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy giá trị diaDiemDen từ Spinner sau khi người dùng chọn
                String diaDiemDen = parent.getItemAtPosition(position).toString();
               filterViewModel.setDiaDiemDen(diaDiemDen);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có mục nào được chọn (nếu cần)
            }
        });
        fragmentFilterBinding.edtGioDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterViewModel.showTimePickerDialog(getContext());
            }
        });

        // Lắng nghe sự kiện khi người dùng nhấn nút "Lọc"
        fragmentFilterBinding.btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ChuyenXe> chuyenXes = filterViewModel.listFilterChuyenXe(getContext());
                if (chuyenXes.isEmpty()) {
                    filterViewModel.getChuyenXeFilterAdapter().setData(new ArrayList<>());
                    fragmentFilterBinding.tvThongBao.setVisibility(View.VISIBLE); // Hiển thị tvThongBao nếu danh sách rỗng
                    fragmentFilterBinding.tvThongBao.setText("Không tìm thấy chuyến xe"); // Đặt thông báo "Không tìm thấy chuyến xe"
                }else {
                    fragmentFilterBinding.tvThongBao.setVisibility(View.GONE);
                    filterViewModel.getChuyenXeFilterAdapter().setData(chuyenXes);
                }
                hideKeyboard();
            }

        });

        return  fragmentFilterBinding.getRoot();

    }
    private void hideKeyboard() {
        // Lấy đối tượng InputMethodManager từ Context của Fragment
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(getContext().INPUT_METHOD_SERVICE);

        // Kiểm tra xem bàn phím có đang hiển thị không trước khi ẩn
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}