/**
 * This class is responsible for tracking the state of the game and all its variables.
 * @author Amir Patel 26034670
 * 
 */
public class gameState {
	
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
    
	private int w = 0, h = 0, r = 0;
	private int th, tw;
	private String floatingTileTracker;
	private String[][] floatingTile;
	private String[][] tracker;
    char firstCharNP = ' ';
    String secondStringNP = "";
    int secondIntNP = 0;
    int insertRow = 0;
    int insertColumn = 0;
    private String[][] wholeBoard;
    private String tileEncoding;
    private int[][] arrayPathFinding;
    private char[] dirs = {'n', 'e', 's', 'w'};
    private int[] greenPos, yellowPos, redPos, bluePos, greenPosW, yellowPosW, redPosW, bluePosW, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos;
	
    /**
     * This constructor is used if the user wants to actually play the game as it will track everything in the game.
     * @param height This is the height of the board, stored as an {@code int}.
     * @param width This is the width of the board, stored as an {@code int}.
     * @param relics This is the amount of relics there are, stored as an {@code int}.
     * @param fTT This is the starting floating tile (in encoding), stored as a {@code String}.
     * @param track This is the starting state of the board (in encodings), stored as a 2D array of type {@code String}.
     */
    public gameState (int height, int width, int relics, String fTT, String[][] track) {
		w = width;
		h = height;
		r = relics;
    	floatingTileTracker = fTT;
    	tracker = track;
    	floatingTile = new String[5][9];
    	tw = (w*7) + (w+1) + 2;
    	th = (h*3) + (h+1) + 2;
    	wholeBoard = new String[th][tw];
    	arrayPathFinding = new int[h][w];
    	greenPos = new int [2];
    	greenPos[0] = 0;
    	greenPos[1] = 0;
    	yellowPos = new int [2];
    	yellowPos[0] = 0;
    	yellowPos[1] = w-1;
    	redPos = new int [2];
    	redPos[0] = h-1;
    	redPos[1] = 0;
    	bluePos = new int [2];
    	bluePos[0] = h-1;
    	bluePos[1] = w-1;
    	greenPosW = new int [2];
    	greenPosW[0] = 2;
    	greenPosW[1] = 3;
    	yellowPosW = new int [2];
    	yellowPosW[0] = 2;
    	yellowPosW[1] = tw-4;
    	redPosW = new int [2];
    	redPosW[0] = th-3;
    	redPosW[1] = 3;
    	bluePosW = new int [2];
    	bluePosW[0] = th-3;
    	bluePosW[1] = tw-4; 
    	greenRelicPos = new int [2];
    	greenRelicPos[0] = 200;
    	greenRelicPos[1] = 200;
    	yellowRelicPos = new int [2];
    	yellowRelicPos[0] = 200;
    	yellowRelicPos[1] = 200;
    	redRelicPos = new int [2];
    	redRelicPos[0] = 200;
    	redRelicPos[1] = 200;
    	blueRelicPos = new int [2];
    	blueRelicPos[0] = 200;
    	blueRelicPos[1] = 200;
	}
	
	/**
	 * Initializes the game by assigning objects to their respective places on the board.
	 */
	public void initializeGame () {
		
		for (int i = 0; i < th; i++) {
    		for (int j = 0; j < tw; j++) {
    			wholeBoard[i][j] = " ";
    		}
    	}
    	for (int i = 0; i < 5; i++) {
    		for (int j = 0; j < 9; j++) {
    			floatingTile[i][j] = " ";
    		}
    	}
    	
    	for (int i = 5, j = 1; i < tw; i+=8, j++) {
    		wholeBoard[0][i] = Integer.toString(j);
    		wholeBoard[th-1][i] = Integer.toString(j);
    	}
    	for (int i = 3, j = 1; i < th; i+=4, j++) {
    		wholeBoard[i][0] = Integer.toString(j);
    		wholeBoard[i][tw-1] = Integer.toString(j);
    	}
    	
    	wholeBoard[1][1] = BORDER_TOPLEFT;
    	wholeBoard[1][tw-2] = BORDER_TOPRIGHT;
    	wholeBoard[th-2][1] = BORDER_BOTTOMLEFT;
    	wholeBoard[th-2][tw-2] = BORDER_BOTTOMRIGHT;
    	
    	for (int i = 1; i < th-1; i+=4) {
    		for (int j = 2; j < tw-2; j++) {
    			wholeBoard[i][j] = BORDER_HORI;
    		}
    	}
    	for (int j = 1; j < tw-1; j+=8) {
    		for (int i = 2; i < th-2; i++) {
    			wholeBoard[i][j] = BORDER_VERT;
    		}
    	}
    	
    	for (int i = 5; i < th-2; i+=4) {
    		wholeBoard[i][1] = BORDER_LEFTEDGE;
    	}
    	for (int i = 5; i < th-2; i+=4) {
    		wholeBoard[i][tw-2] = BORDER_RIGHTEDGE;
    	}
    	for (int i = 9; i < tw-2; i+=8) {
    		wholeBoard[1][i] = BORDER_TOPEDGE;
    	}
    	for (int i = 9; i < tw-2; i+=8) {
    		wholeBoard[th-2][i] = BORDER_BOTTOMEDGE;
    	}
    	
    	for (int i = 5; i < th-2; i+=4) {
    		for (int j = 9; j < tw-2; j+=8) {
    			wholeBoard[i][j] = BORDER_MIDDLE;
    		}
    	}
    	
    	floatingTile[0][0] = BORDER_TOPLEFT;
    	floatingTile[0][8] = BORDER_TOPRIGHT;
    	floatingTile[4][0] = BORDER_BOTTOMLEFT;
    	floatingTile[4][8] = BORDER_BOTTOMRIGHT;
    	
    	for (int i = 1; i < 8; i++) {
    		floatingTile[0][i] = BORDER_HORI;
    		floatingTile[4][i] = BORDER_HORI;
    	}
    	for (int i = 1; i < 4; i++) {
    		floatingTile[i][0] = BORDER_VERT;
    		floatingTile[i][8] = BORDER_VERT;
    	}
    	wholeBoard[2][3] = "G";
        wholeBoard[2][tw-4] = "Y";
        wholeBoard[th-3][3] = "R";
        wholeBoard[th-3][tw-4] = "B";
	}
	
