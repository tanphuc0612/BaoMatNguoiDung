/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import DAO.*;
import Entity.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class XemDoiTuongController implements Initializable {

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> userIDCol;

    @FXML
    private TableColumn<User, String> usernameCol;

    @FXML
    private TableView<Table> tableTable;

    @FXML
    private TableColumn<Table, Integer> tableIDCol;

    @FXML
    private TableColumn<Table, String> tableNameCol;

    @FXML
    private TableView<Role> roleTable;

    @FXML
    private TableColumn<Role, Integer> roleIDCol;

    @FXML
    private TableColumn<Role, String> roleCol;

    @FXML
    private TableView<View> viewTable;

    @FXML
    private TableColumn<View, Integer> viewIDCol;

    @FXML
    private TableColumn<View, String> viewNameCol;

    @FXML
    private Button home;
    
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
    public void LoadUserTable()
    {
        ResultSet rs = null;
        try {
            rs = DAO.Select("SELECT * FROM DBA_USERS WHERE ACCOUNT_STATUS ='OPEN' AND DEFAULT_TABLESPACE='USERS' ORDER BY USER_ID");
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new User(rs.getInt(2),rs.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userid"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        userTable.setItems(list);
    }
    public void LoadRoleTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("select * from session_roles");
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Role> list = FXCollections.observableArrayList();
        try {
            int i=1;
            while(rs.next()){
                list.add(new Role(i++,rs.getString(1)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        roleIDCol.setCellValueFactory(new PropertyValueFactory<>("roleid"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleTable.setItems(list);
    }
    public void LoadTableTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM all_tables where owner= SYS_CONTEXT ('USERENV', 'SESSION_USER')");
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Table> list = FXCollections.observableArrayList();
        try {
            int i=1;
            while(rs.next()){
                list.add(new Table(i++,rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableIDCol.setCellValueFactory(new PropertyValueFactory<>("tableid"));
        tableNameCol.setCellValueFactory(new PropertyValueFactory<>("tablename"));
        tableTable.setItems(list);
    }
    public void LoadViewTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM all_views where owner= SYS_CONTEXT ('USERENV', 'SESSION_USER')");
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<View> list = FXCollections.observableArrayList();
        try {
            int i=1;
            while(rs.next()){
                list.add(new View(i++,rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        viewIDCol.setCellValueFactory(new PropertyValueFactory<>("viewid"));
        viewNameCol.setCellValueFactory(new PropertyValueFactory<>("viewname"));
        viewTable.setItems(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.LoadUserTable();
        this.LoadRoleTable();
        this.LoadTableTable();
        this.LoadViewTable();
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
