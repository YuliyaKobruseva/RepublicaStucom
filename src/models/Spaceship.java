/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import enums.SpaceshipsStatus;

/**
 *
 * @author yuli
 */
public class Spaceship {

    private String name;
    private int capacity;
    private SpaceshipsStatus status;
    private int flightNumbers;

    public Spaceship() {}   
    

    public Spaceship(String name) {
        this.name = name;
    }   

    public Spaceship(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.status=SpaceshipsStatus.LANDED;
        this.flightNumbers = 0;        
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public SpaceshipsStatus getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(SpaceshipsStatus status) {
        this.status = status;
    }

    /**
     * Get the value of flightNumbers
     *
     * @return the value of flightNumbers
     */
    public int getFlightNumbers() {
        return flightNumbers;
    }

    /**
     * Set the value of flightNumbers
     *
     * @param flightNumbers new value of flightNumbers
     */
    public void setFlightNumbers(int flightNumbers) {
        this.flightNumbers = flightNumbers;
    }

    /**
     * Get the value of capacity
     *
     * @return the value of capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set the value of capacity
     *
     * @param capacity new value of capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
