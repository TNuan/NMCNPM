package utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;

import Bean.CuocHopBean;
import Bean.MemOfMeeting;
import Bean.NhanKhauBean;

/**
 * 
 * @author Nuan 
 */

public class TableModelCuocHop {
    public DefaultTableModel setTableNhanKhau(List<NhanKhauBean> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((NhanKhauBean item) -> {  
            obj[0] = item.getNhanKhauModel().getHoTen();
            obj[1] = item.getNhanKhauModel().getGioiTinh();
            obj[2] = item.getNhanKhauModel().getNamSinh();
            obj[3] = item.getNhanKhauModel().getDiaChiHienNay();
            obj[4] = item.getChungMinhThuModel().getSoCMT();
            dtm.addRow(obj);
        });
        return dtm;
    }

    public DefaultTableModel setTableMember(List<MemOfMeeting> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((MemOfMeeting item) -> {  
            // obj[0] = item.getNhanKhau().getNhanKhauModel().getHoTen();
            // obj[1] = item.getNhanKhau().getNhanKhauModel().getNamSinh();
            dtm.addRow(obj);
        });
        return dtm;
    }

    public DefaultTableModel setTableCuocHop(List<CuocHopBean> listItems, String[] listColumn) {
        final int columns = listColumn.length;
        // TODO
        return null;
    }
}
