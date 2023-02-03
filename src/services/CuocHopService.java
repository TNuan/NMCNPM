package services;

import Bean.CuocHopBean;
import controllers.LoginController;
import java.sql.Connection;
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

/**
 * 
 * @author Nuan
 */

public class CuocHopService {
    //them moi cuoc hop
    public boolean addNew(CuocHopBean cuocHopBean) throws ClassNotFoundException, SQLException {
        Connection connection = MysqlConnection.getMysqlConnection();
        String query = "";

        PreparedStatement prepareStatement = connection.prepareStatement(query );

        return false;   
    }
}
