import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the TicTacToeGUI class which contains the GUI and mechanics of the Tic Tac Toe game.
 * @author Rishikesh Pisharody
 */
public class TicTacToeGUI extends JFrame{
    /**
     * buttons (2D Array of Buttons on the 3x3 grid).
     */
    private JButton[][] buttons;
    /**
     * board (The board object which is used to play the Tic Tac Toe game).
     */
    private Board board;
    /**
     * playerSymbol (The symbol 'X' or 'O' that the player picks when starting the game).
     */
    private String playerSymbol;
    /**
     * computerSymbol(The symbol that the computer gets assigned to depending on player symbol choice).
     */
    private String computerSymbol;
    /**
     * gameOver (Determines the game end state).
     */
    private boolean gameOver;

    /**
     * This is the default constructor which sets up all GUI components of the game.
     */
    public TicTacToeGUI(){
        setTitle("Tic Tac Toe"); //Sets the title on the GUI window
        
        setSize(300, 300); //Sets dimensions of GUI window
        
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Close operation of GUI window
        
        setLocationRelativeTo(null); //Places GUI window on center of screen
        
        buttons = new JButton[3][3]; //Initializes the 3x3 grid of buttons but are for now all null
        
        board = new Board(); //Initialize board object
        
        gameOver = false; //Game has started, thus it has not finished at this point in time

        String[] symbols = {"X", "O"}; //Symbols player can choose from

        String choice = (String) JOptionPane.showInputDialog(this, "Choose your symbol", "Symbol Selection", JOptionPane.PLAIN_MESSAGE, null, symbols, symbols[0]);
        playerSymbol = choice;
        //Prompt asking user to choose their Tic Tac Toe symbol and assigning it to playerSymbol

        if(playerSymbol.equals("X")){
            computerSymbol = "O";
        }
        else{
            computerSymbol = "X";
        }
        //Initializes computerSymbol depending on player choice

        initializeButtons();
    }

    
    /**
     * Creates a panel to place the 3x3 button grid on, sets each row and column
     * in the buttons array to their respective positions on the grid, and sets
     * the button to a nonnull value with its own font, action listener, and text.
     */
    private void initializeButtons(){
        JPanel panel = new JPanel(new GridLayout(3, 3));
        for(int r  = 0; r < 3; r++){
            for(int c  = 0; c < 3; c++){
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                button.addActionListener(new ButtonClickListener(r, c));
                button.setText("");
                panel.add(button);
                buttons[r][c] = button;
            }
        }
        add(panel, BorderLayout.CENTER);
    }

    
    /**
     * This is the ButtonClickListener class which is the ActionListener of the buttons on the Tic Tac Toe GUI.
     * @author Rishikesh Pisharody
     */
    private class ButtonClickListener implements ActionListener{
        /**
         * row (Row of the button).
         */
        private int row;
        /**
         * col (Column of the button).
         */
        private int col;

        /**
         * Constructor which initializes the row and column based on the button's location.
         * @param row int
         * @param col int
         */
        public ButtonClickListener(int row, int col){
            this.row = row;
            this.col = col;
        }

        
        /**
         * Performs an action on a clicked button depending on move validity and game state.
         * @param e ActionEvent
         */
        public void actionPerformed (ActionEvent e){
            JButton button = buttons[row][col]; //Button which was clicked
            if(!gameOver && button.getText().equals("")){
                boolean moveMade = board.makeMove(row, col, playerSymbol); //Attempts desired player move
                if(moveMade){
                    button.setText(playerSymbol);
                    button.setEnabled(false);
                } //If attempted plaer move is valid, marks button with playerSymbol and disables the button
                

            } //Check for if game is over and button selected is empty


            if(board.checkWinCondition()){
                JOptionPane.showMessageDialog(null, "Player wins"); //Player win pop up message
                gameOver = true; //Game is over; Player win
                for(int r = 0; r < 3; r++){
                    for(int c = 0; c < 3; c++){
                        if(buttons[r][c].isEnabled()){
                            buttons[r][c].setEnabled(false);
                        }
                    }
                } //Loop to disable remaining enabled buttons
            } //Checks if player has won game 
            else if(board.checkDrawCondition()){
                JOptionPane.showMessageDialog(null, (String) "It's a draw"); //Game draw pop up message
                gameOver = true; //Game is over; Draw

            } //Checks if game ended in draw
            else{
                makeAIMove();
            } //AI makes move if game end not achieved
        }

        
    }
    
