package models;

public class ThamGiaCuocHopModel {
    private int idCuocHop;
    private int idNhanKhau;

    public ThamGiaCuocHopModel(int idCuocHop, int idNhanKhau) {
        this.idCuocHop = idCuocHop;
        this.idNhanKhau = idNhanKhau;
    }

    public int getIdCuocHop() {
        return idCuocHop;
    }

    public void setIdCuocHop(int idCuocHop) {
        this.idCuocHop = idCuocHop;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }
}
