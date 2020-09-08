/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DBConnector;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class UserHomeController implements Initializable {

    @FXML
    private ComboBox<String> roleCb;
    @FXML
    private Label username;
    @FXML
    private Button chon;
    @FXML
    private Button dangXuat;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.username.setText(DBConnector.username);
        roleCb.setItems(FXCollections.observableArrayList(DBConnector.vaitro));
        roleCb.getSelectionModel().selectFirst();
        chon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String new_scene = null, title = null;
                UngDung.setUsername(username.getText());
                if (roleCb.getValue()==null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn không có quyền.");
                    alert.show();
                } 
                else {
                    if (roleCb.getValue().compareTo("NGUOIDIBAU") == 0) {
                        new_scene = "DiBau.fxml";
                        title = "Bầu cử";
                    } else if (roleCb.getValue().compareTo("LAPDANHSACHDIBAU") == 0) {
                        new_scene = "ToLapDanhSachNguoiDiBau.fxml";
                    } else if (roleCb.getValue().compareTo("BANTOCHUC") == 0) {
                        new_scene = "BanToChuc.fxml";
                        title = "Ban tổ chức";
                    } else if (roleCb.getValue().compareTo("GIAMSAT") == 0) {
                        new_scene = "ToGiamSat.fxml";
                        title = "Tổ giám sát";
                    } else if (roleCb.getValue().compareTo("THEODOIKETQUA") == 0) {
                        new_scene = "ToTheoDoiKetQua.fxml";
                        title = "Theo dõi kết quả";
                    } else if (roleCb.getValue().compareTo("GIAMSAT") == 0) {
                        new_scene = "ToGiamSat.fxml";
                        title = "Giám sát";
                    }
                    try {
                        Change(new_scene, 1000, 600, title);
                    } catch (IOException ex) {
                        Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        dangXuat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBConnector.con.close();
                    Change("DangNhap.fxml", 541, 296, "Đăng nhập");
                } catch (SQLException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
