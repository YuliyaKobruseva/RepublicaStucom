/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingTools;

import dao.Dao;
import exceptions.ExceptionsDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Runway;
import models.Spaceport;
import models.Spaceship;
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
     * @param comboBox comboBox Object
     * @param typeComboBox type of comboBox
     * @throws java.sql.SQLException
     * @throws exceptions.ExceptionsDao
     */
    public void generateSelect(JComboBox<String> comboBox, String typeComboBox) throws SQLException, ExceptionsDao{
        switch (typeComboBox) {
            case "runway":
                List<Runway> runways = Dao.getDao().selectAllRunway();
                comboBox.removeAllItems();
                comboBox.addItem("Choose a runway");
                runways.forEach((runway) -> {
                    comboBox.addItem(ToolsApp.convertNumberToString(runway.getNumber()));
                });
                break;
            case "spaceport":
                List<Spaceport> spaceports = Dao.getDao().selectAllSpaceport();
                comboBox.removeAllItems();
                comboBox.addItem("Choose a spaceport");
                spaceports.forEach((spaceport) -> {
                    comboBox.addItem(spaceport.getName());
                });
                break;
            case "spaceship":
                List<Spaceship> spaceships = Dao.getDao().selectAllSpaceship();
                comboBox.removeAllItems();
                comboBox.addItem("Choose a spaceship");
                spaceships.forEach((spaceship) -> {
                    comboBox.addItem(spaceship.getName());
                });
                break;
            case "broken":
                List<Spaceship> spaceshipsBroken = Dao.getDao().selectSpaceshipByStatus("BROKEN");
                comboBox.removeAllItems();
                comboBox.addItem("Choose a spaceship");
                spaceshipsBroken.forEach((spaceship) -> {
                    comboBox.addItem(spaceship.getName());
                });
                break;
            case "deployment":
                List<Spaceship> spaceshipsdeployment = Dao.getDao().selectSpaceshipByStatus("LANDED");
                comboBox.removeAllItems();
                comboBox.addItem("Choose a spaceship");
                spaceshipsdeployment.forEach((spaceship) -> {
                    comboBox.addItem(spaceship.getName());
                });
                break;
            case "landing":
                List<Spaceship> spaceshipsLandings = Dao.getDao().selectSpaceshipByStatus("FLYING");
                comboBox.removeAllItems();
                comboBox.addItem("Choose a spaceship");
                spaceshipsLandings.forEach((spaceship) -> {
                    comboBox.addItem(spaceship.getName());
                });
                break;
            case "maintenance":
                List<Spaceship> spaceshipsMaintenance = Dao.getDao().selectSpaceshipByStatus("BROKEN");
                comboBox.removeAllItems();
                comboBox.addItem("Choose a spaceship");
                spaceshipsMaintenance.forEach((spaceship) -> {
                    comboBox.addItem(spaceship.getName());
                });
                break;
            case "galaxy":
                List<String> galaxies = Dao.getDao().getListGalaxies();
                comboBox.removeAllItems();
                comboBox.addItem("Choose a galaxy");
                galaxies.forEach((galaxy) -> {
                    comboBox.addItem(galaxy);
                });
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
    public void generateDynamicSelect(JComboBox<String> comboBox, String nameSpaceport, String status) throws SQLException, ExceptionsDao {
        List<Runway> runways = Dao.getDao().selectRunwaysBySpaceport(nameSpaceport);
        comboBox.removeAllItems();
        comboBox.addItem("Choose a runway");
        runways.forEach((runway) -> {
            if (runway.getStatus().toString().equalsIgnoreCase(status)) {
                comboBox.addItem(ToolsApp.convertNumberToString(runway.getNumber()));
            }
        });
    }

    /**
     * Method to generate table by Galaxy
     *
     * @param newTable table Object
     * @param galaxy name of galaxy
     * @throws java.sql.SQLException
     * @throws exceptions.InputException
     */
    public void createTableByGalaxy(JTable newTable, String galaxy) throws SQLException {
        List<String> columns = new ArrayList<>();
        columns.add("Planet");
        columns.add("Spaceport");
        columns.add("Runway");
        columns.add("Status");
        columns.add("Flight number");
        columns.add("Spaceship");
        DefaultTableModel model = new DefaultTableModel(null, columns.toArray());
        List<Spaceport> spaceports = Dao.getDao().selectSpaceportByGalaxy(galaxy);
        for (Spaceport spaceport : spaceports) {
            TreeMap<Integer, Runway> runways = spaceport.getRunways();
            String planet = spaceport.getPlanet();
            String nameSpaceport = spaceport.getName();
            String runwayNumber = "";
            String status = "";
            String flightNumber = "";
            String spaceship = "";
            if (runways.lastEntry() != null) {
                for (Runway runway : runways.values()) {
                    runwayNumber = ToolsApp.convertNumberToString(runway.getNumber());
                    status = runway.getStatus().toString();
                    flightNumber = ToolsApp.convertNumberToString(runway.getLandingsNumber());
                    if (runway.getSpaceship() == null) {
                        spaceship = "";
                    } else {
                        spaceship = runway.getSpaceship().getName();
                    }
                    model.addRow(new Object[]{planet, nameSpaceport, runwayNumber, status, flightNumber, spaceship});
                }
            } else {
                model.addRow(new Object[]{planet, nameSpaceport, runwayNumber, status, flightNumber, spaceship});
            }
        }
        newTable.setModel(model);
        newTable.setEnabled(false);
    }

    /**
     * Method to generate table by Spaceship
     *
     * @param newTable table object
     * @throws SQLException
     * @throws ExceptionsDao
     * @throws exceptions.InputException
     */
    public void createTableSpaceships(JTable newTable) throws SQLException, ExceptionsDao {
        List<String> columns = new ArrayList<>();
        columns.add("Spaceship");
        columns.add("Capacity");
        columns.add("Status");
        columns.add("Flight number");
        columns.add("Runway");
        columns.add("Spaceport");
        columns.add("Planet");
        columns.add("Galaxy");
        DefaultTableModel model = new DefaultTableModel(null, columns.toArray());
        List<Spaceship> spaceships = Dao.getDao().selectAllSpaceship();
        for (Spaceship spaceship : spaceships) {
            String nameSpaceship = spaceship.getName();
            String status = spaceship.getStatus().toString();
            String flightNumber = ToolsApp.convertNumberToString(spaceship.getFlightNumbers());
            String capacity = ToolsApp.convertNumberToString(spaceship.getCapacity());
            Runway runwaySpaceship = Dao.getDao().selectRunwayBySpaceship(spaceship.getName());
            String runwayNumber = ToolsApp.convertNumberToString(runwaySpaceship.getNumber());
            if (runwayNumber.equalsIgnoreCase("0")) {
                runwayNumber = "";
            }
            Spaceport spaceportOfSpaceship = Dao.getDao().getSpaceportByRunway(runwayNumber);
            String spaceport = spaceportOfSpaceship.getName();
            String planet = spaceportOfSpaceship.getPlanet();
            String galaxy = spaceportOfSpaceship.getGalaxy();
            model.addRow(new Object[]{nameSpaceship, capacity, status, flightNumber, runwayNumber, spaceport, planet, galaxy});
        }
        newTable.setModel(model);
        newTable.setEnabled(false);
    }

    /**
     * Method to generate table by Spaceports
     *
     * @param newTable table Object
     * @throws SQLException
     * @throws ExceptionsDao
     * @throws exceptions.InputException
     */
    public void createTableSpaceportRunwayavailable(JTable newTable) throws SQLException, ExceptionsDao {
        List<String> columns = new ArrayList<>();
        columns.add("Spaceport");
        columns.add("Planet");
        columns.add("Galaxy");
        columns.add("Number of runway available");
        DefaultTableModel model = new DefaultTableModel(null, columns.toArray());
        List<Spaceport> spaceports = Dao.getDao().selectAllSpaceport();
        for (Spaceport spaceport : spaceports) {
            String name = spaceport.getName();
            String planet = spaceport.getPlanet();
            String galaxy = spaceport.getGalaxy();
            String numberRunway = "Runways in construction";
            if (Dao.getDao().selectRunwaysBySpaceportStatus(name, "FREE").isEmpty()) {
                if (!Dao.getDao().selectRunwaysBySpaceportStatus(name, "BUSY").isEmpty()) {
                    numberRunway = "FULL";
                }
            } else {
                numberRunway = ToolsApp.convertNumberToString(Dao.getDao().selectRunwaysBySpaceportStatus(name, "FREE").size());
            }

            model.addRow(new Object[]{name, planet, galaxy, numberRunway});
        }
        newTable.setModel(model);
        newTable.setEnabled(false);
    }
}
