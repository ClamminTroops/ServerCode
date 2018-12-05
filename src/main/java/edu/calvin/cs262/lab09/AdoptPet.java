package edu.calvin.cs262.lab09;

public class AdoptPet {
	
	private int dogid;
	private String photo;
	private String name;
	private String breed;
	private String Gender;

    public AdoptPet() {
        // The JSON marshaller used by Endpoints requires this default constructor.
    }
    public AdoptPet(int dogid, String photo, String name,String Gender, String breed) {
    	this.dogid = dogid;
		this.photo=photo;
		this.name=name;
		this.breed=breed;
		this.Gender=Gender;
    }
    
    public int getdogID() {
        return this.dogid;
    }
    public void setdogID(int id) {
        this.dogid = id;
    }
		
    public String getphoto() {
        return this.photo;
    }
    public void setphoto(String id) {
        this.photo = id;
    }
		
	public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    	 
	public String getBreed() {
        return this.breed;
    }
    public void setBreed(String bio) {
        this.breed = bio;
    }
    public String getGender() {
        return this.Gender;
    }
    public void setGender(String bio) {
        this.Gender = bio;
    }


}