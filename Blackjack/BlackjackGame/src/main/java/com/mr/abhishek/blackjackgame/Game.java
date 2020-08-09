/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mr.abhishek.blackjackgame;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author mr.abhishek
 */

public abstract class Game
{
    private final String gameName;//the title of the game
    private ArrayList <Player> players;// the players of the game

    public Game(String givenName)
    {
        gameName = givenName;
        players = new ArrayList();
    }
    /**
     * @return the gameName
     */
    public String getGameName()
    {
        return gameName;
    }

    /**
     * @return the players of this game
     */
    public ArrayList <Player> getPlayers()
    {
        return players;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(ArrayList <Player> players)
    {
        this.players = players;
    }

    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     */

    public abstract void Win();
    
    /**
     * The methods to be implemented in Blackjack Class
     *
     */
    
    public abstract boolean hasBlackJack(int handValue);
    public abstract int calcHandValue(List<ACard> hand);
    public abstract int Bet(int cash);
    public abstract void Lose();
    public abstract void Push();
    public abstract void Hit(GroupOfCards deck, List<ACard> hand);
    public abstract boolean isHitorStand(String hitter);
    public abstract boolean checkBust(int handvalue);
    public abstract boolean isyesorno(String answer);
    public abstract void fivecardtrick();

}

