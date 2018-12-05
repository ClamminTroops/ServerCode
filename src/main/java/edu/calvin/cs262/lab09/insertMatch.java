package edu.calvin.cs262.lab09;

/**
 * This class implements a Player Data-Access Object (DAO) class for the Player relation.
 * This provides an object-oriented way to represent and manipulate player "objects" from
 * the traditional (non-object-oriented) Monopoly database.
 *
 */
public class insertMatch {

	private int dogID;
	private int personID;
	
    public insertMatch() {
        // The JSON marshaller used by Endpoints requires this default constructor.
    }
    public insertMatch(int dogid,int personID) {
		this.dogID=dogid;
		this.personID=personID;

    }
		// DOG ID
    public int getdogID() {
        return this.dogID;
    }
    public void setdogID(int id) {
        this.dogID = id;
    }
			// NAME 
	public int getpersonID() {
        return this.personID;
    }
    public void setpersonID(int id) {
        this.personID = id;
    }


}
