/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Hp
 */
public class AuditView {
    private String username;
    private String table;
    private String query;
    private Timestamp time;

    public AuditView(String username, String table, String query, Timestamp time) {
        this.username = username;
        this.table = table;
        this.query = query;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public String getTable() {
        return table;
    }

    public String getQuery() {
        return query;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    
}
