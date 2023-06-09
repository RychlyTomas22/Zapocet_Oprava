/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rychly;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas.rychly
 */
public class PeriodicTable {

    private ArrayList<Element> table;

    public PeriodicTable() {        
        this.table = new ArrayList<>();
    }

    // nacteni a prideleni roku pro elementy z prvky.csv
    public void getElementFromCSV() {
        File prvky = new File("./Data/prvky.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(prvky))) {
            br.readLine();
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                String name = info[0];
                int num = Integer.parseInt(info[1]);
                String symbol = info[2];
                double weight = Double.parseDouble(info[3]);

                Element existingElement = findElementBySymbol(symbol);
                if (existingElement != null) {
                    existingElement.setName(name);
                    existingElement.setProtonNum(num);
                    existingElement.setAtomWeight(weight);
                    setDate(existingElement);
                } else {
                    Element tempEl = new Element(name, symbol, num, weight);
                    table.add(tempEl);
                    setDate(tempEl);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(PeriodicTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Element findElementBySymbol(String symbol) {
        for (Element element : table) {
            if (element.getSymbol().equalsIgnoreCase(symbol)) {
                Element el = element;
                return el;
            }
        }
        return null;
    }

    private void setDate(Element el) {
        if (el.getDate() < 0) {
            File f = new File("./Data/rokObjeveni.txt");
            try (Scanner sc = new Scanner(f)) {
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String yearInfo = sc.nextLine();
                    String[] yearVal = yearInfo.split("\\s+");
                    if (yearVal.length >= 4) {
                        String sym = yearVal[2];
                        String sDate = yearVal[3];
                        if (el.getSymbol().equalsIgnoreCase(sym) && !sDate.equalsIgnoreCase("n")) {
                            el.setDate(Integer.parseInt(sDate));
                        }
                    }
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(PeriodicTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //porovnani nejstarsiho prvku
    
    public void getOldest() {
        int currentYear = Year.now().getValue();
        int temp = 0;
        Element oldest = null;
        for (Element element : table) {
            if (currentYear - element.getDate() > temp && element.getDate() > 0) {
                temp = currentYear - element.getDate();
                oldest = element;
            }
        }
        System.out.println(oldest.toString());
        System.out.println("objeven pred " + temp + " lety");
    }

    public void getTop10() {
        Collections.sort(table, Comparator.comparingDouble(Element::getAtomWeight));
        int n = table.size() - 10;
        for (int i = table.size() - 1; i > n; i--) {
            Element el = table.get(i);
            System.out.println(el.toString());
        }
    }

    public void addElement(String nazev, String symbol, int protCislo, double relHmot) {
        Element temp = new Element(nazev, symbol, protCislo, relHmot);
        table.add(temp);
    }

    public void addElement(Element a) {
        table.add(a);
    }


    // smazani get sorted list
    //smazaní zbytecneho konstruktoru
    
    public int getSize() {
        return table.size();
    }

    public Element getElement(int i) {
        return table.get(i);
    }

}
