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
public class modules extends javax.swing.JInternalFrame {

    /**
     * Creates new form modules
     */
    public modules() {
        initComponents();
        filltitles();
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
                MyData g = new MyData();
                g.pid = rs.getString("project_id");
                g.title = rs.getString("project_title");

                cmbpid.addItem(g);
            }

            y.close();
            x.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    void saveData() {
        try {
            String pid, md, des, mid = "0";
            MyData m = (MyData) cmbpid.getSelectedItem();

            pid = m.pid;
            

            md = txtmd.getText();
            des = txtdes.getText();
            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");

            String q = "insert into modules values(?,?,?,?)";

            PreparedStatement y = x.prepareStatement(q);

            y.setString(1, mid);
            y.setString(2, pid);
            y.setString(3, md);

            y.setString(4, des);

            int n = y.executeUpdate();
            String d = n + " data inserted";

            y.close();

            x.close();

            JOptionPane.showMessageDialog(this, d);
            showData(pid);
            clearData();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());

            ex.printStackTrace();

        }
    }

    void modifyData() {
        try {
            String pid, md, des, mid = "0";
            MyData m = (MyData) cmbpid.getSelectedItem();

            pid = m.pid;

            md = txtmd.getText();
            des = txtdes.getText();

            String moduleid = txtmoduleid.getText();
            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");

            String q = "update modules set project_id=?,module_name=?,description=? where module_id=? ";

            PreparedStatement y = x.prepareStatement(q);

            y.setString(1, pid);
            y.setString(2, md);
            y.setString(3, des);

            y.setString(4, moduleid);

            int n = y.executeUpdate();
            String d = n + " data updated";

            y.close();

            x.close();

            JOptionPane.showMessageDialog(this, d);
            showData(pid);
            clearData();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());

            ex.printStackTrace();

        }
    }

    void clearData() {

        txtmd.setText("");
        txtdes.setText("");

    }

    void showData(String projectid) {

        try {

            Vector h = new Vector();
            h.add("Module_Id");
            h.add("Project Id");
            h.add("Module");
            h.add("Description");
          

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from modules where project_id=?";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,projectid);
            ResultSet rs = y.executeQuery();

            Vector d = new Vector();

            String moduleid, projectidx, modulename, description;
            while (rs.next()) {
                moduleid = rs.getString("module_id");
                projectidx = rs.getString("Project_id");
                modulename = rs.getString("module_name");
                description = rs.getString("description");

                Vector row = new Vector();
                row.add(moduleid);
                row.add(projectidx);
                row.add(modulename);
                row.add(description);

                //inserted into data
                d.add(row);
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

    void deleteData() {
        try {
             MyData m = (MyData) cmbpid.getSelectedItem();

           String pid = m.pid;
            String moduleid = txtmoduleid.getText();

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");

            String q = "delete from modules where module_id=?";

            PreparedStatement y = x.prepareStatement(q);

            y.setString(1, moduleid);

            int n = y.executeUpdate();
            String d = n + " data deleted";

            y.close();

            x.close();

            JOptionPane.showMessageDialog(this, d);

            showData(pid);
            clearData();

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

        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtmoduleid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbpid = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtmd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane2.setViewportView(table);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 440, 280));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Manage Modules");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Module Id");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 20));

        txtmoduleid.setBackground(new java.awt.Color(240, 240, 240));
        txtmoduleid.setBorder(null);
        jPanel1.add(txtmoduleid, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, -1));

        jLabel1.setText("Projects");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 60, 20));

        cmbpid.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbpidItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbpid, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 230, -1));

        jLabel2.setText("Module");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        txtmd.setBackground(new java.awt.Color(240, 240, 240));
        txtmd.setBorder(null);
        jPanel1.add(txtmd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 230, -1));

        jLabel3.setText("Description");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 80, -1));

        txtdes.setColumns(20);
        txtdes.setRows(5);
        jScrollPane1.setViewportView(txtdes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 230, 70));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 230, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 230, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 290, 260));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 70, 40));

        jButton3.setText("Modify");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 70, 40));

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 70, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 300, 110));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 530, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        saveData();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modifyData();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteData();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbpidItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbpidItemStateChanged
      MyData m=(MyData)cmbpid.getSelectedItem();
      String pid=  m.pid;
        showData(pid);
    }//GEN-LAST:event_cmbpidItemStateChanged

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
    TableModel z = table.getModel();

        int row = table.getSelectedRow();
        String mid = (String) z.getValueAt(row, 0);
        String pid = (String) z.getValueAt(row, 1);
        String mdname = (String) z.getValueAt(row, 2);
        String des = (String) z.getValueAt(row, 3);
        

        
        for(int i=0;i<cmbpid.getItemCount();i++)
        {
            MyData p=(MyData)cmbpid.getItemAt(i);
            if(p.pid.equals(pid))
            {
                cmbpid.setSelectedIndex(i);
                break;
            }
        }
            
txtmd.setText(mdname);
txtdes.setText(des);
            txtmoduleid.setText(mid);        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbpid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable table;
    private javax.swing.JTextArea txtdes;
    private javax.swing.JTextField txtmd;
    private javax.swing.JTextField txtmoduleid;
    // End of variables declaration//GEN-END:variables
}
