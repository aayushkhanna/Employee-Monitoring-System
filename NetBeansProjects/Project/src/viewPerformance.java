/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import project.*;

/**
 *
 * @author realinfo
 */
public class viewPerformance extends javax.swing.JInternalFrame {

    /**
     * Creates new form viewPerformance
     */
    Connection x;

    public viewPerformance() {
        initComponents();

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            String str = "jdbc:oracle:thin:@localhost:1521:";
            x = DriverManager.getConnection(str, "project", "12345");
        } catch (Exception ex) {
            Logger.getLogger(viewTeam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void viewEmployees(String desg) {

        try {

            String q = "select * from employee where designation=?";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1, desg);
            ResultSet rs = y.executeQuery();
            while (rs.next()) {

                MyData1 my = new MyData1();
                my.empid = rs.getString("empid");
                my.ename = rs.getString("name");

                cmbemp.addItem(my);

            }
            y.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    void ProjectManagerPerformance(String empid) {

        try {

            Vector h = new Vector();
            h.add("Project Id");
            h.add("Project");
            h.add("Start Date");
            h.add("End Date");
            h.add("Status");
            h.add("Submission Date");

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select project.project_id,project_title,start_date,end_date,project_assign.status,dateofsubmission from project,project_assign,projectsubmission where project_assign.project_id=project.project_id and project_assign.assign_id=projectsubmission.assignid and project_assign.empid=?";
            PreparedStatement y = x.prepareStatement(q);
             y.setString(1,empid);
            ResultSet rs = y.executeQuery();

            Vector d = new Vector();
            
            while (rs.next()) {
               

                Vector r = new Vector();
                r.add(rs.getString("project_id"));
                r.add(rs.getString("project_title"));
                r.add(rs.getString("start_date"));
                r.add(rs.getString("end_date"));
                r.add(rs.getString("status"));
                r.add(rs.getString("dateofsubmission"));
              

                //inserted into data
                d.add(r);
            }
            y.close();
            x.close();

            DefaultTableModel z = new DefaultTableModel(d, h);
            jTable3.setModel(z);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }

    }
    
     void ProjectLeaderPerformance(String empid) {

        try {

            Vector h = new Vector();
            h.add("Team Id");
            h.add("Team Leader");
            h.add("Date of Creation");
           
            h.add("Status");
            h.add("Date of Submission");

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "Select * from team,  teamsubmission ,employee where team.team_id = teamsubmission.teamid and employee.empid= team.team_leader and  employee.empid=?";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,empid);
            ResultSet rs = y.executeQuery();

            Vector d = new Vector();
            
            while (rs.next()) {
               

                Vector r = new Vector();
                r.add(rs.getString("team_id"));
                r.add(rs.getString("team_leader"));
                r.add(rs.getString("date_of_creation"));
                r.add(rs.getString("status"));
                
                r.add(rs.getString("dateofsubmission"));
              

                //inserted into data
                d.add(r);
            }
            y.close();
            x.close();

            DefaultTableModel z = new DefaultTableModel(d, h);
            jTable3.setModel(z);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }

    }
     
      void ProjectMemberPerformance(String empid) {

        try {

            Vector h = new Vector();
            h.add("Module Id");
            h.add("Project Id");
            h.add("Module Name");
            h.add("Date of Assign");
            
            h.add("Date of Submission");

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "Select * from modules, moduleassign ,employee where modules.module_id = moduleassign.moduleid and employee.empid= moduleassign.empid and employee.empid=?";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,empid);
            ResultSet rs = y.executeQuery();

            Vector d = new Vector();
            
            while (rs.next()) {
               

                Vector r = new Vector();
                r.add(rs.getString("module_id"));
                r.add(rs.getString("project_id"));
                r.add(rs.getString("Module_Name"));
                r.add(rs.getString("dateofassign"));
                ;
                r.add(rs.getString("dateofsubmission"));
              

                //inserted into data
                d.add(r);
            }
            y.close();
            x.close();

            DefaultTableModel z = new DefaultTableModel(d, h);
            jTable3.setModel(z);

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbdesg = new javax.swing.JComboBox();
        cmbemp = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Employee");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Designation");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        cmbdesg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Project Manager", "Team Leader", "Team Member" }));
        cmbdesg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbdesgItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbdesg, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 200, 30));

        cmbemp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbempItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 200, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Show");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 100, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 350, 460));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 19, 580, 420));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 620, 460));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Employee Performance");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbdesgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbdesgItemStateChanged
cmbemp.removeAllItems();
        String desg = (String) cmbdesg.getSelectedItem();
        viewEmployees(desg);
    }//GEN-LAST:event_cmbdesgItemStateChanged

    private void cmbempItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbempItemStateChanged

       
       
        
    }//GEN-LAST:event_cmbempItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
         MyData1 my=(MyData1)cmbemp.getSelectedItem();
        String empid=my.empid;
        
       
                
         
      
      
         
        String desg = (String) cmbdesg.getSelectedItem();
        if (desg.equalsIgnoreCase("Project Manager")) {
              ProjectManagerPerformance(empid);

        } else if (desg.equalsIgnoreCase("Team Leader")) {
               ProjectLeaderPerformance(empid);

        } else if (desg.equalsIgnoreCase("Team Member")) {
               ProjectMemberPerformance(empid);

        }

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbdesg;
    private javax.swing.JComboBox cmbemp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
