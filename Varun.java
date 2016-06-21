import java.util.Random;

public class Varun extends Agent
{
    Random r;
    private String name;
    /**
     * Constructs a new agent, giving it the game and telling it whether it is Red or Yellow.
     *
     * @param game The game the agent will be playing.
     * @param iAmRed True if the agent is Red, False if the agent is Yellow.
     */
    public Varun(Connect4Game game, boolean iAmRed, String name)
    {
        super(game, iAmRed);
        r = new Random();
        this.name = name;
    }

    /**
     * The move method is run every time it is this agent's turn in the game. You may assume that
     * when move() is called, the game has at least one open slot for a token, and the game has not
     * already been won.
     */
    public void move()
    {
      int iWin = CanWinGame(iAmRed);
      int uWin = CanWinGame(!iAmRed);

      if(iWin >= 0){
        moveOnColumn(iWin);
      }
      else if(uWin >= 0){
        moveOnColumn(uWin);
      }
      else{
        moveOnColumn(randomMove());
      }

    }

    /**
     * Drops a token into a particular column so that it will fall to the bottom of the column.
     * If the column is already full, nothing will change.
     *
     * @param columnNumber The column into which to drop the token.
     */
    public void moveOnColumn(int columnNumber)
    {
        int lowestEmptySlotIndex = getLowestEmptyIndex(myGame.getColumn(columnNumber));   // Find the top empty slot in the column
                                                                                                  // If the column is full, lowestEmptySlot will be -1
        if (lowestEmptySlotIndex > -1)  // if the column is not full
        {
            Connect4Slot lowestEmptySlot = myGame.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);  // get the slot in this column at this index
            if (iAmRed) // If the current agent is the Red player...
            {
                lowestEmptySlot.addRed(); // Place a red token into the empty slot
            }
            else // If the current agent is the Yellow player (not the Red player)...
            {
                lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
            }
        }
    }

    /**
     * Returns the index of the top empty slot in a particular column.
     *
     * @param column The column to check.
     * @return the index of the top empty slot in a particular column; -1 if the column is already full.
     */
    public int getLowestEmptyIndex(Connect4Column column) {
        int lowestEmptySlot = -1;
        for  (int i = 0; i < column.getRowCount(); i++)
        {
            if (!column.getSlot(i).getIsFilled())
            {
                lowestEmptySlot = i;
            }
        }
        return lowestEmptySlot;
    }



    /**
     * Returns a random valid move. If your agent doesn't know what to do, making a random move
     * can allow the game to go on anyway.
     *
     * @return a random valid move.
     */
    public int randomMove()
    {
        int i = r.nextInt(myGame.getColumnCount());
        while (getLowestEmptyIndex(myGame.getColumn(i)) == -1)
        {
            i = r.nextInt(myGame.getColumnCount());
        }
        return i;
    }


    /**
     * Returns the name of this agent.
     *
     * @return the agent's name
     */
    public String getName()
    {
        return this.name;
    }


    /*
      This function takes in the currentPlayer as boolean value, if the current move can win the game
      for the player, if yes then returns the column to be played else returns -1.
      @param boolean playerColor
      @return column index of the board
    */

    public int CanWinGame(boolean red){

      for (int i = 0; i < myGame.getColumnCount() ; i++) {

        Connect4Column c = myGame.getColumn(i);
        int r = getLowestEmptyIndex(c);

        if(r > -1){
          //vertical check if there are three matching tokens down
          if(r < myGame.getRowCount() - 3){
            if(CheckSlots(red, c.getSlot(r + 1), c.getSlot(r + 2), c.getSlot(r + 3))){
              return i;
            }
          }
          // horizontal check if there are three matching tokens to the right
          if(i < myGame.getColumnCount() - 3){
            if(CheckSlots(red, myGame.getColumn(i+1).getSlot(r), myGame.getColumn(i+2).getSlot(r), myGame.getColumn(i+3).getSlot(r))){
              return i;
            }
          }

          //horizontal check if there are three matching tokens to the left
          if(i > 2){
            if(CheckSlots(red, myGame.getColumn(i-1).getSlot(r), myGame.getColumn(i-2).getSlot(r), myGame.getColumn(i-3).getSlot(r))){
              return i;
            }
          }

          //horizontal check if there is a single matching token at the left and two matching tokens to the right
          if(i < myGame.getColumnCount() - 2 && i > 0){
            if(CheckSlots(red, myGame.getColumn(i+1).getSlot(r), myGame.getColumn(i-1).getSlot(r), myGame.getColumn(i+2).getSlot(r))){
              return i;
            }
          }

          //horizontal check to see if there is a single matching token to the right and two matching tokens to the left
          if(i > 1 && i < myGame.getColumnCount() - 1){
            if(CheckSlots(red, myGame.getColumn(i-1).getSlot(r), myGame.getColumn(i+1).getSlot(r), myGame.getColumn(i-2).getSlot(r))){
              return i;
            }
          }

          //diagonal check if there are three matching tokens to the right and down
          if(r < myGame.getRowCount() - 3 && i < myGame.getColumnCount() - 3){
            if(CheckSlots(red, myGame.getColumn(i+1).getSlot(r+1), myGame.getColumn(i+2).getSlot(r+2), myGame.getColumn(i+3).getSlot(r+3))){
              return i;
            }
          }
          //diagonal check if there are three matching tokens to the right and up
          if(r > myGame.getRowCount() && i < myGame.getColumnCount() - 3 && r > 2 && i >= 0){
            if(CheckSlots(red, myGame.getColumn(i+1).getSlot(r-1), myGame.getColumn(i+2).getSlot(r-2), myGame.getColumn(i+3).getSlot(r-3))){
              return i;
            }
          }

          //diagonal check if there are three matching tokens to the left and down
          if(i > 2 && i < myGame.getColumnCount() && r < myGame.getRowCount() - 3 && r >= 0){
            if(CheckSlots(red, myGame.getColumn(i-1).getSlot(r+1), myGame.getColumn(i-2).getSlot(r+2), myGame.getColumn(i-3).getSlot(r+3))){
              return i;
            }
          }

          //diagonal check if there are three matching tokens to the left and up
          if(r < myGame.getRowCount() && r > myGame.getRowCount() - 4 && i < myGame.getColumnCount() && i > 2){
            if(CheckSlots(red, myGame.getColumn(i-1).getSlot(r-1), myGame.getColumn(i-2).getSlot(r-2), myGame.getColumn(i-3).getSlot(r-3))){
              return i;
            }
          }

          //diagonal check for one matching token to the left-up and two to the down right
          if(r < myGame.getRowCount() - 2 && r > 0 && i > 0 && i < myGame.getColumnCount() - 2){
            if(CheckSlots(red, myGame.getColumn(i-1).getSlot(r-1), myGame.getColumn(i+2).getSlot(r+2), myGame.getColumn(i+1).getSlot(r+1))){
              return i;
            }
          }

          //diagonal check for one matching token to the right-up and two to the down left
          if(r < myGame.getRowCount() - 2 && r > 0 && i > 1 && i < myGame.getColumnCount() - 1){
            if(CheckSlots(red, myGame.getColumn(i-1).getSlot(r+1), myGame.getColumn(i-2).getSlot(r+2), myGame.getColumn(i+1).getSlot(r+1))){
              return i;
            }
          }

          //diagonal check for two matching token to the right-up and one to the down left
          if(r < myGame.getRowCount() - 1 && r > 1 && i > 0 && i < myGame.getColumnCount() - 2){
            if(CheckSlots(red, myGame.getColumn(i+1).getSlot(r-1), myGame.getColumn(i+2).getSlot(r-2), myGame.getColumn(i+1).getSlot(r-1))){
              return i;
            }
          }

          //diagonal check for two matching token to the right-down and one to the up left
          if(r < myGame.getRowCount() - 1 && r > 1 && i > 1 && i < myGame.getColumnCount() - 1){
            if(CheckSlots(red, myGame.getColumn(i-1).getSlot(r-1), myGame.getColumn(i-2).getSlot(r-2), myGame.getColumn(i+1).getSlot(r+1))){
              return i;
            }
          }

        }

      }
      return -1;
    }

    /*
     This function is a helper function for the CanWinGame() function where, it takes the boolean state of the current
     player, and three Connect4Slots and checks if they are all the same color. Returns true if yes or else returns false.
     @param boolean value, Connect4Slot_1, Connect4Slot_2, Connect4Slot_3
     @return true or false (boolean)
    */

    public boolean CheckSlots(boolean myRed, Connect4Slot slot1, Connect4Slot slot2, Connect4Slot slot3){

      //Check if the slots are not empty
      if(slot1.getIsFilled() && slot2.getIsFilled() && slot3.getIsFilled()){
        if (myRed == slot1.getIsRed() && myRed == slot2.getIsRed() && slot3.getIsRed()) {
          return true;
        }
      }
      return false;
    }

}
