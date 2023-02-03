package models;

import java.util.Date;

/**
 * 
 * @author Nuan
 */

public class CuocHopModel {
    private int ID;
    private String maCuocHop;
    private Date thoiGianHop;
    private Date thoiGianTaoCuocHop;
    private String diaDiem;
    private String noiDung;
    private UserMoldel nguoiTaoCuocHop;

    // public CuocHopModel(int ID, String maCuocHop, Date thoiGianHop, Date thoiGianTaoCuocHop, String diaDiem, String noiDung, UserMoldel nguoiTaoCuocHop) {
    //     this.ID = ID;
    //     this.maCuocHop = maCuocHop;
    //     this.thoiGianHop = thoiGianHop;
    //     this.thoiGianTaoCuocHop = thoiGianTaoCuocHop;
    //     this.diaDiem = diaDiem;
    //     this.noiDung = noiDung;
    //     this.nguoiTaoCuocHop = nguoiTaoCuocHop;
    // }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getMaCuocHop() {
        return maCuocHop;
    }

    public void setMaCuocHop(String maCuocHop) {
        this.maCuocHop = maCuocHop;
    }

    public Date getThoiGianHop() {
        return thoiGianHop;
    }

    public void setThoiGianHop(Date thoiGianHop) {
        this.thoiGianHop = thoiGianHop;
    }

    public Date getThoiGianTaoCuocHop() {
        return thoiGianTaoCuocHop;
    }

    public void setThoiGianTaoCuocHop(Date thoiGianTaoCuocHop) {
        this.thoiGianTaoCuocHop = thoiGianTaoCuocHop;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public UserMoldel getNguoiTaoCuocHop() {
        return nguoiTaoCuocHop;
    }

    public void setNguoiTaoCuocHop(UserMoldel nguoiTaoCuocHop) {
        this.nguoiTaoCuocHop = nguoiTaoCuocHop;
    }
    
}
