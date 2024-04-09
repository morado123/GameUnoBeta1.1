package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public void createDeck() {
        // Agregar cartas numeradas (0 al 9) de cada color
        for (CardColor color : CardColor.values()) {
            if (color != CardColor.COLOR_CHANGE) {
                for (int number = 0; number <= 9; number++) {
                    for (int i = 0; i < 2; i++) {
                        cards.add(new Card(CardType.NUMBER, color, number));
                    }
                }
            }
        }

        // Agregar cartas especiales (Skip, Reverse, Draw Two) para cada color
        for (CardColor color : CardColor.values()) {
            if (color != CardColor.COLOR_CHANGE) {
                for (int i = 0; i < 2; i++) {
                    cards.add(new Card(CardType.SKIP, color, -1)); // Cartas "Skip"
                    cards.add(new Card(CardType.REVERSE, color, -1)); // Cartas "Reverse"
                    cards.add(new Card(CardType.EAT_TWO, color, -1)); // Cartas "Draw Two"
                }
            }
        }

        // Agregar cartas de cambio de color (4 en total)
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(CardType.COLOR_CHANGE, CardColor.COLOR_CHANGE, -1));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        }
        return null;
    }

    public int size() {
        return cards.size();
    }
}
