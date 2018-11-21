
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Controller.*;
import Model.*;
import Listener.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_Belanja extends JFrame implements ActionListener,Listener_Barang{
    private JLabel lNama,lNamaBarang,lHarga,lNominalHarga,lGambar,lJumlah,lTotal,lNominalTotal,lKosong;
    private JTextField tfJumlah;
    private JButton bBeli,bAdmin,bTambah,bExit,bReset;
    private JScrollPane ScrollPane;
    private String[] tKolom = {"Kode","Nama","Harga","Jumlah","Satuan"};
    //private String[] tCartKolom = {"Kode","Nama","Jumlah","Keterangan"}; //pengembangan laporan barang
    //private JTable tCart = new JTable(new DefaultTableModel(null,tCartKolom));
    //public DefaultTableModel tabelCartModel = new DefaultTableModel(null,tCartKolom);
    private JTable tTabel = new JTable(new DefaultTableModel(null,tKolom));
    public DefaultTableModel tabelModel = new DefaultTableModel(null,tKolom){ //agar tabel tidak bisa di edit
        @Override
        public boolean isCellEditable(int row,int column)
        {
            return false;
        }
    };
    private JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel0;
    private int load=0,totalBelanja=0,jumlahBarangTemp=0;
    private final Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize(); //mengambil ukuran layar
    private String kodeTemp,satuanTemp;
    private int rowPilih;
    private Controller_Barang controller;
    private Model_Barang modelBarang;
    private Konfirmasi_Beli beli;
    
    public Menu_Belanja(Model_Barang modelBarang,Controller_Barang controller)
    {
        setTitle("MIRATA NGAMPUS");
        setSize(800,730);
        setDefaultCloseOperation(3);
        setLocation(dimensi.width / 2 - getWidth() / 2, dimensi.height / 2 - getHeight() / 2);
        setLayout(new BorderLayout());
        
        this.controller = controller;
        this.modelBarang = modelBarang;
        
        modelBarang.setBarangListener(this);
        controller.loadBarang();
        
        panel0 = new JPanel();
        panel0.setLayout(new GridLayout(2,1));
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,2,10,10));
        panel4 = new JPanel();
        panel4.setLayout(null);
        panel5 = new JPanel();
        panel5.setLayout(new BorderLayout());
        panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
        
        tTabel.setSize(800, 300);
        
        ScrollPane = new JScrollPane(tTabel);
        
        lGambar = new JLabel(new ImageIcon(getClass().getResource(("Mirata_Ngampus1.jpg"))));
        
        lNama = new JLabel("Nama");
        lNama.setFont(new Font("Calibri",Font.BOLD,20));
        
        lNamaBarang = new JLabel("-");
        lNamaBarang.setFont(new Font("Calibri",Font.BOLD,20));
        
        lHarga = new JLabel("Harga");
        lHarga.setFont(new Font("Calibri",Font.BOLD,20));
        
        lNominalHarga = new JLabel("-");
        lNominalHarga.setFont(new Font("Calibri",Font.BOLD,20));
        
        lJumlah = new JLabel("Jumlah");
        lJumlah.setFont(new Font("Calibri",Font.BOLD,20));
        
        tfJumlah = new JTextField("");
        tfJumlah.setFont(new Font("Calibri",Font.BOLD,20));
        
        lTotal = new JLabel("Total : ");
        lTotal.setFont(new Font("Calibri",Font.BOLD,20));
        
        lNominalTotal = new JLabel("Rp.0",SwingConstants.CENTER);
        lNominalTotal.setFont(new Font("Calibri",Font.BOLD,64));
        
        lKosong = new JLabel();

        //tCart.setModel(tabelCartModel);
        
        tTabel.setModel(tabelModel);
        tTabel.getColumnModel().getColumn(1).setMinWidth(400);
        DefaultTableCellRenderer setTengah = new DefaultTableCellRenderer();
        setTengah.setHorizontalAlignment(SwingConstants.CENTER);            //agar rata tengah di tabel
        tTabel.getColumnModel().getColumn(0).setCellRenderer(setTengah);
        tTabel.getColumnModel().getColumn(2).setCellRenderer(setTengah);
        tTabel.getColumnModel().getColumn(3).setCellRenderer(setTengah);
        tTabel.getColumnModel().getColumn(4).setCellRenderer(setTengah);
        tTabel.setRowHeight(25);
        tTabel.setFont(new Font("Book Antiqua",Font.PLAIN,16));
        tTabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) { //agar isi tabel bisa pindah ke textfield
                int row = tTabel.rowAtPoint(me.getPoint());
                rowPilih=row; //agar jumlah barang bisa dikurangi ketika tambah
                kodeTemp=(tabelModel.getValueAt(row, 0).toString());
                lNamaBarang.setText(tabelModel.getValueAt(row, 1).toString());
                lNominalHarga.setText(tabelModel.getValueAt(row, 2).toString());
                jumlahBarangTemp=Integer.parseInt(tabelModel.getValueAt(row, 3).toString());
                satuanTemp=(tabelModel.getValueAt(row, 2).toString());
            }
        });
        
        
        bTambah = new JButton("TAMBAH");
        bTambah.setFont(new Font("Calibri",Font.BOLD,20));
        bTambah.setBorder(BorderFactory.createEmptyBorder());
        bTambah.setContentAreaFilled(false);
        bTambah.setForeground(Color.BLUE);
        bTambah.addActionListener(this);
        
        bBeli = new JButton("BELI");
        bBeli.addActionListener(this);
        
        bAdmin = new JButton("ADMINISTRATOR");
        bAdmin.addActionListener(this);
        
        bExit = new JButton("Exit");
        bExit.addActionListener(this);
        
        bReset = new JButton("RESET");
        bReset.addActionListener(this);
        
        panel1.add(lGambar, BorderLayout.NORTH);
        panel1.add(ScrollPane, BorderLayout.CENTER);
        
        
        panel4.add(lNama).setBounds(30,20,100,20);
        panel4.add(lNamaBarang).setBounds(160, 20, 200, 20);
        panel4.add(lHarga).setBounds(30,70,100,20);
        panel4.add(lNominalHarga).setBounds(160,70,100,20);
        panel4.add(lJumlah).setBounds(30,120,100,20);
        panel4.add(tfJumlah).setBounds(160,110,60,30);
        panel4.add(lKosong).setBounds(30,150,100,20);
        panel4.add(bTambah).setBounds(110,180,130,25);
        //panel4.setBackground(Color.white);
        
        
        panel5.add(lTotal,BorderLayout.NORTH);
        panel5.add(lNominalTotal,BorderLayout.CENTER);
        
        
        panel6.add(bBeli);
        panel6.add(bReset);
        panel6.add(bAdmin);
        panel6.add(bExit);
        panel6.setBackground(Color.white);
        
        
        panel3.add(panel4);
        panel3.add(panel5);
        
        
        panel2.add(panel3, BorderLayout.CENTER);
        panel2.add(panel6, BorderLayout.SOUTH);
        
        
        panel0.add(panel1);
        panel0.add(panel2);
        
        
        add(panel0);
        setVisible(true);
        
    }

    @Override
    public void onChange(Model_Barang modelBarang) { //ketika ada perubahan dimodel, maka akan merubah isi tabel
        String kode=modelBarang.getKode();          //dengan syarat harus kelima variabel dibawah yang diubah
        String nama=modelBarang.getNama();         //dan load=5, yaitu variabel untuk menentukan ada berapa variabel yang onChange
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
        
    public String getKode()
    {
        return kodeTemp;
    }

    public String getNama()
    {
        return lNamaBarang.getText();
    }
    
    public String getHarga()
    {
        return lNominalHarga.getText();
    }

    public int getJumlah()
    {
        return jumlahBarangTemp;
    }
    
    public String getSatuan()
    {
        return satuanTemp;
    }
    
    public int getTotal()
    {
        return totalBelanja;
    }

    public void Reset()
    {
            tfJumlah.setText("");
            lNamaBarang.setText("-");
            lNominalHarga.setText("-");
            totalBelanja=0;
            lNominalTotal.setText("Rp.0");
            tabelModel.setRowCount(0);
            controller.loadBarang();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==bAdmin){
            new View_Login(this,controller,modelBarang);
        }
        
        if(ae.getSource()==bReset){
            Reset();
        }
        
        if(ae.getSource()==bExit){
            controller.tutupKoneksi();
            System.exit(0);
        }
        
        if(ae.getSource()==bBeli){
           try
            {
                if(totalBelanja!=0)
                {
                    beli = new Konfirmasi_Beli(this,controller);
                    totalBelanja=0;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Tidak Ada Barang Yang Di Beli");
                    tfJumlah.setText("");
                    lNominalTotal.setText("");
                }
            }
            catch(NumberFormatException nfe)
            {
                    JOptionPane.showMessageDialog(null, "Format Yang Anda Masukkan Salah!","",JOptionPane.ERROR_MESSAGE);
                    tfJumlah.setText("");
            }
        }
        
        if(ae.getSource()==bTambah){  
           try
            {
                /*String cartKode=kode;         //Pengembangan laporan barang
                String cartNama=lNamaBarang.getText();
                String cartJumlah=tfJumlah.getText();
                String keterangan="Barang Keluar";*/
                if(Integer.parseInt(tfJumlah.getText())>jumlahBarangTemp)
                    JOptionPane.showMessageDialog(null, "Stok Tidak Mencukupi");
                else if(Integer.parseInt(tfJumlah.getText())<0)
                    JOptionPane.showMessageDialog(null, "Harap Masukkan Angka Lebih Dari Nol!");
                else
                    {
                        int Total;
                        try{
                            tfJumlah.getText();
                            Total = Integer.parseInt(lNominalHarga.getText())*Integer.parseInt(tfJumlah.getText());
                        }
                        catch(NumberFormatException nfe)
                            {
                                Total=0;
                            }
                        totalBelanja+=Total;
                        lNominalTotal.setText("Rp."+String.valueOf(totalBelanja));  
                        jumlahBarangTemp-=Integer.parseInt(tfJumlah.getText());
                        tabelModel.setValueAt(jumlahBarangTemp, rowPilih, 3);
                        tfJumlah.setText("");
                    }
                //String[] dataCart={cartKode,cartNama,cartJumlah,keterangan};
                //tabelCartModel.addRow(dataCart);
            }
            
            catch(NumberFormatException nfe)
            {
                    JOptionPane.showMessageDialog(null, "Format Yang Anda Masukkan Salah!","",JOptionPane.ERROR_MESSAGE);
                    tfJumlah.setText("");
            }
        }
    }
}
