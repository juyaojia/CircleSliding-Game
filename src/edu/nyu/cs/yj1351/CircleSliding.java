package edu.nyu.cs.yj1351;

/**
 * A game that requires the user to hit certain aim repeatedly to get their firing accuracy practiced, a simple version of the game aim hero
 * 
 * @author Yaojia Ju,Yizhou Wan
 * @version 1.0
 * 
 */

import edu.nyu.cs.yj1351.Circle;
import edu.nyu.cs.yj1351.Bomb;
import processing.core.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class CircleSliding extends PApplet {

    private static int wide; // the width of the window
    private static int heights; //the height of the window 
    private static int centerX; //where the circle first appear
    private static int centerY; //where the circle first appear
    private static Scanner userInput = new Scanner(System.in);
    private float speed; //speed of the circle changing speed
    private int score = 0; //your score
    public static int life; //your life
    private PImage button;// exit button
    private PImage photo;//show photo
    private PImage photo2;//show photo
    private int photoWide;//the width of the photo
    private int photoHeight;//the length of the photo
    private int photo2Wide;//the width of the photo2
    private int photo2Height;//the length of the photo2
    private float photoSpeed;// the speed of the photo changing speed
    private boolean showPhoto = false;//switch to show photo
    private Circle circle = new Circle();//instantiate a circle object
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    private int mode;
	private static final int EASY = 5;
	private static final int MIDDLE = 10;
	private static final int INFERNAL = 15;
    
   
    //setters and getters
    
    
    /**
	* 
	* Getter method for the ArrayList<Bomb>
	* @return an Array
	*/
    public ArrayList<Bomb> getBomb(){
    	return this.bombs;
    }
    
    /**
	* 
	* Setter method for the ArrayList<Bomb>
	*/
    public void setBomb(ArrayList<Bomb> bomb){
    	this.bombs = bomb;
    }
    
    /**
	* 
	* Getter method for the mode
	* @return mode number
	*/
    public int getMode() {
    	return this.mode;
    }  
	

	/**
	* 
	* Getter method for the width of the window property
	* @return the width of the window
	*/
    public int getWide() {
    	return CircleSliding.wide;
    }
    
    /**
	* 
	* Getter method for the height of the window property
	* @return the height of the window
	*/
    public int getHeights() {
    	return CircleSliding.heights;
    }
    
    /**
	* 
	* Getter method for the score property
	* @return the user's score
	*/
    public int getScore() {
    	return this.score;
    }
    
    /**
	* 
	* Getter method for the speed property
	* @return the speed of the circle
	*/
    public float getSpeed() {
    	return this.speed;
    }
    
    /**
	* 
	* Setter method for the circle speed property
	* @param speed the amount of the speed changes
	*/
    public void setSpeed(float speed) {
    	if (this.getSpeed()+speed==(float)0.5 || this.getSpeed()+speed==(float)1 || this.getSpeed()+speed==(float)3) {
    		this.speed = this.getSpeed()+speed;
    	}
    }
    
    /**
	* 
	* Setter method for the user score property
	* @param score the amount of the score changes 
	*/
    public void setScore(int score) {
    	if (this.getScore()+score>=0) this.score += score;	
		else {
			System.out.println("Opps!You lost all your scores. Bye!");
			exit();
			System.out.println("Thank you for playing! Your score is 0");
		}
    }
    
    
    /**
	* 
	* Setter method for the user life property
	* @param score the amount of the life changes 
	*/
    public void setLife(int life) {
    	if (CircleSliding.life + life >=0) CircleSliding.life += life;
    	else {
    		this.exit();
        	System.out.println("Your score is "+this.getScore()+".");
        	System.out.println("Thank you for playing!");
    	}
    }
    
    public int matchMode(int mode) {
    	switch(mode) {
    		case 1:
    		case 4:
    			return CircleSliding.EASY;
    		case 2:
    			return CircleSliding.MIDDLE;
    		case 3:
    			return CircleSliding.INFERNAL;
    		default:
    			return 1;
    	}
    }
    

	/**
	 * main method that display game instructions to the user
	 */
    public static void main(String[] args) {
    	
    	//overall instruction of the game
    	System.out.println("Hello! Welcome to the fake aim hero!");
    	System.out.println("In the game you have to hit the white circle to score. If you hit the red circle, you will lose one life. If you hit the bomb (black circle), you will lose all life and the game is over.");
    	System.out.println("If you miss one, you will lose one life. If you lose all lives, the game is over");
    	System.out.println("For every ten points you score, you will get one extra life as reward!");
    	System.out.println();
    	System.out.println("Your score and your live are shown on the left right side of the window");
    	System.out.println("If you are bored playing this, you can click the \"Click Me\" icon to exit");
    	
    	//Setting up the window and mode of game
    	
        PApplet.main("edu.nyu.cs.yj1351.CircleSliding");
    }
    
	/**
	 * take input from user to initialize the game settings
	 */
    public void settings() {
    	
    	// Set up window and mode
    	System.out.println("Which resolution you would like choose?");
    	System.out.println("(1):1280*720   (2):1920*1080   (3):2560*1440  (4):fullscreen");
    	String choice = userInput.next();
    	while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) {
    		System.out.println("Sorry, invaild input.");
    		System.out.println("Which resolution you would like choose?");
        	System.out.println("(1):1280*720   (2):1920*1080   (3):2560*1440  (4):fullscreen");
    		choice = userInput.next();	
    	}
    	
    	if (choice.equals("1")) {
    		wide = 1280;
    		heights = 720;
    		centerX = 640;
    		centerY = 360;
    		photoSpeed = 50;
    		photoWide = wide/5;
    		photo2Wide = wide*3/5;
    		photoHeight = heights;
            photo2Height = 0;
    		photo =  this.loadImage("src/photoSmall.png");
        	photo2 =  this.loadImage("src/photo2Small.png");
        	
    	}
    	else if (choice.equals("2")) {
    		wide = 1920;
    		heights = 1080;
    		centerX = 960;
    		centerY = 540;
    		photoSpeed = 60;
    		photoWide = wide/6;
    		photo2Wide = wide*3/5;
    		photoHeight = heights;
            photo2Height = 0;
    		photo =  this.loadImage("src/photo.png");
        	photo2 =  this.loadImage("src/photo2.png");
    	}
    	else if (choice.equals("3")) {
    		wide = 2560;
    		heights = 1440;
    		centerX = 1280;
    		centerY = 720;
    		photoSpeed = 70;
    		photoWide = wide/5;
    		photo2Wide = wide*3/5;
    		photoHeight = heights;
            photo2Height = 0;
    		photo =  this.loadImage("src/photo.png");
        	photo2 =  this.loadImage("src/photo2.png");
    	}
    	else {
    		
    		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    		wide = (int) screenSize.getWidth();
    		heights = (int) screenSize.getHeight();
    		fullScreen();
    		photoSpeed = 60;
    		photoWide = wide/5;
    		photo2Wide = wide*3/5;
    		photoHeight = heights;
            photo2Height = 0;
            photo =  this.loadImage("src/photo.png");
        	photo2 =  this.loadImage("src/photo2.png");
    	}
    	
    	System.out.println("Which mode you would like to choose?");
    	System.out.println("(1):Introduction to Programming Mode   (2):Introduction to Computer Science Mode   (3):Data Structure Mode ");
    	String modeChoice = userInput.next();
    	while (!modeChoice.equals("1") && !modeChoice.equals("2") && !modeChoice.equals("3") && !modeChoice.equals("4")) {
    		System.out.println("Sorry, invaild input.");
    		System.out.println("Which mode you would like choose?");
        	System.out.println("(1):Introduction to Programming Mode   (2):Introduction to Computer Science Mode   (3):Data Structure Mode ");
        	modeChoice = userInput.next();
    	}
    	if (modeChoice.equals("1")) {
    		life = 10;
    		speed =(float) 0.5;
    		mode = 1;
    		
    	}
    	else if (modeChoice.equals("2")) {
    		life = 5;
    		speed =(float) 1;
    		mode = 2;
    		
    	}
    	else if (modeChoice.equals("3")) {
    		life = 1;
    		speed =(float) 3;
    		mode = 3;
    		
    	}
    	else {
    		System.out.println("Cheat mode on!");
    		life = 99;
    		speed =(float) 1;
    		mode = 1;
    		
    	}
    	
    	
    		
    	button = this.loadImage("src/button.png");
    	
        this.size(CircleSliding.wide, CircleSliding.heights);   
        
        
    }
    
    /**
	 * set up the bomb objects that needed to be displayed on the screen
	 */
    public void setup() {
    	int result = this.getMode();
    	for(int i=0; i<this.matchMode(result);i++){
    		Bomb bomb = new Bomb(this,circle);
    		this.bombs.add(bomb);
    	}   	
    }
    
    
    /**
	 * display the game content to the user and let the user interact with the objects in the game
	 */
    public void draw() {
    	
    	
    	if (showPhoto == true) {
    		
    		photoHeight-=photoSpeed;
    		photoSpeed -= (float)0.8;
    		photo2Height+=photoSpeed;
    		photoSpeed -= (float)0.8;
    		
    	}
    	
    	//set up the UI for the game
        this.background(87, 46, 140); // fill screen with color
        this.image(button, 0, 0);
        this.image(photo,photoWide,photoHeight);
	    this.image(photo2,photo2Wide,photo2Height-639);
        this.circle.drawToScreen(this);
       
        this.circle.lifeChecker(this);
       
        
        for (Bomb n: bombs) {
	    	n.drawBomb(this);
        }


        
        textSize(20);
        fill(0, 255, 255, 255);
        text("Score:",wide-200,20);
        textSize(20);
        fill(0, 255, 255, 255);
        text(score,wide-30,20);
        textSize(20);
        fill(0, 255, 255, 255);
        text("Life:",wide-200,40);
        textSize(20);
        fill(0, 255, 255, 255);
        text(life,wide-30,40);
        
    }
          
    /**
	 * detect the mouse clicking from user and compute their scores
	 */
	public void mouseClicked() { 
		
		if (score == 10) {
			showPhoto = true;
		}

		this.circle.afterClicking(this);
		//what happen when click the exit button
		if (this.mouseX > 0 && this.mouseX < this.button.width && this.mouseY > 0 && this.mouseY < this.button.height) {
			exit();
        	System.out.println("Your score is "+score+".");
        	System.out.println("Thank you for playing!");
		}
		for (Bomb n: bombs) this.circle.checkBombClicking(this,n);
		
	}
}