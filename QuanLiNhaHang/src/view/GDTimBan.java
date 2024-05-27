package view;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import control.DatBanDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import model.Ban;
import model.BanDuocDat;
public class GDTimBan extends JFrame implements ActionListener{
    private JTextField txtNgay;
    private JComboBox cbxGio;
    private JTextField txtSoLuongKhach;
    private JButton  btnTimKiem;
    private JTable tblBan;
    private JButton btnTiepTheo;
    private JButton btnQuayLai;
    private List<Ban> listBan; 
    private Date ngayGio;
    DefaultTableModel tbm;
    public GDTimBan() throws HeadlessException {
        super("Tìm bàn trống");
        this.txtNgay = new JTextField();
        String[] items = {"8:00","10:00", "13:00", "15:00", "17:00", "19:00"};
        this.cbxGio = new JComboBox(items);
        this.txtSoLuongKhach = new JTextField();
        this.btnTimKiem = new JButton("Tìm kiếm");
        this.btnTiepTheo = new JButton("Tiếp theo");
        this.btnQuayLai  = new JButton("Quay lại");
        this.setSize(600,600);              
        this.setLocation(200,10);
        this.setLayout(new BorderLayout()); 
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        String[] columns = {"ID", "Tên", "Số lượng khách tối đa", "Mô tả"};
        this.tbm = new DefaultTableModel(columns, 0);
        this.tblBan = new JTable();
        tblBan.setModel(tbm);
        JScrollPane header = new JScrollPane(tblBan);
        
        JPanel nhap = new JPanel();
        nhap.setLayout(new BoxLayout(nhap, BoxLayout.X_AXIS));
        JLabel lbNgay = new JLabel("Ngày");
        nhap.add(lbNgay);
        nhap.add(this.txtNgay);
        JLabel lbGio = new JLabel("Giờ");
        nhap.add(lbGio);
        nhap.add(this.cbxGio);
        JLabel lbsl = new JLabel("Số lượng khách");
        nhap.add(lbsl);
        nhap.add(this.txtSoLuongKhach);
        nhap.add(this.btnTimKiem);
        
        this.add(nhap, BorderLayout.NORTH);
        this.add(header, BorderLayout.CENTER);
        
        JPanel nut = new JPanel();
        nut.setLayout(new BoxLayout(nut, BoxLayout.X_AXIS));
        nut.add(this.btnQuayLai);
        nut.add(this.btnTiepTheo);
        
        this.add(nut, BorderLayout.SOUTH);
        btnTimKiem.addActionListener(this);
        btnTiepTheo.addActionListener(this);
        btnQuayLai.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if(btn.equals(this.btnTimKiem)){
          btnTimKiem_actionPerfromed();
        }else if(btn.equals(this.btnTiepTheo)){
            btnTiepTheo_actionPerformed();
        }else if(btn.equals(this.btnQuayLai)){
            btnQuayLai_actionPerformed();
        }
    }
    public void btnTimKiem_actionPerfromed(){
        DatBanDAO datBan = new DatBanDAO();
          tbm.setNumRows(0);
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            ngayGio = df.parse(txtNgay.getText()+" "+cbxGio.getSelectedItem().toString());
        } catch (ParseException ex) {
            Logger.getLogger(GDTimBan.class.getName()).log(Level.SEVERE, null, ex);
        }
          listBan = datBan.GetlistBanTrong(txtNgay.getText()+" "+cbxGio.getSelectedItem().toString(), txtSoLuongKhach.getText());
          for(Ban x: listBan){
              tbm.addRow(x.toObject());
          }
          
          tblBan.setModel(tbm);
          tblBan.setVisible(true);
    }
    public void btnTiepTheo_actionPerformed(){
        List<Ban> banDuocDat = new ArrayList<>();
        for(int x :tblBan.getSelectedRows()){
            banDuocDat.add(listBan.get(x));
            System.out.println(listBan.get(x).getID());
        }
        BanDuocDat ban = new BanDuocDat();
        ban.setBan(banDuocDat);
        (new GDTimKhachhang(ban, ngayGio)).setVisible(true);
        this.dispose();
    }
    
    public void btnQuayLai_actionPerformed(){
        (new GDChinhNhanVien()).setVisible(true);
        this.dispose();
    }
}
