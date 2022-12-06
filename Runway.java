/**
 * Runway.java is a representation of the airports runways in the simulation 
 * @author Jared Rosenberger
 * @version 12/5/22
 * Runway.java
 * Fall 2022
 */
public class Runway {
	//Instance Variables
	private boolean clear;//Boolean flag to show if the runway is open or not
	private Plane plane;//The plane landing on the runway
	private int endTime;//The time-tick that the runway will be clear
	private int currentTime;//the current time-tick
	
	
	//Constructor
	/**
	 * No-argument constructor initializes class instance variables
	 */
	public Runway() {
		clear = true;
		plane = new Plane();
		endTime = 0;
		currentTime = 0;
	}//end constructor
	
	
	//Accessors and Mutators
	/**
	 * isClear returns the current value of clear
	 * @return the current value of clear
	 */
	public boolean isClear() {
		return clear;
	}//end isClear
	
	/**
	 * land lands a plane on the runway
	 * @param plane is the plane landing on the runway
	 * @param timeStamp is the time-tick that the plane landed
	 */
	public void land(Plane plane, int timeStamp) {
		this.plane = plane;
		clear = false;
		endTime = timeStamp + 5;
	}//end land
	
	/**
	 * getPlane returns the plane landing on the runway
	 * @return the current value of plane
	 */
	public Plane getPlane() {
		return plane;
	}//end getPlane
	
	
	//Other class methods
	/**
	 * update clears runways and resets variables 5 time-ticks after a landing
	 * @param timeStamp is the current time-tick
	 */
	public void update(int timeStamp) {
		currentTime = timeStamp;
		if(currentTime == endTime) {
			clear = true;
			endTime = 0;
			plane = new Plane();
		}
	}//end update
	
	/**
	 * toString returns a string representation of the runway, including whether or not it's clear and the time left until it's clear
	 */
	public String toString() {
		String result = "";
		if(isClear())
			result += "is clear\n";
		else
			result += ("is busy landing flight " + plane.getFlight() + ", " + (endTime-currentTime) + " minutes left unitl runway is clear.\n");
		return result;
	}//end toString
}//end Runway.java