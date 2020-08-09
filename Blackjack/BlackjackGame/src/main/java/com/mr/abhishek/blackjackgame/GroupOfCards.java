/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mr.abhishek.blackjackgame;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author mr.abhishek
 */
public class GroupOfCards {
    private ArrayList<ACard> deck;//represents a deck of cards
    GroupOfCards()
    {
        deck = new ArrayList<ACard>();
        for(int i=0; i<4; i++)
        {
            for(int j=1; j<=13; j++)
            {
                deck.add(new ACard(i,j));
            }
        }
    }
    /*
     * Shuffles the deck by changing the indexes of 200 random pairs of cards in the deck.
     */
    public void shuffle()
    {
        Collections.shuffle(deck);
    }
    /*
     * Draws a card from the deck.
     */
    public ACard drawCard()
    {
        return deck.remove(0);
    }
}



