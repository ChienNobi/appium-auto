package com.example.myapplication.view;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddChuyenXeBinding;
import com.example.myapplication.viewmodel.AddChuyenXeViewModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddChuyenXeFragment extends Fragment {
    FragmentAddChuyenXeBinding fragmentAddChuyenXeBinding;
    AddChuyenXeViewModel addChuyenXeViewModel = new AddChuyenXeViewModel();
    private ActivityResultLauncher<Intent> imagePickerLauncher;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentAddChuyenXeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_chuyen_xe,container,false);
        fragmentAddChuyenXeBinding.setAddChuyenXeViewModel(addChuyenXeViewModel);

            fragmentAddChuyenXeBinding.edtThoiGianBatDau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addChuyenXeViewModel.showTimePickerDialogDi(getContext());
                }
            });

        fragmentAddChuyenXeBinding.edtThoiGianDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChuyenXeViewModel.showTimePickerDialogDen(getContext());
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, addChuyenXeViewModel.getListTenLoaiXe(getContext()));

        fragmentAddChuyenXeBinding.spinnerTenLoaiXe.setAdapter(adapter);

        fragmentAddChuyenXeBinding.spinnerTenLoaiXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                addChuyenXeViewModel.getIdLoaiXeByName(getContext(), (String) adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fragmentAddChuyenXeBinding.btnThemChuyenXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChuyenXeViewModel.AddChuyenXe(getContext());
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

        fragmentAddChuyenXeBinding.edtHinhAnhXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePickerLauncher.launch(intent);
            }
        });

        return fragmentAddChuyenXeBinding.getRoot();
    }
    private void displayImageFromFirebaseStorage(StorageReference imageRef) {
        // Lấy URL của hình ảnh đã tải lên từ Firebase Storage
        imageRef.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    fragmentAddChuyenXeBinding.edtHinhAnhXe.setText(String.valueOf(uri));
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi nếu không thể lấy URL hình ảnh (nếu cần)
                });
    }

}


