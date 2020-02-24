/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.fullstack_slutprojekt.entities;

/**
 *
 * @author erikh
 */
public class Ingredient {
    private String name;
    private int amount;
    private String messurment;

    public Ingredient(String name, int amount, String messurment) {
        this.name = name;
        this.amount = amount;
        this.messurment = messurment;
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMessurment() {
        return messurment;
    }

    public void setMessurment(String messurment) {
        this.messurment = messurment;
    }
}
