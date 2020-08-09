/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mr.abhishek.blackjackgame;

/**
 *
 * @author mr.abhishek
 */public class ACard extends Card{
    private int rank;//represents the rank of a card
    private int suit;//represents the suit of a card
    private int value;//represents the value of a card
    /*
     * Created with an integer that represents a spot in the String array ranks and the String array suits. This represents
     * the rank and suit of an individual card.
     */
    ACard(int suit, int values)
    {
        this.rank=values;
        this.suit=suit;
    }
    /*
     * Returns the string version of a card.
     */
    public String toString()
    {
        return Values.values()[rank] + " of " + Suit.values()[suit];
    }

    /*
     * Returns the value of a card. If a jack, queen, or king the value is ten. Aces are 11 for now.
     */
    public int getValue()
    {
        if(rank>10)
        {
            value=10;
        }
        else if(rank==1)
        {
            value=11;
        }
        else
        {
            value=rank;
        }
        return value;
    }
}

