/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author vur3ris
 */
import Listener.*;
public class Model_Barang {
    
    private String nama,kode,satuan,username,password;
    private int jumlah,harga;
    private Listener_Barang listenerBarang;
    
    protected void fireOnChange()
    {
        if(listenerBarang != null)
            listenerBarang.onChange(this);
    }
    
    public Listener_Barang getBarangListener()
    {
        return listenerBarang;
    }
    
    public void setBarangListener(Listener_Barang listenerBarang)
    {
        this.listenerBarang = listenerBarang;
    }
    
    public void setNama(String nama)
    {
        this.nama=nama;
        fireOnChange();
    }
    
    public String getNama()
    {
        return nama;
    }

    public void setKode(String kode)
    {
        this.kode=kode;
        fireOnChange();
    }
    
    public String getKode()
    {
        return kode;
    }
    public void setHarga(int harga)
    {
        this.harga=harga;
        fireOnChange();
    }
    
    public int getHarga()
    {
        return harga;
    }
    public void setJumlah(int jumlah)
    {
        this.jumlah=jumlah;
        fireOnChange();
    }
    
    public int getJumlah()
    {
        return jumlah;
    }
    public void setSatuan(String satuan)
    {
        this.satuan=satuan;
        fireOnChange();
    }
    
    public String getSatuan()
    {
        return satuan;
    }

    public void setUser(String username)
    {
        this.username=username;
    }
    
    public String getUser()
    {
        return username;
    }
    public void setPass(String password)
    {
        this.password=password;
    }
    
    public String getPass()
    {
        return password;
    }
    
    public void reset()
    {
        kode="";
        nama="";
        harga=0;
        jumlah=0;
    }
}
