/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.NguoiDung;
import view.GDTimBan;

/**
 *
 * @author MINH PHUC
 */
public class GDChinhNhanVien extends JFrame implements ActionListener{
    private JButton btnDatBan;
    public GDChinhNhanVien(){
        super("GDChinhNhanVien");
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        JPanel lblPane = new JPanel();
        lblPane.setLayout(new BoxLayout(lblPane, BoxLayout.LINE_AXIS));
        lblPane.add(Box.createRigidArea(new Dimension(450, 0)));
        JLabel lblUser = new JLabel("Nhân viên đăng nhập!");
        lblUser.setAlignmentX(Component.RIGHT_ALIGNMENT);   
        lblPane.add(lblUser);
        listPane.add(lblPane);
        listPane.add(Box.createRigidArea(new Dimension(0,20)));
        
        btnDatBan = new JButton("Đặt bàn");
        btnDatBan.setAlignmentX(Component.CENTER_ALIGNMENT);  
        btnDatBan.addActionListener(this);
        listPane.add(btnDatBan);
        
         this.setSize(600,300);              
        this.setLocation(200,10);
        this.add(listPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnDatBan){
            (new GDTimBan()).setVisible(true);
            this.dispose();
        }
    }
}
