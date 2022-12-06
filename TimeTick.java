import java.util.TimerTask;
import java.io.IOException;
/**
 * TimeTick.java is used to run each time-tick of the airport simulation
 * @author Jared Rosenberger
 * @version 12/05/22
 * TimeTick.java
 * Fall 2022
 */
public class TimeTick extends TimerTask {
	//Instance variables
	private static Airport myAirport;//The airport to run the simulation with
	private static int timeStamp;//The current time tick, and the length of the simulation
	
	
	//Constructor
	/**
	 * No-argument constructor initializes class instance variables
	 */
	public TimeTick() {
		super();
		myAirport = new Airport();
		timeStamp = 0;
	}//end constructor
	
	
	//Other class methods
	/**
	 * run carries out the simulation by being repeatedly called	 
	 */
	public void run() {
		//Clear previous output
		clear();
		if(timeStamp == 0) {
			System.out.println(myAirport);
			timeStamp++;
		}
		else {
			//Print timeStamp
			System.out.println("TimeStamp: Minute " + timeStamp++ + "\n");
			//Chance to generate plane
			myAirport.generatePlane();
			//Update distance from airport
			myAirport.updateDistance();
			//Print planes on approach
			myAirport.printApproach();
			//Print planes ready to land
			myAirport.printLanding();
			//Chance to generate emergencies
			myAirport.generateEmergency();
			//Update and print runway status
			myAirport.getRunwayStatus(timeStamp);
			//Print number of landed planes
			myAirport.printLanded();
			//land planes if able
			myAirport.landPlane(timeStamp);
		}
	}//end run
	
	/**
	 * clear clears the cmd prompt of text so the screen doesn't keep scrolling
	 */
	public static void clear() {
		try {
			if(System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
        } 
		catch(IOException | InterruptedException ex) {}
    }//end clear
}//end TimeTick