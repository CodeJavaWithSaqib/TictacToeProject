package com.tictactoe.game;

import java.util.Scanner;

import com.tictactoe.menu.GameMenu;
import com.tictactoe.player.Player;
import com.tictactoe.player.Symbol;

public class GameLogic {
	private static String[][] board = new String[3][3];
	static Scanner scan = new Scanner(System.in);
	Integer cellMakred = 0;
	static String playerX = Symbol.X.toString();
	static String playerO = Symbol.O.toString();

	public void loadBoard() {
		Integer cellNumber = 1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = "_" + cellNumber + "_";
				cellNumber++;
			}
		}
	}

	public void printBoard() {
		System.out.println(" ___________ ");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print("|");
				System.out.print(board[i][j]);
			}
			System.out.print("|");
			System.out.println();
		}
	}

	public void playerHandler(Player player) {
		Integer cellNumber = 0;
		boolean isCellFree = false;
		do {
			cellNumber = takeInput(player);
			isCellFree = isCellFree(cellNumber);
			if (isCellFree == false) {
				System.out.println("Cell Already marked!!\nEnter Another cell number!");
			}
		} while (!isCellFree);

		updateBoard(cellNumber, player);
		checkIfWon(player);
	}

	public void AIhandler(Player playerAI) {
		Integer cellNumber = 0;
		boolean isCellFree = false;
		do {
			cellNumber = (int) Math.floor(Math.random() * 9) + 1;
			isCellFree = isCellFree(cellNumber);
		} while (!isCellFree);
		System.out.println("\nPlayer: " +playerAI.playerName+" ("+ playerAI.playerSymbol + "), has Choosen Cell:-> "+cellNumber);
		updateBoard(cellNumber, playerAI);
		checkIfWon(playerAI);

	}

	private boolean isCellFree(Integer cellNumber) {
		int cellCount = 1;
		boolean cellMarked = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				String cell = board[i][j];
				if (cellCount == cellNumber) {
					if (cell.contains(playerX) || cell.contains(playerO)) {
						return cellMarked;
					}
				}
				cellCount++;
			}
		}
		return true;
	}

	private Integer takeInput(Player player) {
		char ch = '0';
		while (!(ch >= '1' && ch <= '9')) {
			System.out.print(
					"\nPlayer: " + player.playerName + " (" + player.playerSymbol + ") , Enter Your Cell # :-> ");
			ch = scan.nextLine().charAt(0);
			if (!(ch >= '1' && ch <= '9')) {
				System.out.println("Only Enter Numbers between (1 - 9)");
			}
		}
		return Integer.parseInt(Character.toString(ch));
	}

	private void updateBoard(Integer cellNumber, Player player) {
		int cellCount = 1;
		String cellReplace = "_" + player.playerSymbol + "_";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (cellCount == cellNumber) {
					board[i][j] = cellReplace;
				}
				cellCount++;
			}
		}
		this.cellMakred++;
		printBoard();
	}

	public void checkIfWon(Player player) {
		if (this.cellMakred == 9) {
			System.out.println("Game Draw! No One Won!");
			GameMenu.gameMenu();
		}
		if (checkHorizontal(player)) {
			System.out.println("\nCongrats! " + player.playerName + "(" + player.playerSymbol + "), You Won!");
			GameMenu.gameMenu();
		}
		if (checkVertical(player)) {
			System.out.println("\nCongrats! " + player.playerName + "(" + player.playerSymbol + "), You Won!");
			GameMenu.gameMenu();
		}
		if (checkDiagonal(player)) {
			System.out.println("\nCongrats! " + player.playerName + "(" + player.playerSymbol + "), You Won!");
			GameMenu.gameMenu();
		}
	}

	private boolean checkHorizontal(Player player) {
		boolean didPlayerWin = false;
		String playerSymbol = player.playerSymbol;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < 1; j++) {
				String cell1 = board[i][j];
				String cell2 = board[i][j + 1];
				String cell3 = board[i][j + 2];
				if (cell1.contains(playerSymbol) && cell2.contains(playerSymbol) && cell3.contains(playerSymbol)) {
					didPlayerWin = true;
				}
			}
		}
		return didPlayerWin;
	}

	private boolean checkVertical(Player player) {
		boolean didPlayerWin = false;
		String playerSymbol = player.playerSymbol;

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < board.length; j++) {
				String cell1 = board[i][j];
				String cell2 = board[i + 1][j];
				String cell3 = board[i + 2][j];
				if (cell1.contains(playerSymbol) && cell2.contains(playerSymbol) && cell3.contains(playerSymbol)) {
					didPlayerWin = true;
				}
			}
		}
		return didPlayerWin;
	}

	private boolean checkDiagonal(Player player) {
		Integer diagonalMarkCount = 0;
		String playerSymbol = player.playerSymbol;
		String cell = "";

		// primary diagonal
		int i = 0;
		while (i < board.length) {
			cell = board[i][i];
			if (cell.contains(playerSymbol)) {
				diagonalMarkCount++;
			}
			i++;
		}
		if (diagonalMarkCount == 3) {
			return true;
		}

		// secondary diagonal
		int j = board.length - 1;
		i = 0;
		diagonalMarkCount = 0;
		while (i < board.length) {
			cell = board[i][j];
			if (cell.contains(playerSymbol)) {
				diagonalMarkCount++;
			}
			i++;
			j--;
		}
		if (diagonalMarkCount == 3) {
			return true;
		}
		return false;
	}
}
