package A4.Game;

import java.io.*;

public class Game {
	
	public static int play(InputStreamReader input){
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3; int player;
		
		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;
		
		while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
			player = nbTurn %2 + 1;
			if (player == 2){
				columnPlayed = getNextMove(keyboard, c, 2);
			}
			if (player == 1){
				columnPlayed = movePlayer1(columnPlayed, c);
			}
			System.out.println(columnPlayed);
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)){
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return(player);
			}
			nbTurn++;
		}
		return -1;
	}
	
	public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
		c.print();
		boolean isValidInput = false;
		int move =-1;

		while(!isValidInput){
			try{
				System.out.print("Enter the column number of your move:	");
				String temp = keyboard.readLine();
				move = Integer.parseInt(temp);
				if(move<0 || move>6) throw new IllegalArgumentException();
				else if(c.available[move]==6) throw new Exception();
				else{
					isValidInput=true;
				}
			}catch(IllegalArgumentException e){
				System.out.println("Invalid column number. Please try again: ");
			}catch(Exception e){
				System.out.println("That column is full. Please try again: ");
			}
		}
		return move;
	}
	
	public static int firstMovePlayer1 (){
		return 3;
	}
	
	public static int movePlayer1 (int columnPlayed2, Configuration c){
		if(c.canWinNextRound(1)!=-1){
			return c.canWinNextRound(1);
		}else if(c.canWinTwoTurns(1)!=-1){
			return c.canWinTwoTurns(1);
		}else{
			int x = -1;
			while(x<0){
				for(int i=1;i<7;i++){
					if(columnPlayed2-i>=0 && c.available[columnPlayed2-i]<6){
						x=columnPlayed2-i;
					}else if(columnPlayed2+i<=6 && c.available[columnPlayed2+i]<6){
						x=columnPlayed2+i;
					}
					if(x!=-1) break;
				}
			}
			return x;
		}
	}
	
}
