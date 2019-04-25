/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exceptions.ExceptionsDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Runway;
import models.Spaceport;
import models.Spaceship;

/**
 *
 * @author yuli
 */
public class Dao {

    Connection conexion;

    /**
     *
     * @throws SQLException
     */
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/starstucom";
        String user = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, user, pass);
    }

    /**
     *
     * @throws SQLException
     */
    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    private boolean existSpaseport(Spaceport spaceport) throws SQLException {
        //conectar
        String select = "select * from spaceport where name='" + spaceport.getName() + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
        //desconectar
    }

    private boolean existRunway(Runway runway) throws SQLException {
        String select = "select * from runway where number='" + runway.getNumber() + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
    }

    private boolean existSpaceship(Spaceship spaceship) throws SQLException {
        String select = "select * from spaceship where name='" + spaceship.getName() + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        boolean existe = false;
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
    }

    public void insertSpaceport(Spaceport spaceport) throws SQLException, ExceptionsDao {
        //comprobar antes de insertar
        if (existSpaseport(spaceport)) {
            throw new ExceptionsDao(ExceptionsDao.SPACEPORT_EXIST);
        }
        String insert = "insert into spaceport values (?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, spaceport.getName());
        ps.setString(2, spaceport.getPlanet());
        ps.setString(3, spaceport.getGalaxy());
        ps.executeUpdate();
        ps.close();
    }

    public void insertRunway(Runway runway) throws SQLException, ExceptionsDao {
        //comprobar antes de insertar
        if (existRunway(runway)) {
            throw new ExceptionsDao(ExceptionsDao.SPACEPORT_EXIST);
        }
        String insert = "insert into runway values (?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, runway.getSpaceship().getName());
        ps.setInt(2, runway.getNumber());
        ps.setString(3, runway.getStatus().toString());
        ps.setInt(4, runway.getLandingsNumber());
        ps.setString(5, runway.getSpaceship().getName());
        ps.executeUpdate();
        ps.close();
    }
    
    public void insertSpaceship(Spaceship spaceship) throws SQLException, ExceptionsDao {
        //comprobar antes de insertar
        if (existSpaceship(spaceship)) {
            throw new ExceptionsDao(ExceptionsDao.SPACESHIP_EXIST);
        }
        String insert = "insert into runway values (?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, spaceship.getName());
        ps.setInt(2, spaceship.getCapacity());
        ps.setString(3, spaceship.getStatus().toString());
        ps.setInt(4, spaceship.getFlightNumbers());        
        ps.executeUpdate();
        ps.close();
    }
    
     public void deleteSpaceship(Spaceship spaceship) throws SQLException, ExceptionsDao {
        if (!existSpaceship(spaceship)) {
            throw new ExceptionsDao(ExceptionsDao.SPACESHIP_NOT_EXIST);
        }
        String delete = "delete from spaceship where name='" + spaceship.getName() + "'";
        Statement st = conexion.createStatement();
        st.executeUpdate(delete);
        st.close();
    }

}
