/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import Entity.ThanhVien;
import Entity.ThanhVienWithCheckBox;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author mymy4
 */
public class ToLapDanhSachNguoiDiBauController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<ThanhVienWithCheckBox> userTable;

    @FXML
    private TableColumn<ThanhVien, String> tbc_NgaySinh;

    @FXML
    private TableColumn<ThanhVien, String> tbc_DcTamTru;

    @FXML
    private TableColumn<ThanhVien, String> tbc_Phai;

    @FXML
    private TableColumn<ThanhVien, String> tbc_QueQuan;

    @FXML
    private TableColumn<ThanhVien, String> tbc_Action;

    @FXML
    private TableColumn<ThanhVien, String> tbc_DCThuongTru;

    @FXML
    private TableColumn<ThanhVien, String> tbc_QuocTich;

    @FXML
    private TableColumn<ThanhVien, String> tbc_HoTen;

    @FXML
    private TableColumn<ThanhVien, String> tbc_TrangThai;

    @FXML
    private TableColumn<ThanhVien, String> tbc_Username;

    @FXML
    private Button btn_QuayVe;

    @FXML
    private Button btn_ApDung;

    @FXML
    private Button btn_LocTrangThai;

    @FXML
    private Button btn_LocDuocBau;

    @FXML
    private Button btn_Edit;

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
    ObservableList<ThanhVienWithCheckBox> list = FXCollections.observableArrayList();
    ObservableList<ThanhVienWithCheckBox> listTT = FXCollections.observableArrayList();
    ObservableList<ThanhVienWithCheckBox> listDB = FXCollections.observableArrayList();

    public void LoadUserTableDonVi() {
        ResultSet rs = null;
        try {
            rs = DAO.Select("SELECT * FROM QT.THANHVIEN");
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        ObservableList<ThanhVienWithCheckBox> list = FXCollections.observableArrayList();
        list.clear();
        try {
            while (rs.next()) {
                list.add(new ThanhVienWithCheckBox(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getString(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tbc_Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tbc_HoTen.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        tbc_Phai.setCellValueFactory(new PropertyValueFactory<>("Phai"));
        tbc_QueQuan.setCellValueFactory(new PropertyValueFactory<>("QueQuan"));
        tbc_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        tbc_QuocTich.setCellValueFactory(new PropertyValueFactory<>("QuocTich"));
        tbc_DCThuongTru.setCellValueFactory(new PropertyValueFactory<>("DcThuongTru"));
        tbc_DcTamTru.setCellValueFactory(new PropertyValueFactory<>("DcTamTru"));
        tbc_Action.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        tbc_TrangThai.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));

        userTable.setItems(list);
    }

    public void ApDungButton() {
        btn_ApDung.setOnMouseClicked((MouseEvent event) -> {
            ObservableList<ThanhVienWithCheckBox> listdataRemove = FXCollections.observableArrayList();

            int n = 0;
            String sql1 = "update qt.THANHVIEN set DuocBau=0 where MaTV = ";
            String sql = "";
            String sql2 = "";
            //String sqlRVR="";
            for (ThanhVienWithCheckBox item : list) {
                if (item.getCheckbox().isSelected()) {
                    listdataRemove.add(item);
                    list.get(n).setDuocBau(0);
                    sql2 = Integer.toString(list.get(n).getMaTV());
//                    try {
//                        String uName= DAO.LayUserName(Integer.valueOf(sql2));
//                        sqlRVR="Revoke NGUOIDIBAU from "+ uName;
//                        System.out.println("String sql = " + sqlRVR);
//                        DAO.Execute(sqlRVR);
//                    } catch (SQLException ex) {
//                        Logger.getLogger(ToLapDanhSachNguoiDiBauController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    sql = sql1 + sql2;
                    try {
                        DAO.Insert(sql);
                        System.out.println("String sql = " + sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(ToLapDanhSachNguoiDiBauController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                n++;
            }

            list.removeAll(listdataRemove);

        });
    }

    public void LocTrangThaiButton() {
        btn_LocTrangThai.setOnMouseClicked((MouseEvent event) -> {
            ResultSet rs = null;
            try {
                rs = DAO.Select("SELECT * FROM QT.THANHVIEN where TRANGTHAI=1");
            } catch (SQLException ex) {
                Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
//        ObservableList<ThanhVienWithCheckBox> list = FXCollections.observableArrayList();
            listTT.clear();
            try {
                while (rs.next()) {
                    listTT.add(new ThanhVienWithCheckBox(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getString(13), rs.getInt(14)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tbc_Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
            tbc_HoTen.setCellValueFactory(new PropertyValueFactory<>("Ten"));
            tbc_Phai.setCellValueFactory(new PropertyValueFactory<>("Phai"));
            tbc_QueQuan.setCellValueFactory(new PropertyValueFactory<>("QueQuan"));
            tbc_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
            tbc_QuocTich.setCellValueFactory(new PropertyValueFactory<>("QuocTich"));
            tbc_DCThuongTru.setCellValueFactory(new PropertyValueFactory<>("DcThuongTru"));
            tbc_DcTamTru.setCellValueFactory(new PropertyValueFactory<>("DcTamTru"));
            tbc_Action.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));
            tbc_TrangThai.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));

            userTable.setItems(listTT);

        });
    }

    public void LocDuocBauButton() {
        btn_LocDuocBau.setOnMouseClicked((MouseEvent event) -> {
            ResultSet rs = null;
            try {
                rs = DAO.Select("SELECT * FROM QT.THANHVIEN where DUOCBAU=1");
            } catch (SQLException ex) {
                Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
//        ObservableList<ThanhVienWithCheckBox> list = FXCollections.observableArrayList();
            listDB.clear();
            try {
                while (rs.next()) {
                    listDB.add(new ThanhVienWithCheckBox(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getInt(10), rs.getString(11), rs.getInt(12), rs.getString(13), rs.getInt(14)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
            }

            tbc_Username.setCellValueFactory(new PropertyValueFactory<>("Username"));
            tbc_HoTen.setCellValueFactory(new PropertyValueFactory<>("Ten"));
            tbc_Phai.setCellValueFactory(new PropertyValueFactory<>("Phai"));
            tbc_QueQuan.setCellValueFactory(new PropertyValueFactory<>("QueQuan"));
            tbc_NgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
            tbc_QuocTich.setCellValueFactory(new PropertyValueFactory<>("QuocTich"));
            tbc_DCThuongTru.setCellValueFactory(new PropertyValueFactory<>("DcThuongTru"));
            tbc_DcTamTru.setCellValueFactory(new PropertyValueFactory<>("DcTamTru"));
            tbc_Action.setCellValueFactory(new PropertyValueFactory<>("DuocBau"));
            tbc_TrangThai.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));

            userTable.setItems(listDB);

        });
    }

    public void EditButton() {
        btn_Edit.setOnMouseClicked((MouseEvent event) -> {
            this.LoadUserTableDonVi();

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.LoadUserTableDonVi();
        this.ApDungButton();
        this.LocTrangThaiButton();
        this.LocDuocBauButton();
        this.EditButton();

        btn_QuayVe.setOnAction(new EventHandler<ActionEvent>() {
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
