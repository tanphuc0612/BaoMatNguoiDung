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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class SuaThongTinController implements Initializable {

    @FXML
    private Button dongy;
    
    @FXML
    private TextField ma;

    @FXML
    private TextField ten;

    @FXML
    private ComboBox<String> phai;

    @FXML
    private DatePicker ngaySinh;

    @FXML
    private TextField donvi;
    
    @FXML
    private Text tdonvi;
    
    private String tableName;
    private String loai;
    
    public LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
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
    public void getParameter(int ma,String ten,String phai,Date ngaySinh,String donvi,String tableName,String loai){
        this.tableName = tableName;
        this.loai = loai;
        this.ma.setText(Integer.toString(ma));
        this.ten.setText(ten);
        this.phai.getItems().addAll("Nam","Nu");
        this.phai.getSelectionModel().select(phai);
        this.ngaySinh.setValue(convertToLocalDate(ngaySinh));
        this.ngaySinh.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
        if(donvi.compareTo("ẩn")==0)
        {
            this.donvi.setVisible(false);
            this.tdonvi.setVisible(false);
        }
        else this.donvi.setText(donvi);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dongy.setOnAction(new EventHandler<ActionEvent>() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            @Override
            public void handle(ActionEvent event) {
                if(ten.getText().isEmpty())
                {
                    alert.setHeaderText(null);
                    alert.setContentText("Không được để trống tên.");
                    alert.show();
                }
                else
                {
                    String query = "UPDATE QT." + tableName + " SET HoTen = '" + ten.getText() + "', Phai ='" 
                        + phai.getValue() + "', NgaySinh =TO_DATE('" + ngaySinh.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + "','YYYY-MM-DD') WHERE Ma"+ loai + "= '" + ma.getText() + "'";
                    try {
                        if(DAO.Insert(query))
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Cập nhật thành công.");
                            alert.show();
                            DBConnector.con.setAutoCommit(false);
                            DBConnector.con.commit();
                            Stage stage = (Stage) dongy.getScene().getWindow();
                            stage.close();
                            try {
                                Change("BanToChuc.fxml",1000,600,"Ban tổ chức");
                            } catch (IOException ex) {
                                Logger.getLogger(SuaThongTinController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Cập nhật thất bại.");
                            alert.show();
                        }
                        } catch (SQLException ex) {
                        Logger.getLogger(SuaThongTinController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
            }
        });
    }    
    
}
