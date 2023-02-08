package controllers.CuocHopManagerController;

import models.UserMoldel;
import services.CuocHopService;
import utility.TableModelCuocHop;

import java.util.EventObject;
import java.util.List;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Bean.CuocHopBean;
import Bean.MemOfMeeting;
public class ThemMoiCuocHopController {
    private UserMoldel nguoiTaoCuocHop;
    private List<MemOfMeeting> listThanhVien;
    private JPanel memTableJpn;
    private TableModelCuocHop tableModelCuocHop = new TableModelCuocHop();
    private final CuocHopService cuocHopService = new CuocHopService();
    private final String[] COLUMNS= {"Họ tên", "Ngày sinh", "Giới tính"};
    
    
    /**
     * 
     * @param listMember
     * @param tablePanel 
     */
    public void setData(List<MemOfMeeting> listMember, JPanel tablePanel) {
        DefaultTableModel model_mem = this.tableModelCuocHop.setTableMember(listMember, COLUMNS);
        JTable table_mem = new JTable(model_mem){
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;   //To change body of generated methods, choose Tools | Templates.
            }
        };
        table_mem.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table_mem.getTableHeader().setPreferredSize(new Dimension(100, 30));
        table_mem.setRowHeight(30);
        table_mem.validate();
        table_mem.repaint();
        table_mem.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table_mem);
        tablePanel.removeAll();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scroll);
        tablePanel.validate();
        tablePanel.repaint();
    }
    
    public void addNew(CuocHopBean cuocHopBean) throws ClassNotFoundException, SQLException{
        this.cuocHopService.addNew(cuocHopBean);
    }
}
