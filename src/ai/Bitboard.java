package ai;

public class Bitboard {
	Long whitePawns;
	Long whiteRooks;
	Long whiteKnights;	
	Long whiteBishops;
	Long whiteKing;
	Long whiteQueens;
	Long blackPawns;
	Long blackRooks;
	Long blackKnights;	
	Long blackBishops;
	Long blackKing;
	Long blackQueens;
	
	/**
	 * 
	 * @return the String representation of the board where uppercase 
	 * letters indicate that the respective piece is white and lowercase letters that 
	 * the piece is black. p is for pawn, r for rook, n for knight, 
	 * b for bishop, k for king and q for queen
	 */
	public String toString(){
		StringBuffer board = new StringBuffer();
		for(int i=0;i<8; i++){
			for(int j=0; j < 8; j++){
				if((whitePawns & generatePosition(j,i)) !=0L)
					board.append('P');
				else if((blackPawns & generatePosition(j,i)) !=0L)
					board.append('p');
				else if((whiteKnights & generatePosition(j,i)) !=0L)
					board.append('N');
				else if((blackKnights & generatePosition(j,i)) !=0L)
					board.append('n');
				else if((whiteRooks & generatePosition(j,i)) !=0L)
					board.append('R');
				else if((blackRooks & generatePosition(j,i)) !=0L)
					board.append('r');
				else if((whiteBishops & generatePosition(j,i)) !=0L)
					board.append('B');
				else if((blackBishops & generatePosition(j,i)) !=0L)
					board.append('b');
				else if((whiteKing & generatePosition(j,i)) !=0L)
					board.append('K');
				else if((blackKing & generatePosition(j,i)) !=0L)
					board.append('k');
				else if((whiteQueens & generatePosition(j,i)) !=0L)
					board.append('Q');
				else if((blackQueens & generatePosition(j,i)) !=0L)
					board.append('q');
				else board.append(' ');
			}
			board.append('\n');
		}
		return board.toString();
	}

	
	public Bitboard(){
		whitePawns =  0xFF00L;
		blackPawns =  (0xFFL) << 48;
		whiteRooks =  0x81L;
		blackRooks =  (0x81L) << 56;
		whiteBishops = 0x24L;
		blackBishops =  (0x24L) << 56;
		whiteKnights =  0x42L;
		blackKnights =  (0x42L) << 56;
		whiteQueens = 80L;
		blackQueens =  (80L) << 56;
		whiteKing =  8L;
		blackKing = (8L) << 56;
	}
	
	/**
	 * @return the whitePawns
	 */
	public Long getWhitePawns() {
		return whitePawns;
	}


	/**
	 * @param whitePawns the whitePawns to set
	 */
	public void setWhitePawns(Long whitePawns) {
		this.whitePawns = whitePawns;
	}


	/**
	 * @return the whiteRooks
	 */
	public Long getWhiteRooks() {
		return whiteRooks;
	}


	/**
	 * @param whiteRooks the whiteRooks to set
	 */
	public void setWhiteRooks(Long whiteRooks) {
		this.whiteRooks = whiteRooks;
	}


	/**
	 * @return the whiteKnights
	 */
	public Long getWhiteKnights() {
		return whiteKnights;
	}


	/**
	 * @param whiteKnights the whiteKnights to set
	 */
	public void setWhiteKnights(Long whiteKnights) {
		this.whiteKnights = whiteKnights;
	}


	/**
	 * @return the whiteBishops
	 */
	public Long getWhiteBishops() {
		return whiteBishops;
	}


	/**
	 * @param whiteBishops the whiteBishops to set
	 */
	public void setWhiteBishops(Long whiteBishops) {
		this.whiteBishops = whiteBishops;
	}


	/**
	 * @return the whiteKing
	 */
	public Long getWhiteKing() {
		return whiteKing;
	}


	/**
	 * @param whiteKing the whiteKing to set
	 */
	public void setWhiteKing(Long whiteKing) {
		this.whiteKing = whiteKing;
	}


	/**
	 * @return the whiteQueens
	 */
	public Long getWhiteQueens() {
		return whiteQueens;
	}


	/**
	 * @param whiteQueens the whiteQueens to set
	 */
	public void setWhiteQueens(Long whiteQueens) {
		this.whiteQueens = whiteQueens;
	}


	/**
	 * @return the blackPawns
	 */
	public Long getBlackPawns() {
		return blackPawns;
	}


	/**
	 * @param blackPawns the blackPawns to set
	 */
	public void setBlackPawns(Long blackPawns) {
		this.blackPawns = blackPawns;
	}


	/**
	 * @return the blackRooks
	 */
	public Long getBlackRooks() {
		return blackRooks;
	}


	/**
	 * @param blackRooks the blackRooks to set
	 */
	public void setBlackRooks(Long blackRooks) {
		this.blackRooks = blackRooks;
	}


	/**
	 * @return the blackKnights
	 */
	public Long getBlackKnights() {
		return blackKnights;
	}


	/**
	 * @param blackKnights the blackKnights to set
	 */
	public void setBlackKnights(Long blackKnights) {
		this.blackKnights = blackKnights;
	}


	/**
	 * @return the blackBishops
	 */
	public Long getBlackBishops() {
		return blackBishops;
	}


	/**
	 * @param blackBishops the blackBishops to set
	 */
	public void setBlackBishops(Long blackBishops) {
		this.blackBishops = blackBishops;
	}


	/**
	 * @return the blackKing
	 */
	public Long getBlackKing() {
		return blackKing;
	}


	/**
	 * @param blackKing the blackKing to set
	 */
	public void setBlackKing(Long blackKing) {
		this.blackKing = blackKing;
	}


	/**
	 * @return the blackQueens
	 */
	public Long getBlackQueens() {
		return blackQueens;
	}


	/**
	 * @param blackQueens the blackQueens to set
	 */
	public void setBlackQueens(Long blackQueens) {
		this.blackQueens = blackQueens;
	}

	/**
	 * @param x the x coordinate on the board
	 * @param y	the y coordinate on the board
	 * @return the long value representing the given coordinate on the chessboard
	 */
	public long generatePosition(int x, int y){
		return 1L << ((7-x)+ 8*(7-y));
	}
}
