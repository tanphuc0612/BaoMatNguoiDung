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
public class LapDanhSachDiBau {
    private int maLDS;
    private String tenLDS;
    private String phaiLDS;
    private Date ngaySinhLDS;
    private String donviLDS;

    public LapDanhSachDiBau(int maLDS, String tenLDS, String phaiLDS, Date ngaySinhLDS, String donviLDS) {
        this.maLDS = maLDS;
        this.tenLDS = tenLDS;
        this.phaiLDS = phaiLDS;
        this.ngaySinhLDS = ngaySinhLDS;
        this.donviLDS = donviLDS;
    }

    public int getMaLDS() {
        return maLDS;
    }

    public void setMaLDS(int maLDS) {
        this.maLDS = maLDS;
    }

    public String getTenLDS() {
        return tenLDS;
    }

    public void setTenLDS(String tenLDS) {
        this.tenLDS = tenLDS;
    }

    public String getPhaiLDS() {
        return phaiLDS;
    }

    public void setPhaiLDS(String phaiLDS) {
        this.phaiLDS = phaiLDS;
    }

    public Date getNgaySinhLDS() {
        return ngaySinhLDS;
    }

    public void setNgaySinhLDS(Date ngaySinhLDS) {
        this.ngaySinhLDS = ngaySinhLDS;
    }

    public String getDonviLDS() {
        return donviLDS;
    }

    public void setDonviLDS(String donviLDS) {
        this.donviLDS = donviLDS;
    }
    
    
}
