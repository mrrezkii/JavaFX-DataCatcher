/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rezki
 */
public class UserSessionNilai {
    private static int id;
    private static int matematika;
    private static int bahasa_indonesia;
    private static int bahasa_inggris;
    private static int teori_kejuruhan;
    private static int pbo;
    private static int ppb;
    private static int ppl;
    private static int asp;
    private static int basdat;
    
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserSessionNilai.id = id;
    }

    public static int getMatematika() {
        return matematika;
    }

    public static void setMatematika(int matematika) {
        UserSessionNilai.matematika = matematika;
    }

    public static int getBahasa_indonesia() {
        return bahasa_indonesia;
    }

    public static void setBahasa_indonesia(int bahasa_indonesia) {
        UserSessionNilai.bahasa_indonesia = bahasa_indonesia;
    }
    
    public static int getBahasa_inggris() {
        return bahasa_inggris;
    }

    public static void setBahasa_inggris(int bahasa_inggris) {
        UserSessionNilai.bahasa_inggris = bahasa_inggris;
    }
    
    public static int getTeori_kejuruhan() {
        return teori_kejuruhan;
    }

    public static void setTeori_kejuruhan(int teori_kejuruhan) {
        UserSessionNilai.teori_kejuruhan = teori_kejuruhan;
    }
    
    public static int getPBO() {
        return pbo;
    }

    public static void setPBO(int pbo) {
        UserSessionNilai.pbo = pbo;
    }
    
    public static int getPPB() {
        return ppb;
    }

    public static void setPPB(int ppb) {
        UserSessionNilai.ppb = ppb;
    }
    
    public static int getPPL() {
        return ppl;
    }

    public static void setPPL(int ppl) {
        UserSessionNilai.ppl = ppl;
    }
    
    public static int getASP() {
        return asp;
    }

    public static void setASP(int asp) {
        UserSessionNilai.asp = asp;
    }
    
    public static int getBASDAT() {
        return basdat;
    }

    public static void setBASDAT(int basdat) {
        UserSessionNilai.basdat = basdat;
    }
}
