/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author USER
 */
public class QuyenChuThe {
    private String username;
    private String loaiQuyen;
    private String quyen;
    private String table;
    private String cot;
    private String nguoiSoHuu;
    private String ad;
    
    public QuyenChuThe(String username, String loaiQuyen, String quyen, String table, String cot, String nguoiSoHuu, String ad) {
        this.username = username;
        this.loaiQuyen = loaiQuyen;
        this.quyen = quyen;
        this.table = table;
        this.cot = cot;
        this.nguoiSoHuu = nguoiSoHuu;
        this.ad = ad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getQuyen() {
        return quyen;
    }

    public void setQuyem(String quyen) {
        this.quyen = quyen;
    }
    
    public String getTable() {
        return table;
    }

    public void setTable(String quyen) {
        this.table = table;
    }
    
    public String getLoaiQuyen() {
        return loaiQuyen;
    }

    public void setLoaiQuyen(String loaiQuyen) {
        this.loaiQuyen = loaiQuyen;
    }
    
    public String getCot() {
        return cot;
    }

    public void setCot(String cot) {
        this.cot = cot;
    }
    
    public String getNguoiSoHuu() {
        return nguoiSoHuu;
    }

    public void setNguoiSoHuu(String nguoiSoHuu) {
        this.nguoiSoHuu = nguoiSoHuu;
    }
    
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
    
}
