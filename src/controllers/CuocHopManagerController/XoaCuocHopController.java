package controllers.CuocHopManagerController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.EventObject;
import java.util.List;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Bean.CuocHopBean;
import services.CuocHopService;
import services.MysqlConnection;
import utility.TableModelCuocHop;

/**
 * 
 * @author Nuan
 */

public class XoaCuocHopController {
    private CuocHopBean cuocHopBean;
    private JTextField passwordJtf;
    private JTextField maCuocHopJtf;
    private JPanel tableJpn;
    private List<CuocHopBean> list;
    private final CuocHopService cuocHopService = new CuocHopService();
    private final TableModelCuocHop tableModelCuocHop = new TableModelCuocHop();
    private final String COLUNMS[] = {"Mã cuộc họp", "Người tạo", "Nội dung chính", "Ngày họp"};
    
    public XoaCuocHopController(CuocHopBean cuocHopBean, JTextField maCuocHopJtf, JPanel tableJpn) {
        this.cuocHopBean = cuocHopBean;
        this.maCuocHopJtf = maCuocHopJtf;
        this.tableJpn = tableJpn;
        this.list = cuocHopService.getListCuocHop();
        setData();
        initAction();
    }

    public void initAction() {
        this.maCuocHopJtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = maCuocHopJtf.getText().trim();
                if (key.isEmpty()) {
                    list = cuocHopService.getListCuocHop();
                } else {
                    list = cuocHopService.search(key);
                }
                setData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = maCuocHopJtf.getText().trim();
                if (key.isEmpty()) {
                    list = cuocHopService.getListCuocHop();
                } else {
                    list = cuocHopService.search(key);
                }
                setData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = maCuocHopJtf.getText().trim();
                if (key.isEmpty()) {
                    list = cuocHopService.getListCuocHop();
                } else {
                    list = cuocHopService.search(key);
                }
                setData();
            }

        });
    }

    public void setData() {
        DefaultTableModel model = tableModelCuocHop.setTableCuocHop(list, COLUNMS);

        JTable table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 30));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        // table.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         CuocHopBean temp = list.get(table.getSelectedRow());
        //         cuocHopBean.setNguoiTaoCuocHop(temp.getNguoiTaoCuocHop());
        //         cuocHopBean.setCuocHopModel(temp.getCuocHopModel());
        //         cuocHopBean.setListNhanKhauModels(temp.getListNhanKhauModels());
        //         cuocHopBean.setListThamGiaCuocHop(temp.getListThamGiaCuocHop());
        //     } 
        // });

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        tableJpn.removeAll();
        tableJpn.setLayout(new BorderLayout());
        tableJpn.add(scroll);
        tableJpn.validate();
        tableJpn.repaint();
    }

    public CuocHopBean getCuocHopBean() {
        return cuocHopBean;
    }

    public void setCuocHopBean(CuocHopBean cuocHopBean) {
        this.cuocHopBean = cuocHopBean;
    }

    public CuocHopService getCuocHopService() {
        return cuocHopService;
    }

    public JTextField getMaCuocHopJtf() {
        return maCuocHopJtf;
    }

    public void setMaCuocHopJtf(JTextField searchJtf) {
        this.maCuocHopJtf = searchJtf;
    }

    public JPanel getTableJpn() {
        return tableJpn;
    }

    public void setTableJpn(JPanel tableJpn) {
        this.tableJpn = tableJpn;
    }

    public List<CuocHopBean> getList() {
        return list;
    }

    public void setList(List<CuocHopBean> list) {
        this.list = list;
    }

}
