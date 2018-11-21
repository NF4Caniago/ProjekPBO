
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.*;
import Model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DAO {
    
    private final String dbUrl = "jdbc:mysql://localhost/mirata_ngampus";
    private final String dbUsername = "root";
    private final String dbPassword = "";
    private Connection koneksi;
    private Statement statement;
    private ResultSet resultset;
    private Model_Barang modelBarang;
    
    public DAO(Model_Barang modelBarang)
    {
        this.modelBarang = modelBarang;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        koneksi = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        statement = koneksi.createStatement();
        }
        catch(SQLException ex)
        {
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert()
    {
        //String insert = "insert into data_barang values('"+modelBarang.getKode()+"','"+modelBarang.getNama()+"','"+modelBarang.getHarga()+"','"+modelBarang.getJumlah()+"','"+modelBarang.getSatuan()+"')";
        String insert = "insert into data_barang (kode,nama,harga,jumlah,satuan) values(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = koneksi.prepareStatement(insert);
            ps.setString(1, modelBarang.getKode());
            ps.setString(2, modelBarang.getNama());
            ps.setInt(3, modelBarang.getHarga());
            ps.setInt(4, modelBarang.getJumlah());
            ps.setString(5, modelBarang.getSatuan());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!","Hasil",JOptionPane.INFORMATION_MESSAGE);    
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan!","Hasil",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void load()
    {
        try{
            String sql = "select * from data_barang";
            resultset = statement.executeQuery(sql);
                    
                    while(resultset.next())
                    {
                        modelBarang.setKode(resultset.getString("kode"));
                        modelBarang.setNama(resultset.getString("nama"));
                        modelBarang.setHarga(Integer.parseInt(resultset.getString("harga")));
                        modelBarang.setJumlah(Integer.parseInt(resultset.getString("jumlah")));
                        modelBarang.setSatuan(resultset.getString("satuan"));
                    }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!","Hasil",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*public void update(Model_Barang modelBarang)
    {
        //String update="UPDATE data_barang SET harga='"+modelBarang.getHarga()+"',jumlah='"+modelBarang.getHarga()+"' WHERE 'kode'='"+modelBarang.getKode()+"' AND 'nama'='"+modelBarang.getNama()+"'";
        String update="UPDATE data_barang SET harga=?,jumlah=? WHERE kode=? AND nama=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(update);
            ps.setInt(1, modelBarang.getHarga());
            ps.setInt(2, modelBarang.getJumlah());
            ps.setString(3, modelBarang.getKode());
            ps.setString(4, modelBarang.getNama());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diperbaharui!","Hasil",JOptionPane.INFORMATION_MESSAGE);    
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diperbaharui!","Hasil",JOptionPane.ERROR_MESSAGE);
        }
    }*/
    
    public boolean update()
    {
        //String update="UPDATE data_barang SET harga='"+modelBarang.getHarga()+"',jumlah='"+modelBarang.getHarga()+"' WHERE 'kode'='"+modelBarang.getKode()+"' AND 'nama'='"+modelBarang.getNama()+"'";
        String update="UPDATE data_barang SET harga=?,jumlah=? WHERE kode=? AND nama=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(update);
            ps.setInt(1, modelBarang.getHarga());
            ps.setInt(2, modelBarang.getJumlah());
            ps.setString(3, modelBarang.getKode());
            ps.setString(4, modelBarang.getNama());
            int status = ps.executeUpdate();
            if(status==1)
                return true;    
            else
                return false;
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diperbaharui!","Hasil",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    public void delete(Menu_Admin menuAdmin)
    {
        String delete="delete from data_barang where kode=? and nama=?";
        try {
            PreparedStatement ps = koneksi.prepareStatement(delete);
            ps.setString(1, menuAdmin.getKode());
            ps.setString(2, menuAdmin.getNama());
            int status = ps.executeUpdate();
            if(status>0)
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!","Hasil",JOptionPane.INFORMATION_MESSAGE);    
            else
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!","Hasil",JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!","Hasil",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean getInfoLogin()
    {
        String username,password;
        try{
            String sql = "select * from info_login where username='"+modelBarang.getUser()+"' and password='"+modelBarang.getPass()+"'";
            resultset = statement.executeQuery(sql);
                    while(resultset.next())
                    {
                        username=resultset.getString("username");
                        password=resultset.getString("password");
                        
                        if(username.equals(modelBarang.getUser()) && password.equals(modelBarang.getPass()))
                            return true;
                    }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Data Admin Tidak Ditemukan!","Hasil",JOptionPane.ERROR_MESSAGE);
        }        
            return false;
    }
    
    public boolean checkBarang(String namaBarang,String kodeBarang)
    {
        String namaBarangTemp;
        String kodeBarangTemp;
        try{
            String sql = "select * from data_barang where nama like '%"+namaBarang+"%' or kode like '%"+kodeBarang+"%'";
            resultset = statement.executeQuery(sql);
                    while(resultset.next())
                    {
                        kodeBarangTemp=resultset.getString("kode");
                        namaBarangTemp=resultset.getString("nama").toLowerCase();
                        if(kodeBarang.equals(kodeBarangTemp))
                            return true;    //true menandakan nama/kode barang sudah ada
                        else if(namaBarang.toLowerCase().equals(namaBarangTemp))
                            return true;
                    }
        }
        catch(SQLException ex)
        {
        }
            return false;   //false menandakan nama/kode bisa di pakai
    }
    
    public void tutupKoneksi()
    {
        try {
            koneksi.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}

    