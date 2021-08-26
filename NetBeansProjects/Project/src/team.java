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
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author uday mahajan
 */
public class team extends javax.swing.JInternalFrame {

    /**
     * Creates new form team
     */
    String empid;
    public team(String empid) {
        initComponents();
       
        
        //showteam();
        this.empid=empid;
        
         filltitles();
        addTeamMembers();
        fillteamleaders();
        
        
        Date d = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yy");
            String cdate = sd.format(d);
            txtdoc.setText(cdate);
    }

    void fillteamleaders() {
        try {

            cmbleaders.removeAllItems();

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from employee where designation='Team Leader' order by name";
            PreparedStatement y = x.prepareStatement(q);
            ResultSet rs = y.executeQuery();

            while (rs.next()) {
                MyData1 g = new MyData1();
                g.empid = rs.getString("empid");
                g.ename = rs.getString("name");

                cmbleaders.addItem(g);
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

            cmbproject.removeAllItems();

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from project,project_assign where project_assign.empid=? and project.project_id=project_assign.project_id and project.status='Pending'";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,empid);
            ResultSet rs = y.executeQuery();

            while (rs.next()) {
                MyData g = new MyData();
                g.pid = rs.getString("assign_id");
                g.title = rs.getString("project_title");

                cmbproject.addItem(g);
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
            String teamid, teamleader, doc, project, status = "Pending";
            teamid = txttid.getText();

            MyData1 r1 = (MyData1) cmbleaders.getSelectedItem();
            teamleader = r1.empid;
            doc = txtdoc.getText();

            MyData r = (MyData) cmbproject.getSelectedItem();
            project = r.pid;

            ListModel lt = listmembers.getModel();
            

           List t =  listmembers.getSelectedValuesList();
         
            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");

            String q = "insert into team values(?,?,?,?,?)";

            PreparedStatement y = x.prepareStatement(q);

            y.setString(1, teamid);
            y.setString(2, teamleader);

            y.setString(3, doc);
            y.setString(4, status);

            y.setString(5, project);
            int n = y.executeUpdate();

//            get recent teamid starts
            q = "select * from team order by team_id desc";

            y = x.prepareStatement(q);
            ResultSet rs = y.executeQuery();
            String recentteamid = "";
            if (rs.next()) {
                recentteamid = rs.getString("team_id");
            }

            for (int i = 0; i < t.size(); i++) {
                 MyData1 my=(MyData1)t.get(i);
                q = "insert into team_members (team_id,emp_id) values(?,?)";

                y = x.prepareStatement(q);

                y.setString(1, recentteamid);
                y.setString(2, my.empid);

                y.executeUpdate();

            }

//            get recent teamid  ends         
           

            String d = n + " data inserted";

            y.close();

            x.close();

            JOptionPane.showMessageDialog(this, d);
         
            clearData();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());

            ex.printStackTrace();

        }
    }

    void modifyData() {
        try {
            String teamid, teamleader, doc, project;
            teamid = txttid.getText();
            teamleader = (String) cmbleaders.getSelectedItem();
            doc = txtdoc.getText();

            project = (String) cmbproject.getSelectedItem();

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");

            String q = "update team set team_leader=?, doc=?, project_id=?  where team_id=? ";

            PreparedStatement y = x.prepareStatement(q);

            y.setString(4, teamid);

            y.setString(1, teamleader);

            y.setString(2, doc);
            y.setString(3, project);

            int n = y.executeUpdate();
            String d = n + " data updated";

            y.close();

            x.close();

            JOptionPane.showMessageDialog(this, d);

           
            clearData();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());

            ex.printStackTrace();
        }
    }

    void clearData() {
        txttid.setText("");
        cmbleaders.setSelectedIndex(0);
        txtdoc.setText("");
        listmembers.setSelectedIndex(0);

        cmbproject.setSelectedItem(0);

    }

