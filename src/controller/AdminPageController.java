/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Time;
import model.UserSessionLogin;

/**
 * FXML Controller class
 *
 * @author Rezki
 */
public class AdminPageController implements Initializable {

    @FXML
    private Label idNama;
    @FXML
    private Label idEmail;
    @FXML
    private Label idTanggal;
    @FXML
    private Label idWaktu;
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
        String nama = UserSessionLogin.getNama();
        String email = UserSessionLogin.getEmail();
        
        idNama.setText(nama);
        idEmail.setText(email);
        bindToTime();
        Time time = new Time();
        idTanggal.setText(time.tanggal());
    }    

    @FXML
    private void LogOt(ActionEvent event) throws IOException {
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
    private void User(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/AdminPageUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
      
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
}
