/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ungdung;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author admin
 */
public class UngDung extends Application {
    private static Stage primaryStage; // **Declare static Stage**
    private static String username;
    static void setPrimaryStage(Stage stage) {
        UngDung.primaryStage = stage;
    }
    static public Stage getPrimaryStage() {
        return UngDung.primaryStage;
    }
    static public void setUsername(String s) {
        UngDung.username = s;
    }

    static public String getUsername() {
        return UngDung.username;
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        setPrimaryStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/DangNhap.fxml"));
        Scene scene = new Scene(root,541,296);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Đăng nhập");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