    void addTeamMembers() {

        try {

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from employee where designation='Team Member' order by name";
            PreparedStatement y = x.prepareStatement(q);
            ResultSet rs = y.executeQuery();

            DefaultListModel df = new DefaultListModel();

            while (rs.next()) {
                MyData1 my = new MyData1();
                my.empid = rs.getString("empid");
                my.ename = rs.getString("name");
                df.addElement(my);

            }
            listmembers.setModel(df);
            x.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    void showteam(String assignid) {

        try {

            Vector h = new Vector();
            h.add("Team_Id");
            h.add("Team_Leader");
            h.add("Date_of_Creation");
            h.add("Status");

            h.add("Project");

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");
            String q = "select * from team where assignid=?";
            PreparedStatement y = x.prepareStatement(q);
            y.setString(1,assignid);
            ResultSet rs = y.executeQuery();

            Vector d = new Vector();

            String tid, teamleader, doc, status = "Pending", projectid;
            while (rs.next()) {
                tid = rs.getString("team_id");
                teamleader = rs.getString("team_leader");
                doc = rs.getString("DATE_of_creation");
                SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                java.util.Date dx1 = f1.parse(doc);
                f1 = new SimpleDateFormat("dd-MMM-yyyy");
                doc = f1.format(dx1);
                status = rs.getString("status");
                projectid = rs.getString("assignid");

                Vector R = new Vector();
                R.add(tid);
                R.add(teamleader);

                R.add(doc);
                R.add(status);

                R.add(projectid);

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
    
    
//    void showteammembers() 
//    {
//
//        try {
//
//            Vector h = new Vector();
//            h.add("Team Members");
//           
//           
//
//            h.add("Project Id");
//
//            Class.forName("oracle.jdbc.OracleDriver");
//            String str = "jdbc:oracle:thin:@localhost:1521:";
//            Connection x = DriverManager.getConnection(str, "project", "12345");
//            String q = "select * from team_members";
//            PreparedStatement y = x.prepareStatement(q);
//            ResultSet rs = y.executeQuery();
//
//            Vector d = new Vector();
//
//            String pid ,teammember;
//            while (rs.next()) {
//                tid = rs.getString("team_id");
//                eid = rs.getString("emp_id");
//                 = rs.getString("s.no");
//                SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                java.util.Date dx1 = f1.parse(doc);
//                f1 = new SimpleDateFormat("dd-MMM-yyyy");
//                doc = f1.format(dx1);
//                status = rs.getString("status");
//                projectid = rs.getString("Project_id");
//
//                Vector R = new Vector();
//                R.add(tid);
//                R.add(teamleader);
//
//                R.add(doc);
//                R.add(status);
//
//                R.add(projectid);
//
//                //inserted into data
//                d.add(R);
//            }
//            y.close();
//            x.close();
//            DefaultTableModel z = new DefaultTableModel(d, h);
//            table.setModel(z);
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
//            ex.printStackTrace();
//        }
//
//     }
    
    void deleteData() {
        try {
            String teamid;
            teamid = txttid.getText();

            Class.forName("oracle.jdbc.OracleDriver");
            String str = "jdbc:oracle:thin:@localhost:1521:";
            Connection x = DriverManager.getConnection(str, "project", "12345");

            String q = "delete from team where team_id=?";

            PreparedStatement y = x.prepareStatement(q);

            y.setString(1, teamid);

            int n = y.executeUpdate();
            String d = n + " data deleted";

            y.close();

            x.close();

            JOptionPane.showMessageDialog(this, d);

           
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

        jLabel1 = new javax.swing.JLabel();
        txttid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbleaders = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdoc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listmembers = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbproject = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Team Leader");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        txttid.setBackground(new java.awt.Color(204, 204, 255));
        txttid.setBorder(null);
        getContentPane().add(txttid, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 290, 30));

        jLabel2.setText("Team Id");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        getContentPane().add(cmbleaders, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 290, 30));

        jLabel3.setText("Date of Creation");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel9.setText("(dd-mon-yyyy)");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        txtdoc.setEditable(false);
        txtdoc.setBackground(new java.awt.Color(204, 204, 255));
        txtdoc.setBorder(null);
        getContentPane().add(txtdoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 290, 30));

        jScrollPane1.setViewportView(listmembers);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 300, -1));

        jLabel5.setText("Team Members");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jLabel6.setText("Project Id");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, -1));

        getContentPane().add(cmbproject, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 300, 30));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 510, 590));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Manage Team");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 530, 620));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 8, -1, -1));

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 80, 30));

        jButton3.setText("Modify");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 80, 30));

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 70, 30));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 330, 70));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 290, 20));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 290, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(jPanel5);

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -40, 950, 710));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 380, 620));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 102, 300, 10));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 102, 300, 10));
        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        MyData my=(MyData)cmbproject.getSelectedItem();
        String assignid=my.pid;
        saveData();  
        showteam(assignid);// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         MyData my=(MyData)cmbproject.getSelectedItem();
        String assignid=my.pid;
        
        modifyData(); 
        showteam(assignid);// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
         MyData my=(MyData)cmbproject.getSelectedItem();
        String assignid=my.pid;
        deleteData();  
        showteam(assignid);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        TableModel k1 = table.getModel();
        String tid = (String) k1.getValueAt(row, 0);
        String tleader = (String) k1.getValueAt(row, 1);
        String doc = (String) k1.getValueAt(row, 2);

        String project = (String) k1.getValueAt(row, 5);

        for (int i = 0; i < cmbleaders.getItemCount(); i++) {
            MyData1 p = (MyData1) cmbleaders.getItemAt(i);
            if (p.empid.equals(tleader)) {
                cmbleaders.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < cmbproject.getItemCount(); i++) {
            MyData p = (MyData) cmbproject.getItemAt(i);
            if (p.pid.equals(project)) {
                cmbproject.setSelectedIndex(i);
                break;
            }
        }

        txttid.setText(tid);
        txtdoc.setText(doc);

        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbleaders;
    private javax.swing.JComboBox cmbproject;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JList listmembers;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtdoc;
    private javax.swing.JTextField txttid;
    // End of variables declaration//GEN-END:variables
}
