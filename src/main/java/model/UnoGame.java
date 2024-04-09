package model;

import structures.tablasHash.HashTable;
import ui.UnoUI;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoGame {
    private final ArrayList<Player> players; // Lista de jugadores
    private final Deck deck; // Mazo de juego
    private final DiscardPile discardPile; // Mazo de descarte
    private final HashTable<Player, Integer> playerCardCounts; // Contadores de cartas por jugador por turno
    private int currentPlayerIndex; // Índice del jugador actual en turno
    private boolean reverseOrder; // Indica si el orden de juego está invertido
    private CardColor currentColor; // Color actual de juego
    private CardType currentSpecialType; // Tipo de carta especial actual (si hay)
    private final UnoUI unoUI;

    public UnoGame(ArrayList<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.discardPile = new DiscardPile();
        this.playerCardCounts = new HashTable<>(players.size());
        this.currentPlayerIndex = 0;
        this.reverseOrder = false;
        this.currentColor = null;
        this.currentSpecialType = null;
        this.unoUI = new UnoUI(this); // Inicializar correctamente el atributo unoUI
        initializeGame();
    }

    public void initializeGame() {
        // Crear y barajar el mazo de juego
        deck.createDeck();
        deck.shuffle();

        // Distribuir 7 cartas a cada jugador
        dealCards();

        // Colocar una carta inicial en el mazo de descarte
        Card initialCard = deck.drawCard();
        discardPile.addCard(initialCard);
        if (initialCard.getType() == CardType.NUMBER) {
            currentColor = initialCard.getColor();
        } else {
            currentColor = CardColor.COLOR_CHANGE;
            currentSpecialType = CardType.COLOR_CHANGE;
        }

        // Inicializar contadores de cartas por jugador por turno
        for (Player player : players) {
            playerCardCounts.put(player, 7); //DISEÑADORES
        }
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card card = deck.drawCard();
                if (card != null) {
                    player.addCard(card);
                } else {
                    System.out.println("¡Error al repartir cartas! No quedan suficientes cartas en el mazo.");
                }
            }
        }
    }

