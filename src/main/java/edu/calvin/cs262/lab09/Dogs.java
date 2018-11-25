package edu.calvin.cs262.lab09;

/**
 * This class implements a Player Data-Access Object (DAO) class for the Player relation.
 * This provides an object-oriented way to represent and manipulate player "objects" from
 * the traditional (non-object-oriented) Monopoly database.
 *
 */
public class Dogs {

	private int dogid;
	private String name;
	private String bio;
	private int age;
	private String sex;
	private int personId;
	private Float price;
	private int breedid;
	


    public Dogs() {
        // The JSON marshaller used by Endpoints requires this default constructor.
    }
    public Dogs(int dogid,String name, String bio, int age, String sex, int personID, Float price, int breeid) {
		this.dogid=dogid;
		this.name=name;
		this.bio=bio;
		this.age=age;
		this.sex=sex;
		this.personId=personID;
		this.price=price;
		this.breedid=breeid;
    }
		// DOG ID
    public int getdogId() {
        return this.dogid;
    }
    public void setdogId(int id) {
        this.dogid = id;
    }
			// NAME 
	public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    	// BIOGRAPHY  
	public String getBio() {
        return this.bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
         	// AGE  
	public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
        	// SEX  
	public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
            	// personID  
	public int getpersonID() {
        return this.personId;
    }
    public void setPersonId(int PersonID) {
        this.personId = PersonID;
    }
               	// prices  
	public Float getPrice() {
        return this.price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
                 	// breedid  
	public int getBreedID() {
        return this.breedid;
    }
    public void setBreedID(int breedID) {
        this.breedid = breedID;
    }






}
