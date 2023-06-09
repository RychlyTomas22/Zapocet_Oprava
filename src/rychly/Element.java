/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rychly;

/**
 *
 * @author tomas.rychly
 */
public class Element implements Weightable {

    private String name;
    private String symbol;
    private int protonNum;
    private double atomWeight;
    private int date;
    // vymazane nepotrebne konstruktory
    
    public Element(String name, String symbol, int protonNum, double atomWeight) {
        this.name = name;
        this.symbol = symbol;
        this.protonNum = protonNum;
        this.atomWeight = atomWeight;
        this.date = -1;
    }
    public int getProtonNum() {
        return protonNum;
    }

    
    public void setDate(int date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getDate() {
        return date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtonNum(int protonNum) {
        this.protonNum = protonNum;
    }

    public void setAtomWeight(double atomWeight) {
        this.atomWeight = atomWeight;
    }

    public double getAtomWeight() {
        return atomWeight;
    }
    
    //smazani nepotrebne funkce addEl
    @Override
    public double getWeight() {
        return getAtomWeight();
    }

    @Override
    public String toString() {
        if (date > 0) {
            return name + " " + symbol + " " + protonNum + " " + atomWeight + " " + date;
        }
        return name + " " + symbol + " " + protonNum + " " + atomWeight;

    }

}
