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



//PENTING! Edit agar data pembelian/pemasukan barang bisa disimpan di database



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import Controller.*;
import Listener.*;
import Model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableCellRenderer;

public class Menu_Admin extends JFrame implements ActionListener,Listener_Barang{
    private JLabel lNama,lHarga,lJumlah,lKode,lAdmin;
    public JComboBox cmbSatuan;
    public JTextField tfNama,tfHarga,tfJumlah,tfKode;
    private String[] lKolom = {"KODE","NAMA","HARGA","JUMLAH","SATUAN"};
    private String[] Satuan = {"-PILIH-","KG","Lusin","Bungkus","Liter","Pack"};
    public DefaultTableModel tabelModel = new DefaultTableModel(null,lKolom){
        @Override
        public boolean isCellEditable(int row,int column)
        {
            return false;
        }
    };
    public JTable tTabel = new JTable();
    private JScrollPane scrollpane;
    private JPanel panel1,panel2,panel3,panel4,panel5,panel6;
    public JButton bAdd,bUpdate,bSave,bEdit,bCancel,bDelete,bExit;
    private final Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
    private Controller_Barang controller;
    private Model_Barang modelBarang;
    private int load=0;
    
    
    public Menu_Admin(Model_Barang modelBarang,Controller_Barang controller)
    {
        super("ADMINISTRATOR");
        setSize(800,600);
        setLocation(dimensi.width / 2 - getWidth() / 2, dimensi.height / 2 - getHeight() / 2);
        setDefaultCloseOperation(3);
        
        this.modelBarang  = modelBarang;
        this.controller = controller;
        
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new GridLayout(1,2,10,10));
        panel5 = new JPanel(new GridLayout(4,2,10,20));
        panel6 = new JPanel(null);
        panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER,40, 10));
        
        scrollpane = new JScrollPane(tTabel);
        
        lAdmin = new JLabel("ADMINISTRATOR",SwingConstants.CENTER);
        lAdmin.setFont(new Font("COUTORE",Font.BOLD,48));
        
        lKode = new JLabel("    KODE");tfKode = new JTextField();
        lKode.setFont(new Font("Champagne & Limousines",Font.BOLD,20));
        lNama = new JLabel("    NAMA");tfNama = new JTextField();
        lNama.setFont(new Font("Champagne & Limousines",Font.BOLD,20));
        lHarga = new JLabel("    HARGA");tfHarga = new JTextField("0");
        lHarga.setFont(new Font("Champagne & Limousines",Font.BOLD,20));
        lJumlah = new JLabel("    JUMLAH");tfJumlah = new JTextField("0");
        lJumlah.setFont(new Font("Champagne & Limousines",Font.BOLD,20));
        
        cmbSatuan = new JComboBox(Satuan);
        
        bAdd = new JButton("ADD");
        bUpdate = new JButton("UPDATE");
        bSave = new JButton("SAVE");
        bEdit = new JButton("EDIT");
        bCancel = new JButton("CANCEL");
        bDelete = new JButton("DELETE");
        bExit = new JButton("EXIT");
       
        tTabel.setModel(tabelModel);
        tTabel.getColumnModel().getColumn(1).setMinWidth(400);
        DefaultTableCellRenderer setTengah = new DefaultTableCellRenderer();
        setTengah.setHorizontalAlignment(SwingConstants.CENTER);
        tTabel.getColumnModel().getColumn(0).setCellRenderer(setTengah);
        tTabel.getColumnModel().getColumn(2).setCellRenderer(setTengah);
        tTabel.getColumnModel().getColumn(3).setCellRenderer(setTengah);
        tTabel.getColumnModel().getColumn(4).setCellRenderer(setTengah);
        tTabel.setRowHeight(25);
        tTabel.setFont(new Font("Book Antiqua",Font.PLAIN,16));
        tTabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
                int row = tTabel.rowAtPoint(me.getPoint());
                tfKode.setText(tabelModel.getValueAt(row, 0).toString());
                tfNama.setText(tabelModel.getValueAt(row, 1).toString());
                tfHarga.setText(tabelModel.getValueAt(row, 2).toString());
                tfJumlah.setText(tabelModel.getValueAt(row, 3).toString());
                switch (tabelModel.getValueAt(row, 4).toString()) {
                    case "KG":
                        cmbSatuan.setSelectedIndex(1);
                        break;
                    case "Lusin":
                        cmbSatuan.setSelectedIndex(2);
                        break;
                    case "Bungkus":
                        cmbSatuan.setSelectedIndex(3);
                        break;
                    case "Liter":
                        cmbSatuan.setSelectedIndex(4);
                        break;
                    default:
                        cmbSatuan.setSelectedIndex(5);
                        break;
                }
            }
        });
        
        panel1.add(lAdmin,BorderLayout.NORTH);
        panel1.add(scrollpane,BorderLayout.CENTER);
        panel1.setBackground(Color.WHITE);
        
        panel5.add(lKode);panel5.add(tfKode);
        panel5.add(lNama);panel5.add(tfNama);
        panel5.add(lHarga);panel5.add(tfHarga);
        panel5.add(lJumlah);panel5.add(tfJumlah);
       
        panel6.add(cmbSatuan).setBounds(5, 133, 80, 20);
        
        panel3.add(panel5);panel3.add(panel6);
        
        panel4.add(bAdd);panel4.add(bSave);
        panel4.add(bUpdate).setVisible(false);panel4.add(bEdit);
        panel4.add(bDelete).setVisible(false);panel4.add(bCancel).setVisible(false);
        panel4.add(bExit);
        
        panel2.add(panel3,BorderLayout.NORTH);
        panel2.add(panel4,BorderLayout.SOUTH);
        
        bSave.addActionListener(this);
        bAdd.addActionListener(this);
        bEdit.addActionListener(this);
        bUpdate.addActionListener(this);
        bCancel.addActionListener(this);
        bDelete.addActionListener(this);
        bExit.addActionListener(this);
        
        tTabel.enable(false);
        Container cont = getContentPane();
        cont.setLayout(new GridLayout(2,1));
        cont.add(panel1);
        cont.add(panel2);
        setVisible(true);
        
        modelBarang.setBarangListener(this);
        controller.loadBarang();
    
    }
    
    public String getKode()
    {
        return tfKode.getText();
    }
    
    public String getNama()
    {
        return tfNama.getText();
    }
    
    public String getHarga()
    {
        return tfHarga.getText();
    }

    public String getJumlah()
    {
        return tfJumlah.getText();
    }
    
    public String getSatuan()
    {
        return (String) cmbSatuan.getSelectedItem();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==bSave)
        {
            if(controller.checkBarang(this))
            {
                controller.saveBarang(this);
                tabelModel.setRowCount(0);
                controller.loadBarang();
            }
            else
                JOptionPane.showMessageDialog(null, "Kode/Nama Barang Sudah Ada");
        }
        
        if(ae.getSource()==bAdd)
        {
            controller.reset(this);
        }
        if(ae.getSource()==bEdit)
        {
            cmbSatuan.setVisible(false);
            tfNama.setEditable(false);
            tfKode.setEditable(false);
            bExit.setVisible(false);
            bAdd.setVisible(false);
            bSave.setVisible(false);
            bCancel.setVisible(true);
            bUpdate.setVisible(true);
            bDelete.setVisible(true);
            bEdit.setVisible(false);
        }
        
        if(ae.getSource()==bUpdate)
        {
            cmbSatuan.setVisible(true);
            tfNama.setEditable(true);
            tfKode.setEditable(true);
            bEdit.setVisible(true);
            bExit.setVisible(true);
            bCancel.setVisible(false);
            bAdd.setVisible(true);
            bSave.setVisible(true);
            bDelete.setVisible(false);
            if(controller.updateBarang(this))
            {
                tabelModel.setRowCount(0);
                controller.loadBarang();
            }
            else
                    JOptionPane.showMessageDialog(null, "Data Gagal Di Update","",JOptionPane.ERROR_MESSAGE);
            bUpdate.setVisible(false);
        }
        
        if(ae.getSource()==bDelete)
        {
            cmbSatuan.setVisible(true);
            tfNama.setEditable(true);
            tfKode.setEditable(true);
            bEdit.setVisible(true);
            bExit.setVisible(true);
            bCancel.setVisible(false);
            bAdd.setVisible(true);
            bSave.setVisible(true);
            bDelete.setVisible(false);
            controller.deleteBarang(this);
            tabelModel.setRowCount(0);
            controller.loadBarang();
            bUpdate.setVisible(false);
        }
        
        if(ae.getSource()==bCancel)
        {
            cmbSatuan.setVisible(true);
            tfNama.setEditable(true);
            tfKode.setEditable(true);
            bEdit.setVisible(true);
            bExit.setVisible(true);
            bCancel.setVisible(false);
            bAdd.setVisible(true);
            bSave.setVisible(true);
            bDelete.setVisible(false);
            bUpdate.setVisible(false);
        }
        
        if(ae.getSource()==bExit)
        {
            new Menu_Belanja(modelBarang,controller);
            dispose();
        }
        
    }
    
    @Override
    public void onChange(Model_Barang modelBarang)
    {
        String kode=modelBarang.getKode();
        String nama=modelBarang.getNama();
        String harga=String.valueOf(modelBarang.getHarga());
        String jumlah=String.valueOf(modelBarang.getJumlah());
        String satuan=modelBarang.getSatuan();
        
        load++;
        if(load==5 && (kode!="" || nama!="" || harga!="0" || jumlah!="0" || satuan!="-PILIH-"))
        {
            String[] dataTabel = {kode,nama,harga,jumlah,satuan};
            tabelModel.addRow(dataTabel);
            load=0;
        }
    }
}
