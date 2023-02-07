package Bean;

import models.ThamGiaCuocHopModel;

/**
 * 
 * @author Nuan
 */

public class MemOfMeeting {
    private NhanKhauBean nhanKhau;
    private ThamGiaCuocHopModel thamGiaCuocHopModel;

    public MemOfMeeting(NhanKhauBean nhanKhau, ThamGiaCuocHopModel thamGiaCuocHopModel) {
        this.nhanKhau = nhanKhau;
        this.thamGiaCuocHopModel = thamGiaCuocHopModel;
    }

    public MemOfMeeting() {
        this.nhanKhau = new NhanKhauBean();
        this.thamGiaCuocHopModel = new ThamGiaCuocHopModel();
    }

    public NhanKhauBean getNhanKhau() {
        return nhanKhau;
    }

    public void setCuocHop(NhanKhauBean nhanKhau) {
        this.nhanKhau = nhanKhau;
    }

    public ThamGiaCuocHopModel getThamGiaCuocHopModel() {
        return thamGiaCuocHopModel;
    }

    public void setThamGiaCuocHopModel(ThamGiaCuocHopModel thamGiaCuocHopModel) {
        this.thamGiaCuocHopModel = thamGiaCuocHopModel;
    }

}
