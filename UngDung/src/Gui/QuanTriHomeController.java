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
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuanTriHomeController implements Initializable {

    @FXML
    private Button xemDoiTuong;

    @FXML
    private Button themUserRole;

    @FXML
    private Button grantRevoke;
    
    @FXML
    private Button grantRevokeRole;

    @FXML
    private Button xemQuyenChuThe;

    @FXML
    private Button dangXuat;
    @FXML
    private Button audit;
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
        xemDoiTuong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("XemDoiTuong.fxml",1000,600,"Xem đối tượng");
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        themUserRole.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("ThemDoiTuong.fxml",1000,600,"Thêm đối tượng");
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grantRevoke.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("GrantRevoke.fxml",1000,600,"Phân quyền/Thu quyền trên bảng");
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grantRevokeRole.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("GrantRevokeRole.fxml",1000,600,"Phân quyền/Thu quyền Role");
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        xemQuyenChuThe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("XemQuyenCuaChuThe.fxml",1000,600,"Xem quyền của chủ thể");
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        dangXuat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DBConnector.con.close();
                    Change("DangNhap.fxml",541,296,"Đăng nhập");
                } catch (SQLException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        audit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("Audit.fxml",1000,600,"Nhật kí hoạt động");
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }    
    
}
