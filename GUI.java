
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * This class is responsible for all graphical components of the game. 
 * It extends {@code JFrame} and implements {@code KeyListener}, {@code MouseListener} and {@code ActionListener}.
 * @author Amir Patel 26034670
 *
 */
public class GUI extends JFrame implements KeyListener ,MouseListener, ActionListener {
	
	private final int w;
	private final int h;
	private final int r;
	private int[] greenPos;
	private int[] yellowPos;
	private int[] redPos;
	private int[] bluePos;
	private int[] greenRelicPos = {200, 200};
	private int[] yellowRelicPos = {200, 200};
	private int[] redRelicPos = {200, 200};
	private int[] blueRelicPos = {200, 200};
	private int greenRelicCollected = 0;
	private int yellowRelicCollected = 0;
	private int redRelicCollected = 0;
	private int blueRelicCollected = 0;
	private int pathRow = 0;
	private int pathCol = 0;
	private int[][] arrayPathFinding;
    private char[] dirs = {'n', 'e', 's', 'w'};
    private String whoseTurn;
    private String nextSlide;
    private String prevSlide;
    private boolean moveMade = true;
    private boolean pickedRowCol = false;
    private boolean pathRowChosen = false;
    private boolean pathColChosen = false;
    private boolean relicCollected;
	private String[][] tracker;
	private String[][] startingTracker;
	private String floatingTileTracker;
	private String startingFloatingTileTracker;
	private int bw;
	private int bh;
	
	private JMenuBar menuBar;
	private JMenu file;
	private JMenu help;
	private JMenu score;
	private JMenuItem quit;
	private JMenuItem restart;
	private JMenuItem backStory;
	private JMenuItem instructions;
	private JMenuItem scoreBoard;
	private Color currColor;
	private JPanel rightPan;
	private JPanel topPan;
	private JPanel leftPan;
	private JPanel bottomPan;
	private JPanel slideOptions;
	private JLabel slideOptionsText;
	private JLabel rightNum;
	private JLabel leftNum;
	private JLabel topNum;
	private JLabel bottomNum;
	private JLabel greenAd;
	private JLabel yellowAd;
	private JLabel redAd;
	private JLabel blueAd;
	private JLabel greenRel;
	private JLabel yellowRel;
	private JLabel redRel;
	private JLabel blueRel;
	private JLabel title;
	private JLabel label;
	private JLabel welcomeLabel;
	private JLabel headingLineTut;
	private JLabel firstLineTut;
	private JLabel secondLineTut;
	private JLabel thirdLineTut;
	private JLabel fourthLineTut;
	private JLabel fifthLineTut;
	private JLabel sixthLineTut;
	private JLabel seventhLineTut;
	private JLabel eighthLineTut;
	private JLabel ninthLineTut;
	private JLabel tenthLineTut;
	private JLabel eleventhLineTut;
	private JLabel twelfthLineTut;
	private JPanel welcomePanel;
	private JPanel floatingPanel;
	private JPanel bigPanel;
	private JPanel titlePanel;
	private JPanel tutorial;
	private JPanel errorPanel;
	private JPanel relicPanel;
	private ImageIcon image;
	private ImageIcon currImage;
	private JButton cont1;
	private JButton cont2;
	private JButton rotateright;
	private JButton rotateleft;
	private JButton north;
	private JButton south;
	private JButton east;
	private JButton west;
	private JButton two;
	private JButton four;
	private JButton six;
	private JButton eight;
	private JButton done;
	private JButton pathFinder;
	private JPanel textBox;
	private JLabel textDisplayed;
	private JLabel errorMsg;
	private JLabel relicMsg;
	private JPanel madeBy;
	private JLabel name;
	private JPanel scoreBoardPanel;
	private JLabel relicsOutOf;
	private JLabel greenScore;
	private JLabel yellowScore;
	private JLabel redScore;
	private JLabel blueScore;
	private JPanel pathRowPanel;
	private JPanel pathColPanel;
	private JLabel pathRowLabel;
	private JLabel pathColLabel;
	private JButton rowOne;
	private JButton rowTwo;
	private JButton rowThree;
	private JButton rowFour;
	private JButton rowFive;
	private JButton rowSix;
	private JButton rowSeven;
	private JButton rowEight;
	private JButton rowNine;
	private JButton colOne;
	private JButton colTwo;
	private JButton colThree;
	private JButton colFour;
	private JButton colFive;
	private JButton colSix;
	private JButton colSeven;
	private JButton colEight;
	private JButton colNine;
//	JComboBox pickRow;
//	JComboBox pickCol;
	
	/**
	 * This constructor constructs the {@code JFrame} that the game uses. 
	 * <p>
	 * In this constructor, all the {@code JPanels}, {@code JLabels}, {@code ImageIcons} and {@code Colors}
	 * are used and implemented. The entire frame and all its components are initialized with 
	 * variables and other components in this constructor. This sets values to every component and 
	 * displays the board. 
	 * @param height This is the height of the board, stored as an {@code int}.
     * @param width This is the width of the board, stored as an {@code int}.
     * @param relics This is the amount of relics there are, stored as an {@code int}.
     * @param fTT This is the starting floating tile (in encoding), stored as a {@code String}.
     * @param track This is the starting state of the board (in encodings), stored as a 2D array of type {@code String}.
	 */
	GUI (int width, int height, int relics, String fTT, String[][] track) {
		
		w = width;
		h = height;
		r = relics;
		floatingTileTracker = fTT;
		startingFloatingTileTracker = fTT;
		tracker = new String[h][w];
		startingTracker = new String[h][w];
		arrayPathFinding = new int[h][w];
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				tracker[i][j] = track[i][j];
				startingTracker[i][j] = track[i][j];
			}
		}
		
		if (w == 3) {
			bw = 1300;
		}
		else if (w == 5) {
			bw = 1100;
		}
		else if (w == 7) {
			bw = 1000;
		}
		else if (w == 9) {
			bw = 900;
		}
		if (h == 3) {
			bh = 300;
		}
		else if (h == 5) {
			bh = 200;
		}
		else if (h == 7) {
			bh = 150;
		}
		else if (h == 9) {
			bh = 50;
		}
		
		updateRelics();
		
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
		
    	menuBar = new JMenuBar();
    	file = new JMenu("File");
    	help = new JMenu("Help");
    	score = new JMenu("Score");
    	quit = new JMenuItem("Quit");
    	restart = new JMenuItem("Restart");
    	backStory = new JMenuItem("Back Story");
    	instructions = new JMenuItem("Instructions");
    	scoreBoard = new JMenuItem("Score Board");
		label = new JLabel();
		slideOptionsText = new JLabel();
		textDisplayed = new JLabel();
		errorMsg = new JLabel();
		relicMsg = new JLabel();
		rightNum = new JLabel();
		leftNum = new JLabel();
		topNum = new JLabel();
		bottomNum = new JLabel();
		textBox = new JPanel();
		rightPan = new JPanel();
		leftPan = new JPanel();
		topPan = new JPanel();
		bottomPan = new JPanel();
		welcomeLabel = new JLabel();
		headingLineTut = new JLabel();
		firstLineTut = new JLabel();
		secondLineTut = new JLabel();
		thirdLineTut = new JLabel();
		fourthLineTut = new JLabel();
		fifthLineTut = new JLabel();
		sixthLineTut = new JLabel();
		seventhLineTut = new JLabel();
		eighthLineTut = new JLabel();
		ninthLineTut = new JLabel();
		tenthLineTut = new JLabel();
		eleventhLineTut = new JLabel();
		twelfthLineTut = new JLabel();
		title = new JLabel();
		greenAd = new JLabel();
		yellowAd = new JLabel();
		redAd = new JLabel();
		blueAd = new JLabel();
		greenRel = new JLabel();
		yellowRel = new JLabel();
		redRel = new JLabel();
		blueRel = new JLabel();
		bigPanel = new JPanel();
		floatingPanel = new JPanel();
		titlePanel = new JPanel();
		welcomePanel = new JPanel();
		slideOptions = new JPanel();
		tutorial = new JPanel();
		errorPanel = new JPanel();
		relicPanel = new JPanel();
		scoreBoardPanel = new JPanel();
		relicsOutOf = new JLabel();
		greenScore = new JLabel();
		yellowScore = new JLabel();
		redScore = new JLabel();
		blueScore = new JLabel();
		cont1 = new JButton();
		cont2 = new JButton();
		rotateright = new JButton();
		rotateleft = new JButton();
		north = new JButton();
		south = new JButton();
		east = new JButton();
		west = new JButton();
		two = new JButton();
		four = new JButton();
		six = new JButton();
		eight = new JButton();
		done = new JButton();
		pathFinder = new JButton();
		pathRowPanel = new JPanel();
		pathColPanel = new JPanel();
		pathRowLabel = new JLabel();
		pathColLabel = new JLabel();
		rowOne = new JButton("1");
		rowTwo = new JButton("2");
		rowThree = new JButton("3");
		rowFour = new JButton("4");
		rowFive = new JButton("5");
		rowSix = new JButton("6");
		rowSeven = new JButton("7");
		rowEight = new JButton("8");
		rowNine = new JButton("9");
		colOne = new JButton("1");
		colTwo = new JButton("2");
		colThree = new JButton("3");
		colFour = new JButton("4");
		colFive = new JButton("5");
		colSix = new JButton("6");
		colSeven = new JButton("7");
		colEight = new JButton("8");
		colNine = new JButton("9");
		madeBy = new JPanel();
		name = new JLabel(new ImageIcon("name.png"));
		currImage = new ImageIcon("greenTitle.png");
		currColor = new Color(50, 190, 120);
		whoseTurn = "[Green]";
		nextSlide = "";
		prevSlide = "";
