/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class NguoiDung {
    private String ten;
    private String matkhau;
    private String chucvu;
    public NguoiDung() {
    }

    public NguoiDung(String ten, String matkhau, String chucvu) {
        this.ten = ten;
        this.matkhau = matkhau;
        this.chucvu = chucvu;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }
    
    
}
