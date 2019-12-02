package hu.auditorium.model.service;

import hu.auditorium.model.domain.Category;
import hu.auditorium.model.domain.Chair;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    private static final String OCCUPIED = "x";

    public List<Chair> parse(List<String> occupationsList, List<String> categoriesList) {
        List<Chair> chairs = new ArrayList<>();
        for (int row = 0; row < occupationsList.size(); row++) {
            String occupations = occupationsList.get(row);
            String categories = categoriesList.get(row);
            chairs.addAll(parseLine(row, occupations, categories));
        }
        return chairs;
    }

    private List<Chair> parseLine(int row, String occupations, String categories) {
        List<Chair> chairs = new ArrayList<>();
        for (int number = 0; number < occupations.length(); number++) {
            String occupation = occupations.substring(number, number + 1);
            String category = categories.substring(number, number + 1);
            chairs.add(createChair(row + 1, number + 1, category, occupation));
        }
        return chairs;
    }

    private Chair createChair(int row, int number, String categoryValue, String occupiedValue) {
        Category category = Category.byId(Integer.valueOf(categoryValue));
        boolean occupied = OCCUPIED.equals(occupiedValue);
        return new Chair(row, number, category, occupied);
    }
}