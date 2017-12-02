/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Rezki
 */
public class TableUser {
    private final IntegerProperty id;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty nama;
    private final StringProperty email;
    private final StringProperty otoritas;
    
    
    public TableUser(int id,String username, String password, String nama, String email, String otoritas){
      this.id = new SimpleIntegerProperty(id);
      this.username = new SimpleStringProperty(username);
      this.password = new SimpleStringProperty(password);
      this.nama = new SimpleStringProperty(nama);
      this.email = new SimpleStringProperty(email);
      this.otoritas = new SimpleStringProperty(otoritas);
    }
    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty UsernameProperty() {
        return username;
    }
    
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty PasswordProperty() {
        return password;
    }
        
    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public StringProperty Nama() {
        return nama;
    }
            
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty EmailProperty() {
        return email;
    }
    
    public String getOtoritas() {
        return otoritas.get();
    }

    public void setOtoritas(String otoritas) {
        this.otoritas.set(otoritas);
    }

    public StringProperty OtoritasProperty() {
        return otoritas;
    }
}
