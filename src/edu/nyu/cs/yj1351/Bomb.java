package edu.nyu.cs.yj1351;

/**
 * Bomb object that kill user if being hit
 * 
 * @author Yaojia Ju, Yizhou Wan
 * @version 1.0
 * 
 */


public class Bomb extends Circle{
	private CircleSliding cs;
	private Circle circle;
	private static final int R=0;
	private static final int G=0;
	private static final int B=0;
	private static final int SIZE = 50;
			
	//non-args constructor
	public Bomb(){
	}
	
	//overload constructor
	public Bomb(CircleSliding cs, Circle circle) {
		this.cs = cs;
		this.circle = circle;
		this.setBombColor();
		this.setBombPosition(cs);
	}
	//setters and getters
    
    /**
	* 
	* Getter method for the bomb size Property
	* @return int size of the bomb
	*/
	public static int getSize() {
		return SIZE;
	}
	
	/**
	* 
	* Getter method for the RGB Property
	* @return the RGB value of the red color
	*/
	public static int getR() {
		return R;
	}

	/**
	* 
	* Getter method for the RGB Property
	* @return the RGB value of the green color
	*/
	public static int getG() {
		return G;
	}

	/**
	* 
	* Getter method for the RGB Property
	* @return the RGB value of the blue color
	*/
	public static int getB() {
		return B;
	}

	/**
	* 
	* Setter method for the bomb color
	*/
	public void setBombColor() {
		this.setCircleR(Bomb.R);
		this.setCircleG(Bomb.G);
		this.setCircleB(Bomb.B);
	}
	
	/**
	* 
	* Setter method for the bomb position (x and y coordinates)
	* @param cs the CircleSliding object
	*/
	public void setBombPosition(CircleSliding cs) {
		int x = this.randomIndex(cs);
		int y = this.randomIndexG(cs);
		this.setX(x,cs);
		this.setY(y, cs);
	}
	
	/**
   	* 
   	* draw the bomb on the screen
   	* @param cs the CircleSliding object 
   	*/
	public void drawBomb(CircleSliding cs) {
		cs.fill(Bomb.getR(),Bomb.getG(),Bomb.getB());
		cs.ellipse(this.getX(),this.getY(),Bomb.getSize(),Bomb.getSize());
	}	
}
