/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import DAO.DBConnector;
import Entity.Table;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import oracle.jdbc.OracleConnection;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author mymy4
 */
public class GrantRevokeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn_Delete;

    @FXML
    private Button btn_Insert;

    @FXML
    private RadioButton rdo_Grant;

    @FXML
    private RadioButton rdo_Revoke;

    @FXML
    private RadioButton rdo_ThanhVien;

    @FXML
    private RadioButton rdo_ToGiamSat;

    @FXML
    private RadioButton rdo_BTC;

    @FXML
    private RadioButton rdo_ToTheoDoi;

    @FXML
    private ComboBox<?> cbb_RUThucHien;

    @FXML
    private CheckBox cb_CoQuyenCapQuyen;

    @FXML
    private Button btn_Select;

    @FXML
    private Button btn_Update;

    @FXML
    private Button home;

    @FXML
    private CheckBox cb_tv_Ten;

    @FXML
    private CheckBox cb_tv_phai;

    @FXML
    private CheckBox cb_tv_QueQuan;

    @FXML
    private TextField edt_RU;

    @FXML
    private CheckBox cb_tv_NgaySinh;

    @FXML
    private CheckBox cb_tv_QuocTich;

    @FXML
    private FlowPane fl_ThanhVien;

    @FXML
    private FlowPane fl_ToGiamSat;

    @FXML
    private FlowPane fl_ToTheoDoi;

    @FXML
    private FlowPane fl_BTC;

    @FXML
    private RadioButton rdo_Role;

    @FXML
    private RadioButton rdo_User;

    @FXML
    private RadioButton rdo_LapDanhSachDiBau;

    @FXML
    private FlowPane fl_LapDanhSachDiBau;

    String sql = "";
    String sql1 = "";
    String sql2 = "";
    String sql3 = "";
    String sqlft = "to";
    String sql4 = "";
    String sql5 = "";
    String columncheck = "";

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

    private int CheckView(String viewname) throws SQLException {
        viewname = viewname.toUpperCase();
        ResultSet rs = null;
        rs = DAO.Select("SELECT COUNT(*) FROM dba_views WHERE view_name = '" + viewname + "'");
        rs.next();
        if (rs.getInt(1) == 0) {
            return 0; //chưa tồn tại
        }
        return 1; // đã tồn tại
    }

    private int CheckRole(String rolename) throws SQLException {
        rolename = rolename.toUpperCase();
        ResultSet rs = null;
        rs = DAO.Select("select count(*) from DBA_ROLES where role = '" + rolename + "'");
        rs.next();
        if (rs.getInt(1) == 0) {
            return 0; //chưa tồn tại
        }
        return 1; // đã tồn tại
    }

    private int CheckUser(String username) throws SQLException {
        username = username.toUpperCase();
        ResultSet rs = null;
        rs = DAO.Select("select count(*) from all_users where username = '" + username + "'");
        rs.next();
        if (rs.getInt(1) == 0) {
            return 0; //chưa tồn tại
        }
        return 1; // đã tồn tại
    }

    private String CountCheckedItems(FlowPane fl, String[] s) {
        String result = "";
//        int count = 0;
        int num = 0;
        List<CheckBox> CheckList = new ArrayList<>();
        for (Node i : fl.getChildren()) {
            if (i.getTypeSelector().equals("CheckBox")) {
                CheckList.add((CheckBox) i);
            }
        }
        for (CheckBox i : CheckList) {
            if (i.isSelected()) {
                result = result + "," + s[num];
            }
            num++;
//               count++;
        }
        return result;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //lua chon grant ha revoke
        ToggleGroup group1 = new ToggleGroup();
        rdo_Grant.setToggleGroup(group1);
        rdo_Revoke.setToggleGroup(group1);

        // Lua chon tren bang nao
        ToggleGroup group2 = new ToggleGroup();
        rdo_ThanhVien.setToggleGroup(group2);
        rdo_LapDanhSachDiBau.setToggleGroup(group2);
        rdo_BTC.setToggleGroup(group2);
        rdo_ToTheoDoi.setToggleGroup(group2);
        rdo_ToGiamSat.setToggleGroup(group2);
//        if (group2.getSelectedToggle() != null) {
//            RadioButton button = (RadioButton) group2.getSelectedToggle();
//            sql2 = button.getText();
//                    System.out.println("sql2: " + button.getText());
//        }

        ToggleGroup group3 = new ToggleGroup();
        rdo_Role.setToggleGroup(group3);
        rdo_User.setToggleGroup(group3);

        btn_Select.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sql4 = edt_RU.getText();
                sql5 = cb_CoQuyenCapQuyen.isSelected() ? " WITH GRANT OPTION" : "";
                if (rdo_Role.isSelected()) {
                    try {
                        if (CheckRole(sql4) == 0) {
                            new Alert(Alert.AlertType.WARNING, "Role không tồn tại!").showAndWait();
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        if (CheckUser(sql4) == 0) {
                            new Alert(Alert.AlertType.WARNING, "User không tồn tại!").showAndWait();
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

//                if (group1.getSelectedToggle() != null) {
//                    RadioButton button = (RadioButton) group1.getSelectedToggle();
//                    sql1 = button.getText();
//                    System.out.println("sql1: " + button.getText());
//                }
                if (rdo_Grant.isSelected()) {
                    sql1 = "Grant";
                    sqlft = "to";
                }
                if (rdo_Revoke.isSelected()) {
                    sql1 = "Revoke";
                    sqlft = "from";
                }

                if (rdo_ThanhVien.isSelected()) {
                    String[] sThanhVien = {"TEN", "PHAI", "QUEQUAN", "QUOCTICH", "DCTHUONGTRU", "DCTAMTRU","DUOCBAU"};
                    sql3 = CountCheckedItems(fl_ThanhVien, sThanhVien);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        String temp = sql3;
                        String sqlnot = sql3;
                        sql3 = "(" + sql3 + ")";
                        columncheck = temp.replace(",", "_");
                        columncheck += "_TV";
                        try {
                            if (CheckView(columncheck) == 0) {// chua ton tai view
                                DAO.Execute("create view " + columncheck + " as select " + sqlnot + " from THANHVIEN");
                                System.out.println("Tao View: " + "create view " + columncheck + " as select " + sqlnot + " from THANHVIEN");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sql = sql1 + " select on " + columncheck + " " + sqlft + " " + sql4 + sql5;
                    } else {
                        sql = sql1 + " select" + sql3 + " on THANHVIEN " + sqlft + " " + sql4 + sql5;
                    }
                    System.out.println(sql);
                }

                if (rdo_LapDanhSachDiBau.isSelected()) {
                    String[] sTLDSDB = {"HOTEN", "PHAI", "NGAYSINH", "MaDV"};
                    sql3 = CountCheckedItems(fl_LapDanhSachDiBau, sTLDSDB);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        String temp = sql3;
                        String sqlnot = sql3;
                        sql3 = "(" + sql3 + ")";
                        columncheck = temp.replace(",", "_");
                        columncheck += "_LDSDB";
                        try {
                            if (CheckView(columncheck) == 0) {// chua ton tai view
                                DAO.Execute("create view " + columncheck + " as select " + sqlnot + " from TOLAPDANHSACH");
                                System.out.println("Tao View: " + "create view " + columncheck + " as select " + sqlnot + " from TOLAPDANHSACH");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sql = sql1 + " select on " + columncheck + " " + sqlft + " " + sql4 + sql5;
                    } else {
                        sql = sql1 + " select" + sql3 + " on TOLAPDANHSACH " + sqlft + " " + sql4 + sql5;
                    }
                    System.out.println(sql);
                }

                if (rdo_BTC.isSelected()) {
                    String[] sBTC = {"HOTEN", "PHAI", "NGAYSINH"};
                    sql3 = CountCheckedItems(fl_BTC, sBTC);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        String temp = sql3;
                        String sqlnot = sql3;
                        sql3 = "(" + sql3 + ")";
                        columncheck = temp.replace(",", "_");
                        columncheck += "_BTC";
                        try {
                            if (CheckView(columncheck) == 0) {// chua ton tai view
                                DAO.Execute("create view " + columncheck + " as select " + sqlnot + " from BANTOCHUC");
                                System.out.println("Tao View: " + "create view " + columncheck + " as select " + sqlnot + " from BANTOCHUC");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sql = sql1 + " select on " + columncheck + " " + sqlft + " " + sql4 + sql5;
                    } else {
                        sql = sql1 + " select" + sql3 + " on BANTOCHUC " + sqlft + " " + sql4 + sql5;
                    }
                    System.out.println(sql);
                }

                if (rdo_ToGiamSat.isSelected()) {
                    String[] sTGS = {"HOTEN", "PHAI", "NGAYSINH"};
                    sql3 = CountCheckedItems(fl_ToGiamSat, sTGS);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        String temp = sql3;
                        String sqlnot = sql3;
                        sql3 = "(" + sql3 + ")";
                        columncheck = temp.replace(",", "_");
                        columncheck += "_TGS";
                        try {
                            if (CheckView(columncheck) == 0) {// chua ton tai view
                                DAO.Execute("create view " + columncheck + " as select " + sqlnot + " from TOGIAMSAT");
                                System.out.println("Tao View: " + "create view " + columncheck + " as select " + sqlnot + " from TOGIAMSAT");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sql = sql1 + " select on " + columncheck + " " + sqlft + " " + sql4 + sql5;
                    } else {
                        sql = sql1 + " select" + sql3 + " on TOGIAMSAT " + sqlft + " " + sql4 + sql5;
                    }
                    System.out.println(sql);
                }

                if (rdo_ToTheoDoi.isSelected()) {
                    String[] sTTD = {"HOTEN", "PHAI", "NGAYSINH"};
                    sql3 = CountCheckedItems(fl_ToTheoDoi, sTTD);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        String temp = sql3;
                        String sqlnot = sql3;
                        sql3 = "(" + sql3 + ")";
                        columncheck = temp.replace(",", "_");
                        columncheck += "_TTD";
                        try {
                            if (CheckView(columncheck) == 0) {// chua ton tai view
                                DAO.Execute("create view " + columncheck + " as select " + sqlnot + " from TOTHEODOI");
                                System.out.println("Tao View: " + "create view " + columncheck + " as select " + sqlnot + " from TOTHEODOI");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sql = sql1 + " select on " + columncheck + " " + sqlft + " " + sql4 + sql5;
                    } else {
                        sql = sql1 + " select" + sql3 + " on TOTHEODOI " + sqlft + " " + sql4 + sql5;
                    }
                    System.out.println(sql);
                }

                try {
                    DAO.Execute(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_Update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sql4 = edt_RU.getText();
                sql5 = cb_CoQuyenCapQuyen.isSelected() ? " WITH GRANT OPTION" : "";
                if (rdo_Role.isSelected()) {
                    try {
                        if (CheckRole(sql4) == 0) {
                            new Alert(Alert.AlertType.WARNING, "Role không tồn tại!").showAndWait();
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        if (CheckUser(sql4) == 0) {
                            new Alert(Alert.AlertType.WARNING, "User không tồn tại!").showAndWait();
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (rdo_Grant.isSelected()) {
                    sql1 = "Grant";
                    sqlft = "to";
                }
                if (rdo_Revoke.isSelected()) {
                    sql1 = "Revoke";
                    sqlft = "from";
                }

                if (rdo_ThanhVien.isSelected()) {
                    String[] sThanhVien = {"TEN", "PHAI", "QUEQUAN", "QUOCTICH", "DCTHUONGTRU", "DCTAMTRU","DUOCBAU"};
                    sql3 = CountCheckedItems(fl_ThanhVien, sThanhVien);
                    System.out.println(sql3);

//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        sql3 = "(" + sql3 + ")";
                    }

                    sql = sql1 + " update" + sql3 + " on THANHVIEN " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_LapDanhSachDiBau.isSelected()) {
                    String[] sTLDSDB = {"HOTEN", "PHAI", "NGAYSINH", "MaDV"};
                    sql3 = CountCheckedItems(fl_LapDanhSachDiBau, sTLDSDB);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        sql3 = "(" + sql3 + ")";
                    }

                    sql = sql1 + " update" + sql3 + " on TOLAPDANHSACH " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_BTC.isSelected()) {
                    String[] sBTC = {"HOTEN", "PHAI", "NGAYSINH"};
                    sql3 = CountCheckedItems(fl_BTC, sBTC);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        sql3 = "(" + sql3 + ")";
                    }

                    sql = sql1 + " update" + sql3 + " on BANTOCHUC " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_ToGiamSat.isSelected()) {
                    String[] sTGS = {"HOTEN", "PHAI", "NGAYSINH"};
                    sql3 = CountCheckedItems(fl_ToGiamSat, sTGS);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        sql3 = "(" + sql3 + ")";
                    }

                    sql = sql1 + " update" + sql3 + " on TOGIAMSAT " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_ToTheoDoi.isSelected()) {
                    String[] sTTD = {"HOTEN", "PHAI", "NGAYSINH"};
                    sql3 = CountCheckedItems(fl_ToTheoDoi, sTTD);
                    System.out.println(sql3);
//                    System.out.println(sql3.length());
                    if (!"".equals(sql3)) {
                        sql3 = sql3.substring(1);
                        sql3 = "(" + sql3 + ")";
                    }

                    sql = sql1 + " update" + sql3 + " on TOTHEODOI " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                try {
                    DAO.Execute(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_Insert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sql4 = edt_RU.getText();
                sql5 = cb_CoQuyenCapQuyen.isSelected() ? " WITH GRANT OPTION" : "";
                if (rdo_Role.isSelected()) {
                    try {
                        if (CheckRole(sql4) == 0) {
                            new Alert(Alert.AlertType.WARNING, "Role không tồn tại!").showAndWait();
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        if (CheckUser(sql4) == 0) {
                            new Alert(Alert.AlertType.WARNING, "User không tồn tại!").showAndWait();
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (rdo_Grant.isSelected()) {
                    sql1 = "Grant";
                    sqlft = "to";
                }
                if (rdo_Revoke.isSelected()) {
                    sql1 = "Revoke";
                    sqlft = "from";
                }

                if (rdo_ThanhVien.isSelected()) {

                    sql = sql1 + " Insert on ThanhVien " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_LapDanhSachDiBau.isSelected()) {
                    sql = sql1 + " Insert on TOLAPDANHSACH " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_BTC.isSelected()) {
                    sql = sql1 + " Insert on BANTOCHUC " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_ToGiamSat.isSelected()) {
                    sql = sql1 + " Insert on TOGIAMSAT " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_ToTheoDoi.isSelected()) {
                    sql = sql1 + " Insert on TOTHEODOI " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                try {
                    DAO.Execute(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_Delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sql4 = edt_RU.getText();
                sql5 = cb_CoQuyenCapQuyen.isSelected() ? " WITH GRANT OPTION" : "";
                if (rdo_Role.isSelected()) {
                    try {
                        if (CheckRole(sql4) == 0) {
                            new Alert(Alert.AlertType.WARNING, "Role không tồn tại!").showAndWait();
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        if (CheckUser(sql4) == 0) {
                            new Alert(Alert.AlertType.WARNING, "User không tồn tại!").showAndWait();
                            return;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (rdo_Grant.isSelected()) {
                    sql1 = "Grant";
                    sqlft = "to";
                }
                if (rdo_Revoke.isSelected()) {
                    sql1 = "Revoke";
                    sqlft = "from";
                }

                if (rdo_ThanhVien.isSelected()) {
                    sql = sql1 + " Delete on ThanhVien " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_LapDanhSachDiBau.isSelected()) {
                    sql = sql1 + " Delete on TOLAPDANHSACH " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_BTC.isSelected()) {
                    sql = sql1 + " Delete on BANTOCHUC " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_ToGiamSat.isSelected()) {
                    sql = sql1 + " Delete on TOGIAMSAT " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                if (rdo_ToTheoDoi.isSelected()) {
                    sql = sql1 + " Delete on TOTHEODOI " + sqlft + " " + sql4 + sql5;
                    System.out.println(sql);
                }

                try {
                    DAO.Execute(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(GrantRevokeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("QuanTriHome.fxml", 1000, 600, "Quản trị Home");
                } catch (IOException ex) {
                    Logger.getLogger(XemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

}
