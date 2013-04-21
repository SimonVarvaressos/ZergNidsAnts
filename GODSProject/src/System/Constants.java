package System;

/////////////////////////////////////////////
//Cette classe contient les constantes comme la vitesse des unités, nombre max de Swarmlings par Swarmide,etc...
//Cela facilite le changement de ces variables
/////////////////////////////////////////////

public class Constants {

	public static final int swarmlingsMax = 5;
	public static final int swarmidesMax = 3;
	public static final int swarmodonsMax = 4;
	
	public static final float swarmlingSpeed = 1.0f;
	public static final float swarmideSpeed = 0.5f;
	public static final float swarmodonSpeed = 0.25f;
	
	public static final float terranlingSpeed = 0.9f;
	public static final float terranideSpeed = 0.5f;
	public static final float terranodonSpeed = 0.2f;
	
	public static final int swarmlingCost = 200;
	public static final int swarmideCost = 750;
	public static final int swarmodonCost = 3500;
	
	public static final String NO_ENERGY = "Insufficient energy";
	
	public static final String NO_ODON = " Max amount of swarmodon reached ";
	public static final String NO_IDE = " Max amount of swarmide reached ";
	public static final String NO_ING = " Max amount of swarmling reached ";
}
