public class SU26034670 {
	public static void firstHandInTextMode (int n, int r, int c, int k) {
		
		boolean allV = true; 			//boolean for testing if all moves made were valid
		boolean win = false;            //boolean for testing if player has won
		boolean blockade = false;       //boolean for testing if player caused a blockade
		boolean quit = false;           //boolean for testing if player has quit
    	String moveOnBoard = ""; 	    //String for Move On Board (what the move looks like) - G, Y, R or B 
    	int moves = 0; 					//int for counting the amount of moves made - including row deletion  
    	int oc = 0;						//int for counting occupied cells - Used in Score calculation 
    	int row = 0; 					//int for Rows
    	int column = 0; 				//int for Column
    	int color = 0;					//int for Color to be played
    	int move = 0; 					//int for Move
    	
		String [][] board = new String [r][c]; //Array for Board of game
		for (int a = 0; a < r; a++) {  //To make open spaces on Board
			String O = "."; 
			board [a][0] = O;   
		} 
		for (int p = 0; p < r ;p++ ) { //To make closed spaces on Board
			for (int l = 1; l < c; l++) { 
				String C = "*"; 
				board [p][l] = C; 	
			}
		} 
		
		StdOut.println("The dimension of your board is: "+r+"x"+c); 
		StdOut.println("The length of a blockade is: "+k);  
		
		StdOut.println(); //Skips a line for spacing
		for (int i = 0; i < r ;i++ ) { //Prints Board out to user
			for (int j = 0; j < c; j++) { 
				StdOut.print(board[i][j]);
			}
			StdOut.println();
		} 
		StdOut.println(); //Skips a line for spacing 
		for (int x = 0; x < r; x++) { //Game Loop 
			if (!quit&&!blockade&&!win) { //Game will run as long as quit, blockade and win are all false
				x = 0; //Resets X to 0 causing an infinite loop unless one of the boolean values change, i.e.(Game Terminates) 
				allV = true; //Resets allV to true so that if an invalid move was done in the previous move, it will reset for the new move 
				
				StdOut.print("Move: "); 
				move = StdIn.readInt(); //int for Move (0, 1 or 2)
				
				if ((move != 0)&&(move != 1)&&(move != 2)) {
					StdOut.println("Invalid move: Unknown move!"); //If Move was not 0, 1 or 2 - Invalid move 
					allV = false;
				}
				if (move == 0) { //If Move = 0 - User wants to delete a row
					StdOut.print("Row Number: ");
					row = StdIn.readInt(); //int for Row number to delete
					
					StdOut.print("Column Number: ");
					column = StdIn.readInt(); //int for Column number to delete
					
					if (((row < 0)||(row >= r))||((column < 0)||(column >= r))) { //If the cell or position chosen is not on the Board, i.e.Out of Bounds - Invalid move
						StdOut.println("Invalid move: Outside of board!");
						allV = false; 
					}
					if(allV == true) {
						if (board[row][column].equals(".")||board[row][column].equals("*")) { //If the cell or position chosen is not an occupied cell, There is nothing to delete - Invalid move
							StdOut.println("Invalid move: Nothing to delete!");
							allV = false; 
						}
					}
					if(allV == true) { //The rest of the row deletion code should only happen if allV is still true (no invalids made)
						board [row][column] = "."; //Selected cell becomes open 
						moves++; //Row Deletion counts as a move
						for (int i = column+1; i < r; i++) { //Cells to the right of the selected cell become closed
							board [row][i] = "*";
						}
						StdOut.println(); //Skips line before Board (for spacing)
						for (int i = 0; i < r ;i++ ) { //Prints Board out to User
							for (int j = 0; j < c; j++) { 
								StdOut.print(board[i][j]);
							}
							StdOut.println();
						}  
						StdOut.println(); //Skips line after Board (for spacing)
					}
				}
				else if (move == 2) { //If Move = 2 - User wants to quit
					x = r; //Loop carries on for as long as x < r, so if x = r, the code will exit the loop, therefore exit the game
				}
				else if (move == 1) { //If Move = 1 - User wants to make a move 
					
					StdOut.print("Row Number: ");
					row = StdIn.readInt(); //int for Row number to play in 
					
					StdOut.print("Column Number: ");
					column = StdIn.readInt(); //int for Column number to play in 
					
					StdOut.print("Color: ");
					color = StdIn.readInt(); //int for Which Color to play 
					
					if (((row < 0)||(row >= r))||((column < 0)||(column >= r))) { //If cell or position chosen is not on the Board, i.e.Out of Bounds - Invalid move
						StdOut.println("Invalid move: Outside of board!");
						allV = false; 
					}
					
					if (allV == true) { //The rest of the loop should only happen if allV is still true (no invalids made)
						if (color != 0&&color != 1&&color!= 2&&color != 3) { //If Color chosen doesn't exist, i.e.is not G, Y, R or B - Invalid move
							StdOut.println("Invalid move: Unknown color!");
							allV = false;
						}
						else if ((color == 2||color == 3)&&n == 2) { //If User chose to play with 2 colors but tries to play the third or fourth color - Invalid move 
							StdOut.println("Invalid move: Unknown color!");
							allV = false; 
						}
						else if (color == 3&&n == 3) { //If User chose to play with 3 colors but tried to play the fourth color - Invalid move 
							StdOut.println("Invalid move: Unknown color!");
							allV = false; 
						}
						else if (!board[row][column].equals(".")) { //If cell or position chosen to play in is not an open cell - Invalid move
    						StdOut.println("Invalid move: Cell is not open!");
    						allV = false; 
    					}
						if (allV == true) {
							if (color == 0) { moveOnBoard = "G"; } //0 is Green 
							if (color == 1) { moveOnBoard = "Y"; } //1 is Yellow
							if (color == 2) { moveOnBoard = "R"; } //2 is Red
							if (color == 3) { moveOnBoard = "B"; } //3 is Blue
							board[row][column] = moveOnBoard; //Assigns the chosen Color to the chosen Row and Column (Chosen cell on the Board)
							moves++; //Counts as a move
							if (column != r-1) { board[row][column+1] = "."; } //If the cell played is not the last cell in the row, make the next cell open 
							StdOut.println(); //Skips line before Board for spacing
							for (int i = 0; i < r ;i++ ) { //Prints Board to User
								for (int j = 0; j < c; j++) { 
									StdOut.print(board[i][j]);
								}
								StdOut.println(); 
							}  
							StdOut.println(); //Skips line after Board for spacing 
						}
					}
				}
				//Termination Testing Starts here:   
				
				blockade = blockade(r, c, blockade, board, k); //tests for blockade - the blockade method will return a boolean which is stored in "blockade" boolean in our loop
				
				win = win(r, c, win, board); //tests for a win - the win method will return a boolean
				
				if (x == r) { //When or If User Selected to quit (Move = 2), then x was assigned the value of r. Therefore this If Statement is simply saying If the User quits: 
					quit = true; //Make r = 0 if User quits (The Game Loop only gets Executed if r is more than 0)
				} //if statement makes quit true
			}
		}
		//After Game Exits Loop:  
		
		if (quit) { //If quit is true - Assigned if User quit
			StdOut.println("Termination: User terminated game!"); 
		}
		else if (blockade) { //If blockade is true - Assigned if User caused blockade
			StdOut.println("Termination: Blockade!");
		}
		else if (win) { //If win is true - Assigned if User has Won 
			StdOut.println("Termination: You have won!");
		}
		for (int i = 0; i < c ;i++ ) { //Counts Amount of occupied cells on Board
			for (int j = 0; j < c; j++) { 
				if (board[i][j].equals("G")||board[i][j].equals("Y")||board[i][j].equals("B")||board[i][j].equals("R")) {
					oc++; 
				}
			}
		}
		double s = oc*100; //components of score
		double e = c*c; //components of score
		double score = s/e; //double for Calculating Score in Percentage  
		StdOut.println("Score: "+Math.round(score)+"%"); 
		StdOut.println("Moves: "+moves); 
		StdOut.println("Game ended!");
		
	}
	public static void firstHandInGUIMode (int n, int r, int c, int k) {
		
        boolean win = false;            //boolean for testing if player has won 
        boolean blockade = false;       //boolean for testing if player has caused a blockade
        boolean quit = false;           //boolean for testing if player has quit
    	int moves = 0; 					//int for counting the amount of moves made - including row deletion  
    	int oc = 0;						//int for counting occupied cells - Used in Score calculation 
		
    	String[][] array = new String[r][r]; //creating a 2D array to track the graphical board
        
        
        StdDraw.clear(StdDraw.BLACK); //makes background black
        StdDraw.setXscale(-2, r+1); //starts from -2 and ends at r+1 to make extra space
        StdDraw.setYscale(-5, r+1); //starts from -5 and ends at r+1 to make extra space
        StdDraw.setPenColor(StdDraw.GRAY); 
        for (int i = 0; i < r; i++) { //makes open cells graphically and storing it in array
        	StdDraw.filledSquare(0, i, 0.4); 
        	array[i][0] = ".";
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i = 0; i < r; i++) { //makes closed cells graphically and storing it in array
        	for (int j = 1; j < r; j++) {
        		StdDraw.filledSquare(j, i, 0.4);
        		array[i][j] = "*";
        	}
        }
        StdDraw.setPenColor(StdDraw.MAGENTA); //for highlight
        StdDraw.setPenRadius(0.009); //the highlighted cell has a thick edged square around the cell
        
        int cX = 0; //int used as x coordinate  
        int cY = 0; //int used as y coordinate
        StdDraw.square(cX, cY, 0.45); //draws highlight (starts at (0,0))
        while (!quit&&!blockade&&!win) { //game will loop as long as quit, win and blockade are false
        	
        	while (!StdDraw.hasNextKeyTyped()); 
        	
        	char key = StdDraw.nextKeyTyped(); 
        	StdDraw.pause(1); 
        	
        	switch (key) {
        	case 'w': //if w was pressed the highlight moves up
        		if (cY != r-1) { //if its not the last vertical cell 
        			cY++; //y coordinate gets incremented
        			StdDraw.enableDoubleBuffering(); 
        			screen(array, cX, cY, r); //draws background of all previous cells without highlight
        			StdDraw.setPenColor(StdDraw.MAGENTA); 
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45); //redraws highlight in the new coordinates
        			StdDraw.show();
        		}
        		else { //else if it is the last vertical cell
        			screen(array, cX, cY, r); 
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Outside of board"); //displays invalid move text to user (coz user tried to move highlight outside of coordinate range of board)
            		invalidAudio(); //plays the invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45); //draws the highlight on the same spot 
            		StdDraw.show();
        		}
        	break; 
        	case 'a': //if w was pressed the highlight moves up
        		if (cX != 0.0) { //if its not the first horizontal cell
        			cX--; //x coordinate gets decremented
        			StdDraw.enableDoubleBuffering();
        			screen(array, cX, cY, r); //(explained previously, i.e. within the w case)
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45); //(explained previously, i.e. within the w case)
        			StdDraw.show();
        		}
        		else { //else if it is the last horizontal cell
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Outside of board"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45); //draws highlight on same spot
            		StdDraw.show();
        		}
        	break; 
        	case 's': 
        		if (cY != 0.0) { //if its not the first vertical cell
        			cY--; //y coordinate decrements
        			StdDraw.enableDoubleBuffering();
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45);
        			StdDraw.show();
        		}
        		else { //else if it is the first vertical cell
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Outside of board"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
            		StdDraw.show();
        		}
        	break; 
        	case 'd': 
        		if (cX != r-1) { //if its not the last horizontal cell
        			cX++; //x coordinate gets incremented
        			StdDraw.enableDoubleBuffering();
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45);
        			StdDraw.show();
        		}
        		else { //else if it is the last horizontal cell
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Outside of board"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
            		StdDraw.show();
        		}
        	break; 
        	case '0': //user wants to play green
        		StdDraw.enableDoubleBuffering();
        		if (array[r-cY-1][cX].equals(".")) { //tests if cell chosen is open (the array x index has to be swapped around from the graphical board index)
        			StdDraw.setPenColor(StdDraw.GREEN); 
        			StdDraw.setPenRadius(); 
        			StdDraw.filledSquare(cX, cY, 0.4); //fills the open cell with green
        			array[r-cY-1][cX] = "G"; //occupies the cell in the array with green
        			if (cX != r-1) { //if its not the last cell in the row
        				array[r-cY-1][cX+1] = "."; //make the next cell open
        				StdDraw.setPenColor(StdDraw.GRAY);
        				StdDraw.filledSquare(cX+1, cY, 0.4); 
        			}
        			screen(array, cX, cY, r); //redraws background
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45); //draws highlight
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displaying a text telling user it was a valid move
            		moveAudio(); //plays moving audio
            		moves++; //increments int for counting moves
        		}
        		else { //else if the cell is not open
        			screen(array, cX, cY, r); 
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Cell is not open"); //displays invalid move for cell is not open
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45); //draws highlight
        		}
        		
        		StdDraw.show(); 
        		
            break; 
        	case '1': //if user wants to play yellow
        		StdDraw.enableDoubleBuffering();
        		if (array[r-cY-1][cX].equals(".")) { //if cell chosen is open
        			StdDraw.setPenColor(StdDraw.YELLOW);
        			StdDraw.setPenRadius();
        			StdDraw.filledSquare(cX, cY, 0.4); //covers cell in yellow
        			array[r-cY-1][cX] = "Y"; //saves it into the array
        			if (cX != r-1) { //if it isnt the last cell in the row
        				array[r-cY-1][cX+1] = "."; //make next one open
        				StdDraw.setPenColor(StdDraw.GRAY);
        				StdDraw.filledSquare(cX+1, cY, 0.4); 
        			}
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displays text for valid move
            		moveAudio(); //plays moving audio
            		moves++; //moves get incremented
        		}
        		else { //else if its not an open cell
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Cell is not open"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
        		}
        		
        		StdDraw.show();
        		
            break; 
        	case '2': //user wants to play red
        		if (n != 2) { //only works if n is 3 or 4
        			StdDraw.enableDoubleBuffering();
        			if (array[r-cY-1][cX].equals(".")) { //if cell chosen is open
        				StdDraw.setPenColor(StdDraw.RED);
        				StdDraw.setPenRadius();
        				StdDraw.filledSquare(cX, cY, 0.4); //fills cell with red
        				array[r-cY-1][cX] = "R"; //saves into array
        				if (cX != r-1) { //if its not the last cell in the row
        					array[r-cY-1][cX+1] = "."; //make next cell open
        					StdDraw.setPenColor(StdDraw.GRAY);
        					StdDraw.filledSquare(cX+1, cY, 0.4);
        				}
        				screen(array, cX, cY, r);
        				StdDraw.setPenColor(StdDraw.MAGENTA);
        				StdDraw.setPenRadius(0.009);
        				StdDraw.square(cX, cY, 0.45);
        				StdDraw.setPenColor(StdDraw.WHITE);
                		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displays text for valid move
                		moveAudio(); //plays moving audio
                		moves++; //moves get incremented
        			}
        			else { //else if cell is not open
        				screen(array, cX, cY, r);
            			StdDraw.setPenColor(StdDraw.WHITE);
                		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Cell is not open"); //displays invalid move text
                		invalidAudio(); //plays invalid move audio
                		StdDraw.setPenColor(StdDraw.MAGENTA);
                		StdDraw.setPenRadius(0.009);
                		StdDraw.square(cX, cY, 0.45);
            		}
        			
        			StdDraw.show();
        		}
        		else { //if user chose red but n = 2 then its an unknown color invalid move
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Unknown Color"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
            		StdDraw.show();
        		}
        	break;
        	case '3': //if user wants to play blue
        		if (n == 4) { //only works if n = 4 
        			StdDraw.enableDoubleBuffering();
        			if (array[r-cY-1][cX].equals(".")) { //if cell is open
        				StdDraw.setPenColor(StdDraw.BLUE);
        				StdDraw.setPenRadius();
        				StdDraw.filledSquare(cX, cY, 0.4); //fills with blue
        				array[r-cY-1][cX] = "B"; //saves as blue in array
        				if (cX != r-1) { //if its not the last cell in the row
        					array[r-cY-1][cX+1] = "."; //make next one open
        					StdDraw.setPenColor(StdDraw.GRAY);
        					StdDraw.filledSquare(cX+1, cY, 0.4);
        				}
        				screen(array, cX, cY, r);
        				StdDraw.setPenColor(StdDraw.MAGENTA);
        				StdDraw.setPenRadius(0.009);
        				StdDraw.square(cX, cY, 0.45);
        				StdDraw.setPenColor(StdDraw.WHITE);
                		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displays valid move text
                		moveAudio(); //plays moving audio
                		moves++; //increments moves
        			}
        			else { //else if cell is not open
        				screen(array, cX, cY, r);
            			StdDraw.setPenColor(StdDraw.WHITE);
                		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Cell is not open"); //displays invalid move text
                		invalidAudio(); //plays invalid move audio
                		StdDraw.setPenColor(StdDraw.MAGENTA);
                		StdDraw.setPenRadius(0.009);
                		StdDraw.square(cX, cY, 0.45);
            		}
        			
        			StdDraw.show();
        		}
        		else { //if n does not equal 4 and user tries to play blue
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Unknown Color"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
            		StdDraw.show(); 
        		}
        	break;
        	case 'x': //if user wants to delete a row
        		StdDraw.enableDoubleBuffering();
        		if (!array[r-cY-1][cX].equals(".")&&!array[r-cY-1][cX].equals("*")) { //checks if cell chosen was an occupied cell
        			StdDraw.setPenColor(StdDraw.GRAY);
        			StdDraw.filledSquare(cX, cY, 0.4); //makes chosen cell open
        			array[r-cY-1][cX] = "."; //does the same in the array
        			StdDraw.setPenColor(StdDraw.WHITE);
        			for (int i = cX+1; i < r; i++) {
        				StdDraw.filledSquare(i, cY, 0.4); //makes all following cells in the row closed
        				array[r-cY-1][i] = "*"; //does the same in the array
        			}
        			screen(array, cX, cY, r); //redraws background
        			StdDraw.setPenColor(StdDraw.MAGENTA);
    				StdDraw.setPenRadius(0.009);
    				StdDraw.square(cX, cY, 0.45); //draws highlight
    				StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displays valid move text
            		moveAudio(); //plays moving audio
            		moves++; //row deletion counts as a move
        		}
        		else { //else if cell chosen was not occupied
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Nothing to delete"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
        		}
        		
        		StdDraw.show();
        	break;
        	case 'q': //if user wants to quit
        		quit = true; //quit becomes true
        	break;
        	default: //if user clicks anything else other than the above keys
        		screen(array, cX, cY, r);
        		StdDraw.setPenColor(StdDraw.WHITE);
        		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Unknown move"); //displays invalid move text
        		invalidAudio(); //plays invalid move audio
        		StdDraw.setPenColor(StdDraw.MAGENTA);
        		StdDraw.setPenRadius(0.009);
        		StdDraw.square(cX, cY, 0.45);
        		StdDraw.show();
        	break;
        	}
        	//Termination Testing Starts here: 
        	
        	blockade = blockade(r, c, blockade, array, k); //tests for blockade - blockade method will return a boolean
			
        	win = win(r, c, win, array); //tests for win - win method will return a boolean
        	
        }
        //After game exits loop:
        
        if (quit) { //if quit is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r); //redraws the board one last time
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: User terminated the game!"); //displays termination text
    		termAudio(); //plays termination audio
    	}
        else if (blockade) { //if blockade is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r); 
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: Blockade!"); //displays termination text
    		termAudio(); //plays termination audio
        }
        else if (win) { //if win is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		winAudio1(); //plays winning audios
    		winAudio2();
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have won!"); //displays winning text
        }
        
        for (int i = 0; i < c ;i++ ) { //Counts Amount of occupied cells on board
			for (int j = 0; j < c; j++) { 
				if (array[i][j].equals("G")||array[i][j].equals("Y")||array[i][j].equals("B")||array[i][j].equals("R")) {
					oc++; 
				}
			}
		}
		double s = oc*100; //components of score
		double e = c*c; //components of score
		double score = s/e; //double for Calculating Score in Percentage  
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(((r+3)/2)-2, -3.5, "Score: "+Math.round(score)+"%, Moves: "+moves);
		StdDraw.show();
		
	}
	public static void secondHandInTextMode (int n, int r, int c, int k) {
		
		boolean allV = true; 			//boolean for testing if all moves made were valid
        boolean win = false;            //boolean for testing for win
        boolean blockade = false;       //boolean for testing for blockade
        boolean splits = false;         //boolean for testing for splits
        boolean deadEnd = false;        //boolean for testing for dead end
        boolean i1 = false;             //boolean that helps when testing for impasse
        boolean impasse = false;        //boolean for testing for impasse
        boolean quit = false;           //boolean for testing for quit
    	String moveOnBoard = ""; 		//String for Move On Board (what the move looks like) - G, Y, R or B 
    	int moves = 0; 					//int for counting the amount of moves made - including row deletion  
    	int oc = 0;						//int for counting occupied cells - Used in Score calculation 
    	int row = 0; 					//int for Rows
    	int column = 0; 				//int for Column
    	int color = 0;					//int for Color to be played
    	int move = 0; 					//int for Move
		
    	String [][] board = new String [r][c]; //Array for Board of game
		for (int a = 0; a < r; a++) {  //To make open spaces on Board
			String O = "."; 
			board [a][0] = O;   
		} 
		for (int p = 0; p < r ;p++ ) { //To make closed spaces on Board
			for (int l = 1; l < c; l++) { 
				String C = "*"; 
				board [p][l] = C; 	
			}
		} 
		
		StdOut.println("The dimension of your board is: "+r+"x"+c); 
		StdOut.println("The length of a blockade is: "+k); 
		
		StdOut.println();
		for (int i = 0; i < r ;i++ ) { //Prints Board out to user
			for (int j = 0; j < c; j++) { 
				StdOut.print(board[i][j]);
			}
			StdOut.println();
		} 
		int x;
		StdOut.println(); //Skips a line for spacing 
		while (!win&&!quit&&!deadEnd&&!blockade&&!impasse&&!splits) { //game will loop for as long as user hasnt quit, won, or lost
			x = 0; //Resets X to 0 causing an infinite loop unless r becomes negative (or 0), i.e.(Game Terminates) 
			allV = true; //Resets allV to true so that if an invalid move was done in the previous move, it will reset for the new move 
			
			StdOut.print("Move: ");
			move = StdIn.readInt(); //int for Move (0, 1 or 2)
			
			if ((move != 0)&&(move != 1)&&(move != 2)) {
				StdOut.println("Invalid move: Unknown move!"); //If Move was not 0, 1 or 2 - Invalid move 
				allV = false;
			}
			if (move == 0) { //If Move = 0 - User wants to delete a row
				StdOut.print("Row Number: ");
				row = StdIn.readInt(); //int for Row number to play in 
				
				StdOut.print("Column Number: ");
				column = StdIn.readInt(); //int for Column number to play in 
				
				if (((row < 0)||(row >= r))||((column < 0)||(column >= r))) { //If the cell or position chosen is not on the Board, i.e.Out of Bounds - Invalid move
					StdOut.println("Invalid move: Outside of board!");
					allV = false; 
				}
				if(allV == true) { //if all moves are still valid
					if (board[row][column].equals(".")||board[row][column].equals("*")) { //If the cell or position chosen is not an occupied cell, There is nothing to delete - Invalid move
						StdOut.println("Invalid move: Nothing to delete!");
						allV = false; 
					}
				}
				if(allV == true) {
					board [row][column] = "."; //Selected cell becomes open 
					moves++; //Row Deletion counts as a move
					for (int i = column+1; i < r; i++) { //Cells to the right of the selected cell become closed
						board [row][i] = "*";
					}
					StdOut.println(); //Skips line before Board (for spacing)
					for (int i = 0; i < r ;i++ ) { //Prints Board out to User
						for (int j = 0; j < c; j++) { 
							StdOut.print(board[i][j]);
						}
						StdOut.println();
					} 
					StdOut.println(); //Skips line after Board (for spacing)
				}
			}
			else if (move == 2) { //If Move = 2 - User wants to quit
				x = r; //Loop carries on for as long as x < r, so if x = r, the code will exit the loop, therefore exit the game
			}
			else if (move == 1) { //If Move = 1 - User wants to make a move 
				
				StdOut.print("Row Number: ");
				row = StdIn.readInt(); //int for Row number to play in 
				
				StdOut.print("Column Number: ");
				column = StdIn.readInt(); //int for Column number to play in 
				
				StdOut.print("Color: ");
				color = StdIn.readInt(); //int for Which Color to play 
				
				if (((row < 0)||(row >= r))||((column < 0)||(column >= r))) { //If cell or position chosen is not on the Board, i.e.Out of Bounds - Invalid move
					StdOut.println("Invalid move: Outside of board!");
					allV = false; 
				}
				
				if (allV == true) {
					if (color != 0&&color != 1&&color!= 2&&color != 3) { //If Color chosen doesn't exist, i.e.is not G, Y, R or B - Invalid move
						StdOut.println("Invalid move: Unknown color!");
						allV = false;
					}
					else if ((color == 2||color == 3)&&n == 2) { //If User chose to play with 2 colors but tries to play the third or fourth color - Invalid move 
						StdOut.println("Invalid move: Unknown color!");
						allV = false; 
					}
					else if (color == 3&&n == 3) { //If User chose to play with 3 colors but tried to play the fourth color - Invalid move 
						StdOut.println("Invalid move: Unknown color!");
						allV = false; 
					}
					else if (!board[row][column].equals(".")) { //If cell or position chosen to play in is not an open cell - Invalid move
    					StdOut.println("Invalid move: Cell is not open!");
    					allV = false; 
    				}
					if (allV == true) {
						if (color == 0) { moveOnBoard = "G"; } //0 is Green 
						if (color == 1) { moveOnBoard = "Y"; } //1 is Yellow
						if (color == 2) { moveOnBoard = "R"; } //2 is Red
						if (color == 3) { moveOnBoard = "B"; } //3 is Blue
						board[row][column] = moveOnBoard; //Assigns the chosen Color to the chosen Row and Column (Chosen cell on the Board)
						moves++; //Counts as a move
						if (column != r-1) { board[row][column+1] = "."; } //If the cell played is not the last cell in the row, make the next cell open 
						StdOut.println(); //Skips line before Board for spacing
						for (int i = 0; i < r ;i++ ) { //Prints Board to User
							for (int j = 0; j < c; j++) { 
								StdOut.print(board[i][j]);
							}
							StdOut.println(); 
						}  
						StdOut.println(); //Skips line after Board for spacing 
					}
				}
			}
			//Termination Testing Starts here:   
			
			blockade = blockade(r, c, blockade, board, k); //tests for blockade - blockade method will return a boolean
			
			splits = splits(r, c, splits, board); //tests for splits - splits method will return a boolean
			
			String dE = ""; //string used in dead end method
			String[] a = new String[c*c]; //array used in dead end method
			boolean de = false; //boolean for dead end 
			for (int q = 0; q < c; q++) { //q acts as a row number (therefore implementing the dead end method for each q from 0 to c will test for dead ends in each row)
				dE = ""; //re-assigns the string to make it empty again (for the new row)
				for (int i = 0; i < c*c; i++) { //re-assigns the array to become empty again (for the new row)
					a[i] = ""; 
				}
				de = deadEnds(q, a, dE, c, board, de); //tests for dead ends - the deadEnds method will return a boolean 
				if (de == true) { //if the boolean is true, deadEnd is true and makes q = c to exit loop
					q = c; 
					deadEnd = true;
				}
			}
			
			int ocI = 0; //int for counting open cells left on board (ocI stands for open cells Impasse)
			int iI = 0;  //int for counting how many of those open cells cant be played on (due to impasse) 
			for (int i = 0; i < c; i++) { //used for checking which cells are still open 
				for (int j = 0; j < c; j++) {
					i1 = false; //makes i1 false again for each new open spot that gets tested
					if (board[i][j].equals(".")) { //if the board at cell position i and j is open
						ocI++; //increment ocI (counting the amount open spots)
						i1 = impasse(i, j, i1, board, c, k, n); //tests for impasse (one open spot at a time) - the impasse method will return a boolean
						if (i1) { //if no moves are possible on that open cell, i.e. the boolean i1 is true: 
							iI++; //then increment iI (counting how many open cells cant be played on)
						}			
					}
				}
			}
			if (iI == ocI) { //if the amount of open cells equal the amount of cells that cannot be played on
				impasse = true; //then impasse is true
			}
			
			win = win(r, c, win, board); //tests for a win - the method win will return a boolean 
			
			if (x == r) { //When or If User Selected to quit (Move = 2), then x was assigned the value of r. Therefore this If Statement is simply saying If the User quits: 
				quit = true; //make quit true
			}	
		}
		//After Game Exits Loop:  
		
		if (quit) { //If quit is true
			StdOut.println("Termination: User terminated game!"); 
		}
		else if (blockade&&deadEnd&&splits) { //if all blockade, dead end and split are true
			StdOut.println("Termination: You have caused a blockade, a dead end, and a split!");
		}
		else if (blockade&&deadEnd) { //else if only blockade and dead end are true
			StdOut.println("Termination: You have caused a blockade and a dead end!");
		}
		else if (blockade&&splits) { //else if only blockade and splits are true
			StdOut.println("Termination: You have caused a blockade and a split!");
		}
		else if (deadEnd&&splits) { //else if only splits and dead end are true
			StdOut.println("Termination: You have caused a dead end and a split!");
		}
		else if (blockade) { //else if only blockade is true
			StdOut.println("Termination: You have caused a blockade!");
		}
		else if (deadEnd) { //else if only dead end is true
			StdOut.println("Termination: You have caused a dead end!");
	  	} 
		else if (splits) { //else if only splits is true
			StdOut.println("Termination: You have caused a split!");
		} 
		else if (impasse) { //else if only impasse is true
			StdOut.println("Termination: Impasse!");
		}
		else if (win) { //else if win is true
			StdOut.println("Termination: You have won!");
		}
		for (int i = 0; i < c ;i++ ) { //Counts Amount of occupied cells on Board
			for (int j = 0; j < c; j++) { 
				if (board[i][j].equals("G")||board[i][j].equals("Y")||board[i][j].equals("B")||board[i][j].equals("R")) {
					oc++; 
				}
			}
		}
		double s = oc*100; //components of score
		double e = c*c; //components of score
		double score = s/e; //double for Calculating Score in Percentage  
		StdOut.println("Score: "+Math.round(score)+"%"); 
		StdOut.println("Moves: "+moves); 
		StdOut.println("Game ended!");
		
		
	}
	public static void secondHandInGUIMode (int n, int r, int c, int k) {
		
        boolean win = false;            //boolean for testing for win
        boolean blockade = false;       //boolean for testing for blockade
        boolean splits = false;         //boolean for testing for splits
        boolean deadEnd = false;        //boolean for testing for dead end
        boolean i1 = false;             //boolean that helps when testing for impasse
        boolean impasse = false;        //boolean for testing for impasse
        boolean quit = false;           //boolean for testing for quit
    	int moves = 0; 					//int for counting the amount of moves made - including row deletion  
    	int oc = 0;						//int for counting occupied cells - Used in Score calculation 
    	
		String[][] array = new String[r][r]; //creating a 2D array to track the graphical board. 
		//Note: The graphical board will be tracked and stored into this array so that it may be used to test for terminations just as in Text mode
        
		StdDraw.clear(StdDraw.BLACK); //makes background black
        StdDraw.setXscale(-2, r+1); //starts from -2 and ends at r+1 to make extra space
        StdDraw.setYscale(-5, r+1); //starts from -5 and ends at r+1 to make extra space
        StdDraw.setPenColor(StdDraw.GRAY); 
        for (int i = 0; i < r; i++) { //makes open cells graphically and storing it in array
        	StdDraw.filledSquare(0, i, 0.4); 
        	array[i][0] = ".";
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int i = 0; i < r; i++) { //makes closed cells graphically and storing it in array
        	for (int j = 1; j < r; j++) {
        		StdDraw.filledSquare(j, i, 0.4);
        		array[i][j] = "*";
        	}
        }
        StdDraw.setPenColor(StdDraw.MAGENTA); //for highlight
        StdDraw.setPenRadius(0.009); //the highlighted cell has a thick edged square around the cell
        
        int cX = 0; //int used as x coordinate  
        int cY = 0; //int used as y coordinate
        StdDraw.square(cX, cY, 0.45); //draws highlight (starts at (0,0))
        while (!quit&&!blockade&&!deadEnd&&!splits&&!impasse&&!win) { //game will loop as long as user has not quit, won or lost
        	 
        	while (!StdDraw.hasNextKeyTyped()); 
        	
        	char key = StdDraw.nextKeyTyped();
        	StdDraw.pause(1);
        	switch (key) {
        	case 'w': //if w was pressed the highlight moves up
        		if (cY != r-1) { //if its not the last vertical cell 
        			cY++; //y coordinate gets incremented
        			StdDraw.enableDoubleBuffering(); 
        			screen(array, cX, cY, r); //draws background of all previous cells without highlight
        			StdDraw.setPenColor(StdDraw.MAGENTA); 
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45); //redraws highlight in the new coordinates
        			StdDraw.show();
        		}
        		else { //else if it is the last vertical cell
        			screen(array, cX, cY, r); 
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Outside of board"); //displays invalid move text to user (coz user tried to move highlight outside of coordinate range of board)
            		invalidAudio(); //plays the invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45); //draws the highlight on the same spot 
            		StdDraw.show();
        		}
        	break; 
        	case 'a': //if w was pressed the highlight moves up
        		if (cX != 0.0) { //if its not the first horizontal cell
        			cX--; //x coordinate gets decremented
        			StdDraw.enableDoubleBuffering();
        			screen(array, cX, cY, r); //(explained previously, i.e. within the w case)
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45); //(explained previously, i.e. within the w case)
        			StdDraw.show();
        		}
        		else { //else if it is the last horizontal cell
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Outside of board"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45); //draws highlight on same spot
            		StdDraw.show();
        		}
        	break; 
        	case 's': 
        		if (cY != 0.0) { //if its not the first vertical cell
        			cY--; //y coordinate decrements
        			StdDraw.enableDoubleBuffering();
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45);
        			StdDraw.show();
        		}
        		else { //else if it is the first vertical cell
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Outside of board"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
            		StdDraw.show();
        		}
        	break; 
        	case 'd': 
        		if (cX != r-1) { //if its not the last horizontal cell
        			cX++; //x coordinate gets incremented
        			StdDraw.enableDoubleBuffering();
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45);
        			StdDraw.show();
        		}
        		else { //else if it is the last horizontal cell
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Outside of board"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
            		StdDraw.show();
        		}
        	break; 
        	case '0': //user wants to play green
        		StdDraw.enableDoubleBuffering();
        		if (array[r-cY-1][cX].equals(".")) { //tests if cell chosen is open (the array x index has to be swapped around from the graphical board index in order to get the correct positioning of the play)
        			StdDraw.setPenColor(StdDraw.GREEN); 
        			StdDraw.setPenRadius(); 
        			StdDraw.filledSquare(cX, cY, 0.4); //fills the open cell with green
        			array[r-cY-1][cX] = "G"; //occupies the cell in the array with green
        			if (cX != r-1) { //if its not the last cell in the row
        				array[r-cY-1][cX+1] = "."; //make the next cell open
        				StdDraw.setPenColor(StdDraw.GRAY);
        				StdDraw.filledSquare(cX+1, cY, 0.4); 
        			}
        			screen(array, cX, cY, r); //redraws background
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45); //draws highlight
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displaying a text telling user it was a valid move
            		moveAudio(); //plays moving audio
            		moves++; //increments int for counting moves
        		}
        		else { //else if the cell is not open
        			screen(array, cX, cY, r); 
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Cell is not open"); //displays invalid move for cell is not open
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45); //draws highlight
        		}
        		
        		StdDraw.show(); 
        		
            break; 
        	case '1': //if user wants to play yellow
        		StdDraw.enableDoubleBuffering();
        		if (array[r-cY-1][cX].equals(".")) { //if cell chosen is open
        			StdDraw.setPenColor(StdDraw.YELLOW);
        			StdDraw.setPenRadius();
        			StdDraw.filledSquare(cX, cY, 0.4); //covers cell in yellow
        			array[r-cY-1][cX] = "Y"; //saves it into the array
        			if (cX != r-1) { //if it isnt the last cell in the row
        				array[r-cY-1][cX+1] = "."; //make next one open
        				StdDraw.setPenColor(StdDraw.GRAY);
        				StdDraw.filledSquare(cX+1, cY, 0.4); 
        			}
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.MAGENTA);
        			StdDraw.setPenRadius(0.009);
        			StdDraw.square(cX, cY, 0.45);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displays text for valid move
            		moveAudio(); //plays moving audio
            		moves++; //moves get incremented
        		}
        		else { //else if its not an open cell
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Cell is not open"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
        		}
        		
        		StdDraw.show();
        		
            break; 
        	case '2': //user wants to play red
        		if (n != 2) { //only works if n is 3 or 4
        			StdDraw.enableDoubleBuffering();
        			if (array[r-cY-1][cX].equals(".")) { //if cell chosen is open
        				StdDraw.setPenColor(StdDraw.RED);
        				StdDraw.setPenRadius();
        				StdDraw.filledSquare(cX, cY, 0.4); //fills cell with red
        				array[r-cY-1][cX] = "R"; //saves into array
        				if (cX != r-1) { //if its not the last cell in the row
        					array[r-cY-1][cX+1] = "."; //make next cell open
        					StdDraw.setPenColor(StdDraw.GRAY);
        					StdDraw.filledSquare(cX+1, cY, 0.4);
        				}
        				screen(array, cX, cY, r);
        				StdDraw.setPenColor(StdDraw.MAGENTA);
        				StdDraw.setPenRadius(0.009);
        				StdDraw.square(cX, cY, 0.45);
        				StdDraw.setPenColor(StdDraw.WHITE);
                		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displays text for valid move
                		moveAudio(); //plays moving audio
                		moves++; //moves get incremented
        			}
        			else { //else if cell is not open
        				screen(array, cX, cY, r);
            			StdDraw.setPenColor(StdDraw.WHITE);
                		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Cell is not open"); //displays invalid move text
                		invalidAudio(); //plays invalid move audio
                		StdDraw.setPenColor(StdDraw.MAGENTA);
                		StdDraw.setPenRadius(0.009);
                		StdDraw.square(cX, cY, 0.45);
            		}
        			
        			StdDraw.show();
        		}
        		else { //if user chose red but n = 2 then its an unknown color invalid move
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Unknown Color"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
            		StdDraw.show();
        		}
        	break;
        	case '3': //if user wants to play blue
        		if (n == 4) { //only works if n = 4 
        			StdDraw.enableDoubleBuffering();
        			if (array[r-cY-1][cX].equals(".")) { //if cell is open
        				StdDraw.setPenColor(StdDraw.BLUE);
        				StdDraw.setPenRadius();
        				StdDraw.filledSquare(cX, cY, 0.4); //fills with blue
        				array[r-cY-1][cX] = "B"; //saves as blue in array
        				if (cX != r-1) { //if its not the last cell in the row
        					array[r-cY-1][cX+1] = "."; //make next one open
        					StdDraw.setPenColor(StdDraw.GRAY);
        					StdDraw.filledSquare(cX+1, cY, 0.4);
        				}
        				screen(array, cX, cY, r);
        				StdDraw.setPenColor(StdDraw.MAGENTA);
        				StdDraw.setPenRadius(0.009);
        				StdDraw.square(cX, cY, 0.45);
        				StdDraw.setPenColor(StdDraw.WHITE);
                		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displays valid move text
                		moveAudio(); //plays moving audio
                		moves++; //increments moves
        			}
        			else { //else if cell is not open
        				screen(array, cX, cY, r);
            			StdDraw.setPenColor(StdDraw.WHITE);
                		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Cell is not open"); //displays invalid move text
                		invalidAudio(); //plays invalid move audio
                		StdDraw.setPenColor(StdDraw.MAGENTA);
                		StdDraw.setPenRadius(0.009);
                		StdDraw.square(cX, cY, 0.45);
            		}
        			
        			StdDraw.show();
        		}
        		else { //if n does not equal 4 and user tries to play blue
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Unknown Color"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
            		StdDraw.show(); 
        		}
        	break;
        	case 'x': //if user wants to delete a row
        		StdDraw.enableDoubleBuffering();
        		if (!array[r-cY-1][cX].equals(".")&&!array[r-cY-1][cX].equals("*")) { //checks if cell chosen was an occupied cell
        			StdDraw.setPenColor(StdDraw.GRAY);
        			StdDraw.filledSquare(cX, cY, 0.4); //makes chosen cell open
        			array[r-cY-1][cX] = "."; //does the same in the array
        			StdDraw.setPenColor(StdDraw.WHITE);
        			for (int i = cX+1; i < r; i++) {
        				StdDraw.filledSquare(i, cY, 0.4); //makes all following cells in the row closed
        				array[r-cY-1][i] = "*"; //does the same in the array
        			}
        			screen(array, cX, cY, r); //redraws background
        			StdDraw.setPenColor(StdDraw.MAGENTA);
    				StdDraw.setPenRadius(0.009);
    				StdDraw.square(cX, cY, 0.45); //draws highlight
    				StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Valid"); //displays valid move text
            		moveAudio(); //plays moving audio
            		moves++; //row deletion counts as a move
        		}
        		else { //else if cell chosen was not occupied
        			screen(array, cX, cY, r);
        			StdDraw.setPenColor(StdDraw.WHITE);
            		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Nothing to delete"); //displays invalid move text
            		invalidAudio(); //plays invalid move audio
            		StdDraw.setPenColor(StdDraw.MAGENTA);
            		StdDraw.setPenRadius(0.009);
            		StdDraw.square(cX, cY, 0.45);
        		}
        		
        		StdDraw.show();
        	break;
        	case 'q': //if user wants to quit
        		quit = true; //quit becomes true
        	break;
        	default: //if user clicks anything else other than the above keys
        		screen(array, cX, cY, r);
        		StdDraw.setPenColor(StdDraw.WHITE);
        		StdDraw.text(((r+3)/2)-2, -2.5, "Invalid move: Unknown move"); //displays invalid move text
        		invalidAudio(); //plays invalid move audio
        		StdDraw.setPenColor(StdDraw.MAGENTA);
        		StdDraw.setPenRadius(0.009);
        		StdDraw.square(cX, cY, 0.45);
        		StdDraw.show();
        	break;
        	}
        	//Termination Testing Starts here: 
        	
        	blockade = blockade(r, c, blockade, array, k); //tests for blockade - the blockade method will return a boolean
        	
			splits = splits(r, c, splits, array); //tests for splits - the splits method will return a boolean
			
			String dE = ""; //string used in dead end method
			String[] a = new String[c*c]; //array used in dead end method
			boolean de = false; //boolean for dead end 
			for (int q = 0; q < c; q++) { //q acts as a row number (therefore implementing the dead end method for each q from 0 to c will test for dead ends in each row)
				dE = ""; //re-assigns the string to make it empty again (for the new row)
				for (int i = 0; i < c*c; i++) { //re-assigns the array to become empty again (for the new row)
					a[i] = ""; 
				}
				de = deadEnds(q, a, dE, c, array, de); //tests for dead ends - the deadEnds method will return a boolean 
				if (de == true) { //if the boolean is true, deadEnd is true and makes q = c to exit loop
					q = c; 
					deadEnd = true;
				}
			}
			
			int ocI = 0; //int for counting open cells left on board (ocI stands for open cells Impasse)
			int iI = 0;  //int for counting how many of those open cells cant be played on (due to impasse) 
			for (int i = 0; i < c; i++) { //used for checking which cells are still open 
				for (int j = 0; j < c; j++) {
					i1 = false; //makes i1 false again for each new open spot that gets tested
					if (array[i][j].equals(".")) { //if the board at cell position i and j is open
						ocI++; //increment ocI (counting the amount open spots)
						i1 = impasse(i, j, i1, array, c, k, n); //tests for impasse (one open spot at a time) - the impasse method will return a boolean
						if (i1) { //if no moves are possible on that open cell, i.e. the boolean i1 is true: 
							iI++; //then increment iI (counting how many open cells cant be played on)
						}			
					}
				}
			}
			if (iI == ocI) { //if the amount of open cells equal the amount of cells that cannot be played on
				impasse = true; //then impasse is true
			}
			
			win = win(r, c, win, array); //tests for a win - the method win will return a boolean 
        	
        }
        //After game exits loop:
        
        if (quit) { //if quit is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: User terminated the game!");
    		termAudio(); //plays termination audio
    	}
        else if (blockade&&deadEnd&&splits) { //else if all blockade, dead end and splits are true 
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have caused a blockade, a dead end, and a split!");
    		termAudio(); //plays termination audio
        }
        else if (blockade&&deadEnd) { //else if only blockade and dead end are true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have caused a blockade and a dead end!");
    		termAudio(); //plays termination audio
        }
        else if (blockade&&splits) { //else if only blockade and splits are true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have caused a blockade and a split!");
    		termAudio(); //plays termination audio
        }
        else if (deadEnd&&splits) { //else if only splits and dead end are true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have caused a dead end and a split!");
    		termAudio(); //plays termination audio
        }
        else if (blockade) { //else if only blockade is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have caused a blockade!");
    		termAudio(); //plays termination audio
        }
        else if (splits) { //else if only splits is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have caused a split!");
    		termAudio(); //plays termination audio
        }
        else if (deadEnd) { //else if only dead end is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have caused a dead end!");
    		termAudio(); //plays termination audio
        }
        else if (impasse) { //else if only impasse is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: Impasse!");
    		termAudio(); //plays termination audio
        }
        else if (win) { //else if win is true
        	StdDraw.enableDoubleBuffering();
    		screen(array, cX, cY, r);
    		winAudio1(); //plays winning audios
    		winAudio2();
    		StdDraw.setPenColor(StdDraw.WHITE);
    		StdDraw.text(((r+3)/2)-2, -2.5, "Termination: You have won!");
    	}
        
        for (int i = 0; i < c ;i++ ) { //Counts Amount of occupied cells on board
			for (int j = 0; j < c; j++) { 
				if (array[i][j].equals("G")||array[i][j].equals("Y")||array[i][j].equals("B")||array[i][j].equals("R")) {
					oc++; 
				}
			}
		}
		double s = oc*100; //components of score
		double e = c*c; //components of score
		double score = s/e; //double for Calculating Score in Percentage  
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(((r+3)/2)-2, -3.5, "Score: "+Math.round(score)+"%, Moves: "+moves);
		StdDraw.show();
	}
	public static boolean blockade (int r, int c, boolean blockade, String[][] board, int k) { //The test for blockades
		
		int bTest = 0; //int for Testing for blockade
		for (int i = 0; i < c ;i++ ) { 
			for (int j = 0; j <= c-k; j++) { 
				bTest = 0; //Resets blockade Test int each time the Test starts over, i.e.Each time it STARTS testing FROM a NEW ROW 
				for (int q = j; q < j+k; q++) {
					if (j != 10000) { //Prevents q from being assigned 10 000 if User caused blockade
						if (board[j][i].equals(board[q][i])&&!board[q][i].equals(".")&&!board[q][i].equals("*")) { //If Occupied cells equal each other increment blockadeIn Test int 
							bTest++;
							if (bTest == k) { //If blockade Test int was incremented k times, the User caused a blockade
								q = j+k; //Assigns j+k to q to exit q's loop
								j = 10000; //Assigns 10 000 to j to exit j's loop 
								i = c; //Assigns c to i to exit i's loop 
								blockade = true; //makes the boolean "blockade" true 
							}
						}
					}
				}
			}	
		} 
		return blockade; //returns the boolean
	}
	public static boolean splits (int r, int c, boolean splits, String[][]array) { //The test for splits
		
		boolean splitsTest = false; //boolean for splits
		int rr = 0;  //int for Testing for Splits (counts how many cells of 2 rows are equal) 
		for (int i = 0; i < r; i++) { 
			for (int j = i + 1; j < r; j++) { 
				if (!splitsTest) { //only if no rows are identical so far, should it continue testing for more rows
					rr = 0; //re-assigns rr to 0 again when comparing 2 new rows
					//If statement below Tests to see if the first move of 2 rows are occupied before comparing them
					if ((array[i][0].equals("G")&&array[j][0].equals("G"))||(array[i][0].equals("Y")&&array[j][0].equals("Y"))||(array[i][0].equals("B")&&array[j][0].equals("B"))||(array[i][0].equals("R")&&array[j][0].equals("R"))) {
						for (int q = 1; q < r; q++) { 
							if (array[i][q].equals(array[j][q])&&!array[i][q].equals(".")&&!array[i][q].equals("*")) { //If the first moves were occupied and the 2 rows are identical 
								rr = rr + 1; //count each cell in 2 rows that are the same
								if (rr == r-1) { //if each cell in the row is identical
									splitsTest = true; //splitsTest is true
								}
							}
						}
					}
				}
			}
		}
		if (splitsTest) { //if splitsTest is true
			splits = true; //then make splits true
		}
		return splits; //returns splits boolean
	}
	public static boolean deadEnds (int q, String[] a, String dE, int c, String[][] board, boolean de) { //The test for dead ends
		
		int m = 0; //int that serves as an index number for the array
		for (int i = 0; i < c; i++) { 
			for (int j = i + 1; j < c; j++) {
				//when the method is called, it is called in a for loop where q assesses each row, therefore q acts as a row number and increments each time the method returns, in order to test each row for dead ends
				if (board[q][i].equals(board[q][j])&&!board[q][i].equals(".")&&!board[q][i].equals("*")) { //in the row of q, if 2 cells equal each other then:  
					int x = i; //int starting from i 
					while (x <= j) { //ending at j
						dE = dE + board[q][x];//assign the string to all the cells from i to j, so that the string holds one brace
						x++;
					}
					a[m] = dE; //that string (brace) is then stores in the array (which starts from index 0(m))
					dE = ""; //re-assigns the string to store a new brace the next time the loop runs
					m++; //m increments so that the array's index moves to the next one
				}
			}
		}
		for (int w = 0; w < c*c; w++) { 
			for (int y = w + 1; y < c*c; y++) {
				if (a[w].equals(a[y])&&!a[w].equals("")) { //if there are any 2 strings (braces) that are the same in the array (thats not empty)
					de = true; //then the boolean for dead end is true
					y = c*c; //makes w and y equal to c times c to exit the loop
					w = c*c;
				}
			}
		}
		return de; //returns the boolean
	}
	public static boolean impasse (int im, int jm, boolean i1, String[][] board, int c, int k, int n) { //The test for impasse
		
		boolean iBg = false; //boolean for testing if blockade will be true if green was in the open cell (when called) (iBg stands for impasse Blockade-green)
		boolean iSg = false; //boolean for testing if splits will be true if green was in the open cell (when called) (iSg stands for impasse Splits-green)
		boolean iDEg = false; //boolean for testing if dead end will be true if green was in the open cell (when called) (iDEg stands for impasse DeadEnd-green)
		
		board[im][jm] = "G"; //temporarily places a green in the open cell index i and j
		iBg = blockade(c, c, iBg, board, k); //tests for blockade
		iSg = splits(c, c, iSg, board); //tests for splits
		String dEg = ""; //tests for dead ends (method and process was explained previously)
		String[] ag = new String[c*c]; 
		boolean deg = false; 
		for (int q = 0; q < c; q++) {
			dEg = ""; 
			for (int i = 0; i < c*c; i++) {
				ag[i] = ""; 
			}
			deg = deadEnds(q, ag, dEg, c, board, deg); 
			if (deg == true) {
				q = c; 
				iDEg = true;
			}
		}
		
		boolean iBy = false; //boolean for testing if blockade will be true if yellow was in the open cell (when called) (iBy stands for impasse Blockade-yellow)
		boolean iSy = false; //boolean for testing if splits will be true if yellow was in the open cell (when called) (iSy stands for impasse Splits-yellow)
		boolean iDEy = false; //boolean for testing if dead end will be true if yellow was in the open cell (when called) (iDEy stands for impasse DeadEnd-yellow)
		
		board[im][jm] = "Y"; //temporarily places a yellow in the open cell index i and j
		iBy = blockade(c, c, iBy, board, k); //tests for blockade
		iSy = splits(c, c, iSy, board); //tests for splits
		String dEy = ""; //tests for dead ends (method and process was explained previously)
		String[] ay = new String[c*c]; 
		boolean dey = false; 
		for (int q = 0; q < c; q++) {
			dEy = ""; 
			for (int i = 0; i < c*c; i++) {
				ay[i] = ""; 
			}
			dey = deadEnds(q, ay, dEy, c, board, dey); 
			if (dey == true) {
				q = c; 
				iDEy = true;
			}
		}
		
		boolean iBr = false; //boolean for testing if blockade will be true if red was in the open cell (when called) (iBr stands for impasse Blockade-red)
		boolean iSr = false; //boolean for testing if splits will be true if red was in the open cell (when called) (iSr stands for impasse Splits-red)
		boolean iDEr = false; //boolean for testing if dead end will be true if red was in the open cell (when called) (iDEr stands for impasse DeadEnd-red)
		
		board[im][jm] = "R"; //temporarily places a red in the open cell index i and j
		iBr = blockade(c, c, iBr, board, k); //tests for blockade
		iSr = splits(c, c, iSr, board); //tests for splits
		String dEr = ""; //tests for dead ends (method and process was explained previously)
		String[] ar = new String[c*c]; 
		boolean der = false; 
		for (int q = 0; q < c; q++) {
			dEr = ""; 
			for (int i = 0; i < c*c; i++) {
				ar[i] = ""; 
			}
			der = deadEnds(q, ar, dEr, c, board, der); 
			if (der == true) {
				q = c; 
				iDEr = true;
			}
		}
		
		boolean iBb = false; //boolean for testing if blockade will be true if blue was in the open cell (when called) (iBb stands for impasse Blockade-blue)
		boolean iSb = false; //boolean for testing if splits will be true if blue was in the open cell (when called) (iSb stands for impasse Splits-blue)
		boolean iDEb = false; //boolean for testing if dead end will be true if blue was in the open cell (when called) (iDEb stands for impasse DeadEnd-blue)
		
		board[im][jm] = "B"; //temporarily places a blue in the open cell index i and j
		iBb = blockade(c, c, iBb, board, k); //tests for blockade
		iSb = splits(c, c, iSb, board); //tests for splits
		String dEb = ""; //tests for dead ends (method and process was explained previously)
		String[] ab = new String[c*c]; 
		boolean deb = false; 
		for (int q = 0; q < c; q++) {
			dEb = ""; 
			for (int i = 0; i < c*c; i++) {
				ab[i] = ""; 
			}
			deb = deadEnds(q, ab, dEb, c, board, deb); 
			if (deb == true) {
				q = c; 
				iDEb = true;
			}
		}
		
		if (n == 2&&(iBg||iSg||iDEg)&&(iBy||iSy||iDEy)) { //if n is 2, only green and yellow matter - and if either one of the termination conditions hold for BOTH green and yellow
			i1 = true; //then the boolean for impasse is true 
		}
		else if (n == 3&&(iBg||iSg||iDEg)&&(iBy||iSy||iDEy)&&(iBr||iSr||iDEr)) { //if n is 3, only green, yellow and red matter - and if either one of the termination conditions hold for ALL 3 green, yellow and red
			i1 = true; //then the boolean for impasse is true 
		}
		else if (n == 4&&(iBg||iSg||iDEg)&&(iBy||iSy||iDEy)&&(iBr||iSr||iDEr)&&(iBb||iSb||iDEb)) { //if n is 4, all colors matter - and if either one of the termination conditions hold for ALL 4 colors
			i1 = true; //then the boolean for impasse is true 
		}
		
		board[im][jm] = "."; //re-assigns the open cell used back to an open cell
		
		return i1; //returns the boolean for impasse
	}
	public static boolean win (int r, int c, boolean win, String[][] board) { //The test for a win
		
		boolean winTest = true; //boolean for Testing if User has won 
		for (int y = 0; y < c; y++) {
			for (int z = 0; z < c; z++) {
				if (board[y][z].equals(".")) { //If any cell on the Board is still open, User has not won - therefore WinTest boolean is now false
					winTest = false;
				}
			}
		}
		if (winTest == true) { //If WinTest boolean is still true, it means no open spaces left on board, therefore User has won 
			win = true; //makes win true
		}
		return win; //returns win
	}
	public static void screen (String array[][], int cX, int cY, int r) { //Screen method used to redraw background in GUI
		
		StdDraw.clear(StdDraw.BLACK); //makes the entire background black
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < r; j++) {
				if (array[i][j].equals(".")) { //if the index at (i, j) stored in the array was an open cell
					StdDraw.setPenColor(StdDraw.GRAY);
					StdDraw.filledSquare(j, r-i-1, 0.4); //draw a gray filled square at where that index is on the Cartesian plane 
					
				}
				else if (array[i][j].equals("*")) { //else if the index at (i, j) stored in the array was a closed cell
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledSquare(j, r-i-1, 0.4); //draw a white filled square at where that index is on the Cartesian plane
					
				}
				else if (array[i][j].equals("G")) { //else if the index at (i, j) stored in the array was a green cell
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.filledSquare(j, r-i-1, 0.4); //draw a green filled square at where that index is on the Cartesian plane
					
				}
				else if (array[i][j].equals("Y")) { //else if the index at (i, j) stored in the array was a yellow cell
					StdDraw.setPenColor(StdDraw.YELLOW);
					StdDraw.filledSquare(j, r-i-1, 0.4); //draw a yellow filled square at where that index is on the Cartesian plane
					
				}
				else if (array[i][j].equals("R")) { //else if the index at (i, j) stored in the array was a red cell
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledSquare(j, r-i-1, 0.4); //draw a red filled square at where that index is on the Cartesian plane
					
				}
				else if (array[i][j].equals("B")) { //else if the index at (i, j) stored in the array was a blue cell
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledSquare(j, r-i-1, 0.4); //draw a blue filled square at where that index is on the Cartesian plane
					
				}
			}
		}
	}
	public static void winAudio1 () { //Winning Audio used in GUI (part 1) - sound played when player wins. NOTE: All Audio functions DO NOT work on Ubuntu at NARGA, but works when I tested it on Windows
		
		double hz1 = 440.0 * Math.pow(2.0, 12.0/12.0); //doubles for creating the adequate frequency for the sound
		double hz2 = 440.0 * Math.pow(2.0, 7.0/12.0);
		double hz3 = 440.0 * Math.pow(2.0, 10.0/12.0);
		double hz4 = 440.0 * Math.pow(2.0, 3.0/12.0);
		double hz5 = 440.0 * Math.pow(2.0, 0.0/12.0);
		double hz6 = 440.0 * Math.pow(2.0, 4.0/12.0);
		double hz7 = 440.0 * Math.pow(2.0, 5.0/12.0);
		double hze = 440.0 * Math.pow(2.0, 0.0/12.0);
		double seconds = 0.2; //doubles for the durations i want each sound to be played for
		double ending = 0.4; 
		int sampleRate = 44100;
		int nN = (int) (seconds * sampleRate); //creates an int from the doubles used, in order to create arrays for the many short sounds (frequencies)
		int mM = (int) (ending * sampleRate); 
		double[] a = new double[nN+1]; //arrays for storing the many small sounds (frequencies)
		double[] b = new double[nN+1];
		double[] c = new double[nN+1];
		double[] d = new double[mM+1];
		double[] e = new double[nN+1];
		double[] f = new double[nN+1];
		double[] g = new double[nN+1];
		double[] end = new double[mM+1];
		for (int i = 0; i <= nN; i++) { //storing the frequencies in the arrays
		 a[i] = Math.sin(2 * Math.PI * i * hz1 / sampleRate);
		 b[i] = Math.sin(2 * Math.PI * i * hz2 / sampleRate);
		 c[i] = Math.sin(2 * Math.PI * i * hz3 / sampleRate);
		 
		 e[i] = Math.sin(2 * Math.PI * i * hz5 / sampleRate);
		 f[i] = Math.sin(2 * Math.PI * i * hz6 / sampleRate);
		 g[i] = Math.sin(2 * Math.PI * i * hz7 / sampleRate);
		}
		for (int i = 0; i <= mM; i++) { //storing the frequencies in the arrays
			end[i] = Math.sin(2 * Math.PI * i * hze / sampleRate);
			d[i] = Math.sin(2 * Math.PI * i * hz4 / sampleRate);
		}
		StdAudio.play(a); //plays the entire array of frequencies, creating an actual small sound
		StdAudio.play(b);
		StdAudio.play(a);
		StdAudio.play(b); 
		StdAudio.play(a); 
		StdAudio.play(b); 
		StdAudio.play(a); 
		StdAudio.play(c); 
		StdAudio.play(d);  
		StdAudio.play(e); 
		StdAudio.play(g); 
		StdAudio.play(f); 
		StdAudio.play(b); 
		StdAudio.play(e); 
	}
	public static void winAudio2 () { //Winning Audio used in GUI (part 2) - sound played when player wins
		
		double hz1 = 440.0 * Math.pow(2.0, 12.0/12.0); //doubles for creating the adequate frequency for the sound
		double hz2 = 440.0 * Math.pow(2.0, 7.0/12.0);
		double hz3 = 440.0 * Math.pow(2.0, 10.0/12.0);
		double hz4 = 440.0 * Math.pow(2.0, 3.0/12.0);
		double hz5 = 440.0 * Math.pow(2.0, 0.0/12.0);
		double hz6 = 440.0 * Math.pow(2.0, 4.0/12.0);
		double hz7 = 440.0 * Math.pow(2.0, 5.0/12.0);
		double hze = 440.0 * Math.pow(2.0, 0.0/12.0);
		double seconds = 0.2; //doubles for the durations i want each sound to be played for
		double ending = 0.4; 
		int sampleRate = 44100;
		int nN = (int) (seconds * sampleRate); //creates an int from the doubles used, in order to create arrays for the many short sounds (frequencies)
		int mM = (int) (ending * sampleRate); 
		double[] a = new double[nN+1]; //arrays for storing the many small sounds (frequencies)
		double[] b = new double[nN+1]; 
		double[] c = new double[nN+1];
		double[] d = new double[mM+1];
		double[] e = new double[nN+1];
		double[] f = new double[nN+1];
		double[] g = new double[nN+1];
		double[] end = new double[mM+1];
		for (int i = 0; i <= nN; i++) { //storing the frequencies in the arrays
		 a[i] = Math.sin(2 * Math.PI * i * hz1 / sampleRate);
		 b[i] = Math.sin(2 * Math.PI * i * hz2 / sampleRate);
		 c[i] = Math.sin(2 * Math.PI * i * hz3 / sampleRate);
		 
		 e[i] = Math.sin(2 * Math.PI * i * hz5 / sampleRate);
		 f[i] = Math.sin(2 * Math.PI * i * hz6 / sampleRate);
		 g[i] = Math.sin(2 * Math.PI * i * hz7 / sampleRate);
		}
		for (int i = 0; i <= mM; i++) { //storing the frequencies in the arrays
			end[i] = Math.sin(2 * Math.PI * i * hze / sampleRate);
			d[i] = Math.sin(2 * Math.PI * i * hz4 / sampleRate);
		}  
		StdAudio.play(g); //plays the entire array of frequencies, creating an actual small sound
		StdAudio.play(f); 
		StdAudio.play(b); 
		StdAudio.play(e); 
		StdAudio.play(a);
		StdAudio.play(b);
		StdAudio.play(a);
		StdAudio.play(b); 
		StdAudio.play(a); 
		StdAudio.play(b); 
		StdAudio.play(a); 
		StdAudio.play(c); 
		StdAudio.play(d); 
	}
	public static void moveAudio () { //Moving Audio used in GUI - sound played when a move is played
		
		double hz1 = 440.0 * Math.pow(2.0, 12.0/12.0); //double for creating the adequate frequency for the sound
		double seconds = 0.2; //double for the duration i want the sound to be played for
		int sampleRate = 44100;
		int nN = (int) (seconds * sampleRate); //creates an int from the double used, in order to create an array for the many short sounds (frequencies)
		double[] a = new double[nN+1]; //array for storing the many small sounds (frequencies)
		for (int i = 0; i <= nN; i++) { //storing the frequencies in the array
			 a[i] = Math.sin(2 * Math.PI * i * hz1 / sampleRate);
		}
		StdAudio.play(a); //plays the entire array of frequencies, creating an actual small sound
	}
	public static void termAudio () { //Termination Audio used in GUI - sound played when game terminates
		
		double hz1 = 440.0 * Math.pow(2.0, 0.0/12.0); //doubles for creating the adequate frequency for the sound
		double hz2 = 440.0 * Math.pow(2.0, 3.0/12.0);
		double hz3 = 440.0 * Math.pow(2.0, 5.0/12.0);
		double hz4 = 440.0 * Math.pow(2.0, 12.0/12.0);
		double seconds = 0.2; //double for the duration i want the sounds to be played for
		int sampleRate = 44100;
		int nN = (int) (seconds * sampleRate); //creates an int from the double used, in order to create arrays for the many short sounds (frequencies)
		double[] a = new double[nN+1]; //arrays for storing the many small sounds (frequencies)
		double[] b = new double[nN+1];
		double[] c = new double[nN+1];
		double[] d = new double[nN+1];
		for (int i = 0; i <= nN; i++) { //storing the frequencies in the arrays
			 a[i] = Math.sin(2 * Math.PI * i * hz1 / sampleRate);
			 b[i] = Math.sin(2 * Math.PI * i * hz2 / sampleRate);
			 c[i] = Math.sin(2 * Math.PI * i * hz3 / sampleRate);
			 d[i] = Math.sin(2 * Math.PI * i * hz4 / sampleRate);
		}
		StdAudio.play(a); //plays the entire array of frequencies, creating an actual small sound
		StdAudio.play(b);
		StdAudio.play(c);
		StdAudio.play(d);
	}
	public static void invalidAudio() { //Invalid Audio used in GUI - played when an invalid move is made
		double hz1 = 440.0 * Math.pow(2.0, 0.0/12.0); //double for creating the adequate frequency for the sound
		double seconds = 0.2; //double for the duration i want the sounds to be played for
		int sampleRate = 44100;
		int nN = (int) (seconds * sampleRate); //creates an int from the double used, in order to create an array for the many short sounds (frequencies)
		double[] a = new double[nN+1]; //array for storing the many small sounds (frequencies)
		for (int i = 0; i <= nN; i++) { //storing the frequencies in the array
			 a[i] = Math.sin(2 * Math.PI * i * hz1 / sampleRate);
		}
		StdAudio.play(a); //plays the entire array of frequencies, creating an actual small sound
	}
	public static void autoSolver (int n, int r, int c, int k) { //Automatic Solver - Note: The Automatic Solver does not work, however i tried it out, and left it here if you would like to take a look 
		
		String [][] board = new String [r][c]; //Array for Board of game
		for (int a = 0; a < r; a++) {  //To make open spaces on Board
			String O = "."; 
			board [a][0] = O;   
		} 
		for (int p = 0; p < r ;p++ ) { //To make closed spaces on Board
			for (int l = 1; l < c; l++) { 
				String C = "*"; 
				board [p][l] = C; 	
			}
		} 
		
		for (int im = 0; im < c; im++) {
			for (int jm = 0; jm < c; jm++) {
				if (board[im][jm].equals(".")) { //if a cell is open
					board = autoSolverTest1(im, jm, board, c, k, n); //call the test for Auto Solver to place color cells in open cells - this method returns the board back inorder to update the new board 
				}
			}
		}
		for (int i = 0; i < c; i++) { //prints the board
			for (int j = 0; j < c; j++) {
				StdOut.print(board[i][j]);
			}
			StdOut.println(); //skips a line after each row
		}
	}
	public static String[][] autoSolverTest1 (int im, int jm, String[][] board, int c, int k, int n) { //Automatic Solver Test - places color cells in open cells 
		
		//Note: for n = 2, the board is filled but however runs into impasse. For n = 3 it doesnt fill. 
		
		boolean iBg = false; //boolean for testing if blockade will be true if green was in the open cell (when called) (iBg stands for impasse Blockade-green)
		boolean iSg = false; //boolean for testing if splits will be true if green was in the open cell (when called) (iSg stands for impasse Splits-green)
		boolean iDEg = false; //boolean for testing if dead end will be true if green was in the open cell (when called) (iDEg stands for impasse DeadEnd-green)
		
		boolean iBy = false; //boolean for testing if blockade will be true if yellow was in the open cell (when called) (iBy stands for impasse Blockade-yellow)
		boolean iSy = false; //boolean for testing if splits will be true if yellow was in the open cell (when called) (iSy stands for impasse Splits-yellow)
		boolean iDEy = false; //boolean for testing if dead end will be true if yellow was in the open cell (when called) (iDEy stands for impasse DeadEnd-yellow)
		
		boolean iBr = false; //boolean for testing if blockade will be true if red was in the open cell (when called) (iBr stands for impasse Blockade-red)
		boolean iSr = false; //boolean for testing if splits will be true if red was in the open cell (when called) (iSr stands for impasse Splits-red)
		boolean iDEr = false; //boolean for testing if dead end will be true if red was in the open cell (when called) (iDEr stands for impasse DeadEnd-red)
		
		if (board[im][jm].equals(".")) { //if the cell is open
			
			board[im][jm] = "G"; //temporarily place green in the open cell
			iBg = blockade(c, c, iBg, board, k); //test for blockade
			iSg = splits(c, c, iSg, board); //test for splits
			String dEg = ""; //test for dead ends
			String[] ag = new String[c*c]; 
			boolean deg = false; 
			for (int q = 0; q < c; q++) {
				dEg = ""; 
				for (int i = 0; i < c*c; i++) {
					ag[i] = ""; 
				}
				deg = deadEnds(q, ag, dEg, c, board, deg); 
				if (deg == true) {
					q = c; 
					iDEg = true;
				}
			}
			
			board[im][jm] = "Y"; //temporarily place yellow in the open cell
			iBy = blockade(c, c, iBy, board, k); //test for blockade
			iSy = splits(c, c, iSy, board); //test for splits
			String dEy = ""; //test for dead ends
			String[] ay = new String[c*c]; 
			boolean dey = false; 
			for (int q = 0; q < c; q++) {
				dEy = ""; 
				for (int i = 0; i < c*c; i++) {
					ay[i] = ""; 
				}
				dey = deadEnds(q, ay, dEy, c, board, dey); 
				if (dey == true) {
					q = c; 
					iDEy = true;
				}
			}
			
			board[im][jm] = "R"; //temporarily place red in the open cell
			iBr = blockade(c, c, iBr, board, k); //test for blockade
			iSr = splits(c, c, iSr, board); //test for splits
			String dEr = ""; //test for dead ends
			String[] ar = new String[c*c]; 
			boolean der = false; 
			for (int q = 0; q < c; q++) {
				dEr = ""; 
				for (int i = 0; i < c*c; i++) {
					ar[i] = ""; 
				}
				der = deadEnds(q, ar, dEr, c, board, der); 
				if (der == true) {
					q = c; 
					iDEr = true;
				}
			}
			board[im][jm] = "."; //make the cell open again
			
			int count = 0;
			if (n == 2) { //if n is 2, only green and yellow matter
				if ((!iBg&&!iSg&&!iDEg)&&(!iBy&&!iSy&&!iDEy)) { //if no termination conditions hold for both green and yellow (can place either in the open cell)
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "G"; //make the open cell green
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "Y"; //make the open cell yellow
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
				else if (!iBg&&!iSg&&!iDEg) { //else if no termination conditions hold for green only
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "G"; //make the open cell green
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
				else if (!iBy&&!iSy&&!iDEy) { //else if no termination conditions hold for yellow only
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "Y"; //make the open cell yellow
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
			}
			else if (n == 3) { //if n is 3, the colors green, yellow and red matter
				if ((!iBg&&!iSg&&!iDEg)&&(!iBy&&!iSy&&!iDEy)&&(!iBr&&!iSr&&!iDEr)) { //if no termination conditions hold for all 3 green, yellow and red (can place any of the 3 in the open cell)
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "G"; //make the open cell green
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "Y"; //make the open cell yellow
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "R"; //make the open cell red
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
				else if ((!iBg&&!iSg&&!iDEg)&&(!iBy&&!iSy&&!iDEy)) { //if no termination conditions only hold for both green and yellow (can place either in the open cell)
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "G"; //make the open cell green
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "Y"; //make the open cell yellow
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
				else if ((!iBy&&!iSy&&!iDEy)&&(!iBr&&!iSr&&!iDEr)) { //if no termination conditions only hold for both red and yellow (can place either in the open cell)
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "Y"; //make the open cell yellow
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "R"; //make the open cell red
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
				else if ((!iBg&&!iSg&&!iDEg)&&(!iBr&&!iSr&&!iDEr)) { //if no termination conditions only hold for both green and red (can place either in the open cell)
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "G"; //make the open cell green
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "R"; //make the open cell red
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
				else if (!iBg&&!iSg&&!iDEg) { //else if no termination conditions hold for green only
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "G"; //make the open cell green
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
				else if (!iBy&&!iSy&&!iDEy) { //else if no termination conditions hold for yellow only
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "Y"; //make the open cell yellow
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
				else if (!iBr&&!iSr&&!iDEr) { //else if no termination conditions hold for red only
					
					count = 0; //re-assign count to 0 - used for counting open cells
					board[im][jm] = "R"; //make the open cell red
					if (jm != c-1) { //if its not the last cell in the row
						jm++; //increment the column number and make it a open cell
						board[im][jm] = ".";
					}
					for (int i = 0; i < c; i++) {
						for (int j = 0; j < c; j++) {
							if (board[i][j].equals(".")) {
								count++; //counts open cells
							}
						}
					}
					if (count != 0) { //if there are still open cells
						board = autoSolverTest1(im, jm, board, c, k, n); //call the auto solver test again
					}
				}
			}
		}
		return board; //returns board after recursive process finishes
	}
    public static void main(String[] args) {
    	
        boolean funcTest = true; 		//boolean for testing if funcTesttionality is supported 
    	String gameMode = "";			//String for Game Mode input (integer value of this input is initiated later in the code)
    	String gui = ""; 				//String for gui input (integer value of this input is initiated later in the code)
    	String colors = "";				//String for Amount of colors (n) input (integer value of this input is initiated later in the code)
    	String blockadeIn = ""; 		//String for Amount for a blockade (k) input (integer value of this input is initiated later in the code)
    	
    	if(args.length < 4) { //must have 4 arguments only
    		StdOut.println("Not enough arguments.");
    		funcTest = false; 
    	}
    	else if (args.length > 4) {
    		StdOut.println("Too many arguments.");
    		funcTest = false; 
    	}
    	if (funcTest == true) { //if 4 arguments are inputed
    		gameMode = args[0]; //Game Mode 
    		int gMode = Integer.parseInt(gameMode); //int for Game Mode
    		if ((gMode != 0)&&(gMode != 1)&&(gMode != 2)) {
    			StdOut.println("First input reset to default."); //If Game Mode was invalid 
    			gMode = 0; 
    		}
    		
    		gui = args[1]; //gui indicator
    		int guii = Integer.parseInt(gui); //int for gui
    		if ((guii != 0)&&(guii != 1)) {
    			StdOut.println("Second input reset to default."); //If gui was invalid
    			guii = 0; 
    		}  
    		
    		colors = args[2]; //Amount of colors (n)
    		int n = Integer.parseInt(colors); //int for Amount of colors (n)
    		if ((n != 2)&&(n != 3)&&(n != 4)) {
    			StdOut.println("Third input reset to default."); //If Amount of colors was invalid
    			n = 2; 
    		} 
    		
    		int r = 0; //int for Rows of Board
    		int c = 0; //int for Columns of Board
    		if (n == 2) { r = 8; } //if 2 colors were chosen to play with
    		else if (n == 3) { r = 30; } //if 3 colors were chosen to play with
    		else if (n == 4) { r = 128; } //if 4 colors were chosen to play with
    		c = r; 
    		
    		blockadeIn = args[3]; //Amount for a blockadeIn (k)
    		int k = Integer.parseInt(blockadeIn); //int for Amount for a blockadeIn (k)
        
    		if ((k < 3)||(k > r)) {
    			StdOut.println("Fourth input reset to default."); //If Amount for a blockadeIn was invalid
    			k = 3; 
    		}  
    		
    		if (gMode == 2&&guii == 1) {
    			guii = 0;
    		}
    		else if (gMode == 2&&n == 4) {
    			n = 2;
    		}
    		
    		
    		if (gMode == 0&&guii == 0) { //First Hand in Text Mode
    			firstHandInTextMode(n, r, c, k);
    		}
    		else if (gMode == 0&&guii == 1) { //First Hand in GUI Mode
    			firstHandInGUIMode(n, r, c, k);
    		}
    		else if (gMode == 1&&guii == 0) { //Second Hand in Text Mode
    			secondHandInTextMode(n, r, c, k);
    		}
    		else if (gMode == 1&&guii == 1) { //Second Hand in GUI Mode
    			secondHandInGUIMode(n, r, c, k);
    		}
    		else if (gMode == 2) { //Automatic Solver 
    			autoSolver(n, r, c, k);
    		}
        }   
    }
}