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
public class NguoiDiBau {
    private String matv;
    private String ten;
    private String phai;
    private Date ngaysinh;
    private String donvi;
    private String tinhtrang;

    public NguoiDiBau(String matv, String ten, String phai, Date ngaysinh, String donvi, String tinhtrang) {
        this.matv = matv;
        this.ten = ten;
        this.phai = phai;
        this.ngaysinh = ngaysinh;
        this.donvi = donvi;
        this.tinhtrang = tinhtrang;
    }

    public String getMatv() {
        return matv;
    }

    public void setMatv(String matv) {
        this.matv = matv;
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

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}
