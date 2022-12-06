/**
 * Plane.java represent planes in the airport simulation
 * @author Jared Rosenberger
 * @version 12/06/22
 * Plane.java
 * Fall 2022
 */
public class Plane implements Comparable<Plane>{
	//Instance Variables
	private String flightID;//The unique ID of this plane
	private int milesFromAirport;//The distance a plane is from the airport at any given time
	private int timeStamp;//The current time of the plane. Used to see if the plane was just initialized or not
	private boolean emergency;//A boolean representation of whether or not a plane has an emergency
	
	
	//Constructors
	/**
	 * No-argument constructor initializes instance variables
	 */
	public Plane() {
		flightID = "";
		milesFromAirport = 0;
		timeStamp = 0;
		emergency = false;
	}//end constructor
	
	/**
	 * Two-argument constructor allows the flight ID and distance from airport to be passed in
	 * @param flightID is the unique ID of the plane
	 * @param distance is the distance from the airport
	 */
	public Plane(String flightID, int distance) {
		this.flightID = flightID;
		milesFromAirport = distance;
		timeStamp = 0;
		emergency = false;
	}//end constructor
	
	
	//Accessors and Mutators
	/**
	 * getFlight returns the current value of flightID
	 * @return the current value of flightID
	 */
	public String getFlight() {
		return flightID;
	}//end getFlight
	
	/**
	 * setFlight allows the user to manually set the value of flightID
	 * @param flightID is the value of flightID as passed into setFlight
	 */
	public void setFlight(String flightID) {
		this.flightID = flightID;
	}//end setFlight
	
	/**
	 * getDistance returns the current distance from the airport
	 * @return the current value of milesFromAirport
	 */
	public int getDistance() {
		return milesFromAirport;
	}//end getDistance
	
	/**
	 * setDistance allows the user to manually set the distance from the airport
	 * @param distance is the new value of milesFromAirport as passed into setDistance
	 */
	public void setDistance(int distance) {
		milesFromAirport = distance;
	}//end setDistance
	
	/**
	 * getTime returns the current time of the plane
	 * @return the current value of timeStamp
	 */
	public int getTime() {
		return timeStamp;
	}//end getTime
	
	/**
	 * update increments the value of timeStamp by 1
	 */
	public void update() {
		timeStamp++;
	}//end update
	
	/**
	 * getEmergency returns the current value of emergency
	 * @return the current value of emergency
	 */
	public boolean getEmergency() {
		return emergency;
	}//end getEmergency
	
	/**
	 * emergency sets the value of emergency to true
	 */
	public void emergency() {
		emergency = true;
	}//end setEmergency
	
	
	//Other class methods
	/**
	 * emergencyPriority changes the distance from the airport if emergency is true, in order to simplify the compareTo
	 */
	public void emergencyPriority() {
		if(emergency)
			milesFromAirport = 7;
	}//end emergencyPriority
	
	/**
	 * compareTo allows two Planes to be compared to each other based on their distance from the airport
	 */
	public int compareTo(Plane b) {
		emergencyPriority();
		b.emergencyPriority();
		if(milesFromAirport < b.milesFromAirport)
			return -1;
		else if(milesFromAirport > b.milesFromAirport)
			return 1;
		else
			return 0;
	}//end compareTo
}//end Plane