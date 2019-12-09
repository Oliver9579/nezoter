package hu.auditorium.model;

import hu.auditorium.model.controller.ChairService;
import hu.auditorium.model.service.*;

import java.util.Scanner;

public class App {

    private final ChairService chairService;
    private final Console console;
    private final FileWriter fileWriter;

    App(){
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        chairService = new ChairService(dataApi.getChairs("foglaltsag.txt", "kategoria.txt"));
        console = new Console(new Scanner(System.in));
        fileWriter = new FileWriter("szabad.txt");
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2. feladat: Adja meg a kiválasztott szék paramétereit:");
        System.out.print("     - sor száma:");
        int row = console.readInt();
        System.out.print("     - szék száma:");
        int number = console.readInt();
        System.out.println("A kiválasztott szék " + (chairService.isGivenChairOccupied(row, number) ? "már foglalt" : "még üres"));
        System.out.println("3.feladat: Az előadásra eddig "+ chairService.getOccupiesChairCount()
                + " jegyet adtak el, ez a nézőtér " + chairService.getOccupiesChairPercent() + "-a");
        System.out.println("5.feladat: A színház jelenlegi bevétele " + chairService.countTotalIncome() + " Ft lenne");
        fileWriter.write(chairService.getAuditoriumStatus());

    }


}
