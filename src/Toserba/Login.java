package Toserba;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener{
    private final JTextField userfield = new JTextField();
    private final JPasswordField passfield = new JPasswordField();
    JLabel luser = new JLabel(" Username ");
    JLabel lpass = new JLabel(" Password ");
    JButton login = new JButton(" Login ");
    JButton cancel = new JButton(" Cancel ");
    Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    private final String[][] data = new String[500][3];
    private final String DBUrl = "jdbc:mysql://localhost/login_toserba";
    private final String DBusername = "root";
    private final String DBpassword = "";
    private String temp;
    private Connection koneksi;
    private Statement statement;
    private ResultSet resultset;
    
    public Login()
    {
        setTitle(" Login ");
        setDefaultCloseOperation(2);
        setSize(300,180);
        setLocation(dimensi.width/2-getWidth()/2,dimensi.height/2-getHeight()/2);
        setLayout(null);
        add(userfield);add(passfield);
        add(luser);add(lpass);
        add(login);add(cancel);
        
        luser.setBounds(66,30,80,20);
        lpass.setBounds(66,60,80,20);
        userfield.setBounds(150,30,90,20);
        passfield.setBounds(150,60,90,20);
        login.setBounds(60,90,80,20);
        cancel.setBounds(150,90,90,20);
        login.addActionListener(this);
        cancel.addActionListener(this);
        setVisible(true);
        setResizable(false);
    }
 
    @Override
    public void actionPerformed(ActionEvent a)
    {  
        if(a.getSource()==login)
        {
                    temp= new String(passfield.getPassword());
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        koneksi = DriverManager.getConnection(DBUrl,DBusername,DBpassword);
                        statement = koneksi.createStatement();
                        String sql = "select * from user_pass where username = '"+userfield.getText()+"' and password = '"+temp+"'";
                        resultset = statement.executeQuery(sql);
                        if(resultset.next())
                        {
                            if(userfield.getText().equals(resultset.getString("username")) && temp.equals(resultset.getString("password")))
                            {
                                JOptionPane.showMessageDialog(null,"Selamat Datang "+userfield.getText());
                                dispose();
                                new Data_Barang();
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Username/Password Salah");
                            userfield.setText("");
                            passfield.setText("");
                            userfield.requestFocus();
                        }
                        statement.close();
                        koneksi.close();
                        }
                    catch(SQLException ex)
                    {
                    }
                    catch(ClassNotFoundException ex)
                    {
                        JOptionPane.showMessageDialog(null, "Data Gagal Ditemukan!","Hasil",JOptionPane.ERROR_MESSAGE);
                    }
        }
        if(a.getSource()==cancel)
        {
            dispose();
        }
    }
}