package view;
import control.DatBanDAO;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Khachhang;
public class GDThemKH extends JFrame implements ActionListener{
    private JTextField txtTen;
    private JTextField txtEmail;
    private JTextField txtSoDienThoai;
    private JTextField txtDiaChi;
    private JButton btnThem;

    public GDThemKH(){
        txtTen = new JTextField();
        txtEmail = new JTextField();
        txtSoDienThoai = new JTextField();
        txtDiaChi = new JTextField();
        btnThem = new JButton("Thêm");
        this.setSize(600,600);              
        this.setLocation(200,10);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        JLabel lbTen = new JLabel("Tên");
        pane.add(lbTen);
        pane.add(txtTen);
        JLabel lbEmail = new JLabel("Email");
        pane.add(lbEmail);
        pane.add(txtEmail);
        JLabel lbSoDienThoai = new JLabel("Số điện thoại");
        pane.add(lbSoDienThoai);
        pane.add(txtSoDienThoai);
        JLabel lbDiaChi = new JLabel("Địa chỉ");
        pane.add(lbDiaChi);
        pane.add(txtDiaChi);
        this.add(pane, BorderLayout.NORTH);
        this.add(btnThem, BorderLayout.SOUTH);
        btnThem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        btnThem_actionPerformed();
    }
    public void btnThem_actionPerformed(){
        Khachhang kh = new Khachhang();
        kh.setTen(txtTen.getText());
        kh.setEmail(txtEmail.getText());
        kh.setSoDienThoai(txtSoDienThoai.getText());
        kh.setDiaChi(txtDiaChi.getText());
        DatBanDAO dao = new DatBanDAO();
        try{
            dao.addKhach(kh);
            this.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Khách hàng đã tồn tại");
        }
    }
    
}
