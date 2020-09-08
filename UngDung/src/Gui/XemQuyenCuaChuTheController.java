/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import DAO.*;
import Entity.*;
import java.io.IOException;
import java.sql.*;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ungdung.UngDung;
import javafx.stage.Screen;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class XemQuyenCuaChuTheController implements Initializable {
    
    @FXML
    private TextField username_input;

    @FXML
    private Button search;

    @FXML
    private TableColumn<QuyenChuThe, String> privilege_col;

    @FXML
    private TableView<QuyenChuThe> table;

    @FXML
    private TableColumn<QuyenChuThe, String> username_col;
    
    @FXML
    private TableColumn<QuyenChuThe, String> table_col;
    
    @FXML
    private TableColumn<QuyenChuThe, String> owner_col;

    @FXML
    private TableColumn<QuyenChuThe, String> ad_col;

    @FXML
    private TableColumn<QuyenChuThe, String> cot_col;

    @FXML
    private TableColumn<QuyenChuThe, String> loaiquyen_col;
    
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    
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
    
    public void LoadQuyenUser(String user)
    {
        ResultSet rs = null;
        
        try {
//            rs = DAO.Select("SELECT * FROM USER_TAB_PRIVS " + "WHERE GRANTEE=" + "'" + user + "'");

            rs = DAO.Select(
"select 'ROLE' typ, grantee grantee, granted_role priv, admin_option ad, '--' tabnm, '--' colnm, '--' owner\n" +
"from dba_role_privs\n" +
"where grantee=" + "'" + user.toUpperCase() + "'" + "\n" +
"union\n" +
"select 'SYSTEM' typ, grantee grantee, privilege priv, admin_option ad, '--' tabnm, '--' colnm,'--' owner\n" +
"from dba_sys_privs\n" +
"where grantee=" + "'" + user.toUpperCase() + "'" + "\n" +
"union\n" +
"select 'TABLE' typ, grantee grantee, privilege priv, grantable ad, table_name tabnm, '--' colnm, owner owner\n" +
"from dba_tab_privs\n" +
"where grantee=" + "'" + user.toUpperCase() + "'" + "\n" +
"union\n" +
"select 'COLUMN' typ, grantee grantee, privilege priv, grantable ad, table_name tabnm, column_name colnm, owner owner\n" +
"from dba_col_privs\n" +
"where grantee=" + "'" + user.toUpperCase() + "'" + "\n" +
"order by 1");

        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<QuyenChuThe> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new QuyenChuThe(rs.getString(2),rs.getString(1),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        loaiquyen_col.setCellValueFactory(new PropertyValueFactory<>("loaiQuyen"));
        privilege_col.setCellValueFactory(new PropertyValueFactory<>("quyen"));
        table_col.setCellValueFactory(new PropertyValueFactory<>("table"));
        cot_col.setCellValueFactory(new PropertyValueFactory<>("cot"));
        owner_col.setCellValueFactory(new PropertyValueFactory<>("nguoiSoHuu"));
        ad_col.setCellValueFactory(new PropertyValueFactory<>("ad"));
        table.setItems(list);
    }
    
    private void SearchButton(){
        search.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            this.LoadQuyenUser(username_input.getText());
        });
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.SearchButton();
        
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("QuanTriHome.fxml",1000,600,"Quản trị Home");
                } catch (IOException ex) {
                    Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }    
    
}
