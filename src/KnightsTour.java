public class KnightsTour {

	public static int N = 20;
	
	int[][] chessBoardArray = new int[N][N];
	int[] xKnightMoves = { 2, 1, -1, -2, -2, -1, 1, 2 };
	int[] yKnightMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };
	int[][] chessBoardArray2 = new int[N][N];
	
	/*
	 * <pre>
	 * 0 59 38 33 30 17  8 63
 	 * 37 34 31 60  9 62 29 16
 	 * 58  1 36 39 32 27 18  7
	 * 35 48 41 26 61 10 15 28
	 * 42 57  2 49 40 23  6 19
	 * 47 50 45 54 25 20 11 14
 	 * 56 43 52  3 22 13 24  5
 	 * 51 46 55 44 53  4 21 12
 	 * </pre>
	 */
	
    
	public int[][] run(int n, int x,int y) {
		
		Main tour2 = new Main();
        chessBoardArray2 = tour2.test(n,x,y);        
        return chessBoardArray2;
	}


	
	public KnightsTour() {
		initialize_board();
        chessBoardArray[0][0] = 0;
	}
	
//	public int[][] run(boolean isSilent,int boardSize) {
//		N = boardSize;
//		if (walk_board(0, 0, 1) == false) {
//			if (!isSilent) {
//				System.out.println("No solution. :(");
//			}
//		} else {
//			if (!isSilent) {
//				print_board();
//			}
//		}
//		return chessBoardArray;
//	}

	public boolean can_move(int x, int y) {
		return ((x >= 0) && (x < N) 
				&& (y >= 0) && (y < N) 
				&& (chessBoardArray[x][y] == -1));
	}

	public boolean walk_board(int x, int y, int m) {
		int next_x;
		int next_y;

		if (m == N * N) {
			return true;
		}

		for (int i = 0; i < 8; i++) {
			next_x = x + xKnightMoves[i];
			next_y = y + yKnightMoves[i];
			if (can_move(next_x, next_y)) {
				chessBoardArray[next_x][next_y] = m;
				if (walk_board(next_x, next_y, m + 1) == true) {
					return true;
				} else {
					chessBoardArray[next_x][next_y] = -1;
				}
			}
		}
		return false;
	}

	public void print_board() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d", chessBoardArray[i][j]);
			}
			System.out.println();
		}
	}

	public void initialize_board() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				chessBoardArray[i][j] = -1;
			}
		}
	}
}
