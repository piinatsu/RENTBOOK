/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc2server;

import javax.swing.JOptionPane;

/**
 *
 * @author Udmin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RC2Server().setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        });
    }
    
}