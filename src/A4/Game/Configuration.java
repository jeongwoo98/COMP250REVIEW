package A4.Game;

public class Configuration {
	
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}
	
	public void addDisk (int index, int player){
		board[index][available[index]] = player;
		available[index] += 1;
		int x=0;
		for(int space : available){
			x+=space;
		}
		if(x<42) spaceLeft = true;
		else spaceLeft = false;
	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		int column = lastColumnPlayed;
		int row = available[lastColumnPlayed]-1;

		/*------------------------------Check Vertical-------------------------------*/
		boolean vertical;
		int verticalCounter =1;
		for(int i=1;i<4;i++){
			if(row-i<0) break;
			else if(board[column][row-i]==player){
				verticalCounter++;
			}
		}
		if(verticalCounter==4) vertical=true;
		else vertical = false;

		/*------------------------------Check Horizontal-------------------------------*/
		boolean horizontal;
		int horizontalCounter=1;

		//Check left-side
		for(int i=1;i<4;i++){
			if(column-i<0) break;
			else if(board[column-i][row]==0 || board[column-i][row]!=player) break;
			else if(board[column-i][row]==player){
				horizontalCounter++;
			}
		}
		if(horizontalCounter==4) horizontal=true;
		else{
			//Check right-side
			for(int i=1;i<4;i++){
				if(column+i>6) break;
				else if(board[column+i][row]==0 ||board[column+i][row]!=player) break;
				else if(board[column+i][row]==player){
					horizontalCounter++;
				}
			}
			if(horizontalCounter>=4) horizontal=true;
			else horizontal=false;
		}

		/*------------------------------Check Diagonal-------------------------------*/
		//Check first diagonal
		boolean diagonal1;
		int diagonalCounter1=1;

		for(int i=1; i<4; i++){
			if(column-i<0 || row-i<0) break;
			else if(board[column-i][row-i]==0 ||board[column-i][row-i]!=player) break;
			else if(board[column-i][row-i]==player) diagonalCounter1++;
		}
		if(diagonalCounter1==4) diagonal1=true;
		else{
			for(int i=1; i<4; i++){
				if(column+i>6 || row+i>5) break;
				else if(board[column+i][row+i]==0 ||board[column+i][row+i]!=player) break;
				else if(board[column+i][row+i]==player) diagonalCounter1++;
			}
			if(diagonalCounter1>=4) diagonal1=true;
			else diagonal1=false;
		}

		//Check second diagonal
		boolean diagonal2;
		int diagonalCounter2=1;

		for(int i=1;i<4;i++){
			if(column-i<0 || row+i>5) break;
			else if(board[column-i][row+i]==0||board[column-i][row+i]!=player) break;
			else if(board[column-i][row+i]==player) diagonalCounter2++;
		}
		if(diagonalCounter2==4) diagonal2=true;
		else{
			for(int i=1;i<4;i++){
				if(column+i>6 || row-i<0) break;
				else if(board[column+i][row-i]==0||board[column+i][row-i]!=player) break;
				else if(board[column+i][row-i]==player) diagonalCounter2++;
			}
			if(diagonalCounter2>=4) diagonal2=true;
			else diagonal2=false;
		}
		boolean diagonal = (diagonal1||diagonal2);

		return (vertical||horizontal||diagonal); // DON'T FORGET TO CHANGE THE RETURN
	}
	
	public int canWinNextRound (int player){
		if(!spaceLeft) return -1;
		//Try simulating the move in the columns
		for(int i=0;i<7;i++){
			if(available[i]<6){
				addDisk(i, player);
				if(isWinning(i,player)){
					board[i][available[i]-1] = 0;
					available[i] -= 1;
					return i;
				}else{
					board[i][available[i]-1] = 0;
					available[i] -= 1;
				}
			}
		}
		return -1;
	}
	
	public int canWinTwoTurns (int player){
		int player2;
		if(player==1) player2=2;
		else player2=1;

		int column = -1;
		for(int i=0; i<6; i++){
			if(available[i]<6){
				addDisk(i,player);
				//If player can win in next turn:
				if(canWinNextRound(player)!=-1){
					int temp = canWinNextRound(player);
					addDisk(temp,player2);
					if(canWinNextRound(player)!=-1){
						column=canWinNextRound(player);
					}
					board[temp][available[temp]-1] = 0;
					available[temp] -= 1;
				}
				board[i][available[i]-1] = 0;
				available[i] -= 1;
				if(column!=-1) break;
			}
		}
		return column;
	}
	
}
