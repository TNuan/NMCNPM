package controllers.CuocHopManagerController;

import services.CuocHopService;
import utility.TableModelCuocHop;

import java.util.Date;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import Bean.CuocHopBean;

public class EditCuocHopController {
    private CuocHopBean cuocHopBean;
    private final CuocHopService cuocHopService = new CuocHopService();
    private JTextField searchJtf;
    private JPanel tableJpn;
    private List<CuocHopBean> list;
    private final TableModelCuocHop tableModelCuocHop = new TableModelCuocHop();
    private final String CLUNMS[] = {"Mã cuộc họp", "Người tạo cuộc họp", "Nội dung chính", "Ngày họp"};
    private JTextField maCuocHopJtf;
    private JTextField diaDiemJtf;
    private JTextField noiDungJtf;
    private Date ngayHop;

    public EditCuocHopController(CuocHopBean cuocHopBean, JTextField searchJtf, JPanel tableJpn) {
        this.cuocHopBean = cuocHopBean;
        this.searchJtf = searchJtf;
        this.tableJpn = tableJpn;
        setData();
        initAction();
    }

    public void setDataJtf(JTextField maCuocHopJtf, JTextField diaDiemJtf, JTextField noiDungJtf, Date ngayHop) {
        this.maCuocHopJtf = maCuocHopJtf;
        this.diaDiemJtf = diaDiemJtf;
        this.noiDungJtf = noiDungJtf;
        this.ngayHop = ngayHop;
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

    private void setData() {
        
    }


}
