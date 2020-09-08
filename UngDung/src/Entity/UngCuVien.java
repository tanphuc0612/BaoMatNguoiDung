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
public class UngCuVien {
    private int maUCV;
    private String tenUCV;
    private String phaiUCV;
    private Date ngaySinhUCV;

    public UngCuVien(int maUCV, String tenUCV, String phaiUCV, Date ngaySinhUCV) {
        this.maUCV = maUCV;
        this.tenUCV = tenUCV;
        this.phaiUCV = phaiUCV;
        this.ngaySinhUCV = ngaySinhUCV;
    }

    public int getMaUCV() {
        return maUCV;
    }

    public void setMaUCV(int maUCV) {
        this.maUCV = maUCV;
    }

    public String getTenUCV() {
        return tenUCV;
    }

    public void setTenUCV(String tenUCV) {
        this.tenUCV = tenUCV;
    }

    public String getPhaiUCV() {
        return phaiUCV;
    }

    public void setPhaiUCV(String phaiUCV) {
        this.phaiUCV = phaiUCV;
    }

    public Date getNgaySinhUCV() {
        return ngaySinhUCV;
    }

    public void setNgaySinhUCV(Date ngaySinhUCV) {
        this.ngaySinhUCV = ngaySinhUCV;
    }
    
    
}
