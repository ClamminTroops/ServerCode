package edu.calvin.cs262.lab09;

/**
 * This class implements a Player Data-Access Object (DAO) class for the Player relation.
 * This provides an object-oriented way to represent and manipulate player "objects" from
 * the traditional (non-object-oriented) Monopoly database.
 *
 */
public class Person {

	private int personID;
	private String loginid;
	private String password;
	private String profilePhoto="null";
	private String name="null";
	private String phonenum="null";
	private String email="null";
	private Double rating=0.0;
	private int numRatings=0;
	private String location="null";
	


    public Person() {
        // The JSON marshaller used by Endpoints requires this default constructor.
    }
    public Person(int PersonID,String loginid, String password, String profilePhoto, String name,String phonenum, String email, Double rating, int numRatings, String Location) {
		this.personID=PersonID;
		this.loginid = loginid;
		this.password=password;
		this.profilePhoto=profilePhoto;
		this.name=name;
		this.phonenum=phonenum;
		this.email=email;
		this.rating=rating;
		this.numRatings=numRatings;
		this.location = Location;
    }
		// PersonID
    public int getpersonID() {
        return this.personID;
    }
    public void setPersonID(int id) {
        this.personID = id;
    }
    	// LoginID
    public String getLoginID() {
        return this.loginid;
    }
    public void setLoginID(String id) {
        this.loginid = id;
    }
    
			// Password 
	public String getPassword() {
        return this.password;
    }
    public void setPassword(String pasword) {
        this.password = pasword;
    }
    	// Profile Picture (raw data)  
	public String getProfilePhoto() {
        return this.profilePhoto;
    }
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
         	// Name   
	public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
        	// Phone Number  
	public String getPhoneNum() {
        return this.phonenum;
    }
    public void setPhoneNum(String phonenum) {
        this.phonenum = phonenum;
    }
            	// Email  
	public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
               	// Rating  
	public Double getRating() {
        return this.rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
                 	// Number of Ratings  
	public int getBreedID() {
        return this.numRatings;
    }
    public void setBreedID(int numRatings) {
        this.numRatings = numRatings;
    }

               	// Location of Ratings  
	public String getLocation() {
        return this.location;
    }
    public void setLocation(String numRatings) {
        this.location = numRatings;
    }





}
