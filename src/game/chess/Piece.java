package game.chess;

public abstract class Piece {
	protected boolean white;
	protected Chessboard board;
	public Piece(boolean white, Chessboard board) {
		super();
		this.white = white;
		this.board = board;
	}

	/**
	 * checks possiblity of a move and returns its type (en passant=1 castle2 or 3 regular move =0)
	 * 
	 * @param i x coordinate of target position
	 * @param j y coordinate of target position
	 * @return 0 if move is possible -1 if it is not
	 */
	abstract public int move(int posX, int posY, int i, int j);
	
	/**
	 *
	 * @return Type of the piece as a string
	 */
	abstract public String getType();
	/**
	 * 
	 * 
	 * @return a char representing the type of the piece
	 */
	abstract public char getChar();


	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}
}
