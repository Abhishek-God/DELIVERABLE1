/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mr.abhishek.blackjackgame;

/**
 *
 * @author mr.abhishek
 */
public abstract class Player {
    private String playerID; //the unique ID for this player

    /**
     * A constructor that allows you to set the player's unique ID
     *
     */
    public Player(String name)
    {
        playerID= name;
    }

    /**
     * @return the playerID
     */
    public String getPlayerID()
    {
        return playerID;
    }

    /**
     * Ensure that the playerID is unique
     * @param givenID the playerID to set
     */
    public void setPlayerID(String givenID)
    {
        playerID = givenID;
    }
}

