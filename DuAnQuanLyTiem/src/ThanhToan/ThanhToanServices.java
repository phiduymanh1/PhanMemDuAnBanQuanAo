/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThanhToan;

import Init.DBConnect;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ThanhToanServices {
    private Connection conn;
    
    public ThanhToanServices(){
        conn = DBConnect.getConnect();
    }
    
    
    public ArrayList<ModeThhanhToan> gethdcho() throws SQLException{
        ArrayList<ModeThhanhToan> list = new ArrayList<>();
        String query = "SELECT MaHD , NgayTao , TrangThaiHoaDon FROM HoaDon WHERE TrangThaiHoaDon LIKE N'ChoThanhToan'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            ModeThhanhToan tt = new ModeThhanhToan();
            tt.setMahd(rs.getString(1));
            tt.setNgaytao(rs.getString(2));
            tt.setTthoadon(rs.getString(3));
            list.add(tt);
        }
        
        return list;
    }
    public void themhdcho(ModeThhanhToan tt) throws SQLException{
        String query = "INSERT INTO HoaDon (MaHD , NgayTao , TrangThaiHoaDon) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, tt.getMahd());
        ps.setString(2, tt.getNgaytao());
        ps.setString(3, tt.getTthoadon());
        ps.execute();
        
    }
    public ArrayList<ModeThhanhToan> loaddatasp() throws SQLException{
        ArrayList<ModeThhanhToan> list = new ArrayList<>();
        String query =  "SELECT sp.MaSP, sp.TenSanPham ,ms.TenMau , kc.TenKichCo , th.TenThuongHieu, sp.MoTa , sp.Gia , sp.SoLuongTonKho ,sp.DonViTinh , sp.TrangThai  FROM SanPham sp\n" +
                        "LEFT JOIN MauSac ms ON sp.MaMau = ms.MaMau\n" +
                        "LEFT JOIN ThuongHieu th ON th.MaThuongHieu = sp.MaThuongHieu\n" +
                        "LEFT JOIN KichCo kc ON kc.MaKichCo = sp.MaKichCo";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ModeThhanhToan tt = new ModeThhanhToan();
            tt.setMaSP(rs.getString(1));
            tt.setTensanpham(rs.getString(2));
            tt.setTenmau(rs.getString(3));
            tt.setTenkc(rs.getString(4));
            tt.setTenth(rs.getString(5));
            tt.setMota(rs.getString(6));
            tt.setGia(rs.getInt(7));
            tt.setSoluongtonkho(rs.getInt(8));
            tt.setDonvitinh(rs.getString(9));
            tt.setTrangthai(rs.getString(10));
            
            list.add(tt);
        }
        
        return list;
    }
    public ArrayList<ModeThhanhToan> getvocher() throws SQLException{
        ArrayList<ModeThhanhToan> list = new ArrayList<>();
        String query = "SELECT TenKhuyenMai , LoaiKhuyenMai , GiaTri FROM KhuyenMai WHERE TrangThai LIKE N'Hoạt Động'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            ModeThhanhToan tt = new ModeThhanhToan();
            tt.setTenkm(rs.getString(1));
            tt.setLoaikm(rs.getString(2));
            tt.setGiatrikm(rs.getInt(3));
            
            list.add(tt);
        }
        
        return list;
    }
//    public ArrayList<ModeThhanhToan> laymact() throws SQLException{
//        ArrayList<ModeThhanhToan> list = new ArrayList<>();
//        String qeury = "SELECT MaCTHD FROM ChiTietHoaDon";
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery(qeury);
//        while (rs.next()) {
//            ModeThhanhToan tt = new ModeThhanhToan();
//            tt.setMacthd(rs.getString(1));
//            
//            list.add(tt);
//        }
//                
//        return list;
//    }
    
    public void thanhtoan(ModeThhanhToan tt){
        String query = "UPDATE HoaDon SET TenKH = ?  , TenNV = ? , TongTien = ? , GiamGia = ? , PhuongThucThanhToan = ? , NgayThanhToan = ? , TienKhachDua = ?, TienThua = ? WHERE MaHD = ?";
        
    }
    
    public void themct(ModeThhanhToan tt){
        String query = "";
    }
    
    
    
    
    
    //    public ArrayList<ModeThhanhToan> getdata() throws SQLException{
    //        ArrayList<ModeThhanhToan> list = new ArrayList<>();
    //        String query = "SELECT MaHD , MaKH , MaNV , NgayTao , TongTien , TienKhachDua , TienThua , NgayThanhToan , PhuongThucThanhToan , TrangThaiHoaDon FROM HoaDon";
    //        PreparedStatement ps = conn.prepareStatement(query);
    //        ResultSet rs = ps.executeQuery();
    //        while (rs.next()) {
    //            ModeThhanhToan tt = new ModeThhanhToan();
    //            ps.setString(1, tt.getMahd());
    //            ps.setString(2, tt.getMakh());
    //            ps.setString(3, tt.getManv());
    //            ps.setString(4, tt.getNgaytao());
    //            ps.setInt(5, tt.getTongtien());
    //            ps.setInt(6, tt.getTienkhachdua());
    //            ps.setInt(7, tt.getTienthua());
    //            ps.setString(8, tt.getNgaythanhtoan());
    //            ps.setString(9, tt.getPtthanhtoan());
    //            ps.setString(10, tt.getTthoadon());
    //            
    //            list.add(tt);
    //        }
    //        
    //        return list;
    //    }
    //    
    //    public void them(ModeThhanhToan tt) throws SQLException{
    //        ArrayList<ModeThhanhToan> list = new ArrayList<>();
    //        String query =  "INSERT INTO HoaDon (MaHD , MaKH , MaNV , NgayTao , TongTien , TienKhachDua , TienThua , NgayThanhToan , PhuongThucThanhToan , TrangThaiHoaDon)\n" +
    //                        "VALUES (? , ? , ? , ? , ? ,? ,?,?,?,?)";
    //        PreparedStatement ps = conn.prepareStatement(query);
    //        ResultSet rs = ps.executeQuery();
    //        ps.setString(0, query);
    //        
    //    }
}
