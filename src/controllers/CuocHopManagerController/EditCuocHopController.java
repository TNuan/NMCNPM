package controllers.CuocHopManagerController;

import services.CuocHopService;
import utility.TableModelCuocHop;

import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.toedter.calendar.JDateChooser;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Bean.CuocHopBean;
import Bean.MemOfMeeting;
import models.ThamGiaCuocHopModel;

public class EditCuocHopController {
    private CuocHopBean cuocHopBean;
    private JTextField searchJtf;
    private JPanel tableJpn;
    private JPanel memTableJpn;
    private List<CuocHopBean> list;
    private List<MemOfMeeting> listMemOfMeeting;
    private final CuocHopService cuocHopService = new CuocHopService();
    private final TableModelCuocHop tableModelCuocHop = new TableModelCuocHop();
    private final TableModelCuocHop tableModelMemOfMeeting = new TableModelCuocHop();
    private final String COLUNMS[] = { "Mã cuộc họp", "Người tạo", "Nội dung chính", "Ngày họp" };
    private final String COLUNMS_TGCH[] = { "Họ tên", "Ngày sinh", "Giới tính" };
    private JTextField maCuocHopJtf;
    private JTextField diaDiemJtf;
    private JTextField noiDungJtf;
    private JDateChooser ngayHopDateC;

    public EditCuocHopController(CuocHopBean cuocHopBean, JTextField searchJtf, JPanel tableJpn, JPanel memTableJpn) {
        this.cuocHopBean = cuocHopBean;
        this.searchJtf = searchJtf;
        this.tableJpn = tableJpn;
        this.memTableJpn = memTableJpn;
        this.list = cuocHopService.getListCuocHop();
        setData();
        initAction();
    }

    public void setDataJtf(JTextField maCuocHopJtf, JTextField diaDiemJtf, JTextField noiDungJtf,
            JDateChooser ngayHopDateC) {
        this.maCuocHopJtf = maCuocHopJtf;
        this.diaDiemJtf = diaDiemJtf;
        this.noiDungJtf = noiDungJtf;
        this.ngayHopDateC = ngayHopDateC;
    }

    public void initAction() {
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
        // System.out.println(list);
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CuocHopBean temp = list.get(table.getSelectedRow());
                cuocHopBean.setNguoiTaoCuocHop(temp.getNguoiTaoCuocHop());
                cuocHopBean.setCuocHopModel(temp.getCuocHopModel());
                cuocHopBean.setListNhanKhauModels(temp.getListNhanKhauModels());
                cuocHopBean.setListThamGiaCuocHop(temp.getListThamGiaCuocHop());

                maCuocHopJtf.setText(cuocHopBean.getCuocHopModel().getMaCuocHop());
                diaDiemJtf.setText(cuocHopBean.getCuocHopModel().getDiaDiem());
                noiDungJtf.setText(cuocHopBean.getCuocHopModel().getNoiDung());
                ngayHopDateC.setDate(cuocHopBean.getCuocHopModel().getNgayHop());
                setDateChoose();
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

    public void setDateChoose() {
        listMemOfMeeting = new ArrayList<>();
        for (int i = 0; i < cuocHopBean.getListNhanKhauModels().size(); i++) {
            MemOfMeeting temp = new MemOfMeeting();
            temp.getNhanKhau().setNhanKhauModel(cuocHopBean.getListNhanKhauModels().get(i));
            temp.setThamGiaCuocHopModel(cuocHopBean.getListThamGiaCuocHop().get(i));
            listMemOfMeeting.add(temp);
        }

        DefaultTableModel model_mem = tableModelMemOfMeeting.setTableMember(listMemOfMeeting, COLUNMS_TGCH);
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
        memTableJpn.removeAll();
        memTableJpn.setLayout(new BorderLayout());
        memTableJpn.add(scroll);
        memTableJpn.validate();
        memTableJpn.repaint();
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

    public List<CuocHopBean> getList() {
        return list;
    }

    public void setList(List<CuocHopBean> list) {
        this.list = list;
    }

}
