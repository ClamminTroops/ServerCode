package edu.calvin.cs262.lab09;

/**
 * This class implements a Player Data-Access Object (DAO) class for the Player relation.
 * This provides an object-oriented way to represent and manipulate player "objects" from
 * the traditional (non-object-oriented) Monopoly database.
 *
 */
public class Matches {


	private int dogID;


    public Matches() {
        // The JSON marshaller used by Endpoints requires this default constructor.
    }
    public Matches(int dogID) {
		this.dogID=dogID;
	
    }
		// DOG ID
    public int getdogID() {
        return this.dogID;
    }
    public void setDogID(int id) {
        this.dogID = id;
    }

}
