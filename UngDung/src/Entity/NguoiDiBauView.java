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
public class NguoiDiBauView {
    private int maNDB;
    private String tenNDB;
    private String phaiNDB;
    private Date ngaySinhNDB;
    private String donviNDB;

    public NguoiDiBauView(int maNDB, String tenNDB, String phaiNDB, Date ngaySinhNDB, String donviNDB) {
        this.maNDB = maNDB;
        this.tenNDB = tenNDB;
        this.phaiNDB = phaiNDB;
        this.ngaySinhNDB = ngaySinhNDB;
        this.donviNDB = donviNDB;
    }

    public int getMaNDB() {
        return maNDB;
    }

    public String getTenNDB() {
        return tenNDB;
    }

    public String getPhaiNDB() {
        return phaiNDB;
    }

    public Date getNgaySinhNDB() {
        return ngaySinhNDB;
    }

    public String getDonviNDB() {
        return donviNDB;
    }

    public void setMaNDB(int maNDB) {
        this.maNDB = maNDB;
    }

    public void setTenNDB(String tenNDB) {
        this.tenNDB = tenNDB;
    }

    public void setPhaiNDB(String phaiNDB) {
        this.phaiNDB = phaiNDB;
    }

    public void setNgaySinhNDB(Date ngaySinhNDB) {
        this.ngaySinhNDB = ngaySinhNDB;
    }

    public void setDonviNDB(String donviNDB) {
        this.donviNDB = donviNDB;
    }
}
