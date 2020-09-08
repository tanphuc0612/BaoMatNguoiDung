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
public class LichSuBau {
    private int ls_STT;
    private int ls_MaTV;
    private int ls_MaUCV;
    private Date ls_Ngay;

    public LichSuBau(int ls_STT, int ls_MaTV, int ls_MaUCV, Date ls_Ngay) {
        this.ls_STT = ls_STT;
        this.ls_MaTV = ls_MaTV;
        this.ls_MaUCV = ls_MaUCV;
        this.ls_Ngay = ls_Ngay;
    }

    public int getLs_STT() {
        return ls_STT;
    }

    public int getLs_MaTV() {
        return ls_MaTV;
    }

    public int getLs_MaUCV() {
        return ls_MaUCV;
    }

    public Date getLs_Ngay() {
        return ls_Ngay;
    }

    public void setLs_STT(int ls_STT) {
        this.ls_STT = ls_STT;
    }

    public void setLs_MaTV(int ls_MaTV) {
        this.ls_MaTV = ls_MaTV;
    }

    public void setLs_MaUCV(int ls_MaUCV) {
        this.ls_MaUCV = ls_MaUCV;
    }

    public void setLs_Ngay(Date ls_Ngay) {
        this.ls_Ngay = ls_Ngay;
    }
    
}
