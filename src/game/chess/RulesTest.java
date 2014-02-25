package game.chess;

import static org.junit.Assert.*;
import game.chess.pieces.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RulesTest {
	static Chessboard cb= new Chessboard();
	
	@BeforeClass
	public static void Setup(){
		cb= new Chessboard(new Piece[8][8]);
		
	}
	
	@Test
	public void testPawn() {
		Piece[][] board = new Piece[8][8];
		board[1][1] = new Pawn(true,cb);	// white Pawns
		board[2][1] = new Pawn(true,cb);
		board[1][3] = new Pawn(true,cb);
		board[4][5] = new Pawn(true,cb);
		board[1][6] = new Pawn(false,cb);//black Pawns
		board[2][6] = new Pawn(false,cb);
		board[3][6] = new Pawn(false,cb);
		cb.setBoard(board);
		//board is set-up. Try moving with white
		assertEquals(0, cb.movepiece(1,1, 1, 2));
		assertEquals("Pawn",cb.getBoard()[1][2].getType());
		assertEquals(null,cb.getBoard()[1][1]);
		assertEquals("Pawn",cb.getBoard()[2][1].getType());
		//move two steps on first move
		assertEquals(0,cb.movepiece(2,1, 2, 3));
		assertEquals("Pawn",cb.getBoard()[2][3].getType());
		assertEquals(null,cb.getBoard()[2][1]);

		//cannot step "over" own pawn
		assertEquals( -1,cb.movepiece(1,2, 1, 3));
		assertEquals("Pawn",cb.getBoard()[1][2].getType());
		assertEquals("Pawn",cb.getBoard()[1][3].getType());
		//move black
		assertEquals(0, cb.movepiece(1,6, 1, 4));       //move two steps on first move
		assertEquals("Pawn",cb.getBoard()[1][4].getType());
		assertEquals(null,cb.getBoard()[1][6]);
		//move one step on first move
		assertEquals("Pawn",cb.getBoard()[2][6].getType());
		assertEquals(0, cb.movepiece(2,6, 2, 5));
		assertEquals(-1, cb.movepiece(2,5, 1, 4));	//can't take own pawn
		assertEquals("Pawn",cb.getBoard()[2][5].getType());
		assertEquals("Pawn",cb.getBoard()[1][4].getType());
		assertEquals(0, cb.movepiece(2,5, 2, 4));
		assertEquals(0, cb.movepiece(2,4, 1, 3));	//take white pawn
		assertEquals(null,cb.getBoard()[2][4]);
		assertFalse(cb.getBoard()[1][3].isWhite());
		assertEquals(-1, cb.movepiece(1,3, 1, 4));//no backwards moves!
		assertEquals("Pawn",cb.getBoard()[4][5].getType());
		assertEquals("Pawn",cb.getBoard()[3][6].getType());
		assertFalse(cb.getBoard()[3][6].isWhite());
		assertEquals(0, cb.movepiece(4,5, 3, 6));//take black pawn
		assertEquals("Pawn",cb.getBoard()[3][6].getType());
		assertEquals(null,cb.getBoard()[4][5]);
		assertTrue(cb.getBoard()[3][6].isWhite());
		
		//TODO En Passant!
	}
	@Test
	public void testKnight() {
		Piece[][] board = new Piece[8][8];
		board[1][1] = new Knight(true,cb);	// white pawns
		board[2][1] = new Knight(true,cb);
		board[1][3] = new Knight(true,cb);
		board[3][5] = new Knight(true,cb);
		board[1][6] = new Knight(false,cb);//black pawns
		board[2][6] = new Knight(false,cb);
		board[3][6] = new Knight(false,cb);
		cb.setBoard(board);
		assertEquals(-1, cb.movepiece(1,3, 2, 1)); //can't move on taken field
		assertEquals(-1, cb.movepiece(2,1, 1, 3));
		assertEquals(-1, cb.movepiece(1,1, -1, 0));
		assertEquals(0, cb.movepiece(1,1, 2, 3)); // can "jump" over other piece
		assertEquals(null, board[1][1]);			// piece is moved
		assertEquals("Knight", board[2][3].getType());

		assertEquals(0, cb.movepiece(2,6, 1, 4)); // move black piece
		assertEquals(null, board[2][6]);
		assertEquals("Knight", board[1][4].getType());
		assertEquals(false,cb.getBoard()[1][6].isWhite());
		assertEquals(0, cb.movepiece(3,5, 1, 6)); // take black piece
		assertEquals("Knight",cb.getBoard()[1][6].getType());
		assertEquals(true,cb.getBoard()[1][6].isWhite());
		assertEquals(null,cb.getBoard()[3][5]);
		assertEquals(0, cb.movepiece(1,4, 3, 5));
		assertEquals(true,cb.getBoard()[1][6].isWhite());
		assertEquals(0, cb.movepiece(3,5, 1, 6)); // take white piece
		assertEquals(false,cb.getBoard()[1][6].isWhite());
		assertEquals("Knight",cb.getBoard()[1][6].getType());
		assertEquals(null,cb.getBoard()[3][5]);
		
		}
	@Test
	public void testQueen() {
		//Queens can move like bishops and like towers, so: Bishop Tests
		Piece[][] board = new Piece[8][8];
		board[2][1] = new Queen(true,cb);	// white Queens
		board[1][0] = new Queen(true,cb);	// white Queens
		board[2][5] = new Queen(false,cb);//black Queens
		board[5][2] = new Queen(false,cb);
		board[5][4] = new Queen(false,cb);
		cb.setBoard(board);
		assertEquals(-1, cb.movepiece(1,0, 2, 1)); //can't move on taken field
		assertEquals(-1, cb.movepiece(2,1, 1, 0));
		assertEquals(-1, cb.movepiece(2,5, 5,2));
		assertEquals(-1, cb.movepiece(2,1, 6,5)); // can't "jump" over other piece
		assertEquals(null, cb.getBoard()[6][5]);			// piece is not moved
		assertEquals(0, cb.movepiece(2,5, 4,3));			// move black piece
		assertEquals(null, cb.getBoard()[2][5]);			// piece is  moved
		assertEquals("Queen", cb.getBoard()[4][3].getType());
		assertEquals(0, cb.movepiece(5,2, 3,0));			// move black piece
		assertEquals(null, cb.getBoard()[5][2]);			// piece is  moved
		assertEquals("Queen", cb.getBoard()[3][0].getType());
		assertEquals(true, cb.getBoard()[2][1].isWhite());	// take white piece
		assertEquals(0, cb.movepiece(4,3, 2,1));			
		assertEquals(null, cb.getBoard()[4][3]);			
		assertEquals("Queen", cb.getBoard()[2][1].getType());
		assertEquals(false, cb.getBoard()[2][1].isWhite());	// take black piece
		assertEquals(0, cb.movepiece(1,0, 2,1));			
		assertEquals(null, cb.getBoard()[1][0]);			
		assertEquals("Queen", cb.getBoard()[2][1].getType());
		assertEquals(true, cb.getBoard()[2][1].isWhite());
		assertEquals(0, cb.movepiece(2,1, 3,1));		// move about
		assertEquals(0, cb.movepiece(3,1, 2,1));		// move about
		assertEquals(0, cb.movepiece(2,1, 0,3));		// move about
		assertEquals(-1, cb.movepiece(0,3, 4,8));		// move about
		assertEquals(0, cb.movepiece(0,3, 4,7));		// move about
		assertEquals(null, cb.getBoard()[2][1]);	
		assertEquals(null, cb.getBoard()[0][3]);		
		assertEquals("Queen", cb.getBoard()[4][7].getType());
		assertEquals(true, cb.getBoard()[4][7].isWhite());		
		
		//now Tower tests
		
		Piece[][] board2 = new Piece[8][8];
		board2[0][0] = new Queen(true,cb);	// white Queens
		board2[0][1] = new Queen(true,cb);
		board2[5][4] = new Queen(false,cb);	// black Queens
		board2[2][5] = new Queen(false,cb);
		board2[5][2] = new Queen(false,cb);
		board2[5][5] = new Queen(false,cb);
		cb.setBoard(board2);
		assertEquals(-1, cb.movepiece(0,0, 0, 7)); //can't move over taken field
		assertEquals(-1, cb.movepiece(0,1, 0, 0)); //can't move to taken field
		assertEquals(0, cb.movepiece(0,0, 7, 0)); //move about

		assertEquals(0, cb.movepiece(7,0,7,7)); //move about

		assertEquals(0, cb.movepiece(7,7,0,7)); //move about
		assertEquals(0, cb.movepiece(0,7,0,2)); //move about

		assertEquals(0, cb.movepiece(0,2,4,6)); // diagonal move

		assertEquals(0, cb.movepiece(4,6,0,2)); // diagonal move back
		assertEquals(true, cb.getBoard()[0][2].isWhite());//take black Queen
		assertEquals(false, cb.getBoard()[5][2].isWhite());
		assertEquals(0, cb.movepiece(0,2,5,2)); 
		assertEquals(null, cb.getBoard()[0][2]);
		assertEquals(true, cb.getBoard()[5][2].isWhite());
		assertEquals(false, cb.getBoard()[5][4].isWhite());//take white Queen
		assertEquals(0, cb.movepiece(5,4,5,2)); 
		assertEquals(null, cb.getBoard()[5][4]);
		assertEquals(false, cb.getBoard()[5][2].isWhite());
		assertEquals(-1, cb.movepiece(5,2,8,2)); //no moving outside the board
		assertEquals(-1, cb.movepiece(5,2,-1,2)); 

		assertEquals(0, cb.movepiece(5,2,4,2)); 
		assertEquals(-1, cb.movepiece(4,2,4,8)); 
		assertEquals(-1, cb.movepiece(4,2,4,-1)); 

		}
	@Test
	public void testBishop() {
		Piece[][] board = new Piece[8][8];
		board[2][1] = new Bishop(true,cb);	// white Bishops
		board[1][0] = new Bishop(true,cb);	// white Bishops
		board[2][5] = new Bishop(false,cb);//black Bishops
		board[5][2] = new Bishop(false,cb);
		board[5][4] = new Bishop(false,cb);
		cb.setBoard(board);
		assertEquals(-1, cb.movepiece(1,0, 2, 1)); //can't move on taken field
		assertEquals(-1, cb.movepiece(2,1, 1, 0));
		assertEquals(-1, cb.movepiece(2,5, 5,2));
		assertEquals(-1, cb.movepiece(2,1, 6,5)); // can't "jump" over other piece
		assertEquals(null, board[6][5]);			// piece is not moved
		assertEquals(0, cb.movepiece(2,5, 4,3));			// move black piece
		assertEquals(null, board[2][5]);			// piece is  moved
		assertEquals("Bishop", board[4][3].getType());

		assertEquals(0, cb.movepiece(5,2, 3,0));			// move black piece
		assertEquals(null, board[5][2]);			// piece is  moved
		assertEquals("Bishop", board[3][0].getType());
		assertEquals(true, board[2][1].isWhite());	// take white piece
		assertEquals(0, cb.movepiece(4,3, 2,1));			
		assertEquals(null, board[4][3]);			
		assertEquals("Bishop", board[2][1].getType());
		assertEquals(false, board[2][1].isWhite());
		assertEquals(false, board[2][1].isWhite());	// take black piece
		assertEquals(0, cb.movepiece(1,0, 2,1));			
		assertEquals(null, board[1][0]);			
		assertEquals("Bishop", board[2][1].getType());
		assertEquals(true, board[2][1].isWhite());
		assertEquals(-1, cb.movepiece(2,1, 3,1));		// move about
		assertEquals(0, cb.movepiece(2,1, 0,3));		// move about
		assertEquals(-1, cb.movepiece(0,3, 4,8));		// move about
		assertEquals(0, cb.movepiece(0,3, 4,7));		// move about
		assertEquals(null, board[2][1]);	
		assertEquals(null, board[0][3]);		
		assertEquals("Bishop", board[4][7].getType());
		assertEquals(true, board[4][7].isWhite());		
		}
	@Test
	public void testTower() {
		Piece[][] board = new Piece[8][8];
		board[0][0] = new Tower(true,cb);	// white Towers
		board[0][1] = new Tower(true,cb);
		board[5][4] = new Tower(false,cb);	// black Towers
		board[2][5] = new Tower(false,cb);
		board[5][2] = new Tower(false,cb);
		board[5][5] = new Tower(false,cb);
		cb.setBoard(board);
		assertEquals(-1, cb.movepiece(0,0, 0, 7)); //can't move over taken field
		assertEquals(-1, cb.movepiece(0,1, 0, 0)); //can't move to taken field
		assertEquals(0, cb.movepiece(0,0, 7, 0)); //move about

		assertEquals(0, cb.movepiece(7,0,7,7)); //move about

		assertEquals(0, cb.movepiece(7,7,0,7)); //move about
		assertEquals(0, cb.movepiece(0,7,0,2)); //move about

		assertEquals(-1, cb.movepiece(0,2,4,6)); //no diagonal move
		assertEquals(true, board[0][2].isWhite());//take black tower
		assertEquals(false, board[5][2].isWhite());
		assertEquals(0, cb.movepiece(0,2,5,2)); 
		assertEquals(null, board[0][2]);
		assertEquals(true, board[5][2].isWhite());
		assertEquals(false, board[5][4].isWhite());//take white tower
		assertEquals(0, cb.movepiece(5,4,5,2)); 
		assertEquals(null, board[5][4]);
		assertEquals(false, board[5][2].isWhite());
		assertEquals(-1, cb.movepiece(5,2,8,2)); //no moving outside the board
		assertEquals(-1, cb.movepiece(5,2,-1,2)); 

		assertEquals(0, cb.movepiece(5,2,4,2)); 
		assertEquals(-1, cb.movepiece(4,2,4,8)); 
		assertEquals(-1, cb.movepiece(4,2,4,-1)); 
		}
	public void testKing() {
		//TODO implement
		}
	@Test
	public void testMate(){
		//use the situations for testCheck first
		Piece[][] board = new Piece[8][8];
		//basic check
		board[0][0] = new King(true,cb);	// white Towers
		board[0][1] = new Tower(false,cb);
		cb.setBoard(board);
		assertFalse(cb.checkForMate(true));
		//move king out of check
		board[0][0] = null;
		board[1][0] = new King(true,cb);
		cb.setBoard(board);
		assertFalse(cb.checkForMate(true));
		//more complicated
		Piece[][] board1 = new Piece[8][8];
		//Towers
		board1[0][0] = new Tower(true, cb);
		board1[7][0] = new Tower(true, cb);
		board1[0][7] = new Tower(false, cb);
		board1[7][7] = new Tower(false, cb);
		//Pawns
		board1[0][1] = new Pawn(true, cb);
		board1[1][1] = new Pawn(true, cb);
		board1[2][1] = new Pawn(true, cb);
		board1[5][1] = new Pawn(true, cb);
		board1[6][1] = new Pawn(true, cb);
		board1[0][6] = new Pawn(false, cb);
		board1[1][6] = new Pawn(false, cb);
		board1[2][6] = new Pawn(false, cb);
		board1[5][6] = new Pawn(false, cb);
		board1[6][6] = new Pawn(false, cb);
		board1[7][6] = new Pawn(false, cb);
		//Kings
		board1[4][0] = new King(true, cb);
		board1[4][7] = new King(false, cb);
		//Queens
		board1[2][3] = new Queen(true, cb);
		board1[3][5] = new Queen(false, cb);
		cb.setBoard(board1);
		/*
			  12345678
			 +--------+
			8|t   k  t|
			7|ppp  ppp|
			6|   q    |
			5|        |
			4|  Q     |
			3|        |
			2|PPP  PP |
			1|T   K  T|
			 +--------+
			  abcdefgh
		 * 
		 */
		assertFalse(cb.checkForMate(true));
		assertFalse(cb.checkForMate(false));
		board1[4][5]=board1[3][5];
		board1[3][5]=null;
		assertFalse(cb.checkForMate(true));
		assertFalse(cb.checkForMate(false));
		board1[4][5].setWhite(true);
		assertFalse(cb.checkForMate(true));
		assertFalse(cb.checkForMate(false));
		board1[0][4]= new Bishop(false,cb);
		/*
		  12345678
		 +--------+
		8|t   k  t|
		7|ppp  ppp|
		6|   Q    |
		5|b       |
		4|  Q     |
		3|        |
		2|PPP  PP |
		1|T   K  T|
		 +--------+
		  abcdefgh
	 * 
	 */
		assertFalse(cb.checkForMate(true));
		assertFalse(cb.checkForMate(false));
		board1[3][5]=null;
		board1[4][5]=new Queen(true,cb);
		board1[3][6]=new Queen(true,cb);
		/*
		  12345678
		 +--------+
		8|t   k  t|
		7|pppQ ppp|
		6|    Q   |
		5|b       |
		4|  Q     |
		3|        |
		2|PPP  PP |
		1|T   K  T|
		 +--------+
		  abcdefgh
	 * 
	 */
		assertFalse(cb.checkForMate(true));
		assertFalse(cb.checkForMate(false));
		board1[6][7] = new Queen(true,cb);
		assertFalse(cb.checkForMate(true));
		assertTrue(cb.checkForMate(false));
	}
	@Test
	public void testCheck(){
		Piece[][] board = new Piece[8][8];
		//basic check
		board[0][0] = new King(true,cb);	// white Towers
		board[0][1] = new Tower(false,cb);
		cb.setBoard(board);
		assertTrue(cb.check(true));
		//move king out of check
		board[0][0] = null;
		board[1][0] = new King(true,cb);
		cb.setBoard(board);
		assertFalse(cb.check(true));
		//more complicated
		Piece[][] board1 = new Piece[8][8];
		//Towers
		board1[0][0] = new Tower(true, cb);
		board1[7][0] = new Tower(true, cb);
		board1[0][7] = new Tower(false, cb);
		board1[7][7] = new Tower(false, cb);
		//Pawns
		board1[0][1] = new Pawn(true, cb);
		board1[1][1] = new Pawn(true, cb);
		board1[2][1] = new Pawn(true, cb);
		board1[5][1] = new Pawn(true, cb);
		board1[6][1] = new Pawn(true, cb);
		board1[0][6] = new Pawn(false, cb);
		board1[1][6] = new Pawn(false, cb);
		board1[2][6] = new Pawn(false, cb);
		board1[5][6] = new Pawn(false, cb);
		board1[6][6] = new Pawn(false, cb);
		board1[7][6] = new Pawn(false, cb);
		//Kings
		board1[4][0] = new King(true, cb);
		board1[4][7] = new King(false, cb);
		//Queens
		board1[2][3] = new Queen(true, cb);
		board1[3][5] = new Queen(false, cb);
		cb.setBoard(board1);
		/*
			  12345678
			 +--------+
			8|t   k  t|
			7|ppp  ppp|
			6|   q    |
			5|        |
			4|  Q     |
			3|        |
			2|PPP  PP |
			1|T   K  T|
			 +--------+
			  abcdefgh
		 * 
		 */
		assertFalse(cb.check(true));
		assertFalse(cb.check(false));
		board1[4][5]=board1[3][5];
		board1[3][5]=null;
		assertTrue(cb.check(true));
		assertFalse(cb.check(false));
		board1[4][5].setWhite(true);
		assertFalse(cb.check(true));
		assertTrue(cb.check(false));
		board1[0][4]= new Bishop(false,cb);
		/*
		  12345678
		 +--------+
		8|t   k  t|
		7|ppp  ppp|
		6|   Q    |
		5|b       |
		4|  Q     |
		3|        |
		2|PPP  PP |
		1|T   K  T|
		 +--------+
		  abcdefgh
	 * 
	 */
		assertTrue(cb.check(true));
		assertTrue(cb.check(false));
		
	}
	@Test
	public void testEnPassant() {
		Piece[][] board = new Piece[8][8];
		cb.setBoard(board);
		board[5][4] = new Pawn(true,cb);	// white Pawns
		board[2][3] = new Pawn(true,cb);
		board[1][1] = new Pawn(true,cb);
		board[3][6] = new Pawn(false,cb);//black Pawns
		board[4][6] = new Pawn(false,cb);
		assertEquals(0, cb.move(2, 3, 2, 4));//white0
		assertEquals(0, cb.move(3, 6, 3, 4));//black1
		assertEquals(0, cb.move(1, 1, 1, 3));//white2
		assertEquals(0, cb.move(4, 6, 4, 4));//black3
		assertEquals(-1, cb.move(2, 4, 3, 5));//white can't en passant
		assertEquals(1, cb.move(5, 4, 4, 5));//white can en passant4
		assertEquals(null, board[4][4]); //piece is taken
		assertTrue(cb.getRecord().getMove(4).isEnPassant());
		//now we reverse this
		cb.moveback();
		//System.out.println(cb.toString());
		assertEquals('p', board[4][4].getChar()); //piece is "untaken"
		assertEquals(4, cb.getRounds());
		assertEquals('P' , board[5][4].getChar());
		assertEquals(null , board[4][5]);
	}
	@Test
	public void testCastle() {
		cb= new Chessboard();
		Piece[][] board = cb.getBoard();
		board[1][7] = null;
		board[2][7] = null;
		board[5][7] = null;
		board[6][7] = null;
		assertEquals(0, cb.move(4, 1, 4, 3));//white, half-round 0
		assertEquals(0, cb.move(3, 6, 3, 4));
		assertEquals(0, cb.move(5, 0, 2, 3));
		assertEquals(0, cb.move(3, 7, 3, 5));
		assertEquals(0, cb.move(6, 0, 4, 1));//white, half-round 4
		assertEquals(0, cb.move(4, 6, 4, 4));
		assertEquals(2, cb.move(4, 0, 6, 0));//white castles in half-round 6
		assertEquals('T', cb.getBoard()[5][0].getChar());
		assertEquals('K', cb.getBoard()[6][0].getChar());
		assertEquals(null, cb.getBoard()[7][0]);
		//take the move back
		assertEquals(0, cb.moveback());
		assertEquals('T', cb.getBoard()[7][0].getChar());
		assertEquals('K', cb.getBoard()[4][0].getChar());
		assertEquals(null, cb.getBoard()[5][0]);
		assertEquals(null, cb.getBoard()[6][0]);
		//now check for queen's castle, white's move again
		assertEquals(0, cb.move(3, 1, 3, 2));
		assertEquals(0, cb.move(0, 6, 0, 5));
		assertEquals(0, cb.move(2, 0, 4, 2));
		assertEquals(0, cb.move(6, 6, 6, 5));
		assertEquals(0, cb.move(1, 0, 0, 2));
		assertEquals(0, cb.move(6, 5, 6, 4));
		assertEquals(-1, cb.move(4, 0, 2, 0)); // no castle possible, queen in the way!
		assertEquals(0, cb.move(3, 0, 3, 1));
		assertEquals(0, cb.move(6, 4, 6, 3));
		assertEquals(3, cb.move(4, 0, 2, 0));// white castles in half-round 13
		assertEquals('T', cb.getBoard()[3][0].getChar());
		assertEquals('K', cb.getBoard()[2][0].getChar());
		assertEquals(null, cb.getBoard()[0][0]);
		assertEquals(null, cb.getBoard()[1][0]);
		assertEquals(null, cb.getBoard()[5][0]);
		
		//now again the backmove
		assertEquals(0, cb.moveback());
		assertEquals('T', cb.getBoard()[0][0].getChar());
		assertEquals('K', cb.getBoard()[4][0].getChar());
		assertEquals(null, cb.getBoard()[1][0]);
		assertEquals(null, cb.getBoard()[2][0]);
		assertEquals(null, cb.getBoard()[3][0]);
		//move once with white again
		assertEquals(0, cb.move(1,1, 1, 2));
		//castle with black
		assertEquals(2, cb.move(4,7, 6, 7));
		assertEquals('t', cb.getBoard()[5][7].getChar());
		assertEquals('k', cb.getBoard()[6][7].getChar());
		assertEquals(null, cb.getBoard()[7][7]);
		assertEquals(null, cb.getBoard()[4][7]);
		assertEquals(0, cb.moveback());
		assertEquals('t', cb.getBoard()[0][7].getChar());
		assertEquals('k', cb.getBoard()[4][7].getChar());
		assertEquals(null, cb.getBoard()[5][7]);
		assertEquals(null, cb.getBoard()[6][7]);
		assertEquals(3, cb.move(4,7, 2, 7));
		assertEquals('t', cb.getBoard()[3][7].getChar());
		assertEquals('k', cb.getBoard()[2][7].getChar());
		assertEquals(null, cb.getBoard()[0][7]);
		assertEquals(null, cb.getBoard()[1][7]);
		assertEquals(null, cb.getBoard()[5][7]);
		assertEquals(0, cb.moveback());
		assertEquals('t', cb.getBoard()[0][7].getChar());
		assertEquals('k', cb.getBoard()[4][7].getChar());
		assertEquals(null, cb.getBoard()[1][7]);
		assertEquals(null, cb.getBoard()[2][7]);
		assertEquals(null, cb.getBoard()[3][7]);
		//Check that no castle is possible when fields are attacked
		cb.moveback();
		board[4][1] = new Tower(false, cb);
		//System.out.println(cb.toString());
		assertEquals(-1, cb.move(4, 0, 6, 0));
		assertEquals(-1, cb.move(4, 0, 2, 0));
		board[4][1] = null;
		board[5][1] = new Tower(false, cb);
		assertEquals(-1, cb.move(4, 0, 6, 0));
		board[5][1].setWhite(true);
		board[6][1] = new Tower(false, cb);
		assertEquals(-1, cb.move(4, 0, 6, 0));
		board[6][1].setWhite(true);
		assertEquals(2, cb.move(4, 0, 6, 0));
		cb.moveback();
		assertEquals('T', cb.getBoard()[7][0].getChar());
		assertEquals('K', cb.getBoard()[4][0].getChar());
		assertEquals(null, cb.getBoard()[5][0]);
		assertEquals(null, cb.getBoard()[6][0]);
		//------------------------------------------------------
		board[3][1] = new Tower(false, cb);
		assertEquals(-1, cb.move(4, 0, 2, 0));
		board[3][1].setWhite(true);
		board[2][1] = new Tower(false, cb);
		assertEquals(-1, cb.move(4, 0, 2, 0));
		board[2][1].setWhite(true);
		assertEquals(3, cb.move(4, 0, 2, 0));
		cb.moveback();
		assertEquals('T', cb.getBoard()[0][0].getChar());
		assertEquals('K', cb.getBoard()[4][0].getChar());
		assertEquals(null, cb.getBoard()[1][0]);
		assertEquals(null, cb.getBoard()[2][0]);
		assertEquals(null, cb.getBoard()[3][0]);
		assertEquals(0, cb.move(1,1, 1, 2));
		//and again for black
		board[4][6] = new Tower(true, cb);
		assertEquals(-1, cb.move(4, 7, 6, 7));
		assertEquals(-1, cb.move(4, 7, 2, 7));
		board[4][6] = null;
		board[5][6] = new Tower(true, cb);
		assertEquals(-1, cb.move(4, 7, 6, 7));
		board[5][6].setWhite(false);
		board[6][6] = new Tower(true, cb);
		assertEquals(-1, cb.move(4, 7, 6, 7));
		board[6][6].setWhite(false);
		assertEquals(2, cb.move(4, 7, 6, 7));
		cb.moveback();
		assertEquals('t', cb.getBoard()[7][7].getChar());
		assertEquals('k', cb.getBoard()[4][7].getChar());
		assertEquals(null, cb.getBoard()[5][7]);
		assertEquals(null, cb.getBoard()[6][7]);
		//------------------------------------------------------
		board[3][6] = new Tower(true, cb);
		assertEquals(-1, cb.move(4, 7, 2, 7));
		board[3][6].setWhite(false);
		board[2][6] = new Tower(true, cb);
		assertEquals(-1, cb.move(4, 7, 2, 7));
		board[2][6].setWhite(false);
		assertEquals(3, cb.move(4, 7, 2, 7));
		cb.moveback();
		assertEquals('t', cb.getBoard()[0][7].getChar());
		assertEquals('k', cb.getBoard()[4][7].getChar());
		assertEquals(null, cb.getBoard()[1][7]);
		assertEquals(null, cb.getBoard()[2][7]);
		assertEquals(null, cb.getBoard()[3][7]);
		
	}
}
