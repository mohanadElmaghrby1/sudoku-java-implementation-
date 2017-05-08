/**
 * sudoku puzzle game 
 */
package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author mohanad
 *
 */
public class Sudoku {

	static int sudoku[][];

	/**
	 * get the valid moves you can put in cell
	 * 
	 * @param row
	 *            : the cell row
	 * @param column
	 *            : the cell column
	 * @return : ArrayList of the valid moves
	 */
	static ArrayList<Integer> getAllMoves(int row, int column) {

		// create boolean array of the valid moves
		// initialize the array to be true
		boolean valid[] = new boolean[10];
		Arrays.fill(valid, true);

		// create ArrayList of the valid moves
		ArrayList<Integer> moves = new ArrayList<>();

		for (int i = 0; i < 9; ++i) {
			// check the horizontal valid moves
			valid[sudoku[row][i]] = false;

			// check the vertical valid moves
			valid[sudoku[i][column]] = false;

		}

		// check the valid moves in the 3*3 square
		// start the square from the top left cell
		row -= row % 3;
		column -= column % 3;

		for (int i = row; i < row + 3; ++i) {
			for (int j = column; j < column + 3; ++j) {
				valid[sudoku[i][j]] = false;
			}
		}

		// iterate for the valid moves and add it to the moves ArrayList
		for (int i = 1; i < 10; ++i)
			if (valid[i])
				moves.add(i);

		return moves;
	}

	/**
	 * check if the sudoku is full or not
	 * 
	 * @return : ture if full : false if not
	 */
	static boolean isFull() {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (sudoku[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	/**
	 * get the first empty cell
	 * 
	 * @return array of tow values the row and the column of the first empty
	 *         cell
	 */
	static int[] getFirstEmptyCell() {
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (sudoku[i][j] == 0) {
					int ans[] = { i, j };
					return ans;
				}

			}
		}
		return null;
	}

	static boolean solved=false;
	static void solve() {

		// check if the sudoku is already solved
		if (solved || isFull()){
			solved=true;
			return;
			
		}

		// get the first empty cell
		int cell[] = getFirstEmptyCell();
		int row = cell[0];
		int column = cell[1];

		// get all valid moves of this cell
		ArrayList<Integer> validMoves = getAllMoves(row, column);

		//try the moves if possible and backtrack if not
		for (int i = 0; validMoves != null && i < validMoves.size() &&!solved; ++i) {
			sudoku[row][column] = validMoves.get(i);
			solve();
		}

		//check if solved
		if (solved)
			return;
		//
		sudoku[row][column] = 0;
		

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		
		//zero indicate for empty cell
		sudoku=new int[9][9];
		for (int i = 0 ; i <9 ; ++i)
			for (int j = 0 ; j <9 ; ++j)
				sudoku[i][j]=scan.nextInt();
		System.out.println("********************************");
		solve();
		print();
	}
	
	
	static void print(){
		for (int a[]:sudoku){
			for (int s : a){
				System.out.print(s+" ");
			}
			System.out.println();
		}
	}

}
