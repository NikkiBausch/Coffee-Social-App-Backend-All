package org.launchcode.coffeeAppHandshake.Models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class User {
    //Id is still not generating, not working in hashcode for some reason, and isn't being picked up in the controller.

    @Id
    @GeneratedValue
    private static int id;



    @JoinColumn(name="Username")
    @NotBlank(message = "Field, 'Username', is required.")
    @Size(min=3, max=15, message = "Username must be between 3 and 15 characters.")
    private static String username;

    @JoinColumn(name="User_email")
    @Valid
    @NotBlank(message = "Field, 'Email', is required.")
    @Email(message="Must be a valid email.")
    private static String email;


    @NotNull
    private static String pwHash;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.")
    private static String password;



    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @JoinColumn(name="First_name")
    @Size(max=15, message = "Name cannot be longer than 15 characters.")
    private static String firstName;

    @JoinColumn(name="about_me")
    @Size(max=500)
    private static String aboutMe;


    public User(String username, String email, String password, String firstName, String aboutMe) {
        this.username = username;
        this.email = email;
        this.pwHash = encoder.encode(password);
        this.firstName = firstName;
        this.aboutMe = aboutMe;
    }

    public User() {

    }
    @Override
    public String toString(){
        return "User{"+
                "username='" + username +'\''+
                "email='" + email + '\''+
                "firstName'" + firstName + '\''+
                "aboutMe='" + aboutMe + '\''+
                '}';
    }



    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }



    public static int getId(){
        return id;
    }

    public void setId(){this.id=id; }

    public static String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public static String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }


    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user  = (User) o;
        return id == id;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
