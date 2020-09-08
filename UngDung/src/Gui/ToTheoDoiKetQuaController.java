/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import Entity.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ToTheoDoiKetQuaController implements Initializable {

    @FXML
    private TableColumn<NguoiDiBau, String> tentv_col1;

    @FXML
    private TableColumn<PhieuBauUCV, String> tentv_col2;

    @FXML
    private TableColumn<NguoiDiBau, String> ngaysinh_col1;

    @FXML
    private TableColumn<NguoiDiBau, String> tinhtrang_col1;

    @FXML
    private TableColumn<PhieuBauUCV, String> matv_col2;

    @FXML
    private TableColumn<PhieuBauUCV, String> sophieubau_col2;

    @FXML
    private TableColumn<NguoiDiBau, String> matv_col1;

    @FXML
    private TableColumn<NguoiDiBau, String> donvi_col1;

    @FXML
    private TableColumn<PhieuBauUCV, String> phai_col2;

    @FXML
    private TableColumn<PhieuBauUCV, String> ngaysinh_col2;

    @FXML
    private TableColumn<NguoiDiBau, String> phai_col1;

    @FXML
    private TableView<PhieuBauUCV> table2;

    @FXML
    private TableView<NguoiDiBau> table1;

    @FXML
    private Button home;

    public void Change(String string, int width, int height, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(string));
        Parent View = loader.load();
        Scene scene;
        scene = new Scene(View, width, height);
        Stage s = UngDung.getPrimaryStage();
        s.setTitle(title);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        s.setX((screenBounds.getWidth() - width) / 2);
        s.setY((screenBounds.getHeight() - height) / 2);
        s.setScene(scene);
    }

    public void LoadNguoiDiBauTable() {
        ResultSet rs = null;
        try {
            rs = DAO.Select("SELECT * FROM QT.DSNGUOIDADIBAU");
        } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<NguoiDiBau> list = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                list.add(new NguoiDiBau(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6)));
                matv_col1.setCellValueFactory(new PropertyValueFactory<>("matv"));
                tentv_col1.setCellValueFactory(new PropertyValueFactory<>("ten"));
                phai_col1.setCellValueFactory(new PropertyValueFactory<>("phai"));
                ngaysinh_col1.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
                donvi_col1.setCellValueFactory(new PropertyValueFactory<>("donvi"));
                tinhtrang_col1.setCellValueFactory(new PropertyValueFactory<>("tinhtrang"));
                table1.setItems(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadUCVTable() {
        ResultSet rs = null;
        ResultSet rs1 = null;
        ObservableList<PhieuBauUCV> list = FXCollections.observableArrayList();
        try {
            rs = DAO.Select("SELECT * FROM QT.UNGCUVIEN");
            while (rs.next()) {
                int i = 0;
                rs1 = DAO.Select("SELECT * FROM QT.DSPHIEUBAUUCV WHERE MAUCV = " + Integer.toString(rs.getInt(1)));
                while (rs1.next()) {
                    i = rs1.getInt(5);
                    list.add(new PhieuBauUCV(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getDate(4),  Integer.toString(i)));
                }
                if(i==0)
                    list.add(new PhieuBauUCV(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), Integer.toString(i)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        matv_col2.setCellValueFactory(new PropertyValueFactory<>("maUCV"));
        tentv_col2.setCellValueFactory(new PropertyValueFactory<>("ten"));
        phai_col2.setCellValueFactory(new PropertyValueFactory<>("phai"));
        ngaysinh_col2.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
        sophieubau_col2.setCellValueFactory(new PropertyValueFactory<>("soPhieuBau"));
        table2.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.LoadNguoiDiBauTable();
        this.LoadUCVTable();
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("UserHome.fxml", 541, 296, "Chọn vai trò đăng nhập");
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
