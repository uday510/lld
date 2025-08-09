Consider a board game where you need to save the current state of the game at 
various checkpoints. Instead of manually creating new board objects and copying all
the pieces or their states (which would be costly if the board is large has many game pieces),
we can use the Prototype Pattern to clone the board.

We will use the prototype pattern allows us to make a copy of the current board,
including all its pieces and their states, without the need for deeply recreating each 
part of the board.