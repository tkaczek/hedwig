package front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import game.Game;
import game.chess.Chessboard;
import game.chess.Move;

public class ConsoleUI {
 Chessboard cb;

public ConsoleUI(Chessboard cb) {
	super();
	this.cb = cb;
}
 public void play(){
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What do you want to play?");
		System.out.println("(0) for nothing\r(1) for standard Chess");
		
		String input;  

		
		try {
			input = br.readLine();
			if(input.compareTo("1")==0){
				Game g = new Game(cb);
				System.out.println("Welcome to Hedwig Chess!");
				boolean end = false;
				while(!end){
				System.out.println(g.getBoard().toString());
				if(g.getBoard().getRounds()%2 ==0){
					System.out.println("It's Player I's turn!");
				}else{
					System.out.println("It's Player II's turn!");
				}
				
				System.out.println("Enter your move (for example 'A1-B2'):");
				input = br.readLine();
				if(stringToMove(input)==999)
					end = true;
				}
				if((g.getBoard().getRounds()-1)%2 ==0){
					System.out.println("Player I won!");
				}else{
					System.out.println("Player II won!");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 
 }
 
 /**
  * This method takes a string coordinates as input and makes the corresponding move on the board
  * The String is supposed to have the form "a1-b2" or "A1-B2".
  * 
  * @param s a string with coordinates
  * @return a move code >=0 if successful -1 otherwise
  */
 public int stringToMove(String s){
	 char[] c = s.toCharArray();
	 if(s.length()!=5) {
		 System.out.println("Illegal User Input!");
		 return -1;
	 }
	 int x;
	 int y;
	 int dx;
	 int dy;
	 if(c[0] < 91){
		 x = c[0]-65;
	 }else{
		 x= c[0]-97;
	 }
	 y = c[1]-49;
	 if(c[3] < 91){
		 dx = c[0]-65;
	 }else{
		 dx= c[0]-97;
	 }
	 dy = c[4]-49;
	 return cb.move(x, y, dx, dy);
	 
 }
}
