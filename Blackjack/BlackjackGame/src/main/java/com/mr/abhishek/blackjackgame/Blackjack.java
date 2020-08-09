/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mr.abhishek.blackjackgame;

/**
 *
 * @author mr.abhishek
 */import java.util.*;
public class Blackjack extends Game{
     private static int cash;               //cash the user bets with
     private static int bet;                //how much the user wants to bet
     private static int AceCounter;          //how many aces are in the user's hand
     private static ArrayList<ACard> hand;       //represents the user's hand
     private static int handvalue;           //the value of the user's hand
     private static String name;             //name of the user

     public Blackjack(String givenName) {
        super(givenName);
     }

    public static void main(String[] args) {
        Blackjack ref = new Blackjack("name");
        System.out.println("Hi! What is your name?");
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
        System.out.println("Hello, " + name + ", lets play some BlackJack!");
        System.out.println("How much cash do you want to start with?");
        Scanner money = new Scanner(System.in);
        cash = money.nextInt();
        System.out.println("You start with cash: " + cash);
        while (cash > 0) {
            GroupOfCards deck = new GroupOfCards();//initialize deck, dealer, hands, and set the bet.
            deck.shuffle();
            AceCounter = 0;
            Dealer dealer = new Dealer(deck);
            List<ACard> hand = new ArrayList<>();
            hand.add(deck.drawCard());
            hand.add(deck.drawCard());
            System.out.println("How much would you like to bet?");
            bet = ref.Bet(cash);
            System.out.println("Cash:" + (cash - bet));
            System.out.println("Money on the table:" + bet);
            System.out.println("Here is your hand: ");
            System.out.println(hand);
            int handvalue = ref.calcHandValue(hand);
            System.out.println("The dealer is showing: ");
            dealer.showFirstCard();
            if (ref.hasBlackJack(handvalue) && dealer.hasBlackJack())//check if both the user and dealer have blackjack.
            {
                ref.Push();
            } else if (ref.hasBlackJack(handvalue))//check if the user has blackjack.
            {
                System.out.println("You have BlackJack!");
                System.out.println("You win 2x your money back!");
                cash = cash + bet;
                ref.Win();
            } else if (dealer.hasBlackJack())//check if the dealer has blackjack.
            {
                System.out.println("Here is the dealer's hand:");
                dealer.showHand();
                ref.Lose();
            } else {
                if (2 * bet < cash)//check if the user can double down.
                {
                    System.out.println("Would you like to double down?");//allows the user to double down.
                    Scanner doubledown = new Scanner(System.in);
                    String doubled = doubledown.nextLine();
                    while (!ref.isyesorno(doubled)) {
                        System.out.println("Please enter yes or no.");
                        doubled = doubledown.nextLine();
                    }
                    if (doubled.equals("yes")) {
                        System.out.println("You have opted to double down!");
                        bet = 2 * bet;
                        System.out.println("Cash:" + (cash - bet));
                        System.out.println("Money on the table:" + bet);
                    }
                }
                System.out.println("Would you like to hit or stand?");//ask if the user will hit or stand
                Scanner hitorstand = new Scanner(System.in);
                String hitter = hitorstand.nextLine();
                while (!ref.isHitorStand(hitter)) {
                    System.out.println("Please enter 'hit' or 'stand'.");
                    hitter = hitorstand.nextLine();
                }
                while (hitter.equals("hit"))//hits the user as many times as he or she pleases.
                {
                    ref.Hit(deck, hand);
                    System.out.println("Your hand is now:");
                    System.out.println(hand);
                    handvalue = ref.calcHandValue(hand);
                    if (ref.checkBust(handvalue))//checks if the user busted
                    {
                        ref.Lose();
                        break;
                    }
                    if (handvalue <= 21 && hand.size() == 5)//checks for a five card trick.
                    {
                        ref.fivecardtrick();
                        break;
                    }
                    System.out.println("Would you like to hit or stand?");
                    hitter = hitorstand.nextLine();
                }
                if (hitter.equals("stand"))//lets the user stand.
                {
                    int dealerhand = dealer.takeTurn(deck);//takes the turn for the dealer.
                    System.out.println("");
                    System.out.println("Here is the dealer's hand:");
                    dealer.showHand();
                    if (dealerhand > 21)//if the dealer busted, user wins.
                    {
                        ref.Win();
                    } else {
                        int you = 21 - handvalue;//check who is closer to 21 and determine winner
                        int deal = 21 - dealerhand;
                        if (you == deal) {
                            ref.Push();
                        }
                        if (you < deal) {
                            ref.Win();
                        }
                        if (deal < you) {
                            ref.Lose();
                        }
                    }
                }
            }
            System.out.println("Would you like to play again?");//ask if the user wants to keep going
            Scanner yesorno = new Scanner(System.in);
            String answer = yesorno.nextLine();
            while (!ref.isyesorno(answer)) {
                System.out.println("Please answer yes or no.");
                answer = yesorno.nextLine();
            }
            if (answer.equals("no")) {
                break;
            }
        }
        System.out.println("Your cash is: " + cash);//if user doesn't want to play or runs out of cash, either congratulates them on their winnings or lets them know
        if (cash == 0) {
            System.out.println("You ran out of cash!");
        } else {
            System.out.println("Enjoy your winnings, " + name + "!");
        }
    }

