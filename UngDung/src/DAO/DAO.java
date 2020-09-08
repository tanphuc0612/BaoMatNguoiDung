/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Hp
 */
public class DAO {

    public static ResultSet Select(String query) throws SQLException {
        ResultSet list = DBConnector.get().createStatement().executeQuery(query);
        return list;
    }

    public static boolean Insert(String query) throws SQLException {
        return (DBConnector.get().createStatement().executeUpdate(query) > 0);
    }

    public static void CreateUser(String user, String pass) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{call SYS.CreateUser(?,?)}");
        statement.setString(1, user);
        statement.setString(2, pass);
        statement.execute();
    }
    public static void DisableAudit(String bang, String ten) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{call SYS.DISABLE_AUDIT(?,?)}");
        statement.setString(1, bang);
        statement.setString(2, ten);
        statement.execute();
    }
    public static void EnableAudit(String bang, String ten) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{call SYS.ENABLE_AUDIT(?,?)}");
        statement.setString(1, bang);
        statement.setString(2, ten);
        statement.execute();
    }
    public static void CreateRole(String role) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{call SYS.CreateRole(?)}");
        statement.setString(1, role);
        statement.execute();
    }
    public static void DropRole(String role) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{call SYS.DropRole(?)}");
        statement.setString(1, role);
        statement.execute();
    }
    public static void GrantRole(String user, String role) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{call SYS.GrantRole(?,?)}");
        statement.setString(1, user);
        statement.setString(2, role);
        statement.execute();
    }
    public static void RevokeRole(String user, String role) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{call SYS.RevokeRole(?,?)}");
        statement.setString(1, user);
        statement.setString(2, role);
        statement.execute();
    }
    public static int LayMaTV(String user) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{? = call SYS.LayMaTV(?)}");
        statement.registerOutParameter (1, Types.INTEGER);
        statement.setString(2, user.toUpperCase());
        statement.execute();
        return statement.getInt(1);
    }
    public static boolean Execute(String query) throws SQLException {
        return DBConnector.get().createStatement().execute(query);
    }
    public static String LayUserName(int matv) throws SQLException {
        CallableStatement statement = DBConnector.get().prepareCall("{? = call SYS.LayUserName(?)}");
        statement.registerOutParameter (1, Types.VARCHAR);
        statement.setInt(2, matv);
        statement.execute();
        return statement.getString(1);
    }
}
