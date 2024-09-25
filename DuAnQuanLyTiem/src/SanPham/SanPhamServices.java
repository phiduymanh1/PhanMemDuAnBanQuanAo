/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SanPham;

import Init.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class SanPhamServices {
    private Connection conn;
    
    public SanPhamServices(){
        conn = DBConnect.getConnect();
    }
    
    public ArrayList<ModeSanPham> loaddata() throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query =  "SELECT sp.MaSP, sp.TenSanPham ,ms.TenMau , kc.TenKichCo , th.TenThuongHieu, sp.MoTa , sp.Gia , sp.SoLuongTonKho ,sp.DonViTinh, sp.NgayTao , sp.TrangThai  FROM SanPham sp\n" +
                        "LEFT JOIN MauSac ms ON sp.MaMau = ms.MaMau\n" +
                        "LEFT JOIN ThuongHieu th ON th.MaThuongHieu = sp.MaThuongHieu\n" +
                        "LEFT JOIN KichCo kc ON kc.MaKichCo = sp.MaKichCo";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ModeSanPham sp = new ModeSanPham();
            sp.setMaSP(rs.getString(1));
            sp.setTensanpham(rs.getString(2));
            sp.setTenmau(rs.getString(3));
            sp.setTenkc(rs.getString(4));
            sp.setTenth(rs.getString(5));
            sp.setMota(rs.getString(6));
            sp.setGia(rs.getInt(7));
            sp.setSoluongtonkho(rs.getInt(8));
            sp.setDonvitinh(rs.getString(9));
            sp.setNgaytao(rs.getString(10));
            sp.setTrangthai(rs.getString(11));
            
            list.add(sp);
        }
        
        return list;
    }
    public ArrayList<ModeSanPham> gettenmau() throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT TenMau FROM MauSac";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {   
            ModeSanPham sp = new ModeSanPham();
            sp.setTenmaucbb(rs.getString("TenMau"));
            
            list.add(sp);
        }
        
        
        return list;
    }
    public ArrayList<ModeSanPham> gettenkc() throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT TenKichCo FROM KichCo";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {   
            ModeSanPham sp = new ModeSanPham();
            sp.setTenkccbb(rs.getString("TenKichCo"));
            
            list.add(sp);
        }
        
        
        return list;
    }
    public ArrayList<ModeSanPham> gettenth() throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT TenThuongHieu FROM ThuongHieu";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {   
            ModeSanPham sp = new ModeSanPham();
            sp.setTenthcbb(rs.getString("TenThuongHieu"));
            
            list.add(sp);
        }
        
        
        return list;
    }
    public void them(ModeSanPham sp) throws SQLException{
        String query =  "INSERT INTO SanPham(MaSP , TenSanPham , MoTa , Gia , SoLuongTonKho , NgayTao , TrangThai , DonViTinh , MaMau , MaKichCo , MaThuongHieu)\n" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, sp.getMaSP());
        ps.setString(2, sp.getTensanpham());
        ps.setString(3, sp.getMota());
        ps.setInt(4, sp.getGia());
        ps.setInt(5, sp.getSoluongtonkho());
        ps.setString(6, sp.getNgaytao());
        ps.setString(7, sp.getTrangthai());
        ps.setString(8, sp.getDonvitinh());
        ps.setString(9, sp.getMamausac());
        ps.setString(10, sp.getMakc());
        ps.setString(11, sp.getMath());
        ps.execute();
    }
    public ArrayList<ModeSanPham> mamau(String tenmau ) throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT MaMau FROM MauSac WHERE TenMau LIKE ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, tenmau);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ModeSanPham sp = new ModeSanPham();
            sp.setMamausac(rs.getString(1));
            
            list.add(sp);
            System.out.println(sp.getMamausac());
            
        }
        return list;
    }
    public ArrayList<ModeSanPham> makc(String tenkc ) throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT MaKichCo FROM KichCo WHERE TenKichCo LIKE ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, tenkc);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ModeSanPham sp = new ModeSanPham();
            sp.setMakc(rs.getString(1));
            
            list.add(sp);
            System.out.println(sp.getMakc());
            
        }
        return list;
    }
    public ArrayList<ModeSanPham> math(String tenth ) throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT MaThuongHieu FROM ThuongHieu WHERE TenThuongHieu LIKE ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, tenth);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ModeSanPham sp = new ModeSanPham();
            sp.setMath(rs.getString(1));
            
            list.add(sp);
            System.out.println(sp.getMath());
            
        }
        return list;
    }
    public void sua(ModeSanPham sp ) throws SQLException{
        String query = "UPDATE SanPham SET MaSP = ? , TenSanPham = ? , MoTa = ? , Gia = ? , SoLuongTonKho = ? , NgayTao = ? , TrangThai = ? , DonViTinh = ? , MaMau = ? , MaKichCo = ? , MaThuongHieu = ? WHERE MaSP = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, sp.getMaSP());
        ps.setString(2, sp.getTensanpham());
        ps.setString(3, sp.getMota());
        ps.setInt(4, sp.getGia());
        ps.setInt(5, sp.getSoluongtonkho());
        ps.setString(6, sp.getNgaytao());
        ps.setString(7, sp.getTrangthai());
        ps.setString(8, sp.getDonvitinh());
        ps.setString(9, sp.getMamausac());
        ps.setString(10, sp.getMakc());
        ps.setString(11, sp.getMath());
        ps.setString(12, sp.getMaSP());
        ps.execute();
        
        
    }
    public void xoa(ModeSanPham sp ) throws SQLException {
        String query = "DELETE FROM SanPham WHERE MaSP = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, sp.getMaSP());
        ps.execute();
        
    }
    
    public ArrayList<ModeSanPham> getmau() throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT * FROM MauSac";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            ModeSanPham sp = new ModeSanPham();
            sp.setMamausac(rs.getString(1));
            sp.setTenmau(rs.getString(2));
            list.add(sp);
        }
        return list;
    }
    public ArrayList<ModeSanPham> getkc() throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT * FROM KichCo";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            ModeSanPham sp = new ModeSanPham();
            sp.setMakc(rs.getString(1));
            sp.setTenkc(rs.getString(2));
            list.add(sp);
        }
        return list;
    }
    public ArrayList<ModeSanPham> getth() throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT * FROM ThuongHieu";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            ModeSanPham sp = new ModeSanPham();
            sp.setMath(rs.getString(1));
            sp.setTenth(rs.getString(2));
            list.add(sp);
        }
        return list;
    }
    public void themmau(ModeSanPham sp) throws SQLException{
        String query = "INSERT INTO MauSac (MaMau , TenMau)\n" +
                        "VALUES ( ? , ?)";
       PreparedStatement ps = conn.prepareStatement(query);
       ps.setString(1, sp.getMamausac());
       ps.setString(2, sp.getTenmau());
       ps.execute();
    }
    public void xoamau(ModeSanPham sp) throws SQLException{
        String query ="DELETE FROM MauSac WHERE MaMau = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, sp.getMamausac());
        ps.execute();
    }
    public void suamau(ModeSanPham sp ) throws SQLException{
        String query = "UPDATE MauSac SET MaMau = ? , TenMau = ? WHERE MaMau = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, sp.getMamausac());
        ps.setString(2, sp.getTenmau());
        ps.setString(3, sp.getMamausac());
        ps.execute();
        
    }
    public ArrayList<ModeSanPham> timkiemmau( String tenmau) throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT * FROM MauSac WHERE TenMau = ? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, tenmau);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
           ModeSanPham sp = new ModeSanPham();
           sp.setMamausac(rs.getString(1));
           sp.setTenmau(rs.getString(2));
           
           list.add(sp);
        }
        
        
        return list;
    } 
    public ArrayList<ModeSanPham> timkiemsanpham(String tensp) throws SQLException {
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT sp.MaSP, sp.TenSanPham ,ms.TenMau , kc.TenKichCo , th.TenThuongHieu, sp.MoTa , sp.Gia , sp.SoLuongTonKho ,sp.DonViTinh, sp.NgayTao , sp.TrangThai  FROM SanPham sp \n" +
"                        LEFT JOIN MauSac ms ON sp.MaMau = ms.MaMau\n" +
"                        LEFT JOIN ThuongHieu th ON th.MaThuongHieu = sp.MaThuongHieu\n" +
"                        LEFT JOIN KichCo kc ON kc.MaKichCo = sp.MaKichCo WHERE TenSanPham LIKE ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, '%'+tensp+'%');
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ModeSanPham sp = new ModeSanPham();
            sp.setMaSP(rs.getString(1));
            sp.setTensanpham(rs.getString(2));
            sp.setTenmau(rs.getString(3));
            sp.setTenkc(rs.getString(4));
            sp.setTenth(rs.getString(5));
            sp.setMota(rs.getString(6));
            sp.setGia(rs.getInt(7));
            sp.setSoluongtonkho(rs.getInt(8));
            sp.setDonvitinh(rs.getString(9));
            sp.setNgaytao(rs.getString(10));
            sp.setTrangthai(rs.getString(11));
            
            list.add(sp);
            
        }
        
        return list;
    }
    public void themkc(ModeSanPham sp) throws SQLException{
        String query = "INSERT INTO KichCo (MaKichCo , TenKichCo)\n" +
                        "VALUES ( ? , ?)";
       PreparedStatement ps = conn.prepareStatement(query);
       ps.setString(1, sp.getMakc());
       ps.setString(2, sp.getTenkc());
       ps.execute();
    }
    public void xoakc(ModeSanPham sp) throws SQLException{
        String query ="DELETE FROM KichCo WHERE MaKichCo = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, sp.getMakc());
        ps.execute();
    }
    public void suakc(ModeSanPham sp ) throws SQLException{
        String query = "UPDATE KichCo SET MaKichCo = ? , TenKichCo = ? WHERE MaKichCo = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, sp.getMakc());
        ps.setString(2, sp.getTenkc());
        ps.setString(3, sp.getMakc());
        ps.execute();
        
    }
    public ArrayList<ModeSanPham> timkiemkichco( String tenkc) throws SQLException{
        ArrayList<ModeSanPham> list = new ArrayList<>();
        String query = "SELECT * FROM KichCo WHERE TenKichCo = ? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, tenkc);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
           ModeSanPham sp = new ModeSanPham();
           sp.setMakc(rs.getString(1));
           sp.setTenkc(rs.getString(2));
           
           list.add(sp);
        }
        
        
        return list;
    } 
}
