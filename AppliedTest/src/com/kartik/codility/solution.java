package com.kartik.codility;

public class solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=8;
		solveNQueens(n);
	}
	
	/* A utility function to print solution */
	static void printSolution(int board[][]) 
	{ 
		int N=board.length;
	    for (int i = 0; i < N; i++) 
	    { 
	        for (int j = 0; j < N; j++) 
	            System.out.printf("%2d ", board[i][j]); 
	        System.out.printf("\n"); 
	    } 
	} 
	  
	/* A Optimized function to check if a queen can 
	be placed on board[row][col] */
	static boolean isSafe(int row, int col, int slashCode[][], 
	            int backslashCode[][], boolean rowLookup[], 
	            boolean slashCodeLookup[], boolean backslashCodeLookup[] ) 
	{ 
	    if (slashCodeLookup[slashCode[row][col]] || 
	        backslashCodeLookup[backslashCode[row][col]] || 
	        rowLookup[row]) 
	    return false; 
	  
	    return true; 
	} 
	  
	/* A recursive utility function to solve N Queen problem */
	static boolean solveNQueensUtil(int board[][], int col, 
	    int slashCode[][], int backslashCode[][], boolean rowLookup[], 
	    boolean slashCodeLookup[], boolean backslashCodeLookup[] ) 
	{ 
		int N=board.length;
	    /* base case: If all queens are placed 
	    then return true */
	    if (col >= N) 
	        return true; 
	  
	    /* Consider this column and try placing 
	       this queen in all rows one by one */
	    for (int i = 0; i < N; i++) 
	    { 
	        /* Check if queen can be placed on 
	           board[i][col] */
	        if ( isSafe(i, col, slashCode, backslashCode, rowLookup, 
	                    slashCodeLookup, backslashCodeLookup) ) 
	        { 
	            /* Place this queen in board[i][col] */
	            board[i][col] = 1; 
	            rowLookup[i] = true; 
	            slashCodeLookup[slashCode[i][col]] = true; 
	            backslashCodeLookup[backslashCode[i][col]] = true; 
	  
	            /* recur to place rest of the queens */
	            if ( solveNQueensUtil(board, col + 1, slashCode, backslashCode, 
	                    rowLookup, slashCodeLookup, backslashCodeLookup) ) 
	                return true; 
	  
	            /* If placing queen in board[i][col] 
	            doesn't lead to a solution, then backtrack */
	  
	            /* Remove queen from board[i][col] */
	            board[i][col] = 0; 
	            rowLookup[i] = false; 
	            slashCodeLookup[slashCode[i][col]] = false; 
	            backslashCodeLookup[backslashCode[i][col]] = false; 
	        } 
	    } 
	  
	    /* If queen can not be place in any row in 
	        this colum col then return false */
	    return false; 
	} 
	  
	/* This function solves the N Queen problem using 
	Branch and Bound. It mainly uses solveNQueensUtil() to 
	solve the problem. It returns false if queens 
	cannot be placed, otherwise return true and 
	prints placement of queens in the form of 1s. 
	Please note that there may be more than one 
	solutions, this function prints one of the 
	feasible solutions.*/
	static boolean solveNQueens(int N) 
	{ 
	    int board[][]=new int[N][N]; 
	    memset(board, 0, N); 
	  
	    // helper matrices 
	    int slashCode[][]=new int [N][N]; 
	    int backslashCode[][]=new int [N][N];
	  
	    // arrays to tell us which rows are occupied 
	    boolean rowLookup[] = new boolean[N];//{false}; 
	  
	    //keep two arrays to tell us which diagonals are occupied 
	    boolean slashCodeLookup[] = new boolean[2*N - 1];
	    //{false}; 
	    boolean backslashCodeLookup[] =new boolean[2*N - 1];
	    //{false}; 
	  
	    // initalize helper matrices 
	    for (int r = 0; r < N; r++){ 
	        for (int c = 0; c < N; c++) {
	            slashCode[r][c] = r + c;
	            backslashCode[r][c] = r - c + 7; 
	        }
	    }
	    if (solveNQueensUtil(board, 0, slashCode, backslashCode, rowLookup, slashCodeLookup, backslashCodeLookup) == false ) 
	    { 
	        System.out.printf("Solution does not exist"); 
	        return false; 
	    } 
	  
	    // solution found 
	    printSolution(board); 
	    return true; 
	} 
	static void memset(int borad[][],int a,int size){
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++){
				borad[i][j]=0;
			}
		}
	}
	  
	
}
