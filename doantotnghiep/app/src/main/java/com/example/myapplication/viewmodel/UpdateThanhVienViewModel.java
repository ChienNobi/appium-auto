package com.example.myapplication.viewmodel;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
 import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.config.AppDatabase;
import com.example.myapplication.config.FunctionPublic;
import com.example.myapplication.config.VariableGlobal;
import com.example.myapplication.model.DAO.QuyenDao;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.view.ListThanhVienFragment;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UpdateThanhVienViewModel extends BaseObservable {
    private String tenDangNhap;
    private String ho;
    private String ten;
    private String matKhau;
    private int idQuyen;
    private String soDienThoai;
    private String email;
    private String avatar;
    private String ngaySinh;


    public void setDetailThanhVien(ThanhVien thanhVien, Context context){


        this.setTenDangNhap(thanhVien.getTenDangNhap());
        this.setHo(thanhVien.getHo());
        this.setTen(thanhVien.getTen());
        this.setMatKhau(thanhVien.getMatKhau());
        this.setSoDienThoai(thanhVien.getSoDienThoai());
        this.setEmail(thanhVien.getEmail());
        this.setNgaySinh(thanhVien.getNgaySinh());
        this.setAvatar(thanhVien.getAvatar());
    }

    public void updateThanhVien(ThanhVien thanhVien, Context context){
        if (!FunctionPublic.isTenDangNhapValid(tenDangNhap)){
            Toast.makeText(context, "Tên đăng nhập sai định dạng, hãy viết liền không dấu", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!FunctionPublic.isPasswordValid(matKhau)){
            Toast.makeText(context, "Độ dài mật khẩu phải lớn hơn hoặc bằng 5 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!FunctionPublic.isEmailValid(email)){
            Toast.makeText(context, "Email sai định dạng, example@gmail.com", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(context).getThanhVienDAO();
            Date date = new Date();
            thanhVien.setTen(getTen());
            thanhVien.setHo(getHo());
            thanhVien.setMatKhau(getMatKhau());
            thanhVien.setIdQuyenThanhVien(getIdQuyen());
            thanhVien.setSoDienThoai(getSoDienThoai());
            thanhVien.setEmail(getEmail());
            thanhVien.setNgaySinh(getNgaySinh());
            thanhVien.setAvatar(getAvatar());
            thanhVien.setNgayCapNhat(VariableGlobal.dateFormat.format(date));

            thanhVienDAO.updateThanhVien(thanhVien);

            Toast.makeText(context, "Chỉnh sửa thành công", Toast.LENGTH_LONG).show();

            goToListFragment(context);
        }
    }
    public void goToListFragment(Context context){
        ListThanhVienFragment listThanhVienFragment =new ListThanhVienFragment();
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerThanhVienManager, listThanhVienFragment);
        fragmentTransaction.commit();
    }



    public void showDatePickerDialog(Context context) {
        // Lấy ngày hiện tại để hiển thị trong DatePicker
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Giới hạn ngày tối đa là ngày hiện tại
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Xử lý ngày được chọn bởi người dùng ở đây
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        // Hiển thị ngày đã chọn trong TextView
                        setNgaySinh(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Giới hạn DatePickerDialog không cho phép chọn các ngày trong tương lai
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

    public int getIdQuyenThanhVien(String tenQuyen, Context context){
        int id = AppDatabase.getInstance(context).getQuyenDAO().getIdQuyen(tenQuyen);
        setIdQuyen(id);
        return id;
    }
    public List<String> getListTenQuyen(Context context){
        List<String> tenQuyen = AppDatabase.getInstance(context).getQuyenDAO().getTenQuyen();
        return tenQuyen;
    }

    @Bindable
    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
        notifyPropertyChanged(BR.ngaySinh);
    }

    @Bindable
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
        notifyPropertyChanged(BR.tenDangNhap);
    }
    @Bindable
    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
        notifyPropertyChanged(BR.ho);
    }
    @Bindable
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
        notifyPropertyChanged(BR.ten);
    }
    @Bindable
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
        notifyPropertyChanged(BR.matKhau);
    }
    @Bindable
    public int getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(int idQuyen) {
        this.idQuyen = idQuyen;
        notifyPropertyChanged(BR.idQuyen);
    }
    @Bindable
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
        notifyPropertyChanged(BR.soDienThoai);
    }
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        notifyPropertyChanged(BR.email);
        this.email = email;
    }
    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }
}
