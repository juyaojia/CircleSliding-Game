package edu.nyu.cs.yj1351;

/**
 * A Circle Object that serve as the aim the user needs to hit in the game
 * 
 * @author Yaojia Ju,Yizhou Wan
 * @version 1.0
 * 
 */
import java.util.Random;
import edu.nyu.cs.yj1351.CircleSliding;
import edu.nyu.cs.yj1351.Bomb;

public class Circle {
    private int x = 250; // starting x position of ellipse
    private int y = 150; // starting y position of ellipse
    private float d = 0; //the size of the circle
    private float e = 0; //the size of the circle
    private int circleR = 255;
    private int circleG = 255;
    private int circleB = 255;
    private Random rand = new Random();
    private Random randCircle = new Random();
    private boolean flag = false; //control circle become larger or smaller
    private int index;
    private int indexG;
    private CircleSliding cs;
    private Bomb bomb;
	
    
    /**
	* no-args constructor. All Circle properties remain with their default value
	*/
	public Circle(){
	}
	
	//setters and getters
	

	/**
	* 
	* Getter method for the x coordinate property
	* @return the x coordinate of the ellipse
	*/
    public int getX() {
    	return this.x;
    }
    

	/**
	* 
	* Getter method for the y coordinate property
	* @return the y coordinate of the ellipse
	*/
    public int getY() {
    	return this.y;
    }
    
    /**
   	* 
   	* Setter method for the x coordinate property
   	*/
    public void setX(int x, CircleSliding cs) {
    	if (x>=0 && x<=cs.getWide()) this.x = x; 
    }
    
    /**
   	* 
   	* Setter method for the y coordinate property
   	*/
    public void setY(int y, CircleSliding cs) {
    	if (y>=0 && y<=cs.getHeights()) this.y = y; 
    }
    
    /**
	* 
	* Getter method for the  RGB value of the red color
	* @return the RGB value of the red color
	*/
    public int getCircleR() {
    	return this.circleR;
    }
    
    /**
	* 
	* Getter method for the  RGB value of the green color
	* @return the RGB value of the green color
	*/
    public int getCircleG() {
    	return this.circleG;
    }
    
    /**
   	* 
   	* Getter method for the  RGB value of the blue color
   	* @return the RGB value of the blue color
   	*/
    public int getCircleB() {
    	return this.circleB;
    }
    
    /**
   	* 
   	* Setter method for the  RGB value of the red color
   	* @return the RGB value of the red color
   	*/
    public void setCircleR(int r) {
    	if (r>=0 && r<=255) this.circleR = r;
    }
    
    /**
   	* 
   	* Setter method for the  RGB value of the green color
   	* @return the RGB value of the green color
   	*/
    public void setCircleG(int g) {
    	if (g>=0 && g<=255) this.circleG = g;
    }
    
    /**
   	* 
   	* Setter method for the  RGB value of the blue color
   	* @return the RGB value of the blue color
   	*/
    public void setCircleB(int b) {
    	if (b>=0 && b<=255) this.circleB = b;
    }
    
    /**
   	* 
   	* Check how many life does the user have got left and display the message when the user lose all life.
   	* @param cs the CircleSliding object 
   	*/
    public void lifeChecker(CircleSliding cs) {
    	 if(CircleSliding.life ==0) {  
         	//fail condition
         	cs.exit();
         	System.out.println("Your score is "+cs.getScore()+".");
         	System.out.println("Thank you for playing!");
         }
     }

    /**
   	* 
   	* Draw an ellipse
   	* @param cs the CircleSliding object 
   	*/
    public void drawEllipse(CircleSliding cs) {
    	cs.fill(this.getCircleR(), this.getCircleG(), this.getCircleB()); // paint with white (R,G,B = 255,255,255)
		cs.ellipse(this.x, this.y, d, e); // draw ellipse with filled color
    }
    

    /**
   	* 
   	* Generate a random number for the next x coordinate
   	* @return a random number
   	*/
    public int randomIndex(CircleSliding cs) {
    	int m = rand.nextInt(cs.getWide()-100)+50;
    	return m;
    }
    
    /**
   	* 
   	* Generate a random number for the next y coordinate
   	* @return a random number
   	*/
    public int randomIndexG(CircleSliding cs) {
    	int n = rand.nextInt(cs.getHeights()-100)+50;
    	return n;
    }
    
