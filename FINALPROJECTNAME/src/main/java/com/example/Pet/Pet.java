package com.example.Pet;

public class Pet {
    
    private String name;
    private PetHealth h = new PetHealth();

    public Pet (String name) {
        this.name=name; 
    }

    public String getName() {return name;}
    
    public void setExpression() {
        int health = h.getPetHealth();
        if (health>80) {

        } else if (health>50) {

        } else if (health>20) {

        } else if (health>0) {

        } else {

        }
    }
}
