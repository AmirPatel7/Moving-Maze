import java.io.File;

/* Stellenbosch University CS144 Project 2022
 * MovingMaze.java
 * Name and surname: Amir Patel
 * Student number: 26034670
 */

/**
 * The Moving Maze class used for the {@code main} game loop of the game of Moving Maze.
 * @author Amir Patel 26034670
 *
 */
public class MovingMaze {

    // ==========================================================
    // Constants
    // ==========================================================

    // Move these where you'll use them, or refer to them with e.g. MovingMaze.PATH_NESW

    // ═ ║ ╔ ╗ ╚ ╝ ╠ ╣ ╦ ╩ ╬
    // ─ │ ┌ ┐ └ ┘ ├ ┤ ┬ ┴ ┼

    public static final String PATH_EW = "═";
    public static final String PATH_NS = "║";
    public static final String PATH_ES = "╔";
    public static final String PATH_SW = "╗";
    public static final String PATH_NE = "╚";
    public static final String PATH_NW = "╝";
    public static final String PATH_NES = "╠";
    public static final String PATH_NSW = "╣";
    public static final String PATH_ESW = "╦";
    public static final String PATH_NEW = "╩";
    public static final String PATH_NESW = "╬";

    public static final String BORDER_HORI = "─";
    public static final String BORDER_VERT = "│";
    public static final String BORDER_TOPLEFT = "┌";
    public static final String BORDER_TOPRIGHT = "┐";
    public static final String BORDER_BOTTOMLEFT = "└";
    public static final String BORDER_BOTTOMRIGHT = "┘";
    public static final String BORDER_LEFTEDGE = "├";
    public static final String BORDER_RIGHTEDGE = "┤";
    public static final String BORDER_TOPEDGE = "┬";
    public static final String BORDER_BOTTOMEDGE = "┴";
    public static final String BORDER_MIDDLE = "┼";
    
