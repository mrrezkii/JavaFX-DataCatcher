/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import db.Koneksi;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import model.UserSessionLogin;
import model.UserSessionNilai;


/**
 * FXML Controller class
 *
 * @author Rezki
 */
public class LoginPageController implements Initializable {

    @FXML
    private JFXTextField idUsername;
    @FXML
    private JFXPasswordField idPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) throws IOException {
        Koneksi db = new Koneksi();
        String username = idUsername.getText();
        String password = idPassword.getText();
        String nama = "";
        String email = "";
        String otoritas = "";
        int matematika = 0;
        int bahasa_indo = 0;
        int bahasa_inggris = 0;
        int teori_kejuruhan = 0;
        
        db.dbConnection();
        try {        
            String sql = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";
            java.sql.Statement st = db.getConnection().createStatement();
            ResultSet rsLogin = st.executeQuery(sql);

           
           while (rsLogin.next()) {
                nama = rsLogin.getString("nama");
                email = rsLogin.getString("email");
                otoritas = rsLogin.getString("otoritas");
                matematika = rsLogin.getInt("Matematika");
                bahasa_indo = rsLogin.getInt("Indonesia");
                bahasa_inggris = rsLogin.getInt("Inggris");
                teori_kejuruhan = rsLogin.getInt("Kejuruhan");

            }
            rsLogin.last(); //mengecek jumlah baris pada hasil query
            if (rsLogin.getRow()==1){
                UserSessionLogin.setUsername(username);
                UserSessionLogin.setPassword(password);
                UserSessionLogin.setId(rsLogin.getInt("id"));
                UserSessionLogin.setNama(nama);
                UserSessionLogin.setEmail(email);
                UserSessionLogin.setOtoritas(otoritas);
                UserSessionNilai.setMatematika(matematika);
                UserSessionNilai.setBahasa_indonesia(bahasa_indo);
                UserSessionNilai.setBahasa_inggris(bahasa_inggris);
                UserSessionNilai.setTeori_kejuruhan(teori_kejuruhan);

                 if(UserSessionLogin.getOtoritas().equals("User")){
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/view/DashboardPage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("Data Catcher");
                    stage.show();
                }
                else if(UserSessionLogin.getOtoritas().equals("Super User")){
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/view/AdminPage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("Data Catcher");
                    stage.show();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Username / Password salah!");
                idPassword.setText("");
                idPassword.requestFocus();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void Register(ActionEvent event) {
        
    }
    
    
}
