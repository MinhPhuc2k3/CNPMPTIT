package control;
import model.Ban;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.BanDuocDat;
import model.Khachhang;
import model.LanDatBan;
 
public class DatBanDAO {
    public static Connection dbCon;
     
    public DatBanDAO(){
        if(dbCon == null){
            String dbUrl = 
                "jdbc:mysql://localhost:3306/quanlinhahang?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.jdbc.Driver";
            try {
                Class.forName(dbClass);
                dbCon = DriverManager.getConnection (dbUrl, "root", "123456");
            }catch(ClassNotFoundException | SQLException e) {
            }
        }
    }
    
    public  List<Ban> GetlistBanTrong(String ngayGio, String soLuongKhach){
        String sql = "SELECT * FROM tblban WHERE ID NOT IN (SELECT BanID FROM tblBanDuocDat WHERE LanDatBanID IN (SELECT ID FROM tblLanDatBan WHERE NgayGio = ?))";
        List<Ban> listBan = new ArrayList<>();
        try{
            PreparedStatement ps = dbCon.prepareStatement(sql);
            ps.setString(1, ngayGio);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                listBan.add(new Ban(Integer.parseInt(res.getString("ID")),res.getString("Ten"),Integer.parseInt(res.getString("SoLuongKhachToiDa")),res.getString("Mota")));
            }
        }catch(NumberFormatException | SQLException e) {
        }
        return listBan;
    }
    
    public List<Khachhang> GetListKhachhang(String ten){
        String sql = "SELECT * FROM tblKhachhang WHERE Ten = ?";
        List <Khachhang> listKH= new ArrayList<>();
        try{
            PreparedStatement ps = dbCon.prepareStatement(sql);
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listKH.add(new Khachhang(Integer.parseInt(rs.getString("ID")), rs.getString("Ten"), rs.getString("Email"), rs.getString("Diachi"), rs.getString("SoDienThoai")));
            }
        }
        catch(NumberFormatException|SQLException e){
        }
        return listKH;
    }
    
    public void addLanDatBan(LanDatBan lanDatBan){
        String sqlAddLanDatBan = "INSERT INTO tblLanDatBan(KhachhangID, NgayGio) VALUES(?,?)";
        String sqlAddBanDuocDat = "INSERT INTO tblBanDuocDat(LanDatBanID, BanID) VALUES(?,?)";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try{
            PreparedStatement ps = dbCon.prepareStatement(sqlAddLanDatBan, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lanDatBan.getKhachHang().getID());
            ps.setString(2, df.format(lanDatBan.getNgayGio()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            ps = dbCon.prepareStatement(sqlAddBanDuocDat);
            if(generatedKeys.next()){
                lanDatBan.setID(generatedKeys.getInt(1));
                for(Ban ban: lanDatBan.getBanDuocDat().getBan()){
                       ps.setInt(1, lanDatBan.getID());
                       ps.setInt(2, ban.getID());
                       ps.executeUpdate();
                }
                
            }
        }catch(SQLException e){
        }
    }
    
    public void addKhach(Khachhang khachHang) throws Exception{
        String sqlAddkhachHang = "INSERT INTO tblKhachhang (Ten, Email, SoDienThoai, DiaChi) VALUES(?,?,?,?)";
        String timKhachHang = "SELECT * FROM tblKhachhang WHERE Ten = ? and Email=? and SoDienThoai=? and DiaChi=?";
        try{
            PreparedStatement ps = dbCon.prepareStatement(timKhachHang);
            ps.setString(1,khachHang.getTen());
            ps.setString(2,khachHang.getEmail());
            ps.setString(3, khachHang.getSoDienThoai());
            ps.setString(4, khachHang.getDiaChi());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                throw new Exception("Đã tồn tại khách hàng");
            }
            else{
                try{
                PreparedStatement p = dbCon.prepareStatement(sqlAddkhachHang);
                p.setString(1,khachHang.getTen());
                p.setString(2,khachHang.getEmail());
                p.setString(3, khachHang.getSoDienThoai());
                p.setString(4, khachHang.getDiaChi());
                p.executeUpdate();
            }catch(SQLException ex){
            }
            }
        }catch(SQLException e){
        }
        
    }
}