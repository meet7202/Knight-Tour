import java.util.*;



public class Main {

	//A 1 on the board represents a visited location
	private static final int VISITED = 1;
	private static int value=1;
	//a 0 represents all other locations on the board
	private static final int UNVISITED_LOCATION = 0;

	private static int SIZE = 20;
	
	private int[][] board = new int[SIZE][SIZE];
	
	
	public int[][] test(int size,int x,int y){
		SIZE = size;
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; i < SIZE; i++){
				board[i][j] = UNVISITED_LOCATION;
			}	
		}
	//User Input Here
		
		nextMove(--x,--y);
		return board;
		
		
	}
	
	public boolean nextMove(int row, int column){
		
		board[row][column] = value;
		value=value+1;
		
		ArrayList<Location> nextMovePossibilities = possibleMoves(row,column);
		
		if(nextMovePossibilities.size() == 0){
			displayBoard();
			//System.out.println("Tour complete");
		}
		else{
		
		
		Location nextMove = findBest(nextMovePossibilities);	
		int nextRow = nextMove.getRow();
		int nextColumn = nextMove.getColumn();
		
		displayBoard();
		System.out.println();
		System.out.println();
		
		return nextMove(nextRow,nextColumn);
		}
		
		return true;
	}
	
	public ArrayList<Location> possibleMoves(int row, int column){
		
		ArrayList<Location> validMoves = new ArrayList<Location>();
		
		if( isValidMove(new Location(row+2, column +1)) ){
			validMoves.add(new Location(row+2, column +1));
		}
		
		if( isValidMove(new Location(row+2, column-1)) ){
			validMoves.add(new Location(row+2, column-1));
		}	
		
		if( isValidMove(new Location(row-2, column +1)) ){
			validMoves.add(new Location(row-2, column +1));
		}	
		
		if( isValidMove(new Location(row-2, column -1)) ){
			validMoves.add(new Location(row-2, column -1));
		}
		
		if( isValidMove(new Location(row+1, column +2)) ){
			validMoves.add(new Location(row+1, column +2));
		}	
			
		if( isValidMove(new Location(row+1, column -2)) ){
			validMoves.add(new Location(row+1, column -2));
		}	
		
		if( isValidMove(new Location(row-1, column +2)) ){
			validMoves.add(new Location(row-1, column +2));
		}	
		
		if( isValidMove(new Location(row-1, column -2)) ){
			validMoves.add(new Location(row-1, column-2));
		}	
		
		return validMoves;
	}
	
	public Location findBest(ArrayList<Location> locations){
		Location bestMove = null;
		
		int bestMoveScore = 64;	
		
		for(Location location : locations){
			
			ArrayList<Location> movesFromThisPos = possibleMoves(location.getRow(), location.getColumn());
			
			if(movesFromThisPos.size() < bestMoveScore){
				bestMove = new Location(location.getRow(), location.getColumn());
				bestMoveScore = movesFromThisPos.size();
			}
		}
		
		return bestMove;
	}
	
	public boolean isValidMove(Location loc){
		
		if(loc.getRow() >= SIZE || loc.getColumn() >= SIZE){
			return false;
		}
		
		if(loc.getRow() < 0 || loc.getColumn() < 0){
			return false;
		}
		
		if(board[loc.getRow()][loc.getColumn()] !=UNVISITED_LOCATION){
			return false;
		}
		
		//valid
		return true;
	}
	
	public void displayBoard(){
		
		//row
		for(int i = 0; i < SIZE; i++){
			
			//columns
			for(int j = 0; j < SIZE; j++){
				System.out.print(board[i][j]+" ");
			}
			
			System.out.println();
		}
	}
}