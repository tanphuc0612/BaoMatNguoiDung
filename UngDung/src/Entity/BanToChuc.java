/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Hp
 */
public class BanToChuc {
    private int maBTC;
    private String tenBTC;
    private String phaiBTC;
    private Date ngaySinhBTC;

    public BanToChuc(int maBTC, String tenBTC, String phaiBTC, Date ngaySinhBTC) {
        this.maBTC = maBTC;
        this.tenBTC = tenBTC;
        this.phaiBTC = phaiBTC;
        this.ngaySinhBTC = ngaySinhBTC;
    }

    public int getMaBTC() {
        return maBTC;
    }

    public String getTenBTC() {
        return tenBTC;
    }

    public String getPhaiBTC() {
        return phaiBTC;
    }

    public Date getNgaySinhBTC() {
        return ngaySinhBTC;
    }

    public void setMaBTC(int maBTC) {
        this.maBTC = maBTC;
    }

    public void setTenBTC(String tenBTC) {
        this.tenBTC = tenBTC;
    }

    public void setPhaiBTC(String phaiBTC) {
        this.phaiBTC = phaiBTC;
    }

    public void setNgaySinhBTC(Date ngaySinhBTC) {
        this.ngaySinhBTC = ngaySinhBTC;
    }
    
}
