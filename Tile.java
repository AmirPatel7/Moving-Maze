/**
 * This class is responsible for anything that has to do with Tiles. 
 * It creates, updates and manipulates tiles.
 * @author Amir Patel 26034670
 *
 */
public class Tile {
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
    
	private String code;
	
	/**
	 * This constructor is used to create, update and manipulate tiles.
	 * @param encoding This is the encoding of the tile, stored as a {@code String}.
	 */
	public Tile (String encoding) {
		code = encoding;
	}
	
	/**
	 * Breaks the encoding down to make it simpler.
	 * @param actualTile The tile that wants to be broken down, stored as a {@link Tile Tile}.
	 * @param r The amount of relics in the game (per player), stored as an {@code int}.
	 * @return Returns an array of Strings determining if a side or direction is open or closed.
	 */
	public String[] stringCracker (Tile actualTile, int r) {
    	
		String tile = actualTile.code;
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
	
	/**
	 * Uses the broken down encoding from the {@link stringCracker stringCracker} method to form a 2D array String version of the tile.
	 * @param tile The tile that wants to be formed or created, stored as a {@link Tile Tile}.
	 * @param ch This is the current height of where the Tile lies on the board, stored as an {@code int}.
	 * @param cw This is the current width of where the Tile lies on the board, stored as an {@code int}.
	 * @param greenRelicPos The current position of the active relic for green, stored as an array of type {@code int}.
	 * @param yellowRelicPos The current position of the active relic for yellow, stored as an array of type {@code int}.
	 * @param redRelicPos The current position of the active relic for red, stored as an array of type {@code int}.
	 * @param blueRelicPos The current position of the active relic for blue, stored as an array of type {@code int}.
	 * @param greenRelicCollected This is the amount of relics green has collected, stored as an {@code int}.
	 * @param yellowRelicCollected This is the amount of relics yellow has collected, stored as an {@code int}.
	 * @param redRelicCollected This is the amount of relics red has collected, stored as an {@code int}.
	 * @param blueRelicCollected This is the amount of relics blue has collected, stored as an {@code int}.
	 * @param r The amount of relics in the game (per player), stored as an {@code int}.
	 * @return Returns the 2D array String form of the Tile.
	 */
	public String[][] getTile (Tile tile, int ch, int cw, int[] greenRelicPos, int[] yellowRelicPos, int[] redRelicPos, int[] blueRelicPos, int greenRelicCollected, int yellowRelicCollected, int redRelicCollected, int blueRelicCollected, int r) {
		
		String[] pathways = new String[4];
		String[][] actualTile = new String [3][7];
		pathways = stringCracker(tile, r);
		
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
		
		return actualTile;
	}
	
	/** 
	 * Uses the broken down encoding from the {@link stringCracker stringCracker} method to form a 2D array String version of the tile.
	 * @param tile The current floating tile, stored as a {@link Tile Tile}.
	 * @param greenRelicPos The current position of the active relic for green, stored as an array of type {@code int}.
	 * @param yellowRelicPos The current position of the active relic for yellow, stored as an array of type {@code int}.
	 * @param redRelicPos The current position of the active relic for red, stored as an array of type {@code int}.
	 * @param blueRelicPos The current position of the active relic for blue, stored as an array of type {@code int}.
	 * @param r The amount of relics in the game (per player), stored as an {@code int}.
	 * @return
	 */
	public String[][] getFloatingTile (Tile tile, int[] greenRelicPos, int[] yellowRelicPos, int[] redRelicPos, int[] blueRelicPos, int r) {
		
		String[] pathways = new String[4];
		String[][] actualTile = new String [3][7];
		pathways = stringCracker(tile, r);
		
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
		
    	return actualTile;
	}
}
