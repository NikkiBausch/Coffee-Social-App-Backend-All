package org.launchcode.coffeeAppHandshake.Models;

public enum Atmosphere {

    INDUSTRIAL("Industrial"),
    RUSTIC("Rustic"),
    CHIC("Chic"),
    COTTAGECORE("Cottage-Core"),
    LOUD("Loud"),
    BUSY("Busy"),
    QUIET("Quiet"),
    OTHER("Other");

    private final String displayAtmosphere;

    Atmosphere(String displayAtmosphere) {
        this.displayAtmosphere = displayAtmosphere;
    }

    public String getDisplayAtmosphere() {
        return displayAtmosphere;
    }
}
