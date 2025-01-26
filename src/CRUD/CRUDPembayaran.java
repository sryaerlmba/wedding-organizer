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
public class CRUDPembayaran {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public CRUDPembayaran(){
        try {
            Koneksi koneksi = new Koneksi();
            conn = koneksi.getKoneksi();
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }
    
    public void insertPembayaran(int idBooking, int idCustomer, int idVendor, String status, double sisa) {
        String sql = "INSERT INTO pembayaran (id_booking, id_customer, id_vendor, status, sisa) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idBooking);
            pst.setInt(2, idCustomer);
            pst.setInt(3, idVendor);
            pst.setString(4, status);
            pst.setDouble(5, sisa);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public DefaultTableModel loadDataPembayaran(int userId) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Pembayaran");
        model.addColumn("ID Booking");
        model.addColumn("ID Customer");
        model.addColumn("ID Vendor");
        model.addColumn("Status");
        model.addColumn("Sisa");

        String sql = "SELECT id_pembayaran, id_booking, id_customer, id_vendor, status, sisa FROM pembayaran WHERE id_customer = ? " ;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[6]; 
                data[0] = rs.getInt("id_pembayaran");
                data[1] = rs.getInt("id_booking");
                data[2] = rs.getInt("id_customer");
                data[3] = rs.getInt("id_vendor");
                data[4] = rs.getString("status");
                data[5] = rs.getDouble("sisa");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
        return model;
    }
    
    public DefaultTableModel loadDataPembayaranAdmin() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Pembayaran");
        model.addColumn("ID Booking");
        model.addColumn("ID Customer");
        model.addColumn("ID Vendor");
        model.addColumn("Status");
        model.addColumn("Sisa");

        String sql = "SELECT id_pembayaran, id_booking, id_customer, id_vendor, status, sisa FROM pembayaran" ;
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[6]; 
                data[0] = rs.getInt("id_pembayaran");
                data[1] = rs.getInt("id_booking");
                data[2] = rs.getInt("id_customer");
                data[3] = rs.getInt("id_vendor");
                data[4] = rs.getString("status");
                data[5] = rs.getDouble("sisa");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
        return model;
    }
    
    public void updatePembayaran(int idPembayaran, String status, double sisa) {
        String sql = "UPDATE pembayaran SET status = ?, sisa = ? WHERE id_pembayaran = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, status);
            pst.setDouble(2, sisa);
            pst.setInt(3, idPembayaran);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

}
