## Connect4 Game Playing Agent
Game Playing Agent for the popular two player board game Connect4 in Java

###Description
Connect 4 involves two players, a ‘red’ player and a ‘yellow’ player, playing against
each other. The game is played on a vertical grid with six rows and seven columns. The
players take turns placing pieces of their color, called ‘tokens’, into the board. The goal
of each player is to get four of his or her tokens in a row in the board, either
horizontally, vertically, or diagonally.

###Project Architecture
* *_Main.java_*: the main driver class. 
* *_Connect4Game.java_*: the object that stores the current status of the game. Connect4Game stores the game board as
a list of Connect4Columns.
* *_Connect4Column.java_*: the object that stores a single column of the game board.
A Connect4Column stores a list of Connect4Slots, from the top to the bottom. The
first slot in the list is the top slot, and the last slot is the bottom slot.
* *_Connect4Slot.java_*: the object that stores a single slot in the game board. A slot
can be either filled by a token or empty; if a slot is empty, no other slots above it
can be filled. If a slot is filled, it is filled by either a red token or a yellow one.
* *_Agent.java_*: the abstract class that gives the actions that every agent must be
able to perform. Generally, every agent must have a name and must be able to
move in the game.
* *_Connect4Frame.java_*: the class that creates the window that shows the game.
The Connect4Frame object also runs the game engine. 
* *_Connect4Panel.java_*: the class that shows the current game itself.
* *_Varun.java_*: this class is the subclass of Agent.java, and all the logic of the agent that plays this game is
implemented in this class.


###UML Diagram
<img src="https://github.com/sharma-varun/Connect4/blob/master/img/Screenshot%20(7).png">

###ScreenShot
<img src="https://github.com/sharma-varun/Connect4/blob/master/img/Screenshot%20(6).png">
<img src="https://github.com/sharma-varun/Connect4/blob/master/img/Screenshot%20(5).png">


