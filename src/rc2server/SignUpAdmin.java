package rc2server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager;
import java.awt.Dimension;

public class SignUpAdmin extends JFrame{
public SignUpAdmin() {

        setTitle("Sign Up");
        setSize(400,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        
        panel1.setLayout(null);
        
        JLabel lblBook = new JLabel("BOOKRENT");
        JLabel lblNama = new JLabel("Nama");
        JLabel lblAlamat = new JLabel("Alamat");
        JLabel lblEmail = new JLabel("E-Mail"); 
        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");
        JTextField txtNama = new JTextField();
        JTextField txtAlamat = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        
        lblBook.setBounds(10, 0, 80, 50);
        panel1.add(lblBook);
        lblNama.setBounds(10,80,80,25);
        panel1.add(lblNama);
        txtNama.setBounds(120,80,180,25);
        panel1.add(txtNama);
        lblAlamat.setBounds(10,120,80,25);
        panel1.add(lblAlamat);
        txtAlamat.setBounds(120,120,180,25);
        panel1.add(txtAlamat);
        lblEmail.setBounds(10,160,80,25);
        panel1.add(lblEmail);
        txtEmail.setBounds(120,160,180,25);
        panel1.add(txtEmail);
        lblUsername.setBounds(10,200,80,25);
        panel1.add(lblUsername);
        txtUsername.setBounds(120,200,180,25);
        panel1.add(txtUsername);
        lblPassword.setBounds(10,250,80,25);
        panel1.add(lblPassword);
        txtPassword.setBounds(120,250,180,25);
        panel1.add(txtPassword);


        JButton btnSign = new JButton("Sign Up");
        JButton btnLogin = new JButton("Login");
        
       
        btnLogin.setBounds(180,290,80,25);
        panel1.add(btnLogin);
        btnSign.setBounds(270,290,80,25);
        panel1.add(btnSign);
        

        setLayout(new GridLayout(2,1));
        add(panel1);
        setVisible(true);  
        
     }
     
        public static void main(String[] args) {
        SignUpAdmin mein = new SignUpAdmin();

     }

    
}
