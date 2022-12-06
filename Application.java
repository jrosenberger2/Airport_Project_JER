import java.util.Timer;
/**
 * Appliaction.java runs the airport simulation using a Timer
 * @author Jared Rosenberger
 * @version 12/05/22
 * Application.java
 * Fall 2022
 */
public class Application {
	
	public static void main(String[] args) {
		Timer myTimer = new Timer();
		TimeTick myTick = new TimeTick();
		myTimer.schedule(myTick, 500, 2850);
	}//end main
}//end Application