package com.example.myapplication.view;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.databinding.FragmentUpdateChuyenXeBinding;
import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.viewmodel.UpdateChuyenXeViewModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UpdateChuyenXeFragment extends Fragment {
    private FragmentUpdateChuyenXeBinding fragmentUpdateChuyenXeBinding;
    private UpdateChuyenXeViewModel updateChuyenXeViewModel = new UpdateChuyenXeViewModel();
    private ActivityResultLauncher<Intent> imagePickerLauncher;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentUpdateChuyenXeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_chuyen_xe,container,false);
        fragmentUpdateChuyenXeBinding.setUpdateChuyenXeViewModel(updateChuyenXeViewModel);
        Bundle bundle = getArguments();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, updateChuyenXeViewModel.getListTenLoaiXe(getContext()));

        fragmentUpdateChuyenXeBinding.spinnerTenLoaiXe.setAdapter(adapter);
        if (bundle != null) {
            ChuyenXe chuyenXe = (ChuyenXe) bundle.getSerializable("chuyenXe");
            updateChuyenXeViewModel.setDetailChuyenXe(chuyenXe, getContext());
            fragmentUpdateChuyenXeBinding.spinnerTenLoaiXe.setSelection(chuyenXe.getIdLoaiXe()-1);
        }
        fragmentUpdateChuyenXeBinding.spinnerTenLoaiXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                updateChuyenXeViewModel.getIdLoaiXeByName( getContext(),(String) adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        fragmentUpdateChuyenXeBinding.edtThoiGianDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChuyenXeViewModel.showTimePickerDialogDi(getContext());
            }
        });

        fragmentUpdateChuyenXeBinding.edtThoiGianDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChuyenXeViewModel.showTimePickerDialogDen(getContext());
            }
        });

        fragmentUpdateChuyenXeBinding.btnUpdateChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuyenXe chuyenXe = (ChuyenXe) bundle.getSerializable("chuyenXe");
                updateChuyenXeViewModel.UpdateChuyenXe(chuyenXe,getContext());
            }
        });

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        // Tham chiếu đến Firebase Storage
                        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

                        // Tạo tên file duy nhất cho hình ảnh (ví dụ: sử dụng thời gian hiện tại làm tên file)
                        String fileName = System.currentTimeMillis() + ".jpg";

                        // Tạo tham chiếu đến file trên Firebase Storage
                        StorageReference imageRef = storageRef.child("images/" + fileName);

                        // Upload hình ảnh lên Firebase Storage
                        imageRef.putFile(selectedImageUri)
                                .addOnSuccessListener(taskSnapshot -> {
                                    // Upload thành công, tiến hành hiển thị hình ảnh
                                    displayImageFromFirebaseStorage(imageRef);
                                })
                                .addOnFailureListener(e -> {
                                    // Upload thất bại, xử lý lỗi tại đây (nếu cần)
                                });
                        // Gọi phương thức xử lý ảnh được chọn

                    }
                }
        );
        FunctionPublic.loadImage(updateChuyenXeViewModel.getHinhAnh(),fragmentUpdateChuyenXeBinding.imgChuyenXe,getContext());

        fragmentUpdateChuyenXeBinding.imgChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePickerLauncher.launch(intent);
            }
        });

        return fragmentUpdateChuyenXeBinding.getRoot();
    }
    private void displayImageFromFirebaseStorage(StorageReference imageRef) {
        // Lấy URL của hình ảnh đã tải lên từ Firebase Storage
        imageRef.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    updateChuyenXeViewModel.setHinhAnh(String.valueOf(uri));
                    Glide.with(getContext()).load(updateChuyenXeViewModel.getHinhAnh()).into(fragmentUpdateChuyenXeBinding.imgChuyenXe);
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi nếu không thể lấy URL hình ảnh (nếu cần)
                });
    }


}


