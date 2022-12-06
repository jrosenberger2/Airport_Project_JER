import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
/**
 * Airport.java represents the airport in the airport simulation
 * @author Jared Rosenberger
 * @version 12/06/22
 * Airport.java
 * Fall 2022
 */
public class Airport {
	//Instance variables
	private Queue<Plane> onApproach;//A queue of the Planes approaching the airport
	private PriorityQueue<Plane> readyToLand;//A priority queue of the planes ready to land, organized by distance from the airport
	private LinkedList<Plane> landed;//A queue of all of the planes that have landed at the airport
	private Random generator;//A random object used to generate random numbers
	private int flightCount;//a count of all of the flights in the simulation, used for flight IDs
	private Runway runOne;//The first runway in the airport
	private Runway runTwo;//The second runway in the airport
	private Runway runThree;//The third runway in the airport
	
	
	//constructor
	/**
	 * No-argument constructor initializes instance variables
	 */
	public Airport() {
		onApproach = new LinkedList<Plane>();
		readyToLand = new PriorityQueue<Plane>();
		landed = new LinkedList<Plane>();
		generator = new Random();
		flightCount = 1;
		runOne = new Runway();
		runTwo = new Runway();
		runThree = new Runway();
	}//end constructor
	
	
	//Accessors and Mutators
	/**
	 * addToApproach adds a plane to onApproach
	 * @param p is the plane being added to onApproach
	 * @return true if successful, false otherwise
	 */
	public void addToApproach(Plane p) {
		onApproach.add(p);
	}//end addToApproach
	
	/**
	 * addToLanding removes a plane from onApproach and adds it to readyToLand
	 * @return true if successful, false otherwise
	 */
	public void addToLanding() {
		readyToLand.add(onApproach.remove());
	}//end addToLanding
	
	
	//Generators
	/**
	 * generatePlane has a chance to generate a new plane every time-tick, that is controlled by Random number generation
	 */
	public void generatePlane() {
		int planeGen = generator.nextInt(100)+1;
		if(planeGen % 2 ==0) {
			Plane flight = new Plane(""+flightCount, generator.nextInt(5,10)*8);
			addToApproach(flight);
			flightCount++;
		}
	}//end generatePlane
	
	/**
	 * generateEmergency has a chance to generate an emergency for every plane every time-tick , that is controlled by Random number generation
	 */
	public void generateEmergency() {
		Random generator = new Random();
		int emergencyGen;
		String id;
		//for-each for planes on approach
		for(Plane p : onApproach) {
			emergencyGen = generator.nextInt(100)+1;
			if(emergencyGen % 30 == 0 && !p.getEmergency()) {
				p.emergency();
				System.out.println("Flight " + p.getFlight() + " is experiencing an emergency!\n\n");
				id = p.getFlight();
				p.setFlight(id + "(E)");
			}//end if
		}//end for-each
		//for each for planes in the landing queue
		for(Plane p : readyToLand) {
			emergencyGen = generator.nextInt(100)+1;
			if(emergencyGen % 30 == 0 && !p.getEmergency()) {
				p.emergency();
				System.out.println("Flight " + p.getFlight() + " is experiencing an emergency!\n\n");
				id = p.getFlight();
				p.setFlight(id + "(E)");
			}//end if
		}//end for-each
	}//end generateEmergency
	
	
	//Other class methods
	/**
	 * updateDistance decrements the distance of all planes that are not freshly initialized or ready to land
	 */
	public void updateDistance() {
		if(!onApproach.isEmpty()) {
			Plane[] update = (Plane[]) onApproach.toArray(new Plane[onApproach.size()]);
			for(Plane p : update) {
				if(p.getTime() > 0 && p.getDistance() > 8) {
					p.setDistance(p.getDistance()-8);
				}
				else if(p.getDistance() == 8)
					readyToLand.add(onApproach.remove());
				else
					p.update();
			}//end for-loop
		}//end if statement
	}//end updateDistance
	
	/**
	 * getRunwayStatus updates the three runways and prints their statuses using the Runway toString
	 * @param time is the current time-tick
	 */
	public void getRunwayStatus(int time) {
		runOne.update(time);
		runTwo.update(time);
		runThree.update(time);
		System.out.println("Runway 1 " + runOne);
		System.out.println("Runway 2 " + runTwo);
		System.out.println("Runway 3 " + runThree + "\n");
	}//end getRunwayStatus
	
	/**
	 * landPlane lands a plane at the airport by removing an element from readyToLand and calling the land method on an empty runway
	 */
	public void landPlane(int time) {
		Plane temp = new Plane();
		while(!readyToLand.isEmpty() && (runOne.isClear() || runTwo.isClear() || runThree.isClear())) {
			//check and land on runway 1
			if(runOne.isClear()) {
				temp = readyToLand.remove();
				runOne.land(temp, time);
				landed.add(temp);
			}
			//check and land on runway 2
			else if(runTwo.isClear()) {
				temp = readyToLand.remove();
				runTwo.land(temp, time);
				landed.add(temp);
			}
			//check and land on runway 3
			else if(runThree.isClear()) {
				temp = readyToLand.remove();
				runThree.land(temp, time);
				landed.add(temp);
			}
		}//end while-loop
	}//end landPlane
	
	/**
	 * printAppraoch prints the number of planes on approach as well as each of their flight numbers and their distance from the airport
	 */
	public void printApproach() {
			System.out.println("Planes approaching the Airport: " + onApproach.size());
			for(Plane a : onApproach) {
				System.out.println("Flight: " + a.getFlight() + "\tDistance: " + a.getDistance() + " Miles from the Airport.");
			}
			System.out.println("\n");
	}//end printApproach
	
	/**
	 * printLanding prints the number of planes ready to land as well as each of their flight numbers and their distance from the airport
	 */
	public void printLanding() {
		System.out.println("Planes ready to land: " + readyToLand.size());
		/*
		if(!readyToLand.isEmpty()) {
			Plane[] print = (Plane[]) readyToLand.toArray(new Plane[onApproach.size()]);
			for(int i=0; i<print.length; i++) {
				System.out.println("Flight: " + print[i].getFlight() + "\tDistance: Circling 8 miles from the Airport");
			}
			System.out.println("\n");
		}
		*/
		for(Plane p : readyToLand) {
			System.out.println("Flight: " + p.getFlight() + "\tDistance: Circling 8 miles from the Airport");
		}
		System.out.println("\n");
	}//end printLanding
	
	/**
	 * printLanded prints the number of planes that have landed at the airport
	 */
	public void printLanded() {
		System.out.println("Total planes landed: " + landed.size());
		/*
		int count = 1;
		for(Plane p : landed) {
			System.out.print("Flight " + p.getFlight() + "\t");
			if(count % 5 == 0)
				System.out.println("\n");
			count++;
		}
		*/
	}//end printLanded
	
	public String toString() {
		String result = "";
		result += "TimeStamp: Minute 0\n";
		result += "\nPlanes approaching the Airport: " + onApproach.size() + "\n\n";
		result += "\nPlanes ready to land: " + readyToLand.size() + "\n\n";
		result += "\nRunway 1 is clear\n\nRunway 2 is clear\n\nRunway 3 is clear\n\n";
		result += "\nTotal planes landed: " + landed.size();
		return result;
	}
}//end Airport