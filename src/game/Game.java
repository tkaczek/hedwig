package game;
import front.ConsoleUI;
import game.chess.Chessboard;

import java.io.*;

public class Game {
	Chessboard board;			// game has rules to allow other kinds of games/variations of chess

	public Chessboard getBoard() {
		return board;
	}

	public void setBoard(Chessboard board) {
		this.board = board;
	}

	public Game(Chessboard board) {
		super();
		this.board = board;
	}


	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleUI ui = new ConsoleUI(new Chessboard());
		ui.play();

	}

}
