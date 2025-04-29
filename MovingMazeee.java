import java.io.File;
import java.util.ArrayList;

/* Stellenbosch University CS144 Project 2022
 * MovingMaze.java
 * Name and surname: Amir Patel
 * Student number: 26034670
 */
public class MovingMazeee {

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


    // ==========================================================
    // Main function
    // ==========================================================

    public static void main(String[] args) {
        boolean functionality = true;
        boolean gameLoop = true;
        String whoseTurn = "";
        String nextSlide = "";
        String nextMove = "";
        char firstCharNP = ' ';
        String secondStringNP = "";
        int secondIntNP = 0;
        int insertRow = 0;
        int insertColumn = 0;
        boolean moveMade = true;
        boolean quit = false;
        boolean win = false;
        boolean moveTerm = false;
        String[] receive = new String[2];
        String prevSlide1 = " ";
        ArrayList <String> list = new ArrayList <> ();
        
        if (args.length != 2) {
        	System.out.println("Incorrect number of arguments.");
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
    		if (reader.isEmpty()) {
    			functionality = false;
    		}
    		else {
    			while (reader.hasNextChar()) {
    				list.add(reader.readString());
    			}
    			if (list.size() < 13) {
    				functionality = false;
    			}
    		}
    		if (functionality) {
    			String[] alles = new String[list.size()];
    			alles = (String[]) list.toArray();
    			int counta = 0;
    			for (int i = 0; i < alles.length; i++) {
    				for (int j = 0; j <= 9; j++) {
    					if (alles[i].equals(Integer.toString(j))) {
    						counta++;
        				}
    				}
    			}
    			if (counta != 3) {
    				functionality = false;
    			}
    			else {
    				
    			}
	    		if (functionality) {       			 			
	    			
	    	    	int w = reader.readInt();
	    	    	int h = reader.readInt();
	    	    	
	    	    	int r = reader.readInt();
	    	    	
	    	    	String floatingTileTracker = reader.readString();
	    	    	String[][] floatingTile = new String[5][9];
	    	    	
	    	    	String[] pathways = new String [4];
	    	    	String[][] tracker = new String[h][w];
	    	    	String[][] temp = new String [h][w];
	    	    	for (int i = 0; i < h; i++) {
	    	    		for (int j = 0; j < w; j++) {
	    	    			tracker[i][j] = reader.readString();
	    	    		}
	    	    	}
	    	    	int tw = (w*7) + (w+1) + 2;
	    	    	int th = (h*3) + (h+1) + 2;
	    	    	String[][] wholeBoard = new String[th][tw];
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
	    	    	for (int i = 0; i < h; i++) {
	    	    		for (int j = 0; j < w; j++) {
	    	    			temp[i][j] = " ";
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
	    	    	
	    	    	int greenRelicCollected = 0;
	    	    	int yellowRelicCollected = 0;
	    	    	int redRelicCollected = 0;
	    	    	int blueRelicCollected = 0;
	    	    	
	    	    	int[] greenPos = {0, 0};
	    	        int[] yellowPos = {0, w-1};
	    	        int[] redPos = {h-1, 0};
	    	        int[] bluePos = {h-1, w-1};
	    	        int[] greenPosW = {2, 3}; 
	    	        int[] yellowPosW = {2, tw-4};
	    	        int[] redPosW = {th-3, 3};
	    	        int[] bluePosW = {th-3, tw-4};
	    		    int[] greenRelicPos = {200, 200};
	    		    int[] yellowRelicPos = {200, 200};
	    		    int[] redRelicPos = {200, 200};
	    		    int[] blueRelicPos = {200, 200};
	    		    
	    		    win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    	    	if (win) {
	    	    		gameLoop = false;
	    	    	}
	    		    
	    	        wholeBoard[2][3] = "G";
	    	        wholeBoard[2][tw-4] = "Y";
	    	        wholeBoard[th-3][3] = "R";
	    	        wholeBoard[th-3][tw-4] = "B";
	    	        
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
	    	    			pathways = stringCracker(tracker[i][j], r);
	    	    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    	    		}
	    	    	}
	    	    	pathways = stringCracker(floatingTileTracker, r);
	    	    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    	    	
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
	    	    	
	    	    	while (gameLoop) {
	    		    	
	    	    		if (!quit&&!win) {
	    	    			
	    	    			whoseTurn = "Green";
	    		    		receive = slidingMove (wholeBoard, nextSlide, prevSlide1, whoseTurn, firstCharNP, secondStringNP, secondIntNP, h, w, floatingTileTracker, moveMade, tracker, temp, insertRow, insertColumn, greenPos, greenPosW, yellowPos, yellowPosW, redPos, redPosW, bluePos, bluePosW, th, tw, floatingTile, pathways, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    		    		floatingTileTracker = receive[0];
	    		    		prevSlide1 = receive[1];
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
	    		    		
	    		    		win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    			    	if (win) {
	    			    		gameLoop = false;
	    			    	}
	    		    		
	    		    		if (!quit&&!win) {
	    		    			
	    		    			for (int j = 0; j < w; j++) {
	    				    		for (int i = 0; i < h; i++) {
	    				    			pathways = stringCracker(tracker[i][j], r);
	    				    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    				    		}
	    				    	}
	    		    			pathways = stringCracker(floatingTileTracker, r);
	    				    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
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
	    		    			
	    		    			greenRelicCollected = movingMove (nextMove, whoseTurn, tracker, h, w, moveTerm, wholeBoard, greenPos, greenPosW, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, pathways, th, tw, floatingTile, floatingTileTracker);
	    			    		
	    			    		if (greenRelicCollected >= 1000) {
	    				    		quit = true;
	    				    		gameLoop = false;
	    				    		greenRelicCollected = greenRelicCollected - 1000;
	    				    	}
	    			    		
	    			    		win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
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
	    					    			pathways = stringCracker(tracker[i][j], r);
	    					    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    					    		}
	    					    	}
	    					    	pathways = stringCracker(floatingTileTracker, r);
	    					    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    					    	
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
	    				    		
	    					    	whoseTurn = "Yellow";
	    					    	receive = slidingMove (wholeBoard, nextSlide, prevSlide1, whoseTurn, firstCharNP, secondStringNP, secondIntNP, h, w, floatingTileTracker, moveMade, tracker, temp, insertRow, insertColumn, greenPos, greenPosW, yellowPos, yellowPosW, redPos, redPosW, bluePos, bluePosW, th, tw, floatingTile, pathways, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    					    	floatingTileTracker = receive[0];
	    				    		prevSlide1 = receive[1];
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
	    				    		
	    				    		win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    					    	if (win) {
	    					    		gameLoop = false;
	    					    	}
	    				    		
	    				    		if (!quit&&!win) {
	    				    			
	    				    			for (int j = 0; j < w; j++) {
	    						    		for (int i = 0; i < h; i++) {
	    						    			pathways = stringCracker(tracker[i][j], r);
	    						    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    						    		}
	    						    	}
	    				    			pathways = stringCracker(floatingTileTracker, r);
	    						    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
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
	    				    			
	    				    			yellowRelicCollected = movingMove (nextMove, whoseTurn, tracker, h, w, moveTerm, wholeBoard, yellowPos, yellowPosW, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, pathways, th, tw, floatingTile, floatingTileTracker);
	    					    		
	    					    		if (yellowRelicCollected >= 1000) {
	    						    		quit = true;
	    						    		gameLoop = false;
	    						    		yellowRelicCollected = yellowRelicCollected - 1000;
	    						    	}
	    					    		
	    					    		win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
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
	    							    			pathways = stringCracker(tracker[i][j], r);
	    							    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    							    		}
	    							    	}
	    							    	pathways = stringCracker(floatingTileTracker, r);
	    							    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    							    	
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
	    							    	
	    						    		whoseTurn = "Red";
	    						    		receive = slidingMove (wholeBoard, nextSlide, prevSlide1, whoseTurn, firstCharNP, secondStringNP, secondIntNP, h, w, floatingTileTracker, moveMade, tracker, temp, insertRow, insertColumn, greenPos, greenPosW, yellowPos, yellowPosW, redPos, redPosW, bluePos, bluePosW, th, tw, floatingTile, pathways, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    						    		floatingTileTracker = receive[0];
	    						    		prevSlide1 = receive[1];
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
	    						    		
	    						    		win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    							    	if (win) {
	    							    		gameLoop = false;
	    							    	}
	    						    		
	    						    		if (!quit&&!win) {
	    						    			
	    						    			for (int j = 0; j < w; j++) {
	    								    		for (int i = 0; i < h; i++) {
	    								    			pathways = stringCracker(tracker[i][j], r);
	    								    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    								    		}
	    								    	}
	    						    			pathways = stringCracker(floatingTileTracker, r);
	    								    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
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
	    						    			
	    						    			redRelicCollected = movingMove (nextMove, whoseTurn, tracker, h, w, moveTerm, wholeBoard, redPos, redPosW, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, pathways, th, tw, floatingTile, floatingTileTracker);
	    							    		
	    							    		if (redRelicCollected >= 1000) {
	    								    		quit = true;
	    								    		gameLoop = false;
	    								    		redRelicCollected = redRelicCollected - 1000;
	    								    	}
	    							    		
	    							    		win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
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
	    									    			pathways = stringCracker(tracker[i][j], r);
	    									    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    									    		}
	    									    	}
	    									    	pathways = stringCracker(floatingTileTracker, r);
	    									    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    									    	
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
	    								    		
	    								    		whoseTurn = "Blue";
	    									    	receive = slidingMove (wholeBoard, nextSlide, prevSlide1, whoseTurn, firstCharNP, secondStringNP, secondIntNP, h, w, floatingTileTracker, moveMade, tracker, temp, insertRow, insertColumn, greenPos, greenPosW, yellowPos, yellowPosW, redPos, redPosW, bluePos, bluePosW, th, tw, floatingTile, pathways, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    									    	floatingTileTracker = receive[0];
	    								    		prevSlide1 = receive[1];
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
	    								    		
	    								    		win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
	    									    	if (win) {
	    									    		gameLoop = false;
	    									    	}
	    								    		
	    								    		if (!quit&&!win) {
	    								    			
	    								    			for (int j = 0; j < w; j++) {
	    										    		for (int i = 0; i < h; i++) {
	    										    			pathways = stringCracker(tracker[i][j], r);
	    										    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    										    		}
	    										    	}
	    								    			pathways = stringCracker(floatingTileTracker, r);
	    										    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
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
	    								    			
	    								    			blueRelicCollected = movingMove (nextMove, whoseTurn, tracker, h, w, moveTerm, wholeBoard, bluePos, bluePosW, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, pathways, th, tw, floatingTile, floatingTileTracker);
	    										    	
	    										    	if (blueRelicCollected >= 1000) {
	    										    		quit = true;
	    										    		gameLoop = false;
	    										    		blueRelicCollected = blueRelicCollected - 1000;
	    										    	}
	    										    	
	    										    	win = relicTest (r, h, w, win, tracker, floatingTileTracker, greenPos, yellowPos, redPos, bluePos,  greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected);
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
	    											    			pathways = stringCracker(tracker[i][j], r);
	    											    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    											    		}
	    											    	}
	    											    	pathways = stringCracker(floatingTileTracker, r);
	    											    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
	    											    	
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
    		}
    	}
    }
    
    
    
    // ==========================================================
    // Test API functions
    // ==========================================================

    // Populate these with high-level code that references your codebase.

    // ----------------------------------------------------------
    // First hand-in
    public static String[] stringCracker (String tile, int r) {
    	String[] positionInLine = new String[4];
    	
    	for (int i = 0; i < 4; i++) {
    		if (tile.charAt(i) == '0') {
    			positionInLine[i] = "c";
    		}
    		else if (tile.charAt(i) == '1') {
    			positionInLine[i] = "o";
    		}
    	}
    	
    	return positionInLine;
    }
    public static boolean relicTest (int r, int h, int w, boolean win, String[][] tracker, String floatingTileTracker, int[] greenPos, int[] yellowPos, int[] redPos, int[] bluePos,  int[] greenRelicPos, int[] yellowRelicPos, int[] redRelicPos, int[] blueRelicPos, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected) {
    	
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
    public static String[] slidingMove (String[][] wholeBoard, String nextSlide, String prevSlide, String whoseTurn, char firstCharNP, String secondStringNP, int secondIntNP, int h, int w, String floatingTileTracker, boolean moveMade, String[][] tracker, String[][] temp, int insertRow, int insertColumn, int[] greenPos, int[] greenPosW, int[] yellowPos, int[] yellowPosW, int[] redPos, int[] redPosW, int[] bluePos, int[] bluePosW, int th, int tw, String[][] floatingTile, String[] pathways, int r, int[] greenRelicPos, int[] yellowRelicPos, int[] redRelicPos, int[] blueRelicPos, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected) {
    	
    	String[] sendBack = new String[2];
    	
    	do {
    		nextSlide = validationTestSlide(whoseTurn, nextSlide, prevSlide, firstCharNP, secondStringNP, secondIntNP, h, w);
	    	if (nextSlide.equals("r")) {
	    		floatingTileTracker = rotateTileRight(floatingTileTracker);
	    		System.out.println("Rotating right.");
	    		for (int j = 0; j < w; j++) {
		    		for (int i = 0; i < h; i++) {
		    			pathways = stringCracker(tracker[i][j], r);
		    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
		    		}
		    	}
    			pathways = stringCracker(floatingTileTracker, r);
		    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
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
	    		moveMade = false;
	    	}
	    	else if (nextSlide.equals("l")) {
	    		floatingTileTracker = rotateTileLeft(floatingTileTracker);
	    		System.out.println("Rotating left.");
	    		for (int j = 0; j < w; j++) {
		    		for (int i = 0; i < h; i++) {
		    			pathways = stringCracker(tracker[i][j], r);
		    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
		    		}
		    	}
    			pathways = stringCracker(floatingTileTracker, r);
		    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
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
    	sendBack[0] = floatingTileTracker;
    	sendBack[1] = nextSlide;
    	if (!nextSlide.equals("quit")) {
    		System.out.println("Inserting at " + nextSlide + ".");
    	}
    	return sendBack;
    }
    public static int movingMove (String nextMove, String whoseTurn, String[][] tracker, int h, int w, boolean moveTerm, String[][] wholeBoard, int[] colorPos, int[] colorPosW, int r, int[] greenRelicPos, int[] yellowRelicPos, int[] redRelicPos, int[] blueRelicPos, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected, String[] pathways, int th, int tw, String[][] floatingTile, String floatingTileTracker) {
    	
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
	    	nextMove = validationTestMove (whoseTurn, nextMove, tracker, h, w, colorPos, r, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos);
	    	
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
	    	
	    	if (nextMove.equals("n")||nextMove.equals("s")||nextMove.equals("e")||nextMove.equals("w")) {
	    		
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
		    			pathways = stringCracker(tracker[i][j], r);
		    			getBoard(wholeBoard, pathways, tracker, h, w, i, j, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
		    		}
		    	}
				pathways = stringCracker(floatingTileTracker, r);
		    	getFloatingTile(floatingTile, pathways, greenRelicPos, yellowRelicPos, redRelicPos, blueRelicPos, greenRelicCollected, yellowRelicCollected, redRelicCollected, blueRelicCollected, r);
		    	
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
    public static String[][] getBoard(String[][] wholeBoard, String[] pathways, String[][] tracker, int h, int w, int ch, int cw, int[] greenRelicPos, int[] yellowRelicPos, int[] redRelicPos, int[] blueRelicPos, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected, int r) {
    	
    	String[][] actualTile = new String [3][7];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				actualTile[i][j] = " ";
			}
		}
		
		if (pathways[0].equals("o")) {
			actualTile[0][3] = PATH_NS;
		}
		if (pathways[1].equals("o")) {
			for (int i = 4; i < 7; i++) {
				actualTile[1][i] = PATH_EW;
			}
		}
		if (pathways[2].equals("o")) {
			actualTile[2][3] = PATH_NS;
		}
		if (pathways[3].equals("o")) {
			for (int i = 0; i < 3; i++) {
				actualTile[1][i] = PATH_EW;
			}
		}
		
		if (pathways[0].equals("o")&&pathways[1].equals("o")&&pathways[2].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_NESW;
		}
		else if (pathways[0].equals("o")&&pathways[1].equals("o")&&pathways[2].equals("o")) {
			actualTile[1][3] = PATH_NES;
		}
		else if (pathways[1].equals("o")&&pathways[2].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_ESW;
		}
		else if (pathways[0].equals("o")&&pathways[2].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_NSW;
		}
		else if (pathways[0].equals("o")&&pathways[1].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_NEW;
		}
		else if (pathways[0].equals("o")&&pathways[1].equals("o")) {
			actualTile[1][3] = PATH_NE;
		}
		else if (pathways[1].equals("o")&&pathways[2].equals("o")) {
			actualTile[1][3] = PATH_ES;
		}
		else if (pathways[2].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_SW;
		}
		else if (pathways[0].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_NW;
		}
		else if (pathways[0].equals("o")&&pathways[2].equals("o")) {
			actualTile[1][3] = PATH_NS;
		}
		else if (pathways[1].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_EW;
		}
		
		if (ch == greenRelicPos[0]&&cw == greenRelicPos[1]&&greenRelicCollected < r) {
			actualTile[1][3] = "g";
		}
		if (ch == yellowRelicPos[0]&&cw == yellowRelicPos[1]&&yellowRelicCollected < r) {
			actualTile[1][3] = "y";
		}
		if (ch == redRelicPos[0]&&cw == redRelicPos[1]&&redRelicCollected < r) {
			actualTile[1][3] = "r";
		}
		if (ch == blueRelicPos[0]&&cw == blueRelicPos[1]&&blueRelicCollected < r) {
			actualTile[1][3] = "b";
		}
		
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
		return wholeBoard;
    }
    public static String[][] getFloatingTile (String [][] floatingTile, String[] pathways, int[] greenRelicPos, int[] yellowRelicPos, int[] redRelicPos, int[] blueRelicPos, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected, int r) {
    	String[][] actualTile = new String [3][7];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				actualTile[i][j] = " ";
			}
		}
		
		if (pathways[0].equals("o")) {
			actualTile[0][3] = PATH_NS;
		}
		if (pathways[1].equals("o")) {
			for (int i = 4; i < 7; i++) {
				actualTile[1][i] = PATH_EW;
			}
		}
		if (pathways[2].equals("o")) {
			actualTile[2][3] = PATH_NS;
		}
		if (pathways[3].equals("o")) {
			for (int i = 0; i < 3; i++) {
				actualTile[1][i] = PATH_EW;
			}
		}
		
		if (pathways[0].equals("o")&&pathways[1].equals("o")&&pathways[2].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_NESW;
		}
		else if (pathways[0].equals("o")&&pathways[1].equals("o")&&pathways[2].equals("o")) {
			actualTile[1][3] = PATH_NES;
		}
		else if (pathways[1].equals("o")&&pathways[2].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_ESW;
		}
		else if (pathways[0].equals("o")&&pathways[2].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_NSW;
		}
		else if (pathways[0].equals("o")&&pathways[1].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_NEW;
		}
		else if (pathways[0].equals("o")&&pathways[1].equals("o")) {
			actualTile[1][3] = PATH_NE;
		}
		else if (pathways[1].equals("o")&&pathways[2].equals("o")) {
			actualTile[1][3] = PATH_ES;
		}
		else if (pathways[2].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_SW;
		}
		else if (pathways[0].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_NW;
		}
		else if (pathways[0].equals("o")&&pathways[2].equals("o")) {
			actualTile[1][3] = PATH_NS;
		}
		else if (pathways[1].equals("o")&&pathways[3].equals("o")) {
			actualTile[1][3] = PATH_EW;
		}
		
		if (greenRelicPos[0] == 100) {
			actualTile[1][3] = "g";
		}
		if (yellowRelicPos[0] == 100) {
			actualTile[1][3] = "y";
		}
		if (redRelicPos[0] == 100) {
			actualTile[1][3] = "r";
		}
		if (blueRelicPos[0] == 100) {
			actualTile[1][3] = "b";
		}
		
		for (int i = 1, y = 0; i <= 3; i++, y++) {
			for (int j = 1, z = 0; j <= 7; j++, z++) {
				floatingTile[i][j] = actualTile[y][z];
			}
		}
    	return floatingTile;
    }
    public static String validationTestSlide (String whoseTurn, String nextSlide, String prevSlide, char firstCharNP, String secondStringNP, int secondIntNP, int h, int w) {
    	
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
    public static String validationTestMove (String whoseTurn, String nextMove, String[][] tracker, int h, int w, int[] colorPos, int r, int[] greenRelicPos, int[] yellowRelicPos, int[] redRelicPos, int[] blueRelicPos) {
    	
    	boolean isValid = true;
    	boolean noPath = false;
    	boolean offBoard = false;
    	String where = "";
    	int count = 0;
		int rowStore = 0;
		int columnStore = 0;
    	
    	String currentPlace = tracker[colorPos[0]][colorPos[1]];
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
    	pathways = stringCracker(currentPlace, r);
    	if (colorPos[0] != 0) {
    		pathwaysUpper = stringCracker(tracker[colorPos[0]-1][colorPos[1]], r);
    	}
    	if (colorPos[0] != h-1) {
    		pathwaysLower = stringCracker(tracker[colorPos[0]+1][colorPos[1]], r);
    	}
    	if (colorPos[1] != 0) {
    		pathwaysLeft = stringCracker(tracker[colorPos[0]][colorPos[1]-1], r);
    	}
    	if (colorPos[1] != w-1) {
    		pathwaysRight = stringCracker(tracker[colorPos[0]][colorPos[1]+1], r);
    	}
    	
    	do {
			System.out.println("[" + whoseTurn + "] Move your adventurer:");
    		System.out.print("> ");
    		nextMove = StdIn.readString();
		} while (nextMove.isEmpty());
    	do {
	    	if ((!nextMove.equals("n")&&!nextMove.equals("e")&&!nextMove.equals("w")&&!nextMove.equals("s")&&!nextMove.equals("done")&&!nextMove.equals("quit"))||!isValid) {
	    		
//	    		for (int i = 1; i <= 9; i++) {
//	    			for (int j = 1; j <= 9; j++) {
//	    				if (nextMove.equals(Integer.toString(i) + "," + Integer.toString(j))) {
//	    					count++;
//	    					rowStore = i;
//	    					columnStore = j;
//	    				}
//	    			}
//	    		}
//	    		if (count == 0) {
		    		do {
		    			if (offBoard) {
		    				System.out.println("Cannot move " + where + ": off the board.");
		    				offBoard = false;
		    				where = "";
		    			}
		    			else if (noPath) {
		    				System.out.println("Cannot move " + where + ": no path.");
		    				noPath = false;
		    				count = 0;
		    				where = "";
		    			}
		    			else {
		    				System.out.println("Invalid input.");
		    			}
			    		do {
			    			System.out.println("[" + whoseTurn + "] Move your adventurer:");
				    		System.out.print("> ");
				    		nextMove = StdIn.readString();
			    		} while (nextMove.isEmpty());
		    		} while (!nextMove.equals("n")&&!nextMove.equals("e")&&!nextMove.equals("w")&&!nextMove.equals("s")&&!nextMove.equals("done")&&!nextMove.equals("quit"));	
//	    		}
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
//	    	else if (count > 0) {
//	    		noPath = pathTest(tracker, h, w, r, rowStore, columnStore, colorPos);
//	    	}
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
    		else if (count > 0) {
    			where = Integer.toString(rowStore) + "," + Integer.toString(columnStore);
    		}
    	} while (!isValid);
    	return nextMove;
    }
    public static boolean pathTest (String[][] tracker, int h, int w, int r, int rowStore, int columnStore, int[] colorPos) {
    	
    	//idk
    	
    	return true;
    }
    public static String rotateTileRight(String floatingTileTracker) {
        
    	String first = String.valueOf(floatingTileTracker.charAt(0));
    	String second = String.valueOf(floatingTileTracker.charAt(1));
    	String third = String.valueOf(floatingTileTracker.charAt(2));
    	String fourth = String.valueOf(floatingTileTracker.charAt(3));
    	String fifth = String.valueOf(floatingTileTracker.charAt(4));
    	String sixth = String.valueOf(floatingTileTracker.charAt(5));
    	
    	floatingTileTracker = fourth + first + second + third + fifth + sixth;
    	
    	return floatingTileTracker; 
    }

    public static String rotateTileLeft(String floatingTileTracker) {
        
    	String first = String.valueOf(floatingTileTracker.charAt(0));
    	String second = String.valueOf(floatingTileTracker.charAt(1));
    	String third = String.valueOf(floatingTileTracker.charAt(2));
    	String fourth = String.valueOf(floatingTileTracker.charAt(3));
    	String fifth = String.valueOf(floatingTileTracker.charAt(4));
    	String sixth = String.valueOf(floatingTileTracker.charAt(5));
    	
    	floatingTileTracker = second + third + fourth + first + fifth + sixth;
    	
    	return floatingTileTracker; 
    }
    public static boolean isTileOpenToSide(String tileEncoding, char dir) {
        
    	boolean isItDoe = false;
    	if (tileEncoding.charAt(0) == '1'&&dir == 'n') {
    		isItDoe = true;
    	}
    	else if (tileEncoding.charAt(1) == '1'&&dir == 'e') {
    		isItDoe = true;
    	}
    	else if (tileEncoding.charAt(2) == '1'&&dir == 's') {
    		isItDoe = true;
    	}
    	else if (tileEncoding.charAt(3) == '1'&&dir == 'w') {
    		isItDoe = true;
    	}
    	else {
    		isItDoe = false;
    	}
    	return isItDoe;
    }
    
    public static boolean[] rotateTileClockwise(String tileEncoding) {
        
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

    public static boolean[] rotateTileCounterclockwise(String tileEncoding) {
    	
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
    
    public static boolean[] slideTileIntoMaze1(String[][] mazeTileEncodings, String floatingTileEncoding, String slidingIndicator) {
        
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

    public static boolean canMoveInDirection(String curTileEncoding, String newTileEncoding, char dir) {
        
    	boolean canYouDoe = false;
    	if (dir == 'n') {
    		if (curTileEncoding.charAt(0) == '1'&&newTileEncoding.charAt(2) == '1') {
    			canYouDoe = true;
    		}
    	}
    	else if (dir == 's') {
    		if (curTileEncoding.charAt(2) == '1'&&newTileEncoding.charAt(0) == '1') {
    			canYouDoe = true;
    		}
    	}
    	else if (dir == 'e') {
    		if (curTileEncoding.charAt(1) == '1'&&newTileEncoding.charAt(3) == '1') {
    			canYouDoe = true;
    		}
    	}
    	else if (dir == 'w') {
    		if (curTileEncoding.charAt(3) == '1'&&newTileEncoding.charAt(1) == '1') {
    			canYouDoe = true;
    		}
    	}
    	return canYouDoe; 
    }

    public static boolean canMoveAlongPath(String[][] mazeTileEncodings, char[] steps) {
        
    	boolean canYouDoe = true;
    	int[] colorPos = {0, 0};
    	for (int i = 0; i < steps.length; i++) {
    		if ((steps[i] == 'n'&&colorPos[0] == 0)||(steps[i] == 's'&&colorPos[0] == mazeTileEncodings.length -1)||(steps[i] == 'w'&&colorPos[1] == 0)||(steps[i] == 'e'&&colorPos[1] == mazeTileEncodings[0].length -1)) {
    			canYouDoe = false;
    			i = steps.length;
    		}
    		else if (steps[i] == 'n') {
        		if (mazeTileEncodings[colorPos[0]][colorPos[1]].charAt(0) != '1'||mazeTileEncodings[colorPos[0]-1][colorPos[1]].charAt(2) != '1') {
        			canYouDoe = false;
        			i = steps.length;
        		}
        		else {
        			colorPos[0] = colorPos[0] - 1;
        		}
        	}
        	else if (steps[i] == 's') {
        		if (mazeTileEncodings[colorPos[0]][colorPos[1]].charAt(2) != '1'||mazeTileEncodings[colorPos[0]+1][colorPos[1]].charAt(0) != '1') {
        			canYouDoe = false;
        			i = steps.length;
        		}
        		else {
        			colorPos[0] = colorPos[0] + 1;
        		}
        	}
        	else if (steps[i] == 'e') {
        		if (mazeTileEncodings[colorPos[0]][colorPos[1]].charAt(1) != '1'||mazeTileEncodings[colorPos[0]][colorPos[1]+1].charAt(3) != '1') {
        			canYouDoe = false;
        			i = steps.length;
        		}
        		else {
        			colorPos[1] = colorPos[1] + 1;
        		}
        	}
        	else if (steps[i] == 'w') {
        		if (mazeTileEncodings[colorPos[0]][colorPos[1]].charAt(3) != '1'||mazeTileEncodings[colorPos[0]][colorPos[1]-1].charAt(1) != '1') {
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

    // ----------------------------------------------------------
    // Second hand-in

    public static boolean tileHasRelic(String tileEncoding) {
        return false; // TODO
    }

    public static char slideTileIntoMaze2(String[][] mazeTileEncodings, String floatingTileEncoding, String slidingIndicator) {
        return '?'; // TODO
    }

}