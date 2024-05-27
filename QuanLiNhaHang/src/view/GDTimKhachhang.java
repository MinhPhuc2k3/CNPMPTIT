package view;
import control.DatBanDAO;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.BanDuocDat;
import model.Khachhang;
import view.GDXacNhan;
public class GDTimKhachhang extends JFrame implements ActionListener, MouseListener{
    private JTextField txtTen;
    private JTable tblKhachhang;
    private JButton btnTimKiem;
    private JButton btnThem;
    private BanDuocDat ban;
    private Date ngayGio;
    private JButton btnQuayLai;

    public GDTimKhachhang(BanDuocDat ban, Date ngayGio){
        this.txtTen = new JTextField("Tên");
        this.btnTimKiem = new JButton("Tìm kiếm");
        this.btnThem  = new JButton("Thêm khách hàng mới");
        this.btnQuayLai  = new JButton("Quay lại");
        this.ban = ban;
        this.ngayGio = ngayGio;
        
        this.setSize(600,600);              
        this.setLocation(200,10);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        JLabel lbTen = new JLabel("Tên");
        pane.add(lbTen);
        pane.add(txtTen);
        pane.add(btnTimKiem);
        String[] cols = {"ID", "Tên", "Email","Số điện thoại", "Địa chỉ"};
        DefaultTableModel tbm = new DefaultTableModel(cols,0);
        tblKhachhang = new JTable(tbm);
        JScrollPane bang = new JScrollPane(tblKhachhang);
        this.add(pane, BorderLayout.NORTH);
        this.add(bang, BorderLayout.CENTER);
        
        JPanel nut = new JPanel();
        nut.setLayout(new BoxLayout(nut, BoxLayout.X_AXIS));
        nut.add(this.btnQuayLai);
        nut.add(this.btnThem);
        
        this.add(nut, BorderLayout.SOUTH);
        btnThem.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnQuayLai.addActionListener(this);
        tblKhachhang.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if(btn.equals(btnThem)){
            btnThem_actionPerformed();
        }
        if(btn.equals(btnTimKiem)){
            btnTimKiem_actionPerformed();
        }
    }
    public void btnThem_actionPerformed(){
        (new GDThemKH()).setVisible(true);
    }
    public void btnTimKiem_actionPerformed(){
        DatBanDAO dao = new DatBanDAO();
        List<Khachhang> listKH = dao.GetListKhachhang(txtTen.getText());
        DefaultTableModel tbm = (DefaultTableModel)tblKhachhang.getModel();
        tbm.setRowCount(0);
        for(Khachhang x: listKH){
            tbm.addRow(new Object[]{x.getID(), x.getTen(), x.getEmail(), x.getSoDienThoai(), x.getDiaChi()});
        }
    }
    
    public void selectKhachhang_performed(){
        int x = tblKhachhang.getSelectedRow();
        Khachhang kh = new Khachhang(Integer.parseInt(tblKhachhang.getValueAt(x, 0).toString()), tblKhachhang.getValueAt(x, 1).toString(), tblKhachhang.getValueAt(x, 2).toString(), tblKhachhang.getValueAt(x, 3).toString(), tblKhachhang.getValueAt(x, 4).toString());
        (new GDXacNhan(ban, kh, ngayGio)).setVisible(true);
        this.dispose();
    }
    
    public void btnQuayLai_actionPerformed(){
        (new GDTimBan()).setVisible(true);
        this.dispose();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(tblKhachhang)){
            selectKhachhang_performed();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}    
}
