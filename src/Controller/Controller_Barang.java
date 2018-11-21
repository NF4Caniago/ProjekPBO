
package Controller;



import View.*;
import Model.*;
import DAO.*;
import javax.swing.JOptionPane;

public class Controller_Barang {
    private Model_Barang modelBarang;
    private DAO dao;
    
    public Controller_Barang(Model_Barang modelBarang,DAO dao)
    {
        this.modelBarang=modelBarang;
        this.dao=dao;
    }
    
    public void reset(Menu_Admin menuAdmin)
    {
        if(menuAdmin.getKode().equals("") && menuAdmin.getNama().equals("") && menuAdmin.getHarga().equals("0") && menuAdmin.getJumlah().equals(0))
        {
            modelBarang.reset();
        }
        else
        {
            modelBarang.reset();
            menuAdmin.tfKode.setText(modelBarang.getKode());
            menuAdmin.tfNama.setText(modelBarang.getNama());
            menuAdmin.tfHarga.setText(String.valueOf(modelBarang.getHarga()));
            menuAdmin.tfJumlah.setText(String.valueOf(modelBarang.getJumlah()));
            menuAdmin.cmbSatuan.setSelectedIndex(0);
        }
    }
    
    public void saveBarang(Menu_Admin menuAdmin)
    {        
        if(checkField(menuAdmin))
        {
            int jumlah=Integer.parseInt(menuAdmin.getJumlah());
            int harga=Integer.parseInt(menuAdmin.getHarga());
            try
            {
                if(jumlah>=0 && harga>=0)
                {
                    modelBarang.setNama(menuAdmin.getNama());
                    modelBarang.setKode(menuAdmin.getKode());
                    modelBarang.setHarga(harga);
                    modelBarang.setJumlah(jumlah);
                    modelBarang.setSatuan(menuAdmin.getSatuan());
                    dao.insert();
                }
                else
                    JOptionPane.showMessageDialog(null, "Format Yang Dimasukkan Salah!");
            }
            catch(NumberFormatException nfe)
            {
                JOptionPane.showMessageDialog(menuAdmin, "Harus Input Dengan Angka");
            }
        }
    }
    
    public void laporanBarang()
    {
        
    }
    
    public void loadBarang()
    {
        dao.load();
    }
    
    public boolean infoLogin(View_Login viewLogin)
    {
        modelBarang.setUser(viewLogin.getUser());
        modelBarang.setPass(viewLogin.getPass());
        if(dao.getInfoLogin())
            return true;
        else
            return false;
    }
    
    /*public void updateBarang(Menu_Admin menuAdmin)
    {
        if(checkField(menuAdmin))
        {
            modelBarang.setNama(menuAdmin.getNama());
            modelBarang.setKode(menuAdmin.getKode());
            modelBarang.setHarga(Integer.parseInt(menuAdmin.getHarga()));
            modelBarang.setJumlah(Integer.parseInt(menuAdmin.getJumlah())); 
            modelBarang.setSatuan(menuAdmin.getSatuan());
            dao.update(modelBarang);
        }
    }*/
    
    public boolean updateBarang(Menu_Admin menuAdmin)
    {
        
        if(checkField(menuAdmin))
        {
            int jumlah=Integer.parseInt(menuAdmin.getJumlah());
            int harga=Integer.parseInt(menuAdmin.getHarga());
            if(jumlah>=0 && harga>=0)
            {
                modelBarang.setKode(menuAdmin.getKode());
                modelBarang.setNama(menuAdmin.getNama());
                modelBarang.setHarga(harga);
                modelBarang.setJumlah(jumlah);
                modelBarang.setSatuan(menuAdmin.getSatuan());
                if(dao.update())
                    return true;
                else
                    return false;
            }
            else
                JOptionPane.showMessageDialog(null, "Format Yang Dimasukkan Salah!");
        }
        return false;
    }
    
    public boolean updateBarang(Menu_Belanja menuBelanja)
    {
        int jumlah=menuBelanja.getJumlah();
        if(jumlah>=0)
        {
            modelBarang.setKode(menuBelanja.getKode());
            modelBarang.setNama(menuBelanja.getNama());
            modelBarang.setHarga(Integer.parseInt(menuBelanja.getHarga()));
            modelBarang.setJumlah(jumlah);
            modelBarang.setSatuan(menuBelanja.getSatuan());
            if(dao.update())
                return true;
            else
                return false;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Jumlah Yang Dimasukkan Salah!");
            return false;
        }
    }
    
    public void deleteBarang(Menu_Admin menuAdmin)
    {
        dao.delete(menuAdmin);
    }
    
    public boolean checkField(Menu_Admin menuAdmin)
    {
        if(menuAdmin.getKode().trim().equals(""))
        {
            JOptionPane.showMessageDialog(menuAdmin, "Kode Barang Masih Kosong!");
            return false;
        }
        else if(menuAdmin.getNama().trim().equals(""))
        {
            JOptionPane.showMessageDialog(menuAdmin, "Nama Barang Masih Kosong!");
            return false;
        }
        else if(menuAdmin.getHarga().trim().equals("0") || menuAdmin.getHarga().trim().equals(""))
        {
            JOptionPane.showMessageDialog(menuAdmin, "Harga Masih Kosong");
            return false;
        }
        else if(menuAdmin.getJumlah().trim().equals(""))
        {
            JOptionPane.showMessageDialog(menuAdmin, "Harap Isi Jumlah Barang!");
            return false;
        }
        else if(menuAdmin.getSatuan().trim().equals("-PILIH-"))
        {
            JOptionPane.showMessageDialog(menuAdmin, "Satuan Barang Belum Dipilih!");
            return false;
        }
        else
            return true;
    }
    
    public boolean checkBarang(Menu_Admin menuAdmin)
    {
        if(dao.checkBarang(menuAdmin.getNama(),menuAdmin.getKode()))
        {
            return false; //false menandakan nama/kode tidak bisa dipakai
        }
        else
            return true;    //true menandakan nama/kode bisa dipakai
    }
    
    public void tutupKoneksi()
    {
        dao.tutupKoneksi();
    }
}