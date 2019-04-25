/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowsApp;

import dao.Dao;
import exceptions.ExceptionsDao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Spaceport;

/**
 *
 * @author dafna
 */
public class NewSpaceport extends javax.swing.JDialog {

    /**
     * Creates new form NewSpaceport
     */
    public NewSpaceport(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        nameRequired.setVisible(false);
        planetRequired.setVisible(false);
        galaxyRequired.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spaceportName1 = new javax.swing.JLabel();
        planet = new javax.swing.JLabel();
        galaxy = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        namePlanet = new javax.swing.JTextField();
        nameGalaxy = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        galaxyRequired = new javax.swing.JLabel();
        addSpaceport = new javax.swing.JButton();
        planetRequired = new javax.swing.JLabel();
        nameRequired = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        spaceportName1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        spaceportName1.setLabelFor(name);
        spaceportName1.setText("Name of spaceport");

        planet.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        planet.setLabelFor(namePlanet);
        planet.setText("Planet");

        galaxy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        galaxy.setLabelFor(nameGalaxy);
        galaxy.setText("Galaxy");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        galaxyRequired.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        galaxyRequired.setForeground(new java.awt.Color(255, 0, 0));
        galaxyRequired.setText("Name of galaxy is required");

        addSpaceport.setText("Add new spaceport");
        addSpaceport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSpaceportActionPerformed(evt);
            }
        });

        planetRequired.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        planetRequired.setForeground(new java.awt.Color(255, 0, 0));
        planetRequired.setText("Name of planet is required");

        nameRequired.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        nameRequired.setForeground(new java.awt.Color(255, 0, 0));
        nameRequired.setText("Name of spaceport is required");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spaceportName1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(planet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(galaxy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(namePlanet)
                                        .addGap(12, 12, 12)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameGalaxy, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addComponent(galaxyRequired, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameRequired, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(planetRequired, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(addSpaceport, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spaceportName1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameRequired)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(planet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namePlanet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(galaxy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameGalaxy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(planetRequired)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(galaxyRequired)
                .addGap(44, 44, 44)
                .addComponent(addSpaceport)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addSpaceportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSpaceportActionPerformed
        String nameSpaceport = name.getText();
        String namePlanet = this.namePlanet.getText();
        String nameGalaxy = this.nameGalaxy.getText();
        if (nameSpaceport.equalsIgnoreCase("") && namePlanet.equalsIgnoreCase("") && nameGalaxy.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "All dates are required", "Message", JOptionPane.WARNING_MESSAGE);
        } else {
            if (nameSpaceport.equalsIgnoreCase("") || nameSpaceport.trim().equalsIgnoreCase("")) {
                nameRequired.setVisible(true);
            } else {
                nameRequired.setVisible(false);
                if (namePlanet.equalsIgnoreCase("") || namePlanet.trim().equalsIgnoreCase("")) {
                    planetRequired.setVisible(true);
                } else {
                    planetRequired.setVisible(false);
                    if (nameGalaxy.equalsIgnoreCase("") || nameGalaxy.trim().equalsIgnoreCase("")) {
                        galaxyRequired.setVisible(true);
                    } else {
                        planetRequired.setVisible(false);
                        Spaceport newSpaceport = new Spaceport(nameSpaceport, namePlanet, nameGalaxy);
                        try {
                            if (Dao.getDao().existSpaseport(newSpaceport)) {
                                JOptionPane.showMessageDialog(this, "Spaceport already exist", "Message", JOptionPane.WARNING_MESSAGE);
                            } else {
                                Dao.getDao().insertSpaceport(newSpaceport);
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
                        } catch (ExceptionsDao ex) {
                            JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }

        }
    }//GEN-LAST:event_addSpaceportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSpaceport;
    private javax.swing.JLabel galaxy;
    private javax.swing.JLabel galaxyRequired;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nameGalaxy;
    private javax.swing.JTextField namePlanet;
    private javax.swing.JLabel nameRequired;
    private javax.swing.JLabel planet;
    private javax.swing.JLabel planetRequired;
    private javax.swing.JLabel spaceportName1;
    // End of variables declaration//GEN-END:variables
}
