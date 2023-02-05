package models;

import java.util.Date;

/**
 * 
 * @author Nuan
 */

public class CuocHopModel {
    private int ID;
    private String maCuocHop;
    private Date ngayHop;
    private Date ngayTaoCuocHop;
    private String diaDiem;
    private String noiDung;
    private UserMoldel nguoiTaoCuocHop;

    // public CuocHopModel(int ID, String maCuocHop, Date ngayHop, Date ngayTaoCuocHop, String diaDiem, String noiDung, UserMoldel nguoiTaoCuocHop) {
    //     this.ID = ID;
    //     this.maCuocHop = maCuocHop;
    //     this.ngayHop = ngayHop;
    //     this.ngayTaoCuocHop = ngayTaoCuocHop;
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

    public Date getngayHop() {
        return ngayHop;
    }

    public void setngayHop(Date ngayHop) {
        this.ngayHop = ngayHop;
    }

    public Date getngayTaoCuocHop() {
        return ngayTaoCuocHop;
    }

    public void setngayTaoCuocHop(Date ngayTaoCuocHop) {
        this.ngayTaoCuocHop = ngayTaoCuocHop;
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
