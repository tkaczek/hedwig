package game.chess;

import game.Board;
import game.chess.pieces.*;

/**
 * models a standard chessboard
 * 
 * @author tobias
 * 
 */
public class Chessboard extends Board {
	/**
	 * Representation of the board
	 */
	Piece[][] board = new Piece[8][8];
	/**
	 * number of rounds
	 */
	int rounds;
	/**
	 * Move history stored as a PartyRecord
	 */
	PartyRecord record;
	
	/**
	 * true if a check situation has already occurred
	 */
	boolean firstcheck;
	
	/**
	 * true if one player is currently in Check
	 */
	boolean check;

	/**
	 * 
	 * Method to move a piece and update the corresponding fields (like rounds
	 * etc)
	 * 
	 * @param x
	 *            x coordinate of origin
	 * @param y
	 *            y coordinate of origin
	 * @param destX
	 *            x coordinate of destination
	 * @param destY
	 *            y coordinate of destination
	 * @return 0 if successful, -1 otherwise
	 */
	public int move(int x, int y, int destX, int destY) {
		if (board[x][y] != null
				&& ((rounds % 2 == 1 && !board[x][y].isWhite()) || ((rounds % 2 == 0 && board[x][y]
						.isWhite()))) && x < 8 && y < 8 && x >= 0 && y >= 0
				&& destX < 8 && destY < 8 && destX >= 0 && destY >= 0) {
			int moveCode = board[x][y].move(x, y, destX, destY);
			if (moveCode != -1) {
				char dest;
				if (board[destX][destY] != null)
					dest = board[destX][destY].getChar();
				else
					dest = '-';
				board[destX][destY] = board[x][y];
				board[x][y] = null;
				boolean k = (moveCode == 2);	//king's castle
				boolean q = (moveCode == 3);	//queen's caste
				if (k) {
					board[5][y] = board[7][y];
					board[7][y] = null;
				}
				if (q) {
					board[3][y] = board[0][y];
					board[0][y] = null;
				}
				boolean ep = (moveCode == 1);
				if (ep) {
					// for En Passant take the respective taken piece as dest piece
					dest= board[destX][y].getChar(); 
					board[destX][y] = null;
				}
				boolean ccheck = check(rounds % 2 == 0); 	// white's turn? check
															// black is in
															// check!
				
				boolean mate = checkForMate(rounds % 2 == 0);
				if(mate) moveCode= 999;
				record.addMove(rounds, new Move(x, y, board[x][y].getChar(), destX, destY,
						dest, k, q, ep, ccheck, mate));
				rounds++;
				if (ccheck) {
					check = true;
					if (checkForMate(rounds % 2 == 0))// check for mate
						mate = true;
				} else
					check = false;
				return moveCode;
			}
		}
		return -1;
	}

	/**
	 * This is similar to the move method, but without updates for the record
	 * and no rounds updates etc. This method is for testing purposes only
	 * 
	 * @param x
	 *            x coordinate of origin
	 * @param y
	 *            y coordinate of origin
	 * @param destX
	 *            x coordinate of destination
	 * @param destY
	 *            y coordinate of destination
	 * @return 0 if successful, -1 otherwise
	 */
	public int movepiece(int x, int y, int destX, int destY) {
		if (board[x][y] != null && x < 8 && y < 8 && x >= 0 && y >= 0
				&& destX < 8 && destY < 8 && destX >= 0 && destY >= 0) {
			int moveCode = board[x][y].move(x, y, destX, destY);
			if (moveCode != -1) {
				board[destX][destY] = board[x][y];
				board[x][y] = null;
				return 0;
			}
		}
		return -1;
	}

	/**
	 * 
	 * checks the record to undo the last move in the record. The Move stays in
	 * the record, but will be lost when another move is made.
	 * 
	 * @return 0 if successful, -1 otherwise
	 */

