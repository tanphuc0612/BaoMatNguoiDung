/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import Entity.ViewTaoUser;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ThemDoiTuongController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnTao;
    @FXML
    private Button btnRole;
    @FXML
    private Button xoaRole;
    @FXML
    private TableView<ViewTaoUser> table;
    @FXML
    private TableColumn<ViewTaoUser, Integer> MaTV;
    @FXML
    private TableColumn<ViewTaoUser, String> Ten;
    @FXML
    private TableColumn<ViewTaoUser, String> Phai;
    @FXML
    private TableColumn<ViewTaoUser, String> NoiCongTac;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField role;
    @FXML
    private ChoiceBox choice;

    public void HomeButton() {
        btnHome.setOnMouseClicked((MouseEvent event) -> {
            try {
                Change("QuanTriHome.fxml", 1000, 600, "Quản trị Home");
            } catch (IOException ex) {
                Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

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

    public void TableView() {
        ResultSet rs = null;
        try {
            rs = DAO.Select("SELECT * FROM QT.ThanhVien where username is null");
        } catch (SQLException ex) {
            Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<ViewTaoUser> list = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                list.add(new ViewTaoUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(5), rs.getString(9),rs.getInt(12)));
                MaTV.setCellValueFactory(new PropertyValueFactory<>("MaTV"));
                Ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
                Phai.setCellValueFactory(new PropertyValueFactory<>("Phai"));
                NoiCongTac.setCellValueFactory(new PropertyValueFactory<>("NoiCongTac"));
                table.setItems(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init() {
        String st[] = {"BTC", "Tổ lập danh sách", "Tổ giám sát", "Tổ kết quả", "Người bình thường", "Ứng cử viên"};
        choice.setItems(FXCollections.observableArrayList(st));
        choice.setValue("Người bình thường");
    }

    public void TaoButton() {
        btnTao.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            if (table.getSelectionModel().getSelectedItem() != null) {
                if (username.getText().isEmpty() || password.getText().isEmpty()) {
                    alert.setContentText("Bạn cần nhập đủ username và pass");
                } else {
                    try {
                        if (this.checkTonTaiUser()) {
                            alert.setContentText("User hoặc role đã tồn tại trong hệ thống");
                        } else {
                            ViewTaoUser view = table.getSelectionModel().getSelectedItem();
                            try {
                                DAO.CreateUser(username.getText(), password.getText());
                                DAO.Insert("Update QT.ThanhVien set Username = '" + username.getText() + "' where MaTV = " + view.getMaTV());
                                this.GanQuyenUser(view);
                                alert.setContentText("tạo thành công");
                                Change("ThemDoiTuong.fxml", 1000, 600, "Thêm đối tượng");
                            } catch (SQLException ex) {
                                Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                alert.show();
                this.TableView();
            }
        });
    }

    public void TaoRoleButton() {
        btnRole.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            if (role.getText().isEmpty()) {
                alert.setContentText("Bạn cần nhập tên role");
            } else {
                try {
                    if (this.checkTonTaiRole()) {
                        alert.setContentText("User hoặc role đã tồn tại trong hệ thống");
                    } else {
                        try {
                            DAO.CreateRole(role.getText());
                            alert.setContentText("tạo thành công");
                        } catch (SQLException ex) {
                            Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            alert.show();
        });
    }
    public void XoaRoleButton() {
        xoaRole.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            if (role.getText().isEmpty()) {
                alert.setContentText("Bạn cần nhập tên role");
            } else {
                try {
                    if (!this.checkTonTaiRole()) {
                        alert.setContentText("User hoặc role không tồn tại trong hệ thống");
                    } else {
                        try {
                            DAO.DropRole(role.getText());
                            alert.setContentText("xóa role thành công");
                            role.setText("");
                        } catch (SQLException ex) {
                            Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            alert.show();
        });
    }
    public boolean checkTonTaiUser() throws SQLException {
        ResultSet rs1 = DAO.Select("SELECT * FROM DBA_USERS WHERE USERNAME = '" + username.getText().toUpperCase() + "'");
        ResultSet rs2 = DAO.Select("select * from session_roles where ROLE = '" + username.getText().toUpperCase() + "'");
        return (rs1.next() || rs2.next());
    }

    public boolean checkTonTaiRole() throws SQLException {
        ResultSet rs1 = DAO.Select("SELECT * FROM DBA_ROLES WHERE ROLE = '" + role.getText().toUpperCase() + "'");
        return (rs1.next());
    }

    public void GanQuyenUser(ViewTaoUser view) throws SQLException {
        if (choice.getValue().equals("BTC")) {
            DAO.GrantRole(username.getText(), "BANTOCHUC");
            DAO.Insert("INSERT INTO BANTOCHUC VALUES(" + Integer.toString(view.getMaTV()) + ",'" + view.getTen() + "','" + view.getPhai()
                    + "',TO_DATE('" + view.getNgaySinh().toString() + "','YYYY-MM-DD'))");
        } else if (choice.getValue().equals("Tổ lập danh sách")) {
            DAO.GrantRole(username.getText(), "LAPDANHSACHDIBAU");
            DAO.Insert("INSERT INTO TOGIAMSAT VALUES(" + Integer.toString(view.getMaTV()) + ",'" + view.getTen() + "','" + view.getPhai()
                    + "',TO_DATE('" + view.getNgaySinh().toString() + "','YYYY-MM-DD'),'" + Integer.toString(view.getMaDonVi()) +"','" +username.getText() +"')");
        } else if (choice.getValue().equals("Tổ giám sát")) {
            DAO.GrantRole(username.getText(), "GIAMSAT");
            DAO.Insert("INSERT INTO TOGIAMSAT VALUES(" + Integer.toString(view.getMaTV()) + ",'" + view.getTen() + "','" + view.getPhai()
                    + "',TO_DATE('" + view.getNgaySinh().toString() + "','YYYY-MM-DD'))");
        } else if (choice.getValue().equals("Tổ kết quả")) {
            DAO.GrantRole(username.getText(), "THEODOIKETQUA");
            DAO.Insert("INSERT INTO TOTHEODOI VALUES(" + Integer.toString(view.getMaTV()) + ",'" + view.getTen() + "','" + view.getPhai()
                    + "',TO_DATE('" + view.getNgaySinh().toString() + "','YYYY-MM-DD'))");
        } else if (choice.getValue().equals("Ứng cử viên")){
            DAO.Insert("INSERT INTO UNGCUVIEN VALUES(" + Integer.toString(view.getMaTV()) + ",'" + view.getTen() + "','" + view.getPhai()
                    + "',TO_DATE('" + view.getNgaySinh().toString() + "','YYYY-MM-DD'))");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.HomeButton();
        this.TableView();
        this.init();
        this.TaoButton();
        this.TaoRoleButton();
        this.XoaRoleButton();
    }
}
