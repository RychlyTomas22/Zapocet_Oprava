/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rychly;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tomas.rychly
 */
public class MoleculeElementApp {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        List<MoleculeSame> chemicals = new ArrayList<MoleculeSame>();
        PeriodicTable table = new PeriodicTable();
        table.getElementFromCSV();

        String input = "";
        int counter = 0;

        while (!input.equalsIgnoreCase("konec")) {

            System.out.println("zadejte symbol elementu nebo napiste konec pro ukonceni");
            input = sc.nextLine();
            String symbol = input;
            Element el = table.findElementBySymbol(symbol);

            System.out.println("zadejte pocet ( 1 pro samostany element)");
            int n = sc.nextInt();
            sc.nextLine();

            if (el != null && n > 0) {
                MoleculeSame temp = new MoleculeSame(el, n);
                chemicals.add(temp);
                System.out.println("hmotnost pro element: " + el.getName() + " mixovaný " + temp.getAmount() + " krat je " + temp.getWeight());
            } else {
                System.out.println("neplatny symbol nebo pocet");
            }
            counter++;
        }

        System.out.println("chcete ulozit vasi sadu do souboru ?");
        System.out.println("1 > ano | 2 > ne");
        int save = sc.nextInt();
        if (save == 1) {
            sc.nextLine();
            System.out.println("zadejete pouze nazev vystupniho sobuoru");
            String name = sc.nextLine();
            File file = new File("./Data/" + name + ".dat");
            if (file.exists()) {
                System.out.println("soubor jiz existuje chcete ho prepsat ?");
                System.out.println("1 > ano | 2 > ne");
                int answer = sc.nextInt();
                if (answer == 1) {
                    file.delete();
                    try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
                        outputStream.writeInt(counter);
                        outputStream.writeBytes("\n");
                        for (MoleculeSame chemical : chemicals) {
                            outputStream.writeUTF(chemical.getEl().getSymbol());
                            outputStream.writeBytes("\n");
                            outputStream.writeInt(chemical.getAmount());
                            outputStream.writeBytes("\n");
                            outputStream.writeDouble(chemical.getWeight());
                            outputStream.writeBytes("\n");
                        }
                    }
                    System.out.println("soubor prepsan");
                } else if (answer == 2) {
                    name = name + "_copy.dat";
                    file = new File("./Data/" + name);
                    try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
                        outputStream.writeInt(counter);
                        outputStream.writeBytes("\n");
                        for (MoleculeSame chemical : chemicals) {
                            outputStream.writeUTF(chemical.getEl().getSymbol());
                            outputStream.writeBytes("\n");
                            outputStream.writeInt(chemical.getAmount());
                            outputStream.writeBytes("\n");
                            outputStream.writeDouble(chemical.getWeight());
                            outputStream.writeBytes("\n");
                        }
                    }
                }
            } else {
                try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
                    outputStream.writeInt(counter);
                    outputStream.writeBytes("\n");
                    for (MoleculeSame chemical : chemicals) {
                        outputStream.writeUTF(chemical.getEl().getSymbol());
                        outputStream.writeBytes("\n");
                        outputStream.writeInt(chemical.getAmount());
                        outputStream.writeBytes("\n");
                        outputStream.writeDouble(chemical.getWeight());
                        outputStream.writeBytes("\n");
                    }
                }
            }
        } else {
            System.out.println("program se ukoncuje");
            chemicals.clear();
        }
    }

}
