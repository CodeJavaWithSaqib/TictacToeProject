package com.tictactoe.game;

import java.util.Scanner;

import com.tictactoe.player.Player;
import com.tictactoe.player.Symbol;

public class GameLoader {
	static Scanner scan = new Scanner(System.in);

	static String playerX = Symbol.X.toString();
	static String playerO = Symbol.O.toString();

	public void startPlayerVsPlayerGame() {
		System.out.println("\nPlayer X");
		Player player1 = getPlayerInfo(playerX);
		System.out.println("\nPlayer O");
		Player player2 = getPlayerInfo(playerO);
		System.out.println("\n**********************\n");

		System.out.println("Now, In order to mark a place with your symbol");
		System.out.println("Enter cell number between (1 - 9)\n");
		GameLogic game = new GameLogic();
		game.loadBoard();
		game.printBoard();

		while (true) {
			game.playerHandler(player1);
			game.playerHandler(player2);
		}
	}

	public void startPlayerVsAIGame() {
		Player playerHuman = null;
		Player playerAI = null;
		char ch = '0';

		System.out.println("Player Human");
		System.out.println("Choose Your Symbol:");
		System.out.println("1. X");
		System.out.println("2. O");
		do {
			System.out.print("Choice:-> ");
			ch = scan.nextLine().charAt(0);
			if (ch == '1') {
				playerHuman = getPlayerInfo(playerX);
				playerAI = new Player("Computer", Symbol.O.toString());
			} else if (ch == '2') {
				playerHuman = getPlayerInfo(playerO);
				playerAI = new Player("Computer", Symbol.X.toString());
			} else {
				System.out.println("Incorrect Choice: Enter (1 or 2) to choose a symbol!");
			}
		} while (!(ch == '1' || ch == '2'));

		System.out.println("\n**********************\n");

		System.out.println("Now, In order to mark a place with your symbol");
		System.out.println("Enter cell number between (1 - 9)\n");
		GameLogic game = new GameLogic();
		game.loadBoard();
		game.printBoard();
		
		Integer randomFirstTerm = (int) Math.round(Math.random());
		if(randomFirstTerm == 0) {
			System.out.println(playerHuman.playerName+" Gets to Play first Move!");
			game.playerHandler(playerHuman);
		} else {
			System.out.println(playerAI.playerName+" Gets to Play first Move!");
			game.AIhandler(playerAI);
		}
		
		boolean currentTermHuman = randomFirstTerm == 1 ? true : false;
		boolean currentTermAI = randomFirstTerm == 0 ? true : false;
		
		while(true) {
			if(currentTermHuman) {
				game.playerHandler(playerHuman);
				currentTermHuman = false;
				currentTermAI = true;
			} else if(currentTermAI) {
				game.AIhandler(playerAI);
				currentTermHuman = true;
				currentTermAI = false;
			}
		}
	}

	private Player getPlayerInfo(String symbol) {
		String name = "";
		System.out.print("Enter your name:-> ");
		name = scan.nextLine();
		Player player = new Player(name, symbol);
		System.out.println(player.toString());
		return player;
	}

}
