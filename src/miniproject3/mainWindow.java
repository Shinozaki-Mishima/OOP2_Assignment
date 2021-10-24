/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject3;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shinozaki-Kirishima
 */
public class mainWindow extends javax.swing.JFrame {

    /**
     * Creates new form mainWindow
     */
    String imagePath;
    int position=0;
    
    public mainWindow() {
        initComponents();
        getConnection();
        showProductsInTable();
        //welcome message
        final ImageIcon icon1 = new ImageIcon("Joptionpane/welcome.gif");
        JOptionPane.showMessageDialog(null, "", "Welcome !!!",
        JOptionPane.INFORMATION_MESSAGE, icon1);
    }
    public Connection getConnection()
    {
        Connection connection = null;
        try {
            connection=DriverManager.getConnection("jdbc:mariadb://localhost:3306/miniproject3","root","");
            return connection;
        } catch (SQLException ex) {
            final ImageIcon icon1 = new ImageIcon("Joptionpane/notConnected.gif");
            JOptionPane.showMessageDialog(null, "", "Not Connected ...",
            JOptionPane.INFORMATION_MESSAGE, icon1);
            return null;
        }
    }
    
    //check inputs 
    public boolean checkInput()
    {
        if(idTxt.getText()==null||nameTxt.getText()==null||priceTxt.getText()==null||dateChooser.getText()==null){
            return false;
        }else{
            try{
                Float.parseFloat(priceTxt.getText());
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }
    
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
        ImageIcon myimage=null;
        if(imagePath!=null)
        {
            myimage=new ImageIcon(imagePath);
        }else
        {
            myimage=new ImageIcon(pic);
        }
        Image img=myimage.getImage();
        Image img2=img.getScaledInstance(imageDisplay.getWidth(), imageDisplay.getHeight(), imageDisplay.getWidth());
        ImageIcon image=new ImageIcon(img2);
        return image;
    }
    
    //Display data from database using the JTable
    //function to fill ArrayList with data
    public ArrayList<Products>getProductList()
    {
        ArrayList<Products> productList=new ArrayList<Products>();
        Connection connection=getConnection();
        String query="SELECT * FROM products";
        
        Statement statement;
        ResultSet resultSet;
        
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            Products product;
            
            while(resultSet.next())
            {
                product=new Products(resultSet.getInt("id"),resultSet.getString("name"),
                        Float.parseFloat(resultSet.getString("price")),resultSet.getString("add_date"),resultSet.getBytes("image"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }
    //function to populate the table with data
    public void showProductsInTable()
    {
        ArrayList<Products> productList=getProductList();
        DefaultTableModel model=(DefaultTableModel)displayDataBase.getModel();
        //clear table content   
        model.setRowCount(0);
        Object[] row=new Object[4];
        for (int i = 0; i < productList.size(); i++) 
        {
            row[0]=productList.get(i).getId();
            row[1]=productList.get(i).getName();
            row[2]=productList.get(i).getPrice();
            row[3]=productList.get(i).getAddDate();
            
            model.addRow(row);
        }
    }
    
    //function to display to the user the items within the database 
    public void showItem(int index)
    {
        idTxt.setText(Integer.toString(getProductList().get(index).getId()));
        nameTxt.setText(getProductList().get(index).getName());
        priceTxt.setText(Float.toString(getProductList().get(index).getPrice()));
        dateChooser.setText(getProductList().get(index).getAddDate());
        imageDisplay.setIcon(ResizeImage(null, getProductList().get(index).getPicture()));
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
        idLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        addDateLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        idTxt = new javax.swing.JTextField();
        nameTxt = new javax.swing.JTextField();
        priceTxt = new javax.swing.JTextField();
        dateChooser = new datechooser.beans.DateChooserCombo();
        imageDisplay = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayDataBase = new javax.swing.JTable();
        chooseImage = new javax.swing.JButton();
        insert = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        first = new javax.swing.JButton();
        next = new javax.swing.JButton();
        previous = new javax.swing.JButton();
        last = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        idLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        idLabel.setText("ID:");

        nameLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        nameLabel.setText("NAME:");

        priceLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        priceLabel.setText("PRICE:");

        addDateLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        addDateLabel.setText("ADD DATE:");

        imageLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        imageLabel.setText("IMAGE:");

        idTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        idTxt.setEnabled(false);
        idTxt.setPreferredSize(new java.awt.Dimension(59, 40));
        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        nameTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        nameTxt.setPreferredSize(new java.awt.Dimension(59, 40));
        nameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTxtActionPerformed(evt);
            }
        });

        priceTxt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        priceTxt.setPreferredSize(new java.awt.Dimension(59, 40));
        priceTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTxtActionPerformed(evt);
            }
        });

        imageDisplay.setOpaque(true);

        displayDataBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Add Date"
            }
        ));
        displayDataBase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayDataBaseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(displayDataBase);

        chooseImage.setText("Choose Image");
        chooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImageActionPerformed(evt);
            }
        });

        insert.setText("Insert");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        first.setText("First");
        first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionPerformed(evt);
            }
        });

        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        previous.setText("Previous");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        last.setText("Last");
        last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(idTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(priceTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imageDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(insert, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(last, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chooseImage))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(last, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTxtActionPerformed

    private void nameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTxtActionPerformed

    private void priceTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTxtActionPerformed

    private void chooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseImageActionPerformed
       JFileChooser file=new JFileChooser();
       file.setCurrentDirectory(new File(System.getProperty("user.home")));
       
       FileNameExtensionFilter filter=new FileNameExtensionFilter("*.images","jpg","png");
       file.addChoosableFileFilter(filter);
       int result=file.showSaveDialog(null);
       if(result==JFileChooser.APPROVE_OPTION){
           File selectedFile=file.getSelectedFile();
           String path=selectedFile.getAbsolutePath();
           imageDisplay.setIcon(ResizeImage(path,null));
           //confirmation
           final ImageIcon icon1 = new ImageIcon("Joptionpane/chooseImage.gif");
           JOptionPane.showMessageDialog(null, "", "Image Choosen!",
           JOptionPane.INFORMATION_MESSAGE, icon1);
           
           imagePath=path;
       }else{//no file selected
             final ImageIcon icon1 = new ImageIcon("Joptionpane/NoFileSelected.gif");
           JOptionPane.showMessageDialog(null, "", "No File Selected !!!!",
           JOptionPane.INFORMATION_MESSAGE, icon1);
       }
    }//GEN-LAST:event_chooseImageActionPerformed

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        // code for insert button 
        if(checkInput()&&imagePath!=null)
        {
          try
          {
             Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products(name,price,add_date,image)"
                     + "values(?,?,?,?) ");// set fields
             preparedStatement.setString(1, nameTxt.getText());
             preparedStatement.setString(2, priceTxt.getText());//make simple dateform,at and store selected date in string 
            //SimpleDateFormat dateFromat=new SimpleDateFormat("MM-dd-yyyy");
             String addDate = this.dateChooser.getText();//make string addDate = to date Chooser.getText
             preparedStatement.setString(3, addDate);
             //
             InputStream img = new FileInputStream(new File(imagePath));
             preparedStatement.setBlob(4, img);
             preparedStatement.executeUpdate();
            // JOptionPane.showMessageDialog(null, "Data Stored");
            final ImageIcon icon1 = new ImageIcon("Joptionpane/Insert.gif");
            JOptionPane.showMessageDialog(null, "", "Data Stored!",
            JOptionPane.INFORMATION_MESSAGE, icon1);
             showProductsInTable();
          }catch(FileNotFoundException | SQLException e)
          {
             JOptionPane.showMessageDialog(null, e); 
          }
        }else
        {
            final ImageIcon icon1 = new ImageIcon("Joptionpane/missingField.gif");
            JOptionPane.showMessageDialog(null, "", "One or more fields are empty or wrong!!!!!",
            JOptionPane.INFORMATION_MESSAGE, icon1);
        }
    }//GEN-LAST:event_insertActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // code for update button
        if(checkInput()&&idTxt.getText()!=null){
             String updateQuery=null;
             Connection connection=getConnection();
             PreparedStatement preparedStatement=null;
             //update without image 
             if(imagePath==null){
                 try
                 {
                    updateQuery="UPDATE products SET name = ?, price = ?, add_date = ? WHERE id = ?"; 
                    preparedStatement=connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, nameTxt.getText());
                    preparedStatement.setString(2, priceTxt.getText());
                    String addDate = this.dateChooser.getText();
                    preparedStatement.setString(3, addDate);
                    preparedStatement.setInt(4, Integer.parseInt(this.idTxt.getText()));
                    
                    preparedStatement.executeUpdate();
                    showProductsInTable();
                    final ImageIcon icon1 = new ImageIcon("Joptionpane/updateComplete.gif");
                    JOptionPane.showMessageDialog(null, "", "Successfully Updated!!",
                    JOptionPane.INFORMATION_MESSAGE, icon1);
                 }catch(SQLException e)
                 {
                     JOptionPane.showMessageDialog(null, e);
                 }//update with image 
                 }else
                 {
                try {     
                    InputStream img = new FileInputStream(new File(imagePath));
                    updateQuery="UPDATE products SET name = ?, price = ?, add_date = ?, image = ? WHERE id = ?"; 
                    preparedStatement=connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, nameTxt.getText());
                    preparedStatement.setString(2, priceTxt.getText());
                    String addDate = this.dateChooser.getText();
                    preparedStatement.setString(3, addDate);
                    preparedStatement.setBlob(4, img);
                    preparedStatement.setInt(5, Integer.parseInt(this.idTxt.getText()));
                    
                    preparedStatement.executeUpdate();
                    showProductsInTable();
                    final ImageIcon icon1 = new ImageIcon("Joptionpane/updateComplete.gif");
                    JOptionPane.showMessageDialog(null, "", "Successfully Updated!!",
                    JOptionPane.INFORMATION_MESSAGE, icon1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                 }
        }else{
            final ImageIcon icon1 = new ImageIcon("Joptionpane/missingField.gif");
            JOptionPane.showMessageDialog(null, "", "One or more fields are empty or wrong!!!!!",
            JOptionPane.INFORMATION_MESSAGE, icon1);
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // code for delete button here : 
        if(!idTxt.getText().equals(""))
        {
            try
            {
                Connection connection = getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM products WHERE id = ?");
                int id =Integer.parseInt(idTxt.getText());
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                showProductsInTable();
                final ImageIcon icon1 = new ImageIcon("Joptionpane/Deleted.gif");
                    JOptionPane.showMessageDialog(null, "", "Deletion Complete ...",
                    JOptionPane.INFORMATION_MESSAGE, icon1);
            }catch(Exception e)
            {
               JOptionPane.showMessageDialog(null, "Product Not Deleted."); 
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void displayDataBaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displayDataBaseMouseClicked
        // when value in table is clicked display item info to user 
        int index=this.displayDataBase.getSelectedRow();
        showItem(index);
    }//GEN-LAST:event_displayDataBaseMouseClicked

    private void firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionPerformed
        //code for button "first"
        position=0;
        this.showItem(position);
    }//GEN-LAST:event_firstActionPerformed

    private void lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionPerformed
        // Tcode for last button action 
        position=getProductList().size()-1;
        this.showItem(position);
    }//GEN-LAST:event_lastActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // code for next action button 
        position++;
        if(position>=getProductList().size())
        {
            position=getProductList().size()-1;
        }
        this.showItem(position);
    }//GEN-LAST:event_nextActionPerformed

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        position--;
        if(position<0)
        {
            position=0;
        }
        this.showItem(position);
    }//GEN-LAST:event_previousActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton Update;
    private javax.swing.JLabel addDateLabel;
    private javax.swing.JButton chooseImage;
    private datechooser.beans.DateChooserCombo dateChooser;
    private javax.swing.JTable displayDataBase;
    private javax.swing.JButton first;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel imageDisplay;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton insert;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton last;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JButton next;
    private javax.swing.JButton previous;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTxt;
    // End of variables declaration//GEN-END:variables
}
