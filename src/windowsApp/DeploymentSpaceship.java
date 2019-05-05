/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowsApp;

import Exceptions.ExceptionsDatabase;
import SwingTools.SwingTools;
import dao.Dao;
import exceptions.ExceptionsDao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Runway;
import models.Spaceship;

/**
 *
 * @author dafna
 */
public class DeploymentSpaceship extends javax.swing.JDialog {

    /**
     * Creates new form DeploymentSpaceship
     */
    public DeploymentSpaceship(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            SwingTools.getSwingTools().generateSelect(spaceship, "deployment");
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
        deployment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        deployment.setText("Deployment spaceship");
        deployment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deploymentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(spaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(deployment, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(spaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(deployment)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deploymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deploymentActionPerformed
        String spaceshipSelected = this.spaceship.getSelectedItem().toString();
        if (spaceshipSelected.equalsIgnoreCase("Choose a spaceship") || spaceshipSelected.trim().equalsIgnoreCase("Choose a spaceship")) {
            JOptionPane.showMessageDialog(this, "You have not selected any spaceship", "Message", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Spaceship deploymentSpaceship = new Spaceship(spaceshipSelected);
                Runway runway = Dao.getDao().selectRunwayBySpaceship(spaceshipSelected);
                Dao.getDao().updateSpaceshipRunwayDeployment(deploymentSpaceship, runway);
                JOptionPane.showMessageDialog(this, "The spaceship has been deployment successful", "Message", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException | ExceptionsDatabase ex) {
                JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_deploymentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deployment;
    private javax.swing.JComboBox<String> spaceship;
    // End of variables declaration//GEN-END:variables
}
