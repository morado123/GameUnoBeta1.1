package model;
public class Card {
    private CardType type;
    private CardColor color;
    private int number;

    public Card(CardType type, CardColor color, int number) {
        this.type = type;
        this.color = color;
        this.number = number;
    }

    public CardType getType() {
        return type;
    }

    public CardColor getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        if (type == CardType.NUMBER) {
            return color + " " + number;
        } else {
            return color + " " + type;
        }
    }
}
