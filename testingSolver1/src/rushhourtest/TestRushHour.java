package rushhourtest;
import java.io.File;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import rushhour.Solver;


public class

TestRushHour
{

	public static int dirChar2Int(char d) {
		switch (d) {
		case 'U': {
			return RushHour.UP;
		}
		case 'D': {
			return RushHour.DOWN;
		}
		case 'L': {
			return RushHour.LEFT;
		}
		case 'R': {
			return RushHour.RIGHT;
		}
		default:
			throw new IllegalArgumentException("Unexpected direction: " + d);
		}
	}
	
	public static void testSolution(String puzzleName, String solName) {
		try {
			
			RushHour puzzle = new RushHour(puzzleName);
			
			Scanner scannerSolution = new Scanner(new File(solName));
			while (scannerSolution.hasNextLine()) {
				String line = scannerSolution.nextLine();
				
				if (line.length() != 3)
					throw new IllegalMoveException(line);
				puzzle.makeMove(line.charAt(0), dirChar2Int(line.charAt(1)), line.charAt(2) - '0');
			}
			
			if (puzzle.isSolved()) {
				System.out.println("Solved");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// if wanted to run at the level: ../Desktop/rushHourSolver/
	// compile with: javac -sourcepath testingSolver1/src testingSolver1/src/rushhourtest/TestRushHour.java
	// run with: java -cp testingSolver1/src rushhourtest.TestRushHour

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		String basePath = "../rushHourSolver/testingSolver1/src/";

		for (int i = 0; i < 36; i++) {

			String inputFile = ""; 
			String outputFile = "";

			if(i<10){

				inputFile = basePath + "A0" + i + ".txt"; 
				outputFile = basePath + "sol-A0" + i + ".txt";

			}
			else if (i < 11) { 
				inputFile = basePath + "A" + i + ".txt"; 
				outputFile = basePath + "sol-A" + i + ".txt";
			}else if (i < 21) { 
				inputFile = basePath + "B" + i + ".txt";
				outputFile = basePath + "sol-B" + i + ".txt"; 
			}else if (i < 30) { 
				inputFile = basePath + "C" + i + ".txt";
				outputFile = basePath + "sol-C" + i + ".txt"; 
			}else if (i < 36) { 
				inputFile = basePath + "D" + i + ".txt"; 
				outputFile = basePath + "sol-D" + i + ".txt"; 
			}


			Solver.solveFromFile(inputFile, outputFile); 
			
			testSolution(inputFile, outputFile);



		}

		//Solver.solveFromFile(puzzleName, "sol-"+puzzleName);
		//Solver.solveFromFile("/Users/thomaslui/Desktop/testingSolver1/src/"+"D30.txt","/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-A00");


		//testSolution("/Users/thomaslui/Desktop/testingSolver1/src/"+"D30.txt", "/Users/thomaslui/Desktop/testingSolver1/"+"filename5.txt");
	}
}