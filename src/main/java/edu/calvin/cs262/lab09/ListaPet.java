package edu.calvin.cs262.lab09;

/**
 * This class implements a Player Data-Access Object (DAO) class for the Player relation.
 * This provides an object-oriented way to represent and manipulate player "objects" from
 * the traditional (non-object-oriented) Monopoly database.
 *
 */
public class ListaPet {

	private int personid;
	private int dogid;
	private String name;
	private String gender;
	private String breed;
	private String energylevel;
	private String housetrained;
	private String size;
	private String photo;



    public ListaPet() {
        // The JSON marshaller used by Endpoints requires this default constructor.
    }
    public ListaPet(int dogid,int personID, String name,String gender,String breed,String energylevel,String housetrained,String size,String imageurl) {
		this.dogid = dogid;
		this.personid = personID;
		this.name=name;
		this.gender=gender;
		this.breed=breed;
		this.energylevel=energylevel;
		this.housetrained=housetrained;
		this.size=size;
		this.photo=imageurl;
	
    }
    	// DOG ID
    public int getdogid() {
        return this.dogid;
    }
    public void setdogid(int dogid) {
        this.dogid = dogid;
    }
		// DOG ID
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
			// NAME 
	public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    	// BIOGRAPHY  
	public String getBreed() {
        return this.breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
         	// AGE  
	public String getenergylevel() {
        return this.energylevel;
    }
    public void setenergylevel(String energylevel) {
        this.energylevel = energylevel;
    }
        	// SEX  
	public String gethousetrained() {
        return this.housetrained;
    }
    public void sethousetrained(String housetrained) {
        this.housetrained = housetrained;
    }
            	// personID  
	public String getSize() {
        return this.size;
    }
    public void setSize(String size) {
        this.size = size;
    }
               	// prices  
	public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
                   	// prices  
	public int getPersonID() {
        return this.personid;
    }
    public void setPersonID(int personid) {
        this.personid = personid;
    }



}