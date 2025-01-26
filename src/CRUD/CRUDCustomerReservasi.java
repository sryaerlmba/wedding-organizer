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
public class CRUDCustomerReservasi {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public CRUDCustomerReservasi(){
        try {
            Koneksi koneksi = new Koneksi();
            conn = koneksi.getKoneksi();
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }
    
    public void simpanData(int idVendor, int idCustomer, String namaCustomer, String jenisKelamin, String agama, String alamat, String noHp, String namaVendor, String namaPaket, String harga, java.sql.Date tanggalReservasi, String deskripsi){
        String sql = "INSERT INTO BOOKING (id_vendor, id_customer, nama_customer, jenis_kelamin, agama, alamat, no_hp, nama_vendor, nama_paket, harga, tanggal_reservasi, deskripsi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idVendor);
            pst.setInt(2, idCustomer);
            pst.setString(3, namaCustomer);
            pst.setString(4, jenisKelamin);
            pst.setString(5, agama);
            pst.setString(6, alamat);
            pst.setString(7, noHp);
            pst.setString(8, namaVendor);
            pst.setString(9, namaPaket);
            pst.setString(10, harga);
            pst.setDate(11, tanggalReservasi);
            pst.setString(12, deskripsi);
            pst.executeUpdate();
            System.out.println("Data berhasil disimpan.");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }
    
    public DefaultTableModel loadDataVendor(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id Vendor");
        model.addColumn("Nama Vendor");
        model.addColumn("Nama Paket");
        model.addColumn("Harga");
        model.addColumn("Deskripsi");

        String sql = "SELECT user_id, nama_vendor, nama_paket, harga, deskripsi FROM vendors";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[5];
                data[0] = rs.getInt("user_id");
                data[1] = rs.getString("nama_vendor");
                data[2] = rs.getString("nama_paket");
                data[3] = rs.getString("harga");
                data[4] = rs.getString("deskripsi");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
        return model;
    }
}
