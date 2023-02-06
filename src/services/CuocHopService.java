package services;

import Bean.CuocHopBean;
import controllers.LoginController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.mysql.cj.PerConnectionLRUFactory;

import models.CuocHopModel;
import models.NhanKhauModel;
import models.ThamGiaCuocHopModel;
import models.UserMoldel;

/**
 * 
 * @author Nuan
 */

public class CuocHopService {
    // them moi cuoc hop
    public boolean addNew(CuocHopBean cuocHopBean) throws ClassNotFoundException, SQLException {
        Connection connection = MysqlConnection.getMysqlConnection();
        String query = "INSERT INTO cuoc_hop(maCuocHop, ngayHop, ngayTaoCuocHop, diaDiem, noiDung, idNguoiTaoCuocHop)"
                + " values (?, ?, NOW(), ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, cuocHopBean.getCuocHopModel().getMaCuocHop());
        preparedStatement.setDate(2, (Date) cuocHopBean.getCuocHopModel().getNgayHop());
        preparedStatement.setString(4, cuocHopBean.getCuocHopModel().getDiaDiem());
        preparedStatement.setString(5, cuocHopBean.getCuocHopModel().getNoiDung());
        preparedStatement.setInt(6, cuocHopBean.getnguoiTaoCuocHop().getID());

        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            int genID = rs.getInt(1);
            String sql = "INSERT INTO tham_gia_cuoc_hop(idNhanKhau, idCuocHop)"
                    + " values (?, ?)";
            cuocHopBean.getListThamGiaCuocHop().forEach((ThamGiaCuocHopModel thamGia) -> {
                try {
                    PreparedStatement preStatement = connection.prepareStatement(sql);
                    preStatement.setInt(1, thamGia.getIdNhanKhau());
                    preStatement.setInt(2, genID);
                    preStatement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(CuocHopService.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        }
        preparedStatement.close();
        connection.close();
        return true;
    }

    public boolean checkPerson(int id) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM cuoc_hop INNER JOIN tham_gia_cuoc_hop ON cuoc_hop.ID = tham_gia_cuoc_hop.idCuocHop"
                    + " WHERE cuoc_hop.idNguoiTaoCuocHop = "
                    + id
                    + " OR tham_gia_cuoc_hop.idNhanKhau = "
                    + id;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }

    // lay ra 10 cuoc hop
    public List<CuocHopBean> getListCuocHop() {
        List<CuocHopBean> list = new ArrayList<>();

        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM cuoc_hop INNER JOIN users ON cuoc_hop.idNguoiTaoCuocHop = users.ID ORDER BY ngayTaoCuocHop DESC LIMIT 0, 10";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CuocHopBean temp = new CuocHopBean();
                CuocHopModel cuocHopModel = temp.getCuocHopModel();
                cuocHopModel.setID(rs.getInt("ID"));
                cuocHopModel.setIdNguoiTaoCuocHop(rs.getInt("idNguoiTaoCuocHop"));
                cuocHopModel.setMaCuocHop(rs.getString("maCuocHop"));
                cuocHopModel.setNgayHop(rs.getDate("ngayHop"));
                cuocHopModel.setDiaDiem(rs.getString("diaDiem"));
                cuocHopModel.setNoiDung(rs.getString("noiDung"));
                try {
                    String sql = "SELECT * FROM nhan_khau INNER JOIN tham_gia_cuoc_hop ON nhan_khau.ID = tham_gia_cuoc_hop.idNhanKhau "
                            + "WHERE tham_gia_cuoc_hop.idCuocHop = "
                            + cuocHopModel.getID();
                    PreparedStatement prst = connection.prepareStatement(sql);
                    ResultSet rs_1 = prst.executeQuery();
                    List<NhanKhauModel> listNhanKhau = temp.getListNhanKhauModels();
                    List<ThamGiaCuocHopModel> listThamGiaCuocHop = temp.getListThamGiaCuocHop();
                    while (rs_1.next()) {
                        NhanKhauModel nhanKhauModel = new NhanKhauModel();
                        ThamGiaCuocHopModel thamGiaCuocHopModel = new ThamGiaCuocHopModel();
                        nhanKhauModel.setID(rs_1.getInt("idNhanKhau"));
                        nhanKhauModel.setBietDanh(rs_1.getString("bietDanh"));
                        nhanKhauModel.setHoTen(rs_1.getString("hoTen"));
                        nhanKhauModel.setGioiTinh(rs_1.getString("gioiTinh"));
                        nhanKhauModel.setNamSinh(rs_1.getDate("namSinh"));
                        nhanKhauModel.setNguyenQuan(rs_1.getString("nguyenQuan"));
                        nhanKhauModel.setTonGiao(rs_1.getString("tonGiao"));
                        nhanKhauModel.setDanToc(rs_1.getString("danToc"));
                        nhanKhauModel.setQuocTich(rs_1.getString("quocTich"));
                        nhanKhauModel.setSoHoChieu(rs_1.getString("soHoChieu"));
                        nhanKhauModel.setNoiThuongTru(rs_1.getString("noiThuongTru"));
                        nhanKhauModel.setDiaChiHienNay(rs_1.getString("diaChiHienNay"));

                        thamGiaCuocHopModel.setIdCuocHop(rs_1.getInt("idCuocHop"));
                        thamGiaCuocHopModel.setIdNhanKhau(rs_1.getInt("idNhanKhau"));
                        listNhanKhau.add(nhanKhauModel);
                        listThamGiaCuocHop.add(thamGiaCuocHopModel);
                    }
                } catch (Exception e) {
                    System.out.println("services.CuocHopService.getListCuocHop()");
                    System.out.println(e.getMessage());
                }
                list.add(temp);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // tim kiem theo ten chu ho va ma cuoc hop
    public List<CuocHopBean> search(String key) {
        List<CuocHopBean> list = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * "
                    + "FROM cuoc_hop "
                    + "INNER JOIN users "
                    + "ON cuoc_hop.idNguoiTaoCuocHop = users.ID "
                    + "WHERE MATCH(maCuocHop) AGAINST ('"
                    + key
                    + "' IN NATURAL LANGUAGE MODE);";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CuocHopBean temp = new CuocHopBean();
                CuocHopModel cuocHopModel = temp.getCuocHopModel();
                cuocHopModel.setID(rs.getInt("ID"));
                cuocHopModel.setIdNguoiTaoCuocHop(rs.getInt("idNguoiTaoCuocHop"));
                cuocHopModel.setMaCuocHop(rs.getString("maCuocHop"));
                cuocHopModel.setNgayHop(rs.getDate("ngayHop"));
                cuocHopModel.setDiaDiem(rs.getString("diaDiem"));
                cuocHopModel.setNoiDung(rs.getString("noiDung"));
                // NhanKhauModel chuHo = temp.getChuHo();
                // chuHo.setID(rs.getInt("ID"));
                // chuHo.setHoTen(rs.getString("hoTen"));
                // chuHo.setGioiTinh(rs.getString("gioiTinh"));
                // chuHo.setNamSinh(rs.getDate("namSinh"));
                // chuHo.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                try {
                    String sql = "SELECT * FROM nhan_khau INNER JOIN tham_gia_cuoc_hop ON nhan_khau.ID = tham_gia_cuoc_hop.idNhanKhau "
                            + "WHERE tham_gia_cuoc_hop.idCuocHop = "
                            + cuocHopModel.getID();
                    PreparedStatement prst = connection.prepareStatement(sql);
                    ResultSet rs_1 = prst.executeQuery();
                    List<NhanKhauModel> listNhanKhau = temp.getListNhanKhauModels();
                    List<ThamGiaCuocHopModel> listThamGiaCuocHop = temp.getListThamGiaCuocHop();
                    while (rs_1.next()) {
                        NhanKhauModel nhanKhauModel = new NhanKhauModel();
                        ThamGiaCuocHopModel thamGiaCuocHopModel = new ThamGiaCuocHopModel();
                        nhanKhauModel.setID(rs_1.getInt("idNhanKhau"));
                        nhanKhauModel.setBietDanh(rs_1.getString("bietDanh"));
                        nhanKhauModel.setHoTen(rs_1.getString("hoTen"));
                        nhanKhauModel.setGioiTinh(rs_1.getString("gioiTinh"));
                        nhanKhauModel.setNamSinh(rs_1.getDate("namSinh"));
                        nhanKhauModel.setNguyenQuan(rs_1.getString("nguyenQuan"));
                        nhanKhauModel.setTonGiao(rs_1.getString("tonGiao"));
                        nhanKhauModel.setDanToc(rs_1.getString("danToc"));
                        nhanKhauModel.setQuocTich(rs_1.getString("quocTich"));
                        nhanKhauModel.setSoHoChieu(rs_1.getString("soHoChieu"));
                        nhanKhauModel.setNoiThuongTru(rs_1.getString("noiThuongTru"));
                        nhanKhauModel.setDiaChiHienNay(rs_1.getString("diaChiHienNay"));

                        thamGiaCuocHopModel.setIdCuocHop(rs_1.getInt("idCuocHop"));
                        thamGiaCuocHopModel.setIdNhanKhau(rs_1.getInt("idNhanKhau"));
                        listNhanKhau.add(nhanKhauModel);
                        listThamGiaCuocHop.add(thamGiaCuocHopModel);
                    }
                } catch (Exception e) {
                    System.out.println("services.CuocHopService.search()");
                    System.out.println(e.getMessage());
                }
                list.add(temp);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
