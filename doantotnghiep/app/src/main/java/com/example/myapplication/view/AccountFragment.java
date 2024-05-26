package com.example.myapplication.view;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.DataLocalManager;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.config.VariableGlobal;
import com.example.myapplication.databinding.FragmentAccountBinding;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.viewmodel.AccountViewModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AccountFragment extends Fragment {
    private FragmentAccountBinding fragmentAccountBinding;
    AccountViewModel accountViewModel = new AccountViewModel();
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAccountBinding = FragmentAccountBinding.inflate(inflater,container, false );

        fragmentAccountBinding.setAccountViewModel(accountViewModel);

        ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(getContext()).getThanhVienDAO();



        ThanhVien thanhVien = thanhVienDAO.getThanhVienByUserName(DataLocalManager.getNameUser());

        accountViewModel.showAccount(thanhVien, getContext());
        FunctionPublic.loadImage(accountViewModel.getAvatar(),fragmentAccountBinding.avatarImg,getContext());

        fragmentAccountBinding.btnUpdateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountViewModel.updateAccount(getContext());
            }
        });

        fragmentAccountBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
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

        fragmentAccountBinding.avatarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePickerLauncher.launch(intent);
            }
        });


        return fragmentAccountBinding.getRoot();



    }




    private void Logout() {
        DataLocalManager.setIsLogin(false);
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
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
                    accountViewModel.setAvatar(String.valueOf(uri));
                    Glide.with(getContext()).load(accountViewModel.getAvatar()).into(fragmentAccountBinding.avatarImg);
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi nếu không thể lấy URL hình ảnh (nếu cần)
                });
    }

}