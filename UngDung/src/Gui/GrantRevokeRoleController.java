/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class GrantRevokeRoleController implements Initializable {

    @FXML
    private Button grant;

    @FXML
    private Button revoke;

    @FXML
    private Button home;
    
    @FXML
    private ComboBox<String> role;

    @FXML
    private ComboBox<String> user;
    public void Change(String string,int width, int height,String title) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(string));
        Parent View = loader.load();
        Scene scene;
        scene = new Scene(View,width,height); 
        Stage s = UngDung.getPrimaryStage();
        s.setTitle(title);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        s.setX((screenBounds.getWidth() - width) / 2);
        s.setY((screenBounds.getHeight() - height) / 2);
        s.setScene(scene);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rsRole =null;
        ResultSet rsUser =null;
        try {
            rsRole = DAO.Select("select role from session_roles where role <> 'QUANTRI' and role <> 'AUDIT_VIEWER'");
            rsUser = DAO.Select("SELECT USERNAME FROM DBA_USERS WHERE ACCOUNT_STATUS ='OPEN' AND DEFAULT_TABLESPACE='USERS' "
                    + "AND USERNAME <> SYS_CONTEXT ('USERENV', 'SESSION_USER') ORDER BY USER_ID");
        } catch (SQLException ex) {
            Logger.getLogger(GrantRevokeRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> listRole = new ArrayList<>();
        try {
            while(rsRole.next())
                listRole.add(rsRole.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(GrantRevokeRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> listUser = new ArrayList<>();
        try {
            while(rsUser.next())
                listUser.add(rsUser.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(GrantRevokeRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        role.setItems(FXCollections.observableArrayList(listRole));
        role.getSelectionModel().selectFirst();
        user.setItems(FXCollections.observableArrayList(listUser));
        user.getSelectionModel().selectFirst();
        grant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DAO.GrantRole(user.getValue(), role.getValue());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Grant thành công");
                    alert.show();
                } catch (SQLException ex) {
                    Logger.getLogger(GrantRevokeRoleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        revoke.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DAO.RevokeRole(user.getValue(), role.getValue());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Revoke thành công");
                    alert.show();
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Đối tượng chưa được gán role, không thể revoke");
                    alert.show();
                }
            }
        });
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("QuanTriHome.fxml",1000,600,"Quản trị Home");
                } catch (IOException ex) {
                    Logger.getLogger(GrantRevokeRoleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }    
    
}
