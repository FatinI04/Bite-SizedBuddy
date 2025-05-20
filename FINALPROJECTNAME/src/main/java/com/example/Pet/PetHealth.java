package com.example.Pet;

public class PetHealth {
    
    private int health;

    public PetHealth() {
        health = 100;
    }

    public void changePetHealth(int num) {
        health = health + num;
    }

    public int getPetHealth() {return health;}
    public void setPetHealth(int num) {health = num;}
}
