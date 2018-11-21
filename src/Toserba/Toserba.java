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

import View.*;
import Controller.*;
import Model.*;
import DAO.*;

public class Toserba {
    
    public static void main(String[] args) {
        Model_Barang modelBarang = new Model_Barang();
        DAO dao = new DAO(modelBarang);
        Controller_Barang controller = new Controller_Barang(modelBarang,dao);
        new Menu_Belanja(modelBarang,controller);
    }
    
}
