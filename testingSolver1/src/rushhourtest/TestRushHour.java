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
				//System.out.println("lmao "+line);
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

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		for (int i = 0; i < 36; i++) {

			if(i<10){
				Solver.solveFromFile("/Users/thomaslui/Desktop/testingSolver1/src/"+"A0"+i+".txt","/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"A0"+i+".txt");


				testSolution("/Users/thomaslui/Desktop/testingSolver1/src/"+"A0"+i+".txt", "/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"A0"+i+".txt");

			}
			if(i==10){
				Solver.solveFromFile("/Users/thomaslui/Desktop/testingSolver1/src/"+"A"+i+".txt","/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"A"+i+".txt");


				testSolution("/Users/thomaslui/Desktop/testingSolver1/src/"+"A"+i+".txt", "/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"A"+i+".txt");
			}
			if((i<=20)&&(i>=11)){

				Solver.solveFromFile("/Users/thomaslui/Desktop/testingSolver1/src/"+"B"+i+".txt","/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"B"+i+".txt");


				testSolution("/Users/thomaslui/Desktop/testingSolver1/src/"+"B"+i+".txt", "/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"B"+i+".txt");

			}
			if((i<=29)&&(i>=21)){
				Solver.solveFromFile("/Users/thomaslui/Desktop/testingSolver1/src/"+"C"+i+".txt","/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"C"+i+".txt");


				testSolution("/Users/thomaslui/Desktop/testingSolver1/src/"+"C"+i+".txt", "/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"C"+i+".txt");
			}
			if((i<=35)&&(i>=30)){
				Solver.solveFromFile("/Users/thomaslui/Desktop/testingSolver1/src/"+"D"+i+".txt","/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"D"+i+".txt");


				testSolution("/Users/thomaslui/Desktop/testingSolver1/src/"+"D"+i+".txt", "/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-"+"D"+i+".txt");

			}



		}

		//Solver.solveFromFile(puzzleName, "sol-"+puzzleName);
		//Solver.solveFromFile("/Users/thomaslui/Desktop/testingSolver1/src/"+"D30.txt","/Users/thomaslui/Desktop/testingSolver1/src/"+"sol-A00");


		//testSolution("/Users/thomaslui/Desktop/testingSolver1/src/"+"D30.txt", "/Users/thomaslui/Desktop/testingSolver1/"+"filename5.txt");
	}
}