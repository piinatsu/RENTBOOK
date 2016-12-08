package rc2server;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.Vector;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Administrator extends JFrame {
    public Administrator(){
        
        setTitle("Administrator");

		setSize(500,700);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel(new GridLayout(1,1));

		JPanel panel2 = new JPanel(new GridLayout(5,2));

		JPanel panel3 = new JPanel();
                
		Vector data = null;
                Vector<Object> columnNames = new Vector<Object>();
                columnNames.add("Kode Buku");
                columnNames.add("Judul Buku");
                columnNames.add("Penulis");
                columnNames.add("Penerbit");
                columnNames.add("ISBN");

		JTable table = new JTable(data,columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);
                table.setEnabled(true);
                add(scrollPane,BorderLayout.WEST);
            
                panel1.add(scrollPane);
                
                
		JLabel lblKode = new JLabel("Kode Buku");
		JLabel lblJudul = new JLabel("Judul Buku");
		JLabel lblPengarang = new JLabel("Pengarang");
		JLabel lblPenerbit = new JLabel("Penerbit");
		JLabel lblISBN = new JLabel("ISBN");
		JTextField txtKode = new JTextField();
                JTextField txtJudul = new JTextField();
                JTextField txtPengarang = new JTextField();
                JTextField txtPenerbit = new JTextField();
		JTextField txtISBN = new JTextField();

		panel2.add(lblKode);
                panel2.add(txtKode);
                panel2.add(lblJudul);
                panel2.add(txtJudul);
                panel2.add(lblPengarang);
                panel2.add(txtPengarang);
                panel2.add(lblPenerbit);
                panel2.add(txtPenerbit);
                panel2.add(lblISBN);
                panel2.add(txtISBN);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Book's Data");
		panel2.setBorder(title);
		
                panel3.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                
                JButton btnAdd = new JButton("Add");
		JButton btnUpdate = new JButton("Update");
		JButton btnDelete = new JButton("Delete");
		JButton btnClose = new JButton("Close");

                panel3.add(Box.createRigidArea(new Dimension(25,10)));
		panel3.add(btnAdd);
                panel3.add(Box.createRigidArea(new Dimension(25,10)));
		panel3.add(btnUpdate);
                panel3.add(Box.createRigidArea(new Dimension(25,10)));
		panel3.add(btnDelete);
                panel3.add(Box.createRigidArea(new Dimension(25,10)));
		panel3.add(btnClose);

		setLayout(new GridLayout(3,4));
		add(panel1);
		add(panel2);
		add(panel3);

		setVisible(true);
    }
    
    public static void main(String[] args) {

		Administrator main = new Administrator();

	}
    
}
