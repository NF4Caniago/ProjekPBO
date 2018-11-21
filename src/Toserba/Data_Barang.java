/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toserba;

/**
 *
 * @author vur3ris
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Data_Barang extends JFrame{
    
    private final Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    JButton add,save,edit,menu;
    
    String[][] data = new String[50][4];
    String[] kolom = {"ID","Nama","Harga","Kuantitas"};
    JTable tabel;
    JPanel panel1,panel2;
    JScrollPane scrollpane;
    String DBUrl = "jdbc:mysql://localhost/data_toko";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultset;
    
    public Data_Barang()
    {
        setTitle("Data Toko");
        setSize(700,500);
        setDefaultCloseOperation(3);
        setLocation(dimensi.width/2-getWidth()/2,dimensi.height/2-getHeight()/2);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBUrl,DBusername,DBpassword);
            statement = koneksi.createStatement();
            String sql = "select * from data_barang";
            resultset = statement.executeQuery(sql);
                    int p = 0;
                    while(resultset.next())
                    {
                        data[p][0]=resultset.getString("id");
                        data[p][1]=resultset.getString("nama");
                        data[p][2]=resultset.getString("harga");
                        data[p][3]=resultset.getString("kuantitas");
                        p++;
                    }
            statement.close();
            koneksi.close();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!","Hasil",JOptionPane.ERROR_MESSAGE);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditemukan!","Hasil",JOptionPane.ERROR_MESSAGE);
        }
        
        tabel = new JTable(data,kolom);
        scrollpane = new JScrollPane(tabel);
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel1.add(scrollpane);
        /*
        add = new JButton("Add");
        save = new JButton("Save");
        edit = new JButton("Edit");
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,4));
        panel2.add(add);
        add.addActionListener((ActionEvent ae) -> {
        
        });
        panel2.add(edit);
        panel2.add(save);
        panel2.add(menu);
        
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(panel1,BorderLayout.NORTH);
        container.add(panel2,BorderLayout.CENTER);
        */
        setResizable(false);
        setVisible(true);
        pack();
    }
}
