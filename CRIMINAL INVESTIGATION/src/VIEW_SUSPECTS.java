
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PANDA
 */
public class VIEW_SUSPECTS extends javax.swing.JInternalFrame implements ListSelectionListener{

    /**
     * Creates new form VIEW_SUSPECTS
     */
    public VIEW_SUSPECTS() {
        initComponents();
        fillModel1();
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
        jLabel4.setEnabled(false);
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel8.setEnabled(false);
        jLabel9.setEnabled(false);
        jLabel10.setEnabled(false);
        jLabel11.setEnabled(false);
        jLabel12.setEnabled(false);
        
        
        jTable1.getSelectionModel().addListSelectionListener(this);
    }
public void valueChanged(ListSelectionEvent evt) {
        MysqlDataSource m=new MysqlDataSource();
        m.setUser("root");
        m.setPassword("mysql");
        m.setDatabaseName("criminal_investigator");
        m.setServerName("localhost");
        m.setPortNumber(3306);
        try
        {
            Connection con=m.getConnection();
            int row=jTable1.getSelectedRow();
            int id=Integer.parseInt(jTable1.getValueAt(row,8).toString());
            //System.out.print(id);
            PreparedStatement ps=con.prepareStatement("Select suspect_name,image,rank,mobile_no,logical_score,physical_score from suspect_details where record_id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                String name=rs.getString("suspect_name");
                String imgName=rs.getString("image");
                Float prob=rs.getFloat("rank");
                String mob=rs.getString("mobile_no");
                int l=rs.getInt("Logical_score");
                int p=rs.getInt("Physical_score");
                String file=System.getProperty("user.dir")+"\\src\\profile_pics\\suspects\\"+imgName;
                File f=new File(file);
                BufferedImage img=ImageIO.read(f);
                Image dimg = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(),
        Image.SCALE_SMOOTH);
                ImageIcon icon=new ImageIcon(dimg);
                jLabel2.setEnabled(true);
                jLabel2.setIcon(icon);
                jLabel3.setEnabled(true);
                jLabel3.setText("Name : ");
                jLabel4.setEnabled(true);
                jLabel4.setText("Mobile no : ");
                jLabel5.setEnabled(true);
                jLabel5.setText(name);
                jLabel6.setEnabled(true);
                jLabel6.setText(mob);
                jLabel7.setEnabled(true);
                jLabel7.setText("Logical Score : ");
                jLabel8.setEnabled(true);
                jLabel8.setText("Physical Score : ");
                jLabel9.setEnabled(true);
                jLabel9.setText(Integer.toString(l));
                jLabel10.setEnabled(true);
                jLabel10.setText(Integer.toString(p));
                jLabel11.setEnabled(true);
                jLabel11.setText("Probability : ");
                jLabel12.setEnabled(true);
                jLabel12.setText(Float.toString(prob));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
            
        }
private void fillModel1()
    {
        MysqlDataSource m=new MysqlDataSource();
        m.setUser("root");
        m.setPassword("mysql");
        m.setDatabaseName("criminal_investigator");
        m.setServerName("localhost");
        m.setPortNumber(3306);
        try
        {
            Connection con=m.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select case_id from case_detail");
            int id;
            jComboBox1.addItem("Select Case ID");
            while(rs.next())
            {
                id=rs.getInt("case_id");
                jComboBox1.addItem(Integer.toString(id));
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
private void displayTable()
{
    MysqlDataSource m=new MysqlDataSource();
        m.setUser("root");
        m.setPassword("mysql");
        m.setDatabaseName("criminal_investigator");
        m.setServerName("localhost");
        m.setPortNumber(3306);
        try
        {
            Connection con=m.getConnection();            
            PreparedStatement stmt=con.prepareStatement("Select case_id,suspect_name,mobile_no,address,relation,date,rank,note,record_id from suspect_details where case_id=?");
            stmt.setInt(1,Integer.parseInt((String)jComboBox1.getSelectedItem()));
            ResultSet rs=stmt.executeQuery();
            DefaultTableModel model = new DefaultTableModel(new String[]{"CaseId","Name","Mobile no","Address","Relation","Date","Rank","Note","RecordID"},0); 
            while(rs.next())
            {
                int case_id=rs.getInt("case_id");
                String name=rs.getString("suspect_name");
                String no=rs.getString("mobile_no");
                String address=rs.getString("address");
                String relation=rs.getString("relation");
                Date d=rs.getDate("date");
                int points=rs.getInt("rank");
                String note=rs.getString("note");
                int id=rs.getInt("record_id");
                model.addRow(new Object[] {case_id,name,no,address,relation,d,points,note,id});
            }
            jTable1.setModel(model);
            jTable1.getColumnModel().getColumn(8).setWidth(0);
            jTable1.getColumnModel().getColumn(8).setMinWidth(0);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
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

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jSplitPane1.setDividerLocation(200);

        jComboBox1.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel1.setText("Case ID : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTable1.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CaseID", "Name", "Mobile No.", "Address", "Relation", "Date", "Rank", "Note", "RecordID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Float.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        jSplitPane2.setLeftComponent(jScrollPane1);

        jPanel2.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jPanel2.setLayout(null);

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel2.add(jLabel5);
        jLabel5.setBounds(236, 68, 150, 25);

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel2.add(jLabel6);
        jLabel6.setBounds(236, 99, 150, 25);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(jLabel4);
        jLabel4.setBounds(130, 99, 100, 25);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(jLabel3);
        jLabel3.setBounds(130, 68, 100, 25);

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 32, 114, 133);

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(jLabel8);
        jLabel8.setBounds(396, 99, 100, 25);

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(jLabel7);
        jLabel7.setBounds(396, 68, 100, 25);

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel9);
        jLabel9.setBounds(502, 68, 75, 25);

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel10);
        jLabel10.setBounds(502, 99, 75, 25);

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(jLabel11);
        jLabel11.setBounds(587, 84, 100, 25);

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel2.add(jLabel12);
        jLabel12.setBounds(693, 84, 75, 25);

        jSplitPane2.setRightComponent(jPanel2);

        jSplitPane1.setRightComponent(jSplitPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       displayTable();
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