    /**
     * This method is called when the user wants to play the game in text mode.
     * @param w This is the width of the board, stored as an {@code int}.
     * @param h This is the height of the board, stored as an {@code int}.
     * @param r This is the amount of relics there are, stored as an {@code int}.
     * @param floatingTileTracker This is the starting floating tile (in encoding), stored as a {@code String}.
     * @param tracker This is the starting state of the board (in encodings), stored as a 2D array of type {@code String}.
     */
    public static void textMode (int w, int h, int r, String floatingTileTracker, String[][] tracker) {
    	
    	boolean gameLoop = true;
        String whoseTurn = "";
        String nextSlide = "";
        String nextMove = "";
        String prevSlide1 = " ";
        boolean moveMade = true;
        boolean quit = false;
        boolean win = false;
        boolean moveTerm = false;
        String[] receiver = new String[5];
    	
    	int greenRelicCollected = 0;
    	int yellowRelicCollected = 0;
    	int redRelicCollected = 0;
    	int blueRelicCollected = 0;
    	
    	gameState gS = new gameState (h, w, r, floatingTileTracker, tracker);
    	gS.initializeGame();
	    
	    win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
    	if (win) {
    		gameLoop = false;
    	}
    	
        for (int i = 0; i < 50; i++) {
			System.out.print("-");
		}
		System.out.println("\nMoving Maze");
		System.out.println("Relic goal: " + r);
		for (int i = 0; i < 50; i++) {
			System.out.print("-");
		}
		System.out.println();
		for (int j = 0; j < w; j++) {
    		for (int i = 0; i < h; i++) {
    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
    		}
    	}
    	gS.getFloatingTile(floatingTileTracker);
		gS.printBoard();
    	
    	while (gameLoop) {
	    	
    		if (!quit&&!win) {
    			
    			whoseTurn = "Green";
	    		receiver = gS.slidingMove(nextSlide, prevSlide1, whoseTurn, moveMade, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    		prevSlide1 = receiver[0];
	    		greenRelicCollected = Integer.parseInt(receiver[1]);
	    		yellowRelicCollected = Integer.parseInt(receiver[2]);
	    		redRelicCollected = Integer.parseInt(receiver[3]);
	    		blueRelicCollected = Integer.parseInt(receiver[4]);
	    		
	    		if (prevSlide1.charAt(0) == 'n') {
	    			prevSlide1 = Character.toString('s') + Character.toString(prevSlide1.charAt(1));
	    		}
	    		else if (prevSlide1.charAt(0) == 's') {
	    			prevSlide1 = Character.toString('n') + Character.toString(prevSlide1.charAt(1));
	    		}
	    		else if (prevSlide1.charAt(0) == 'e') {
	    			prevSlide1 = Character.toString('w') + Character.toString(prevSlide1.charAt(1));
	    		}
	    		else if (prevSlide1.charAt(0) == 'w') {
	    			prevSlide1 = Character.toString('e') + Character.toString(prevSlide1.charAt(1));
	    		}
	    		else if (prevSlide1.equals("quit")) {
	    			quit = true;
	    			gameLoop = false;
	    		}
	    		
	    		win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
		    	if (win) {
		    		gameLoop = false;
		    	}
	    		
	    		if (!quit&&!win) {
	    			
	    			for (int j = 0; j < w; j++) {
	    	    		for (int i = 0; i < h; i++) {
	    	    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    	    		}
	    	    	}
	    	    	gS.getFloatingTile(floatingTileTracker);
	        		gS.printBoard();
	    			
	    			greenRelicCollected = gS.movingMove(nextMove, whoseTurn, moveTerm, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
		    		
		    		if (greenRelicCollected >= 1000) {
			    		quit = true;
			    		gameLoop = false;
			    		greenRelicCollected = greenRelicCollected - 1000;
			    	}
		    		
		    		win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
			    	if (win) {
			    		gameLoop = false;
			    	}
		    		
		    		if (!quit&&!win) {
		    			
		    			System.out.println("Relics collected /" + r + ":");
		    	    	System.out.println("- Green  " + greenRelicCollected);
		    	    	System.out.println("- Yellow " + yellowRelicCollected);
		    	    	System.out.println("- Red    " + redRelicCollected);
		    	    	System.out.println("- Blue   " + blueRelicCollected);
		    			
		    	    	for (int j = 0; j < w; j++) {
		    	    		for (int i = 0; i < h; i++) {
		    	    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
		    	    		}
		    	    	}
		    	    	gS.getFloatingTile(floatingTileTracker);
		        		gS.printBoard();
			    		
				    	whoseTurn = "Yellow";
				    	receiver = gS.slidingMove(nextSlide, prevSlide1, whoseTurn, moveMade, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
				    	prevSlide1 = receiver[0];
			    		greenRelicCollected = Integer.parseInt(receiver[1]);
			    		yellowRelicCollected = Integer.parseInt(receiver[2]);
			    		redRelicCollected = Integer.parseInt(receiver[3]);
			    		blueRelicCollected = Integer.parseInt(receiver[4]);
				    	
				    	if (prevSlide1.charAt(0) == 'n') {
			    			prevSlide1 = Character.toString('s') + Character.toString(prevSlide1.charAt(1));
			    		}
			    		else if (prevSlide1.charAt(0) == 's') {
			    			prevSlide1 = Character.toString('n') + Character.toString(prevSlide1.charAt(1));
			    		}
			    		else if (prevSlide1.charAt(0) == 'e') {
			    			prevSlide1 = Character.toString('w') + Character.toString(prevSlide1.charAt(1));
			    		}
			    		else if (prevSlide1.charAt(0) == 'w') {
			    			prevSlide1 = Character.toString('e') + Character.toString(prevSlide1.charAt(1));
			    		}
			    		else if (prevSlide1.equals("quit")) {
			    			quit = true;
			    			gameLoop = false;
			    		}
			    		
			    		win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
				    	if (win) {
				    		gameLoop = false;
				    	}
			    		
			    		if (!quit&&!win) {
			    			
			    			for (int j = 0; j < w; j++) {
			    	    		for (int i = 0; i < h; i++) {
			    	    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
			    	    		}
			    	    	}
			    	    	gS.getFloatingTile(floatingTileTracker);
			        		gS.printBoard();
			    			
			    			yellowRelicCollected = gS.movingMove(nextMove, whoseTurn, moveTerm, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
				    		
				    		if (yellowRelicCollected >= 1000) {
					    		quit = true;
					    		gameLoop = false;
					    		yellowRelicCollected = yellowRelicCollected - 1000;
					    	}
				    		
				    		win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
					    	if (win) {
					    		gameLoop = false;
					    	}
				    		
				    		if (!quit&&!win) {
				    			
				    			System.out.println("Relics collected /" + r + ":");
				    	    	System.out.println("- Green  " + greenRelicCollected);
				    	    	System.out.println("- Yellow " + yellowRelicCollected);
				    	    	System.out.println("- Red    " + redRelicCollected);
				    	    	System.out.println("- Blue   " + blueRelicCollected);
				    			
				    	    	for (int j = 0; j < w; j++) {
				    	    		for (int i = 0; i < h; i++) {
				    	    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
				    	    		}
				    	    	}
				    	    	gS.getFloatingTile(floatingTileTracker);
				        		gS.printBoard();
						    	
					    		whoseTurn = "Red";
					    		receiver = gS.slidingMove(nextSlide, prevSlide1, whoseTurn, moveMade, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
					    		prevSlide1 = receiver[0];
					    		greenRelicCollected = Integer.parseInt(receiver[1]);
					    		yellowRelicCollected = Integer.parseInt(receiver[2]);
					    		redRelicCollected = Integer.parseInt(receiver[3]);
					    		blueRelicCollected = Integer.parseInt(receiver[4]);
					    		
					    		if (prevSlide1.charAt(0) == 'n') {
					    			prevSlide1 = Character.toString('s') + Character.toString(prevSlide1.charAt(1));
					    		}
					    		else if (prevSlide1.charAt(0) == 's') {
					    			prevSlide1 = Character.toString('n') + Character.toString(prevSlide1.charAt(1));
					    		}
					    		else if (prevSlide1.charAt(0) == 'e') {
					    			prevSlide1 = Character.toString('w') + Character.toString(prevSlide1.charAt(1));
					    		}
					    		else if (prevSlide1.charAt(0) == 'w') {
					    			prevSlide1 = Character.toString('e') + Character.toString(prevSlide1.charAt(1));
					    		}
					    		else if (prevSlide1.equals("quit")) {
					    			quit = true;
					    			gameLoop = false;
					    		}
					    		
					    		win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
						    	if (win) {
						    		gameLoop = false;
						    	}
					    		
					    		if (!quit&&!win) {
					    			
					    			for (int j = 0; j < w; j++) {
					    	    		for (int i = 0; i < h; i++) {
					    	    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
					    	    		}
					    	    	}
					    	    	gS.getFloatingTile(floatingTileTracker);
					        		gS.printBoard();
					    			
					    			redRelicCollected = gS.movingMove(nextMove, whoseTurn, moveTerm, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
						    		
						    		if (redRelicCollected >= 1000) {
							    		quit = true;
							    		gameLoop = false;
							    		redRelicCollected = redRelicCollected - 1000;
							    	}
						    		
						    		win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
							    	if (win) {
							    		gameLoop = false;
							    	}
						    		
						    		if (!quit&&!win) {
						    			
						    			System.out.println("Relics collected /" + r + ":");
						    	    	System.out.println("- Green  " + greenRelicCollected);
						    	    	System.out.println("- Yellow " + yellowRelicCollected);
						    	    	System.out.println("- Red    " + redRelicCollected);
						    	    	System.out.println("- Blue   " + blueRelicCollected);
						    			
						    	    	for (int j = 0; j < w; j++) {
						    	    		for (int i = 0; i < h; i++) {
						    	    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
						    	    		}
						    	    	}
						    	    	gS.getFloatingTile(floatingTileTracker);
						        		gS.printBoard();
							    		
							    		whoseTurn = "Blue";
							    		receiver = gS.slidingMove(nextSlide, prevSlide1, whoseTurn, moveMade, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
							    		prevSlide1 = receiver[0];
							    		greenRelicCollected = Integer.parseInt(receiver[1]);
							    		yellowRelicCollected = Integer.parseInt(receiver[2]);
							    		redRelicCollected = Integer.parseInt(receiver[3]);
							    		blueRelicCollected = Integer.parseInt(receiver[4]);
							    		
							    		if (prevSlide1.charAt(0) == 'n') {
							    			prevSlide1 = Character.toString('s') + Character.toString(prevSlide1.charAt(1));
							    		}
							    		else if (prevSlide1.charAt(0) == 's') {
							    			prevSlide1 = Character.toString('n') + Character.toString(prevSlide1.charAt(1));
							    		}
							    		else if (prevSlide1.charAt(0) == 'e') {
							    			prevSlide1 = Character.toString('w') + Character.toString(prevSlide1.charAt(1));
							    		}
							    		else if (prevSlide1.charAt(0) == 'w') {
							    			prevSlide1 = Character.toString('e') + Character.toString(prevSlide1.charAt(1));
							    		}
							    		else if (prevSlide1.equals("quit")) {
							    			quit = true;
							    			gameLoop = false;
							    		}
							    		
							    		win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
								    	if (win) {
								    		gameLoop = false;
								    	}
							    		
							    		if (!quit&&!win) {
							    			
							    			for (int j = 0; j < w; j++) {
							    	    		for (int i = 0; i < h; i++) {
							    	    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
							    	    		}
							    	    	}
							    	    	gS.getFloatingTile(floatingTileTracker);
							        		gS.printBoard();
							    			
							    			blueRelicCollected = gS.movingMove(nextMove, whoseTurn, moveTerm, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
									    	
									    	if (blueRelicCollected >= 1000) {
									    		quit = true;
									    		gameLoop = false;
									    		blueRelicCollected = blueRelicCollected - 1000;
									    	}
									    	
									    	win = gS.relicTest(win, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
									    	if (win) {
									    		gameLoop = false;
									    	}
									    	
									    	if (!quit&&!win) {
									    		
									    		System.out.println("Relics collected /" + r + ":");
										    	System.out.println("- Green  " + greenRelicCollected);
										    	System.out.println("- Yellow " + yellowRelicCollected);
										    	System.out.println("- Red    " + redRelicCollected);
										    	System.out.println("- Blue   " + blueRelicCollected);
									    		
										    	for (int j = 0; j < w; j++) {
								    	    		for (int i = 0; i < h; i++) {
								    	    			gS.getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
								    	    		}
								    	    	}
								    	    	gS.getFloatingTile(floatingTileTracker);
								        		gS.printBoard();
										    	
									    	}
							    		}
						    		}
					    		}
				    		}
			    		}
		    		}
	    		}
    		}
    	} //game is running thingie
    	
    	if (quit) {
    		System.out.println("Game has been quit.");
    	}
    	System.out.println("Relics collected /" + r + ":");
    	System.out.println("- Green  " + greenRelicCollected);
    	System.out.println("- Yellow " + yellowRelicCollected);
    	System.out.println("- Red    " + redRelicCollected);
    	System.out.println("- Blue   " + blueRelicCollected);
    }
    
    /**
     * This method is called when the user wants to play the game in graphical mode.
     * @param w This is the width of the board, stored as an {@code int}.
     * @param h This is the height of the board, stored as an {@code int}.
     * @param r This is the amount of relics there are, stored as an {@code int}.
     * @param floatingTileTracker This is the starting floating tile (in encoding), stored as a {@code String}.
     * @param tracker This is the starting state of the board (in encodings), stored as a 2D array of type {@code String}.
     */
    public static void guiMode (int w, int h, int r, String floatingTileTracker, String[][] tracker) {
    	
    	GUI gui = new GUI(w, h, r, floatingTileTracker, tracker);
    	
    }
    
    // ==========================================================
    // Main function
    // ==========================================================
    
    /**
     * Main execution of the {@link MovingMaze MovingMaze} game and game loop.
     * @param args This is the array of Strings of the command line arguments.
     */
    public static void main(String[] args) {
        
    	boolean functionality = true;
        
        if (args.length != 2) {
        	functionality = false;
        }
    	if (functionality) {
    		File hey = new File (args[0]);
        	if (!hey.exists()) {
        		System.out.println("The game board file does not exist.");
        		functionality = false;
        	}
    	}
    	if (functionality) {
    		if (!args[1].equals("text")&&!args[1].equals("gui")) {
    			System.out.println("Unknown visual mode.");
    			functionality = false;
    		}
    	}
    	if (functionality) {
    		
    		In reader = new In(args[0]);
	    	int w = reader.readInt();
	    	int h = reader.readInt();
	    	
	    	int r = reader.readInt();
	    	
	    	String floatingTileTracker = reader.readString();
	    	String[][] tracker = new String[h][w];
	    	for (int i = 0; i < h; i++) {
	    		for (int j = 0; j < w; j++) {
	    			tracker[i][j] = reader.readString();
	    		}
	    	}
	    	
	    	if (args[1].equals("text")) {
	    		textMode(w, h, r, floatingTileTracker, tracker);
	    	}
	    	else if (args[1].equals("gui")) {
	    		guiMode(w, h, r, floatingTileTracker, tracker);
	    	}
	    	
    	}
    }
    
    
    
    // ==========================================================
    // Test API functions
    // ==========================================================

    // Populate these with high-level code that references your codebase.

    // ----------------------------------------------------------
    // First hand-in
    
    /**
     * Tests if the Tile has a pathway on it in the specified direction. 
     * @param tileEncoding The tile encoding that wants to be tested.
     * @param dir The specified direction. 
     * @return Returns {@code true} if the tile encoded as {@code tileEncoding} is open in the direction indicated.
     */
    public static boolean isTileOpenToSide(String tileEncoding, char dir) {
        
    	boolean isItDoe = false;
    	gameState gameState = new gameState(tileEncoding);
    	isItDoe = gameState.openOrNah(dir);
    	
    	return isItDoe;
    }
    
    /**
     * Take the tile encoded with {@code tileEncoding} and rotate it once clockwise.
     * @param tileEncoding The tile encoding that wants to be rotated.
     * @return Return a boolean array with length 4 – each element in this boolean array must indicate whether the rotated
     * tile is open to a specific side. The order of sides is north, east, south, west.
     */
    public static boolean[] rotateTileClockwise(String tileEncoding) {
        
    	boolean[] rotation = new boolean[4];
    	gameState gameState = new gameState(tileEncoding);
    	rotation = gameState.rotateTileClockwise();
    	
    	return rotation;
    }
    
    /**
     * Take the tile encoded with {@code tileEncoding} and rotate it once anti-clockwise.
     * @param tileEncoding The tile encoding that wants to be rotated.
     * @return Return a boolean array with length 4 – each element in this boolean array must indicate whether the rotated
     * tile is open to a specific side. The order of sides is north, east, south, west.
     */
    public static boolean[] rotateTileCounterclockwise(String tileEncoding) {
    	
    	boolean[] rotation = new boolean[4];
    	gameState gameState = new gameState(tileEncoding);
    	rotation = gameState.rotateTileCounterclockwise();
    	
    	return rotation;
    }
    
    /**
     * Take the floating tile encoded with {@code floatingTileEncoding} insert it at the sliding position {@code slidingIndicator} into
     * the maze encoded with the 2D array of strings {@code mazeTileEncodings}
     * @param mazeTileEncodings This is the 2D array of the encodings of the maze.
     * @param floatingTileEncoding This is the encoding of the floating tile.
     * @param slidingIndicator This is where the tile wants to be slid.
     * @return Returns a boolean array of length 4. 
     * Each element in this boolean array must indicate whether the new floating tile (after the slide is performed) is open to a specific side. 
     * The order of sides is north, east, south, west. 
     */
    public static boolean[] slideTileIntoMaze1(String[][] mazeTileEncodings, String floatingTileEncoding, String slidingIndicator) {
        
    	gameState gameState = new gameState(mazeTileEncodings);
    	floatingTileEncoding = gameState.getNewFloatingTile(floatingTileEncoding, slidingIndicator);
    	
    	boolean[] newFloatEncodings = new boolean[4];
    	
    	if (floatingTileEncoding.charAt(0) == '1') {
    		newFloatEncodings[0] = true;
    	}
    	if (floatingTileEncoding.charAt(1) == '1') {
    		newFloatEncodings[1] = true;
    	}
    	if (floatingTileEncoding.charAt(2) == '1') {
    		newFloatEncodings[2] = true;
    	}
    	if (floatingTileEncoding.charAt(3) == '1') {
    		newFloatEncodings[3] = true;
    	}
    	return newFloatEncodings; 
    }
    
    /**
     * Determines if a player can move in the specified direction. 
     * @param curTileEncoding This is the current tile encoding that the player lies on.
     * @param newTileEncoding This is the next tile encoding in the direction specified.
     * @param dir This is the direction specified.
     * @return Returns {@code true} if the tile encoded as {@code newTileEncoding} can be stepped to from the tile encoded as 
     * {@code curTileEncoding} if {@code newTileEncoding} is in direction {@code dir} from {@code curTileEncoding}. 
     * Returns {@code false} if not.
     */
    public static boolean canMoveInDirection(String curTileEncoding, String newTileEncoding, char dir) {
        
    	boolean canYouDoe = false;
    	gameState gameState = new gameState(curTileEncoding);
    	canYouDoe = gameState.canPlayaMoov(newTileEncoding, dir);
    	
    	return canYouDoe; 
    }
    
    /**
     * Determines if the player who starts in the top-left corner can move in a sequence of given directions.
     * @param mazeTileEncodings This is the 2D String array of the board of tiles in the form of encodings.
     * @param steps This is the sequence of directions given as a char array.
     * @return Returns {@code true} if an adventurer starting in the top-left corner can successfully complete 
     * the steps in the directions contained in {@code steps} 
     */
    public static boolean canMoveAlongPath(String[][] mazeTileEncodings, char[] steps) {
        
    	boolean canYouDoe = true;
    	gameState gameState = new gameState(mazeTileEncodings);
    	canYouDoe = gameState.canPlayaMoovPath(steps);
    	
    	return canYouDoe; 
    }

    // ----------------------------------------------------------
    // Second hand-in
    
    /**
     * Determines if a tile has a relic on it or not.
     * @param tileEncoding This is the encoding of the tile that wants to be tested.
     * @return Returns {@code true} if the tile encoded with tileEncoding has a relic on it, 
     * and returns {@code false} if not.
     */
    public static boolean tileHasRelic(String tileEncoding) {
        
    	boolean hasRelic = false;
    	gameState gameState = new gameState(tileEncoding);
    	hasRelic = gameState.hasRelic();
    	
    	return hasRelic; 
    }
    
    /**
     * Take the floating tile encoded with {@code floatingTileEncoding} insert it at the sliding position 
     * {@code slidingIndicator} into the maze encoded with the 2D array of strings {@code mazeTileEncodings}
     * @param mazeTileEncodings This is the 2D String array of the board of tiles in the form of encodings. 
     * @param floatingTileEncoding This is the encoding of the floating tile.
     * @param slidingIndicator This is where the tile wants to be slid.
     * @return Returns a Java character. This character represents the relic on the new floating tile. 
     * If it has a relic (regardless of its collection order number), 
     * return its lowercase initial; if it has no relic, return ‘x’.
     */
    public static char slideTileIntoMaze2(String[][] mazeTileEncodings, String floatingTileEncoding, String slidingIndicator) {
        
    	char returnStatement = '?';
    	gameState gameState = new gameState(mazeTileEncodings);
    	returnStatement = gameState.slideTwo(floatingTileEncoding, slidingIndicator);
    	
    	return returnStatement;
    }
}
