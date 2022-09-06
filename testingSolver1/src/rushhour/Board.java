package rushhour;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Board {

    private String newBoardDetails;
    char carMovedName;
    int numbMovedSlot = 0;
    private int dir;
    private int hash;


    public Board (char [][]board){

        String temp = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                temp = temp + board[i][j];

            }
        }

        this.newBoardDetails = temp;

    }

    public Board (char [][]board,char CarName,int Numbmoves,int dir){

        //changed from storing char[][] to storing string in board obj
        String temp = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                temp = temp + board[i][j];

            }
        }

        this.newBoardDetails = temp;
       this.numbMovedSlot = Numbmoves;
       this.carMovedName = CarName;
       this.dir = dir;
       hash = computingHashes();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return carMovedName == board.carMovedName && numbMovedSlot == board.numbMovedSlot && dir == board.dir && Objects.equals(newBoardDetails, board.newBoardDetails);
    }

    @Override
    public int hashCode() {
        return hash;
    }

    public int computingHashes(){

        return Objects.hash(newBoardDetails, carMovedName, numbMovedSlot, dir);

    }

    public int getNumbMovedSlot() {
        return this.numbMovedSlot;
    }

    public int getDir(){
        return this.dir;
    }

    public char getCarMovedName(){
        return this.carMovedName;
    }


    public String getNewBoardDetails(){
        return this.newBoardDetails;
    }


    /**
     * checks if the board is at the target (the car reached the exit point)
     * @return
     */
    public boolean CarAtGoal(){

        String temp = this.newBoardDetails;
        char [][] theboard = new char[6][6];

        int count = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                theboard[i][j] = temp.charAt(count);
                count++;
            }
        }

        //if these two spot has X then the board is at the target
        if(theboard[2][4]=='X'&&theboard[2][5]=='X'){

            //System.out.println("Board is at Goal");
            return true;
        }else{

            return false;
        }

    }

    /**
     * simple swap method
     * @param array
     * @param row1
     * @param row2
     * @param col1
     * @param col2
     */
    private void swap(char[][] array, int row1, int row2, int col1, int col2) {
        char temp = array[row1][col1];
        array[row1][col1] = array[row2][col2];
        array[row2][col2] = temp;
    }

    /**
     * method of cloning a board (it takes the string board details and create a board type char [][] and return it)
     * @return type char[][] cloned board
     */
    public char[][] cloneBoard() {

        String temp = newBoardDetails;
        char[][] array = new char[6][6];

        int count = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                array[i][j] = temp.charAt(count);
                count++;
            }
        }

        return array;
    }

    /**
     * generating all of the possible neighbouring board based on the current vertex board
     * each iteration through the board has rules to ensure that a valid board is created and added to the neighbours hash set of boards
     * @return the hash set of board (all created based on the current vertex board)
     */
    public Set<Board> neighbours (){

        Set<Board> neighbours = new HashSet<Board>();

        int tempi = 0, tempj = 0;

        char[][] tempBoard = cloneBoard();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                if (tempBoard[i][j] == '.') {

                    //car moving downward (vertical)
                    if (i > 0 && tempBoard[i-1][j] != '.') {
                        char theCarName = tempBoard[i-1][j];
                        if (i > 1 && theCarName == tempBoard[i-2][j]) {
                            tempi = i - 1;
                            while (tempi < 6 - 1 && tempBoard[tempi+1][j] == '.') {
                                int tempw = tempi - 1;
                                while (tempw > 0 && tempBoard[tempw-1][j] == theCarName)
                                    tempw--;
                                swap(tempBoard, tempw, ++tempi, j, j);
                                Board b = new Board(tempBoard, theCarName, tempi - i + 1,1);
                                neighbours.add(b);
                            }
                        }

                        tempBoard = cloneBoard();//cloning it since it doesnt want to create another board that based off the altered board
                    }

                    //car moving right (horizontal)
                    if (j > 0 && tempBoard[i][j-1] != '.') {
                        char theCarName = tempBoard[i][j-1];
                        if (j > 1 && theCarName == tempBoard[i][j-2]) {
                            tempj = j - 1;
                            while (tempj < 6 - 1 && tempBoard[i][tempj+1] == '.') {
                                int tempw = tempj - 1;
                                while (tempw > 0 && tempBoard[i][tempw-1] == theCarName)
                                    tempw--;
                                swap(tempBoard, i, i, tempw, ++tempj);
                                Board b = new Board(tempBoard, theCarName, tempj - j + 1,3);
                                neighbours.add(b);
                            }
                        }

                        tempBoard = cloneBoard();//cloning it since it doesnt want to create another board that based off the altered board
                    }

                    //car moving upward (vertical)
                    if (i < 6 - 1 && tempBoard[i+1][j] != '.') {
                        char theCarName = tempBoard[i+1][j];
                        if (i < 6 - 2 && theCarName == tempBoard[i+2][j]) {
                            tempi = i + 1;
                            while (tempi > 0 && tempBoard[tempi-1][j] == '.') {
                                int tempw = tempi + 1;
                                while (tempw < 6 - 1 && tempBoard[tempw+1][j] == theCarName)
                                    tempw++;
                                swap(tempBoard, tempw, --tempi, j, j);
                                Board b = new Board(tempBoard, theCarName, tempi - i - 1,0);
                                neighbours.add(b);
                            }
                        }
                        tempBoard = cloneBoard(); //cloning it since it doesnt want to create another board that based off the altered board
                    }

                    //car moving left (horizontal)
                    if (j < 6 - 1 && tempBoard[i][j+1] != '.') {
                        char theCarName = tempBoard[i][j+1];
                        if (j < 6 - 2 && theCarName == tempBoard[i][j+2]) {
                            tempj = j + 1;
                            while (tempj > 0 && tempBoard[i][tempj-1] == '.') {
                                int tempw = tempj + 1;
                                while (tempw < 6 - 1 && tempBoard[i][tempw+1] == theCarName)
                                    tempw++;
                                swap(tempBoard, i, i, tempw, --tempj);
                                Board b = new Board(tempBoard, theCarName, tempj -j - 1,2);
                                neighbours.add(b);
                            }
                        }
                        tempBoard = cloneBoard();//cloning it since it doesnt want to create another board that based off the altered board
                    }
                }

            }
        }

        return neighbours;
    }



}
