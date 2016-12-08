package rc2server;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AddToCart extends JFrame  {
        public AddToCart(){
            setTitle("AddToCart");
            setSize(400,700);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            
            panel1.setLayout(new GridLayout (1,1));
            
            Vector data = null;
            Vector<Object> columnNames = new Vector<Object>();
            columnNames.add("Kode Buku");
            columnNames.add("Judul Buku");
            columnNames.add("Penulis");
            columnNames.add("Penerbit");
            columnNames.add("ISBN");
            columnNames.add("Kategori");
            
            JTable table = new JTable(data,columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);
            add(scrollPane,BorderLayout.WEST);
            
            panel1.add(scrollPane);
            
            panel2.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            JButton btnNext = new JButton("Next");
            panel2.add(Box.createRigidArea(new Dimension(200,10)));
            panel2.add(btnNext);
            
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            add(panel1);
            add(panel2);
            
            setVisible(true);
           
}

public static void main(String[] args) {
    AddToCart main = new AddToCart();
     }
}
    
