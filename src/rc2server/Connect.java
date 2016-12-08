/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc2server;

/**
 *
 * @author Udmin
 */
import java.io.*;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lava
 */
class Connect extends Thread 
{
    RC2Server servGUI;
    String IP;
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    String[] msg;
    int meja;
    private static Lock lock = new ReentrantLock();
    
    public Connect (Socket aClientSocket, RC2Server exServGUI, String exIP, int exMeja) 
    {
        try 
	{
            DBConn.setConnection();
            clientSocket = aClientSocket;
            IP = exIP;
            meja = exMeja;
            servGUI = exServGUI;
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());            
            this.start();
	} 
	catch(IOException e) 
	{
            System.out.println("Connection: "+e.getMessage());
	}
    }

    public void run()
    {
        while(true)
        { 
            try 
            {
                System.out.println("Request");
                String data = in.readObject().toString();
                System.out.println(data);
                msg = data.split("-");
                if (msg[0].equalsIgnoreCase("Meja")) 		
                    out.writeObject(meja);
                else if (msg[0].equalsIgnoreCase("Menu")) 
                {
                    TableModel tabmodMenu = tableModel("SELECT ID_BUKU AS KODE, NAMA_BUKU AS JUDUL FROM BUKU");
                    out.writeObject(tabmodMenu);
                }
                else if (msg[0].equalsIgnoreCase("Search")){
                    TableModel tabmodMenu = tableModel("SELECT * FROM BUKU WHERE ID_BUKU LIKE '%" + msg[1] 
                            + "%' OR GENRE_BUKU LIKE '%" + msg[1]  + "%' OR NAMA_BUKU LIKE '%" + msg[1] 
                            + "%' OR PENGARANG LIKE '%" + msg[1] + "%' OR PENERBIT LIKE '%" + msg[1] 
                            + "%' OR HARGA_BUKU LIKE '%" + msg[1] + "%' OR RAK LIKE '%" + msg[1]
                            + "%' OR STOK LIKE '%" + msg[1] + "%'");
                    out.writeObject(tabmodMenu);
                }
                else if (msg[0].equalsIgnoreCase("Pesanan"))
                {
                    lock.lock();

                        DBConn.rowSet.setCommand("SELECT ID_PESANAN, NO_MEJA AS 'Meja', NAMA_BARANG AS 'Nama', JUMLAH AS 'Jumlah', STATUS AS 'Status', IP, TANGGAL FROM pesanan");
                        DBConn.rowSet.execute();
                        DBConn.rowSet.moveToInsertRow();
                        DBConn.rowSet.updateString("Meja", msg[1]);
                        DBConn.rowSet.updateString("Nama", msg[2]);
                        DBConn.rowSet.updateInt("Jumlah", Integer.parseInt(msg[3]));
                        DBConn.rowSet.updateString("Status", msg[5]);
                        DBConn.rowSet.updateString("IP", IP);
                        DBConn.rowSet.updateString("TANGGAL", LocalDateTime.now().toString());
                        DBConn.rowSet.insertRow();			
                        //servGUI.updatePesanan();
                        
                        TableModel tabmodPesanan = tableModel("SELECT NO_MEJA AS 'Meja', NAMA_BARANG AS 'Nama', JUMLAH AS 'Jumlah', STATUS AS 'Status' FROM pesanan WHERE IP = '" + IP + "' AND status <> 'Lunas' ");
                        out.writeObject(tabmodPesanan);

                    lock.unlock();
                }
                else if (msg[0].equalsIgnoreCase("Antrian")) 
                {
                    TableModel tabmodAntrian = tableModel("SELECT NO_MEJA AS 'Meja', NAMA_BARANG AS 'Nama', JUMLAH AS 'Jumlah' FROM pesanan WHERE STATUS NOT in ('Lunas', 'Bayar', 'SiapSaji') ORDER BY TANGGAL ASC ");
                    out.writeObject(tabmodAntrian);
                }
                else if (msg[0].equalsIgnoreCase("Status")) 
                {
                    TableModel tabmodStatus = tableModel("SELECT NO_MEJA AS 'Meja', NAMA_BARANG AS 'Menu', JUMLAH AS 'Jumlah', STATUS AS 'Status' FROM pesanan WHERE NO_MEJA = '" + msg[1] + "' AND STATUS <> 'Lunas' ");
                    out.writeObject(tabmodStatus);
                }
                else if (msg[0].equalsIgnoreCase("Bayar")) 
                {
                    lock.lock();
                        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rc2database","root","");
                        PreparedStatement ps = con.prepareStatement("UPDATE pesanan SET STATUS=?, TANGGAL=? WHERE NO_MEJA=? AND NAMA_BARANG=? AND STATUS=?");
                        ps.setString(1, "Bayar");
                        ps.setString(2, LocalDateTime.now().toString());
                        ps.setString(3, msg[1]);
                        ps.setString(4, msg[3]);
                        ps.setString(5, "SiapSaji");
                        ps.executeUpdate();
                        //servGUI.updatePesanan();
                        //servGUI.updateBayar();                        
                        
                        TableModel orderModel = tableModel("SELECT NO_MEJA AS 'Meja', NAMA_BARANG AS 'Nama', JUMLAH AS 'Jumlah', STATUS AS 'Status' FROM pesanan WHERE NO_MEJA = '" + msg[1] + "' AND STATUS <> 'Lunas' ");
                        out.writeObject(orderModel);
                    lock.unlock();
                }
                else if (msg[0].equalsIgnoreCase("AllBuku")) {
                    TableModel tabmodAntrian = tableModel("SELECT ID_BUKU AS 'ID', GENRE_BUKU AS 'Genre', NAMA_BUKU AS 'Judul' FROM BUKU");
                    out.writeObject(tabmodAntrian);
                }
            } 
            catch(EOFException e) 
            {
                System.out.println("EOF: "+e.getMessage());
            } 
            catch(IOException e) 
            {
                System.out.println("IO:s a"+e.getMessage());
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                        Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public TableModel tableModel(String str) throws SQLException
    {		
        DBConn.rowSet.setCommand(str);
        DBConn.rowSet.execute();
		
	return DBTableModel.resultSetToTableModel(DBConn.rowSet);
    }
}
