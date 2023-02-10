package controllers;

import Bean.CuocHopBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import services.CuocHopService;
import services.StringService;
import utility.TableModelCuocHop;
import views.infoViews.InfoJframe;

/**
 * 
 * @author Nuan
 */

public class CuocHopPanelController {
    private JTextField searchJtf;
    private JPanel tableJpn;
    private JComboBox StatusJcb;
    private List<CuocHopBean> list;
    private final CuocHopService cuocHopService = new CuocHopService();
    private final TableModelCuocHop tableModelCuocHop = new TableModelCuocHop();
    private final String COLUNMS[] = { "Mã cuộc họp", "Người tạo", "Nội dung chính", "Ngày họp" };
    private JFrame parentJFrame;

    public CuocHopPanelController(JTextField searchJtf, JPanel tableJpn, JComboBox statusJcb) {
        this.searchJtf = searchJtf;
        this.tableJpn = tableJpn;
        this.StatusJcb = statusJcb;
        this.list = cuocHopService.getListCuocHop();
        setData();
        initAction();
    }

    private void initAction() {
        this.searchJtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = cuocHopService.getListCuocHop();
                } else {
                    list = cuocHopService.search(key);
                }
                setData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = cuocHopService.getListCuocHop();
                } else {
                    list = cuocHopService.search(key);
                }
                setData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
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
        String status = StringService.covertToString((String)this.StatusJcb.getSelectedItem());
        System.out.println("Status is : " + status);
        list = cuocHopService.statisticCuocHop(status);
        DefaultTableModel model = tableModelCuocHop.setTableCuocHop(list, COLUNMS);

        JTable table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }

        };
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    CuocHopBean temp = list.get(table.getSelectedRow());
                    InfoJframe infoJframe = new InfoJframe(temp.toString(), parentJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                }
            }

        });

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        tableJpn.removeAll();
        tableJpn.setLayout(new BorderLayout());
        tableJpn.add(scroll);
        tableJpn.validate();
        tableJpn.repaint();
    }

    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
    
    public List<CuocHopBean> getList() {
        return list;
    }

    public void setList(List<CuocHopBean> list) {
        this.list = list;
    }

    public JTextField getSearchJtf() {
        return searchJtf;
    }

    public void setSearchJtf(JTextField searchJtf) {
        this.searchJtf = searchJtf;
    }

    public JPanel getTableJpn() {
        return tableJpn;
    }

    public void setTableJpn(JPanel tableJpn) {
        this.tableJpn = tableJpn;
    }
    
    public JComboBox getStatusJcb() {
        return StatusJcb;
    }

    public void setStatusJcb(JComboBox StatusJcb) {
        this.StatusJcb = StatusJcb;
    }

}
