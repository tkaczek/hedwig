package game.chess.pieces;

import game.chess.Chessboard;
import game.chess.Piece;

public class Knight extends Piece {

	@Override
	public int move(int posX, int posY,int x, int y) {
		if(((Math.abs(posX- x) ==1 && Math.abs(posY- y) == 2) 
				|| (Math.abs(posX- x) ==2 && Math.abs(posY- y) == 1))){
				if(board.getBoard()[x][y] == null || ( board.getBoard()[x][y].isWhite()^white )){
					return 0;	
				}
			}
			return -1;
	}
	public Knight(boolean white, Chessboard board) {
		super(white, board);
		// TODO Auto-generated constructor stub
	}
	public String getType(){
		return "Knight";
		
	}
	public char getChar() {
		if(white) return 'N';
		else return 'n';

	}
}