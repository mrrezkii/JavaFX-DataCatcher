/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.UserSessionLogin;
import db.Config;
import db.Koneksi;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import model.Time;
import model.UserSessionNilai;

/**
 * FXML Controller class
 *
 * @author Rezki
 */
public class DashboardPageNilaiController implements Initializable {
    @FXML
    private Label idNama;
    @FXML
    private Label idEmail;
    @FXML
    private Label idTanggal;
    @FXML
    private Label idWaktu;
    @FXML
    private JFXTextField idMatematika;
    @FXML
    private JFXTextField idBahasaIndo;
    @FXML
    private JFXTextField idBahasaInggris;
    @FXML
    private JFXTextField idTeoriKejuruhan;
    @FXML
    private Label idAkreditasi;
    @FXML
    private JFXButton idTampil;
    String nama = UserSessionLogin.getNama();
    String email = UserSessionLogin.getEmail();
    int mm  = UserSessionNilai.getMatematika();
    int bi = UserSessionNilai.getBahasa_indonesia();
    int bing = UserSessionNilai.getBahasa_inggris();
    int kejuruhan = UserSessionNilai.getTeori_kejuruhan();
    /**
     * Initializes the controller class.
     */
    private void bindToTime() {
        Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(0),
          new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
              Calendar time = Calendar.getInstance();
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
              idWaktu.setText(simpleDateFormat.format(time.getTime()));
            }
          }
        ),
        new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idNama.setText(nama);
        idEmail.setText(email);
        bindToTime();
        Time time = new Time();
        idTanggal.setText(time.tanggal());
        idMatematika.setText(""+mm);
        idBahasaIndo.setText(""+bi);
        idBahasaInggris.setText(""+bing);
        idTeoriKejuruhan.setText(""+kejuruhan);    
    }    

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Data Catcher");
        stage.show();
    }

    @FXML
    private void Dashboard(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/DashboardPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    @FXML
    void Tampil(ActionEvent event) {
        int kondisi;
        kondisi = (mm + bi + bing + kejuruhan)/4;
        
        if(kondisi > 89 && kondisi <= 100){
            idAkreditasi.setText("A");
        }
        else if(kondisi > 74 && kondisi <= 89){
            idAkreditasi.setText("B");
        }
        else if(kondisi  <=74){
            idAkreditasi.setText("C");
        }
        else if(kondisi > 100){
            idAkreditasi.setText("invalid");
        }
    }
    @FXML
    private void Update(ActionEvent event) {
        
        try{
            String sql = "UPDATE login SET Matematika="+idMatematika.getText()+",Indonesia ="+idBahasaIndo.getText()+",Inggris ="+idBahasaInggris.getText()+",Kejuruhan ="+idTeoriKejuruhan.getText()+" Where id="+UserSessionLogin.getId();
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Press the Refresh Button");
        }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void Biodata(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Biodata.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Data Catcher");
        stage.show();
    }
    @FXML
    void Refresh(ActionEvent event) throws IOException {
        Koneksi db = new Koneksi();
        String username = UserSessionLogin.getUsername();
        String password = UserSessionLogin.getPassword();
        String namareload = "";
        String emailreload = "";
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
                namareload = rsLogin.getString("nama");
                emailreload = rsLogin.getString("email");
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
                UserSessionLogin.setNama(namareload);
                UserSessionLogin.setEmail(emailreload);
                UserSessionLogin.setOtoritas(otoritas);
                UserSessionNilai.setMatematika(matematika);
                UserSessionNilai.setBahasa_indonesia(bahasa_indo);
                UserSessionNilai.setBahasa_inggris(bahasa_inggris);
                UserSessionNilai.setTeori_kejuruhan(teori_kejuruhan);

                    Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/DashboardPageNilai.fxml"));
                    Scene tableViewScene = new Scene(tableViewParent);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(tableViewScene);
                    window.show();

                    idMatematika.setText(""+mm);
                    idBahasaIndo.setText(""+bi);
                    idBahasaInggris.setText(""+bing);
                    idTeoriKejuruhan.setText(""+kejuruhan);    
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