    /*
     * Checks if the user has blackjack.
     */

    @Override
    public boolean hasBlackJack(int handValue) {
        if (handValue == 21) {
            return true;
        }
        return false;
    }

    /*
     * Calculates the value of a player's hand.
     */

    @Override
    public int calcHandValue(List<ACard> hand) {
        ACard[] aHand = new ACard[]{};
        aHand = hand.toArray(aHand);
        int handvalue = 0;
        for (int i = 0; i < aHand.length; i++) {
            handvalue += aHand[i].getValue();
            if (aHand[i].getValue() == 11) {
                AceCounter++;
            }
            while (AceCounter > 0 && handvalue > 21) {
                handvalue -= 10;
                AceCounter--;
            }
        }
        return handvalue;
    }

    /*
     * Asks the user how much he or she would like to bet.
     */

    @Override
    public int Bet(int cash) {
        Scanner sc = new Scanner(System.in);
        int bet = sc.nextInt();
        while (bet > cash) {
            System.out.println("You cannot bet more cash than you have!");
            System.out.println("How much would you like to bet?");
            bet = sc.nextInt();
        }
        return bet;
    }

    /*
     * Called if the user wins.
     */
    @Override
    public void Win() {
        System.out.println("Congratulations, you win!");
        cash = cash + bet;
        System.out.println("Cash: " + cash);
    }

    /*
     * Called if the user loses.
     */

    @Override
    public void Lose() {
        System.out.println("Sorry, you lose!");
        cash = cash - bet;
        System.out.println("Cash: " + cash);
    }

    /*
     * Called if the user pushes
     */

    @Override
    public void Push() {
        System.out.println("It's a push!");
        System.out.println("You get your money back.");
        System.out.println("Cash: " + cash);
    }

    /*
     * Adds a card to user's hand and calculates the value of that hand. Aces are taken into account.
     */

    @Override
    public void Hit(GroupOfCards deck, List<ACard> hand) {
        hand.add(deck.drawCard());
        ACard[] aHand = new ACard[]{};
        aHand = hand.toArray(aHand);
        handvalue = 0;
        for (int i = 0; i < aHand.length; i++) {
            handvalue += aHand[i].getValue();
            if (aHand[i].getValue() == 11) {
                AceCounter++;
            }
            while (AceCounter > 0 && handvalue > 21) {
                handvalue -= 10;
                AceCounter--;
            }
        }
    }

    /*
     * Determines if a user has input hit or stand.
     */

    @Override
    public boolean isHitorStand(String hitter) {
        if (hitter.equals("hit") || hitter.equals("stand")) {
            return true;
        }
        return false;
    }

    /*
     * Determines if a user has busted.
     */

    @Override
    public boolean checkBust(int handvalue) {
        if (handvalue > 21) {
            System.out.println("You have busted!");
            return true;
        }
        return false;
    }
    /*
     * Determines if a user has input yes or no.
     */

    @Override
    public boolean isyesorno(String answer) {
        if (answer.equals("yes") || answer.equals("no")) {
            return true;
        }
        return false;
    }

    /*
     * Called if the user has a five card trick.
     */

    @Override
    public void fivecardtrick() {
        System.out.println("You have achieved a five card trick!");
        Win();
    }
}
