/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingTools;

import dao.Dao;
import exceptions.ExceptionsDao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import models.Runway;
import models.Spaceport;
import tools.ToolsApp;

/**
 * Swing assistant class to create elements
 *
 * @author Yuli
 */
public class SwingTools {

    private static SwingTools swingTools;

    public static SwingTools getSwingTools() {
        if (swingTools == null) {
            swingTools = new SwingTools();
        }
        return swingTools;
    }

    /**
     * Method to generate a comboBox
     *
     * @param comboBox
     * @param typeComboBox type of comboBox
     * @throws java.sql.SQLException
     * @throws exceptions.ExceptionsDao
     */
    public void generateSelect(JComboBox<String> comboBox, String typeComboBox) throws SQLException, ExceptionsDao {
        switch (typeComboBox) {
            case "runway":
                List<Runway> runways = Dao.getDao().selectAllRunway();
                comboBox.addItem("Choose a runway");
                runways.forEach((runway) -> {
                    comboBox.addItem(ToolsApp.convertNumberToString(runway.getNumber()));
        });
                break;
            case "spaceport":
                List<Spaceport> spaceports = Dao.getDao().selectAllSpaceport();
                comboBox.addItem("Choose a spaceport");
                spaceports.forEach((spaceport) -> {
                    comboBox.addItem(spaceport.getName());
        });
                break;
            default:
                break;
        }
    }

    /**
     * Methos to generate a dynamic comboBox
     *
     * @param comboBox
     * @param nameSpaceport
     * @throws java.sql.SQLException
     * @throws exceptions.ExceptionsDao
     */
    public void generateDynamicSelect(JComboBox<String> comboBox, String nameSpaceport) throws SQLException, ExceptionsDao {
        List<Runway> runways = Dao.getDao().selectAllRunway();
        comboBox.removeAllItems();
        comboBox.addItem("Choose a runway");
        runways.forEach((runway) -> {
            comboBox.addItem(ToolsApp.convertNumberToString(runway.getNumber()));
        });
    }

//    /**
//     * Method to set icon of app
//     * @param dialog
//     * @param frame
//     */
//    public void setIcon(JDialog dialog, JFrame frame) {
//        if (dialog == null) {
//            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconLogin.png")));
//        } else {
//            dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconLogin.png")));
//        }
//    }
//
//    /**
//     * Method to generate table
//     * @param newTable
//     * @param typeTable type of table
//     */
//    public void createTable(JTable newTable, String typeTable) throws ManagerException {
//        ArrayList<String> columns = new ArrayList<>();
//        ArrayList<String> rows = new ArrayList<>();
//        if (typeTable.equalsIgnoreCase("level")) {
//            for (TypeDifficultyLevel level : TypeDifficultyLevel.values()) {
//                columns.add(level.toString());
//                rows.add(Manager.getManager().quantityScores(typeTable, level.toString()));
//            }
//        } else if (typeTable.equalsIgnoreCase("instrument")) {
//            for (TypeInstrument instrument : TypeInstrument.values()) {
//                columns.add(instrument.toString());
//                rows.add(Manager.getManager().quantityScores(typeTable, instrument.toString()));
//            }
//        } else if (typeTable.equalsIgnoreCase("genre")) {
//            for (TypeGenre genre : TypeGenre.values()) {
//                columns.add(genre.toString());
//                rows.add(Manager.getManager().quantityScores(typeTable, genre.toString()));
//            }
//        }
//        DefaultTableModel model = new DefaultTableModel(null, columns.toArray());
//        model.addRow(rows.toArray());
//        newTable.setModel(model);
//        newTable.setEnabled(false);
//    }
//
//    /**
//     * Method to show button that was selected
//     * @param buttonSelected
//     * @param buttonGroup
//     */
//    public void setSelectedButtonText(String buttonSelected, ButtonGroup buttonGroup) {
//        for (Enumeration buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
//            AbstractButton button = (AbstractButton) buttons.nextElement();
//            if (button.getText().equalsIgnoreCase(buttonSelected)) {
//                button.setSelected(true);
//            }
//        }
//    }
//
//    /**
//     * Method to get name of the selected button
//     * @param buttonGroup
//     * @return name of button
//     */
//    public String getSelectedButtonText(ButtonGroup buttonGroup) {
//        for (Enumeration buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
//            AbstractButton button = (AbstractButton) buttons.nextElement();
//            if (button.isSelected()) {
//                return button.getText();
//            }
//        }
//        return null;
//    }
}
