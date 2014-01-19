package game.chess;

import java.util.ArrayList;

public class PartyRecord {
	ArrayList<Move> record;

	public PartyRecord() {
		super();
		this.record = new ArrayList<Move>();
	}

	/**
	 * 
	 * Checks whether in this party the specified colour can still castle
	 * 
	 * @param white
	 * @param kings
	 *            whether it's king's or queen's castle
	 * @return true if it is possible to caste, false otherwise
	 */
	public boolean CastleCheck(boolean white, boolean kings) {
		if (record.size() == 0)
			return true;
		// Has there been a check situation?
		int round = 0;
		char king;
		if (white)
			king = 'K';
		else
			king = 'k';
		// TODO check whether the respective rook has been moved!

		while (round < record.size() && !getMove(round).isUndone()) {
			if (getMove(round).isCheck())
				return false;
			if (getMove(round).getOrigFig() == king)
				return false;
			if (white) {
				if (kings)
					if (getMove(round).getOrigFig() == 'T'
							&& getMove(round).getOrigX() == 7)
						return false;
					else if (getMove(round).getOrigFig() == 'T'
							&& getMove(round).getOrigX() == 0)
						return false;
			} else {
				if (kings)
					if (getMove(round).getOrigFig() == 't'
							&& getMove(round).getOrigX() == 7)
						return false;
					else if (getMove(round).getOrigFig() == 't'
							&& getMove(round).getOrigX() == 0)
						return false;
			}
			round++;
		}
		return true;
	}

	/**
	 * adds Move m to the record
	 * 
	 * @param m
	 * @return true if successfully added
	 */
	public Move addMove(int i, Move m) {
		if(record.size()>i){
			return record.set(i, m);
		} else{
			record.add(m);
			return m;
		}
		
	}

	/**
	 * @param halfTurn
	 * @return the move in the specified halfTurn (one turn consists of two half
	 *         turns in which white and black move respectively
	 */
	public Move getMove(int halfTurn) {
		return record.get(halfTurn);
	}

	public PartyRecord(ArrayList<Move> history) {
		super();
		this.record = history;
	}

	public ArrayList<Move> getRecord() {
		return record;
	}

	public void setRecord(ArrayList<Move> record) {
		this.record = record;
	}

}
