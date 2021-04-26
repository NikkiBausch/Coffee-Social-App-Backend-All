package org.launchcode.coffeeAppHandshake.Models;

public enum BeverageName {
    HOTCOFFEE("Hot Coffee"),
    LATTE("Latte"),
    MACHIATO("Machiato"),
    ESPRESSO("Espresso"),
    AMERICANO("Americano"),
    COLDBREW("Cold Brew"),
    ICEDCOFFEE("Iced Coffee"),
    FRAPPE("Frappucino"),
    TEA("Tea"),
    OTHER("Other");

    private final String displayName;

    BeverageName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
