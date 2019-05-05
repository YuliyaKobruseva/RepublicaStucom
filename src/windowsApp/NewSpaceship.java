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
import exceptions.InputException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Runway;
import models.Spaceport;
import models.Spaceship;
import tools.ToolsApp;

/**
 *
 * @author dafna
 */
public class NewSpaceship extends javax.swing.JDialog {

    List<Spaceport> spaceportsApp = new ArrayList<>();
    List<Runway> runwaysApp = new ArrayList<>();

    /**
     * Create new form NewSpaceship
     *
     * @param parent
     * @param modal
     */
    public NewSpaceship(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        nameRequired.setVisible(false);
        capacityRequired.setVisible(false);
        try {
            SwingTools.getSwingTools().generateSelect(spaceport, "spaceport");
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

        spaceship = new javax.swing.JLabel();
        nameSpaceship = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nameRequired = new javax.swing.JLabel();
        capacitySpaceship = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        capacityRequired = new javax.swing.JLabel();
        addSpaceship = new javax.swing.JButton();
        capacity = new javax.swing.JFormattedTextField();
        spaceport = new javax.swing.JComboBox<>();
        runways = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        spaceship.setLabelFor(nameSpaceship);
        spaceship.setText("Name of spaceship");

        nameSpaceship.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameSpaceshipFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        nameRequired.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        nameRequired.setForeground(new java.awt.Color(255, 0, 0));
        nameRequired.setText("Name is required");

        capacitySpaceship.setLabelFor(capacity);
        capacitySpaceship.setText("Capacity");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        capacityRequired.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        capacityRequired.setForeground(new java.awt.Color(255, 0, 0));
        capacityRequired.setText("Capacity is required");

        addSpaceship.setText("Add a new spaceship");
        addSpaceship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSpaceshipActionPerformed(evt);
            }
        });

        capacity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));
        capacity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                capacityFocusLost(evt);
            }
        });

        spaceport.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                spaceportItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(addSpaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(capacitySpaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spaceport, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(runways, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameRequired, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(capacityRequired, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nameSpaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5))))))
                        .addGap(0, 143, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameSpaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addComponent(nameRequired)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(capacitySpaceship, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(capacityRequired)
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spaceport, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(runways))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(addSpaceship)
                .addGap(73, 73, 73))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void capacityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_capacityFocusLost
        int newCapacity = 0;
        if (capacity.getText().equalsIgnoreCase("") || capacity.getText().trim().equalsIgnoreCase("")) {
            capacityRequired.setVisible(true);
        } else {
            try {
                newCapacity = ToolsApp.convertStringToNumber(capacity.getText());
            } catch (InputException ex) {
                JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
            }
            if (newCapacity <= 0) {
                capacityRequired.setText("Capacity can not be less than or equal to 0");
                capacityRequired.setVisible(true);
            } else {
                capacityRequired.setVisible(false);
            }
        }

    }//GEN-LAST:event_capacityFocusLost

    private void addSpaceshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSpaceshipActionPerformed
        String name = nameSpaceship.getText();
        String nameSpaceport = spaceport.getSelectedItem().toString();
        String runway = runways.getSelectedItem().toString();
        try {
            int newCapacity = ToolsApp.convertStringToNumber(capacity.getText());
            Spaceship newSpaceship = new Spaceship(name, newCapacity);
            if (Dao.getDao().existSpaceship(newSpaceship)) {
                JOptionPane.showMessageDialog(this, "Spaceship already exist", "Message", JOptionPane.WARNING_MESSAGE);
            } else {
                Dao.getDao().insertSpaceship(newSpaceship, nameSpaceport, runway);
                JOptionPane.showMessageDialog(this, "Spaceship created successful", "Message", JOptionPane.INFORMATION_MESSAGE);
                //comprobar selectbox
            }
        } catch (InputException ex) {
            JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
        } catch (ExceptionsDatabase ex) {
            JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_addSpaceshipActionPerformed

    private void nameSpaceshipFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameSpaceshipFocusLost
        String name = nameSpaceship.getText();
        if (name.equalsIgnoreCase("") || name.trim().equalsIgnoreCase("")) {
            nameRequired.setVisible(true);
        } else {
            nameRequired.setVisible(false);
        }
    }//GEN-LAST:event_nameSpaceshipFocusLost


    private void spaceportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_spaceportItemStateChanged
        String spaceportSelected = spaceport.getSelectedItem().toString();        
        if(spaceportSelected.equalsIgnoreCase("Choose a runway")){
            JOptionPane.showMessageDialog(this, "You have not selected any runway", "Message", JOptionPane.WARNING_MESSAGE);
        }else{
            try {                
                SwingTools.getSwingTools().generateDynamicSelect(runways, spaceportSelected);
            } catch (SQLException | ExceptionsDao ex) {
                JOptionPane.showMessageDialog(this, "" + ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
            }
        }        
    }//GEN-LAST:event_spaceportItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSpaceship;
    private javax.swing.JFormattedTextField capacity;
    private javax.swing.JLabel capacityRequired;
    private javax.swing.JLabel capacitySpaceship;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel nameRequired;
    private javax.swing.JTextField nameSpaceship;
    private javax.swing.JComboBox<String> runways;
    private javax.swing.JComboBox<String> spaceport;
    private javax.swing.JLabel spaceship;
    // End of variables declaration//GEN-END:variables
}
