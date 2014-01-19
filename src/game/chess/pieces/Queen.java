package game.chess.pieces;

import game.chess.Chessboard;
import game.chess.Piece;

public class Queen extends Piece {

	@Override
	public int move(int posX, int posY, int x, int y) {
		if((board.getBoard()[x][y] == null && ( (Math.abs(x - posX) == Math.abs( y - posY)) && (Math.abs(x - posX) !=0)))//"normal" move
				|| ( board.getBoard()[x][y] != null  &&  (board.getBoard()[x][y].isWhite() ^ white)  // take another piece
					&& ( (Math.abs(x - posX) == Math.abs( y - posY)) && (Math.abs(x - posX) !=0)))){
				boolean free = true;																//need to check the way now
				int dir = (int) Math.signum((x - posX));					// get direction
				int dir2 = (int) Math.signum((y - posY));	
				assert dir != 0;
				assert dir2 != 0;
				int j=0;
				//check that nothing is in the way
				for( int i=dir; posX+ i != x; i+= dir){
					j+= dir2;
					if(board.getBoard()[posX+ i][posY+ j]!= null) free = false;
				}
				if(free){
					posX = x;
					posY = y;
					return 0;
				}
			}
		if((board.getBoard()[x][y] == null && ( (x == posX && y != posY) || (x != posX && y == posY) ))//"normal" move
				|| ( board.getBoard()[x][y] != null  &&  (board.getBoard()[x][y].isWhite() ^ white)  // take another piece
				   && (  y != posY ^ (x != posX )))){
				boolean free = true;																//need to check the way now
				int dir = (int) Math.signum((y - posY) + (x - posX));					// get direction
				assert dir != 0;
				if(y != posY){																//check whether nothing is in the way
					for( int i=posY+dir; i != y; i+= dir){
						if(board.getBoard()[x][i]!= null) free = false;
					}
				}else{
					for( int i=posX+dir; i != x; i+= dir){
						if(board.getBoard()[i][y]!= null) free = false;
					}
				}
				if(free){
					posX = x;
					posY = y;
					return 0;
				}
			}
			return -1;
	}

	
	public String getType(){
		return "Queen";
		
	}


	public Queen(boolean white, Chessboard board) {
		super(white, board);
		// TODO Auto-generated constructor stub
	}
	public char getChar() {
		if(white) return 'Q';
		else return 'q';

	}
}