//		pickRow = new JComboBox(rows);
//    	pickCol = new JComboBox(columns);
//    	
//    	pickRow.setBounds(150, 640, 250, 80);
//    	pickCol.setBounds(700, 640, 250, 80);
//    	
//    	pickRow.setFont(new Font("Impact", Font.ITALIC, 20));
//    	pickCol.setFont(new Font("Impact", Font.ITALIC, 20));
//    	
//    	pickRow.setBackground(Color.black);
//    	pickCol.setBackground(Color.black);
//    	
//    	pickRow.setForeground(Color.white);
//    	pickCol.setForeground(Color.white);
//    	
//    	pickRow.addActionListener(this);
//    	pickCol.addActionListener(this);
    	
		done.addActionListener(this);
		done.setBounds(450, 640, 200, 100);
		done.setText("DONE");
		done.setFont(new Font("Impact", Font.ITALIC, 30));
		done.setFocusable(false);
		done.setForeground(currColor);
		done.setBackground(Color.black);
		done.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		pathFinder.addActionListener(this);
		pathFinder.setBounds(450, 790, 200, 170);
		pathFinder.setLayout(null);
		pathFinder.setText("USE PATHFINDER");
		pathFinder.setFont(new Font("Impact", Font.ITALIC, 18));
		pathFinder.setIcon(new ImageIcon("pathFinder.png"));
		pathFinder.setVerticalAlignment(JButton.TOP);
		pathFinder.setHorizontalAlignment(JButton.CENTER);
		pathFinder.setVerticalTextPosition(JButton.BOTTOM);
		pathFinder.setHorizontalTextPosition(JButton.CENTER);
		pathFinder.setFocusable(false);
		pathFinder.setForeground(currColor);
		pathFinder.setBackground(Color.black);
		pathFinder.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		cont1.addActionListener(this);
		cont1.setBounds(650, 600, 500, 100);
		cont1.setText("CLICK ME TO CONTINUE INTO SPACE...");
		cont1.setFocusable(false);
		cont1.setForeground(Color.lightGray);
		cont1.setBackground(Color.black);
		cont1.setFont(new Font("Comic Sans", Font.BOLD, 20));
		cont1.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		
		cont2.addActionListener(this);
		cont2.setBounds(650, 800, 500, 100);
		cont2.setText("NEXT");
		cont2.setFocusable(false);
		cont2.setForeground(Color.lightGray);
		cont2.setBackground(Color.black);
		cont2.setFont(new Font("Comic Sans", Font.BOLD, 20));
		cont2.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		
		rotateleft.addActionListener(this);
		rotateleft.setBounds(330, 500, 150, 110);
		rotateleft.setText("ROTATE TILE LEFT");
		rotateleft.setIcon(new ImageIcon("anticlockwise.png"));
		rotateleft.setLayout(new BorderLayout());
		rotateleft.setVerticalTextPosition(JLabel.TOP);
		rotateleft.setVerticalAlignment(JLabel.BOTTOM);
		rotateleft.setHorizontalAlignment(JLabel.CENTER);
		rotateleft.setHorizontalTextPosition(JLabel.CENTER);
		rotateleft.setFont(new Font("Impact", Font.ITALIC, 16));
		rotateleft.setFocusable(false);
		rotateleft.setForeground(currColor);
		rotateleft.setBackground(Color.black);
		rotateleft.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		rotateright.addActionListener(this);
		rotateright.setBounds(630, 500, 150, 110);
		rotateright.setText("ROTATE TILE RIGHT");
		rotateright.setIcon(new ImageIcon("clockwise.png"));
		rotateright.setLayout(new BorderLayout());
		rotateright.setVerticalTextPosition(JLabel.TOP);
		rotateright.setVerticalAlignment(JLabel.BOTTOM);
		rotateright.setHorizontalAlignment(JLabel.CENTER);
		rotateright.setHorizontalTextPosition(JLabel.CENTER);
		rotateright.setFont(new Font("Impact", Font.ITALIC, 16));
		rotateright.setFocusable(false);
		rotateright.setForeground(currColor);
		rotateright.setBackground(Color.black);
		rotateright.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		north.addActionListener(this);
		north.setBounds(330, 650, 120, 80);
		north.setText("NORTHERN SIDE");
		north.setFont(new Font("Impact", Font.ITALIC, 16));
		north.setFocusable(false);
		north.setForeground(currColor);
		north.setBackground(Color.black);
		north.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		south.addActionListener(this);
		south.setBounds(460, 650, 120, 80);
		south.setText("SOUTHERN SIDE");
		south.setFont(new Font("Impact", Font.ITALIC, 16));
		south.setFocusable(false);
		south.setForeground(currColor);
		south.setBackground(Color.black);
		south.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		east.addActionListener(this);
		east.setBounds(590, 650, 120, 80);
		east.setText("EASTERN SIDE");
		east.setFont(new Font("Impact", Font.ITALIC, 16));
		east.setFocusable(false);
		east.setForeground(currColor);
		east.setBackground(Color.black);
		east.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		west.addActionListener(this);
		west.setBounds(720, 650, 120, 80);
		west.setText("WESTERN SIDE");
		west.setFont(new Font("Impact", Font.ITALIC, 16));
		west.setFocusable(false);
		west.setForeground(currColor);
		west.setBackground(Color.black);
		west.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		two.addActionListener(this);
		two.setBounds(330, 740, 120, 80);
		two.setFont(new Font("Impact", Font.ITALIC, 16));
		two.setFocusable(false);
		two.setForeground(currColor);
		two.setBackground(Color.black);
		two.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		four.addActionListener(this);
		four.setBounds(460, 740, 120, 80);
		four.setFont(new Font("Impact", Font.ITALIC, 16));
		four.setFocusable(false);
		four.setForeground(currColor);
		four.setBackground(Color.black);
		four.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		six.addActionListener(this);
		six.setBounds(590, 740, 120, 80);
		six.setFont(new Font("Impact", Font.ITALIC, 16));
		six.setFocusable(false);
		six.setForeground(currColor);
		six.setBackground(Color.black);
		six.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		eight.addActionListener(this);
		eight.setBounds(720, 740, 120, 80);
		eight.setFont(new Font("Impact", Font.ITALIC, 16));
		eight.setFocusable(false);
		eight.setForeground(currColor);
		eight.setBackground(Color.black);
		eight.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		
		rowOne.addActionListener(this);
		rowOne.setBounds(170 + 90, 640, 50, 50);
		rowOne.setFont(new Font("Impact", Font.ITALIC, 16));
		rowOne.setFocusable(false);
		rowOne.setForeground(currColor);
		rowOne.setBackground(Color.black);
		rowOne.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		rowTwo.addActionListener(this);
		rowTwo.setBounds(240 + 90 - 5, 640, 50, 50);
		rowTwo.setFont(new Font("Impact", Font.ITALIC, 16));
		rowTwo.setFocusable(false);
		rowTwo.setForeground(currColor);
		rowTwo.setBackground(Color.black);
		rowTwo.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		rowThree.addActionListener(this);
		rowThree.setBounds(310 + 90 - 10, 640, 50, 50);
		rowThree.setFont(new Font("Impact", Font.ITALIC, 16));
		rowThree.setFocusable(false);
		rowThree.setForeground(currColor);
		rowThree.setBackground(Color.black);
		rowThree.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		rowFour.addActionListener(this);
		rowFour.setBounds(380 + 90 - 15, 640, 50, 50);
		rowFour.setFont(new Font("Impact", Font.ITALIC, 16));
		rowFour.setFocusable(false);
		rowFour.setForeground(currColor);
		rowFour.setBackground(Color.black);
		rowFour.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		rowFive.addActionListener(this);
		rowFive.setBounds(450 + 90 - 20, 640, 50, 50);
		rowFive.setFont(new Font("Impact", Font.ITALIC, 16));
		rowFive.setFocusable(false);
		rowFive.setForeground(currColor);
		rowFive.setBackground(Color.black);
		rowFive.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		rowSix.addActionListener(this);
		rowSix.setBounds(520 + 90 - 25, 640, 50, 50);
		rowSix.setFont(new Font("Impact", Font.ITALIC, 16));
		rowSix.setFocusable(false);
		rowSix.setForeground(currColor);
		rowSix.setBackground(Color.black);
		rowSix.setBorder(BorderFactory.createLineBorder(currColor, 2, true));

		rowSeven.addActionListener(this);
		rowSeven.setBounds(590 + 90 - 30, 640, 50, 50);
		rowSeven.setFont(new Font("Impact", Font.ITALIC, 16));
		rowSeven.setFocusable(false);
		rowSeven.setForeground(currColor);
		rowSeven.setBackground(Color.black);
		rowSeven.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		rowEight.addActionListener(this);
		rowEight.setBounds(660 + 90 - 35, 640, 50, 50);
		rowEight.setFont(new Font("Impact", Font.ITALIC, 16));
		rowEight.setFocusable(false);
		rowEight.setForeground(currColor);
		rowEight.setBackground(Color.black);
		rowEight.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		rowNine.addActionListener(this);
		rowNine.setBounds(730 + 90 - 40, 640, 50, 50);
		rowNine.setFont(new Font("Impact", Font.ITALIC, 16));
		rowNine.setFocusable(false);
		rowNine.setForeground(currColor);
		rowNine.setBackground(Color.black);
		rowNine.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		colOne.addActionListener(this);
		colOne.setBounds(170 + 90, 740, 50, 50);
		colOne.setFont(new Font("Impact", Font.ITALIC, 16));
		colOne.setFocusable(false);
		colOne.setForeground(currColor);
		colOne.setBackground(Color.black);
		colOne.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		colTwo.addActionListener(this);
		colTwo.setBounds(240 + 90 - 5, 740, 50, 50);
		colTwo.setFont(new Font("Impact", Font.ITALIC, 16));
		colTwo.setFocusable(false);
		colTwo.setForeground(currColor);
		colTwo.setBackground(Color.black);
		colTwo.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		colThree.addActionListener(this);
		colThree.setBounds(310 + 90 - 10, 740, 50, 50);
		colThree.setFont(new Font("Impact", Font.ITALIC, 16));
		colThree.setFocusable(false);
		colThree.setForeground(currColor);
		colThree.setBackground(Color.black);
		colThree.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		colFour.addActionListener(this);
		colFour.setBounds(380 + 90 - 15, 740, 50, 50);
		colFour.setFont(new Font("Impact", Font.ITALIC, 16));
		colFour.setFocusable(false);
		colFour.setForeground(currColor);
		colFour.setBackground(Color.black);
		colFour.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		colFive.addActionListener(this);
		colFive.setBounds(450 + 90 - 20, 740, 50, 50);
		colFive.setFont(new Font("Impact", Font.ITALIC, 16));
		colFive.setFocusable(false);
		colFive.setForeground(currColor);
		colFive.setBackground(Color.black);
		colFive.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		colSix.addActionListener(this);
		colSix.setBounds(520 + 90 - 25, 740, 50, 50);
		colSix.setFont(new Font("Impact", Font.ITALIC, 16));
		colSix.setFocusable(false);
		colSix.setForeground(currColor);
		colSix.setBackground(Color.black);
		colSix.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		colSeven.addActionListener(this);
		colSeven.setBounds(590 + 90 - 30, 740, 50, 50);
		colSeven.setFont(new Font("Impact", Font.ITALIC, 16));
		colSeven.setFocusable(false);
		colSeven.setForeground(currColor);
		colSeven.setBackground(Color.black);
		colSeven.setBorder(BorderFactory.createLineBorder(currColor, 2, true));

		colEight.addActionListener(this);
		colEight.setBounds(660 + 90 - 35, 740, 50, 50);
		colEight.setFont(new Font("Impact", Font.ITALIC, 16));
		colEight.setFocusable(false);
		colEight.setForeground(currColor);
		colEight.setBackground(Color.black);
		colEight.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		colNine.addActionListener(this);
		colNine.setBounds(730 + 90 - 40, 740, 50, 50);
		colNine.setFont(new Font("Impact", Font.ITALIC, 16));
		colNine.setFocusable(false);
		colNine.setForeground(currColor);
		colNine.setBackground(Color.black);
		colNine.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		
		greenAd.setIcon(new ImageIcon("greenAd.png"));
		greenAd.setBounds(10, 5, 50, 50);
		
		yellowAd.setIcon(new ImageIcon("yellowAd.png"));
		yellowAd.setBounds(30, 5, 50, 50);
		
		redAd.setIcon(new ImageIcon("redAd.png"));
		redAd.setBounds(10, 15, 50, 50);
		
		blueAd.setIcon(new ImageIcon("blueAd.png"));
		blueAd.setBounds(30, 15, 50, 50);
		
		greenRel.setIcon(new ImageIcon("greenRel.png"));
		greenRel.setBounds(25, 25, 30, 30);
		
		yellowRel.setIcon(new ImageIcon("yellowRel.png"));
		yellowRel.setBounds(25, 25, 30, 30);
		
		redRel.setIcon(new ImageIcon("redRel.png"));
		redRel.setBounds(25, 25, 30, 30);
		
		blueRel.setIcon(new ImageIcon("blueRel.png"));
		blueRel.setBounds(25, 25, 30, 30);
		
		
		topPan.setBounds(bw, bh-40, w*100, 35);
		bottomPan.setBounds(bw, bh + (h*100) + 5, w*100, 35);
		leftPan.setBounds(bw-40, bh, 35, h*100);
		rightPan.setBounds(bw + (w*100) + 5, bh, 35, h*100);
		
		topPan.setBackground(currColor);
		bottomPan.setBackground(currColor);
		leftPan.setBackground(currColor);
		rightPan.setBackground(currColor);
		
		topPan.setLayout(null);
		bottomPan.setLayout(null);
		leftPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 62));
		rightPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 62));
		
		String text = "";
		for (int i = 1; i <= w; i++) {
			text = text + Integer.toString(i) + "        ";
		}
		topNum.setText(text);
		bottomNum.setText(text);
		
		topNum.setBounds(50, 5, w*100, 35);
		bottomNum.setBounds(50, 5, w*100, 35);
		
		topNum.setForeground(Color.darkGray);
		bottomNum.setForeground(Color.darkGray);
		
		topNum.setFont(new Font("Comic Sans", Font.BOLD, 28));
		bottomNum.setFont(new Font("Comic Sans", Font.BOLD, 28));
		
		text = "";
		for (int i = 1; i <= h; i++) {
			JLabel Num1 = new JLabel(Integer.toString(i));
			JLabel Num2 = new JLabel(Integer.toString(i));
			Num1.setForeground(Color.darkGray);
			Num1.setFont(new Font("Comic Sans", Font.BOLD, 28));
			Num2.setForeground(Color.darkGray);
			Num2.setFont(new Font("Comic Sans", Font.BOLD, 28));
			rightPan.add(Num1);
			leftPan.add(Num2);
		}
		
		label.setIcon(new ImageIcon("background2.gif"));
		title.setIcon(currImage);
		welcomeLabel.setIcon(new ImageIcon("welcomepic.jpeg"));
		welcomeLabel.setText("WELCOME TO MOVING MAZE. NUMBER OF RELICS: " + r);
		welcomeLabel.setForeground(Color.white);
		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));
		welcomeLabel.setSize(400, 300);
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		welcomeLabel.setVerticalAlignment(JLabel.TOP);
		welcomeLabel.setHorizontalTextPosition(JLabel.CENTER);
		welcomeLabel.setVerticalTextPosition(JLabel.BOTTOM);
		welcomeLabel.setIconTextGap(50);
		
		tutorial.setLayout(null);
		
		headingLineTut.setForeground(Color.black);
		headingLineTut.setFont(new Font("Comic Sans", Font.BOLD, 50));
		headingLineTut.setBounds(330, 20, 1000, 50);
		
		firstLineTut.setForeground(Color.black);
		firstLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		firstLineTut.setBounds(20, 90, 1000, 14);
		
		secondLineTut.setForeground(Color.black);
		secondLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		secondLineTut.setBounds(20, 128, 1000, 14);
		
		thirdLineTut.setForeground(Color.black);
		thirdLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		thirdLineTut.setBounds(20, 166, 1000, 14);
		
		fourthLineTut.setForeground(Color.black);
		fourthLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		fourthLineTut.setBounds(20, 204, 1000, 14);
		
		fifthLineTut.setForeground(Color.black);
		fifthLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		fifthLineTut.setBounds(20, 242, 1000, 14);
		
		sixthLineTut.setForeground(Color.black);
		sixthLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		sixthLineTut.setBounds(20, 280, 1000, 14);

		seventhLineTut.setForeground(Color.black);
		seventhLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		seventhLineTut.setBounds(20, 318, 1000, 14);
		
		eighthLineTut.setForeground(Color.black);
		eighthLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		eighthLineTut.setBounds(20, 356, 1000, 14);
		
		ninthLineTut.setForeground(Color.black);
		ninthLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		ninthLineTut.setBounds(20, 394, 1000, 14);
		
		tenthLineTut.setForeground(Color.black);
		tenthLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		tenthLineTut.setBounds(20, 432, 1000, 14);
		
		eleventhLineTut.setForeground(Color.black);
		eleventhLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		eleventhLineTut.setBounds(20, 470, 1000, 14);
		
		twelfthLineTut.setForeground(Color.black);
		twelfthLineTut.setFont(new Font("Comic Sans", Font.BOLD, 14));
		twelfthLineTut.setBounds(20, 508, 1000, 14);
		
		
		textDisplayed.setText(whoseTurn + " Rotate and slide the floating tile: ");
		textDisplayed.setForeground(currColor);
		textDisplayed.setFont(new Font("Serif", Font.ITALIC, 21));
		errorMsg.setText("Invalid move: ");
		errorMsg.setForeground(currColor);
		errorMsg.setFont(new Font("Serif", Font.ITALIC, 20));
		relicMsg.setText("Has collected a relic: ");
		relicMsg.setForeground(currColor);
		relicMsg.setFont(new Font("Serif", Font.ITALIC, 20));
		slideOptionsText.setText("Insert using the following options: ");
		slideOptionsText.setForeground(currColor);
		slideOptionsText.setFont(new Font("Serif", Font.ITALIC, 16));
		pathRowLabel.setText("Select a Row to move to: ");
		pathRowLabel.setForeground(currColor);
		pathRowLabel.setFont(new Font("Serif", Font.ITALIC, 16));
		pathColLabel.setText("Select a Column to move to: ");
		pathColLabel.setForeground(currColor);
		pathColLabel.setFont(new Font("Serif", Font.ITALIC, 16));
		
		relicsOutOf.setForeground(Color.white);
		greenScore.setForeground(Color.white);
		yellowScore.setForeground(Color.white);
		redScore.setForeground(Color.white);
		blueScore.setForeground(Color.white);
		relicsOutOf.setText("Relics collected /" + r + ": ");
		greenScore.setText("Green:    " + greenRelicCollected);
		yellowScore.setText("Yellow:   " + yellowRelicCollected);
		redScore.setText("Red:        " + redRelicCollected);
		blueScore.setText("Blue:       " + blueRelicCollected);
		
		welcomePanel.setBounds(400, 100, 1000, 400);
		tutorial.setBounds(400, 100, 1000, 600);
		titlePanel.setBounds(300, 100, 500, 100);
		bigPanel.setBounds(bw, bh, w*100, h*100);
		textBox.setBounds(300, 420, 510, 50);
		errorPanel.setBounds(300, 340, 510, 50);
		relicPanel.setBounds(300, 260, 510, 50);
		scoreBoardPanel.setBounds(30, 300, 150, 120);
		madeBy.setBounds(50, 880, 280, 110);
		floatingPanel.setBounds(500, 500, 110, 110);
		slideOptions.setBounds(20, 650, 300, 40);
		pathRowPanel.setBounds(10, 640, 240, 40);
		pathColPanel.setBounds(10, 740, 240, 40);
		bigPanel.setBackground(Color.white);
		textBox.setBackground(Color.darkGray);
		errorPanel.setBackground(Color.darkGray);
		relicPanel.setBackground(Color.darkGray);
		scoreBoardPanel.setBackground(Color.black);
		madeBy.setBackground(Color.black);
		floatingPanel.setBackground(Color.white);
		titlePanel.setBackground(Color.black);
		welcomePanel.setBackground(Color.lightGray);
		welcomePanel.setOpaque(false);
		tutorial.setBackground(Color.lightGray);
		slideOptions.setBackground(Color.darkGray);
		pathRowPanel.setBackground(Color.darkGray);
		pathColPanel.setBackground(Color.darkGray);
		bigPanel.setLayout(new GridLayout(h, w, 10, 10));
		bigPanel.setBorder(BorderFactory.createLineBorder(Color.white, 10));
		floatingPanel.setBorder(BorderFactory.createLineBorder(Color.white, 10));
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.white, 10, true));
		welcomePanel.setBorder(BorderFactory.createLineBorder(Color.white, 10));
		tutorial.setBorder(BorderFactory.createLineBorder(Color.white, 10));
		textBox.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		errorPanel.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		relicPanel.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		scoreBoardPanel.setBorder(BorderFactory.createLineBorder(Color.white, 5, true));
		slideOptions.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		pathRowPanel.setBorder(BorderFactory.createLineBorder(currColor, 3, true));
		pathColPanel.setBorder(BorderFactory.createLineBorder(currColor, 3, true));
		madeBy.setBorder(BorderFactory.createLineBorder(Color.white, 2, true));
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				
				image = new ImageIcon(tracker[i][j].substring(0, 4)+"real.jpg");
				JLabel tile = new JLabel(image);
				tile.setLayout(null);
				if (greenPos[0] == i&&greenPos[1] == j) {
					tile.add(greenAd);
				}
				if (yellowPos[0] == i&&yellowPos[1] == j) {
					tile.add(yellowAd);
				}
				if (redPos[0] == i&&redPos[1] == j) {
					tile.add(redAd);
				}
				if (bluePos[0] == i&&bluePos[1] == j) {
					tile.add(blueAd);
				}
				if (greenRelicPos[0] == i&&greenRelicPos[1] == j) {
					tile.add(greenRel);
				}
				if (yellowRelicPos[0] == i&&yellowRelicPos[1] == j) {
					tile.add(yellowRel);
				}
				if (redRelicPos[0] == i&&redRelicPos[1] == j) {
					tile.add(redRel);
				}
				if (blueRelicPos[0] == i&&blueRelicPos[1] == j) {
					tile.add(blueRel);
				}
				bigPanel.add(tile);
			}
		}
		JLabel floater = new JLabel(new ImageIcon(floatingTileTracker.substring(0, 4)+"real.jpg"));
		if (greenRelicPos[0] == 100&&greenRelicPos[1] == 100) {
			floater.add(greenRel);
		}
		if (yellowRelicPos[0] == 100&&yellowRelicPos[1] == 100) {
			floater.add(yellowRel);
		}
		if (redRelicPos[0] == 100&&redRelicPos[1] == 100) {
			floater.add(redRel);
		}
		if (blueRelicPos[0] == 100&&blueRelicPos[1] == 100) {
			floater.add(blueRel);
		}
		floatingPanel.add(floater);
		
		file.setMnemonic(KeyEvent.VK_F);
		help.setMnemonic(KeyEvent.VK_H);
		score.setMnemonic(KeyEvent.VK_S);
		quit.setMnemonic(KeyEvent.VK_Q);
		restart.setMnemonic(KeyEvent.VK_R);
		backStory.setMnemonic(KeyEvent.VK_B);
		instructions.setMnemonic(KeyEvent.VK_I);
		scoreBoard.setMnemonic(KeyEvent.VK_S);
		
		quit.setIcon(new ImageIcon("quit.png"));
		restart.setIcon(new ImageIcon("restart.png"));
		backStory.setIcon(new ImageIcon("backStory.jpg"));
		instructions.setIcon(new ImageIcon("instructions.jpg"));
		scoreBoard.setIcon(new ImageIcon("scoreboard.jpg"));
		
		quit.addActionListener(this);
		restart.addActionListener(this);
		backStory.addActionListener(this);
		instructions.addActionListener(this);
		scoreBoard.addActionListener(this);
		
		titlePanel.add(title);
		welcomePanel.add(welcomeLabel);
		tutorial.add(headingLineTut);
		tutorial.add(firstLineTut);
		tutorial.add(secondLineTut);
		tutorial.add(thirdLineTut);
		tutorial.add(fourthLineTut);
		tutorial.add(fifthLineTut);
		tutorial.add(sixthLineTut);
		tutorial.add(seventhLineTut);
		tutorial.add(eighthLineTut);
		tutorial.add(ninthLineTut);
		tutorial.add(tenthLineTut);
		tutorial.add(eleventhLineTut);
		tutorial.add(twelfthLineTut);
		topPan.add(topNum);
		bottomPan.add(bottomNum);
		leftPan.add(leftNum);
		rightPan.add(rightNum);
		textBox.add(textDisplayed);
		errorPanel.add(errorMsg);
		relicPanel.add(relicMsg);
		scoreBoardPanel.add(relicsOutOf);
		scoreBoardPanel.add(greenScore);
		scoreBoardPanel.add(yellowScore);
		scoreBoardPanel.add(redScore);
		scoreBoardPanel.add(blueScore);
		madeBy.add(name);
		slideOptions.add(slideOptionsText);
		pathRowPanel.add(pathRowLabel);
		pathColPanel.add(pathColLabel);
		this.setTitle("MOVING MAZE");
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(1850, 1050);
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setVerticalAlignment(JLabel.TOP);
		label.setSize(1850, 1050);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		label.add(pickCol);
