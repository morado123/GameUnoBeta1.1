// Clase UnoUI


//NO SE ESTA UTILIZando LA CLASE PILA
//Me ayudarias con algo despues del maso, quiero que cua
package ui;

import model.Player;
import model.UnoGame;
import model.Card;
import structures.Queue;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoUI {
    private final UnoGame unoGame;

    public UnoUI(UnoGame unoGame) {
        this.unoGame = unoGame;
    }

    public Card selectCardFromHand(Player player, int selectedIndex) {
        Queue<Card> hand = player.getHand();
        int currentIndex = 1;

        for (Card card : hand) {
            if (currentIndex == selectedIndex) {
                return card;
            }
            currentIndex++;
        }

        return null; // Devuelve null si el índice es inválido
    }

    public void printHand(Player player) {
        Queue<Card> hand = player.getHand();
        int index = 1;
        for (Card card : hand) {
            System.out.println(index + ". " + card);
            index++;
        }
    }

    public void startGame(ArrayList<Player> players) {
        Scanner scanner = new Scanner(System.in);

        // Bucle principal del juego
        while (!unoGame.checkGameEnd()) {
            // Obtener el jugador actual
            Player currentPlayer = unoGame.getCurrentPlayer();

            // Mostrar el estado actual del juego
            if (currentPlayer != null) {
                System.out.println("\nTurno de " + currentPlayer.getName());
            } else {
                System.out.println("\nEl jugador actual es nulo.");
            }

            System.out.println("\nCarta actual en el mazo de descarte: " + unoGame.getTopCard());
            System.out.println("Cartas restantes en el mazo: " + unoGame.getRemainingDeckSize());

            // Lógica del juego: permitir al jugador jugar una carta válida
            unoGame.nextTurn();
        }

        // Juego terminado, fin fin end end
        System.out.println("\n¡Fin del juego!");
        System.out.println("El ganador es: " + unoGame.getWinner().getName());
    }

//Lo ideal es poner este main en el paquete Ui obviamente mejorandolo
// La clase UnoUi sin el main pasarle al paquete model

    //Hay un mal manejo de los turnos, se esta duplicando. Revision Pendiente
    //No se como hice, pero creo que una estructura no la estoy aplicando, estructura Stack. Revision Pendiente
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n¡BIENVENIDO AL JUEGO DE CARTAS UNO!\n");
        System.out.println("Diseñado por los estudiantes de ingeniería de sistemas de 3er semestre: Adri, Felipe y Karol");
        System.out.println("Comencemos...\n");

        //Agregar condicional para que acepte entre 2 a 5 jugadores. o seria de 2 hasta 10. Revision Pendiente.
        System.out.print("Ingrese el número de jugadores (entre 2 y 5): ");
        int numPlayers = scanner.nextInt();

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Ingrese el nombre del jugador " + i + ": ");
            String playerName = scanner.next();
            Player player = new Player(playerName);
            players.add(player);
        }

        // Crear el juego con los jugadores creados
        UnoGame unoGame = new UnoGame(players);

        // Crear interfaz de usuario y comenzar el juego
        UnoUI unoConsoleUI = new UnoUI(unoGame);
        unoConsoleUI.startGame(players);
    }

}
