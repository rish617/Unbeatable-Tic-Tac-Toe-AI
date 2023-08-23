/**
 * This is the Cell class which represents an individual cell of a Tic Tac Toe game.
 * @author Rishikesh Pisharody
 */
public class Cell{
    /**
     * row (Row in 3x3 grid cell is located in).
     */
    private int row;
    /**
     * col (Column in 3x3 grid cell is located in).
     */
    private int col;
    /**
     * status (How the cell on the grid is marked [X, O, or empty]).
     */
    private String status;

    /**
     * This is a constructor which sets the row, column, and status of the cell.
     * @param row int
     * @param col int
     * @param status String
     */
    public Cell(int row, int col, String status){
        this.row = row;
        this.col = col;
        this.status = status;
    }

    
    /** 
     * Returns the row of the cell.
     * @return int
     */
    public int getRow(){
        return row;
    }

    /**
     * Sets the row of the cell.
     * @param row int
     */
    public void setRow(int row){
        this.row = row;
    }

    /**
     * Returns the column of the cell.
     * @return int
     */
    public int getCol(){
        return col;
    }

    /**
     * Sets the column of the cell.
     * @param col int
     */
    public void setCol(int col){
        this.col = col;
    }

    /**
     * Returns the status of the cell.
     * @return String
     */
    public String getStatus(){
        return status;
    }

    /**
     * Sets the status of the cell.
     * @param status String
     */
    public void setStatus(String status){
        this.status = status;
    }

    //@Override
    /*public boolean equals(Object o){
        if(o instanceof Cell){

        }
        else{
            return false;
        }
    }*/
}