//		label.add(pickRow);
		label.add(titlePanel);
		label.add(bigPanel);
		label.add(floatingPanel);
		label.add(welcomePanel);
		label.add(tutorial);
		label.add(cont1);
		label.add(cont2);
		label.add(rotateleft);
		label.add(rotateright);
		label.add(bottomPan);
		label.add(topPan);
		label.add(rightPan);
		label.add(leftPan);
		label.add(textBox);
		label.add(errorPanel);
		label.add(relicPanel);
		label.add(scoreBoardPanel);
		label.add(madeBy);
		label.add(slideOptions);
		label.add(pathRowPanel);
		label.add(pathColPanel);
		label.add(north);
		label.add(south);
		label.add(east);
		label.add(west);
		label.add(two);
		label.add(four);
		label.add(six);
		label.add(eight);
		label.add(rowOne);
		label.add(rowTwo);
		label.add(rowThree);
		label.add(rowFour);
		label.add(rowFive);
		label.add(rowSix);
		label.add(rowSeven);
		label.add(rowEight);
		label.add(rowNine);
		label.add(colOne);
		label.add(colTwo);
		label.add(colThree);
		label.add(colFour);
		label.add(colFive);
		label.add(colSix);
		label.add(colSeven);
		label.add(colEight);
		label.add(colNine);
		label.add(done);
		label.add(pathFinder);
		
		bigPanel.setVisible(false);
		floatingPanel.setVisible(false);
		titlePanel.setVisible(false);
		tutorial.setVisible(false);
		topPan.setVisible(false);
		bottomPan.setVisible(false);
		leftPan.setVisible(false);
		rightPan.setVisible(false);
		rotateleft.setVisible(false);
		rotateright.setVisible(false);
		textBox.setVisible(false);
		errorPanel.setVisible(false);
		relicPanel.setVisible(false);
		scoreBoardPanel.setVisible(false);
		slideOptions.setVisible(false);
		pathRowPanel.setVisible(false);
		pathColPanel.setVisible(false);
		north.setVisible(false);
		south.setVisible(false);
		east.setVisible(false);
		west.setVisible(false);
		two.setVisible(false);
		four.setVisible(false);
		six.setVisible(false);
		eight.setVisible(false);
		rowOne.setVisible(false);
		rowTwo.setVisible(false);
		rowThree.setVisible(false);
		rowFour.setVisible(false);
		rowFive.setVisible(false);
		rowSix.setVisible(false);
		rowSeven.setVisible(false);
		rowEight.setVisible(false);
		rowNine.setVisible(false);
		colOne.setVisible(false);
		colTwo.setVisible(false);
		colThree.setVisible(false);
		colFour.setVisible(false);
		colFive.setVisible(false);
		colSix.setVisible(false);
		colSeven.setVisible(false);
		colEight.setVisible(false);
		colNine.setVisible(false);
		done.setVisible(false);
		pathFinder.setVisible(false);
		cont2.setVisible(false);
		madeBy.setVisible(false);
