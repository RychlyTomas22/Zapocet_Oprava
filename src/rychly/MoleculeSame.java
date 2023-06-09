/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rychly;

import java.util.ArrayList;

/**
 *
 * @author tomas.rychly
 */
public class MoleculeSame implements Weightable {

    private Element el;
    private int amount;
    private ArrayList<Element> molecules;

    // vytvoreni  elementu pro tuto tridu a nasledne ulozeni zadaneho elementu do instance tridy
    public MoleculeSame(Element el, int amount) {
        this.el = el;
        this.amount = amount;
    }

    public Element getEl() {
        return this.el;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public double getWeight() {
        return el.getWeight() * amount;
    }

}