	public int moveback() {
		if (rounds > 0) {
			// origin piece
			if (record.getMove(rounds - 1).getOrigFig() == 'P'
					|| record.getMove(rounds - 1).getOrigFig() == 'p') {
				Pawn p = new Pawn(record.getMove(rounds-1).getOrigFig() == 'P', this);
				board[record.getMove(rounds - 1).getOrigX()][record.getMove(
						rounds - 1).getOrigY()] = p;
			}
			if (record.getMove(rounds - 1).getOrigFig() == 'N'
					|| record.getMove(rounds - 1).getOrigFig() == 'n') {
				Knight p = new Knight(record.getMove(rounds -1).getOrigFig() == 'N',
						this);
				board[record.getMove(rounds - 1).getOrigX()][record.getMove(
						rounds - 1).getOrigY()] = p;
			}
			if (record.getMove(rounds - 1).getOrigFig() == 'Q'
					|| record.getMove(rounds - 1).getOrigFig() == 'q') {
				Queen p = new Queen(record.getMove(rounds -1).getOrigFig() == 'Q',
						this);
				board[record.getMove(rounds - 1).getOrigX()][record.getMove(
						rounds - 1).getOrigY()] = p;
			}
			if (record.getMove(rounds - 1).getOrigFig() == 'B'
					|| record.getMove(rounds - 1).getOrigFig() == 'b') {
				Bishop p = new Bishop(record.getMove(rounds -1).getOrigFig() == 'B',
						this);
				board[record.getMove(rounds - 1).getOrigX()][record.getMove(
						rounds - 1).getOrigY()] = p;
			}
			if (record.getMove(rounds - 1).getOrigFig() == 'T'
					|| record.getMove(rounds - 1).getOrigFig() == 't') {
				Tower p = new Tower(record.getMove(rounds -1).getOrigFig() == 'T',
						this);
				board[record.getMove(rounds - 1).getOrigX()][record.getMove(
						rounds - 1).getOrigY()] = p;
			}
			if (record.getMove(rounds - 1).getOrigFig() == 'K'
					|| record.getMove(rounds - 1).getOrigFig() == 'k') {
				boolean colour = record.getMove(rounds-1).getOrigFig() == 'K';
				King k = new King(colour, this);
				board[record.getMove(rounds - 1).getOrigX()][record.getMove(
						rounds - 1).getOrigY()] = k;
				if (record.getMove(rounds - 1).isKingsCastle()) {
					if (colour) {
						board[4][0] = board[6][0];
						board[7][0] = board[5][0];
						board[5][0] = null;
						board[6][0] = null;
					} else {
						board[4][7] = board[6][7];
						board[7][7] = board[5][7];
						board[5][7] = null;
						board[6][7] = null;
					}
				} else if (record.getMove(rounds - 1).isQueensCastle()) {
					if (colour) {
						board[4][0] = board[2][0];
						board[0][0] = board[3][0];
						board[1][0] = null;
						board[2][0] = null;
						board[3][0] = null;
					} else {
						board[4][7] = board[2][7];
						board[0][7] = board[3][7];
						board[1][7] = null;
						board[2][7] = null;
						board[3][7] = null;
					}
				}
			}

			// destination piece
			if (record.getMove(rounds - 1).getDestFig() == 'P'
					|| record.getMove(rounds - 1).getDestFig() == 'p') {
				Pawn p = new Pawn(record.getMove(rounds-1).getDestFig() == 'P', this);
				if(record.getMove(rounds -1).isEnPassant()){
					board[record.getMove(rounds - 1).getDestX()][record.getMove(
							rounds - 1).getOrigY()] = p;
					board[record.getMove(rounds - 1).getDestX()][record.getMove(
							rounds - 1).getDestY()] = null;
				} else{
					board[record.getMove(rounds - 1).getDestX()][record.getMove(
							rounds - 1).getDestY()] = p;
					board[record.getMove(rounds - 1).getDestX()][record.getMove(
							rounds - 1).getDestY()] = null;
				}
				
			}
			if (record.getMove(rounds - 1).getDestFig() == 'N'
					|| record.getMove(rounds - 1).getDestFig() == 'n') {
				Knight p = new Knight(record.getMove(-1).getDestFig() == 'N',
						this);
				board[record.getMove(rounds - 1).getDestX()][record.getMove(
						rounds - 1).getDestY()] = p;
			}
			if (record.getMove(rounds - 1).getDestFig() == 'Q'
					|| record.getMove(rounds - 1).getDestFig() == 'q') {
				Queen p = new Queen(record.getMove(rounds-1).getDestFig() == 'Q',
						this);
				board[record.getMove(rounds - 1).getDestX()][record.getMove(
						rounds - 1).getDestY()] = p;
			}
			if (record.getMove(rounds - 1).getDestFig() == 'B'
					|| record.getMove(rounds - 1).getDestFig() == 'b') {
				Bishop p = new Bishop(record.getMove(rounds-1).getDestFig() == 'B',
						this);
				board[record.getMove(rounds - 1).getDestX()][record.getMove(
						rounds - 1).getDestY()] = p;
			}
			if (record.getMove(rounds - 1).getDestFig() == 'T'
					|| record.getMove(rounds - 1).getDestFig() == 't') {
				Tower p = new Tower(record.getMove(rounds-1).getDestFig() == 'T',
						this);
				board[record.getMove(rounds - 1).getDestX()][record.getMove(
						rounds - 1).getDestY()] = p;
			}
			if (record.getMove(rounds - 1).getDestFig() == '-') {
				board[record.getMove(rounds - 1).getDestX()][record.getMove(
						rounds - 1).getDestY()] = null;
			}
			record.getMove(rounds-1).setUndone(true);// mark the undone move
			rounds--;
			check = check(rounds % 2 == 1);
			return 0;
		}

		return -1;
	}

