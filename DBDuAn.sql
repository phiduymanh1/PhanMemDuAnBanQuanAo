-- Tạo cơ sở dữ liệu
CREATE DATABASE QuanLyTiemQuanAo;

-- Sử dụng cơ sở dữ liệu
USE QuanLyTiemQuanAo;

-- Tạo bảng Chức Vụ
CREATE TABLE ChucVu (
    MaCV VARCHAR(10) PRIMARY KEY,
    TenCV NVARCHAR(255) NOT NULL
);

-- Chèn dữ liệu vào bảng Chức Vụ
INSERT INTO ChucVu (MaCV, TenCV)
VALUES
('CV001', N'Quản Lý'),
('CV002', N'Nhân Viên');

-- Tạo bảng MauSac
CREATE TABLE MauSac (
    MaMau VARCHAR(10) PRIMARY KEY,
    TenMau NVARCHAR(50) 
);

-- Chèn dữ liệu vào bảng MauSac
INSERT INTO MauSac (MaMau, TenMau)
VALUES
('M001', N'Xanh'),
('M002', N'Đỏ'),
('M003', N'Vàng'),
('M004', N'Tím'),
('M005', N'Đen'),
('M006', N'Tráng'),
('M007', N'Cam');

-- Tạo bảng Kích Cỡ
CREATE TABLE KichCo (
    MaKichCo VARCHAR(10) PRIMARY KEY,
    TenKichCo NVARCHAR(50) 
);

-- Chèn dữ liệu vào bảng Kích Cỡ
INSERT INTO KichCo (MaKichCo, TenKichCo)
VALUES
('KC001', 'S'),
('KC002', 'M'),
('KC003', 'L'),
('KC004', 'XL'),
('KC005', 'XXL');

-- Tạo bảng Thương Hiệu
CREATE TABLE ThuongHieu (
    MaThuongHieu VARCHAR(10) PRIMARY KEY,
    TenThuongHieu NVARCHAR(255) 
);

-- Chèn dữ liệu vào bảng Thương Hiệu
INSERT INTO ThuongHieu (MaThuongHieu, TenThuongHieu)
VALUES
('TH001', N'Levis'),
('TH002', N'Nike'),
('TH003', N'Adidas'),
('TH004', N'Puma'),
('TH005', N'H&M');
-- Tạo bảng Sản Phẩm
CREATE TABLE SanPham (
    MaSP VARCHAR(10) PRIMARY KEY,
    TenSanPham NVARCHAR(255) ,
    MoTa NVARCHAR(255),
    Gia DECIMAL(10, 2) ,
    SoLuongTonKho INT ,
    NgayTao DATE ,
    TrangThai NVARCHAR(50) ,
    DonViTinh VARCHAR(50),
    MaMau VARCHAR(10),
    MaKichCo VARCHAR(10),
    MaThuongHieu VARCHAR(10),
    FOREIGN KEY (MaMau) REFERENCES MauSac(MaMau),
    FOREIGN KEY (MaKichCo) REFERENCES KichCo(MaKichCo),
    FOREIGN KEY (MaThuongHieu) REFERENCES ThuongHieu(MaThuongHieu)
);
-- Chèn dữ liệu vào bảng SanPham
INSERT INTO SanPham (MaSP, TenSanPham, MoTa, Gia, SoLuongTonKho, MaThuongHieu, MaMau, MaKichCo, NgayTao, TrangThai, DonViTinh)
VALUES
('SP001', N'Quần Jean',  N'Quần Jean Siêu Đẹp', 3000000.00, 100, 'TH001', 'M001', 'KC003', '2024-08-01', N'Còn Hàng', N'Cái'),
('SP002', N'Áo Thun',  N'Áo Thun Siêu Bền', 280000.00, 100, 'TH002', 'M005', 'KC002', '2024-08-02', N'Còn Hàng', N'Cái');

-- Tạo bảng Khách Hàng
CREATE TABLE KhachHang (
    MaKH VARCHAR(10) PRIMARY KEY,
    TenKhachHang NVARCHAR(255) ,
    Email NVARCHAR(255) ,
    DiaChi NVARCHAR(255),
    SoDienThoai NVARCHAR(10),
    NgayTao DATE ,
    GioiTinh NVARCHAR(10)
);

-- Chèn dữ liệu vào bảng Khách Hàng
INSERT INTO KhachHang (MaKH, TenKhachHang, Email, DiaChi, SoDienThoai, NgayTao, GioiTinh)
VALUES
('KH001', N'Nguyễn Văn A', 'a@gmail.com', N'123 Đường A', '0123456789', '2024-08-06', N'Nam'),
('KH002', N'Trần Thị B', 'b@gmail.com', N'456 Đường B', '0987654321', '2024-08-03', N'Nữ'),
('KH003', N'Trần Văn C', 'c@gmail.com', N'785 Đường C', '0987847390', '2024-08-02', N'Nam');

