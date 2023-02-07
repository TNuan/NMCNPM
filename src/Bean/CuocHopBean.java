package Bean;

import java.util.ArrayList;
import java.util.List;
import models.CuocHopModel;
import models.NhanKhauModel;
import models.UserMoldel;
import models.ThamGiaCuocHopModel;
import models.ThanhVienCuaHoModel;

/**
 * 
 * @author Nuan
 * Ket noi tu nhien Cuoc hop va nhan khau
 */


public class CuocHopBean {
    private CuocHopModel cuocHopModel;
    private UserMoldel nguoiTaoCuocHop;
    private List<NhanKhauModel> listNhanKhauModels;
    private List<ThamGiaCuocHopModel> listThamGiaCuocHop;

    public CuocHopBean(CuocHopModel cuocHopModel, UserMoldel nguoiTaoCuocHop, List<NhanKhauModel> listNhanKhauModels, List<ThamGiaCuocHopModel> listThamGiaCuocHopModels) {
        this.cuocHopModel = cuocHopModel;
        this.nguoiTaoCuocHop = nguoiTaoCuocHop;
        this.listNhanKhauModels = listNhanKhauModels;
        this.listThamGiaCuocHop = listThamGiaCuocHopModels;
    }

    public CuocHopBean() {
        this.cuocHopModel = new CuocHopModel();
        this.nguoiTaoCuocHop = new UserMoldel();
        this.listNhanKhauModels = new ArrayList<>();
        this.listThamGiaCuocHop = new ArrayList<>();
    }

    public CuocHopModel getCuocHopModel() {
        return cuocHopModel;
    }

    public void setCuocHopModel(CuocHopModel cuocHopModel) {
        this.cuocHopModel = cuocHopModel;
    }

    public UserMoldel getNguoiTaoCuocHop() {
        return nguoiTaoCuocHop;
    }
    
    public void setNguoiTaoCuocHop(UserMoldel nguoiTaoCuocHop) {
        this.nguoiTaoCuocHop = nguoiTaoCuocHop;
    }

    public List<NhanKhauModel> getListNhanKhauModels() {
        return listNhanKhauModels;
    }

    public void setListNhanKhauModels(List<NhanKhauModel> listNhanKhauModels) {
        this.listNhanKhauModels = listNhanKhauModels;
    }

    public List<ThamGiaCuocHopModel> getListThamGiaCuocHop () {
        return listThamGiaCuocHop;
    }

    public void setListThamGiaCuocHop(List<ThamGiaCuocHopModel> listThamGiaCuocHop) {
        this.listThamGiaCuocHop = listThamGiaCuocHop;
    }


    @Override
    public String toString() {
        String res = "<html> <style>p {padding: 5px; margin-left: 20px} table, th, td {border: 1px solid black; border-collapse: collapse;} table {width: 500px}</style> <div>"
                + "<h3>Thông tin cơ bản"
                + "<p>Mã cuộc họp: <b>" + cuocHopModel.getMaCuocHop() + "</p>"
                + "<p>Người tạo cuộc họp: <b>" + nguoiTaoCuocHop.getUserName() + "</p>"
                + "<p>Ngày tạo: <b>" + cuocHopModel.getNgayTaoCuocHop().toString() + "</p>"
                + "<p>Địa điểm: <b>" + cuocHopModel.getDiaDiem() + "</p>"
                + "<h4>Danh sách thành viên tham gia họp<table>"
                + "<tr>"
                + "<th>Họ tên</th>"
                + "<th>Năm sinh</th>"
                + "<th>Giới tính</th>"
                + "</tr>";
        for (int i = 0; i < listNhanKhauModels.size(); i++) {
            // ThanhVienCuaHoModel temp = listNhanKhauModels.get(i);
            res += "<tr>"
                    + "<td>"
                    + listNhanKhauModels.get(i).getHoTen()
                    + "</td>"
                    + "<td>"
                    + listNhanKhauModels.get(i).getNamSinh().toString()
                    + "</td>"
                    + "<td>"
                    + listNhanKhauModels.get(i).getGioiTinh()
                    + "</td>"
                    + "</tr>";
        }
        res += "</table></div></html>";
        return res;
    }

}
