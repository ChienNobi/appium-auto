package com.example.myapplication.config;


import android.app.Application;
import android.util.Log;

import com.example.myapplication.model.ChuyenXe;
import com.example.myapplication.model.DAO.ChuyenXeDAO;

import com.example.myapplication.model.DAO.DanhGiaDAO;
import com.example.myapplication.model.DAO.LoaiXeDAO;
import com.example.myapplication.model.DAO.QuyenDao;
import com.example.myapplication.model.DAO.ThanhVienDAO;
import com.example.myapplication.model.DAO.TrangThaiDAO;
import com.example.myapplication.model.DanhGia;
import com.example.myapplication.model.LoaiXe;
import com.example.myapplication.model.Quyen;
import com.example.myapplication.model.ThanhVien;
import com.example.myapplication.model.TrangThai;
import com.google.firebase.FirebaseApp;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());

        AppDatabase.getInstance(getApplicationContext());

        FirebaseApp.initializeApp(getApplicationContext());





        if (!DataLocalManager.getIsFirst())
        {
            ThanhVienDAO thanhVienDAO = AppDatabase.getInstance(getApplicationContext()).getThanhVienDAO();
            QuyenDao quyenDao = AppDatabase.getInstance(getApplicationContext()).getQuyenDAO();
            Quyen q1 = new Quyen("ROLE_ADMIN");
            Quyen q2 = new Quyen("ROLE_MEMBER");
            Quyen q3 = new Quyen("ROLE_CUSTOMER");

            quyenDao.insert(q1,q2,q3);
            ThanhVien t1 = new ThanhVien("thaigiavuong", "giavuong.1205@gmail.com");
            t1.setMatKhau("admin");
            t1.setSoDienThoai("0912309812");
            t1.setIdQuyenThanhVien(1);
            t1.setAvatar("https://f9-zpcloud.zdn.vn/9192093310976170948/a4fd78baa13c7062292d.jpg");

            ThanhVien t2 = new ThanhVien("dinhtanhuy", "dinhtanhuy@gmail.com");
            t2.setMatKhau("admin");
            t2.setSoDienThoai("12345678");
            t2.setIdQuyenThanhVien(1);
            t2.setAvatar("https://f9-zpcloud.zdn.vn/9192093310976170948/a4fd78baa13c7062292d.jpg");

            ThanhVien t3 = new ThanhVien("ntngoc", "ntngoc@gmail.com");
            t3.setMatKhau("123456");
            t3.setSoDienThoai("0245976532");
            t3.setIdQuyenThanhVien(1);
            t3.setAvatar("https://caydothi.vn/upload/administrator/image/2.jpg");

            ThanhVien t4 = new ThanhVien("khachhang", "khachhang.1205@gmail.com");
            t4.setMatKhau("khachhang");
            t4.setSoDienThoai("908734098");
            t4.setIdQuyenThanhVien(3);
            t4.setAvatar("https://f9-zpcloud.zdn.vn/9192093310976170948/a4fd78baa13c7062292d.jpg");

            ThanhVien t5 = new ThanhVien("nhanvien", "nhanvien@gmail.com");
            t5.setMatKhau("nhanvien");
            t5.setSoDienThoai("1230948029");
            t5.setIdQuyenThanhVien(2);
            t5.setAvatar("https://f9-zpcloud.zdn.vn/3275679253552964365/8521a938356ce532bc7d.jpg");

            ThanhVien t6 = new ThanhVien("ngocnt", "ngocnt@gmail.com");
            t6.setTen("Ngoc");
            t6.setHo("Nguyen");
            t6.setMatKhau("123456");
            t6.setSoDienThoai("0538592386");
            t6.setIdQuyenThanhVien(3);
            t6.setAvatar("https://cdn.tuoitre.vn/thumb_w/480/2019/9/13/dead-tree-1-15683444579521989065752.jpg");

            ThanhVien t7 = new ThanhVien("test1", "test1@gmail.com");
            t7.setMatKhau("123456");
            t7.setSoDienThoai("0538596253");
            t7.setIdQuyenThanhVien(3);
            t7.setAvatar("https://tindep.com/wp-content/uploads/2023/03/hinh-nen-cay-xanh-tuoi-dep-cang-tran-nhua-song-51.jpg");

            ThanhVien t8 = new ThanhVien("test2", "test2@gmail.com");
            t8.setMatKhau("123456");
            t8.setSoDienThoai("0539243253");
            t8.setIdQuyenThanhVien(3);
            t8.setAvatar("https://gcs.tripi.vn/public-tripi/tripi-feed/img/474074Nvw/nhung-cau-noi-hay-ve-cay-xanh_045009869.jpg");

            ThanhVien t9 = new ThanhVien("test3", "test3@gmail.com");
            t9.setMatKhau("123456");
            t9.setSoDienThoai("0192243253");
            t9.setIdQuyenThanhVien(3);
            t9.setAvatar("https://vuonannam.com/wp-content/uploads/2023/02/cay-canh-trong-nha-11-1.jpg");

            ThanhVien t10 = new ThanhVien("test4", "test4@gmail.com");
            t10.setMatKhau("123456");
            t10.setSoDienThoai("0192203452");
            t10.setIdQuyenThanhVien(3);
            t10.setAvatar("https://upload.wikimedia.org/wikipedia/commons/a/a1/Hoa_gi%E1%BA%A5y_c%E1%BB%95_th%E1%BB%A5.jpg");


            thanhVienDAO.insertAll(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10);


            ChuyenXeDAO chuyenXeDAO = AppDatabase.getInstance(getApplicationContext()).getChuyenXeDAO();
            LoaiXeDAO loaiXeDAO = AppDatabase.getInstance(getApplicationContext()).getLoaiXeDAO();
            LoaiXe loaiXe1 = new LoaiXe("Ford Transit",16);
            LoaiXe loaiXe2 = new LoaiXe("Thaco Kinglong",35);
            LoaiXe loaiXe3 = new LoaiXe("Mercedes Sprinter Limousine",16);
            LoaiXe loaiXe4 = new LoaiXe("Luxury Limousine",20);
            LoaiXe loaiXe5 = new LoaiXe("Sakura",40);
            LoaiXe loaiXe6 = new LoaiXe("Huyndai",28);
            LoaiXe loaiXe7 = new LoaiXe("Long Limousine",34);
            LoaiXe loaiXe8 = new LoaiXe("Travel Bus",45);
            LoaiXe loaiXe9 = new LoaiXe("Mercedes VIP",12);
            LoaiXe loaiXe10 = new LoaiXe("Transport Vehicle",10);
            loaiXeDAO.insert(loaiXe1,loaiXe2,loaiXe3,loaiXe4,loaiXe5,loaiXe6,loaiXe7,loaiXe8,loaiXe9,loaiXe10);


            ChuyenXe chuyenXe1 = new ChuyenXe("TP.Hồ Chí Minh - TP.Vũng Tàu","null","8h00","12h00","Bến xe Thành phố 1","Bến xe Vũng Tàu",500000.00,"mô tả");
            chuyenXe1.setIdLoaiXe(1);
            chuyenXe1.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/doanandroid-c9b13.appspot.com/o/xe_35_cho.jpg?alt=media&token=89119ebf-e6a8-4c0a-af7e-0b3fac672114");
            ChuyenXe chuyenXe2 = new ChuyenXe("TP.Cần Thơ - Nha Trang","null","9h00","12h00","Bến xe Miền Tây","Bến xe Nha Trang",450000.00,"mô tả");
            chuyenXe2.setIdLoaiXe(2);
            chuyenXe2.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/doanandroid-c9b13.appspot.com/o/xe_35_cho.jpg?alt=media&token=89119ebf-e6a8-4c0a-af7e-0b3fac672114");
            ChuyenXe chuyenXe3 = new ChuyenXe("TP.Hà Nội - Thanh Hóa","null","10h00","13h00","Bến xe Giáp Bát","Bến xe phía Bắc",300000.00,"mô tả");
            chuyenXe3.setIdLoaiXe(3);
            chuyenXe3.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/doanandroid-c9b13.appspot.com/o/xe_35_cho.jpg?alt=media&token=89119ebf-e6a8-4c0a-af7e-0b3fac672114");
            ChuyenXe chuyenXe4 = new ChuyenXe("TP.Hồ Chí Minh - Quảng Bình","null","11h00","22h00","Bến xe Thành phố 2","Bến xe Quảng Bình",500000.00,"mô tả");
            chuyenXe4.setIdLoaiXe(3);
            chuyenXe4.setHinhAnh("https://firebasestorage.googleapis.com/v0/b/doanandroid-c9b13.appspot.com/o/xe_35_cho.jpg?alt=media&token=89119ebf-e6a8-4c0a-af7e-0b3fac672114");
            ChuyenXe chuyenXe5 = new ChuyenXe("TP.Hà Nội - Hải Dương","null","11h00","14h00","Bến xe Mỹ Đình","Bến xe Hải Dương",200000.00,"mô tả");
            chuyenXe5.setIdLoaiXe(4);
            chuyenXe5.setHinhAnh("https://motogo.vn/wp-content/uploads/2022/04/xe-khach-ha-noi-sapa-19.jpg");
            ChuyenXe chuyenXe6 = new ChuyenXe("Hà Nam - Quảng Ninh","null","8h30","12h00","Bến xe Hà Nam","Bến xe Quảng Ninh",250000.00,"mô tả");
            chuyenXe6.setIdLoaiXe(5);
            chuyenXe6.setHinhAnh("https://motogo.vn/wp-content/uploads/2023/03/xe-limousine-ha-noi-quang-ninh7.jpg");
            ChuyenXe chuyenXe7 = new ChuyenXe("Nghệ An - Thừa Thiên Huế","null","9h30","15h30","Bến xe Nghệ An","Bến xe Thừa Thiên Huế",400000.00,"mô tả");
            chuyenXe7.setIdLoaiXe(6);
            chuyenXe7.setHinhAnh("https://motortrip.vn/wp-content/uploads/2022/01/xe-khach-la-gi-15.jpg");
            ChuyenXe chuyenXe8 = new ChuyenXe("Sơn La - Cao Bằng","null","12h15","16h30","Bến xe Sơn La","Bến xe Cao Bằng",350000.00,"mô tả");
            chuyenXe8.setIdLoaiXe(7);
            chuyenXe8.setHinhAnh("https://otoansuong.vn/upload/tour/xe-khach-daewoo-29-cho310758676424_640x480.jpg");
            ChuyenXe chuyenXe9 = new ChuyenXe("Sa Pa - Hà Nội","null","15h00","19h00","Bến xe Sa Pa","Bến xe Mỹ Đình",350000.00,"mô tả");
            chuyenXe9.setIdLoaiXe(8);
            chuyenXe9.setHinhAnh("https://www.limo24h.vn/wp-content/uploads/2020/05/top-5-nha-xe-limousine-ha-noi-di-hai-phong-chat-luong.jpg");
            ChuyenXe chuyenXe10 = new ChuyenXe("Hà Tĩnh - Hà Nam","null","23h00","6h00","Bến xe Hà Tĩnh","Bến xe Hà Nam",400000.00,"mô tả");
            chuyenXe10.setIdLoaiXe(9);
            chuyenXe10.setHinhAnh("https://shojiki.vn/template/uploads/2022/02/dan-xe-khach-phuong-trang-3-1030x532.jpg");


            chuyenXeDAO.insert(chuyenXe1,chuyenXe2,chuyenXe3,chuyenXe4,chuyenXe5,chuyenXe6,chuyenXe7,chuyenXe8,chuyenXe9,chuyenXe10);


            TrangThai trangThai1 = new TrangThai();
            trangThai1.setTenTrangThai("Đang chờ");
            TrangThai trangThai2 = new TrangThai();
            trangThai2.setTenTrangThai("Đang chờ duyệt");
            TrangThai trangThai3 = new TrangThai();
            trangThai3.setTenTrangThai("Đã hủy");
            TrangThai trangThai4 = new TrangThai();
            trangThai4.setTenTrangThai("Chấp thuận");

            TrangThaiDAO trangThaiDAO = AppDatabase.getInstance(getApplicationContext()).getTrangThaiDAO();
            trangThaiDAO.insert(trangThai1,trangThai2,trangThai3, trangThai4);
            DataLocalManager.setIsFirst(true);
        }

    }

}
