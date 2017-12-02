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
public class UserSessionLogin {
    private static int id;
    private static String username;
    private static String password;
    private static String nama;
    private static String email;
    private static String otoritas;
    
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserSessionLogin.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserSessionLogin.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserSessionLogin.password = password;
    }
    
    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        UserSessionLogin.nama = nama;
    }
    
    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserSessionLogin.email = email;
    }
    
    public static String getOtoritas() {
        return otoritas;
    }

    public static void setOtoritas(String otoritas) {
        UserSessionLogin.otoritas = otoritas;
    }
    
}
