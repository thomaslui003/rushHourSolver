package rushhour;

import java.util.ArrayList;
import java.util.*;

public class Vertex<Board> {

    private Board board;
    private Vertex<Board> parent;
    private int hash;
    private int moves;

    public Vertex(Board b, Vertex<Board> parent, int moves){
        this.board = b;
        this.moves = moves;
        this.parent = parent;
        hash = computeHashcode();

    }
    //not used as hashcode was not implemented
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return moves == vertex.moves && Objects.equals(board, vertex.board) && Objects.equals(parent, vertex.parent);
    }

    @Override
    public int hashCode() {
        return hash;
    }

    public int computeHashcode (){
        return Objects.hash(board, parent, moves);
    }


    public Board getBoard (){
        return board;
    }

    public int getMoves (){
        return moves;
    }

    //setting parent vertex
    public void setParent(Vertex<Board> parent) {
        this.parent = parent;
    }

    public Vertex<Board> getParent() {
        return parent;
    }



}
