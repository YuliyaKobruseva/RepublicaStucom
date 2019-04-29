/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.TreeMap;

/**
 *
 * @author yuli
 */
public class Spaceport {

    private String name;
    private String planet;
    private String galaxy;
    private TreeMap<Integer, Runway> runways;
    
    public Spaceport(){}

    public Spaceport(String name, String planet, String galaxy) {
        this.name = name;
        this.planet = planet;
        this.galaxy = galaxy;
    }
    

    /**
     * Get the value of runways
     *
     * @return the value of runways
     */
    public TreeMap getRunways() {
        return runways;
    }

    /**
     * Set the value of runways
     *
     * @param runways new value of runways
     */
    public void setRunways(TreeMap runways) {
        this.runways = runways;
    }

    /**
     * Get the value of galaxy
     *
     * @return the value of galaxy
     */
    public String getGalaxy() {
        return galaxy;
    }

    /**
     * Set the value of galaxy
     *
     * @param galaxy new value of galaxy
     */
    public void setGalaxy(String galaxy) {
        this.galaxy = galaxy;
    }

    /**
     * Get the value of planet
     *
     * @return the value of planet
     */
    public String getPlanet() {
        return planet;
    }

    /**
     * Set the value of planet
     *
     * @param planet new value of planet
     */
    public void setPlanet(String planet) {
        this.planet = planet;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

}
