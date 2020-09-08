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
public class TheoDoiKetQua {
    private int maTDKQ;
    private String tenTDKQ;
    private String phaiTDKQ;
    private Date ngaySinhTDKQ;

    public TheoDoiKetQua(int maTDKQ, String tenTDKQ, String phaiTDKQ, Date ngaySinhTDKQ) {
        this.maTDKQ = maTDKQ;
        this.tenTDKQ = tenTDKQ;
        this.phaiTDKQ = phaiTDKQ;
        this.ngaySinhTDKQ = ngaySinhTDKQ;
    }

    public int getMaTDKQ() {
        return maTDKQ;
    }

    public void setMaTDKQ(int maTDKQ) {
        this.maTDKQ = maTDKQ;
    }

    public String getTenTDKQ() {
        return tenTDKQ;
    }

    public void setTenTDKQ(String tenTDKQ) {
        this.tenTDKQ = tenTDKQ;
    }

    public String getPhaiTDKQ() {
        return phaiTDKQ;
    }

    public void setPhaiTDKQ(String phaiTDKQ) {
        this.phaiTDKQ = phaiTDKQ;
    }

    public Date getNgaySinhTDKQ() {
        return ngaySinhTDKQ;
    }

    public void setNgaySinhTDKQ(Date ngaySinhTDKQ) {
        this.ngaySinhTDKQ = ngaySinhTDKQ;
    }
    
    
}
