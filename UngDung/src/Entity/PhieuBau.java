/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hp
 */
public class PhieuBau {
    private int p_STT;
    private int p_MaTV;
    private int p_MaUCV;

    public PhieuBau(int p_STT, int p_MaTV, int p_MaUCV) {
        this.p_STT = p_STT;
        this.p_MaTV = p_MaTV;
        this.p_MaUCV = p_MaUCV;
    }

    public int getP_STT() {
        return p_STT;
    }

    public int getP_MaTV() {
        return p_MaTV;
    }

    public int getP_MaUCV() {
        return p_MaUCV;
    }

    public void setP_STT(int p_STT) {
        this.p_STT = p_STT;
    }

    public void setP_MaTV(int p_MaTV) {
        this.p_MaTV = p_MaTV;
    }

    public void setP_MaUCV(int p_MaUCV) {
        this.p_MaUCV = p_MaUCV;
    }


}
