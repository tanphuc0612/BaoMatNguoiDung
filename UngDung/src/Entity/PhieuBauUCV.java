/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class PhieuBauUCV {
    private String maUCV;
    private String ten;
    private String phai;
    private Date ngaysinh;
    private String soPhieuBau;
    
    public PhieuBauUCV(String maUCV, String ten, String phai, Date ngaysinh, String soPhieuBau) {
        this.maUCV = maUCV;
        this.ten = ten;
        this.phai = phai;
        this.ngaysinh = ngaysinh;
        this.soPhieuBau = soPhieuBau;
    }
    
    public String getMaUCV() {
        return maUCV;
    }

    public void setMaUCV(String maUCV) {
        this.maUCV = maUCV;
    }
    
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }
    
    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
    
    public String getSoPhieuBau() {
        return soPhieuBau;
    }

    public void setSoPhieuBau(String soPhieuBau) {
        this.soPhieuBau = soPhieuBau;
    }
}