//		pickRow.setVisible(false);
//		pickCol.setVisible(false);
		
		file.add(quit);
		file.add(restart);
		help.add(backStory);
		help.add(instructions);
		score.add(scoreBoard);
		menuBar.add(file);
		menuBar.add(help);
		menuBar.add(score);
		this.setJMenuBar(menuBar);
		this.add(label);
		this.addKeyListener(this);
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == quit) {
			
			String[] responses = {"Absolutely", "Na let's keep playing"};
			int ans = JOptionPane.showOptionDialog(null, "Are you sure you want to quit? All progress will be lost!", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("quit.png"), responses, 0);
			if (ans == 0) {
				this.dispose();
				System.exit(0);
			}
			
		}
		
		if (e.getSource() == restart) {
			
			String[] responses = {"Absolutely", "Na let's keep playing"};
			int ans = JOptionPane.showOptionDialog(null, "Are you sure you want to restart? All progress will be lost!", "Restart?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("restart.png"), responses, 0);
			if (ans == 0) {
				this.dispose();
				GUI frame = new GUI(w, h, r, startingFloatingTileTracker, startingTracker);
			}
			
		}
		
		if (e.getSource() == backStory) {
			
			label.setIcon(new ImageIcon("background2.gif"));
			headingLineTut.setText("BACKSTORY");
			firstLineTut.setText("ONCE UPON A TIME, 4 COUNTRIES FROM EARTH SENT OUT A TEAM TO SPACE IN ONE BIG SPACESHIP, TO DISCOVER A NEW");
			secondLineTut.setText("SOURCE OF ENERGY. THIS MISSION WAS TOP SECRET AND COSTED BILLIONS. ALL 4 COUNTRIES HAD TO JOIN TO MAKE THIS");	
			thirdLineTut.setText("POSSIBLE. THEY CAPTURED SOME SAMPLES OF THE NEW ENERGY SOURCE. THE MISSION WAS GOING WELL, UNTIL SOME");		
			fourthLineTut.setText("COUNTRIES GOT GREEDY AND WANTED THE ENERGY SOURCE FOR THEMSELVES. DEAD BODIES WERE FOUND ON BOARD. THIS");	
			fifthLineTut.setText("LED TO THE POINT WHERE THERE WAS ONLY ONE SURVIVOR LEFT PER COUNTRY. WHO OR HOW MANY MURDERERS THERE");	
			sixthLineTut.setText("ARE, IS UNKNOWN. TO MAKE IT WORSE, THE SAMPLES OF ENERGY BECAME UNSTABLE AND WILL CAUSE THE SPACESHIP TO");
			seventhLineTut.setText("EXPLODE VERY SOON. YOUR MISSION, AS THE SURVIVOR OF YOUR TEAM, IS TO BE AN HONORABLE TEAM MEMBER AND");
			eighthLineTut.setText("COLLECT THE DEAD BODIES OF YOUR FELLOW FALLEN SOLDIERS, AND USE THE ESCAPE POD TO TRAVEL BACK HOME. THERE");
			ninthLineTut.setText("IS ONLY ONE ESCAPE POD, AND IT IS LOCATED WHEREVER YOU STARTED. ONCE THE ESCAPE POD LEAVES, IT CAN DESTRUCT");
			tenthLineTut.setText("THE REST OF THE SPACESHIP WITH EVERYONE ON IT. THE MISSION IS NOW TO RETRIEVE ALL YOUR TEAM'S FALLEN SOLDIERS");
			eleventhLineTut.setText("AND MAKE IT OUT, WHILE PREVENTING THE OTHER TEAMS TO REACH THEIR FALLEN SOLDIERS. ONE CAN DO THIS USING");
			twelfthLineTut.setText("STRATEGIC TILE MOVEMENTS. GOODLUCK SOLDIER!");
			cont1.setVisible(false);
			welcomePanel.setVisible(false);
			bigPanel.setVisible(false);
			floatingPanel.setVisible(false);
			titlePanel.setVisible(false);
			topPan.setVisible(false);
			bottomPan.setVisible(false);
			leftPan.setVisible(false);
			rightPan.setVisible(false);
			rotateleft.setVisible(false);
			rotateright.setVisible(false);
			textBox.setVisible(false);
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			slideOptions.setVisible(false);
			pathRowPanel.setVisible(false);
			pathColPanel.setVisible(false);
			scoreBoardPanel.setVisible(false);
			north.setVisible(false);
			south.setVisible(false);
			east.setVisible(false);
			west.setVisible(false);
			two.setVisible(false);
			four.setVisible(false);
			six.setVisible(false);
			eight.setVisible(false);
			done.setVisible(false);
			pathFinder.setVisible(false);
			rowOne.setVisible(false);
			rowTwo.setVisible(false);
			rowThree.setVisible(false);
			rowFour.setVisible(false);
			rowFive.setVisible(false);
			rowSix.setVisible(false);
			rowSeven.setVisible(false);
			rowEight.setVisible(false);
			rowNine.setVisible(false);
			colOne.setVisible(false);
			colTwo.setVisible(false);
			colThree.setVisible(false);
			colFour.setVisible(false);
			colFive.setVisible(false);
			colSix.setVisible(false);
			colSeven.setVisible(false);
			colEight.setVisible(false);
			colNine.setVisible(false);
			madeBy.setVisible(false);
//			pickRow.setVisible(false);
//			pickCol.setVisible(false);
			cont2.setVisible(true);
			tutorial.setVisible(true);
			
		}
		if (e.getSource() == instructions) {
			
			label.setIcon(new ImageIcon("background2.gif"));
			headingLineTut.setText("INSTRUCTIONS");
			firstLineTut.setText("-EACH PLAYER GETS A TURN TO PLAY, IN THIS ORDER: GREEN, YELLOW, RED, BLUE.");
			secondLineTut.setText("-EACH TURN CONSISTS OF A SLIDE & A MOVE. IT IS COMPULSORY FOR EACH PLAYER TO SLIDE, BUT OPTIONAL TO MOVE.");	
			thirdLineTut.setText("-IN ORDER TO SLIDE, ONE SHOULD CHOOSE A SIDE TO INSERT THE FLOATING TILE IN, AND THEN A ROW OR COLUMN NUMBER.");		
			fourthLineTut.setText("-NOTE THAT ONE CAN ONLY SLIDE INTO AN EVEN NUMBERED ROW/COLUMN, AND THE ENTIRE ROW/COLUMN WILL MOVE.");	
			fifthLineTut.setText("-AFTER A SLIDING MOVE, THE PLAYER GETS A MOVING MOVE, WHERE HE GETS TO MOVE AROUND IN THE SPACESHIP.");	
			sixthLineTut.setText("-ONE CAN MOVE USING ARROW OR WASD KEYS. NOTE YOU CAN ONLY MOVE ONTO A NEW TILE IF THERE IS A PATHWAY.");
			seventhLineTut.setText("-ONE CANNOT MOVE OFF THE BOARD, BUT CAN MOVE AS MUCH AS HE LIKES. CLICK THE \"DONE\" BUTTON WHEN FINISHED");
			eighthLineTut.setText("-THE NEXT PLAYER'S TURN IS THEN INVOKED. IT IS ALSO AUTOMATICALLY INVOKED WHEN ONE COLLECTS A RELIC.");
			ninthLineTut.setText("-NOTE: WHEN A PLAYER IS SLID OFF THE BOARD, HE WRAPS AROUND TO THE OTHER SIDE.");
			tenthLineTut.setText("-NOTE: WHEN A RELIC IS SLID OFF THE BOARD, IT GETS SLID ONTO THE NEW FLOATING TILE.");
			eleventhLineTut.setText("-THE FIRST PLAYER TO COLLECT ALL THEIR RELICS AND GET BACK TO THEIR STARTING POSITION, WINS.");
			twelfthLineTut.setText("-QUITTING IS UNDER THE FILE MENU. THE SCOREBOARD IS UNDER THE SCORE MENU. GOODLUCK SOLDIER!");
			cont1.setVisible(false);
			welcomePanel.setVisible(false);
			bigPanel.setVisible(false);
			floatingPanel.setVisible(false);
			titlePanel.setVisible(false);
			topPan.setVisible(false);
			bottomPan.setVisible(false);
			leftPan.setVisible(false);
			rightPan.setVisible(false);
			rotateleft.setVisible(false);
			rotateright.setVisible(false);
			textBox.setVisible(false);
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			slideOptions.setVisible(false);
			pathRowPanel.setVisible(false);
			pathColPanel.setVisible(false);
			scoreBoardPanel.setVisible(false);
			north.setVisible(false);
			south.setVisible(false);
			east.setVisible(false);
			west.setVisible(false);
			two.setVisible(false);
			four.setVisible(false);
			six.setVisible(false);
			eight.setVisible(false);
			done.setVisible(false);
			pathFinder.setVisible(false);
			rowOne.setVisible(false);
			rowTwo.setVisible(false);
			rowThree.setVisible(false);
			rowFour.setVisible(false);
			rowFive.setVisible(false);
			rowSix.setVisible(false);
			rowSeven.setVisible(false);
			rowEight.setVisible(false);
			rowNine.setVisible(false);
			colOne.setVisible(false);
			colTwo.setVisible(false);
			colThree.setVisible(false);
			colFour.setVisible(false);
			colFive.setVisible(false);
			colSix.setVisible(false);
			colSeven.setVisible(false);
			colEight.setVisible(false);
			colNine.setVisible(false);
			madeBy.setVisible(false);
//			pickRow.setVisible(false);
//			pickCol.setVisible(false);
			cont2.setVisible(true);
			tutorial.setVisible(true);
			
		}
		
		if (e.getSource() == scoreBoard) {
			
			label.setIcon(new ImageIcon("background2.gif"));
			headingLineTut.setText("SCORE BOARD");
			firstLineTut.setText("");
			secondLineTut.setText("RELICS COLLECTED OUT OF " + r + " :");	
			thirdLineTut.setText("");		
			fourthLineTut.setText("GREEN: " + greenRelicCollected);	
			fifthLineTut.setText("");	
			sixthLineTut.setText("YELLOW: " + yellowRelicCollected);
			seventhLineTut.setText("");
			eighthLineTut.setText("RED: " + redRelicCollected);
			ninthLineTut.setText("");
			tenthLineTut.setText("BLUE: " + blueRelicCollected);
			eleventhLineTut.setText("");
			twelfthLineTut.setText("");
			cont1.setVisible(false);
			welcomePanel.setVisible(false);
			bigPanel.setVisible(false);
			floatingPanel.setVisible(false);
			titlePanel.setVisible(false);
			topPan.setVisible(false);
			bottomPan.setVisible(false);
			leftPan.setVisible(false);
			rightPan.setVisible(false);
			rotateleft.setVisible(false);
			rotateright.setVisible(false);
			textBox.setVisible(false);
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			slideOptions.setVisible(false);
			pathRowPanel.setVisible(false);
			pathColPanel.setVisible(false);
			scoreBoardPanel.setVisible(false);
			north.setVisible(false);
			south.setVisible(false);
			east.setVisible(false);
			west.setVisible(false);
			two.setVisible(false);
			four.setVisible(false);
			six.setVisible(false);
			eight.setVisible(false);
			done.setVisible(false);
			pathFinder.setVisible(false);
			rowOne.setVisible(false);
			rowTwo.setVisible(false);
			rowThree.setVisible(false);
			rowFour.setVisible(false);
			rowFive.setVisible(false);
			rowSix.setVisible(false);
			rowSeven.setVisible(false);
			rowEight.setVisible(false);
			rowNine.setVisible(false);
			colOne.setVisible(false);
			colTwo.setVisible(false);
			colThree.setVisible(false);
			colFour.setVisible(false);
			colFive.setVisible(false);
			colSix.setVisible(false);
			colSeven.setVisible(false);
			colEight.setVisible(false);
			colNine.setVisible(false);
			madeBy.setVisible(false);
//			pickRow.setVisible(false);
//			pickCol.setVisible(false);
			cont2.setVisible(true);
			tutorial.setVisible(true);
			
		}
		
		if (e.getSource() == cont2) {
			
			label.setIcon(new ImageIcon("background.jpg"));
			if (moveMade) {
				
				rotateleft.setVisible(true);
				rotateright.setVisible(true);
				north.setVisible(true);
				south.setVisible(true);
				east.setVisible(true);
				west.setVisible(true);
				slideOptions.setVisible(true);
				if (pickedRowCol) {
					two.setVisible(true);
					four.setVisible(true);
					six.setVisible(true);
					eight.setVisible(true);
				}
				
			}
			else {
				done.setVisible(true);
				pathFinder.setVisible(true);
			}
			
			floatingPanel.setVisible(true);
			bigPanel.setVisible(true);
			titlePanel.setVisible(true);
			cont2.setVisible(false);
			tutorial.setVisible(false);
			topPan.setVisible(true);
			bottomPan.setVisible(true);
			leftPan.setVisible(true);
			rightPan.setVisible(true);
			textBox.setVisible(true);
			scoreBoardPanel.setVisible(true);
			madeBy.setVisible(true);
			
		}
		if (e.getSource() == cont1) {
			
			label.setIcon(new ImageIcon("background.jpg"));
			bigPanel.setVisible(true);
			floatingPanel.setVisible(true);
			titlePanel.setVisible(true);
			cont2.setVisible(false);
			tutorial.setVisible(false);
			topPan.setVisible(true);
			bottomPan.setVisible(true);
			leftPan.setVisible(true);
			rightPan.setVisible(true);
			rotateleft.setVisible(true);
			rotateright.setVisible(true);
			textBox.setVisible(true);
			slideOptions.setVisible(true);
			scoreBoardPanel.setVisible(true);
			north.setVisible(true);
			south.setVisible(true);
			east.setVisible(true);
			west.setVisible(true);
			cont1.setVisible(false);
			welcomePanel.setVisible(false);
			madeBy.setVisible(true);
			
		}
		
		if (e.getSource() == rowOne) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 1;
			rowTwo.setEnabled(false);
			rowThree.setEnabled(false);
			rowFour.setEnabled(false);
			rowFive.setEnabled(false);
			rowSix.setEnabled(false);
			rowSeven.setEnabled(false);
			rowEight.setEnabled(false);
			rowNine.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == rowTwo) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 2;
			rowOne.setEnabled(false);
			rowThree.setEnabled(false);
			rowFour.setEnabled(false);
			rowFive.setEnabled(false);
			rowSix.setEnabled(false);
			rowSeven.setEnabled(false);
			rowEight.setEnabled(false);
			rowNine.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == rowThree) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 3;
			rowTwo.setEnabled(false);
			rowOne.setEnabled(false);
			rowFour.setEnabled(false);
			rowFive.setEnabled(false);
			rowSix.setEnabled(false);
			rowSeven.setEnabled(false);
			rowEight.setEnabled(false);
			rowNine.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == rowFour) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 4;
			rowTwo.setEnabled(false);
			rowThree.setEnabled(false);
			rowOne.setEnabled(false);
			rowFive.setEnabled(false);
			rowSix.setEnabled(false);
			rowSeven.setEnabled(false);
			rowEight.setEnabled(false);
			rowNine.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == rowFive) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 5;
			rowTwo.setEnabled(false);
			rowThree.setEnabled(false);
			rowFour.setEnabled(false);
			rowOne.setEnabled(false);
			rowSix.setEnabled(false);
			rowSeven.setEnabled(false);
			rowEight.setEnabled(false);
			rowNine.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == rowSix) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 6;
			rowTwo.setEnabled(false);
			rowThree.setEnabled(false);
			rowFour.setEnabled(false);
			rowFive.setEnabled(false);
			rowOne.setEnabled(false);
			rowSeven.setEnabled(false);
			rowEight.setEnabled(false);
			rowNine.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == rowSeven) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 7;
			rowTwo.setEnabled(false);
			rowThree.setEnabled(false);
			rowFour.setEnabled(false);
			rowFive.setEnabled(false);
			rowSix.setEnabled(false);
			rowOne.setEnabled(false);
			rowEight.setEnabled(false);
			rowNine.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == rowEight) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 8;
			rowTwo.setEnabled(false);
			rowThree.setEnabled(false);
			rowFour.setEnabled(false);
			rowFive.setEnabled(false);
			rowSix.setEnabled(false);
			rowSeven.setEnabled(false);
			rowOne.setEnabled(false);
			rowNine.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == rowNine) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathRow = 9;
			rowTwo.setEnabled(false);
			rowThree.setEnabled(false);
			rowFour.setEnabled(false);
			rowFive.setEnabled(false);
			rowSix.setEnabled(false);
			rowSeven.setEnabled(false);
			rowEight.setEnabled(false);
			rowOne.setEnabled(false);
			pathRowChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathRow > h) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colOne) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 1;
			colTwo.setEnabled(false);
			colThree.setEnabled(false);
			colFour.setEnabled(false);
			colFive.setEnabled(false);
			colSix.setEnabled(false);
			colSeven.setEnabled(false);
			colEight.setEnabled(false);
			colNine.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colTwo) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 2;
			colOne.setEnabled(false);
			colThree.setEnabled(false);
			colFour.setEnabled(false);
			colFive.setEnabled(false);
			colSix.setEnabled(false);
			colSeven.setEnabled(false);
			colEight.setEnabled(false);
			colNine.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colThree) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 3;
			colTwo.setEnabled(false);
			colOne.setEnabled(false);
			colFour.setEnabled(false);
			colFive.setEnabled(false);
			colSix.setEnabled(false);
			colSeven.setEnabled(false);
			colEight.setEnabled(false);
			colNine.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colFour) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 4;
			colTwo.setEnabled(false);
			colThree.setEnabled(false);
			colOne.setEnabled(false);
			colFive.setEnabled(false);
			colSix.setEnabled(false);
			colSeven.setEnabled(false);
			colEight.setEnabled(false);
			colNine.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colFive) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 5;
			colTwo.setEnabled(false);
			colThree.setEnabled(false);
			colFour.setEnabled(false);
			colOne.setEnabled(false);
			colSix.setEnabled(false);
			colSeven.setEnabled(false);
			colEight.setEnabled(false);
			colNine.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colSix) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 6;
			colTwo.setEnabled(false);
			colThree.setEnabled(false);
			colFour.setEnabled(false);
			colFive.setEnabled(false);
			colOne.setEnabled(false);
			colSeven.setEnabled(false);
			colEight.setEnabled(false);
			colNine.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colSeven) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 7;
			colTwo.setEnabled(false);
			colThree.setEnabled(false);
			colFour.setEnabled(false);
			colFive.setEnabled(false);
			colSix.setEnabled(false);
			colOne.setEnabled(false);
			colEight.setEnabled(false);
			colNine.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colEight) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 8;
			colTwo.setEnabled(false);
			colThree.setEnabled(false);
			colFour.setEnabled(false);
			colFive.setEnabled(false);
			colSix.setEnabled(false);
			colSeven.setEnabled(false);
			colOne.setEnabled(false);
			colNine.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
		if (e.getSource() == colNine) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			bigPanel.setVisible(false);
			pathCol = 9;
			colTwo.setEnabled(false);
			colThree.setEnabled(false);
			colFour.setEnabled(false);
			colFive.setEnabled(false);
			colSix.setEnabled(false);
			colSeven.setEnabled(false);
			colEight.setEnabled(false);
			colOne.setEnabled(false);
			pathColChosen = true;
			if (whoseTurn.equals("[Green]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					greenPos[0] = pathRow - 1;
					greenPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Yellow]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					yellowPos[0] = pathRow - 1;
					yellowPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Red]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					redPos[0] = pathRow - 1;
					redPos[1] = pathCol - 1;
				}
			}
			if (whoseTurn.equals("[Blue]")) {
				if (pathCol > w) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
					errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
					errorPanel.setVisible(true);
				}
				else {
					bluePos[0] = pathRow - 1;
					bluePos[1] = pathCol - 1;
				}
			}
			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
				if (greenRelicCollected < r) {
					relicMsg.setText("Green has collected a relic.");
					relicPanel.setVisible(true);
	    			greenRelicCollected++;
	    		}
	    		if (greenRelicCollected == r) {
	    			errorMsg.setText("Green has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
				if (yellowRelicCollected < r) {
					relicMsg.setText("Yellow has collected a relic.");
					relicPanel.setVisible(true);
	    			yellowRelicCollected++;
	    		}
	    		if (yellowRelicCollected == r) {
	    			errorMsg.setText("Yellow has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
				if (redRelicCollected < r) {
					relicMsg.setText("Red has collected a relic.");
					relicPanel.setVisible(true);
	    			redRelicCollected++;
	    		}
	    		if (redRelicCollected == r) {
	    			errorMsg.setText("Red has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
				if (blueRelicCollected < r) {
					relicMsg.setText("Blue has collected a relic.");
					relicPanel.setVisible(true);
	    			blueRelicCollected++;
	    		}
	    		if (blueRelicCollected == r) {
	    			errorMsg.setText("Blue has all their relics.");
					errorPanel.setVisible(true);
	    		}
	    		relicCollected = true;
	    		theDoneButton();
			}
			updateRelics();
			updateBoard();
			bigPanel.setVisible(true);
			if (pathRowChosen&&pathColChosen) {
				rowOne.setVisible(false);
				rowTwo.setVisible(false);
				rowThree.setVisible(false);
				rowFour.setVisible(false);
				rowFive.setVisible(false);
				rowSix.setVisible(false);
				rowSeven.setVisible(false);
				rowEight.setVisible(false);
				rowNine.setVisible(false);
				colOne.setVisible(false);
				colTwo.setVisible(false);
				colThree.setVisible(false);
				colFour.setVisible(false);
				colFive.setVisible(false);
				colSix.setVisible(false);
				colSeven.setVisible(false);
				colEight.setVisible(false);
				colNine.setVisible(false);
				pathRowPanel.setVisible(false);
				pathColPanel.setVisible(false);
				if (relicCollected) {
					pathFinder.setVisible(false);
					done.setVisible(false);
				}
				else {
					pathFinder.setVisible(true);
					done.setVisible(true);
				}
				relicCollected = false;
				pathRowChosen = false;
				pathColChosen = false;
				
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		
//		if (e.getSource() == pickRow) {
//			
//			errorPanel.setVisible(false);
//			bigPanel.setVisible(false);
//			if (pickRow.getSelectedIndex() != 0) {
//				pathRow = Integer.parseInt((String) pickRow.getSelectedItem());
//				pickRow.setEnabled(false);
//				if (whoseTurn.equals("[Green]")) {
//					if (pathRow > h) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else {
//						greenPos[0] = pathRow - 1;
//						greenPos[1] = pathCol - 1;
//					}
//				}
//				if (whoseTurn.equals("[Yellow]")) {
//					if (pathRow > h) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else {
//						yellowPos[0] = pathRow - 1;
//						yellowPos[1] = pathCol - 1;
//					}
//				}
//				if (whoseTurn.equals("[Red]")) {
//					if (pathRow > h) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else {
//						redPos[0] = pathRow - 1;
//						redPos[1] = pathCol - 1;
//					}
//				}
//				if (whoseTurn.equals("[Blue]")) {
//					if (pathRow > h) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else {
//						bluePos[0] = pathRow - 1;
//						bluePos[1] = pathCol - 1;
//					}
//				}
//			}
//			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
//				if (greenRelicCollected < r) {
//					relicMsg.setText("Green has collected a relic.");
//					relicPanel.setVisible(true);
//	    			greenRelicCollected++;
//	    		}
//	    		if (greenRelicCollected == r) {
//	    			errorMsg.setText("Green has all their relics.");
//					errorPanel.setVisible(true);
//	    		}
//	    		theDoneButton();
//			}
//			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
//				if (yellowRelicCollected < r) {
//					relicMsg.setText("Yellow has collected a relic.");
//					relicPanel.setVisible(true);
//	    			yellowRelicCollected++;
//	    		}
//	    		if (yellowRelicCollected == r) {
//	    			errorMsg.setText("Yellow has all their relics.");
//					errorPanel.setVisible(true);
//	    		}
//	    		theDoneButton();
//			}
//			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
//				if (redRelicCollected < r) {
//					relicMsg.setText("Red has collected a relic.");
//					relicPanel.setVisible(true);
//	    			redRelicCollected++;
//	    		}
//	    		if (redRelicCollected == r) {
//	    			errorMsg.setText("Red has all their relics.");
//					errorPanel.setVisible(true);
//	    		}
//	    		theDoneButton();
//			}
//			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
//				if (blueRelicCollected < r) {
//					relicMsg.setText("Blue has collected a relic.");
//					relicPanel.setVisible(true);
//	    			blueRelicCollected++;
//	    		}
//	    		if (blueRelicCollected == r) {
//	    			errorMsg.setText("Blue has all their relics.");
//					errorPanel.setVisible(true);
//	    		}
//	    		theDoneButton();
//			}
//			updateRelics();
//			updateBoard();
//			bigPanel.setVisible(true);
//			if (!pickRow.isEnabled()&&!pickCol.isEnabled()) {
//				pickRow.setVisible(false);
//				pickCol.setVisible(false);
//				pathFinder.setVisible(true);
//				done.setVisible(true);
//				
//				greenScore.setText("Green:    " + greenRelicCollected);
//				yellowScore.setText("Yellow:   " + yellowRelicCollected);
//				redScore.setText("Red:        " + redRelicCollected);
//				blueScore.setText("Blue:       " + blueRelicCollected);
//			}
//			
//		}
//		
//		if (e.getSource() == pickCol) {
//			
//			errorPanel.setVisible(false);
//			bigPanel.setVisible(false);
//			if (pickCol.getSelectedIndex() != 0) {
//				pathCol = Integer.parseInt((String) pickCol.getSelectedItem());
//				pickCol.setEnabled(false);
//				if (whoseTurn.equals("[Green]")) {
//					if (pathCol > w) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else if (!pathFinder1(greenPos[0], greenPos[1], pathRow - 1, pathCol - 1)) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else {
//						greenPos[0] = pathRow - 1;
//						greenPos[1] = pathCol - 1;
//					}
//				}
//				if (whoseTurn.equals("[Yellow]")) {
//					if (pathCol > w) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else if (!pathFinder1(yellowPos[0], yellowPos[1], pathRow - 1, pathCol - 1)) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else {
//						yellowPos[0] = pathRow - 1;
//						yellowPos[1] = pathCol - 1;
//					}
//				}
//				if (whoseTurn.equals("[Red]")) {
//					if (pathCol > w) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else if (!pathFinder1(redPos[0], redPos[1], pathRow - 1, pathCol - 1)) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else {
//						redPos[0] = pathRow - 1;
//						redPos[1] = pathCol - 1;
//					}
//				}
//				if (whoseTurn.equals("[Blue]")) {
//					if (pathCol > w) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else if (!pathFinder1(bluePos[0], bluePos[1], pathRow - 1, pathCol - 1)) {
//						errorMsg.setText("Cannot move to " + String.valueOf(pathCol) + "," + String.valueOf(pathRow) + ": no path.");
//						errorPanel.setVisible(true);
//					}
//					else {
//						bluePos[0] = pathRow - 1;
//						bluePos[1] = pathCol - 1;
//					}
//				}
//			}
//			if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
//				if (greenRelicCollected < r) {
//					relicMsg.setText("Green has collected a relic.");
//					relicPanel.setVisible(true);
//	    			greenRelicCollected++;
//	    		}
//	    		if (greenRelicCollected == r) {
//	    			errorMsg.setText("Green has all their relics.");
//					errorPanel.setVisible(true);
//	    		}
//	    		theDoneButton();
//			}
//			if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
//				if (yellowRelicCollected < r) {
//					relicMsg.setText("Yellow has collected a relic.");
//					relicPanel.setVisible(true);
//	    			yellowRelicCollected++;
//	    		}
//	    		if (yellowRelicCollected == r) {
//	    			errorMsg.setText("Yellow has all their relics.");
//					errorPanel.setVisible(true);
//	    		}
//	    		theDoneButton();
//			}
//			if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
//				if (redRelicCollected < r) {
//					relicMsg.setText("Red has collected a relic.");
//					relicPanel.setVisible(true);
//	    			redRelicCollected++;
//	    		}
//	    		if (redRelicCollected == r) {
//	    			errorMsg.setText("Red has all their relics.");
//					errorPanel.setVisible(true);
//	    		}
//	    		theDoneButton();
//			}
//			if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
//				if (blueRelicCollected < r) {
//					relicMsg.setText("Blue has collected a relic.");
//					relicPanel.setVisible(true);
//	    			blueRelicCollected++;
//	    		}
//	    		if (blueRelicCollected == r) {
//	    			errorMsg.setText("Blue has all their relics.");
//					errorPanel.setVisible(true);
//	    		}
//	    		theDoneButton();
//			}
//			updateRelics();
//			updateBoard();
//			bigPanel.setVisible(true);
//			if (!pickRow.isEnabled()&&!pickCol.isEnabled()) {
//				pickRow.setVisible(false);
//				pickCol.setVisible(false);
//				pathFinder.setVisible(true);
//				done.setVisible(true);
//				
//				greenScore.setText("Green:    " + greenRelicCollected);
//				yellowScore.setText("Yellow:   " + yellowRelicCollected);
//				redScore.setText("Red:        " + redRelicCollected);
//				blueScore.setText("Blue:       " + blueRelicCollected);
//			}
//			
//		}
		
		
		if (e.getSource() == north||e.getSource() == south) {
			
			pickedRowCol = true;
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			if (e.getSource() == north) {
				nextSlide = "n";
			}
			else if (e.getSource() == south) {
				nextSlide = "s";
			}
			
			if (w == 3) {
				four.setEnabled(false);
				six.setEnabled(false);
				eight.setEnabled(false);
			}
			if (w == 5) {
				four.setEnabled(true);
				six.setEnabled(false);
				eight.setEnabled(false);
			}
			if (w == 7) {
				four.setEnabled(true);
				six.setEnabled(true);
				eight.setEnabled(false);
			}
			
			two.setText("2nd COLUMN");
			four.setText("4th COLUMN");
			six.setText("6th COLUMN");
			eight.setText("8th COLUMN");
			two.setVisible(true);
			four.setVisible(true);
			six.setVisible(true);
			eight.setVisible(true);
			
			rotateright.setEnabled(false);
			rotateleft.setEnabled(false);
			north.setEnabled(false);
			south.setEnabled(false);
			east.setEnabled(false);
			west.setEnabled(false);
			
		}
		
		if (e.getSource() == rotateright) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			floatingPanel.setVisible(false);
			String first = String.valueOf(floatingTileTracker.charAt(0));
	    	String second = String.valueOf(floatingTileTracker.charAt(1));
	    	String third = String.valueOf(floatingTileTracker.charAt(2));
	    	String fourth = String.valueOf(floatingTileTracker.charAt(3));
	    	String fifth = String.valueOf(floatingTileTracker.charAt(4));
	    	String sixth = String.valueOf(floatingTileTracker.charAt(5));
	    	
	    	floatingTileTracker = fourth + first + second + third + fifth + sixth;
	    	updateRelics();
	    	updateBoard();
	    	floatingPanel.setVisible(true);
	    	
		}
		
		if (e.getSource() == rotateleft) {
			
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			floatingPanel.setVisible(false);
			String first = String.valueOf(floatingTileTracker.charAt(0));
	    	String second = String.valueOf(floatingTileTracker.charAt(1));
	    	String third = String.valueOf(floatingTileTracker.charAt(2));
	    	String fourth = String.valueOf(floatingTileTracker.charAt(3));
	    	String fifth = String.valueOf(floatingTileTracker.charAt(4));
	    	String sixth = String.valueOf(floatingTileTracker.charAt(5));
	    	
	    	floatingTileTracker = second + third + fourth + first + fifth + sixth;
	    	updateRelics();
	    	updateBoard();
	    	floatingPanel.setVisible(true);
			
		}
		
		if (e.getSource() == east||e.getSource() == west) {
			
			pickedRowCol = true;
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			if (e.getSource() == east) {
				nextSlide = "e";
			}
			else if (e.getSource() == west) {
				nextSlide = "w";
			}
			
			if (h == 3) {
				four.setEnabled(false);
				six.setEnabled(false);
				eight.setEnabled(false);
			}
			if (h == 5) {
				four.setEnabled(true);
				six.setEnabled(false);
				eight.setEnabled(false);
			}
			if (h == 7) {
				four.setEnabled(true);
				six.setEnabled(true);
				eight.setEnabled(false);
			}
			
			two.setText("2nd ROW");
			four.setText("4th ROW");
			six.setText("6th ROW");
			eight.setText("8th ROW");
			two.setVisible(true);
			four.setVisible(true);
			six.setVisible(true);
			eight.setVisible(true);
			
			rotateright.setEnabled(false);
			rotateleft.setEnabled(false);
			north.setEnabled(false);
			south.setEnabled(false);
			east.setEnabled(false);
			west.setEnabled(false);
		}
		if (e.getSource() == two||e.getSource() == four||e.getSource() == six||e.getSource() == eight) {
			
			pickedRowCol = false;
			if (e.getSource() == two) {
				nextSlide = nextSlide + Integer.toString(2);
			}
			else if (e.getSource() == four) {
				nextSlide = nextSlide + Integer.toString(4);
			}
			else if (e.getSource() == six) {
				nextSlide = nextSlide + Integer.toString(6);
			}
			else if (e.getSource() == eight) {
				nextSlide = nextSlide + Integer.toString(8);
			}
			
			boolean isValid = true;
			
			if (prevSlide.equals(nextSlide)) {
				errorMsg.setText("Invalid Move: Last exit point.");
				errorPanel.setVisible(true);
				relicPanel.setVisible(false);
				isValid = false;
				rotateright.setEnabled(true);
				rotateleft.setEnabled(true);
				north.setEnabled(true);
				south.setEnabled(true);
				east.setEnabled(true);
				west.setEnabled(true);
				two.setVisible(false);
				four.setVisible(false);
				six.setVisible(false);
				eight.setVisible(false);
			}
			
			if (isValid) {
				
				int insertRow;
				int insertColumn;
		    	String [][] temp = new String[h][w];
		    	for (int i = 0; i < h; i++) {
		    		for (int j = 0; j < w; j++) {
		    			temp[i][j] = " ";
		    		}
		    	}
		    	if (nextSlide.charAt(0) == 'n') {
		    		insertRow = 0;
		    		insertColumn = Integer.parseInt(String.valueOf(nextSlide.charAt(1))) - 1;
		    		if (greenPos[1] == insertColumn&&greenPos[0] != h-1) {
		    			greenPos[0] = greenPos[0] + 1;
		    		}
		    		else if (greenPos[1] == insertColumn&&greenPos[0] == h-1) {
		    			greenPos[0] = 0;
		    		}
		    		if (yellowPos[1] == insertColumn&&yellowPos[0] != h-1) {
		    			yellowPos[0] = yellowPos[0] + 1;
		    		}
		    		else if (yellowPos[1] == insertColumn&&yellowPos[0] == h-1) {
		    			yellowPos[0] = 0;
		    		}
		    		if (redPos[1] == insertColumn&&redPos[0] != h-1) {
		    			redPos[0] = redPos[0] + 1;
		    		}
		    		else if (redPos[1] == insertColumn&&redPos[0] == h-1) {
		    			redPos[0] = 0;
		    		}
		    		if (bluePos[1] == insertColumn&&bluePos[0] != h-1) {
		    			bluePos[0] = bluePos[0] + 1;
		    		}
		    		else if (bluePos[1] == insertColumn&&bluePos[0] == h-1) {
		    			bluePos[0] = 0;
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
		    		}
		    		else if (greenPos[1] == insertColumn&&greenPos[0] == 0) {
		    			greenPos[0] = h-1;
		    		}
		    		if (yellowPos[1] == insertColumn&&yellowPos[0] != 0) {
		    			yellowPos[0] = yellowPos[0] - 1;
		    		}
		    		else if (yellowPos[1] == insertColumn&&yellowPos[0] == 0) {
		    			yellowPos[0] = h-1;
		    		}
		    		if (redPos[1] == insertColumn&&redPos[0] != 0) {
		    			redPos[0] = redPos[0] - 1;
		    		}
		    		else if (redPos[1] == insertColumn&&redPos[0] == 0) {
		    			redPos[0] = h-1;
		    		}
		    		if (bluePos[1] == insertColumn&&bluePos[0] != 0) {
		    			bluePos[0] = bluePos[0] - 1;
		    		}
		    		else if (bluePos[1] == insertColumn&&bluePos[0] == 0) {
		    			bluePos[0] = h-1;
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
		    		}
		    		else if (greenPos[0] == insertRow&&greenPos[1] == w-1) {
		    			greenPos[1] = 0;
		    		}
		    		if (yellowPos[0] == insertRow&&yellowPos[1] != w-1) {
		    			yellowPos[1] = yellowPos[1] + 1;
		    		}
		    		else if (yellowPos[0] == insertRow&&yellowPos[1] == w-1) {
		    			yellowPos[1] = 0;
		    		}
		    		if (redPos[0] == insertRow&&redPos[1] != w-1) {
		    			redPos[1] = redPos[1] + 1;
		    		}
		    		else if (redPos[0] == insertRow&&redPos[1] == w-1) {
		    			redPos[1] = 0;
		    		}
		    		if (bluePos[0] == insertRow&&bluePos[1] != w-1) {
		    			bluePos[1] = bluePos[1] + 1;
		    		}
		    		else if (bluePos[0] == insertRow&&bluePos[1] == w-1) {
		    			bluePos[1] = 0;
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
		    		}
		    		else if (greenPos[0] == insertRow&&greenPos[1] == 0) {
		    			greenPos[1] = w-1;
		    		}
		    		if (yellowPos[0] == insertRow&&yellowPos[1] != 0) {
		    			yellowPos[1] = yellowPos[1] - 1;
		    		}
		    		else if (yellowPos[0] == insertRow&&yellowPos[1] == 0) {
		    			yellowPos[1] = w-1;
		    		}
		    		if (redPos[0] == insertRow&&redPos[1] != 0) {
		    			redPos[1] = redPos[1] - 1;
		    		}
		    		else if (redPos[0] == insertRow&&redPos[1] == 0) {
		    			redPos[1] = w-1;
		    		}
		    		if (bluePos[0] == insertRow&&bluePos[1] != 0) {
		    			bluePos[1] = bluePos[1] - 1;
		    		}
		    		else if (bluePos[0] == insertRow&&bluePos[1] == 0) {
		    			bluePos[1] = w-1;
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
		    	
		    	updateRelics();
		    	updateBoard();
		    	
				two.setVisible(false);
				four.setVisible(false);
				six.setVisible(false);
				eight.setVisible(false);
				rotateleft.setVisible(false);
				rotateright.setVisible(false);
				slideOptions.setVisible(false);
				north.setVisible(false);
				south.setVisible(false);
				east.setVisible(false);
				west.setVisible(false);
				floatingPanel.setVisible(true);
				
				textDisplayed.setText(whoseTurn + " Move your adventurer: ");
				done.setVisible(true);
				pathFinder.setVisible(true);
				moveMade = false;
				
				if (nextSlide.charAt(0) == 'n') {
	    			prevSlide = Character.toString('s') + Character.toString(nextSlide.charAt(1));
	    		}
	    		else if (nextSlide.charAt(0) == 's') {
	    			prevSlide = Character.toString('n') + Character.toString(nextSlide.charAt(1));
	    		}
	    		else if (nextSlide.charAt(0) == 'e') {
	    			prevSlide = Character.toString('w') + Character.toString(nextSlide.charAt(1));
	    		}
	    		else if (nextSlide.charAt(0) == 'w') {
	    			prevSlide = Character.toString('e') + Character.toString(nextSlide.charAt(1));
	    		}
				
				
				if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
					if (greenRelicCollected < r) {
						relicMsg.setText("Green has collected a relic.");
						relicPanel.setVisible(true);
		    			greenRelicCollected++;
		    		}
		    		if (greenRelicCollected == r) {
		    			errorMsg.setText("Green has all their relics.");
						errorPanel.setVisible(true);
		    		}
				}
				if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
					if (yellowRelicCollected < r) {
						relicMsg.setText("Yellow has collected a relic.");
						relicPanel.setVisible(true);
		    			yellowRelicCollected++;
		    		}
		    		if (yellowRelicCollected == r) {
		    			errorMsg.setText("Yellow has all their relics.");
						errorPanel.setVisible(true);
		    		}
				}
				if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
					if (redRelicCollected < r) {
						relicMsg.setText("Red has collected a relic.");
						relicPanel.setVisible(true);
		    			redRelicCollected++;
		    		}
		    		if (redRelicCollected == r) {
		    			errorMsg.setText("Red has all their relics.");
						errorPanel.setVisible(true);
		    		}
				}
				if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
					if (blueRelicCollected < r) {
						relicMsg.setText("Blue has collected a relic.");
						relicPanel.setVisible(true);
		    			blueRelicCollected++;
		    		}
		    		if (blueRelicCollected == r) {
		    			errorMsg.setText("Blue has all their relics.");
						errorPanel.setVisible(true);
		    		}
				}
				updateRelics();
				updateBoard();
				bigPanel.setVisible(true);
				greenScore.setText("Green:    " + greenRelicCollected);
				yellowScore.setText("Yellow:   " + yellowRelicCollected);
				redScore.setText("Red:        " + redRelicCollected);
				blueScore.setText("Blue:       " + blueRelicCollected);
			}
			
		}
		if (e.getSource() == done) {
			
			pickedRowCol = false;
			theDoneButton();
			
		}
		if (e.getSource() == pathFinder) {
			
			pathRow = 0;
			pathCol = 0;
//			pickRow.setSelectedIndex(0);
//			pickCol.setSelectedIndex(0);
			pathFinder.setVisible(false);
			done.setVisible(false);
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			pathRowPanel.setVisible(true);
			pathColPanel.setVisible(true);
			rowOne.setVisible(true);
			rowTwo.setVisible(true);
			rowThree.setVisible(true);
			rowFour.setVisible(true);
			rowFive.setVisible(true);
			rowSix.setVisible(true);
			rowSeven.setVisible(true);
			rowEight.setVisible(true);
			rowNine.setVisible(true);
			colOne.setVisible(true);
			colTwo.setVisible(true);
			colThree.setVisible(true);
			colFour.setVisible(true);
			colFive.setVisible(true);
			colSix.setVisible(true);
			colSeven.setVisible(true);
			colEight.setVisible(true);
			colNine.setVisible(true);
			
			rowOne.setEnabled(true);
			rowTwo.setEnabled(true);
			rowThree.setEnabled(true);
			rowFour.setEnabled(true);
			rowFive.setEnabled(true);
			rowSix.setEnabled(true);
			rowSeven.setEnabled(true);
			rowEight.setEnabled(true);
			rowNine.setEnabled(true);
			colOne.setEnabled(true);
			colTwo.setEnabled(true);
			colThree.setEnabled(true);
			colFour.setEnabled(true);
			colFive.setEnabled(true);
			colSix.setEnabled(true);
			colSeven.setEnabled(true);
			colEight.setEnabled(true);
			colNine.setEnabled(true);
			
			if (h == 3) {
				rowFour.setEnabled(false);
				rowFive.setEnabled(false);
				rowSix.setEnabled(false);
				rowSeven.setEnabled(false);
				rowEight.setEnabled(false);
				rowNine.setEnabled(false);
			}
			else if (h == 5) {
				rowSix.setEnabled(false);
				rowSeven.setEnabled(false);
				rowEight.setEnabled(false);
				rowNine.setEnabled(false);
			}
			else if (h == 7) {
				rowEight.setEnabled(false);
				rowNine.setEnabled(false);
			}
			if (w == 3) {
				colFour.setEnabled(false);
				colFive.setEnabled(false);
				colSix.setEnabled(false);
				colSeven.setEnabled(false);
				colEight.setEnabled(false);
				colNine.setEnabled(false);
			}
			else if (w == 5) {
				colSix.setEnabled(false);
				colSeven.setEnabled(false);
				colEight.setEnabled(false);
				colNine.setEnabled(false);
			}
			else if (w == 7) {
				colEight.setEnabled(false);
				colNine.setEnabled(false);
			}
//			pickRow.setEnabled(true);
//			pickCol.setEnabled(true);
//			pickRow.setVisible(true);
//			pickCol.setVisible(true);
			
		}
		
	}
	
	/**
	 * This method updates the player to the next player's turn and updates the {@code Color} 
	 * that is used to show whose turn it is. 
	 */
	public void next () {
		
		nextSlide = "";
		if (whoseTurn.equals("[Green]")) {
			whoseTurn = "[Yellow]";
			currColor = new Color(189, 180, 0);
			currImage = new ImageIcon("yellowTitle.png");
		}
		else if (whoseTurn.equals("[Yellow]")) {
			whoseTurn = "[Red]";
			currColor = new Color(255, 40, 20);
			textBox.setBackground(Color.lightGray);
			errorPanel.setBackground(Color.lightGray);
			relicPanel.setBackground(Color.lightGray);
			slideOptions.setBackground(Color.lightGray);
			pathRowPanel.setBackground(Color.lightGray);
			pathColPanel.setBackground(Color.lightGray);
			currImage = new ImageIcon("redTitle.png");
		}
		else if (whoseTurn.equals("[Red]")) {
			whoseTurn = "[Blue]";
			currColor = new Color(60, 80, 200);
			currImage = new ImageIcon("blueTitle.png");
		}
		else if (whoseTurn.equals("[Blue]")) {
			whoseTurn = "[Green]";
			currColor = new Color(50, 190, 120);
			textBox.setBackground(Color.darkGray);
			errorPanel.setBackground(Color.darkGray);
			relicPanel.setBackground(Color.darkGray);
			slideOptions.setBackground(Color.darkGray);
			pathRowPanel.setBackground(Color.darkGray);
			pathColPanel.setBackground(Color.darkGray);
			currImage = new ImageIcon("greenTitle.png");
		}
		greenScore.setText("Green:    " + greenRelicCollected);
		yellowScore.setText("Yellow:   " + yellowRelicCollected);
		redScore.setText("Red:        " + redRelicCollected);
		blueScore.setText("Blue:       " + blueRelicCollected);
	}
	
	/**
	 * This method will be called when a player's Moving Turn is finished. 
	 * It calls the {@link next next} method which updates to the next player's turn. This 
	 * method will make the respective components visible and invisible. 
	 */
	public void theDoneButton () {
		
		moveMade = true;
		next();
		
		done.setForeground(currColor);
		done.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		pathFinder.setForeground(currColor);
		pathFinder.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		rotateleft.setForeground(currColor);
		rotateleft.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		rotateright.setForeground(currColor);
		rotateright.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		north.setForeground(currColor);
		north.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		south.setForeground(currColor);
		south.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		east.setForeground(currColor);
		east.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		west.setForeground(currColor);
		west.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		two.setForeground(currColor);
		two.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		four.setForeground(currColor);
		four.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		six.setForeground(currColor);
		six.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		eight.setForeground(currColor);
		eight.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		topPan.setBackground(currColor);
		bottomPan.setBackground(currColor);
		leftPan.setBackground(currColor);
		rightPan.setBackground(currColor);
		textDisplayed.setForeground(currColor);
		errorMsg.setForeground(currColor);
		relicMsg.setForeground(currColor);
		slideOptionsText.setForeground(currColor);
		pathRowLabel.setForeground(currColor);
		pathColLabel.setForeground(currColor);
		rowOne.setForeground(currColor);
		rowTwo.setForeground(currColor);
		rowThree.setForeground(currColor);
		rowFour.setForeground(currColor);
		rowFive.setForeground(currColor);
		rowSix.setForeground(currColor);
		rowSeven.setForeground(currColor);
		rowEight.setForeground(currColor);
		rowNine.setForeground(currColor);
		colOne.setForeground(currColor);
		colTwo.setForeground(currColor);
		colThree.setForeground(currColor);
		colFour.setForeground(currColor);
		colFive.setForeground(currColor);
		colSix.setForeground(currColor);
		colSeven.setForeground(currColor);
		colEight.setForeground(currColor);
		colNine.setForeground(currColor);
		textBox.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		errorPanel.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		relicPanel.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		slideOptions.setBorder(BorderFactory.createLineBorder(currColor, 5, true));
		pathRowPanel.setBorder(BorderFactory.createLineBorder(currColor, 3, true));
		pathColPanel.setBorder(BorderFactory.createLineBorder(currColor, 3, true));
		rowOne.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		rowTwo.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		rowThree.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		rowFour.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		rowFive.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		rowSix.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		rowSeven.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		rowEight.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		rowNine.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colOne.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colTwo.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colThree.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colFour.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colFive.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colSix.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colSeven.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colEight.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		colNine.setBorder(BorderFactory.createLineBorder(currColor, 2, true));
		title.setIcon(currImage);
		
		textDisplayed.setText(whoseTurn + " Rotate and slide the floating tile: ");
		
		done.setVisible(false);
		pathFinder.setVisible(false);
		pathRowPanel.setVisible(false);
		pathColPanel.setVisible(false);
		rowOne.setVisible(false);
		rowTwo.setVisible(false);
		rowThree.setVisible(false);
		rowFour.setVisible(false);
		rowFive.setVisible(false);
		rowSix.setVisible(false);
		rowSeven.setVisible(false);
		rowEight.setVisible(false);
		rowNine.setVisible(false);
		colOne.setVisible(false);
		colTwo.setVisible(false);
		colThree.setVisible(false);
		colFour.setVisible(false);
		colFive.setVisible(false);
		colSix.setVisible(false);
		colSeven.setVisible(false);
		colEight.setVisible(false);
		colNine.setVisible(false);
//		pickRow.setVisible(false);
//		pickCol.setVisible(false);
		
		rotateleft.setVisible(true);
		rotateright.setVisible(true);
		slideOptions.setVisible(true);
		north.setVisible(true);
		south.setVisible(true);
		east.setVisible(true);
		west.setVisible(true);
		floatingPanel.setVisible(true);
		rotateright.setEnabled(true);
		rotateleft.setEnabled(true);
		north.setEnabled(true);
		south.setEnabled(true);
		east.setEnabled(true);
		west.setEnabled(true);
		if (relicCollected) {
			relicPanel.setVisible(true);
		}
		else {
			relicPanel.setVisible(false);
		}
		if (relicCollected&&whoseTurn.equals("[Yellow]")&&greenRelicCollected == r) {
			errorPanel.setVisible(true);
		}
		else if (relicCollected&&whoseTurn.equals("[Red]")&&yellowRelicCollected == r) {
			errorPanel.setVisible(true);
		}
		else if (relicCollected&&whoseTurn.equals("[Blue]")&&redRelicCollected == r) {
			errorPanel.setVisible(true);
		}
		else if (relicCollected&&whoseTurn.equals("[Green]")&&blueRelicCollected == r) {
			errorPanel.setVisible(true);
		}
		else {
			errorPanel.setVisible(false);
		}
		
	}
	
	/**
	 * This method updates the current relic that should be displayed on the screen. 
	 * It also tests if any player has won. 
	 */
	public void updateRelics () {
		
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
	        	
	        	updateBoard();
				bigPanel.setVisible(true);
	        	String[] responses = {"Exit", "Restart"};
				int ans = JOptionPane.showOptionDialog(null, "GREEN HAS WON THE GAME, CONGRAGULATIONS! THANKS FOR PLAYING.", "Green has made his country proud", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("greenAd.png"), responses, 0);
				if (ans == 0||ans == -1) {
					this.dispose();
					System.exit(0);
				}
				if (ans == 1) {
					this.dispose();
					GUI frame = new GUI(w, h, r, startingFloatingTileTracker, startingTracker);
				}
	        }
	        if (r == yellowRelicCollected&&yellowPos[0] == 0&&yellowPos[1] == w-1) {
	        	
	        	updateBoard();
				bigPanel.setVisible(true);
	        	String[] responses = {"Exit", "Restart"};
				int ans = JOptionPane.showOptionDialog(null, "YELLOW HAS WON THE GAME, CONGRAGULATIONS! THANKS FOR PLAYING.", "Yellow has made his country proud", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("yellowAd.png"), responses, 0);
				if (ans == 0||ans == -1) {
					this.dispose();
					System.exit(0);
				}
				if (ans == 1) {
					this.dispose();
					GUI frame = new GUI(w, h, r, startingFloatingTileTracker, startingTracker);
				}
	        }
	        if (r == redRelicCollected&&redPos[0] == h-1&&redPos[1] == 0) {
	        	
	        	updateBoard();
				bigPanel.setVisible(true);
	        	String[] responses = {"Exit", "Restart"};
				int ans = JOptionPane.showOptionDialog(null, "RED HAS WON THE GAME, CONGRAGULATIONS! THANKS FOR PLAYING.", "Red has made his country proud", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("redAd.png"), responses, 0);
				if (ans == 0||ans == -1) {
					this.dispose();
					System.exit(0);
				}
				if (ans == 1) {
					this.dispose();
					GUI frame = new GUI(w, h, r, startingFloatingTileTracker, startingTracker);
				}
	        }
	        if (r == blueRelicCollected&&bluePos[0] == h-1&&bluePos[1] == w-1) {
	        	
	        	updateBoard();
				bigPanel.setVisible(true);
	        	String[] responses = {"Exit", "Restart"};
				int ans = JOptionPane.showOptionDialog(null, "BLUE HAS WON THE GAME, CONGRAGULATIONS! THANKS FOR PLAYING.", "Blue has made his country proud", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("blueAd.png"), responses, 0);
				if (ans == 0||ans == -1) {
					this.dispose();
					System.exit(0);
				}
				if (ans == 1) {
					this.dispose();
					GUI frame = new GUI(w, h, r, startingFloatingTileTracker, startingTracker);
				}
	        }
		}
		
	}
	
	/**
	 * This method will update the graphical board. It should be called whenever a new movement has been made 
	 * on the board or floating tile. 
	 */
	public void updateBoard () {
		
		bigPanel.removeAll();
		floatingPanel.removeAll();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				
				image = new ImageIcon(tracker[i][j].substring(0, 4)+"real.jpg");
				JLabel tile = new JLabel(image);
				tile.setLayout(null);
				if (greenPos[0] == i&&greenPos[1] == j) {
					tile.add(greenAd);
				}
				if (yellowPos[0] == i&&yellowPos[1] == j) {
					tile.add(yellowAd);
				}
				if (redPos[0] == i&&redPos[1] == j) {
					tile.add(redAd);
				}
				if (bluePos[0] == i&&bluePos[1] == j) {
					tile.add(blueAd);
				}
				if (greenRelicPos[0] == i&&greenRelicPos[1] == j) {
					tile.add(greenRel);
				}
				if (yellowRelicPos[0] == i&&yellowRelicPos[1] == j) {
					tile.add(yellowRel);
				}
				if (redRelicPos[0] == i&&redRelicPos[1] == j) {
					tile.add(redRel);
				}
				if (blueRelicPos[0] == i&&blueRelicPos[1] == j) {
					tile.add(blueRel);
				}
				bigPanel.add(tile);
			}
		}
		JLabel floater = new JLabel(new ImageIcon(floatingTileTracker.substring(0, 4)+"real.jpg"));
		if (greenRelicPos[0] == 100&&greenRelicPos[1] == 100) {
			floater.add(greenRel);
		}
		if (yellowRelicPos[0] == 100&&yellowRelicPos[1] == 100) {
			floater.add(yellowRel);
		}
		if (redRelicPos[0] == 100&&redRelicPos[1] == 100) {
			floater.add(redRel);
		}
		if (blueRelicPos[0] == 100&&blueRelicPos[1] == 100) {
			floater.add(blueRel);
		}
		floatingPanel.add(floater);
		
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
    	
    	if (destRow == -1||destCol == -1) {
    		return false;
    	}
    	
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
    
	@Override
	public void keyTyped(KeyEvent e) {
		
		if (!moveMade) {
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			switch (e.getKeyChar()) {
			case 'w': 	bigPanel.setVisible(false);
						if (whoseTurn.equals("[Green]")&&greenPos[0] != 0) {
							if (tracker[greenPos[0]][greenPos[1]].charAt(0) == '1'&&tracker[greenPos[0] - 1][greenPos[1]].charAt(2) == '1') {
								greenPos[0] = greenPos[0] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Green]")&&greenPos[0] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[0] != 0) {
							if (tracker[yellowPos[0]][yellowPos[1]].charAt(0) == '1'&&tracker[yellowPos[0] - 1][yellowPos[1]].charAt(2) == '1') {
								yellowPos[0] = yellowPos[0] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[0] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Red]")&&redPos[0] != 0) {
							if (tracker[redPos[0]][redPos[1]].charAt(0) == '1'&&tracker[redPos[0] - 1][redPos[1]].charAt(2) == '1') {
								redPos[0] = redPos[0] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Red]")&&redPos[0] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[0] != 0) {
							if (tracker[bluePos[0]][bluePos[1]].charAt(0) == '1'&&tracker[bluePos[0] - 1][bluePos[1]].charAt(2) == '1') {
								bluePos[0] = bluePos[0] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[0] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
							if (greenRelicCollected < r) {
								relicMsg.setText("Green has collected a relic.");
				    			greenRelicCollected++;
				    			theDoneButton();
				    			relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (greenRelicCollected == r) {
				    			errorMsg.setText("Green has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
							if (yellowRelicCollected < r) {
								relicMsg.setText("Yellow has collected a relic.");
				    			yellowRelicCollected++;
				    			theDoneButton();
				    			relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (yellowRelicCollected == r) {
				    			errorMsg.setText("Yellow has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
							if (redRelicCollected < r) {
								relicMsg.setText("Red has collected a relic.");
				    			redRelicCollected++;
				    			theDoneButton();
				    			relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (redRelicCollected == r) {
				    			errorMsg.setText("Red has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
							if (blueRelicCollected < r) {
								relicMsg.setText("Blue has collected a relic.");
				    			blueRelicCollected++;
				    			theDoneButton();
				    			relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (blueRelicCollected == r) {
				    			errorMsg.setText("Blue has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						updateRelics();
						updateBoard();
						bigPanel.setVisible(true);
			break;
			case 's': 	bigPanel.setVisible(false);
						if (whoseTurn.equals("[Green]")&&greenPos[0] != h-1) {
							if (tracker[greenPos[0]][greenPos[1]].charAt(2) == '1'&&tracker[greenPos[0] + 1][greenPos[1]].charAt(0) == '1') {
								greenPos[0] = greenPos[0] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Green]")&&greenPos[0] == h-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[0] != h-1) {
							if (tracker[yellowPos[0]][yellowPos[1]].charAt(2) == '1'&&tracker[yellowPos[0] + 1][yellowPos[1]].charAt(0) == '1') {
								yellowPos[0] = yellowPos[0] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[0] == h-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Red]")&&redPos[0] != h-1) {
							if (tracker[redPos[0]][redPos[1]].charAt(2) == '1'&&tracker[redPos[0] + 1][redPos[1]].charAt(0) == '1') {
								redPos[0] = redPos[0] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Red]")&&redPos[0] == h-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[0] != h-1) {
							if (tracker[bluePos[0]][bluePos[1]].charAt(2) == '1'&&tracker[bluePos[0] + 1][bluePos[1]].charAt(0) == '1') {
								bluePos[0] = bluePos[0] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[0] == h-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
							if (greenRelicCollected < r) {
								relicMsg.setText("Green has collected a relic.");
				    			greenRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (greenRelicCollected == r) {
				    			errorMsg.setText("Green has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
							if (yellowRelicCollected < r) {
								relicMsg.setText("Yellow has collected a relic.");
				    			yellowRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (yellowRelicCollected == r) {
				    			errorMsg.setText("Yellow has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
							if (redRelicCollected < r) {
								relicMsg.setText("Red has collected a relic.");
				    			redRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (redRelicCollected == r) {
				    			errorMsg.setText("Red has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
							if (blueRelicCollected < r) {
								relicMsg.setText("Blue has collected a relic.");
				    			blueRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (blueRelicCollected == r) {
				    			errorMsg.setText("Blue has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						updateRelics();
						updateBoard();
						bigPanel.setVisible(true);
			break;
			case 'a': 	bigPanel.setVisible(false);
						if (whoseTurn.equals("[Green]")&&greenPos[1] != 0) {
							if (tracker[greenPos[0]][greenPos[1]].charAt(3) == '1'&&tracker[greenPos[0]][greenPos[1] - 1].charAt(1) == '1') {
								greenPos[1] = greenPos[1] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Green]")&&greenPos[1] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[1] != 0) {
							if (tracker[yellowPos[0]][yellowPos[1]].charAt(3) == '1'&&tracker[yellowPos[0]][yellowPos[1] - 1].charAt(1) == '1') {
								yellowPos[1] = yellowPos[1] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[1] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Red]")&&redPos[1] != 0) {
							if (tracker[redPos[0]][redPos[1]].charAt(3) == '1'&&tracker[redPos[0]][redPos[1] - 1].charAt(1) == '1') {
								redPos[1] = redPos[1] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Red]")&&redPos[1] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[1] != 0) {
							if (tracker[bluePos[0]][bluePos[1]].charAt(3) == '1'&&tracker[bluePos[0]][bluePos[1] - 1].charAt(1) == '1') {
								bluePos[1] = bluePos[1] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[1] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
							if (greenRelicCollected < r) {
								relicMsg.setText("Green has collected a relic.");
				    			greenRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (greenRelicCollected == r) {
				    			errorMsg.setText("Green has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
							if (yellowRelicCollected < r) {
								relicMsg.setText("Yellow has collected a relic.");
				    			yellowRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (yellowRelicCollected == r) {
				    			errorMsg.setText("Yellow has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
							if (redRelicCollected < r) {
								relicMsg.setText("Red has collected a relic.");
				    			redRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (redRelicCollected == r) {
				    			errorMsg.setText("Red has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
							if (blueRelicCollected < r) {
								relicMsg.setText("Blue has collected a relic.");
				    			blueRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (blueRelicCollected == r) {
				    			errorMsg.setText("Blue has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						updateRelics();
						updateBoard();
						bigPanel.setVisible(true);
			break;
			case 'd': 	bigPanel.setVisible(false);
						if (whoseTurn.equals("[Green]")&&greenPos[1] != w-1) {
							if (tracker[greenPos[0]][greenPos[1]].charAt(1) == '1'&&tracker[greenPos[0]][greenPos[1] + 1].charAt(3) == '1') {
								greenPos[1] = greenPos[1] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Green]")&&greenPos[1] == w-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[1] != w-1) {
							if (tracker[yellowPos[0]][yellowPos[1]].charAt(1) == '1'&&tracker[yellowPos[0]][yellowPos[1] + 1].charAt(3) == '1') {
								yellowPos[1] = yellowPos[1] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[1] == w-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Red]")&&redPos[1] != w-1) {
							if (tracker[redPos[0]][redPos[1]].charAt(1) == '1'&&tracker[redPos[0]][redPos[1] + 1].charAt(3) == '1') {
								redPos[1] = redPos[1] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Red]")&&redPos[1] == w-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[1] != w-1) {
							if (tracker[bluePos[0]][bluePos[1]].charAt(1) == '1'&&tracker[bluePos[0]][bluePos[1] + 1].charAt(3) == '1') {
								bluePos[1] = bluePos[1] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[1] == w-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
							if (greenRelicCollected < r) {
								relicMsg.setText("Green has collected a relic.");
				    			greenRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (greenRelicCollected == r) {
				    			errorMsg.setText("Green has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
							if (yellowRelicCollected < r) {
								relicMsg.setText("Yellow has collected a relic.");
				    			yellowRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (yellowRelicCollected == r) {
				    			errorMsg.setText("Yellow has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
							if (redRelicCollected < r) {
								relicMsg.setText("Red has collected a relic.");
				    			redRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (redRelicCollected == r) {
				    			errorMsg.setText("Red has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
							if (blueRelicCollected < r) {
								relicMsg.setText("Blue has collected a relic.");
				    			blueRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
								moveMade = true;
				    		}
				    		if (blueRelicCollected == r) {
				    			errorMsg.setText("Blue has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						updateRelics();
						updateBoard();
						bigPanel.setVisible(true);
			break;
			}
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (!moveMade) {
			errorPanel.setVisible(false);
			relicPanel.setVisible(false);
			switch (e.getKeyCode()) {
			case 38: 	bigPanel.setVisible(false);
						if (whoseTurn.equals("[Green]")&&greenPos[0] != 0) {
							if (tracker[greenPos[0]][greenPos[1]].charAt(0) == '1'&&tracker[greenPos[0] - 1][greenPos[1]].charAt(2) == '1') {
								greenPos[0] = greenPos[0] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Green]")&&greenPos[0] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[0] != 0) {
							if (tracker[yellowPos[0]][yellowPos[1]].charAt(0) == '1'&&tracker[yellowPos[0] - 1][yellowPos[1]].charAt(2) == '1') {
								yellowPos[0] = yellowPos[0] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[0] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Red]")&&redPos[0] != 0) {
							if (tracker[redPos[0]][redPos[1]].charAt(0) == '1'&&tracker[redPos[0] - 1][redPos[1]].charAt(2) == '1') {
								redPos[0] = redPos[0] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Red]")&&redPos[0] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[0] != 0) {
							if (tracker[bluePos[0]][bluePos[1]].charAt(0) == '1'&&tracker[bluePos[0] - 1][bluePos[1]].charAt(2) == '1') {
								bluePos[0] = bluePos[0] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[0] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
							if (greenRelicCollected < r) {
								relicMsg.setText("Green has collected a relic.");
				    			greenRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (greenRelicCollected == r) {
				    			errorMsg.setText("Green has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
							if (yellowRelicCollected < r) {
								relicMsg.setText("Yellow has collected a relic.");
				    			yellowRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (yellowRelicCollected == r) {
				    			errorMsg.setText("Yellow has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
							if (redRelicCollected < r) {
								relicMsg.setText("Red has collected a relic.");
				    			redRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (redRelicCollected == r) {
				    			errorMsg.setText("Red has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
							if (blueRelicCollected < r) {
								relicMsg.setText("Blue has collected a relic.");
				    			blueRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (blueRelicCollected == r) {
				    			errorMsg.setText("Blue has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						updateRelics();
						updateBoard();
						bigPanel.setVisible(true);
			break;
			case 40: 	bigPanel.setVisible(false);
						if (whoseTurn.equals("[Green]")&&greenPos[0] != h-1) {
							if (tracker[greenPos[0]][greenPos[1]].charAt(2) == '1'&&tracker[greenPos[0] + 1][greenPos[1]].charAt(0) == '1') {
								greenPos[0] = greenPos[0] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Green]")&&greenPos[0] == h-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[0] != h-1) {
							if (tracker[yellowPos[0]][yellowPos[1]].charAt(2) == '1'&&tracker[yellowPos[0] + 1][yellowPos[1]].charAt(0) == '1') {
								yellowPos[0] = yellowPos[0] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[0] == h-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Red]")&&redPos[0] != h-1) {
							if (tracker[redPos[0]][redPos[1]].charAt(2) == '1'&&tracker[redPos[0] + 1][redPos[1]].charAt(0) == '1') {
								redPos[0] = redPos[0] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Red]")&&redPos[0] == h-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[0] != h-1) {
							if (tracker[bluePos[0]][bluePos[1]].charAt(2) == '1'&&tracker[bluePos[0] + 1][bluePos[1]].charAt(0) == '1') {
								bluePos[0] = bluePos[0] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[0] == h-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
							if (greenRelicCollected < r) {
								relicMsg.setText("Green has collected a relic.");
				    			greenRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (greenRelicCollected == r) {
				    			errorMsg.setText("Green has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
							if (yellowRelicCollected < r) {
								relicMsg.setText("Yellow has collected a relic.");
				    			yellowRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (yellowRelicCollected == r) {
				    			errorMsg.setText("Yellow has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
							if (redRelicCollected < r) {
								relicMsg.setText("Red has collected a relic.");
				    			redRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (redRelicCollected == r) {
				    			errorMsg.setText("Red has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
							if (blueRelicCollected < r) {
								relicMsg.setText("Blue has collected a relic.");
				    			blueRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (blueRelicCollected == r) {
				    			errorMsg.setText("Blue has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						updateRelics();
						updateBoard();
						bigPanel.setVisible(true);
			break;
			case 37: 	bigPanel.setVisible(false);
						if (whoseTurn.equals("[Green]")&&greenPos[1] != 0) {
							if (tracker[greenPos[0]][greenPos[1]].charAt(3) == '1'&&tracker[greenPos[0]][greenPos[1] - 1].charAt(1) == '1') {
								greenPos[1] = greenPos[1] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Green]")&&greenPos[1] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[1] != 0) {
							if (tracker[yellowPos[0]][yellowPos[1]].charAt(3) == '1'&&tracker[yellowPos[0]][yellowPos[1] - 1].charAt(1) == '1') {
								yellowPos[1] = yellowPos[1] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[1] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Red]")&&redPos[1] != 0) {
							if (tracker[redPos[0]][redPos[1]].charAt(3) == '1'&&tracker[redPos[0]][redPos[1] - 1].charAt(1) == '1') {
								redPos[1] = redPos[1] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Red]")&&redPos[1] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[1] != 0) {
							if (tracker[bluePos[0]][bluePos[1]].charAt(3) == '1'&&tracker[bluePos[0]][bluePos[1] - 1].charAt(1) == '1') {
								bluePos[1] = bluePos[1] - 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[1] == 0) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
							if (greenRelicCollected < r) {
								relicMsg.setText("Green has collected a relic.");
				    			greenRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (greenRelicCollected == r) {
				    			errorMsg.setText("Green has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
							if (yellowRelicCollected < r) {
								relicMsg.setText("Yellow has collected a relic.");
				    			yellowRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (yellowRelicCollected == r) {
				    			errorMsg.setText("Yellow has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
							if (redRelicCollected < r) {
								relicMsg.setText("Red has collected a relic.");
				    			redRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (redRelicCollected == r) {
				    			errorMsg.setText("Red has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
							if (blueRelicCollected < r) {
								relicMsg.setText("Blue has collected a relic.");
				    			blueRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (blueRelicCollected == r) {
				    			errorMsg.setText("Blue has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						updateRelics();
						updateBoard();
						bigPanel.setVisible(true);
			break;
			case 39: 	bigPanel.setVisible(false);
						if (whoseTurn.equals("[Green]")&&greenPos[1] != w-1) {
							if (tracker[greenPos[0]][greenPos[1]].charAt(1) == '1'&&tracker[greenPos[0]][greenPos[1] + 1].charAt(3) == '1') {
								greenPos[1] = greenPos[1] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Green]")&&greenPos[1] == w-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[1] != w-1) {
							if (tracker[yellowPos[0]][yellowPos[1]].charAt(1) == '1'&&tracker[yellowPos[0]][yellowPos[1] + 1].charAt(3) == '1') {
								yellowPos[1] = yellowPos[1] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Yellow]")&&yellowPos[1] == w-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Red]")&&redPos[1] != w-1) {
							if (tracker[redPos[0]][redPos[1]].charAt(1) == '1'&&tracker[redPos[0]][redPos[1] + 1].charAt(3) == '1') {
								redPos[1] = redPos[1] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Red]")&&redPos[1] == w-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[1] != w-1) {
							if (tracker[bluePos[0]][bluePos[1]].charAt(1) == '1'&&tracker[bluePos[0]][bluePos[1] + 1].charAt(3) == '1') {
								bluePos[1] = bluePos[1] + 1;
							}
							else {
								errorMsg.setText("Invalid Move: No path.");
								errorPanel.setVisible(true);
							}
						}
						else if (whoseTurn.equals("[Blue]")&&bluePos[1] == w-1) {
							errorMsg.setText("Invalid Move: Off the board.");
							errorPanel.setVisible(true);
						}
						if (greenPos[0] == greenRelicPos[0]&&greenPos[1] == greenRelicPos[1]) {
							if (greenRelicCollected < r) {
								relicMsg.setText("Green has collected a relic.");
				    			greenRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (greenRelicCollected == r) {
				    			errorMsg.setText("Green has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (yellowPos[0] == yellowRelicPos[0]&&yellowPos[1] == yellowRelicPos[1]) {
							if (yellowRelicCollected < r) {
								relicMsg.setText("Yellow has collected a relic.");
				    			yellowRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (yellowRelicCollected == r) {
				    			errorMsg.setText("Yellow has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (redPos[0] == redRelicPos[0]&&redPos[1] == redRelicPos[1]) {
							if (redRelicCollected < r) {
								relicMsg.setText("Red has collected a relic.");
				    			redRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (redRelicCollected == r) {
				    			errorMsg.setText("Red has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						if (bluePos[0] == blueRelicPos[0]&&bluePos[1] == blueRelicPos[1]) {
							if (blueRelicCollected < r) {
								relicMsg.setText("Blue has collected a relic.");
				    			blueRelicCollected++;
				    			theDoneButton();
								relicPanel.setVisible(true);
				    			moveMade = true;
				    		}
				    		if (blueRelicCollected == r) {
				    			errorMsg.setText("Blue has all their relics.");
								errorPanel.setVisible(true);
				    		}
						}
						updateRelics();
						updateBoard();
						bigPanel.setVisible(true);
			break;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
