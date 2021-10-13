package com.tictactoe.menu;

import java.util.Scanner;

import com.tictactoe.game.GameLoader;
import com.tictactoe.player.Symbol;

public class GameMenu {

	static Scanner scan = new Scanner(System.in);

	public static void gameStart() {
		System.out.println("*****************************************");
		System.out.println("-----Developed By: CodeJavaWithSaqib-----");
		System.out.println("--------TicTacToe Game Project-----------");
		System.out.println("*****************************************");

		System.out.println("\nWelcome to TicTacToe Game, Lets Play!");
		gameMenu();
	}

	public static void gameMenu() {
		char ch = '0';
		System.out.println("Choose an Option!");
		System.out.println("1. Play Game: Player Vs Player");
		System.out.println("2. Play Game: Player Vs AI");
		System.out.println("3. Exit Game!");
		do {
			System.out.println("Enter your Option:->");
			ch = scan.nextLine().charAt(0);
			switch (ch) {
			case '1':
				loadPlayerVsPlayerGame();
				break;
			case '2':
				loadPlayerVsAI();
				break;
			case '3':
				System.out.println("Thanks for Playing!");
				System.exit(0);
				break;
			default:
				System.out.println("Enter Choice (1 - 3) to Continue!");
				break;
			}
		} while (!(ch == '1' || ch == '2' || ch == '3'));
	}
	
	private static void loadPlayerVsPlayerGame() {
		System.out.println();
		int i = 1;
		for (Symbol symbol : Symbol.values()) {
			System.out.println("Player "+ i + " Symbol: " + symbol.toString());
			i++;
		}
		GameLoader loader = new GameLoader();
		loader.startPlayerVsPlayerGame();
	}
	
	private static void loadPlayerVsAI() {
		GameLoader loader = new GameLoader();
		loader.startPlayerVsAIGame();
	}
}
