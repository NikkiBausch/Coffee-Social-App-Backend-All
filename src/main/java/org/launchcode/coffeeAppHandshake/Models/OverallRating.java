package org.launchcode.coffeeAppHandshake.Models;

public enum OverallRating {
    ONE( "The Worst!"),

    TWO(  "Not Completely Horrible"),

    THREE("Eh"),

    FOUR( "Pretty Good!"),

    FIVE(   "Amazing!");







    private final String displayRating;

    OverallRating(String displayRating) {
        this.displayRating = displayRating;
    }

    public String getDisplayRating() {
        return displayRating;
    }
}