    /**
   	* 
   	* change the color and position of the circle and draw it on the screen
   	* @param cs the CircleSliding object 
   	*/
	public void drawToScreen(CircleSliding cs) {
		this.drawEllipse(cs);
        if (flag == true){
        	this.d-=cs.getSpeed();
        	this.e-=cs.getSpeed();
        	
        	if (d <= 0) {  
        		// what happened if player miss the click
        		if(circleR == 255 && circleG ==0 &&  circleB == 0){
        			this.x = randomIndex(cs);
            		this.y = randomIndexG(cs);
            		
            		flag = false;
        			this.index = this.randCircle.nextInt(10);
                	this.indexG = this.randCircle.nextInt(25);
                	
                	if (index == 1) {
                		this.circleR = 255;
                	    this.circleG = 0;
                	    this.circleB = 0;
                	}
                	else if (indexG == 2) {
                		this.circleR = 0;
                	    this.circleG = 173;
                	    this.circleB = 67;
                	}
                	else {
                		this.circleR = 255;
                	    this.circleG = 255;
                	    this.circleB = 255;
                	}
                	flag = false;
        		}
        		else {
	        		CircleSliding.life -=1;
	        		this.x = randomIndex(cs);
	        		this.y = randomIndexG(cs);
	        		this.flag = false;
	        		this.index = this.randCircle.nextInt(10);
	            	this.indexG = this.randCircle.nextInt(25);
	            	if (index == 1) {
	            		this.circleR = 255;
	            	    this.circleG = 0;
	            	    this.circleB = 0;
	            	}
	            	else if (indexG == 2) {
	            		this.circleR = 0;
	            	    this.circleG = 173;
	            	    this.circleB = 67;
	            	}
	            	else {
	            		this.circleR = 255;
	            	    this.circleG = 255;
	            	    this.circleB = 255;
	            	}
        		}
        	}
        }
		
        else if (flag == false) {
        	this.d+=cs.getSpeed();
        	this.e+=cs.getSpeed();
        	if (this.d >= 100) flag = true;
        }
        
	}
	
	/**
   	* 
   	* Check if the user hit the bomb and display the message when the user lose all life.
   	* @param cs the CircleSliding object 
   	* @param bomb the Bomb object
   	*/
	public void checkBombClicking(CircleSliding cs, Bomb bomb) {
		if(cs.mouseX <= bomb.getX()+Bomb.getSize()/2 && cs.mouseX >= bomb.getX()-Bomb.getSize()/2 && cs.mouseY <= bomb.getY()+Bomb.getSize()/2 && cs.mouseY >= bomb.getY()-Bomb.getSize()/2) { 
			System.out.println("Opps! You hit the bomb and it exploded!");
			CircleSliding.life = 0;
			cs.exit();
		}
	}
	
	/**
   	* 
   	* keep track of the score and life the user has and generate new circles after the user clicks
   	* @param cs the CircleSliding object 
   	*/
	public void afterClicking(CircleSliding cs) {
		// what happen when the circle is clicked
		if(cs.mouseX <= this.x+d/2 && cs.mouseX >= this.x-d/2 && cs.mouseY <= this.y+e/2 && cs.mouseY >= this.y-e/2) { 
        	d = 0;// the circle disappear
        	e = 0;
        	this.x = rand.nextInt(cs.getWide()-100)+50; //new location for circle
    		this.y = rand.nextInt(cs.getHeights()-100)+50;
        	flag = false;
        	if(circleB == 255) {
        		cs.setScore(1);
        	}
        	else if(circleB == 67) {
        		CircleSliding.life += 1;
        		cs.setScore(1);
        	}
        	else {
        		CircleSliding.life -= 1;
        	}
        	
        	//change the speed and add life
        	if(cs.getScore() % 10 == 0 && cs.getScore() <=100) { 
        		CircleSliding.life += 1;
        		cs.setSpeed((float)0.5);
        	}
        	
        	//create potential red circle and green circle
        	index = randCircle.nextInt(10);
        	indexG = randCircle.nextInt(25);
        	if (index == 1) {
        		this.circleR = 255;
        	    this.circleG = 0;
        	    this.circleB = 0;
        	}
        	else if (indexG == 2) {
        		this.circleR = 0;
        	    this.circleG = 173;
        	    this.circleB = 67;
        	}
        	else {
        		this.circleR = 255;
        	    this.circleG = 255;
        	    this.circleB = 255;
        	}
        }

	}
	
}
