package com.example.myapplication.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySignupBinding;
import com.example.myapplication.viewmodel.SignupViewModel;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding activitySignupBinding;
    SignupViewModel signupViewModel = new SignupViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        activitySignupBinding.setSignupViewModel(signupViewModel);
        activitySignupBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signupViewModel.handleSignup(getApplicationContext());

            }
        });


        handleDeleteText();
        handleShowPassword();
    }

    private void handleShowPassword() {
        activitySignupBinding.imgShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPass(activitySignupBinding.txtPassword);
            }
        });
        activitySignupBinding.imgShowPasswordAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPass(activitySignupBinding.txtPasswordAgain);
            }
        });
    }

    private boolean showPass(EditText editText) {
        if (editText.getTransformationMethod() instanceof PasswordTransformationMethod) {
            // Nếu đang ẩn mật khẩu, hiển thị văn bản
            editText.setTransformationMethod(null);
            return true;
        } else {
            // Nếu đang hiển thị văn bản, ẩn mật khẩu
            editText.setTransformationMethod(new PasswordTransformationMethod());
            return false;
        }
    }



    private void handleDeleteText(){
        activitySignupBinding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteText(activitySignupBinding.txtUserName);
            }
        });
        activitySignupBinding.deleteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteText(activitySignupBinding.txtName);
            }
        });
        activitySignupBinding.deleteLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteText(activitySignupBinding.txtLastName);
            }
        });
        activitySignupBinding.deleteNumberPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteText(activitySignupBinding.txtPhoneNumber);
            }
        });
        activitySignupBinding.deleteEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteText(activitySignupBinding.txtEmail);
            }
        });
    }
    private void deleteText(EditText view){
        view.setText("");
    }


}



