package game.chess.pieces;

import game.chess.Chessboard;
import game.chess.Piece;

public class Pawn extends Piece {

	@Override
	public int move(int posX, int posY, int x, int y) {
		if (white) {
			if ((posY == 1 && board.getBoard()[x][y] == null && x == posX && (y
					- posY == 1 || y - posY == 2))// first move TODO hier fehlt was
					|| (posY != 1 && (board.getBoard()[x][y] == null
							&& x == posX && y - posY == 1))// "normal" move
					|| ((board.getBoard()[x][y] != null
							&& !(board.getBoard()[x][y].isWhite())
							&& Math.abs(x - posX) == 1 && y - posY == 1)// take
																		// another
																		// piece
					|| (board.getBoard()[x][y] == null // en passant
							&& posY == 4 && board.getRounds()>0 
							&& board.getRecord().getMove(board.getRounds()-1)
									.getOrigFig() == 'p'
							&& Math.abs(posX - x) == 1
							&& board.getRecord().getMove(board.getRounds()-1)
									.getOrigY() == 6
							&& board.getRecord().getMove(board.getRounds()-1)
									.getDestY() == 4
							&& board.getRecord().getMove(board.getRounds()-1)
									.getDestX() == x && y == 5))) {
				boolean ep = posX != x && board.getBoard()[x][y] == null;

				if (ep)
					return 1;
				return 0;
			}

		} else {
			if ((posY == 6 && board.getBoard()[x][y] == null && x == posX && (posY
					- y == 1 || posY - y == 2))// first move
					|| (posY != 6 && (board.getBoard()[x][y] == null
							&& x == posX && posY - y == 1))// "normal" move
					|| ((board.getBoard()[x][y] != null
							&& board.getBoard()[x][y].isWhite()
							&& Math.abs(x - posX) == 1 && posY - y == 1))// take
																			// another
																			// piece
					|| (board.getBoard()[x][y] == null // en passant
							&& posY == 2 &&board.getRounds()>0
							&& board.getRecord().getMove(board.getRounds()-1)
									.getOrigFig() == 'P'
							&& Math.abs(posX - x) == 1
							&& board.getRecord().getMove(board.getRounds()-1)
									.getOrigY() == 1
							&& board.getRecord().getMove(board.getRounds()-1)
									.getDestY() == 3
							&& board.getRecord().getMove(board.getRounds()-1)
									.getDestX() == x && y == 1))// en passant
			{
				boolean ep = posX != x && board.getBoard()[x][y] == null;
				if (ep)
					return 1;
				return 0;
			}
		}
		return -1;
	}

	public String getType() {
		return "Pawn";

	}

	public Pawn(boolean white, Chessboard board) {
		super(white, board);
		// TODO Auto-generated constructor stub
	}

	public char getChar() {
		if (white)
			return 'P';
		else
			return 'p';

	}
}
