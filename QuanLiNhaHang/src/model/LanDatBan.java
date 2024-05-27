
package model;
import java.util.Date;
import model.BanDuocDat;
import model.Khachhang;
public class LanDatBan {
    private int ID;
    private Date ngayGio;
    private Khachhang khachHang;
    private BanDuocDat banDuocDat;
    public LanDatBan(Date ngayGio, Khachhang khachHang, BanDuocDat banDuocDat) {
        this.ngayGio = ngayGio;
        this.khachHang = khachHang;
        this.banDuocDat = banDuocDat;
    }
    public int getID() {
        return ID;
    }
    public Date getNgayGio() {
        return ngayGio;
    }
    public Khachhang getKhachHang() {
        return khachHang;
    }
    public BanDuocDat getBanDuocDat() {
        return banDuocDat;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setNgay(Date ngayGio) {
        this.ngayGio = ngayGio;
    }
    public void setKhachHang(Khachhang khachHang) {
        this.khachHang = khachHang;
    }
    public void setBanDuocDat(BanDuocDat banDuocDat) {
        this.banDuocDat = banDuocDat;
    }
}