//Revision Pendiente. Se estan repitiendo un jugadore 2 veces.
    public void nextTurn() {
        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = getCurrentPlayer();
        System.out.println("Turno de " + currentPlayer.getName());

        // Mostrar el estado actual del juego
        System.out.println("\nCarta actual en el mazo de descarte: " + discardPile.getTopCard());

        // Mostrar mensaje para seleccionar una carta o comer dos cartas
        System.out.println("\nSelecciona el índice de la carta que deseas jugar (o selecciona 0 para comer dos cartas): ");

        // Lógica del juego: permitir al jugador jugar una carta válida
        Card selectedCard = null;
        while (selectedCard == null) {
            System.out.println("\nTu mano:");
            unoUI.printHand(currentPlayer);

            int selectedIndex = scanner.nextInt();
            if (selectedIndex == 0) {
                drawCards(currentPlayer);
                for (Card card : currentPlayer.getHand()) {
                    if (isValidPlay(card)) {
                        selectedCard = card;
                        break;
                    }
                }
                if (selectedCard == null) {
                    nextPlayer();
                    return;
                }
            } else {
                selectedCard = unoUI.selectCardFromHand(currentPlayer, selectedIndex);
                if (selectedCard == null || !isValidPlay(selectedCard)) {
                    System.out.println("\n¡Carta inválida! Intenta de nuevo.");
                }
            }
        }

        // Realizar la jugada
        playCard(currentPlayer, selectedCard);

        // Verificar si el jugador ganó después de jugar su carta
        if (currentPlayer.getHand().isEmpty()) {
            System.out.println(currentPlayer.getName() + " ha ganado el juego!");
            return;
        }

        // Avanzar al siguiente turno
        nextPlayer();
    }


    private void nextPlayer() {
        if (reverseOrder) {
            currentPlayerIndex = (currentPlayerIndex == 0) ? players.size() - 1 : currentPlayerIndex - 1;
        } else {
            currentPlayerIndex = (currentPlayerIndex == players.size() - 1) ? 0 : currentPlayerIndex + 1;
        }
        System.out.println("Índice del jugador actual: " + currentPlayerIndex);
    }

    private void drawCards(Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawCard();
            if (card != null) {
                player.addCard(card);
                System.out.println("Has obtenido la carta: " + card);
            } else {
                System.out.println("No quedan cartas en el mazo.");
            }
        }
    }

    public boolean isValidPlay(Card card) {
        Card topCard = discardPile.getTopCard();

        // Permitir jugar una carta de "Reversa" si coincide con el color o es un cambio de color
        if (card.getType() == CardType.REVERSE) {
            return card.getColor() == currentColor || card.getColor() == CardColor.COLOR_CHANGE;
        }

        if (topCard.getType() == CardType.NUMBER && card.getType() == CardType.NUMBER) {
            return topCard.getColor() == card.getColor() || topCard.getNumber() == card.getNumber();
        } else {
            return topCard.getType() == card.getType() || card.getType() == CardType.COLOR_CHANGE;
        }
    }

    public void playCard(Player player, Card card) {
        if (!isValidPlay(card)) {
            System.out.println("Invalid card play. Please try again.");
            return;
        }

        if (!player.contains(card)) {
            System.out.println("The player does not have that card in hand.");
            return;
        }

        discardPile.addCard(card);
        player.removeCard(card);

        if (card.getType() == CardType.COLOR_CHANGE) {
            handleSpecialCard(card);
        } else {
            switch (card.getType()) {
                case SKIP:
                    skipNextPlayer();
                    break;
                case REVERSE:
                    reverseOrder();
                    break;
                case EAT_TWO:
                    drawTwoCards();
                    skipNextPlayer();
                    break;
                default:
                    break;
            }

            if (!player.getHand().isEmpty()) {
                nextTurn();
            }
        }
    }

    private void skipNextPlayer() {
        nextPlayer();
    }

    private void reverseOrder() {
        reverseOrder = !reverseOrder;
        System.out.println("Order reversed: " + reverseOrder);
    }

    public void handleSpecialCard(Card card) {
        switch (card.getType()) {
            case SKIP:
                skipNextPlayer();
                break;
            case REVERSE:
                reverseOrder();
                break;
            case EAT_TWO:
                drawTwoCards();
                skipNextPlayer();
                break;
            case COLOR_CHANGE:
                chooseNewColor();
                break;
        }
    }
    public void changeColor(CardColor color) {
        currentColor = color;
    }

    private void chooseNewColor() {
        // Imprimir un mensaje para que el jugador elija un nuevo color
        System.out.println("Choose a new color:");
        System.out.println("1. Red");
        System.out.println("2. Blue");
        System.out.println("3. Green");
        System.out.println("4. Yellow");

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validChoice = false;

        // Ciclo para validar la entrada del jugador
        while (!validChoice) {
            // Solicitar al jugador que ingrese su elección
            System.out.print("Enter the number corresponding to your choice: ");
            choice = scanner.nextInt();

            // Validar la entrada del jugador
            if (choice >= 1 && choice <= 4) {
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        // Establecer el nuevo color según la elección del jugador
        switch (choice) {
            case 1:
                changeColor(CardColor.RED);
                break;
            case 2:
                changeColor(CardColor.BLUE);
                break;
            case 3:
                changeColor(CardColor.GREEN);
                break;
            case 4:
                changeColor(CardColor.YELLOW);
                break;
        }

        System.out.println("You chose " + getCurrentColor() + " as the new color.");
    }

    private void drawTwoCards() {
        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawCard();
            if (card != null) {
                nextPlayer.addCard(card);
            } else {
                System.out.println("No quedan cartas en el mazo.");
            }
        }
    }

    public boolean checkGameEnd() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true; // El jugador ha ganado...
            }
        }
        return false; // El juego continúa...
    }

    public Player getWinner() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return player; // Devolver al jugador que ha ganado
            }
        }
        return null; // En caso de que no haya ganador
    }

    public Player getCurrentPlayer() {
        if (players.isEmpty()) {
            throw new IllegalStateException("No hay jugadores en el juego");
        }
        return players.get(currentPlayerIndex);
    }

    public Card getTopCard() {
        return discardPile.getTopCard();
    }

    public CardColor getCurrentColor() {
        return currentColor;
    }

    public int getRemainingDeckSize() {
        return deck.size();
    }
}