	/**
	 * Updates the current state of the board.
	 * <p>
	 * This update happens tile by tile. 
	 * This method therefore needs to be called in a loop to update the entire board.
	 * {@code greenRelicCollected}, {@code yellowRelicCollected}, {@code redRelicCollected} and {@code blueRelicCollected} 
	 * are parameters because it is needed for the getTile method in the {@link Tile Tile} class.
	 * @param code This is the Tile's updated code, stored as a {@code String}.
	 * @param ch This is the current height of where the Tile lies on the board, stored as an {@code int}. 
	 * @param cw This is the current width of where the Tile lies on the board, stored as an {@code int}.
	 * @param greenRelicCollected This is the amount of relics green has collected, stored as an {@code int}.
	 * @param yellowRelicCollected This is the amount of relics yellow has collected, stored as an {@code int}.
	 * @param redRelicCollected This is the amount of relics red has collected, stored as an {@code int}.
	 * @param blueRelicCollected This is the amount of relics blue has collected, stored as an {@code int}.
	 */
	public void getBoard (String code, int ch, int cw, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected) {
		
		String[][] actualTile = new String[3][7];
		Tile currentTile = new Tile (code); 
		actualTile = currentTile.getTile(currentTile, ch, cw, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
		
		int wbhS = 0;
		int wbhE = 0;
		int wbwS = 0;
		int wbwE = 0;
		if (ch == 0) {
			wbhS = 2;
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 1) {
			wbhS = 6; 
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 2) {
			wbhS = 10; 
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 3) {
			wbhS = 14; 
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 4) {
			wbhS = 18; 
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 5) {
			wbhS = 22; 
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 6) {
			wbhS = 26; 
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 7) {
			wbhS = 30; 
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 8) {
			wbhS = 34; 
			wbhE = (wbhS + 4 - 2);
		}
		else if (ch == 9) {
			wbhS = 38; 
			wbhE = (wbhS + 4 - 2);
		}
		
		if (cw == 0) {
			wbwS = 2;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 1) {
			wbwS = 10;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 2) {
			wbwS = 18;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 3) {
			wbwS = 26;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 4) {
			wbwS = 34;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 5) {
			wbwS = 42;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 6) {
			wbwS = 50;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 7) {
			wbwS = 58;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 8) {
			wbwS = 66;
			wbwE = (wbwS + 8 -2);
		}
		else if (cw == 9) {
			wbwS = 74;
			wbwE = (wbwS + 8 -2);
		}
		for (int i = wbhS, y = 0; i <= wbhE; i++, y++) {
			for (int j = wbwS, z = 0; j <= wbwE; j++, z++) {
				if (!wholeBoard[i][j].equals("G")&&!wholeBoard[i][j].equals("Y")&&!wholeBoard[i][j].equals("R")&&!wholeBoard[i][j].equals("B")) {
					wholeBoard[i][j] = actualTile[y][z];
				}
			}
		}
	}
	
	/**
	 * Updates the current state of the floating tile. 
	 * @param code This is the encoding of the floating Tile, stored as a {@code String}.
	 */
	public void getFloatingTile (String code) {
		
		String[][] actualTile = new String[3][7];
		Tile currentFloatingTile = new Tile (floatingTileTracker);
		actualTile = currentFloatingTile.getFloatingTile(currentFloatingTile, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, r);
		
		for (int i = 1, y = 0; i <= 3; i++, y++) {
			for (int j = 1, z = 0; j <= 7; j++, z++) {
				floatingTile[i][j] = actualTile[y][z];
			}
		}
	}
	
	/**
	 * Prints the current state of the board in {@code String} form.
	 */
	public void printBoard () {
		System.out.println();
    	
    	for (int i = 0; i < th; i++) {
    		for (int j = 0; j < tw; j++) {
    			System.out.print(wholeBoard[i][j]);
    		}
    		System.out.println();
    	}
    	
    	System.out.println();
    	
    	for (int i = 0; i < 5; i++) {
    		for (int j = 0; j < 9; j++) {
    			System.out.print(floatingTile[i][j]);
    		}
    		System.out.println();
    	}
    	
    	System.out.println();
	}
	
	/**
	 * Tests for relics.
	 * <p>
	 * This method will test which relics are active or still hidden. 
	 * It will update the relic's position if any relic was collected. 
	 * It also tests if a player has won (if he has collected all his relics and moved to his original position)
	 * @param win This is a boolean that is returned used to determine if a player has won or not, stored as a {@code boolean}.
	 * @param greenRelicCollected This is the amount of relics green has collected, stored as an {@code int}.
	 * @param yellowRelicCollected This is the amount of relics yellow has collected, stored as an {@code int}.
	 * @param redRelicCollected This is the amount of relics red has collected, stored as an {@code int}.
	 * @param blueRelicCollected This is the amount of relics blue has collected, stored as an {@code int}.
	 * @return {@code win} is returned stating whether a player has won or not.
	 */
	public boolean relicTest (boolean win, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected) {
    	
    	if (r > 0) {
	    	for (int i = 0; i < h; i++) {
	        	for (int j = 0; j < w; j++) {
	        		if (tracker[i][j].charAt(4) == 'g'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(greenRelicCollected + 1))) {
	        			greenRelicPos[0] = i;
	        			greenRelicPos[1] = j; 
	        		}
	        		if (tracker[i][j].charAt(4) == 'y'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(yellowRelicCollected + 1))) {
	        			yellowRelicPos[0] = i;
	        			yellowRelicPos[1] = j; 
	        		}
	        		if (tracker[i][j].charAt(4) == 'r'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(redRelicCollected + 1))) {
	        			redRelicPos[0] = i;
	        			redRelicPos[1] = j; 
	        		}
	        		if (tracker[i][j].charAt(4) == 'b'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(blueRelicCollected + 1))) {
	        			blueRelicPos[0] = i;
	        			blueRelicPos[1] = j; 
	        		}
	        	}
	        }
	        if (floatingTileTracker.charAt(4) == 'g'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(greenRelicCollected + 1))) {
	        	greenRelicPos[0] = 100;
    			greenRelicPos[1] = 100; 
	        }
	        if (floatingTileTracker.charAt(4) == 'y'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(yellowRelicCollected + 1))) {
	        	yellowRelicPos[0] = 100;
    			yellowRelicPos[1] = 100; 
	        }
	        if (floatingTileTracker.charAt(4) == 'r'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(redRelicCollected + 1))) {
	        	redRelicPos[0] = 100;
    			redRelicPos[1] = 100;  
	        }
	        if (floatingTileTracker.charAt(4) == 'b'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(blueRelicCollected + 1))) {
	        	blueRelicPos[0] = 100;
    			blueRelicPos[1] = 100;
	        }
	        if (r == greenRelicCollected) {
	        	greenRelicPos[0] = 300;
    			greenRelicPos[1] = 300; 
	        }
	        if (r == yellowRelicCollected) {
	        	yellowRelicPos[0] = 300;
    			yellowRelicPos[1] = 300; 
	        }
	        if (r == redRelicCollected) {
	        	redRelicPos[0] = 300;
    			redRelicPos[1] = 300; 
	        }
	        if (r == blueRelicCollected) {
	        	blueRelicPos[0] = 300;
    			blueRelicPos[1] = 300;
	        }
	        if (r == greenRelicCollected&&greenPos[0] == 0&&greenPos[1] == 0) {
	        	win = true;
	        	System.out.println("Green has won.");
	        }
	        if (r == yellowRelicCollected&&yellowPos[0] == 0&&yellowPos[1] == w-1) {
	        	win = true;
	        	System.out.println("Yellow has won.");
	        }
	        if (r == redRelicCollected&&redPos[0] == h-1&&redPos[1] == 0) {
	        	win = true;
	        	System.out.println("Red has won.");
	        }
	        if (r == blueRelicCollected&&bluePos[0] == h-1&&bluePos[1] == w-1) {
	        	win = true;
	        	System.out.println("Blue has won.");
	        }
	    }
    	
    	return win;
    }
	
	/**
	 * Inserts the floating tile into the specified position. 
	 * @param nextSlide This updates the player's input (stores whether he wants to slide or rotate), stored as a {@code String}.
	 * @param prevSlide This is the last exit point from the previous slide, stored as a {@code String}.
	 * @param whoseTurn This is whoever's turn it is, stored as a {@code String}.
	 * @param moveMade This is a boolean to determine if the player actually slid or just rotated, stored as a {@code boolean}.
	 * @param greenRelicCollected This is the amount of relics green has collected, stored as an {@code int}.
	 * @param yellowRelicCollected This is the amount of relics yellow has collected, stored as an {@code int}.
	 * @param redRelicCollected This is the amount of relics red has collected, stored as an {@code int}.
	 * @param blueRelicCollected This is the amount of relics blue has collected, stored as an {@code int}.
	 * @return Returns 5 things in the form of a String array : {@code nextSlide} is returned to form {@code prevSlide} for the next sliding move. The other 4 are Relic Collected values.
	 */
    public String[] slidingMove (String nextSlide, String prevSlide, String whoseTurn, boolean moveMade, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected) {
    	
    	String [][] temp = new String[h][w];
    	for (int i = 0; i < h; i++) {
    		for (int j = 0; j < w; j++) {
    			temp[i][j] = " ";
    		}
    	}
    	
    	do {
    		nextSlide = validationTestSlide(whoseTurn, nextSlide, prevSlide);
	    	if (nextSlide.equals("r")) {
	    		floatingTileTracker = rotateTileRight();
	    		System.out.println("Rotating right.");
	    		for (int j = 0; j < w; j++) {
		    		for (int i = 0; i < h; i++) {
		    			getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
		    		}
		    	}
		    	getFloatingTile(floatingTileTracker);
	    		printBoard();
	    		moveMade = false;
	    	}
	    	else if (nextSlide.equals("l")) {
	    		floatingTileTracker = rotateTileLeft();
	    		System.out.println("Rotating left.");
	    		for (int j = 0; j < w; j++) {
		    		for (int i = 0; i < h; i++) {
		    			getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
		    		}
		    	}
		    	getFloatingTile(floatingTileTracker);
	    		printBoard();
	    		moveMade = false;
	    	}
	    	else {
	    		moveMade = true;
	    	}
		} while (!moveMade);
    	wholeBoard[greenPosW[0]][greenPosW[1]] = " ";
    	wholeBoard[yellowPosW[0]][yellowPosW[1]] = " ";
    	wholeBoard[redPosW[0]][redPosW[1]] = " ";
    	wholeBoard[bluePosW[0]][bluePosW[1]] = " ";
		
    	if (nextSlide.charAt(0) == 'n') {
    		insertRow = 0;
    		insertColumn = Integer.parseInt(String.valueOf(nextSlide.charAt(1))) - 1;
    		if (greenPos[1] == insertColumn&&greenPos[0] != h-1) {
    			greenPos[0] = greenPos[0] + 1;
    			greenPosW[0] = greenPosW[0] + 4;
    		}
    		else if (greenPos[1] == insertColumn&&greenPos[0] == h-1) {
    			greenPos[0] = 0;
    			greenPosW[0] = 2;
    		}
    		if (yellowPos[1] == insertColumn&&yellowPos[0] != h-1) {
    			yellowPos[0] = yellowPos[0] + 1;
    			yellowPosW[0] = yellowPosW[0] + 4;
    		}
    		else if (yellowPos[1] == insertColumn&&yellowPos[0] == h-1) {
    			yellowPos[0] = 0;
    			yellowPosW[0] = 2;
    		}
    		if (redPos[1] == insertColumn&&redPos[0] != h-1) {
    			redPos[0] = redPos[0] + 1;
    			redPosW[0] = redPosW[0] + 4;
    		}
    		else if (redPos[1] == insertColumn&&redPos[0] == h-1) {
    			redPos[0] = 0;
    			redPosW[0] = 4;
    		}
    		if (bluePos[1] == insertColumn&&bluePos[0] != h-1) {
    			bluePos[0] = bluePos[0] + 1;
    			bluePosW[0] = bluePosW[0] + 4;
    		}
    		else if (bluePos[1] == insertColumn&&bluePos[0] == h-1) {
    			bluePos[0] = 0;
    			bluePosW[0] = 4;
    		}
	    	for (int i = 0; i < h; i++) {
	    		temp[i][insertColumn] = tracker[i][insertColumn];
	    	}
	    	tracker[insertRow][insertColumn] = floatingTileTracker;
	    	floatingTileTracker = tracker[h-1][insertColumn];
	    	for (int i = 1; i < h; i++) {
	    		tracker[i][insertColumn] = temp[i-1][insertColumn];
	    	}
    	}
    	else if (nextSlide.charAt(0) == 's') {
    		insertRow = h-1;
    		insertColumn = Integer.parseInt(String.valueOf(nextSlide.charAt(1))) - 1;
    		if (greenPos[1] == insertColumn&&greenPos[0] != 0) {
    			greenPos[0] = greenPos[0] - 1;
    			greenPosW[0] = greenPosW[0] - 4;
    		}
    		else if (greenPos[1] == insertColumn&&greenPos[0] == 0) {
    			greenPos[0] = h-1;
    			greenPosW[0] = th-5;
    		}
    		if (yellowPos[1] == insertColumn&&yellowPos[0] != 0) {
    			yellowPos[0] = yellowPos[0] - 1;
    			yellowPosW[0] = yellowPosW[0] - 4;
    		}
    		else if (yellowPos[1] == insertColumn&&yellowPos[0] == 0) {
    			yellowPos[0] = h-1;
    			yellowPosW[0] = th-5;
    		}
    		if (redPos[1] == insertColumn&&redPos[0] != 0) {
    			redPos[0] = redPos[0] - 1;
    			redPosW[0] = redPosW[0] - 4;
    		}
    		else if (redPos[1] == insertColumn&&redPos[0] == 0) {
    			redPos[0] = h-1;
    			redPosW[0] = th-3;
    		}
    		if (bluePos[1] == insertColumn&&bluePos[0] != 0) {
    			bluePos[0] = bluePos[0] - 1;
    			bluePosW[0] = bluePosW[0] - 4;
    		}
    		else if (bluePos[1] == insertColumn&&bluePos[0] == 0) {
    			bluePos[0] = h-1;
    			bluePosW[0] = th-3;
    		}
    		for (int i = 0; i < h; i++) {
	    		temp[i][insertColumn] = tracker[i][insertColumn];
	    	}
	    	tracker[insertRow][insertColumn] = floatingTileTracker;
	    	floatingTileTracker = tracker[0][insertColumn];
	    	for (int i = 0; i < h-1; i++) {
	    		tracker[i][insertColumn] = temp[i+1][insertColumn];
	    	}
    	}
    	else if (nextSlide.charAt(0) == 'w') {
    		insertColumn = 0;
    		insertRow = Integer.parseInt(String.valueOf(nextSlide.charAt(1))) - 1;
    		if (greenPos[0] == insertRow&&greenPos[1] != w-1) {
    			greenPos[1] = greenPos[1] + 1;
    			greenPosW[1] = greenPosW[1] + 8;
    		}
    		else if (greenPos[0] == insertRow&&greenPos[1] == w-1) {
    			greenPos[1] = 0;
    			greenPosW[1] = 3;
    		}
    		if (yellowPos[0] == insertRow&&yellowPos[1] != w-1) {
    			yellowPos[1] = yellowPos[1] + 1;
    			yellowPosW[1] = yellowPosW[1] + 8;
    		}
    		else if (yellowPos[0] == insertRow&&yellowPos[1] == w-1) {
    			yellowPos[1] = 0;
    			yellowPosW[1] = 7;
    		}
    		if (redPos[0] == insertRow&&redPos[1] != w-1) {
    			redPos[1] = redPos[1] + 1;
    			redPosW[1] = redPosW[1] + 8;
    		}
    		else if (redPos[0] == insertRow&&redPos[1] == w-1) {
    			redPos[1] = 0;
    			redPosW[1] = 3;
    		}
    		if (bluePos[0] == insertRow&&bluePos[1] != w-1) {
    			bluePos[1] = bluePos[1] + 1;
    			bluePosW[1] = bluePosW[1] + 8;
    		}
    		else if (bluePos[0] == insertRow&&bluePos[1] == w-1) {
    			bluePos[1] = 0;
    			bluePosW[1] = 7;
    		}
    		for (int i = 0; i < w; i++) {
	    		temp[insertRow][i] = tracker[insertRow][i];
	    	}
	    	tracker[insertRow][insertColumn] = floatingTileTracker;
	    	floatingTileTracker = tracker[insertRow][w-1];
	    	for (int i = 1; i < w; i++) {
	    		tracker[insertRow][i] = temp[insertRow][i-1];
	    	}
    	}
    	else if (nextSlide.charAt(0) == 'e') {
    		insertColumn = w-1;
    		insertRow = Integer.parseInt(String.valueOf(nextSlide.charAt(1))) - 1;
    		if (greenPos[0] == insertRow&&greenPos[1] != 0) {
    			greenPos[1] = greenPos[1] - 1;
    			greenPosW[1] = greenPosW[1] - 8;
    		}
    		else if (greenPos[0] == insertRow&&greenPos[1] == 0) {
    			greenPos[1] = w-1;
    			greenPosW[1] = tw-8;
    		}
    		if (yellowPos[0] == insertRow&&yellowPos[1] != 0) {
    			yellowPos[1] = yellowPos[1] - 1;
    			yellowPosW[1] = yellowPosW[1] - 8;
    		}
    		else if (yellowPos[0] == insertRow&&yellowPos[1] == 0) {
    			yellowPos[1] = w-1;
    			yellowPosW[1] = tw-4;
    		}
    		if (redPos[0] == insertRow&&redPos[1] != 0) {
    			redPos[1] = redPos[1] - 1;
    			redPosW[1] = redPosW[1] - 8;
    		}
    		else if (redPos[0] == insertRow&&redPos[1] == 0) {
    			redPos[1] = w-1;
    			redPosW[1] = tw-8;
    		}
    		if (bluePos[0] == insertRow&&bluePos[1] != 0) {
    			bluePos[1] = bluePos[1] - 1;
    			bluePosW[1] = bluePosW[1] - 8;
    		}
    		else if (bluePos[0] == insertRow&&bluePos[1] == 0) {
    			bluePos[1] = w-1;
    			bluePosW[1] = tw-4;
    		}
    		for (int i = 0; i < w; i++) {
	    		temp[insertRow][i] = tracker[insertRow][i];
	    	}
	    	tracker[insertRow][insertColumn] = floatingTileTracker;
	    	floatingTileTracker = tracker[insertRow][0];
	    	for (int i = 0; i < w-1; i++) {
	    		tracker[insertRow][i] = temp[insertRow][i+1];
	    	}
    	}
    	
    	wholeBoard[greenPosW[0]][greenPosW[1]] = "G";
    	wholeBoard[yellowPosW[0]][yellowPosW[1]] = "Y";
    	wholeBoard[redPosW[0]][redPosW[1]] = "R";
    	wholeBoard[bluePosW[0]][bluePosW[1]] = "B";
    	if (!nextSlide.equals("quit")) {
    		System.out.println("Inserting at " + nextSlide + ".");
    	}
    	
    	if (r > 0) {
    		for (int i = 0; i < h; i++) {
	        	for (int j = 0; j < w; j++) {
	        		if (tracker[i][j].charAt(4) == 'g'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(greenRelicCollected + 1))) {
	        			greenRelicPos[0] = i;
	        			greenRelicPos[1] = j; 
	        		}
	        		if (tracker[i][j].charAt(4) == 'y'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(yellowRelicCollected + 1))) {
	        			yellowRelicPos[0] = i;
	        			yellowRelicPos[1] = j; 
	        		}
	        		if (tracker[i][j].charAt(4) == 'r'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(redRelicCollected + 1))) {
	        			redRelicPos[0] = i;
	        			redRelicPos[1] = j; 
	        		}
	        		if (tracker[i][j].charAt(4) == 'b'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(blueRelicCollected + 1))) {
	        			blueRelicPos[0] = i;
	        			blueRelicPos[1] = j; 
	        		}
	        	}
	        }
	        if (floatingTileTracker.charAt(4) == 'g'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(greenRelicCollected + 1))) {
	        	greenRelicPos[0] = 100;
    			greenRelicPos[1] = 100; 
	        }
	        if (floatingTileTracker.charAt(4) == 'y'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(yellowRelicCollected + 1))) {
	        	yellowRelicPos[0] = 100;
    			yellowRelicPos[1] = 100; 
	        }
	        if (floatingTileTracker.charAt(4) == 'r'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(redRelicCollected + 1))) {
	        	redRelicPos[0] = 100;
    			redRelicPos[1] = 100;  
	        }
	        if (floatingTileTracker.charAt(4) == 'b'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(blueRelicCollected + 1))) {
	        	blueRelicPos[0] = 100;
    			blueRelicPos[1] = 100;
	        }
	        if (r == greenRelicCollected) {
	        	greenRelicPos[0] = 300;
    			greenRelicPos[1] = 300; 
	        }
	        if (r == yellowRelicCollected) {
	        	yellowRelicPos[0] = 300;
    			yellowRelicPos[1] = 300; 
	        }
	        if (r == redRelicCollected) {
	        	redRelicPos[0] = 300;
    			redRelicPos[1] = 300; 
	        }
	        if (r == blueRelicCollected) {
	        	blueRelicPos[0] = 300;
    			blueRelicPos[1] = 300;
	        }
		}
    	
    	if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
    		if (greenRelicCollected < r) {
    			greenRelicCollected++;
    		}
    		for (int j = 0; j < w; j++) {
        		for (int i = 0; i < h; i++) {
        			getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
        		}
        	}
        	getFloatingTile(floatingTileTracker);
    		printBoard();
    		System.out.println("Green has collected a relic.");
    		if (greenRelicCollected == r) {
    			System.out.println("Green has all their relics.");
    		}
    		System.out.println("Relics collected /" + r + ":");
	    	System.out.println("- Green  " + greenRelicCollected);
	    	System.out.println("- Yellow " + yellowRelicCollected);
	    	System.out.println("- Red    " + redRelicCollected);
	    	System.out.println("- Blue   " + blueRelicCollected);
    	}
    	if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
    		if (yellowRelicCollected < r) {
    			yellowRelicCollected++;
    		}
    		for (int j = 0; j < w; j++) {
        		for (int i = 0; i < h; i++) {
        			getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
        		}
        	}
        	getFloatingTile(floatingTileTracker);
    		printBoard();
    		System.out.println("Yellow has collected a relic.");
    		if (yellowRelicCollected == r) {
    			System.out.println("Yellow has all their relics.");
    		}
    		System.out.println("Relics collected /" + r + ":");
	    	System.out.println("- Green  " + greenRelicCollected);
	    	System.out.println("- Yellow " + yellowRelicCollected);
	    	System.out.println("- Red    " + redRelicCollected);
	    	System.out.println("- Blue   " + blueRelicCollected);
    	}
    	if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
    		if (redRelicCollected < r) {
    			redRelicCollected++;
    		}
    		for (int j = 0; j < w; j++) {
        		for (int i = 0; i < h; i++) {
        			getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
        		}
        	}
        	getFloatingTile(floatingTileTracker);
    		printBoard();
    		System.out.println("Red has collected a relic.");
    		if (redRelicCollected == r) {
    			System.out.println("Red has all their relics.");
    		}
    		System.out.println("Relics collected /" + r + ":");
	    	System.out.println("- Green  " + greenRelicCollected);
	    	System.out.println("- Yellow " + yellowRelicCollected);
	    	System.out.println("- Red    " + redRelicCollected);
	    	System.out.println("- Blue   " + blueRelicCollected);
    	}
    	if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
    		if (blueRelicCollected < r) {
    			blueRelicCollected++;
    		}
    		for (int j = 0; j < w; j++) {
        		for (int i = 0; i < h; i++) {
        			getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
        		}
        	}
        	getFloatingTile(floatingTileTracker);
    		printBoard();
    		System.out.println("Blue has collected a relic.");
    		if (blueRelicCollected == r) {
    			System.out.println("Blue has all their relics.");
    		}
    		System.out.println("Relics collected /" + r + ":");
	    	System.out.println("- Green  " + greenRelicCollected);
	    	System.out.println("- Yellow " + yellowRelicCollected);
	    	System.out.println("- Red    " + redRelicCollected);
	    	System.out.println("- Blue   " + blueRelicCollected);
    	}
    	
    	String[] sendBack = new String[5];
    	sendBack[0] = nextSlide;
    	sendBack[1] = Integer.toString(greenRelicCollected);
    	sendBack[2] = Integer.toString(yellowRelicCollected);
    	sendBack[3] = Integer.toString(redRelicCollected);
    	sendBack[4] = Integer.toString(blueRelicCollected);
    	return sendBack;
    }
    
    /**
     * Allows a player to make as many moves as they want.
     * @param nextMove This updates the player's input (stores the direction he wants to move), stored as a {@code String}.
	 * @param whoseTurn This is whoever's turn it is, stored as a {@code String}.
	 * @param moveTerm This is a boolean to determine if the moving is finished or not, stored as a {@code boolean}.
	 * @param greenRelicCollected This is the amount of relics green has collected, stored as an {@code int}.
	 * @param yellowRelicCollected This is the amount of relics yellow has collected, stored as an {@code int}.
	 * @param redRelicCollected This is the amount of relics red has collected, stored as an {@code int}.
	 * @param blueRelicCollected This is the amount of relics blue has collected, stored as an {@code int}.
     * @return returns the relics collected variable of whoever's turn it is. 
     */
    public int movingMove (String nextMove, String whoseTurn, boolean moveTerm, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected) {
    	
    	int[] colorPos = new int [2];
    	int[] colorPosW = new int [2];
    	if (whoseTurn.equals("Green")) {
    		colorPos[0] = greenPos[0];
    		colorPos[1] = greenPos[1];
    		colorPosW[0] = greenPosW[0];
    		colorPosW[1] = greenPosW[1];
    	}
    	else if (whoseTurn.equals("Yellow")) {
    		colorPos[0] = yellowPos[0];
    		colorPos[1] = yellowPos[1];
    		colorPosW[0] = yellowPosW[0];
    		colorPosW[1] = yellowPosW[1];
    	}
    	else if (whoseTurn.equals("Red")) {
    		colorPos[0] = redPos[0];
    		colorPos[1] = redPos[1];
    		colorPosW[0] = redPosW[0];
    		colorPosW[1] = redPosW[1];
    	}
    	else if (whoseTurn.equals("Blue")) {
    		colorPos[0] = bluePos[0];
    		colorPos[1] = bluePos[1];
    		colorPosW[0] = bluePosW[0];
    		colorPosW[1] = bluePosW[1];
    	}
    	int didQuit = 0;
    	String printAdv = " ";
    	boolean win = false;
    	boolean relicCol = false;
    	boolean relicColAll = false;
    	
    	if (whoseTurn.equals("Green")) {
    		printAdv = "G";
    	}
    	else if (whoseTurn.equals("Yellow")) {
    		printAdv = "Y";
    	}
    	else if (whoseTurn.equals("Red")) {
    		printAdv = "R";
    	}
    	else if (whoseTurn.equals("Blue")) {
    		printAdv = "B";
    	}
    	
    	do {
    		moveTerm = false;
	    	nextMove = validationTestMove (whoseTurn, nextMove);
	    	
	    	if (nextMove.equals("n")) {
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = " ";
	    		wholeBoard[colorPosW[0]-4][colorPosW[1]] = printAdv;
	    		colorPos[0] = colorPos[0] - 1;
	    		colorPosW[0] = colorPosW[0] - 4; 
	    		System.out.println("Moving north.");
	    	}
	    	else if (nextMove.equals("s")) {
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = " ";
	    		wholeBoard[colorPosW[0]+4][colorPosW[1]] = printAdv;
	    		colorPos[0] = colorPos[0] + 1;
	    		colorPosW[0] = colorPosW[0] + 4; 
	    		System.out.println("Moving south.");
	    	}
	    	else if (nextMove.equals("w")) {
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = " ";
	    		wholeBoard[colorPosW[0]][colorPosW[1]-8] = printAdv;
	    		colorPos[1] = colorPos[1] - 1;
	    		colorPosW[1] = colorPosW[1] - 8; 
	    		System.out.println("Moving west.");
	    	}
	    	else if (nextMove.equals("e")) {
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = " ";
	    		wholeBoard[colorPosW[0]][colorPosW[1]+8] = printAdv;
	    		colorPos[1] = colorPos[1] + 1;
	    		colorPosW[1] = colorPosW[1] + 8;
	    		System.out.println("Moving east.");
	    	}
	    	else if (nextMove.length() == 3&&whoseTurn.equals("Green")) {
	    		
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = " ";
	    		int rowPlacement = Integer.parseInt(String.valueOf(nextMove.charAt(2)));
	    		int colPlacement = Integer.parseInt(String.valueOf(nextMove.charAt(0)));
	    		colorPos[0] = rowPlacement - 1;
	    		colorPos[1] = colPlacement - 1;
	    		if (rowPlacement == 1) {
	    			colorPosW[0] = 2;
	    		}
	    		else {
	    			colorPosW[0] = 2 + ((rowPlacement - 1) * 4);
	    		}
	    		if (colPlacement == 1) {
	    			colorPosW[1] = 3;
	    		}
	    		else {
	    			colorPosW[1] = 3 + ((colPlacement - 1) * 8);
	    		}
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = printAdv;
	    		System.out.println("Moving to " + nextMove + ".");
	    		
	    	}
	    	else if (nextMove.length() == 3&&whoseTurn.equals("Yellow")) {
	    		
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = " ";
	    		int rowPlacement = Integer.parseInt(String.valueOf(nextMove.charAt(2)));
	    		int colPlacement = Integer.parseInt(String.valueOf(nextMove.charAt(0)));
	    		colorPos[0] = rowPlacement - 1;
	    		colorPos[1] = colPlacement - 1;
	    		if (rowPlacement == 1) {
	    			colorPosW[0] = 2;
	    		}
	    		else {
	    			colorPosW[0] = 2 + ((rowPlacement - 1) * 4);
	    		}
	    		if (colPlacement == 1) {
	    			colorPosW[1] = 7;
	    		}
	    		else {
	    			colorPosW[1] = 7 + ((colPlacement - 1) * 8);
	    		}
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = printAdv;
	    		System.out.println("Moving to " + nextMove + ".");
	    		
	    	}
	    	else if (nextMove.length() == 3&&whoseTurn.equals("Red")) {
	    		
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = " ";
	    		int rowPlacement = Integer.parseInt(String.valueOf(nextMove.charAt(2)));
	    		int colPlacement = Integer.parseInt(String.valueOf(nextMove.charAt(0)));
	    		colorPos[0] = rowPlacement - 1;
	    		colorPos[1] = colPlacement - 1;
	    		if (rowPlacement == 1) {
	    			colorPosW[0] = 4;
	    		}
	    		else {
	    			colorPosW[0] = 4 + ((rowPlacement - 1) * 4);
	    		}
	    		if (colPlacement == 1) {
	    			colorPosW[1] = 3;
	    		}
	    		else {
	    			colorPosW[1] = 3 + ((colPlacement - 1) * 8);
	    		}
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = printAdv;
	    		System.out.println("Moving to " + nextMove + ".");
	    		
	    	}
	    	else if (nextMove.length() == 3&&whoseTurn.equals("Blue")) {
	    		
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = " ";
	    		int rowPlacement = Integer.parseInt(String.valueOf(nextMove.charAt(2)));
	    		int colPlacement = Integer.parseInt(String.valueOf(nextMove.charAt(0)));
	    		colorPos[0] = rowPlacement - 1;
	    		colorPos[1] = colPlacement - 1;
	    		if (rowPlacement == 1) {
	    			colorPosW[0] = 4;
	    		}
	    		else {
	    			colorPosW[0] = 4 + ((rowPlacement - 1) * 4);
	    		}
	    		if (colPlacement == 1) {
	    			colorPosW[1] = 7;
	    		}
	    		else {
	    			colorPosW[1] = 7 + ((colPlacement - 1) * 8);
	    		}
	    		wholeBoard[colorPosW[0]][colorPosW[1]] = printAdv;
	    		System.out.println("Moving to " + nextMove + ".");
	    		
	    	}
	    	
	    	if (whoseTurn.equals("Green")) {
	    		greenPos[0] = colorPos[0];
	    		greenPos[1] = colorPos[1];
	    		greenPosW[0] = colorPosW[0];
	    		greenPosW[1] = colorPosW[1];
	    	}
	    	else if (whoseTurn.equals("Yellow")) {
	    		yellowPos[0] = colorPos[0];
	    		yellowPos[1] = colorPos[1];
	    		yellowPosW[0] = colorPosW[0];
	    		yellowPosW[1] = colorPosW[1];
	    	}
	    	else if (whoseTurn.equals("Red")) {
	    		redPos[0] = colorPos[0];
	    		redPos[1] = colorPos[1];
	    		redPosW[0] = colorPosW[0];
	    		redPosW[1] = colorPosW[1];
	    	}
	    	else if (whoseTurn.equals("Blue")) {
	    		bluePos[0] = colorPos[0];
	    		bluePos[1] = colorPos[1];
	    		bluePosW[0] = colorPosW[0];
	    		bluePosW[1] = colorPosW[1];
	    	}
	    	
	    	if (whoseTurn.equals("Green")&&colorPos[0] == greenRelicPos[0]&&colorPos[1] == greenRelicPos[1]) {
	    		relicCol = true;
	    		if (greenRelicCollected < r) {
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			relicColAll = true;
	    		}
	    		moveTerm = true;
	    	}
	    	else if (whoseTurn.equals("Yellow")&&colorPos[0] == yellowRelicPos[0]&&colorPos[1] == yellowRelicPos[1]) {
	    		relicCol = true;
	    		if (yellowRelicCollected < r) {
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			relicColAll = true;
	    		}
	    		moveTerm = true;
	    	}
	    	else if (whoseTurn.equals("Red")&&colorPos[0] == redRelicPos[0]&&colorPos[1] == redRelicPos[1]) {
	    		relicCol = true;
	    		if (redRelicCollected < r) {
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			relicColAll = true;
	    		}
	    		moveTerm = true;
	    	}
	    	else if (whoseTurn.equals("Blue")&&colorPos[0] == blueRelicPos[0]&&colorPos[1] == blueRelicPos[1]) {
	    		relicCol = true;
	    		if (blueRelicCollected < r) {
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			relicColAll = true;
	    		}
	    		moveTerm = true;
	    	}
	    	
	    	if (nextMove.equals("n")||nextMove.equals("s")||nextMove.equals("e")||nextMove.equals("w")||nextMove.length() == 3) {
	    		
	    		if (r > 0) {
		    		for (int i = 0; i < h; i++) {
			        	for (int j = 0; j < w; j++) {
			        		if (tracker[i][j].charAt(4) == 'g'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(greenRelicCollected + 1))) {
			        			greenRelicPos[0] = i;
			        			greenRelicPos[1] = j; 
			        		}
			        		if (tracker[i][j].charAt(4) == 'y'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(yellowRelicCollected + 1))) {
			        			yellowRelicPos[0] = i;
			        			yellowRelicPos[1] = j; 
			        		}
			        		if (tracker[i][j].charAt(4) == 'r'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(redRelicCollected + 1))) {
			        			redRelicPos[0] = i;
			        			redRelicPos[1] = j; 
			        		}
			        		if (tracker[i][j].charAt(4) == 'b'&&String.valueOf(tracker[i][j].charAt(5)).equals(Integer.toString(blueRelicCollected + 1))) {
			        			blueRelicPos[0] = i;
			        			blueRelicPos[1] = j; 
			        		}
			        	}
			        }
			        if (floatingTileTracker.charAt(4) == 'g'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(greenRelicCollected + 1))) {
			        	greenRelicPos[0] = 100;
		    			greenRelicPos[1] = 100; 
			        }
			        if (floatingTileTracker.charAt(4) == 'y'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(yellowRelicCollected + 1))) {
			        	yellowRelicPos[0] = 100;
		    			yellowRelicPos[1] = 100; 
			        }
			        if (floatingTileTracker.charAt(4) == 'r'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(redRelicCollected + 1))) {
			        	redRelicPos[0] = 100;
		    			redRelicPos[1] = 100;  
			        }
			        if (floatingTileTracker.charAt(4) == 'b'&&String.valueOf(floatingTileTracker.charAt(5)).equals(Integer.toString(blueRelicCollected + 1))) {
			        	blueRelicPos[0] = 100;
		    			blueRelicPos[1] = 100;
			        }
			        if (r == greenRelicCollected) {
			        	greenRelicPos[0] = 300;
		    			greenRelicPos[1] = 300; 
			        }
			        if (r == yellowRelicCollected) {
			        	yellowRelicPos[0] = 300;
		    			yellowRelicPos[1] = 300; 
			        }
			        if (r == redRelicCollected) {
			        	redRelicPos[0] = 300;
		    			redRelicPos[1] = 300; 
			        }
			        if (r == blueRelicCollected) {
			        	blueRelicPos[0] = 300;
		    			blueRelicPos[1] = 300;
			        }
	    		}
	    		
	    		for (int j = 0; j < w; j++) {
		    		for (int i = 0; i < h; i++) {
		    			getBoard(tracker[i][j], i, j, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
		    		}
		    	}
		    	getFloatingTile(floatingTileTracker);
	    		printBoard();
	    	}
	    	
	    	if (relicCol) {
	    		System.out.println(whoseTurn + " has collected a relic.");
	    	}
	    	if (relicColAll) {
	    		System.out.println(whoseTurn + " has all their relics.");
	    	}
	    	
	    	if (whoseTurn.equals("Green")&&greenRelicCollected == r&&colorPos[0] == 0&&colorPos[1] == 0&&r > 0) {
	    		moveTerm = true;
	    		win = true;
	    	}
	    	else if (whoseTurn.equals("Yellow")&&yellowRelicCollected == r&&colorPos[0] == 0&&colorPos[1] == w-1&&r > 0) {
	    		moveTerm = true;
	    		win = true;
	    	}
	    	else if (whoseTurn.equals("Red")&&redRelicCollected == r&&colorPos[0] == h-1&&colorPos[1] == 0&&r > 0) {
	    		moveTerm = true;
	    		win = true;
	    	}
	    	else if (whoseTurn.equals("Blue")&&blueRelicCollected == r&&colorPos[0] == h-1&&colorPos[1] == w-1&&r > 0) {
	    		moveTerm = true;
	    		win = true;
	    	}
	    	if (nextMove.equals("done")) {
	    		moveTerm = true;
	    	}
	    	if (nextMove.equals("quit")) {
	    		moveTerm = true;
	    		didQuit = 1000;
	    	}
    	} while (!moveTerm);
    	
    	if (!nextMove.equals("quit")&&!win) {
    		System.out.println("End of " + whoseTurn + "'s turn.");
    	}
    	
    	if (whoseTurn.equals("Green")) {
    		return greenRelicCollected + didQuit;
    	}
    	else if (whoseTurn.equals("Yellow")) {
    		return yellowRelicCollected + didQuit;
    	}
    	else if (whoseTurn.equals("Red")) {
    		return redRelicCollected + didQuit;
    	}
    	else {
    		return blueRelicCollected + didQuit;
    	}
    }
    
    /**
     * Validates the sliding inputs.
     * @param whoseTurn This is whoever's turn it is, stored as a {@code String}.
     * @param nextSlide This updates the player's input (stores whether he wants to slide or rotate), stored as a {@code String}.
	 * @param prevSlide This is the last exit point from the previous slide, stored as a {@code String}.
     * @return {@code nextSlide} is returned fully validated, ready to be implemented on the visible board.
     */
    public String validationTestSlide (String whoseTurn, String nextSlide, String prevSlide) {
    	
    	boolean isValid = true;
    	boolean lastExit = false;
    	
    	do {
			System.out.println("[" + whoseTurn + "] Rotate and slide the floating tile:");
    		System.out.print("> ");
    		nextSlide = StdIn.readString();
		} while (nextSlide.isEmpty());
    	
    	do {
	    	if ((!nextSlide.equals("r")&&!nextSlide.equals("l")&&!nextSlide.equals("n2")&&!nextSlide.equals("n4")&&!nextSlide.equals("n6")&&!nextSlide.equals("n8")&&!nextSlide.equals("s2")&&!nextSlide.equals("s4")&&!nextSlide.equals("s6")&&!nextSlide.equals("s8")&&!nextSlide.equals("e2")&&!nextSlide.equals("e4")&&!nextSlide.equals("e6")&&!nextSlide.equals("e8")&&!nextSlide.equals("w2")&&!nextSlide.equals("w4")&&!nextSlide.equals("w6")&&!nextSlide.equals("w8")&&!nextSlide.equals("quit"))||!isValid||lastExit) {
	    		do {
	    			if (nextSlide.equals("n1")||nextSlide.equals("n3")||nextSlide.equals("n5")||nextSlide.equals("n7")||nextSlide.equals("n9")||nextSlide.equals("s1")||nextSlide.equals("s3")||nextSlide.equals("s5")||nextSlide.equals("s7")||nextSlide.equals("s9")||nextSlide.equals("e1")||nextSlide.equals("e3")||nextSlide.equals("e5")||nextSlide.equals("e7")||nextSlide.equals("e9")||nextSlide.equals("w1")||nextSlide.equals("w3")||nextSlide.equals("w5")||nextSlide.equals("w7")||nextSlide.equals("w9")) {
	    				System.out.println("Cannot slide into odd positions.");
	    			}
	    			else if (lastExit) {
	    				System.out.println("Cannot slide into last exit point.");
	    				lastExit = false;
	    			}
	    			else {
	    				System.out.println("Invalid input.");
	    			}
		    		do {
		    			System.out.println("[" + whoseTurn + "] Rotate and slide the floating tile:");
			    		System.out.print("> ");
			    		nextSlide = StdIn.readString();
		    		} while (nextSlide.isEmpty());
	    		} while(!nextSlide.equals("r")&&!nextSlide.equals("l")&&!nextSlide.equals("n2")&&!nextSlide.equals("n4")&&!nextSlide.equals("n6")&&!nextSlide.equals("n8")&&!nextSlide.equals("s2")&&!nextSlide.equals("s4")&&!nextSlide.equals("s6")&&!nextSlide.equals("s8")&&!nextSlide.equals("e2")&&!nextSlide.equals("e4")&&!nextSlide.equals("e6")&&!nextSlide.equals("e8")&&!nextSlide.equals("w2")&&!nextSlide.equals("w4")&&!nextSlide.equals("w6")&&!nextSlide.equals("w8")&&!nextSlide.equals("quit"));
	    	}
	    	if (nextSlide.length() == 2) {
	    		firstCharNP = nextSlide.charAt(0);
		    	secondStringNP = String.valueOf(nextSlide.charAt(1));
		    	secondIntNP = Integer.parseInt(secondStringNP);
		    	if (nextSlide.equals(prevSlide)) {
		    		isValid = false;
		    		lastExit = true;
		    	}
		    	if ((firstCharNP == 'n'||firstCharNP == 's')&&(secondIntNP < 1||secondIntNP > w)) {
			    	isValid = false;
		    	}
		    	else if ((firstCharNP == 'e'||firstCharNP == 'w')&&(secondIntNP < 1||secondIntNP > h)) {
		    		isValid = false;
		    	}
		    	else {
		    		isValid = true;
		    	}
	    	}
	    	else if (nextSlide.length() == 1) {
	    		isValid = true;
	    	}
	    	else if (nextSlide.equals("quit")) {
	    		isValid = true;
	    	}
    	} while (!isValid||lastExit);
    	return nextSlide; 
    }
    
    /**
     * Validates the moving inputs.
     * @param whoseTurn This is whoever's turn it is, stored as a {@code String}.
     * @param nextMove This updates the player's input (stores the direction he wants to move), stored as a {@code String}.
     * @return {@code nextMove} is returned fully validated, ready to be implemented on the visible board.
     */
    public String validationTestMove (String whoseTurn, String nextMove) {
    	
    	int[] colorPos = new int [2];
    	if (whoseTurn.equals("Green")) {
    		colorPos[0] = greenPos[0];
    		colorPos[1] = greenPos[1];
    	}
    	else if (whoseTurn.equals("Yellow")) {
    		colorPos[0] = yellowPos[0];
    		colorPos[1] = yellowPos[1];
    	}
    	else if (whoseTurn.equals("Red")) {
    		colorPos[0] = redPos[0];
    		colorPos[1] = redPos[1];
    	}
    	else if (whoseTurn.equals("Blue")) {
    		colorPos[0] = bluePos[0];
    		colorPos[1] = bluePos[1];
    	}
    	boolean isValid = true;
    	boolean noPath = false;
    	boolean noPath1 = false;
    	boolean offBoard = false;
    	String where = "";
    	
    	Tile currentPlace = new Tile (tracker[colorPos[0]][colorPos[1]]);
    	Tile upperPlace, lowerPlace, rightPlace, leftPlace;
    	String[] pathways = new String[4];
    	String[] pathwaysUpper = new String[4];
    	String[] pathwaysLower = new String[4];
    	String[] pathwaysRight = new String[4];
    	String[] pathwaysLeft = new String[4];
    	for (int i = 0; i < 4; i++) {
    		pathways[i] = " ";
    		pathwaysUpper[i] = " ";
    		pathwaysLower[i] = " ";
    		pathwaysLeft[i] = " ";
    		pathwaysRight[i] = " ";
    	}
    	pathways = currentPlace.stringCracker(currentPlace, r);
    	if (colorPos[0] != 0) {
    		upperPlace = new Tile (tracker[colorPos[0]-1][colorPos[1]]);
    		pathwaysUpper = upperPlace.stringCracker(upperPlace, r);
    	}
    	if (colorPos[0] != h-1) {
    		lowerPlace = new Tile (tracker[colorPos[0]+1][colorPos[1]]);
    		pathwaysLower = lowerPlace.stringCracker(lowerPlace, r);
    	}
    	if (colorPos[1] != 0) {
    		leftPlace = new Tile (tracker[colorPos[0]][colorPos[1]-1]);
    		pathwaysLeft = leftPlace.stringCracker(leftPlace, r);
    	}
    	if (colorPos[1] != w-1) {
    		rightPlace = new Tile (tracker[colorPos[0]][colorPos[1]+1]);
    		pathwaysRight = rightPlace.stringCracker(rightPlace, r);
    	}
    	
    	do {
			System.out.println("[" + whoseTurn + "] Move your adventurer:");
    		System.out.print("> ");
    		nextMove = StdIn.readString();
		} while (nextMove.isEmpty());
    	
    	do {
	    	if ((!nextMove.equals("n")&&!nextMove.equals("e")&&!nextMove.equals("w")&&!nextMove.equals("s")&&!nextMove.equals("done")&&!nextMove.equals("quit")&&nextMove.length() != 3)||!isValid) {
	    		do {
	    			if (offBoard) {
	    				System.out.println("Cannot move " + where + ": off the board.");
	    				offBoard = false;
	    				where = "";
	    			}
	    			else if (noPath) {
	    				System.out.println("Cannot move " + where + ": no path.");
	    				noPath = false;
	    				where = "";
	    			}
	    			else if (noPath1) {
	    				System.out.println("Cannot move to " + nextMove + ": no path.");
	    				noPath1 = false;
	    				isValid = true;
	    			}
	    			else {
	    				System.out.println("Invalid input.");
	    				isValid = true;
	    			}
		    		do {
		    			System.out.println("[" + whoseTurn + "] Move your adventurer:");
			    		System.out.print("> ");
			    		nextMove = StdIn.readString();
		    		} while (nextMove.isEmpty());
	    		} while (!nextMove.equals("n")&&!nextMove.equals("e")&&!nextMove.equals("w")&&!nextMove.equals("s")&&!nextMove.equals("done")&&!nextMove.equals("quit")&&nextMove.length() != 3);	
	    	}
	    	
	    	if ((colorPos[0] == 0&&nextMove.equals("n"))||(colorPos[0] == h-1&&nextMove.equals("s"))||(colorPos[1] == 0&&nextMove.equals("w"))||(colorPos[1] == w-1&&nextMove.equals("e"))) {
	    		isValid = false;
	    		offBoard = true;
	    	}
	    	else if ((pathways[0].equals("c")&&nextMove.equals("n"))||(nextMove.equals("n")&&pathwaysUpper[2].equals("c"))) {
	    		isValid = false;
	    		noPath = true;
	    	}
	    	else if ((pathways[2].equals("c")&&nextMove.equals("s"))||(nextMove.equals("s")&&pathwaysLower[0].equals("c"))) {
	    		isValid = false;
	    		noPath = true;
	    	}
	    	else if ((pathways[1].equals("c")&&nextMove.equals("e"))||(nextMove.equals("e")&&pathwaysRight[3].equals("c"))) {
	    		isValid = false;
	    		noPath = true;
	    	}
	    	else if ((pathways[3].equals("c")&&nextMove.equals("w"))||(nextMove.equals("w")&&pathwaysLeft[1].equals("c"))) {
	    		isValid = false;
	    		noPath = true;
	    	}
	    	else if (nextMove.length() == 3) {
	    		if (!String.valueOf(nextMove.charAt(0)).equals("1")&&!String.valueOf(nextMove.charAt(0)).equals("2")&&!String.valueOf(nextMove.charAt(0)).equals("3")&&!String.valueOf(nextMove.charAt(0)).equals("4")&&!String.valueOf(nextMove.charAt(0)).equals("5")&&!String.valueOf(nextMove.charAt(0)).equals("6")&&!String.valueOf(nextMove.charAt(0)).equals("7")&&!String.valueOf(nextMove.charAt(0)).equals("8")&&!String.valueOf(nextMove.charAt(0)).equals("9")) {
	    			isValid = false;
	    		}
	    		else if (!String.valueOf(nextMove.charAt(2)).equals("1")&&!String.valueOf(nextMove.charAt(2)).equals("2")&&!String.valueOf(nextMove.charAt(2)).equals("3")&&!String.valueOf(nextMove.charAt(2)).equals("4")&&!String.valueOf(nextMove.charAt(2)).equals("5")&&!String.valueOf(nextMove.charAt(2)).equals("6")&&!String.valueOf(nextMove.charAt(2)).equals("7")&&!String.valueOf(nextMove.charAt(2)).equals("8")&&!String.valueOf(nextMove.charAt(2)).equals("9")) {
	    			isValid = false;
	    		}
	    		else if (!String.valueOf(nextMove.charAt(1)).equals(",")) {
	    			isValid = false;
	    		}
	    		else if (Integer.parseInt(String.valueOf(nextMove.charAt(2))) > h||Integer.parseInt(String.valueOf(nextMove.charAt(0))) > w) {
	    			isValid = false;
	    			noPath1 = true;
	    		}
	    		else if (!pathFinder1(colorPos[0], colorPos[1], Integer.parseInt(String.valueOf(nextMove.charAt(2))) - 1, Integer.parseInt(String.valueOf(nextMove.charAt(0))) - 1)) {
	    			isValid = false;
	    			noPath1 = true;
	    		}
	    		else {
	    			isValid = true;
	    		}
	    	}
	    	else {
	    		isValid = true;
	    	}
	    	if (nextMove.equals("n")) {
    			where = "north";
    		}
    		else if (nextMove.equals("e")) {
    			where = "east";
    		}
    		else if (nextMove.equals("w")) {
    			where = "west";
    		}
    		else if (nextMove.equals("s")) {
    			where = "south";
    		}
    	} while (!isValid);
    	return nextMove;
    }
    
    /**
     * This method is called only in {@link pathFinder1 the first Path Finding method}.
     * This method calls itself recursively until a path is found or until all tiles are marked visited. 
     * @param currRow This is the current row number that the player is on.
     * @param currCol This is the current column number that the player is on.
     * @return It will return an {@code int}, 2 if a path has been found, 0 if not found.
     */
    public int pathFinder2 (int currRow,int currCol) {
    	
    	int movedRow = 0;
		int movedCol = 0;
		
		String where = "";
		
		for (int i = 0; i < 4; i++) {
			if (tracker[currRow][currCol].substring(i, i + 1).contains("1")) {
				where = where + String.valueOf(dirs[i]);
			}
		}
		
		for (int i = 0; i < where.length(); i++) {
			
			if (where.charAt(i) == 'n') {
				movedRow = currRow - 1;
				movedCol = currCol;
			}
			else if (where.charAt(i) == 's') {
				movedRow =currRow + 1;
				movedCol = currCol;
			}
			else if (where.charAt(i) == 'e') {
				movedRow = currRow;
				movedCol =currCol + 1;
			}
			else if (where.charAt(i) == 'w') {
				movedRow = currRow;
				movedCol =currCol - 1;
			}
				
			if (movedRow > -1&&movedCol > -1&&movedRow < h&&movedCol < w) {
				if(MovingMaze.canMoveInDirection(tracker[currRow][currCol], tracker[movedRow][movedCol], where.charAt(i)) && arrayPathFinding[movedRow][movedCol] != 1) {
					if(arrayPathFinding[movedRow][movedCol] == 2) {
						return 2;
					}
					arrayPathFinding[movedRow][movedCol] = 1; 
					if(pathFinder2(movedRow, movedCol) == 2) {
						return 2;
					}
				}
			}
		}
    	return 0;
    }
    
    /**
     * This method is called when one wants to find a path to a specific spot. It will initialize the required variables, 
     * then call {@link pathFinder2 the second Path Finding method}, which will then call itself recursively. 
     * <p>
     * How the path finder works is that it has a 2D integer array the same size as the board. 
     * It initializes all of the indexes to 0. The position of the player is indexed with a 3. 
     * The position of the destination is indexed with a 2. After initializing this, it will test in 
     * which directions the player can currently move, and call the {@link pathFinder2 the second Path Finding method}
     * with the new current position. If the {@link pathFinder2 the second Path Finding method} finds a path to 2
     * (the destination), it will return 2, if not it will return 0. this will allow this method to return either true or false: 
     * true if {@link pathFinder2 the second Path Finding method} returned 2, false if not. 
     * @param currRow This is the current row number that the player is on.
     * @param currCol This is the current column number that the player is on.
     * @param destRow This is the row that the player wants to move to.
     * @param destCol This is the column that the player wants to move to.
     * @return true if a path to the destination position has been found, false if not. 
     */
    public boolean pathFinder1 (int currRow, int currCol, int destRow, int destCol) {
    	
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				arrayPathFinding[i][j] = 0;
			}
		}
		
		arrayPathFinding[currRow][currCol] = 3;
		arrayPathFinding[destRow][destCol] = 2;
		
		int movedRow = 0;
		int movedCol = 0;
		
		String where = "";
		
		for (int i = 0; i < 4; i++) {
			if (tracker[currRow][currCol].substring(i, i + 1).contains("1")) {
				where = where + String.valueOf(dirs[i]);
			}
		}
		
		for (int i = 0; i < where.length(); i++) {
			
			if (where.charAt(i) == 'n') {
				movedRow = currRow - 1;
				movedCol = currCol;
			}
			else if (where.charAt(i) == 's') {
				movedRow = currRow + 1;
				movedCol = currCol;
			}
			else if (where.charAt(i) == 'e') {
				movedRow = currRow;
				movedCol = currCol + 1;
			}
			else if (where.charAt(i) == 'w') {
				movedRow = currRow;
				movedCol = currCol - 1;
			}
			
			if (movedRow > -1&&movedCol > -1&&movedRow < h&&movedCol < w) {
				if(MovingMaze.canMoveInDirection(tracker[currRow][currCol], tracker[movedRow][movedCol], where.charAt(i))) {
					if(arrayPathFinding[movedRow][movedCol] == 2) {
						return true;
					}
					arrayPathFinding[movedRow][movedCol] = 1; 
					if(pathFinder2(movedRow, movedCol) == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}
    
    
    /**
     * Rotates the tile clockwise. (for {@link gameState gameState constructor 1})
     * @return Returns the new floating tile encoding.
     */
    public String rotateTileRight () {
        
    	String first = String.valueOf(floatingTileTracker.charAt(0));
    	String second = String.valueOf(floatingTileTracker.charAt(1));
    	String third = String.valueOf(floatingTileTracker.charAt(2));
    	String fourth = String.valueOf(floatingTileTracker.charAt(3));
    	String fifth = String.valueOf(floatingTileTracker.charAt(4));
    	String sixth = String.valueOf(floatingTileTracker.charAt(5));
    	
    	floatingTileTracker = fourth + first + second + third + fifth + sixth;
    	
    	return floatingTileTracker; 
    }
    
    /**
     * Rotates the tile anti-clockwise. (for {@link gameState gameState constructor 1})
     * @return Returns the new floating tile encoding.
     */
    public String rotateTileLeft () {
        
    	String first = String.valueOf(floatingTileTracker.charAt(0));
    	String second = String.valueOf(floatingTileTracker.charAt(1));
    	String third = String.valueOf(floatingTileTracker.charAt(2));
    	String fourth = String.valueOf(floatingTileTracker.charAt(3));
    	String fifth = String.valueOf(floatingTileTracker.charAt(4));
    	String sixth = String.valueOf(floatingTileTracker.charAt(5));
    	
    	floatingTileTracker = second + third + fourth + first + fifth + sixth;
    	
    	return floatingTileTracker; 
    }
    
    /**
     * This constructor is used if the user wants to determine certain aspects of the game.
     * @param tE This is the tile encoding of the tile the user wants to determine aspects of.
     */
    public gameState (String tE) {
    	tileEncoding = tE;
    }
    
    /**
     * This constructor is used if the user wants to determine certain aspects of the game.
     * @param mTE This is the 2D array of encodings of the current board.
     */
    public gameState (String[][] mTE) {
    	tracker = mTE;
    }
    
    /**
     * Determines if a pathway is open in a specific direction. 
     * @param where The direction.
     * @return Returns whether a path is open or not. {@code True} if there is, {@code false} otherwise.
     */
    public boolean openOrNah (char where) {
        
    	boolean isItDoe = false;
    	if (tileEncoding.charAt(0) == '1'&&where == 'n') {
    		isItDoe = true;
    	}
    	else if (tileEncoding.charAt(1) == '1'&&where == 'e') {
    		isItDoe = true;
    	}
    	else if (tileEncoding.charAt(2) == '1'&&where == 's') {
    		isItDoe = true;
    	}
    	else if (tileEncoding.charAt(3) == '1'&&where == 'w') {
    		isItDoe = true;
    	}
    	else {
    		isItDoe = false;
    	}
    	return isItDoe;
    }
    
    /**
     * Rotates the tile clockwise. (for {@link gameState gameState constructor 2})
     * @return Returns the new floating tile encoding.
     */
    public boolean[] rotateTileClockwise () {
        
    	boolean[] rotation = new boolean[4];
    	boolean one = false;
    	boolean two = false;
    	boolean three = false;
    	boolean four = false;
    	
    	if (tileEncoding.charAt(0) == '1') {
    		one = true;
    	}
    	if (tileEncoding.charAt(1) == '1') {
    		two = true;
    	}
    	if (tileEncoding.charAt(2) == '1') {
    		three = true;
    	}
    	if (tileEncoding.charAt(3) == '1') {
    		four = true;
    	}
    	rotation[0] = four;
    	rotation[1] = one;
    	rotation[2] = two;
    	rotation[3] = three;
    	
    	return rotation;
    }
    
    /**
     * Rotates the tile anti-clockwise. (for {@link gameState gameState constructor 2})
     * @return Returns the new floating tile encoding.
     */
    public boolean[] rotateTileCounterclockwise () {
    	
    	boolean[] rotation = new boolean[4];
    	boolean one = false;
    	boolean two = false;
    	boolean three = false;
    	boolean four = false;
    	
    	if (tileEncoding.charAt(0) == '1') {
    		one = true;
    	}
    	if (tileEncoding.charAt(1) == '1') {
    		two = true;
    	}
    	if (tileEncoding.charAt(2) == '1') {
    		three = true;
    	}
    	if (tileEncoding.charAt(3) == '1') {
    		four = true;
    	}
    	rotation[0] = two;
    	rotation[1] = three;
    	rotation[2] = four;
    	rotation[3] = one;
    	
    	return rotation; 
    }
    
    /**
     * Determines the new floating tile after a slide.
     * @param floatTile This is the current floating tile.
     * @param where This is from which side the slide occurred.
     * @return Returns the new floating tile encoding.
     */
    public String getNewFloatingTile (String floatTile, String where) {
        
    	int e = Integer.parseInt(String.valueOf(where.charAt(1))) - 1;
    	if (where.charAt(0) == 'n') {
    		floatTile = tracker[tracker.length-1][e];
    	}
    	if (where.charAt(0) == 's') {
    		floatTile = tracker[0][e];
    	}
    	if (where.charAt(0) == 'w') {
    		floatTile = tracker[e][tracker[0].length-1];
    	}
    	if (where.charAt(0) == 'e') {
    		floatTile = tracker[e][0];
    	}
    	
    	return floatTile; 
    }
    
    /**
     * Determines if a player can move in a specific direction.
     * @param newTileEncoding This is the tile towards the direction the player wants to move.
     * @param where This is in which direction the player wants to move.
     * @return Returns if the player can or cannot move in the direction. {@code True} if so, {@code false} otherwise.
     */
    public boolean canPlayaMoov (String newTileEncoding, char where) {
        
    	boolean canYouDoe = false;
    	if (where == 'n') {
    		if (tileEncoding.charAt(0) == '1'&&newTileEncoding.charAt(2) == '1') {
    			canYouDoe = true;
    		}
    	}
    	else if (where == 's') {
    		if (tileEncoding.charAt(2) == '1'&&newTileEncoding.charAt(0) == '1') {
    			canYouDoe = true;
    		}
    	}
    	else if (where == 'e') {
    		if (tileEncoding.charAt(1) == '1'&&newTileEncoding.charAt(3) == '1') {
    			canYouDoe = true;
    		}
    	}
    	else if (where == 'w') {
    		if (tileEncoding.charAt(3) == '1'&&newTileEncoding.charAt(1) == '1') {
    			canYouDoe = true;
    		}
    	}
    	return canYouDoe; 
    }
    
    /**
     * Determines if a player can move in a specific set of directions, i.e. if there is a pathway open.
     * @param steps This is the set of directions.
     * @return Returns if the player can or cannot move in the set of directions. {@code True} if so, {@code false} otherwise.
     */
    public boolean canPlayaMoovPath (char[] steps) {
        
    	boolean canYouDoe = true;
    	int[] colorPos = {0, 0};
    	for (int i = 0; i < steps.length; i++) {
    		if ((steps[i] == 'n'&&colorPos[0] == 0)||(steps[i] == 's'&&colorPos[0] == tracker.length -1)||(steps[i] == 'w'&&colorPos[1] == 0)||(steps[i] == 'e'&&colorPos[1] == tracker[0].length -1)) {
    			canYouDoe = false;
    			i = steps.length;
    		}
    		else if (steps[i] == 'n') {
        		if (tracker[colorPos[0]][colorPos[1]].charAt(0) != '1'||tracker[colorPos[0]-1][colorPos[1]].charAt(2) != '1') {
        			canYouDoe = false;
        			i = steps.length;
        		}
        		else {
        			colorPos[0] = colorPos[0] - 1;
        		}
        	}
        	else if (steps[i] == 's') {
        		if (tracker[colorPos[0]][colorPos[1]].charAt(2) != '1'||tracker[colorPos[0]+1][colorPos[1]].charAt(0) != '1') {
        			canYouDoe = false;
        			i = steps.length;
        		}
        		else {
        			colorPos[0] = colorPos[0] + 1;
        		}
        	}
        	else if (steps[i] == 'e') {
        		if (tracker[colorPos[0]][colorPos[1]].charAt(1) != '1'||tracker[colorPos[0]][colorPos[1]+1].charAt(3) != '1') {
        			canYouDoe = false;
        			i = steps.length;
        		}
        		else {
        			colorPos[1] = colorPos[1] + 1;
        		}
        	}
        	else if (steps[i] == 'w') {
        		if (tracker[colorPos[0]][colorPos[1]].charAt(3) != '1'||tracker[colorPos[0]][colorPos[1]-1].charAt(1) != '1') {
        			canYouDoe = false;
        			i = steps.length;
        		}
        		else {
        			colorPos[1] = colorPos[1] - 1;
        		}
        	}
    	}
    	
    	return canYouDoe; 
    }
    
    /**
     * Determines if a tile has a relic on it or not.
     * @return Returns {@code true} if so, {@code false} otherwise.
     */
    public boolean hasRelic () {
        
    	boolean hasRelic = false;
    	if (tileEncoding.charAt(4) == 'g'||tileEncoding.charAt(4) == 'y'||tileEncoding.charAt(4) == 'r'||tileEncoding.charAt(4) == 'b') {
    		hasRelic = true;
    	}
    	return hasRelic; 
    }
    
    /**
     * Determines the relic type (if any) on the new floating tile after a slide.
     * @param floatTile This is the current floating tile.
     * @param where This is from which side the slide occurred. 
     * @return Returns the type of relic on the tile (returns {@code x} if non).
     */
    public char slideTwo (String floatTile, String where) {
        
    	char returnStatement = '?';
    	String newFloat = slideTileIntoMaze1Point2(tracker, floatTile, where);
    	boolean hasRelic = false;
    	if (newFloat.charAt(4) == 'g'||newFloat.charAt(4) == 'y'||newFloat.charAt(4) == 'r'||newFloat.charAt(4) == 'b') {
    		hasRelic = true;
    	}
    	if (hasRelic) {
    		returnStatement = newFloat.charAt(4);
    	}
    	else {
    		returnStatement = 'x';
    	}
    	return returnStatement;
    }
    
    private String slideTileIntoMaze1Point2 (String[][] mazeTileEncodings, String floatingTileEncoding, String slidingIndicator) {
        
    	int e = Integer.parseInt(String.valueOf(slidingIndicator.charAt(1))) - 1;
    	if (slidingIndicator.charAt(0) == 'n') {
    		floatingTileEncoding = mazeTileEncodings[mazeTileEncodings.length-1][e];
    	}
    	if (slidingIndicator.charAt(0) == 's') {
    		floatingTileEncoding = mazeTileEncodings[0][e];
    	}
    	if (slidingIndicator.charAt(0) == 'w') {
    		floatingTileEncoding = mazeTileEncodings[e][mazeTileEncodings[0].length-1];
    	}
    	if (slidingIndicator.charAt(0) == 'e') {
    		floatingTileEncoding = mazeTileEncodings[e][0];
    	}
    	
    	return floatingTileEncoding; 
    }
}
