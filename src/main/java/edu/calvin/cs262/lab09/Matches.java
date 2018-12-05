package edu.calvin.cs262.lab09;

/**
 * This class implements a Player Data-Access Object (DAO) class for the Player relation.
 * This provides an object-oriented way to represent and manipulate player "objects" from
 * the traditional (non-object-oriented) Monopoly database.
 *
 */
public class Matches {


	
	private String DogName; 
	private String photo;
	private String description;
	


    public Matches() {
        // The JSON marshaller used by Endpoints requires this default constructor.
    }
    public Matches(String dName, String photo, String description) {
		this.DogName=dName;
		this.photo=photo;
		this.description=description;	
    }
    

    // DOG Photo
    public String getDogName() {
        return this.DogName;
    }
    public void setDogName(String data) {
        this.DogName = data;
    }
    
    public String getProfilePicture() {
        return this.photo;
    }
    public void setProfilePicture(String name) {
        this.photo = name;
    }
    // DOG breed
    public String getDogBreed() {
        return this.description;
    }
    public void setDogBreed(String breed) {
        this.description = breed;
    }
    

}
