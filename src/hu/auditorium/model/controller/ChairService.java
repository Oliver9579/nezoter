package hu.auditorium.model.controller;

import hu.auditorium.model.domain.Chair;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChairService {

    public static final int MAX_ROW = 15;
    public static final int MAX_NUMBER = 20;
    private final List<Chair> chairs;

    public ChairService(List<Chair> chairs) {
        this.chairs = chairs;
    }

    public long getOccupiesChairCount() {
        return chairs.stream()
                .filter(Chair::isOccupied)
                .count();
    }

    public String getOccupiesChairPercent() {
        double percent = getOccupiesChairCount() * 100.0 / chairs.size();
        return String.format("%2.0f%%", percent);
    }

    public boolean isGivenChairOccupied(int row, int number) {
        return getChair(row, number)
                .map(Chair::isOccupied)
                .orElse(true);
    }

    public int getMostPopularChairCategory() {
        return getChairCategoryCountMap().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
    }

    public int countTotalIncome() {
        return chairs.stream()
                .filter(Chair::isOccupied)
                .mapToInt(Chair::getPrice)
                .sum();
    }

    public long getSingleFreeChairCount() {
        return chairs.stream()
                .filter(this::isSingleFreeChair)
                .count();
    }

    public List<String> getAuditoriumStatus() {
        String auditoriumStatusInRow = getAuditoriumStatusInRow();
        return IntStream.range(0, MAX_ROW)
                .mapToObj(row -> auditoriumStatusInRow.substring(row * MAX_NUMBER, row * MAX_NUMBER + (MAX_NUMBER - 1)))
                .collect(Collectors.toList());
    }

    private boolean isSingleFreeChair(Chair chair) {
        int row = chair.getRow();
        int number = chair.getNumber();
        return !chair.isOccupied() && isGivenChairOccupied(row, number - 1) && isGivenChairOccupied(row, number + 1);
    }

    private Map<Integer, Long> getChairCategoryCountMap() {
        return chairs.stream()
                .filter(Chair::isOccupied)
                .collect(Collectors.groupingBy(Chair::getCategoryId, Collectors.counting()));
    }

    private Optional<Chair> getChair(int row, int number) {
        return chairs.stream()
                .filter(i -> i.findChair(row, number))
                .findAny();
    }

    private String getAuditoriumStatusInRow() {
        return chairs.stream()
                .map(Chair::toString)
                .collect(Collectors.joining());

    }
}
