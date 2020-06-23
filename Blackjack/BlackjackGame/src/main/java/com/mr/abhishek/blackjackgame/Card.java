/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mr.abhishek.blackjackgame;

/**
 * @author mr.abhishek
 * @modifier mr.abhishek
 * Code adapted from stackOverflow example
 * (Source: https://codereview.stackexchange.com/questions/92586/first-attempt-at-a-java-blackjack-game)
 */
public class Card {
    /*
     * Creates a playing card.
     */
    
    private int rank;       //represents the rank of a card
    private int suit;       //represents the suit of a card
    private int value;      //represents the value of a card
    private static String[] myCards = {"Joker", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private static String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
    /*
     * Created with an integer that represents a spot in the String array ranks and the String array suits. This represents
     * the rank and suit of an individual card.
     */

    public Card(int suit, int values)
    {
        this.rank=values;
        this.suit=suit;
    }

    /*
     * Returns the string version of a card.
     */
    
    public String toString() {
        return myCards[rank] + " of " + suits[suit];
    }

    /*
     * Returns the rank of a card.
     */
    public int getRank() {
        return this.rank;
    }

    /*
     * Returns the suit of a card.
     */
    public int getSuit() {
        return this.suit;
    }

    /*
     * Returns the value of a card. If a jack, queen, or king the value is ten. Aces are 11 for now.
     */
    public int getValue() {
        if (rank > 10) {        // 10 J Q K = 10POINTS
            this.value = 10;
        } else if (rank == 1) {     // Ace = 11POINTS
            this.value = 11;
        } else {
            this.value = rank;          // 2, 9, 3 CARRY THEIR OWN VALUE
        }
        return this.value;
    }

    /*
     * Sets the value of a card.
     */
    public void setValue(int set) {
        this.value = set;
    }
}
