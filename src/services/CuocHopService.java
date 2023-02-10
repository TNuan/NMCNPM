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
        String query = "INSERT INTO cuoc_hop(maCuocHop, ngayHop, diaDiem, noiDung, idNguoiTaoCuocHop, ngayTaoCuocHop)"
                + " values (?, ?, ?, ?, ?, NOW())";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, cuocHopBean.getCuocHopModel().getMaCuocHop());
        java.sql.Date ngayHop = new java.sql.Date(cuocHopBean.getCuocHopModel().getNgayHop().getTime());
        preparedStatement.setDate(2, ngayHop);
        preparedStatement.setString(3, cuocHopBean.getCuocHopModel().getDiaDiem());
        preparedStatement.setString(4, cuocHopBean.getCuocHopModel().getNoiDung());
        preparedStatement.setInt(5, LoginController.currentUser.getID());
        // java.sql.Date createDate = new java.sql.Date(quanlynhankhau.QuanLyNhanKhau.calendar.getTime().getTime());
        // preparedStatement.setDate(3, createDate);
        // System.out.println("something here: " + preparedStatement);

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

    // lay list cuoc hop
    public List<CuocHopBean> getListCuocHop() {
        List<CuocHopBean> list = new ArrayList<>();

        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM cuoc_hop INNER JOIN users ON cuoc_hop.idNguoiTaoCuocHop = users.ID ORDER BY ngayTaoCuocHop";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CuocHopBean temp = new CuocHopBean();
                CuocHopModel cuocHopModel = temp.getCuocHopModel();
                cuocHopModel.setID(rs.getInt("ID"));
                cuocHopModel.setIdNguoiTaoCuocHop(rs.getInt("idNguoiTaoCuocHop"));
                cuocHopModel.setMaCuocHop(rs.getString("maCuocHop"));
                cuocHopModel.setNgayHop(rs.getDate("ngayHop"));
                cuocHopModel.setNgayTaoCuocHop(rs.getDate("ngayTaoCuocHop"));
                cuocHopModel.setDiaDiem(rs.getString("diaDiem"));
                cuocHopModel.setNoiDung(rs.getString("noiDung"));
                UserMoldel nguoiTaoCuocHop = temp.getNguoiTaoCuocHop();
                nguoiTaoCuocHop.setID(rs.getInt("ID"));
                nguoiTaoCuocHop.setUserName(rs.getString("userName"));
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

    // lay cuoc hop tu ngay den ngay
    public List<CuocHopBean> statisticCuocHop(String status/*, String nguoiTaoCuocHop */) {
        List<CuocHopBean> list = new ArrayList<>();

        String query = "SELECT * FROM cuoc_hop INNER JOIN users ON cuoc_hop.idNguoiTaoCuocHop = users.ID ";
        if (status.equalsIgnoreCase("Toan bo")) {
            query += "";
        } else if (status.equalsIgnoreCase("Hom nay")) {
            query += " WHERE ngayHop = CURDATE() ";
        } else if (status.equalsIgnoreCase("Chua hop")) {
            query += " WHERE ngayHop > CURDATE() ";
        } else if (status.equalsIgnoreCase("Da hop")) {
            query += " WHERE ngayHop < CURDATE() ";
        }
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CuocHopBean temp = new CuocHopBean();
                CuocHopModel cuocHopModel = temp.getCuocHopModel();
                cuocHopModel.setID(rs.getInt("ID"));
                cuocHopModel.setIdNguoiTaoCuocHop(rs.getInt("idNguoiTaoCuocHop"));
                cuocHopModel.setMaCuocHop(rs.getString("maCuocHop"));
                cuocHopModel.setNgayHop(rs.getDate("ngayHop"));
                cuocHopModel.setNgayTaoCuocHop(rs.getDate("ngayTaoCuocHop"));
                cuocHopModel.setDiaDiem(rs.getString("diaDiem"));
                cuocHopModel.setNoiDung(rs.getString("noiDung"));
                UserMoldel nguoiTaoCuocHop = temp.getNguoiTaoCuocHop();
                nguoiTaoCuocHop.setID(rs.getInt("ID"));
                nguoiTaoCuocHop.setUserName(rs.getString("userName"));
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
                    System.out.println("services.CuocHopService.statisticCuocHop()");
                    System.out.println(e.getMessage());
                }
                list.add(temp);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
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

    // edit cuoc hop duoc chon
    public boolean editCuocHop(int idCuocHop, String maCuocHop, String diaDiem, String noiDungChinh, Date ngayHop) {
        
        String query = "UPDATE cuoc_hop SET maCuocHop = '" + maCuocHop + "',"
                    + "diaDiem = " + diaDiem + ","
                    + "noiDung = " + noiDungChinh + ","
                    + "ngayHop = " + ngayHop
                    + "WHERE cuoc_hop.ID = " + idCuocHop;
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("services.CuocHopService.editCuocHop()");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra! vui lòng kiểm tra lại.", "Warning!!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }


    // delete cuoc hop duoc chon 
    public boolean deleteCuocHop(int idCuocHop) {
        String query = "DELETE FROM cuoc_hop from ID = " + idCuocHop;
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("services.CuocHopService.deleteCuocHop()");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra! vui lòng kiểm tra lại.", "Warning!!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
    }


    public int checkMaCuocHop(String maCuocHop) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM cuoc_hop WHERE maCuocHop = " + maCuocHop; 
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra! Vui lòng kiểm tra lại.", "Warning!!", JOptionPane.WARNING_MESSAGE);
        }
        return -1;
    }
}
