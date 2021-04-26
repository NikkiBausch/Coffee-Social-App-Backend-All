package org.launchcode.coffeeAppHandshake.Models;

public class EmailAndPasswordVerification extends User {



    private static String verifyPassword;

    public static String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

}
