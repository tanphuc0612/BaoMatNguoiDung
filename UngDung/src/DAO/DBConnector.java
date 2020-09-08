/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class DBConnector {
    public static Connection con = null;
    public static ArrayList<String> vaitro = null;
    public static String username = null;
    public static void setConnection(String username, String pass)
    {
	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",username,pass);
	} catch (ClassNotFoundException | SQLException e) {
            con = null;
        }
    }
    public static Connection get(){
        return con;
    }
}
