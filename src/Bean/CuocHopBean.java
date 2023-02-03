package Bean;

import java.util.ArrayList;
import java.util.List;
import models.CuocHopModel;
import models.NhanKhauModel;
import models.UserMoldel;
import models.ThamGiaCuocHopModel;

/**
 * 
 * @author Nuan
 * Ket noi tu nhien Cuoc hop va nhan khau
 */


public class CuocHopBean {
    private CuocHopModel cuocHopModel;
    private UserMoldel chuCuocHop;
    private List<NhanKhauModel> listNhanKhauModels;
    private List<ThamGiaCuocHopModel> listThamGiaCuocHop;

    public CuocHopBean(CuocHopModel cuocHopModel, UserMoldel chuCuocHop, List<NhanKhauModel> listNhanKhauModels, List<ThamGiaCuocHopModel> listThamGiaCuocHopModels) {
        this.cuocHopModel = cuocHopModel;
        this.chuCuocHop = chuCuocHop;
        this.listNhanKhauModels = listNhanKhauModels;
        this.listThamGiaCuocHop = listThamGiaCuocHopModels;
    }

    public CuocHopBean() {
        this.cuocHopModel = new CuocHopModel();
        this.chuCuocHop = new UserMoldel();
        this.listNhanKhauModels = new ArrayList<>();
        this.listThamGiaCuocHop = new ArrayList<>();
    }

    public CuocHopModel getCuocHopModel() {
        return cuocHopModel;
    }

    public void setCuocHopModel(CuocHopModel cuocHopModel) {
        this.cuocHopModel = cuocHopModel;
    }

    public UserMoldel getChuCuocHop() {
        return chuCuocHop;
    }
    
    public void setChuCuocHop(UserMoldel chuCuocHop) {
        this.chuCuocHop = chuCuocHop;
    }

    public List<ThamGiaCuocHopModel> getListThamGiaCuocHop () {
        return listThamGiaCuocHop;
    }

}
