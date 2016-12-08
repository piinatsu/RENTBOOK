package rc2server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class LoginAdmin  extends JFrame {
     public LoginAdmin() {
       
        setTitle("Login Admin");
        setSize(400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        

        panel1.setLayout(null);

        JLabel lblBook = new JLabel("BOOKRENT");
        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();

        lblBook.setBounds(10, 0, 80, 50);
        panel1.add(lblBook);
        lblUsername.setBounds(10,80,80,25);
        panel1.add(lblUsername);
        txtUsername.setBounds(120,80,180,25);
        panel1.add(txtUsername);
        lblPassword.setBounds(10,120,80,25);
        panel1.add(lblPassword);
        txtPassword.setBounds(120,120,180,25);
        panel1.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        JButton btnSign = new JButton("Sign Up");

        btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ASDASDASD");
			}
		});
        
        btnLogin.setBounds(180,190,80,25);
        panel1.add(btnLogin);
        btnSign.setBounds(280,190,80,25);
        panel1.add(btnSign);

        setLayout(new GridLayout(2, 1));
        add(panel1);
        setVisible(true);  
        
    }
    
    public static void main(String[] args) {
          LoginAdmin main = new LoginAdmin();
     }
    
}

    
    