	/**
	 * check whether the specified field is attacked by the specified colour
	 * 
	 * @param white
	 *            colour
	 * @param x
	 *            x coordinate of the field
	 * @param y
	 *            y coordinate of the field
	 * @return true if the field is attacked, false otherwise
	 */
	public boolean checkAttacked(boolean white, int x, int y) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null)
					if (!white) { // if we check for black
						if (!board[i][j].isWhite()) { // check black pieces
							if(board[i][j].getChar()!='k' || j != y || Math.abs(i - x)!=2)
								if (board[i][j].move(i, j, x, y) != -1)
									return true; // for attacking
						}
					} else { // otherwise
						if (board[i][j].isWhite()) { // check white pieces
							if (board[i][j].move(i, j, x, y) != -1)
								if(board[i][j].getChar()!='K' || j != y || Math.abs(i - x)!=2)
									return true; // for creating attacking
						}
					}
			}
		return false;

	}

	/**
	 * checks whether the specified player is in check on the current board
	 * 
	 * @param white
	 *            true to check for white, false to check for black
	 * @return true if the specified player is in check on the current board,
	 *         false otherwise
	 */
	public boolean check(boolean white) {
		// find King
		int x = -1;
		int y = -1;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null)
					if (white) {
						if (board[i][j].getChar() == 'K') {
							x = i;
							y = j;
						}
					} else {
						if (board[i][j].getChar() == 'k') {
							x = i;
							y = j;
						}
					}
			}
		if (x == -1)
			return false; // no king found
		return checkAttacked(!white, x, y);
	}

	/**
	 * checks whether the specified player is mate on the current board
	 * 
	 * @param white
	 *            true to check for white, false to check for black
	 * @return true if the specified player is mate on the current board, false
	 *         otherwise
	 */
	public boolean checkForMate(boolean white) {
		for (int i = 0; i < 8; i++)
			// for all places
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null && board[i][j].isWhite() == white) {// of
																			// all
																			// pieces
					for (int x = 0; x < 8; x++)
						// check for all positions
						for (int y = 0; y < 8; y++) {
							if (board[i][j].move(i, j, x, y) != -1) {// whether
																		// they
																		// can
																		// move
																		// there
								// if they can check for check
								boolean b = false;
								Piece p = board[x][y];
								board[x][y] = board[i][j];
								board[i][j] = null;
								if (!check(white))
									b = true; // if not check, then we need to
												// rebuild the board and return
												// false
								board[i][j] = board[x][y];
								board[x][y] = p;
								if (b) {
									return false;
								}
							}
						}
				}
			}
		return true; // there is no possible move which does not yield a check
						// situation
	}

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public PartyRecord getRecord() {
		return record;
	}

	public void setRecord(PartyRecord record) {
		this.record = record;
	}

	public Piece[][] getBoard() {
		return board;
	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	/**
	 * initialises new Chessboard
	 */

	public Chessboard() {
		super();
		rounds = 0;
		record = new PartyRecord();
		firstcheck = true;
		check = false;
		
		
		for(int i=0; i<8;i++){
			board[i][1] = new Pawn(true, this);
			board[i][6] = new Pawn(false, this);
		}
		board[0][7] =new Tower(false, this);
		board[1][7] =new Knight(false, this);		
		board[2][7] =new Bishop(false, this);	
		board[3][7] =new Queen(false, this);
		board[4][7] =new King(false, this);
		board[5][7] =new Bishop(false, this);	
		board[6][7] =new Knight(false, this);
		board[7][7] =new Tower(false, this);	
		board[0][0] =new Tower(true, this);
		board[1][0] =new Knight(true, this);		
		board[2][0] =new Bishop(true, this);	
		board[3][0] =new Queen(true, this);
		board[4][0] =new King(true, this);
		board[5][0] =new Bishop(true, this);	
		board[6][0] =new Knight(true, this);
		board[7][0] =new Tower(true, this);	
	}

	/**
	 * Initialises new custom Chessboard
	 * 
	 * @param board
	 *            custom Chessboard
	 */
	public Chessboard(Piece[][] board) {
		super();
		rounds = 0;
		firstcheck = true;
		record = new PartyRecord();
		check = false;
		this.board = board;
	}

	public Chessboard(Piece[][] board, int rounds, boolean firstcheck,
			boolean check) {
		super();
		this.board = board;
		this.rounds = rounds;
		this.firstcheck = firstcheck;
		record = new PartyRecord();
		this.check = check;
	}

	/**
	 * @return a String representation of the current Chessboard.
	 */
	public String toString() {
		String b = "  ABCDEFGH\r";
		b += " +--------+\r";
		for (int i = 7; i >= 0; i--) {
			b += (i+1);
			b += '|';
			for (int j = 0; j < 8; j++)
				if (board[j][i] == null)
					b += ' ';
				else
					b += board[j][i].getChar();
			b += '|';
			b += '\r';
		}
		b += " +--------+\r";
		b += "  ABCDEFGH\r";
		return b;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Chessboard s = new Chessboard();
		System.out.println(s.toString());
	}

}
