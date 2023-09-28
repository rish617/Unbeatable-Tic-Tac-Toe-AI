/**
 * This is the Board class which represents the board of a Tic Tac Toe game.
 * @author Rishikesh Pisharody
 */
public class Board{
    /**
     * table (Table of cells on board).
     */
    private Cell[][] table;

    /**
     * This is a default constructor which initializes the 3x3 cell grid of the board.
     */
    public Board(){
        this.table = new Cell[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                table[i][j] = new Cell("");
            }
        } //Loop to initialize cells for every spot on the table
    }

    
    /** 
     * Returns the cell grid of the board.
     * @return Cell[][]
     */
    public Cell[][] getTable(){
        return table;
    }

    /**
     * Returns the cell provided its row and column.
     * @param row int
     * @param col int
     * @return Cell
     */
    public Cell getCell(int row, int col){
        return table[row][col];
    }

    /**
     * Checks whether any player has gotten 3 cells in a row on the board.
     * @return boolean
     */
    public boolean checkWinCondition(){
        
        for(int i = 0; i < 3; i++){
            if(table[i][0].getStatus().equals(table[i][1].getStatus()) && table[i][0].getStatus().equals(table[i][2].getStatus()) && table[i][0].getStatus().equals("") == false){
                return true;
            } //Checks for 3 in a row in the vertical direction
        }

        for(int j = 0; j < 3; j++){
            if(table[0][j].getStatus().equals(table[1][j].getStatus()) && table[0][j].getStatus().equals(table[2][j].getStatus()) && table[0][j].getStatus().equals("") == false){
                return true;
            } //Checks for 3 in a row in the horizontal direction
        }

        if(table[0][0].getStatus().equals(table[1][1].getStatus()) && table[0][0].getStatus().equals(table[2][2].getStatus()) && table[0][0].getStatus().equals("") == false){
            return true;
        } //Checks for 3 in a row in the left to right diagonal
        
        if(table[0][2].getStatus().equals(table[1][1].getStatus()) && table[0][2].getStatus().equals(table[2][0].getStatus()) && table[0][2].getStatus().equals("") == false){
            return true;
        } //Checks for 3 in a row in the right to left diagonal
        return false;
    }
    
    /**
     * Checks if all cells are marked while a win condition has not been achieved.
     * @return boolean
     */
    public boolean checkDrawCondition(){
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(table[r][c].getStatus().equals("")){
                    return false;
                }
            }
        } //If a single cell in the grid is empty return false
        return true;
    }

    /**
     * Determines if the move a player made is valid dependent on if they are 
     * within bounds and also if they're selecting an empty cell.
     * @param row int
     * @param col int
     * @return boolean
     */
    public boolean isValidMove(int row, int col){
        if(row < 0 || row >= table.length || col < 0 || col >= table[0].length){
            return false;
        }
        return table[row][col].getStatus().equals("");
    }

    /**
     * Makes move and sets the player status of the selected row and column 
     * if move is valid.
     * @param row int
     * @param col int
     * @param symbol String
     * @return boolean
     */
    public boolean makeMove(int row, int col, String symbol){
        if(isValidMove(row, col)){
            table[row][col].setStatus(symbol);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Undos a move if the cell is not empty (Method is only used when the minimax algorithm is in effect).
     * @param row int
     * @param col int
     */
    public void undoMove(int row, int col){
        if(/*isValidMove(row, col)*/ /*&&*/ table[row][col].getStatus().equals("") == false){
            table[row][col].setStatus("");
        }
    }

}