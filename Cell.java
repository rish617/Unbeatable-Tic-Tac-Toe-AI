/**
 * This is the Cell class which represents an individual cell of a Tic Tac Toe game.
 * @author Rishikesh Pisharody
 */
public class Cell{
    /**
     * status (How the cell on the grid is marked [X, O, or empty]).
     */
    private String status;

    /**
     * This is a constructor which sets the status of the cell.
     * @param status String
     */
    public Cell(String status){
        this.status = status;
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

}