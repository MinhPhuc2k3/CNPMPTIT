package model;
public class Khachhang {
    private int ID;
    private String ten;
    private String email;
    private String soDienThoai;
    private String diaChi;
    public Khachhang(){};
    public Khachhang(int ID, String ten, String email, String soDienThoai, String diaChi) {
        this.ID = ID;
        this.ten = ten;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}