package model;

public class Answer {

	public String type = "Answer";
	private String name;
	private String animal;
	private String location;
	private String object;
	
	public Answer(String name, String animal, String location, String object){
		this.name = name;
		this.animal = animal;
		this.location = location;
		this.object = object;
	}

//Getters And Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

}
