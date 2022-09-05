package rushhour;

import rushhourtest.BadBoardException;

import javax.swing.tree.DefaultTreeCellEditor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Solver
{
	public final static int SIZE = 6;


	public static void solveFromFile(String inputPath, String outputPath) throws FileNotFoundException {
		// TODO

		char board [][] = new char[SIZE][SIZE];
		int i,j;
		File file= new File(inputPath);
		Scanner reader = null;
		try {
			reader = new Scanner(file);
			for (i=0; i<6; i++) {
				String data = reader.nextLine();
				for (j=0; j<6; j++)
					board[i][j] = data.charAt(j);
			}
		}
		catch (FileNotFoundException exception) {
			throw exception;
		}
		catch (Exception e) {
			throw new BadBoardException(e);
		}
		finally {
			if(reader!=null)
				reader.close();
		}

		//have the board in char[6][6]
		//test to print
//		for (int k = 0; k < 6; k++) {
//			for (int l = 0; l < 6; l++) {
//				System.out.print(board[k][l]);
//
//			}
//			System.out.println();
//		}

		//check for bad board and if it's not then call the startsolver method and write all of the board instruction to a file with the returned stack
		if(!checkBadBoard(board)){
			//System.out.println("board is bad or not: "+checkBadBoard(board));

			Board rootBoard = new Board(board);
			//String tempboard =convertingRepBoard(board);
			RushHourGraph thegraph = new RushHourGraph(rootBoard);
			Stack<Vertex<Board>> results = thegraph.startingSolver(rootBoard);

			try {
				File myObj = new File(outputPath);
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					FileWriter mywriter = new FileWriter(outputPath);

					while(!results.isEmpty()){

						Vertex<Board> temp = results.pop();
						int direction = temp.getBoard().getDir();
						char name = temp.getBoard().getCarMovedName();
						int movedSteps = Math.abs(temp.getBoard().getNumbMovedSlot());

						String temp1 ="";

						if(direction ==1){
							//dir= down
							temp1 = name +"D"+ movedSteps;

						}else if(direction == 2){
							//dir= left
							temp1 = name +"L"+ movedSteps;
						}else if(direction == 3){
							//dir= right
							temp1 = name +"R"+ movedSteps;
						}else{
							//dir= up
							temp1 = name +"U"+ movedSteps;
						}

						mywriter.write(temp1);
						mywriter.write("\n");


					}

					mywriter.close();


				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}


		}


	}


	/**
	 * check for bad board if return true then it's a bad board else return false which
	 * @param board
	 * @return
	 */
	public static boolean checkBadBoard(char[][]board){ //throws Exception
		//isBadBoard initiated as not bad board
		//only checked for empty space for bad board
		boolean isBadBoard =false;

		for (int i = 0; i < 6; i++) {
			for (int a = 0; a < 6; a++) {
				//if (this.listofStrings.get(i).charAt(a) == ' ') {
				if (board[i][a] == ' ') {

					//throw new Exception();
					isBadBoard = true;
					return isBadBoard;
				}
			}
		}
		return isBadBoard;
	}



}
