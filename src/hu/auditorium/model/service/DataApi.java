package hu.auditorium.model.service;

import hu.auditorium.model.domain.Chair;

import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;

    public DataApi(FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
    }

    public List<Chair> getChairs(String occupation, String category) {
        return dataParser.parse(fileReader.read(occupation), fileReader.read(category));
    }
}
