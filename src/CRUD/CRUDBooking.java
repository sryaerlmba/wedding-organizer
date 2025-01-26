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
public class CRUDBooking {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public CRUDBooking(){
        try {
            Koneksi koneksi = new Koneksi();
            conn = koneksi.getKoneksi();
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }
    
    public static void ubahData(int idBooking, String namaCustomer, String jenisKelamin, String agama, String alamat, String noHp, int idVendor, String namaVendor, String namaPaket, String harga, java.sql.Date tanggalAcara, String deskripsi, String status) {
        String sql = "UPDATE booking SET nama_customer=?, jenis_kelamin=?, agama=?, alamat=?, no_hp=?, id_vendor=?, nama_vendor=?, nama_paket=?, harga=?, tanggal_reservasi=?, deskripsi=?, status=? WHERE id_booking=?";
        try (Connection conn = new Koneksi().getKoneksi(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, namaCustomer);
            pstmt.setString(2, jenisKelamin);
            pstmt.setString(3, agama);
            pstmt.setString(4, alamat);
            pstmt.setString(5, noHp);
            pstmt.setInt(6, idVendor);
            pstmt.setString(7, namaVendor);
            pstmt.setString(8, namaPaket);
            pstmt.setString(9, harga);
            pstmt.setDate(10, tanggalAcara);
            pstmt.setString(11, deskripsi);
            pstmt.setString(12, status);
            pstmt.setInt(13, idBooking);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Gagal mengubah data: " + ex.getMessage());
        }
    }
    
    public void hapusData(int idBooking, int idCustomer) {
        String sql = "DELETE FROM booking WHERE id_booking = ? AND id_customer = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idBooking);
            pst.setInt(2, idCustomer);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Tidak ada data yang dihapus. Pastikan id_booking dan id_customer valid.");
            }
        } catch (SQLException ex) {
            System.out.println("Gagal menghapus data: " + ex.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                System.out.println("Gagal menutup prepared statement: " + e.getMessage());
            }
        }
}
        
    public DefaultTableModel loadDataBooking(int userID){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id Booking");
        model.addColumn("Nama Customer");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Agama");
        model.addColumn("Alamat");
        model.addColumn("No HP");
        model.addColumn("id Vendor");
        model.addColumn("Nama vendor");
        model.addColumn("Nama Paket");
        model.addColumn("Harga");
        model.addColumn("Tanggal Acara");
        model.addColumn("Deskripsi");
        model.addColumn("Status");

        String sql = "SELECT id_booking, nama_customer, jenis_kelamin, agama, alamat, no_hp, id_vendor, nama_vendor, nama_paket, harga, tanggal_reservasi, deskripsi, status FROM booking WHERE id_customer = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, userID);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[13]; 
                data[0] = rs.getInt("id_booking");
                data[1] = rs.getString("nama_customer");
                data[2] = rs.getString("jenis_kelamin");
                data[3] = rs.getString("agama");
                data[4] = rs.getString("alamat");
                data[5] = rs.getString("no_hp");
                data[6] = rs.getInt("id_vendor");
                data[7] = rs.getString("nama_vendor");
                data[8] = rs.getString("nama_paket");
                data[9] = rs.getString("harga");
                data[10] = rs.getDate("tanggal_reservasi");
                data[11] = rs.getString("deskripsi");
                data[12] = rs.getString("status");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
        return model;
    }
    
    public void updateStatusSisa(int idBooking, String status, double sisa) {
        String sql = "UPDATE booking SET status = ?, sisa = ? WHERE id_booking = ?";
        try  {
            pst = conn.prepareStatement(sql);
            pst.setString(1, status);
            pst.setDouble(2, sisa);
            pst.setInt(3, idBooking);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void updateStatus(int idBooking, String status) {
        String sql = "UPDATE booking SET status = ? WHERE id_booking = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, status);
            pst.setInt(2, idBooking);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public DefaultTableModel loadDataBookingAdmin(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id Booking");
        model.addColumn("id Customer");
        model.addColumn("Nama Customer");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Agama");
        model.addColumn("Alamat");
        model.addColumn("No HP");
        model.addColumn("id Vendor");
        model.addColumn("Nama vendor");
        model.addColumn("Nama Paket");
        model.addColumn("Harga");
        model.addColumn("Tanggal Acara");
        model.addColumn("Deskripsi");
        model.addColumn("Status");

        String sql = "SELECT id_booking, id_customer, nama_customer, jenis_kelamin, agama, alamat, no_hp, id_vendor, nama_vendor, nama_paket, harga, tanggal_reservasi, deskripsi, status FROM booking";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[14]; 
                data[0] = rs.getInt("id_booking"); 
                data[1] = rs.getInt("id_customer");
                data[2] = rs.getString("nama_customer");
                data[3] = rs.getString("jenis_kelamin");
                data[4] = rs.getString("agama");
                data[5] = rs.getString("alamat");
                data[6] = rs.getString("no_hp");
                data[7] = rs.getInt("id_vendor");
                data[8] = rs.getString("nama_vendor");
                data[9] = rs.getString("nama_paket");
                data[10] = rs.getString("harga");
                data[11] = rs.getDate("tanggal_reservasi");
                data[12] = rs.getString("deskripsi");
                data[13] = rs.getString("status");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
        return model;
    }
    
    public String getStatusPembayaran(int idBooking) {
        String status = "";
        try {
            String sql = "SELECT status FROM pembayaran WHERE id_booking = ?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, idBooking);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                status = resultSet.getString("status");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
