package controllers.CuocHopManagerController;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import services.MysqlConnection;

/**
 * 
 * @author Nuan
 */

public class XoaCuocHopController {
    public boolean deleted(int ID) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "DELETE FROM cuoc_hop from ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra! vui lòng kiểm tra lại.", "Warning!!", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
    }
}
