/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Init;


import java.sql.*;
/**
 *
 * @author ASUS
 */
public class DBConnect {
    public static String USER = "sa";
    public static String PASS = "123456";
    public static String URL = "jdbc:sqlserver://localhost;databaseName=QuanLyTiemQuanAo;user=sa;password=123456;encrypt=true;trustServerCertificate=true;";


    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getConnect() {
        Connection con = null;
        try {
            con = java.sql.DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static void main(String[] args) {
        Connection con1 = getConnect();
        if (con1 != null) {
            System.out.println("DONE");
        } else {
            System.out.println("FAIL");
        }
    }
}
