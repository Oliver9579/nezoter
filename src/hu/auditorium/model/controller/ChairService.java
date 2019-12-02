package hu.auditorium.model.controller;

import hu.auditorium.model.domain.Chair;

import java.util.List;

public class ChairService {

    private final List<Chair> chairs;

    public ChairService(List<Chair> chairs) {
        this.chairs = chairs;
    }

    public long getOccupiesChairCount(){
        return chairs.stream()
                .filter(Chair::isOccupied)
                .count();
    }
}
