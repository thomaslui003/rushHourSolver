package rushhour;

import java.util.*;

public class RushHourGraph{

    private int moves;
    //private Set<Vertex<Board>> vertices;
    private Board sourceBoard;
    //private Map<Vertex<Board>,Set<Vertex<Board>>> edges;
    private int value = 0;
    private int expandedVertex=0;
    private Set<Board>explored;
    //private Set<Integer>hashExplored;



    public RushHourGraph(Board sourceBoard){

        this.sourceBoard = sourceBoard;
        //create the first rootBoard
        Vertex<Board> rootBoard = new Vertex<Board>(sourceBoard,null,0);

        this.explored = new HashSet<Board>();
        //this.hashExplored= new HashSet<Integer>();

    }

    public Stack<Vertex<Board>> startingSolver (Board rootBoard) {

        boolean solved = false;

        //LinkedList<Integer> TESTqueue = new LinkedList<Integer>(); //for hashcode but never implemented

        LinkedList<Vertex<Board>> queue = new LinkedList<Vertex<Board>>();

        //Vertex<Board> root = new Vertex<Board>(rootBoard,null,0);
        //int testingHashroot = root.hashCode();

        //TESTqueue.addLast(testingHashroot);

        queue.addLast(new Vertex<Board>(rootBoard, null, 0));

        Vertex<Board> currentVertex = null;
        while (!queue.isEmpty()) {

            currentVertex = queue.removeFirst();

            //if the vertex is already in the vertices hash set then skip this round
            if (explored.contains(currentVertex.getBoard())) continue;

            //if(hashExplored.contains(currentVertex.hashCode())) continue;

            //if vertex is at the goal aka solved the board then break the loop
            if (currentVertex.getBoard().CarAtGoal()) break;

            //hashExplored.add(currentVertex.hashCode());
            explored.add(currentVertex.getBoard());

            Set<Board> NeighVertex = currentVertex.getBoard().neighbours();

            for (Board a : NeighVertex) {

                if (!explored.contains(a)) {
                    //System.out.println("lmao: " + value);
                    this.value++;
                    queue.addLast(new Vertex<Board>(a, currentVertex, currentVertex.getMoves()));

                }

            }


        }

        if (queue.isEmpty()) {
            System.out.println("unable to solve the board");
            solved = false;
            //return; here?
            return null;
        }

        Vertex<Board> result = currentVertex;
        Stack<Vertex<Board>> listInstr = new Stack<Vertex<Board>>();

        while (result != null) {

            listInstr.push(result);
            moves++;
            result = result.getParent();

        }
        solved = true;

        //popped the first vertex since it doesnt have any data
        Vertex<Board> uselessNode = listInstr.pop();


        //tester for the stack
//        while(!listInstr.isEmpty()){
//
//            int s = listInstr.pop().getBoard().getDir();
//            System.out.println("car name moved:  "+ s);
//
//        }


        return listInstr;
    }

        //original draft approach
        //listOfCarNames = NumberofCars(sourceBoard);
       // converting = convertingData(listOfCarNames,sourceBoard);

        //int numberCars = NumberofCars(sourceBoard);


        //need to create new vertex(state of board or each change of board) based on the available move of each car
        //need to make a function that create all available move vertex board
            //check how many cars are in each board (first check the source board)
            //record each car properties:
                //          this.name = name;
                //			this.dir = dir;
                //			this.topLeftX = x;
                //			this.topLeftY = y;
                //			this.length = len;

        //function check if it's equal to a previous created vertex (maybe through hashcode) for now just evaluate the vertex properties?
                //if it's equal --> true then dont create the vertex board
                //else if not equal --> create the vertex board with the new properties

        //function to convert the properties?


        //function for checking if it's equal to target

//        for(int x = 0; x<n; x++){
//            Vertex<Integer> newVertex = new Vertex<Integer>(x);
//            boolean newNode = vertices.add(newVertex);
//            if (newNode)
//                edges.put(newVertex, new HashSet<Vertex<Integer>>());
//        }

    public int moves(){

        return moves;
    }
    public int getExpandedVertex(){

        return expandedVertex;
    }


}
// not used code
//    public ArrayList<String> convertingData (ArrayList<Character>listName,char[][] board){
//
//        int tempNumbCars = listName.size();
//
//        ArrayList<String> temp = new ArrayList<String>();
//       // String pos = "";
//
//        for(char carName: listName){
//           // System.out.println(carName);
//            int count = 0;
//            String pos = ""+ carName;
//
//            for (int i = 0; i < 6; i++) {
//                for (int j = 0; j < 6; j++) {
//
//                    if(board[i][j]== carName){
//                        count++;
//
//                    }
//
//                }
//            }
//            pos = pos + count;
//            temp.add(pos);
//
//        }
//
//        for(String a : temp) {
//            System.out.println(a);
//        }
//
//        return null;
//    }    //check how many cars in the source board
////    public ArrayList<Character> NumberofCars (char[][] sourceBoard){
////
////
////        ArrayList<Character> thelist = new ArrayList<Character>();
////
////        for (int i = 0; i < 6; i++) {
////            for (int j = 0; j < 6; j++) {
////
////                if(!thelist.contains(sourceBoard[i][j])&&sourceBoard[i][j]!='.'){
////                    thelist.add(sourceBoard[i][j]);
////                }
////            }
////        }
////
////        int numDifferentCars = thelist.size();
////
////        System.out.println(thelist.size());
////
////        return thelist;
////    }