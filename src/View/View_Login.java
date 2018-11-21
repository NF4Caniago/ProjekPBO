package View;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Model.*;
import Controller.*;

public class View_Login extends JFrame implements ActionListener{
    private final JTextField userfield = new JTextField();
    private final JPasswordField passfield = new JPasswordField();
    JLabel luser = new JLabel(" Username ");
    JLabel lpass = new JLabel(" Password ");
    JButton login = new JButton(" Login ");
    JButton cancel = new JButton(" Cancel ");
    Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    private Model_Barang modelBarang;
    private Controller_Barang controller;
    private int log=0;
    Menu_Belanja menuBelanja;
    public View_Login(Menu_Belanja menuBelanja,Controller_Barang controller,Model_Barang modelBarang)
    {
        this.controller = controller;
        this.modelBarang = modelBarang;
        
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
        setVisible(true);
        setResizable(false);
        
        login.addActionListener(this);
        cancel.addActionListener(this);
        
        this.menuBelanja = menuBelanja;
        
    }

    public String getUser()
    {
        return userfield.getText();
    }
    
    public String getPass()
    {
        return passfield.getText();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login){
         if(controller.infoLogin(View_Login.this))
            {
                menuBelanja.dispose();
                dispose();
                new Menu_Admin(modelBarang,controller);
            }
            else if(userfield.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Username Masih Kosong");
            else if(passfield.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Password Masih Kosong");
            else
                JOptionPane.showMessageDialog(null, "Username/Password Salah");
        }
        
        if(ae.getSource() == cancel){
             dispose();
        }
        
    }
}