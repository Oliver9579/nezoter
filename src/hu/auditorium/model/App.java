package hu.auditorium.model;

import hu.auditorium.model.controller.ChairService;
import hu.auditorium.model.service.DataApi;
import hu.auditorium.model.service.DataParser;
import hu.auditorium.model.service.FileReader;

public class App {

    private final ChairService chairService;

    App(){
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        chairService = new ChairService(dataApi.getChairs("foglaltsag.txt", "kategoria.txt"));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("3.feladat: Az előadásra eddig "+ chairService.getOccupiesChairCount()+ " jegyet adtak el.");
    }
}
