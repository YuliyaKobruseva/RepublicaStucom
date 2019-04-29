/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowsApp;

import SwingTools.SwingTools;
import dao.Dao;
import exceptions.ExceptionsDao;
import exceptions.InputException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Runway;
import tools.ToolsApp;

/**
 *
 * @author dafna
 */
public class NewRunway extends javax.swing.JDialog {

    /**
     * Creates new form NewRunway
     */
    public NewRunway(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        nameRequired.setVisible(false);
        spaceportRequired.setVisible(false);
        try {
            SwingTools.getSwingTools().generateSelect(spaceport, "spaceport");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
        } catch (ExceptionsDao ex) {
            JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
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
        jLabel2 = new javax.swing.JLabel();
        nameRunway = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameRequired = new javax.swing.JLabel();
        addRunway = new javax.swing.JButton();
        spaceport = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        spaceportRequired = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name of runway");

        jLabel2.setText("Select a spaceport");

        nameRunway.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameRunwayFocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        nameRequired.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nameRequired.setForeground(new java.awt.Color(255, 0, 0));
        nameRequired.setText("Name is required");

        addRunway.setText("Add a new runway");
        addRunway.setToolTipText("");
        addRunway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRunwayActionPerformed(evt);
            }
        });

        spaceport.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                spaceportFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        spaceportRequired.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        spaceportRequired.setForeground(new java.awt.Color(255, 0, 0));
        spaceportRequired.setText("Spaceport is required");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(addRunway, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spaceportRequired, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nameRequired, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                .addComponent(nameRunway)
                                .addComponent(spaceport, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameRunway, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(nameRequired)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spaceport, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spaceportRequired)
                .addGap(41, 41, 41)
                .addComponent(addRunway)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spaceportFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spaceportFocusLost
        checkSelect();
    }//GEN-LAST:event_spaceportFocusLost

    private void nameRunwayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameRunwayFocusLost
        String runway = nameRunway.getText();
        if (runway.equalsIgnoreCase("") || runway.trim().equalsIgnoreCase("")) {
            nameRequired.setVisible(true);
        } else {
            nameRequired.setVisible(false);
        }
    }//GEN-LAST:event_nameRunwayFocusLost

    private void addRunwayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRunwayActionPerformed
        String spaceportSelected = checkSelect();
        if (spaceportSelected == null) {
            spaceportRequired.setVisible(true);
        } else {
            try {
                int runway = ToolsApp.convertStringToNumber(nameRunway.getText());
                Runway newRunway = new Runway(runway);
                if (Dao.getDao().existRunway(newRunway)) {
                    JOptionPane.showMessageDialog(this, "Runway already exist", "Message", JOptionPane.WARNING_MESSAGE);
                } else {
                    Dao.getDao().insertRunway(newRunway, spaceportSelected);
                    JOptionPane.showMessageDialog(this, "Runway created successful", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (InputException ex) {
                JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
            }
        }


    }//GEN-LAST:event_addRunwayActionPerformed

    private String checkSelect() {
        String spaceportSelected = spaceport.getSelectedItem().toString();
        int selectedIndexSpaceport = spaceport.getSelectedIndex();
        if (spaceportSelected.equalsIgnoreCase("Choose a spaceport") || selectedIndexSpaceport == -1) {
            spaceportRequired.setVisible(true);
        } else {
            spaceportRequired.setVisible(false);
            return spaceportSelected;
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRunway;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel nameRequired;
    private javax.swing.JTextField nameRunway;
    private javax.swing.JComboBox<String> spaceport;
    private javax.swing.JLabel spaceportRequired;
    // End of variables declaration//GEN-END:variables
}