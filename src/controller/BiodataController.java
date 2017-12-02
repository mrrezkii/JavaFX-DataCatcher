/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import model.UserSessionLogin;
import db.Config;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Rezki
 */
public class BiodataController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private JFXTextField idUsername;
    @FXML
    private JFXTextField idNama;
    @FXML
    private JFXTextField idEmail;
    @FXML
    private JFXTextField idPassword;
    @FXML
    private JFXComboBox<String> idOtoritas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int id_login = UserSessionLogin.getId();
        String username = UserSessionLogin.getUsername();
        String password = UserSessionLogin.getPassword();
        String nama = UserSessionLogin.getNama();
        String email = UserSessionLogin.getEmail();
        
        id.setText(""+id_login);
        idUsername.setText(username);
        idPassword.setText(password);
        idNama.setText(nama);
        idEmail.setText(email);
        idOtoritas.setValue("User");
        
    }    

    @FXML
    private void Update(ActionEvent event) throws IOException {
        try{
            String sql = "UPDATE login SET username='"+idUsername.getText()+"', password='"+idPassword.getText()+"', nama='"+idNama.getText()+"' ,email='"+idEmail.getText()+"' Where id="+UserSessionLogin.getId();
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Log Out to Effect");
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    
}
