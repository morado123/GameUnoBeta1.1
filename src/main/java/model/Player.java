package model;

import structures.*;

// Clase para representar un jugador
public class Player {
    private String name; // Nombre del jugador
    private Queue<Card> hand; // Mazo de cartas del jugador


    public Player(String name) {
        this.name = name;
        this.hand = new Queue<>();
    }


    // Método para agregar una carta a la mano del jugador
    public void addCard(Card card) {
        hand.enqueue(card);
    }

    // Método para obtener el nombre del jugador
    public String getName() {
        return name;
    }

    // Método para obtener la mano del jugador
    public Queue<Card> getHand() {
        return hand;
    }

    // Método para obtener el número de cartas en la mano del jugador
    public int getCardCount() {
        return hand.size();
    }

    //  para verificar si la mano contiene una carta específica
    public boolean contains(Card card) {
        for (Card currentCard : hand) {
            if (currentCard.equals(card)) {
                return true;
            }
        }
        return false;
    }

    //  para eliminar una carta específica de la mano
    public void removeCard(Card card) {
        Queue<Card> temp = new Queue<>();

        for (Card currentCard : hand) {
            if (!currentCard.equals(card)) {
                temp.enqueue(currentCard);
            }
        }

        hand = temp; // Reemplazar la mano original con la nueva mano sin la carta eliminada
    }



}
