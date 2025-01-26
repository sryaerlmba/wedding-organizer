/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;
import connection.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
/**
 *
 * @author surya
 */
public class CRUDUser {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public CRUDUser(){
        try {
            Koneksi koneksi = new Koneksi();
            conn = koneksi.getKoneksi();
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }
    
    public DefaultTableModel tampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID User");
        model.addColumn("Fullname");
        model.addColumn("Username");
        model.addColumn("Role");

        String sql = "SELECT * FROM users WHERE role = 'Customer' OR role = 'Vendor'";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[4];
                data[0] = rs.getInt("id");
                data[1] = rs.getString("fullname");
                data[2] = rs.getString("username");
                data[3] = rs.getString("role");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
    return model;
}

    
}
