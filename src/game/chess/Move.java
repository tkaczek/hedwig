package game.chess;

public class Move {
	
	int origX;
	int origY;
	char origFig; //piece that moves
	int destX;
	int destY;
	char destFig; // piece on the destination, '-' if no piece there
	boolean kingsCastle;
	boolean queensCastle;
	boolean enPassant;
	boolean check;
	boolean mate;
	boolean undone; //true if the move has been undone with the moveback function
	
	
	public boolean isKingsCastle() {
		return kingsCastle;
	}


	public void setKingsCastle(boolean kingsCastle) {
		this.kingsCastle = kingsCastle;
	}


	public boolean isQueensCastle() {
		return queensCastle;
	}


	public void setQueensCastle(boolean queensCastle) {
		this.queensCastle = queensCastle;
	}


	public boolean isEnPassant() {
		return enPassant;
	}


	public void setEnPassant(boolean enPassant) {
		this.enPassant = enPassant;
	}


	public boolean isUndone() {
		return undone;
	}


	public void setUndone(boolean undone) {
		this.undone = undone;
	}


	public Move(int origX, int origY, char origFig, int destX, int destY,
			char destFig,	boolean kingsCastle,	boolean queensCastle, boolean enPassant, boolean check, boolean mate) {
		super();
		this.origX = origX;
		this.origY = origY;
		this.origFig = origFig;
		this.destX = destX;
		this.destY = destY;
		this.destFig = destFig;
		this.kingsCastle = kingsCastle;
		this.queensCastle = queensCastle;
		this.enPassant = enPassant;
		this.check = check;
		this.mate = mate;
	}
	
	
/**
 * @return the standard algebraic chess notation for the move
 * 
 */
	public String toString(){
		String fig = "";
		String cap="";
		String dest="";
		String enPas="";
		String ischeck="";
		String ismate="";
		if(kingsCastle) return "0-0";
		if(queensCastle) return "0-0-0";
		if(origFig=='p'||origFig=='P'){
			fig="";
			fig+= (char) 97 + origX;
		}
		
		if(origFig=='t'||origFig=='T')
			fig="T";
		if(origFig=='b'||origFig=='B')
			fig="B";
		if(origFig=='n'||origFig=='N')
			fig="N";
		if(origFig=='k'||origFig=='K')
			fig="K";
		if(origFig=='q'||origFig=='Q')
			fig="Q";
		
		if(destFig!='-')
			cap+='x';
			
		dest+=(char) 97 + destX;
		dest+= destY;
		if(enPassant) enPas = "e.p.";
		if(check) ischeck ="+";
		if(mate) ismate = "#";
		return fig + cap + dest + enPas + ischeck + ismate;

	}
	
	public int getOrigX() {
		return origX;
	}
	public void setOrigX(int origX) {
		this.origX = origX;
	}
	public int getOrigY() {
		return origY;
	}
	public void setOrigY(int origY) {
		this.origY = origY;
	}
	public char getOrigFig() {
		return origFig;
	}
	public void setOrigFig(char origFig) {
		this.origFig = origFig;
	}
	public int getDestX() {
		return destX;
	}
	public void setDestX(int destX) {
		this.destX = destX;
	}
	public int getDestY() {
		return destY;
	}
	public void setDestY(int destY) {
		this.destY = destY;
	}
	public char getDestFig() {
		return destFig;
	}
	public void setDestFig(char destFig) {
		this.destFig = destFig;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public boolean isMate() {
		return mate;
	}
	public void setMate(boolean mate) {
		this.mate = mate;
	}
	

}
