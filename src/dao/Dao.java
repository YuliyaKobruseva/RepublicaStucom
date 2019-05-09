/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Exceptions.ExceptionsDatabase;
import exceptions.InputException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Runway;
import models.Spaceport;
import models.Spaceship;
import tools.ToolsApp;

/**
 *
 * @author yuli
 */
public class Dao {

    Connection conexion;
    private static Dao dao;

    /**
     * Method that allows access to Dao
     *
     * @return singleton
     */
    public static synchronized Dao getDao() {
        if (dao == null) {
            dao = new Dao();
        }
        return dao;
    }

    /**
     * Connection with database
     *
     * @throws SQLException
     */
    public void connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/starstucom";
        String user = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, user, pass);
    }

    /**
     * Disconnect from database
     *
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    /**
     * Select one spaceport by name from database
     *
     * @param spaceport object spaceport
     * @return true if spaceport is in bbdd and else if is not
     * @throws SQLException
     */
    public boolean existSpaseport(Spaceport spaceport) throws SQLException {
        connection();
        String select = "select * from spaceport where name='" + spaceport.getName() + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        disconnect();
        return existe;
    }

    /**
     * Select one runway by name from database
     *
     * @param runway object runway
     * @return true if runway is in bbdd and else if is not
     * @throws SQLException
     */
    public boolean existRunway(Runway runway, String spaceport) throws SQLException {
        connection();
        String select = "select * from runway where number='" + runway.getNumber() + "' and spaceport = '"+spaceport+"'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        disconnect();
        return existe;
    }

    /**
     * Select one spaceship by name from database
     *
     * @param spaceship object spaceship
     * @return true if spaceship is in bbdd and else if is not
     * @throws SQLException
     */
    public boolean existSpaceship(Spaceship spaceship) throws SQLException {
        connection();
        String select = "select * from spaceship where name='" + spaceship.getName() + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        disconnect();
        return existe;
    }

    /**
     * Insert in database a new spaceport
     *
     * @param spaceport object spaceport
     * @throws SQLException
     */
    public void insertSpaceport(Spaceport spaceport) throws SQLException {
        connection();
        String insert = "insert into spaceport values (?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, spaceport.getName());
        ps.setString(2, spaceport.getPlanet());
        ps.setString(3, spaceport.getGalaxy());
        ps.executeUpdate();
        ps.close();
        disconnect();
    }

    /**
     * Insert in database a new runway
     *
     * @param runway object runway
     * @param nameSpaceport name of spaceport what will it belong it
     * @throws SQLException
     */
    public void insertRunway(Runway runway, String nameSpaceport) throws SQLException {
        connection();
        String insert = "insert into runway values (?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, nameSpaceport);
        ps.setInt(2, runway.getNumber());
        ps.setString(3, runway.getStatus().toString());
        ps.setInt(4, runway.getLandingsNumber());
        ps.setString(5, null);
        ps.executeUpdate();
        ps.close();
        disconnect();
    }

    /**
     * Insert in database a new spaceship
     *
     * @param spaceship object spaceship
     * @param spaceport name of spaceport when runway is
     * @param runway number of runway when is landed
     * @throws SQLException
     * @throws ExceptionsDatabase when it has not been possible to insert in any
     * of the two tables
     */
    public void insertSpaceship(Spaceship spaceship, String spaceport, String runway) throws SQLException, ExceptionsDatabase {
        connection();
        String insert = "insert into spaceship values (?, ?, ?, ?);";
        String updateRunway = "update runway set spaceship='" + spaceship.getName() + "', status='BUSY', numlandings=numlandings+1 where spaceport='" + spaceport
                + "' and number = " + runway;
        PreparedStatement ps = conexion.prepareStatement(insert);
        Statement st = conexion.createStatement();
        try {
            conexion.setAutoCommit(false);
            ps.setString(1, spaceship.getName());
            ps.setInt(2, spaceship.getCapacity());
            ps.setString(3, spaceship.getStatus().toString());
            ps.setInt(4, spaceship.getFlightNumbers());
            ps.executeUpdate();
            st.executeUpdate(updateRunway);
            conexion.commit();
        } catch (SQLException ex) {
            conexion.rollback();
            throw new ExceptionsDatabase(ExceptionsDatabase.MULTIPLE_ACCTION_FAILED);
        } finally {
            ps.close();
            st.close();
            conexion.setAutoCommit(true);
        }
        disconnect();
    }

    /**
     * Delete from database a spaceship
     *
     * @param spaceship object spaceship
     * @throws SQLException
     * @throws Exceptions.ExceptionsDatabase when it has not been possible to
     * insert in any of the two tables
     */
    public void deleteSpaceship(Spaceship spaceship) throws SQLException, ExceptionsDatabase {
        connection();
        String updateRunway = "update runway set status='FREE' where spaceship='" + spaceship.getName() + "'";
        String delete = "delete from spaceship where name='" + spaceship.getName() + "'";
        Statement st = conexion.createStatement();
        try {
            conexion.setAutoCommit(false);
            st.executeUpdate(updateRunway);
            st.executeUpdate(delete);
            conexion.commit();
        } catch (SQLException ex) {
            conexion.rollback();
            throw new ExceptionsDatabase(ExceptionsDatabase.MULTIPLE_ACCTION_FAILED);
        } finally {
            st.close();
            conexion.setAutoCommit(true);
        }
        disconnect();
    }

    /**
     * Select all spaceports
     *
     * @return @throws SQLException
     */
    public List<Spaceport> selectAllSpaceport() throws SQLException {
        connection();
        String query = "select * from spaceport";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Spaceport> spaceports = new ArrayList<>();
        while (rs.next()) {
            Spaceport spaceport = new Spaceport();
            spaceport.setName(rs.getString("name"));
            spaceport.setPlanet(rs.getString("planet"));
            spaceport.setGalaxy(rs.getString("galaxy"));
            spaceports.add(spaceport);
        }
        rs.close();
        st.close();
        disconnect();
        return spaceports;
    }

    /**
     * Select all runways
     *
     * @return @throws SQLException
     * @throws exceptions.InputException
     */
    public List<Runway> selectAllRunway() throws SQLException {
        connection();
        String query = "select * from runway";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Runway> runways = new ArrayList<>();
        while (rs.next()) {
            Runway runway = new Runway();
            runway.setNumber(rs.getInt("number"));
            runway.setStatus(ToolsApp.converStringToRunwaysStatus(rs.getString("status")));
            runway.setLandingsNumber(rs.getInt("numlandings"));
            runway.setSpaceship(getSpaceshipByName(rs.getString("spaceship")));
            runways.add(runway);
        }
        rs.close();
        st.close();
        disconnect();
        return runways;
    }

    /**
     * Select all spaceships
     *
     * @return
     * @throws SQLException
     */
    public List<Spaceship> selectAllSpaceship() throws SQLException {
        connection();
        String query = "select * from spaceship";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Spaceship> spacehips = new ArrayList<>();
        while (rs.next()) {
            Spaceship spaceship = new Spaceship();
            spaceship.setName(rs.getString("name"));
            spaceship.setStatus(ToolsApp.converStringToSpaceshipsStatus(rs.getString("status")));
            spaceship.setCapacity(rs.getInt("capacity"));
            spaceship.setFlightNumbers(rs.getInt("numflights"));
            spacehips.add(spaceship);
        }
        rs.close();
        st.close();
        disconnect();
        return spacehips;
    }

    /**
     * Select runways for a certain type
     *
     * @param status type of status
     * @return list with runways
     * @throws SQLException
     */
    public List<Runway> selectRunwaysByStatus(String status) throws SQLException {
        connection();
        String query = "select * from runway where status ='" + status + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Runway> runways = new ArrayList<>();
        while (rs.next()) {
            Runway runway = new Runway();
            runway.setNumber(rs.getInt("number"));
            runway.setStatus(ToolsApp.converStringToRunwaysStatus(rs.getString("status")));
            runway.setLandingsNumber(rs.getInt("numlandings"));
            if (rs.getString("spaceship") == null) {
                runway.setSpaceship(null);
            } else {
                runway.setSpaceship(getSpaceshipByName(rs.getString("spaceship")));
            }
            runways.add(runway);
        }
        rs.close();
        st.close();
        disconnect();
        return runways;
    }

    /**
     * Select runways what belong to certain airport
     *
     * @param spaceport name of spaceport
     * @return list of runways
     * @throws SQLException
     */
    public List<Runway> selectRunwaysBySpaceport(String spaceport) throws SQLException {
        connection();
        String query = "select * from runway where spaceport ='" + spaceport + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Runway> runways = new ArrayList<>();
        while (rs.next()) {
            Runway runway = new Runway();
            runway.setNumber(rs.getInt("number"));
            runway.setStatus(ToolsApp.converStringToRunwaysStatus(rs.getString("status")));
            runway.setLandingsNumber(rs.getInt("numlandings"));
            if (rs.getString("spaceship") == null) {
                runway.setSpaceship(null);
            } else {
                runway.setSpaceship(getSpaceshipByName(rs.getString("spaceship")));
            }
            runways.add(runway);
        }
        rs.close();
        st.close();
        disconnect();
        return runways;
    }

    /**
     * Select from database a one spaceship by name
     *
     * @param name name of spaceship
     * @return object spaceship
     * @throws SQLException
     */
    public Spaceship getSpaceshipByName(String name) throws SQLException {
        connection();
        Spaceship spaceship = new Spaceship(name);
        String select = "select * from spaceship where name='" + name + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        spaceship = new Spaceship();
        if (rs.next()) {
            spaceship.setName(rs.getString("name"));
            spaceship.setCapacity(rs.getInt("capacity"));
            spaceship.setStatus(ToolsApp.converStringToSpaceshipsStatus(rs.getString("status")));
            spaceship.setFlightNumbers(rs.getInt("numflights"));
        }
        rs.close();
        st.close();
        disconnect();
        return spaceship;
    }

    /**
     * Select from database a spaceport which belong certain runway
     *
     * @param number number of runway
     * @return object spaceport
     * @throws SQLException
     */
    public Spaceport getSpaceportByRunway(String number) throws SQLException {
        connection();
        Spaceport spaceport = new Spaceport();
        String select = "SELECT spaceport, planet, galaxy FROM runway left join spaceport on spaceport=name where number ='" + number + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        spaceport = new Spaceport();
        if (rs.next()) {
            spaceport.setName(rs.getString("spaceport"));
            spaceport.setPlanet(rs.getString("planet"));
            spaceport.setGalaxy(rs.getString("galaxy"));
        }
        rs.close();
        st.close();
        disconnect();
        return spaceport;
    }

    /**
     * Select from database list of runway by certain spaceport and status
     *
     * @param nameSpaceport name of spaceport
     * @param status status type
     * @return list of runway
     * @throws SQLException
     */
    public List<Runway> selectRunwaysBySpaceportStatus(String nameSpaceport, String status) throws SQLException {
        connection();
        String query = "select * from runway where spaceport ='" + nameSpaceport + "' and status='" + status + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Runway> runways = new ArrayList<>();
        while (rs.next()) {
            Runway runway = new Runway();
            runway.setNumber(rs.getInt("number"));
            runway.setStatus(ToolsApp.converStringToRunwaysStatus(rs.getString("status")));
            runway.setLandingsNumber(rs.getInt("numlandings"));
            if (rs.getString("spaceship") == null) {
                runway.setSpaceship(null);
            } else {
                runway.setSpaceship(getSpaceshipByName(rs.getString("spaceship")));
            }
            runways.add(runway);
        }
        rs.close();
        st.close();
        disconnect();
        return runways;
    }

    /**
     * Select from database one runway where certain spaceship is landed
     *
     * @param nameSpaceship name of spaceship
     * @return object runway
     * @throws SQLException
     */
    public Runway selectRunwayBySpaceship(String nameSpaceship) throws SQLException {
        connection();
        String query = "select * from runway where spaceship ='" + nameSpaceship + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        Runway runway = new Runway();
        while (rs.next()) {
            runway.setNumber(rs.getInt("number"));
            runway.setStatus(ToolsApp.converStringToRunwaysStatus(rs.getString("status")));
            runway.setLandingsNumber(rs.getInt("numlandings"));
            if (rs.getString("spaceship") == null) {
                runway.setSpaceship(null);
            } else {
                runway.setSpaceship(getSpaceshipByName(rs.getString("spaceship")));
            }
        }
        rs.close();
        st.close();
        disconnect();
        return runway;
    }

    /**
     * Update spaceship and runway in case spaceship deployment
     *
     * @param spaceship object spaceship
     * @param runway object runway
     * @throws SQLException
     * @throws ExceptionsDatabase when it has not been possible to insert in any
     * of the two tables
     */
    public void updateSpaceshipRunwayDeployment(String spaceship, Runway runway) throws SQLException, ExceptionsDatabase {
        connection();
        Statement st = conexion.createStatement();
        try {
            conexion.setAutoCommit(false);
            String updateSpaceship = "update spaceship set status='FLYING', numflights = numflights+1 where name='" + spaceship + "'";
            String updateRunway = "";
            if (runway.getLandingsNumber() % 5 == 0) {
                updateRunway = "update runway set status='CLEANING', spaceship=null where number='" + runway.getNumber() + "'";
            } else {
                updateRunway = "update runway set status='FREE', spaceship=null where number='" + runway.getNumber() + "'";
            }
            st.executeUpdate(updateSpaceship);
            st.executeUpdate(updateRunway);
            conexion.commit();
        } catch (SQLException ex) {
            conexion.rollback();
            throw new ExceptionsDatabase(ExceptionsDatabase.MULTIPLE_ACCTION_FAILED);
        } finally {
            st.close();
            conexion.setAutoCommit(true);
        }
        disconnect();
    }

    /**
     * Update spaceship and runway in case spaceship landing
     *
     * @param spaceship object spaceship
     * @param runway object runway
     * @param spaceport
     * @throws SQLException
     * @throws ExceptionsDatabase when it has not been possible to insert in any
     * of the two tables
     */
    public void updateSpaceshipRunwayLanding(Spaceship spaceship, String runway, String spaceport) throws SQLException, ExceptionsDatabase {
        connection();
        Statement st = conexion.createStatement();
        try {
            conexion.setAutoCommit(false);
            String updateSpaceship = "";
            if (spaceship.getFlightNumbers() % 15 == 0) {
                updateSpaceship = "update spaceship set status='BROKEN' where name='" + spaceship.getName() + "'";
            } else {
                updateSpaceship = "update spaceship set status='LANDED' where name='" + spaceship.getName() + "'";
            }
            String updateRunway = "update runway set spaceship='" + spaceship.getName() + "', status='BUSY', numlandings=numlandings+1 where spaceport='" + spaceport
                    + "' and number = " + runway;
            st.executeUpdate(updateSpaceship);
            st.executeUpdate(updateRunway);
            conexion.commit();
        } catch (SQLException ex) {
            conexion.rollback();
            throw new ExceptionsDatabase(ExceptionsDatabase.MULTIPLE_ACCTION_FAILED);
        } finally {
            st.close();
            conexion.setAutoCommit(true);
        }
        disconnect();
    }

    /**
     * Select from database list of spaceship by certain status type
     *
     * @param status status type
     * @return list of spaceship
     * @throws SQLException
     */
    public List<Spaceship> selectSpaceshipByStatus(String status) throws SQLException {
        connection();
        String query = "select * from spaceship where status ='" + status + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Spaceship> spaceships = new ArrayList<>();
        while (rs.next()) {
            Spaceship spaceship = new Spaceship();
            spaceship.setName(rs.getString("name"));
            spaceship.setCapacity(rs.getInt("capacity"));
            spaceship.setStatus(ToolsApp.converStringToSpaceshipsStatus(rs.getString("status")));
            spaceship.setFlightNumbers(rs.getInt("numflights"));
            spaceships.add(spaceship);
        }
        rs.close();
        st.close();
        disconnect();
        return spaceships;
    }

    /**
     * Select from database list of spaceports by certain galaxy
     *
     * @param galaxy name of galaxy
     * @return list of spaceport
     * @throws SQLException
     */
    public List<Spaceport> selectSpaceportByGalaxy(String galaxy) throws SQLException {
        connection();
        String query = "SELECT * FROM spaceport where galaxy ='" + galaxy + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Spaceport> spaceports = new ArrayList<>();
        while (rs.next()) {
            Spaceport spaceport = new Spaceport();
            spaceport.setName(rs.getString("name"));
            spaceport.setPlanet(rs.getString("planet"));
            spaceport.setGalaxy(rs.getString("galaxy"));
            List<Runway> runways = selectRunwaysBySpaceport(rs.getString("name"));
            for (Runway runway : runways) {
                spaceport.addRunway(runway);
            }
            spaceports.add(spaceport);
        }
        rs.close();
        st.close();
        disconnect();
        return spaceports;
    }

    /**
     * Update data of one spaceship in database in case of maintenance
     *
     * @param spaceship object spaceship
     * @throws SQLException
     */
    public void updateSpaceshipMaintenance(Spaceship spaceship) throws SQLException {
        connection();
        Statement st = conexion.createStatement();
        String updateSpaceship = "update spaceship set status='LANDED' where name='" + spaceship.getName() + "'";
        st.executeUpdate(updateSpaceship);
        st.close();
        disconnect();
    }

    /**
     * Update data of one runway in case of cleaning
     *
     * @param spaceport name of spaceport
     * @param runway number of runway
     * @throws SQLException
     */
    public void updateRunwayCleaning(String spaceport, String runway) throws SQLException {
        connection();
        Statement st = conexion.createStatement();
        String updateRunway = "update runway set status='FREE' where number='" + runway + "' and spaceport ='" + spaceport + "'";
        st.executeUpdate(updateRunway);
        st.close();
        disconnect();
    }

    /**
     * Select from database list of available galaxies
     *
     * @return list of galaxies
     * @throws SQLException
     */
    public List<String> getListGalaxies() throws SQLException {
        connection();
        String query = "select distinct galaxy from spaceport";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<String> galaxies = new ArrayList<>();
        while (rs.next()) {
            galaxies.add(rs.getString("galaxy"));
        }
        rs.close();
        st.close();
        disconnect();
        return galaxies;
    }

}
