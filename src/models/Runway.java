/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import enums.RunwaysStatus;

/**
 *
 * @author yuli
 */
public class Runway {

    private int number;
    private RunwaysStatus status;
    private int landingsNumber;
    private Spaceship spaceship;
    
    public Runway(){}

    public Runway(int number) {
        this.number = number;
        this.status = RunwaysStatus.FREE;
        this.landingsNumber = 0;        
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public RunwaysStatus getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(RunwaysStatus status) {
        this.status = status;
    }

    /**
     * Get the value of spaceship
     *
     * @return the value of spaceship
     */
    public Spaceship getSpaceship() {
        return spaceship;
    }

    /**
     * Set the value of spaceship
     *
     * @param spaceship new value of spaceship
     */
    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    /**
     * Get the value of landingsNumber
     *
     * @return the value of landingsNumber
     */
    public int getLandingsNumber() {
        return landingsNumber;
    }

    /**
     * Set the value of landingsNumber
     *
     * @param landingsNumber new value of landingsNumber
     */
    public void setLandingsNumber(int landingsNumber) {
        this.landingsNumber = landingsNumber;
    }

    /**
     * Get the value of number
     *
     * @return the value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set the value of number
     *
     * @param number new value of number
     */
    public void setNumber(int number) {
        this.number = number;
    }

}
