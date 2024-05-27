package model;

public class Ban {
    private int ID;
    private String ten;
    private int soLuongKhachToiDa;
    private String moTa;
    public Ban(){   
    }
    public Ban(int ID, String ten, int soLuongKhachToiDa, String moTa) {
        this.ID = ID;
        this.ten = ten;
        this.soLuongKhachToiDa = soLuongKhachToiDa;
        this.moTa = moTa;
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
    public int getSoLuongKhachToiDa() {
        return soLuongKhachToiDa;
    }
    public void setSoLuongKhachToiDa(int soLuongKhachToiDa) {
        this.soLuongKhachToiDa = soLuongKhachToiDa;
    }
    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public Object[] toObject(){
        return new Object[]{ID, ten, soLuongKhachToiDa, moTa};
    }
}
