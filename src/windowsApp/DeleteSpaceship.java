/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowsApp;

import Exceptions.ExceptionsDatabase;
import SwingTools.SwingTools;
import exceptions.ExceptionsDao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Spaceship;
import dao.Dao;

/**
 *
 * @author dafna
 */
public class DeleteSpaceship extends javax.swing.JDialog {

    /**
     * Creates new form DeleteSpaceship
     */
    public DeleteSpaceship(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            SwingTools.getSwingTools().generateSelect(spaceship, "spaceship");
        } catch (SQLException | ExceptionsDao ex) {
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

        spaceship = new javax.swing.JComboBox<>();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        delete.setText("Delete spacehip");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(spaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(spaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(delete)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        String spaceshipSelected = spaceship.getSelectedItem().toString();
        if (spaceshipSelected.equalsIgnoreCase("Choose a spaceship")) {
            JOptionPane.showMessageDialog(this, "You have not selected any spaceship", "Message", JOptionPane.WARNING_MESSAGE);
        } else {
            int optionSelected = JOptionPane.showConfirmDialog(this, "Are you sure?", "Delete spaceship", JOptionPane.YES_NO_CANCEL_OPTION);
            // 0=yes, 1=no, 2=cancel
            if (optionSelected == 0) {
                Spaceship deleteSpaceship = new Spaceship(spaceshipSelected);
                try {
                    Dao.getDao().deleteSpaceship(deleteSpaceship);
                    JOptionPane.showMessageDialog(this, "The spaceship has been deleted successful", "Message", JOptionPane.INFORMATION_MESSAGE);
                    SwingTools.getSwingTools().generateSelect(spaceship, "spaceship");
                } catch (SQLException | ExceptionsDatabase | ExceptionsDao ex) {
                    JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JComboBox<String> spaceship;
    // End of variables declaration//GEN-END:variables
}