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

    public void insertSpaceship(Spaceship spaceship, String spaceport, String runway) throws SQLException, ExceptionsDatabase {
        connection();
        String insert = "insert into spaceship values (?, ?, ?, ?);";        
        PreparedStatement ps = conexion.prepareStatement(insert);        
        String updateRunway = "update runway set spaceship='"+spaceship.getName()+"'"+", status='BUSY', numlandings=1 where spaceport='" + spaceport + "' and number = "+runway+"'";
        try {
            conexion.setAutoCommit(false);
            ps.setString(1, spaceship.getName());
            ps.setInt(2, spaceship.getCapacity());
            ps.setString(3, spaceship.getStatus().toString());
            ps.setInt(4, spaceship.getFlightNumbers());
            ps.executeUpdate();
            //add insert al runway
            conexion.commit();
        }catch (SQLException ex) {
            conexion.rollback();
            throw new ExceptionsDatabase(ExceptionsDatabase.MULTIPLE_INSERT_UPDATE_FAILED);
        } finally {
            ps.close();
            conexion.setAutoCommit(true);
        }        
        disconnect();
        
    }

    public void deleteSpaceship(Spaceship spaceship) throws SQLException {
        connection();
        String delete = "delete from spaceship where name='" + spaceship.getName() + "'";
        Statement st = conexion.createStatement();
        st.executeUpdate(delete);
        st.close();
        disconnect();
    }

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
     * @param name
     * @return
     * @throws SQLException
     * @throws ExceptionsDao
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
}
