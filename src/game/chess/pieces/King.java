package game.chess.pieces;

import game.chess.Chessboard;
import game.chess.Piece;

public class King extends Piece {

	@Override
	public int move(int posX, int posY, int x, int y) {
		if((board.getBoard()[x][y] == null
				|| (board.getBoard()[x][y].isWhite() ^ white)) 
				&& (((Math.abs(x - posX) <= 1) &&  (Math.abs( y - posY)) <= 1 )
				    && (Math.abs(x - posX) + Math.abs( y - posY)) !=0)    
			    || (posX == 4 && posY == 0 && white  &&((x== 6 && y == 0 && !board.check(white)&& board.getRecord().CastleCheck(white, x==6) && board.getBoard()[5][0] == null //castle white
			        && !board.checkAttacked(!white, 5, 0)&& board.getBoard()[6][0] == null  && !board.checkAttacked(!white, 6, 0)) 
			        ||  (x== 2 && y == 0 &&!board.check(white)&& board.getRecord().CastleCheck(white, x==6) && board.getBoard()[3][0] == null && !board.checkAttacked(!white, 3, 0) && board.getBoard()[2][0] == null && !board.checkAttacked(!white, 2, 0)
			            && board.getBoard()[1][0]== null)))
			    || (posX == 4 && posY == 7 && !white && ((x== 6 && y == 7 && !board.check(white) && board.getRecord().CastleCheck(white, x==6) && board.getBoard()[5][7] == null //castle black
			        && !board.checkAttacked(!white, 5, 7)&& board.getBoard()[6][7] == null  && !board.checkAttacked(!white, 6, 7)) 
			        ||  (x== 2 && y == 7 && !board.check(white) && board.getRecord().CastleCheck(white, x==6) && board.getBoard()[3][7] == null && !board.checkAttacked(!white, 3, 7) && board.getBoard()[2][7] == null && !board.checkAttacked(!white, 2, 7)
			            && board.getBoard()[1][7]== null))))
			
			{//"normal" move
					if(x==6 && posX == 4){//king's castle -> different return
						return 2;
					}
					if(x==2 && posX == 4){//queen's castle -> different return
						return 3;
					}
					return 0;
				
			}
			return -1;
	}
	public String getType(){
		return "King";
		
	}
	public King(boolean white, Chessboard board) {
		super(white, board);
		// TODO Auto-generated constructor stub
	}
	public char getChar() {
		if(white) return 'K';
		else return 'k';

	}
}
