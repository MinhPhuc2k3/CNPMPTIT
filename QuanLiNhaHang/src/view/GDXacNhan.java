package view;
import control.DatBanDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Ban;
import model.BanDuocDat;
import model.Khachhang;
import model.LanDatBan;
public class GDXacNhan extends JFrame implements ActionListener{
    private BanDuocDat ban;
    private Khachhang khachhang;
    private Date ngayGio;
    private JButton btnXacNhan;
    private JButton btnHuy;
    private JTable tblDanhSachBan;
    private JTable tblKhachHang;
    private JTextField txtNgayGio;
    private LanDatBan lanDatBan;

    public GDXacNhan(BanDuocDat ban, Khachhang khachhang, Date ngayGio) {
        this.setSize(600,600);              
        this.setLocation(200,10);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.ban = ban;
        this.khachhang = khachhang;
        this.ngayGio = ngayGio;
        this.btnHuy = new JButton("Hủy");
        this.btnXacNhan = new JButton("Xác nhận");
        DefaultTableModel bm = new DefaultTableModel(
                new String[]{"ID Bàn","Tên Bàn","Số lượng khách tối đa","Mô tả"},0
        );
        DefaultTableModel kh = new DefaultTableModel(
                new String[]{"ID","Tên","Email","Số điện thoại", "Địa chỉ"},0
        );
        tblDanhSachBan = new JTable();
        tblKhachHang = new JTable();
        tblDanhSachBan.setModel(bm);
        tblKhachHang.setModel(kh);
        kh.addRow(new Object[]{khachhang.getID(), khachhang.getTen(), khachhang.getEmail(), khachhang.getSoDienThoai(), khachhang.getDiaChi()});
        for(Ban x: ban.getBan()){
              bm.addRow(x.toObject());
          }
        JScrollPane jbm  = new JScrollPane(tblDanhSachBan);
        JScrollPane jkh = new JScrollPane(tblKhachHang);
        JPanel bang = new JPanel();
        bang.setLayout((new BoxLayout(bang, BoxLayout.Y_AXIS)));
        bang.add(jbm);
        bang.add(jkh);
        
        JPanel nut = new JPanel();
        nut.setLayout(new BoxLayout(nut, BoxLayout.X_AXIS));
        nut.add(btnHuy);
        nut.add(btnXacNhan);
        
        this.add(bang, BorderLayout.CENTER);
        this.add(nut, BorderLayout.SOUTH);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        txtNgayGio = new JTextField("Giờ đặt bàn: "+df.format(this.ngayGio));
        txtNgayGio.setForeground(Color.red);
        txtNgayGio.setEnabled(false);
        bang.add(txtNgayGio);
        btnHuy.addActionListener(this);
        btnXacNhan.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn.equals(btnHuy)){
            btnHuy_actionPerformed();
        }else if(btn.equals(btnXacNhan)){
            btnXacnhan_actionPerformed();
        }
    }
    
    public void btnHuy_actionPerformed(){
    
    }
    public void btnXacnhan_actionPerformed(){
        this.lanDatBan = new LanDatBan(ngayGio, khachhang, ban);
        DatBanDAO dao = new DatBanDAO();
        dao.addLanDatBan(lanDatBan);
        JOptionPane.showMessageDialog(this, "Đặt bàn thành công");
        (new GDChinhNhanVien()).setVisible(true);
        this.dispose();
    }
}
