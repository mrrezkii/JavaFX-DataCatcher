/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import db.Config;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.TableUser;
import model.Time;
import model.UserSessionLogin;

/**
 * FXML Controller class
 *
 * @author Rezki
 */
public class AdminPageUserController implements Initializable {

    @FXML
    private Label idNama;
    @FXML
    private Label idEmail;
    @FXML
    private Label idTanggal;
    @FXML
    private Label idWaktu;
    @FXML
    private TableView<TableUser> tableUser;
    @FXML
    private TableColumn<TableUser, Integer> colId;
    @FXML
    private TableColumn<TableUser, String> colUsername;
    @FXML
    private TableColumn<TableUser, String> colPassword;
    @FXML
    private TableColumn<TableUser, String> colNama;
    @FXML
    private TableColumn<TableUser, String> colEmail;
    @FXML
    private TableColumn<TableUser, String> colOtoritas;
    private Config dc;
    private ObservableList<TableUser> data;

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



     tableUser.setOnMousePressed(new EventHandler<MouseEvent>() {
     @Override
     public void handle(MouseEvent event){
        int id = tableUser.getSelectionModel().getSelectedItem().getId(); 
        String username = tableUser.getSelectionModel().getSelectedItem().getUsername(); 
        String password = tableUser.getSelectionModel().getSelectedItem().getPassword();
        String nama = tableUser.getSelectionModel().getSelectedItem().getNama() ;
        String email = tableUser.getSelectionModel().getSelectedItem().getEmail() ;
        String otoritas = tableUser.getSelectionModel().getSelectedItem().getOtoritas() ;
        }
      }
    );
    try {
        java.sql.Connection con = dc.configDB();
        data = FXCollections.observableArrayList();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `login`");
            while (rs.next()) {
                data.add(new TableUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(3), rs.getString(5)));
            }
    } catch (SQLException ex) {
         System.out.println("Erorr"+ex);

    }
    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
    colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    colOtoritas.setCellValueFactory(new PropertyValueFactory<>("otoritas"));
    tableUser.setItems(null);
    tableUser.setItems(data);








        
        
        
        
        
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
    private void Dashboard(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/view/AdminPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
      
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
}
