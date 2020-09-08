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
public class ThanhVien {
    private int MaTV;
    private String Ten;
    private String Phai;
    private String QueQuan;
    private Date NgaySinh;
    private String QuocTich;
    private String DCThuongTru;
    private String DCTamTru;
    private  String NoiCongTac;
    private int TrangThai;
    private String LyDo;
    private int MaDV;
    private String Username;
    private int DuocBau;
    
    public void setMaTV(int n){
        this.MaTV = n;
    }
    public void setTen(String s){
        this.Ten = s;
    }
    public void setPhai(String s){
        this.Phai = s;
    }
    public void setQueQuan(String s){
        this.QueQuan = s;
    }

    public ThanhVien(int MaTV, String Ten, String Phai, String QueQuan, Date NgaySinh, String QuocTich, String DCThuongTru, String DCTamTru, String NoiCongTac, int TrangThai, String LyDo, int MaDV, String Username, int DuocBau) {
        this.MaTV = MaTV;
        this.Ten = Ten;
        this.Phai = Phai;
        this.QueQuan = QueQuan;
        this.NgaySinh = NgaySinh;
        this.QuocTich = QuocTich;
        this.DCThuongTru = DCThuongTru;
        this.DCTamTru = DCTamTru;
        this.NoiCongTac = NoiCongTac;
        this.TrangThai = TrangThai;
        this.LyDo = LyDo;
        this.MaDV = MaDV;
        this.Username = Username;
        this.DuocBau = DuocBau;
    }
    
    public void setNgaySinh(Date d){
        this.NgaySinh = d;
    }
    public void setQuocTich(String s){
        this.QuocTich = s;
    }
    public void setDCThuongTru(String s){
        this.DCThuongTru = s;
    }
    public void setDCTamTru(String s){
        this.DCTamTru = s;
    }
    public void setNoiCongTac(String s){
        this.NoiCongTac = s;
    }
    public void setTrangThai(int n){
        this.TrangThai = n;
    }
    public void setLyDo(String s){
        this.LyDo = s;
    }
    public void setMaDV(int n){
        this.MaDV = n;
    }
    public void setUsername(String s){
        this.Username = s;
    }
    public void setDuocBau(int n){
        this.DuocBau = n;
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

    public String getQueQuan() {
        return QueQuan;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public String getDCThuongTru() {
        return DCThuongTru;
    }

    public String getDCTamTru() {
        return DCTamTru;
    }

    public String getNoiCongTac() {
        return NoiCongTac;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public String getLyDo() {
        return LyDo;
    }

    public int getMaDV() {
        return MaDV;
    }

    public String getUsername() {
        return Username;
    }

    public int getDuocBau() {
        return DuocBau;
    }
}
