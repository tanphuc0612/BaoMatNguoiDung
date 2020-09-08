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
public class ViewTaoUser {
    private int MaTV;
    private String Ten;
    private String Phai;
    private Date NgaySinh;
    private  String NoiCongTac;
    private int MaDonVi;

    public ViewTaoUser(int MaTV, String Ten, String Phai, Date NgaySinh,String NoiCongTac,int MaDonVi) {
        this.MaTV = MaTV;
        this.Ten = Ten;
        this.Phai = Phai;
        this.NgaySinh = NgaySinh;
        this.NoiCongTac = NoiCongTac;
        this.MaDonVi = MaDonVi;
    }

    public int getMaTV() {
        return MaTV;
    }

    public String getTen() {
        return Ten;
    }

    public String getPhai() {
        return Phai;
    }

    public String getNoiCongTac() {
        return NoiCongTac;
    }

    public void setMaTV(int MaTV) {
        this.MaTV = MaTV;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public void setPhai(String Phai) {
        this.Phai = Phai;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setNoiCongTac(String NoiCongTac) {
        this.NoiCongTac = NoiCongTac;
    }

    public int getMaDonVi() {
        return MaDonVi;
    }

    public void setMaDonVi(int MaDonVi) {
        this.MaDonVi = MaDonVi;
    }
    
}
