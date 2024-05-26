package com.example.myapplication.view;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.databinding.FragmentUpdateThanhVienBinding;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.viewmodel.UpdateThanhVienViewModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class UpdateThanhVienFragment extends Fragment {

    private FragmentUpdateThanhVienBinding fragmentUpdateThanhVienBinding;
    private UpdateThanhVienViewModel updateThanhVienViewModel = new UpdateThanhVienViewModel();

    private ActivityResultLauncher<Intent> imagePickerLauncher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentUpdateThanhVienBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_thanh_vien, container, false);
        fragmentUpdateThanhVienBinding.setUpdateThanhVienViewModel(updateThanhVienViewModel);
        Bundle bundle = getArguments();


        fragmentUpdateThanhVienBinding.edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateThanhVienViewModel.showDatePickerDialog(getContext());
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, updateThanhVienViewModel.getListTenQuyen(getContext()));

        fragmentUpdateThanhVienBinding.spnerTenQuyen.setAdapter(adapter);
        if (bundle != null) {
            ThanhVien thanhVien = (ThanhVien) bundle.getSerializable("thanhVien");
            updateThanhVienViewModel.setDetailThanhVien(thanhVien, getContext());
            fragmentUpdateThanhVienBinding.spnerTenQuyen.setSelection(thanhVien.getIdQuyenThanhVien() - 1);
        }
        fragmentUpdateThanhVienBinding.spnerTenQuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                updateThanhVienViewModel.getIdQuyenThanhVien((String) adapterView.getItemAtPosition(i), getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fragmentUpdateThanhVienBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien thanhVien = (ThanhVien) bundle.getSerializable("thanhVien");
                updateThanhVienViewModel.updateThanhVien(thanhVien, getContext());
            }
        });

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        uploadImageToFireBase(selectedImageUri);
                    }
                }
        );

        FunctionPublic.loadImage(updateThanhVienViewModel.getAvatar(),fragmentUpdateThanhVienBinding.avatarImg,getContext());

        fragmentUpdateThanhVienBinding.avatarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePickerLauncher.launch(intent);
            }
        });
        // Inflate the layout for this fragment
        return fragmentUpdateThanhVienBinding.getRoot();
    }

    public void uploadImageToFireBase(Uri uri){
        // Tham chiếu đến Firebase Storage
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        // Tạo tên file duy nhất cho hình ảnh (ví dụ: sử dụng thời gian hiện tại làm tên file)
        String fileName = System.currentTimeMillis() + ".jpg";

        // Tạo tham chiếu đến file trên Firebase Storage
        StorageReference imageRef = storageRef.child("images/" + fileName);

        // Upload hình ảnh lên Firebase Storage
        imageRef.putFile(uri)
                .addOnProgressListener(snapshot -> {
                    Toast.makeText(getContext(), "Đang tải ảnh ", Toast.LENGTH_LONG).show();
                })
                .addOnSuccessListener(taskSnapshot -> {
                    // Upload thành công, tiến hành hiển thị hình ảnh
                    displayImageFromFirebaseStorage(imageRef);
                    Toast.makeText(getContext(), "Tải ảnh thành công ", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> {
                    // Upload thất bại, xử lý lỗi tại đây (nếu cần)
                });
        // Gọi phương thức xử lý ảnh được chọn

    }

    private void displayImageFromFirebaseStorage(StorageReference imageRef) {
        // Lấy URL của hình ảnh đã tải lên từ Firebase Storage
        imageRef.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    updateThanhVienViewModel.setAvatar(String.valueOf(uri));
                    Glide.with(getContext()).load(updateThanhVienViewModel.getAvatar()).into(fragmentUpdateThanhVienBinding.avatarImg);
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi nếu không thể lấy URL hình ảnh (nếu cần)
                });
    }


}