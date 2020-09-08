/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import DAO.DBConnector;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class DangNhapController implements Initializable {

    @FXML
    private PasswordField pass;
    @FXML
    private Button dang_nhap;
    @FXML
    private TextField username;
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
        dang_nhap.setOnAction(new EventHandler<ActionEvent>() {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            @Override
            public void handle(ActionEvent event) {
                if(username.getText().trim().isEmpty() || pass.getText().trim().isEmpty())
                {   
                    alert.setHeaderText(null);
                    alert.setContentText("Cần điền đầy đủ thông tin");
                    alert.show();
                }
                else 
                {
                    ResultSet rs;
                    DBConnector.setConnection(username.getText(), pass.getText());
                    try {
                        if(DBConnector.con != null)
                        {
                            boolean flag = false;
                            ArrayList<String> list = new ArrayList<>();
                            rs = DAO.Select("SELECT * from sys.THONGTINROLE");
                            while(rs.next())
                            {
                                list.add(rs.getString(1));
                                if(rs.getString(1).compareTo("QUANTRI") == 0)
                                {
                                    Change("QuanTriHome.fxml",1000,600,"Quản trị Home");
                                    flag = true; //neu la quan tri
                                }
                            }
                            DBConnector.vaitro = list;
                            DBConnector.username = username.getText();
                            if(!flag)
                            {
                                Change("UserHome.fxml",541,296,"Chọn vai trò đăng nhập");
                            }
                        }
                        else
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Sai tài khoản/mật khẩu");
                            alert.show();
                        }
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
            }
        });
    }    
    
}
