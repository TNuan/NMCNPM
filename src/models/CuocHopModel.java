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
    private int idNguoiTaoCuocHop;

    // public CuocHopModel(int ID, String maCuocHop, Date ngayHop, Date ngayTaoCuocHop, String diaDiem, String noiDung, UserMoldel idNguoiTaoCuocHop) {
    //     this.ID = ID;
    //     this.maCuocHop = maCuocHop;
    //     this.ngayHop = ngayHop;
    //     this.ngayTaoCuocHop = ngayTaoCuocHop;
    //     this.diaDiem = diaDiem;
    //     this.noiDung = noiDung;
    //     this.idNguoiTaoCuocHop = idNguoiTaoCuocHop;
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

    public Date getNgayHop() {
        return ngayHop;
    }

    public void setNgayHop(Date ngayHop) {
        this.ngayHop = ngayHop;
    }

    public Date getNgayTaoCuocHop() {
        return ngayTaoCuocHop;
    }

    public void setNgayTaoCuocHop(Date ngayTaoCuocHop) {
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

    public int getIdNguoiTaoCuocHop() {
        return idNguoiTaoCuocHop;
    }

    public void setIdNguoiTaoCuocHop(int idNguoiTaoCuocHop) {
        this.idNguoiTaoCuocHop = idNguoiTaoCuocHop;
    }
    
}
