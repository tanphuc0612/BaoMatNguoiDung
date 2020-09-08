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
public class ViewPhieuBau {
    private int ma;
    private String ten;
    private Date Ngay;

    public ViewPhieuBau(int ma, String ten, Date Ngay) {
        this.ma = ma;
        this.ten = ten;
        this.Ngay = Ngay;
    }

    public int getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setNgay(Date Ngay) {
        this.Ngay = Ngay;
    }
    
}
