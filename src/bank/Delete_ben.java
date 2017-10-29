/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import Class.ben;
import Class.IPClass;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aditya
 */
public class Delete_ben extends javax.swing.JFrame {

   long cust;
   String mobile;
   DefaultTableModel dtm;
    public Delete_ben(long cust) {
       
	   this.cust=cust;
	   dtm = new DefaultTableModel(0, 0);
	   String header[] = new String[] { "Account", "Beneficiary Name", "IFSC",
	       "email", "mobile_no", "Pin" };
	   dtm.setColumnIdentifiers(header);
	   initComponents();
	   init_table();
        
	
	
    }

  
    public void init_table()
    {
	try
	{
	Socket s=new Socket(IPClass.host,5555);
	   ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
	    ObjectInputStream in=new ObjectInputStream(s.getInputStream());
	    out.flush();
	    out.writeLong(cust);
	    out.flush();
	    ArrayList<ben> a=(ArrayList<ben>)in.readObject();
	    Iterator<ben> t=a.iterator();
	    while(t.hasNext())
	    {
		ben b=t.next();
		dtm.addRow(new Object[]{b.account,b.name,b.IFSC,b.email,b.mobile,b.pin});
	    }
	    in.close();
	    out.close();
	    s.close();
	}catch (IOException ex) {
	   Logger.getLogger(Delete_ben.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
	   Logger.getLogger(Delete_ben.class.getName()).log(Level.SEVERE, null, ex);
       }
	
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(dtm);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Delete Benecianry");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mobile=JOptionPane.showInputDialog("Enter mobile number of beneficiary you want to remove",0);
       try {
	   Socket s=new Socket(IPClass.host,4444);
	   ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
	   ObjectInputStream in=new ObjectInputStream(s.getInputStream());
	   out.flush();
	   out.writeUTF(mobile);
	   out.flush();
	   String k=in.readUTF();
	   if("OK".equals(k))
	   {
	       JOptionPane.showMessageDialog(rootPane,"Deleted Successfully");
	       int i=0;
		while(i!=dtm.getRowCount())
		{
		    dtm.removeRow(i);
		}
	       init_table();
	   }
	   in.close();
	   out.close();
	   s.close();
       } catch (IOException ex) {
	   Logger.getLogger(Delete_ben.class.getName()).log(Level.SEVERE, null, ex);
       }
	
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
	new Beneficiaries(cust).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