    /**
     * Allows AI to make it's own move on the board depending on the optimal 
     * location discovered through other private methods [findBestMove and miniMax].
     */
    private void makeAIMove(){
        int[] bestMove = findBestMove(); //Finds location of most optimal move for AI.
        int row = bestMove[0];
        int col = bestMove[1];
        //Storage of location that is most optimal move
        
        boolean moveMade = board.makeMove(row, col, computerSymbol); //Attempts AI move on optimal location

        if(moveMade){
            buttons[row][col].setText(computerSymbol);
            buttons[row][col].setEnabled(false);
            
            if(board.checkWinCondition()){
                JOptionPane.showMessageDialog(null, "Computer wins"); //AI win pop up message
                gameOver = true; //Game is over; AI win
                for(int r = 0; r < 3; r++){
                    for(int c = 0; c < 3; c++){
                        if(buttons[r][c].isEnabled()){
                            buttons[r][c].setEnabled(false);
                        }
                    }
                } //Loop to disable any remaining enabled buttons on the board
            } //Checks if AI has won the game
            
            else if(board.checkDrawCondition()){
                JOptionPane.showMessageDialog(null, (String) "It's a draw"); //Game draw pop up message
                gameOver = true; //Game is over; Draw
            } //Checks for game draw condition
        } //If AI move is valid mark button with computerSymbol and disable the button and check for end game conditions
    }

    
    
    /** 
     * Returns the row and column of the most optimal AI move on game board.
     * @return int[]
     */
    private int[] findBestMove(){
        int bestScore = Integer.MIN_VALUE; //bestScore starts off at the minimum integer value and increases as a higher score is found
        int[] bestMove = {-1, -1}; //Location of optimal move is initiialized to negative numbers
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(board.getCell(r, c).getStatus().equals("")){
                    board.makeMove(r, c, computerSymbol); //Attempts AI Move
                    int score = miniMax(board, 0, false); //Uses minimax algorithm to find best score
                    board.undoMove(r, c); //Undoes move in case better moves are available

                    if(score > bestScore){
                        bestScore = score;
                        bestMove[0] = r;
                        bestMove[1] = c;
                    } //If score is greater than bestScore update move location and set bestScore to score
                } //If button symbol is empty
            }
        } //Loop to find the most optimal location on board
        return bestMove;
    }

    
    
    /** 
     * Method which simulates a game between the AI and the player in which
     * the AI tries to maximize the score while the "player" attempts to minimize
     * it.
     * @param board Board
     * @param depth int
     * @param maximizingPlayer boolean
     * @return int
     */
    private int miniMax (Board board, int depth, boolean maximizingPlayer){
        if(board.checkWinCondition()){
            return (maximizingPlayer) ? depth - 10 : 10 - depth;
        } //Checks if there was a win condition achieved and returns a score based on how many turns were made based on who won the game
        else if(board.checkDrawCondition()){
            return 0;
        } //Checks if the simulated game ended with a draw

        if(maximizingPlayer){
            int maxScore = Integer.MIN_VALUE; //Score starts at the integer minimum value and goes up
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 3; c++){
                    if(board.getCell(r, c).getStatus().equals("")){
                        board.makeMove(r, c, computerSymbol); //Simulates the AI making a potential move
                        int score = miniMax(board, depth + 1, false); //Simulates a player turn
                        board.undoMove(r, c); //Undoes the move in case a better move was discovered later
                        maxScore = Math.max(maxScore, score); //Sets maxScore to the greatest of maxScore and score
                    } //If cell that is being selected is empty
                }
            } //Loop with recursive calls to find the highest score for the AI
            return maxScore;
        } //If player during recursive call is the AI
        else{
            int minScore = Integer.MAX_VALUE; //Score starts at the integer maximum value and goes down
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 3; c++){
                    if(board.getCell(r, c).getStatus().equals("")){
                        board.makeMove(r, c, playerSymbol); //Simulates the player making a potential move
                        int score = miniMax(board, depth + 1, true); //Simulates an AI turn
                        board.undoMove(r, c); //Undoes the move in case a better move was discovered later
                        minScore = Math.min(minScore, score); //Sets minScore to the smallest of minScore and score
                    } //If cell that is being selected is empty
                }
            } //Loop with recursive calls to find the lowest score for the player
            return minScore;
        } //If player during recursive call is the human player
    }

    
    /**
     * Main method to start up GUI window for the TicTacToe Game.
     * @param args String
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI gui = new TicTacToeGUI();
            gui.setVisible(true);
        });
    }

    
}
