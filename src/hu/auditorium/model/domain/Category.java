package hu.auditorium.model.domain;

import java.util.Arrays;

public enum Category {

    FIRST(1, 5000),
    SECOND(2, 4000),
    THIRD(3, 3000),
    FOURTH(4, 2000),
    FIFTH(5, 1500);

    private final int id;

    private final int price;

    Category(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public static Category byId(int id) {
        return Arrays.stream(Category.values())
                .filter(i -> i.getId()==id)
                .findAny()
                .get();
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}
