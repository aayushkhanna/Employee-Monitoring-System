/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import project.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author uday mahajan
 */
public class passign extends javax.swing.JInternalFrame {

   
    public passign() {
        initComponents();
        fillenames();
        filltitles();
        showData();
    }

    
    void showData() {

        try {

            Vector h = new Vector();
            h.add("Assign_Id");
            h.add("Eployee_Id");           
            h.add("Project_Id");
            h.add("Status");

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from project_assign";
            PreparedStatement y = x.prepareStatement(q);
            ResultSet rs = y.executeQuery();

            Vector d = new Vector();

            String assignid, empid,pid,status ;
            while (rs.next()) {
                assignid=rs.getString("assign_id");
                empid = rs.getString("empid");
                pid = rs.getString("project_id");
                status = rs.getString("status");
                

                Vector R = new Vector();
                R.add(assignid);
                R.add(empid);

                R.add(pid);
                R.add(status);

               

                //inserted into data
                d.add(R);
            }
            y.close();
            x.close();
            DefaultTableModel z = new DefaultTableModel(d, h);
            table.setModel(z);
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }

    }
    
    void clear()
    {
        cmbempid.getSelectedItem();
        cmbpid.getSelectedItem();
    }
    void assign()
    {
        
        try {
            String  pid,passignid="0",empid,status="Pending";
            
            MyData1 r1=(MyData1)cmbempid.getSelectedItem();
            empid=r1.empid;  
            MyData r=(MyData)cmbpid.getSelectedItem();
            pid=r.pid;
            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");

            String q = "insert into project_assign values(?,?,?,?)";

            PreparedStatement y = x.prepareStatement(q);
            y.setString(1, passignid);
            y.setString(2, empid);
            y.setString(3, pid);
            y.setString(4, status);
            
            y.executeUpdate();
            y.close();

            x.close();

            JOptionPane.showMessageDialog(this, "Project Assigned");

            

            y.close();

            x.close();
            showData();
            clear();

            
           

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());

            ex.printStackTrace();
        }
    }
    
    
     void fillenames() {

        try {

            cmbempid.removeAllItems();
            

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from employee where designation='Project Manager' order by name ";
            PreparedStatement y = x.prepareStatement(q);
            ResultSet rs = y.executeQuery();
           		
            while (rs.next()) {
		MyData1 g=new MyData1();
		g.empid=rs.getString("empid");
		g.ename=rs.getString("name");

                cmbempid.addItem(g);
            }

            y.close();
            x.close();

            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }

    }
     
      void filltitles() {

        try {

            cmbpid.removeAllItems();
            

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from project where status='pending' order by project_title";
            PreparedStatement y = x.prepareStatement(q);
            ResultSet rs = y.executeQuery();
           		
            while (rs.next()) {
		MyData g=new MyData();
		g.pid=rs.getString("project_id");
		g.title=rs.getString("project_title");

                cmbpid.addItem(g);
            }

            y.close();
            x.close();

            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbempid = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbpid = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 350, 300));

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Employee Id");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, -1));

        jPanel1.add(cmbempid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 250, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Project Manager");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Project");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jPanel1.add(cmbpid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 250, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Assign");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 70, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 310, 160));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 440, 360));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Assign Project");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 190, 50));

        jButton2.setText("Send Mail");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
assign();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
TableModel z = table.getModel();

        int row = table.getSelectedRow();
        
        String empid = (String) z.getValueAt(row, 1);
        String pid = (String) z.getValueAt(row, 2);
        

        
        for(int i=0;i<cmbempid.getItemCount();i++)
        {
            MyData1 p=(MyData1)cmbempid.getItemAt(i);
            if(p.empid.equals(empid))
            {
                cmbempid.setSelectedIndex(i);
                break;
            }
        }
             for(int j=0;j<cmbpid.getItemCount();j++)
        {
            MyData a=(MyData)cmbpid.getItemAt(j);
            if(a.pid.equals(pid))
            {
                cmbpid.setSelectedIndex(j);
                break;
            }

            // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
      MyData1 my=(MyData1)cmbempid.getSelectedItem();
      
      String empid=my.empid;
      String email="";
      try
      {
       Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from employee where empid=?";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,empid);
            ResultSet rs = y.executeQuery();
           		
            if (rs.next()) {
             email=rs.getString("email");
            }
      
      
     
      
     
        String subject="";
        String message="";
        SendMail.sendEmail(email, subject, message);
      }
        catch(Exception ex)
      {
          ex.printStackTrace();
      } 
        
    }//GEN-LAST:event_jButton2ActionPerformed
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbempid;
    private javax.swing.JComboBox cmbpid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
