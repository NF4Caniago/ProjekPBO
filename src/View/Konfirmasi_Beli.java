/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author vur3ris
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Konfirmasi_Beli extends JFrame implements ActionListener{
    JLabel lBayar, lTotal,lKembalian,lNominalTotal,lNominalKembalian;
    JTextField tfBayar;
    JButton bOK,bHitung;
    JPanel panel1,panel2;
    Controller_Barang controller;
    Menu_Belanja menuBelanja;
    public Konfirmasi_Beli(Menu_Belanja menuBelanja,Controller_Barang controller)
    {
        super("Pembayaran");
        setSize(300,200);
        
        this.controller = controller;
        
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,2));
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        lBayar = new JLabel("Pembayaran");
        lTotal = new JLabel("Total Belanja");
        lKembalian = new JLabel("Kembalian");
        lNominalTotal = new JLabel(String.valueOf(menuBelanja.getTotal()));
        lNominalKembalian = new JLabel();
        tfBayar = new JTextField();
        bOK = new JButton("OK");
        bHitung = new JButton("Hitung");
        panel1.add(lTotal);
        panel1.add(lNominalTotal);
        panel1.add(lBayar);
        panel1.add(tfBayar);
        panel1.add(lKembalian);
        panel1.add(lNominalKembalian);
        
        panel2.add(bOK).setVisible(false);
        panel2.add(bHitung);
        
       this.menuBelanja = menuBelanja;
        
        bHitung.addActionListener(this);
        bOK.addActionListener(this);
        
        
        Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(panel1, BorderLayout.CENTER);
        cont.add(panel2, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == bOK){
        if(controller.updateBarang(menuBelanja))
                {
                    menuBelanja.tabelModel.setRowCount(0);
                }
            else
                JOptionPane.showMessageDialog(null, "Data Gagal Di Update");
            controller.loadBarang();
            menuBelanja.Reset();
            this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
        }
        
        if(ae.getSource() == bHitung){
        int bayar=0;
            int total=0;
            try{
                bayar=Integer.parseInt(tfBayar.getText());
                total=Integer.parseInt(lNominalTotal.getText());
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(null, "Format Yang Anda Masukkan Salah!");
            }
            if(bayar<total)
                JOptionPane.showMessageDialog(null, "Uang Pembayaran Kurang!");
            else
            {
                lNominalKembalian.setText(String.valueOf(bayar-total));
            bOK.setVisible(true);
            bHitung.setVisible(false);
            }
        }
        
        
        }
    
       
    
    }
    