-- Tạo bảng Nhân Viên (Gộp với Quản Lý)
CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    TenNV NVARCHAR(255) ,
    Email NVARCHAR(255) ,
    MatKhau VARCHAR(255) ,
    SoDienThoai VARCHAR(10) ,
    GioiTinh NVARCHAR(10),
    MaCV VARCHAR(10),
    FOREIGN KEY (MaCV) REFERENCES ChucVu(MaCV)
);

-- Chèn dữ liệu vào bảng Nhân Viên
INSERT INTO NhanVien (MaNV, TenNV, Email, MatKhau, SoDienThoai, GioiTinh, MaCV)
VALUES
('NV001', N'Nguyễn Văn E', 'nguyenvane@gmail.com', 'nguyenvane123', '0123456789', N'Nam', 'CV002'),
('NV002', N'Nguyễn Thị F', 'nguyenthif@gmail.com', 'nguyenthif123', '0987654321', N'Nữ', 'CV002'),
('NV003', N'Le Thi H', 'lethih@gmail.com', 'lethih123', '0928372728', N'Nữ', 'CV002'),
('QL001', N'Nguyễn Trung H', 'ntrungh@gmail.com', 'ntrungh123', '0123456789', N'Nam', 'CV001'),
('QL002', N'Nguyễn Thị D', 'nguyenthid@gmail.com', 'nguyenthid123', '0987654321', N'Nữ', 'CV001');

-- Tạo bảng Khuyến Mãi
CREATE TABLE KhuyenMai (
    MaKM VARCHAR(10) PRIMARY KEY,
    TenKhuyenMai NVARCHAR(255) ,
    MoTa NVARCHAR(225),
    LoaiKhuyenMai NVARCHAR(225) ,
    GiaTri DECIMAL(10, 2) ,
    NgayBatDau DATE ,
    NgayKetThuc DATE ,
    TrangThai NVARCHAR(20) 
);

-- Chèn dữ liệu vào bảng Khuyến Mãi
INSERT INTO KhuyenMai (MaKM, TenKhuyenMai, MoTa, LoaiKhuyenMai, GiaTri, NgayBatDau, NgayKetThuc, TrangThai)
VALUES
('KM001', N'Không Có', N'Giảm 0K', 'tienmat', 0, '2024-08-01', '2025-01-01', N'Hoạt Động'),
('KM002', N'Khuyến Mãi 20%', N'Giảm 20%', 'phantram', 20.00, '2024-08-01', '2025-01-01', N'Hoạt Động'),
('KM003', N'Khuyến Mãi 50%', N'Giảm 50%', 'phantram', 50.00, '2024-08-01', '2024-08-13', N'Hết Hạn'),
('KM004', N'Khuyến Mãi 50K', N'Giảm 50k', 'tienmat', 50000.00, '2024-08-01', '2024-08-13', N'Hoạt Động');


CREATE TABLE HoaDon (
    MaHD VARCHAR(10) PRIMARY KEY,
    TenKH NVARCHAR(50),
    TenNV NVARCHAR(50),
    NgayTao DATE,
    TongTien DECIMAL(10, 2),   -- Tổng tiền trước giảm giá
    GiamGia DECIMAL(10, 2),    -- Giảm giá cho toàn bộ hóa đơn
    TrangThaiHoaDon NVARCHAR(50),
    PhuongThucThanhToan NVARCHAR(50),
    NgayThanhToan DATE,
    TienKhachDua DECIMAL(10, 2),
    TienThua DECIMAL(10, 2)
);
-- Chèn dữ liệu mẫu vào bảng Hóa Đơn
INSERT INTO HoaDon (MaHD, TenKH, TenNV, NgayTao, TongTien, GiamGia, TrangThaiHoaDon, PhuongThucThanhToan, NgayThanhToan, TienKhachDua, TienThua)
VALUES
('HD001', 'Nguyen Van A', 'Tran Van B', '2024-08-01', 3000000.00, 600000.00, 'ChoThanhToan', 'TheTinDung', '2024-08-01', 2400000.00, 0.00),
('HD002', 'Le Thi C', 'Nguyen Thi D', '2024-08-02', 280000.00, 50000.00, 'DaThanhToan', 'ChuyenKhoan', '2024-08-01', 230000.00, 0.00);

-- Tạo lại bảng Chi Tiết Hóa Đơn mà không có cột ThanhTien
CREATE TABLE ChiTietHoaDon (
    MaCTHD INT  PRIMARY KEY IDENTITY,
    HoaDonID VARCHAR(10),
    SanPhamID VARCHAR(10),
    SoLuong INT,
    GiaBan DECIMAL(10, 2),  -- Giá bán của từng sản phẩm
    FOREIGN KEY (HoaDonID) REFERENCES HoaDon(MaHD),
    FOREIGN KEY (SanPhamID) REFERENCES SanPham(MaSP)
);


-- Chèn dữ liệu vào bảng Chi Tiết Hóa Đơn
INSERT INTO ChiTietHoaDon ( HoaDonID, SanPhamID, SoLuong, GiaBan)
VALUES
( 'HD001', 'SP001', 1, 3000000.00),
( 'HD002', 'SP002', 1, 280000.00);