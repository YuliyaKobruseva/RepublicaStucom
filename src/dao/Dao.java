/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Exceptions.ExceptionsDatabase;
import exceptions.ExceptionsDao;
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
     *
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    /**
     *
     * @param spaceport
     * @return
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
     *
     * @param runway
     * @return
     * @throws SQLException
     */
    public boolean existRunway(Runway runway) throws SQLException {
        connection();
        String select = "select * from runway where number='" + runway.getNumber() + "'";
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
     *
     * @param spaceship
     * @return
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
     *
     * @param spaceport
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
     *
     * @param runway
     * @param nameSpaceport
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
     *
     * @param spaceship
     * @param spaceport
     * @param runway
     * @throws SQLException
     * @throws ExceptionsDatabase
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
     *
     * @param spaceship
     * @throws SQLException
     * @throws Exceptions.ExceptionsDatabase
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
     *
     * @return @throws SQLException
     * @throws ExceptionsDao
     */
    public List<Runway> selectAllRunway() throws SQLException, ExceptionsDao {
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

    public List<Spaceship> selectAllSpaceship() throws SQLException, ExceptionsDao {
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
     *
     * @param status
     * @return
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
     *
     * @param spaceport
     * @return
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
     *
     * @param name
     * @return
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
     *
     * @param nameSpaceport
     * @return
     * @throws SQLException
     */
    public List<Runway> selectFreeRunwaysBySpaceport(String nameSpaceport) throws SQLException {
        connection();
        String query = "select * from runway where spaceport ='" + nameSpaceport + "'";
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
     *
     * @param runway
     * @throws SQLException
     */
    public void updateRunway(Runway runway) throws SQLException {
        connection();
        Statement st = conexion.createStatement();
        String query = "update runway set status='CLEANING', numlandings=0, spaceship=null where number=" + runway.getNumber();
        st.executeUpdate(query);
        st.close();
        disconnect();
    }

    /**
     * Update spaceship and runway in case spaceship deployment
     *
     * @param spaceship
     * @param runway
     * @throws SQLException
     * @throws ExceptionsDatabase
     */
    public void updateSpaceshipRunwayDeployment(Spaceship spaceship, Runway runway) throws SQLException, ExceptionsDatabase {
        connection();
        Statement st = conexion.createStatement();
        try {
            conexion.setAutoCommit(false);
            String updateSpaceship = "update spaceship set status='FLYING', numflights = numflights+1 where name='" + spaceship.getName() + "'";
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
     * Update spaceship and runway in case spaceship deployment
     *
     * @param spaceship
     * @param runway
     * @throws SQLException
     * @throws ExceptionsDatabase
     */
    public void updateSpaceshipRunwayLanding(Spaceship spaceship, Runway runway) throws SQLException, ExceptionsDatabase {
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
//            String updateRunway = "update runway set spaceship='" + spaceship.getName() + "', status='BUSY', numlandings=numlandings+1 where spaceport='" + spaceport
//                + "' and number = " + runway;          
            st.executeUpdate(updateSpaceship);
//            st.executeUpdate(updateRunway);
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
     *
     * @param status
     * @return
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
     *
     * @param galaxy
     * @return
     * @throws SQLException
     */
    public List<Spaceport> selectSpaceportByGalaxy(String galaxy) throws SQLException {
        connection();
        String query = "select * from spaceport where galaxy='" + galaxy + "'";
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

}
