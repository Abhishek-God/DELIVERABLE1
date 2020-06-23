/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mr.abhishek.blackjackgame;
import java.util.ArrayList;
import java.util.Random;
/**
 *@author mr.abhishek
 */
public class Deck {
     private ArrayList<Card> deck;//represents a deck of cards

    Deck() {
        deck = new ArrayList<Card>();
        for (int i = 0; i < 4; i++) {       //4 different types
            for (int j = 1; j <= 13; j++) { // 13 different values
                deck.add(new Card(i, j));
            }
        }
    }

    /*
     * Shuffles the deck by changing the indexes of 200 random pairs of cards in the deck.
     */
    public void shuffle() {
        Random random = new Random();
        Card temp;
        int size = deck.size() - 1;
        for (int k = 0; k < 200; k++) {
            int i = random.nextInt(size);
            int j = random.nextInt(size);
            temp = deck.get(j);
            deck.set(j, deck.get(i));
            deck.set(i, temp);
        }
    }

    /*
     * Draws a card from the deck.
     */
    public Card drawCard() {
        return deck.remove(0);
    }
}
