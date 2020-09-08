/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class GiamSat {
    private int maGS;
    private String tenGS;
    private String phaiGS;
    private Date ngaySinhGS;

    public GiamSat(int maGS, String tenGS, String phaiGS, Date ngaySinhGS) {
        this.maGS = maGS;
        this.tenGS = tenGS;
        this.phaiGS = phaiGS;
        this.ngaySinhGS = ngaySinhGS;
    }

    public int getMaGS() {
        return maGS;
    }

    public void setMaGS(int maGS) {
        this.maGS = maGS;
    }

    public String getTenGS() {
        return tenGS;
    }

    public void setTenGS(String tenGS) {
        this.tenGS = tenGS;
    }

    public String getPhaiGS() {
        return phaiGS;
    }

    public void setPhaiGS(String phaiGS) {
        this.phaiGS = phaiGS;
    }

    public Date getNgaySinhGS() {
        return ngaySinhGS;
    }

    public void setNgaySinhGS(Date ngaySinhGS) {
        this.ngaySinhGS = ngaySinhGS;
    }
    
    
}
