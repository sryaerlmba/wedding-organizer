package CRUD;

import connection.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class CRUDVendor {
   
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public CRUDVendor() {
        try {
            Koneksi koneksi = new Koneksi();
            conn = koneksi.getKoneksi();
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }

    public void simpanData(String namaVendor, String namaPaket, String noHp, String harga, String deskripsi, int userID) {
        String sql = "INSERT INTO vendors (nama_vendor, nama_paket, no_hp, harga, deskripsi, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, namaVendor);
            pst.setString(2, namaPaket);
            pst.setString(3, noHp);
            pst.setString(4, harga);
            pst.setString(5, deskripsi);
            pst.setInt(6, userID);
            pst.executeUpdate();
            System.out.println("Data berhasil disimpan.");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    public void ubahData(int id, String namaVendor, String namaPaket, String noHp, String harga, String deskripsi) {
        String sql = "UPDATE vendors SET nama_vendor = ?, nama_paket = ?, no_hp = ?, harga = ?, deskripsi = ? WHERE id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, namaVendor);
            pst.setString(2, namaPaket);
            pst.setString(3, noHp);
            pst.setString(4, harga);
            pst.setString(5, deskripsi);
            pst.setInt(6, id);
            pst.executeUpdate();
            System.out.println("Data berhasil diubah.");
        } catch (SQLException e) {
            System.out.println("Gagal mengubah data: " + e.getMessage());
        }
    }

    public void hapusData(int id) {
        String sql = "DELETE FROM vendors WHERE id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Data berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }

    public DefaultTableModel tampilData(int userID) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("User ID");
        model.addColumn("Nama Vendor");
        model.addColumn("Nama Paket");
        model.addColumn("No HP");
        model.addColumn("Harga");
        model.addColumn("Deskripsi");

        String sql = "SELECT * FROM vendors WHERE user_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, userID);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[7];
                data[0] = rs.getInt("id");
                data[1] = rs.getInt("user_id");
                data[2] = rs.getString("nama_vendor");
                data[3] = rs.getString("nama_paket");
                data[4] = rs.getString("no_hp");
                data[5] = rs.getString("harga");
                data[6] = rs.getString("deskripsi");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
        return model;
    }
    
    public DefaultTableModel tampilDataAdmin() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("User ID");
        model.addColumn("Nama Vendor");
        model.addColumn("Nama Paket");
        model.addColumn("No HP");
        model.addColumn("Harga");
        model.addColumn("Deskripsi");

        String sql = "SELECT * FROM vendors";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[7];
                data[0] = rs.getInt("id");
                data[1] = rs.getInt("user_id");
                data[2] = rs.getString("nama_vendor");
                data[3] = rs.getString("nama_paket");
                data[4] = rs.getString("no_hp");
                data[5] = rs.getString("harga");
                data[6] = rs.getString("deskripsi");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
        return model;
    }
    
    public DefaultTableModel loadDataBookingVendor(int vendorId) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id Booking");
        model.addColumn("Nama Customer");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Agama");
        model.addColumn("Alamat");
        model.addColumn("No HP");
        model.addColumn("Nama Vendor");
        model.addColumn("Nama Paket");
        model.addColumn("Harga");
        model.addColumn("Tanggal Acara");
        model.addColumn("Deskripsi");
        model.addColumn("Status");

        String sql = "SELECT id_booking, nama_customer, jenis_kelamin, agama, alamat, no_hp, nama_vendor, nama_paket, harga, tanggal_reservasi, deskripsi, status FROM booking WHERE id_vendor = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, vendorId); // Set vendorId ke query
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] data = new Object[12]; // Sesuaikan dengan jumlah kolom
                data[0] = rs.getInt("id_booking");
                data[1] = rs.getString("nama_customer");
                data[2] = rs.getString("jenis_kelamin");
                data[3] = rs.getString("agama");
                data[4] = rs.getString("alamat");
                data[5] = rs.getString("no_hp");
                data[6] = rs.getString("nama_vendor");
                data[7] = rs.getString("nama_paket");
                data[8] = rs.getDouble("harga");
                data[9] = rs.getDate("tanggal_reservasi");
                data[10] = rs.getString("deskripsi");
                data[11] = rs.getString("status");
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
        return model;
    }
    
    public boolean isVendorDuplicate(String namaVendor, String namaPaket) {
    String sql = "SELECT COUNT(*) FROM vendors WHERE nama_vendor = ? AND nama_paket = ?";
    try {
        pst = conn.prepareStatement(sql);
        pst.setString(1, namaVendor);
        pst.setString(2, namaPaket);
        rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Jika jumlahnya lebih dari 0, berarti duplikat ditemukan
        }
    } catch (SQLException e) {
        System.out.println("Gagal memeriksa duplikat: " + e.getMessage());
    }
    return false; // Tidak ada duplikat
}
}
