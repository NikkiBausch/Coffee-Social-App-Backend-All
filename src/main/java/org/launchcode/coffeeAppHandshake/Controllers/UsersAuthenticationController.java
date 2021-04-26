package org.launchcode.coffeeAppHandshake.Controllers;

import org.launchcode.coffeeAppHandshake.Data.UserRepository;
import org.launchcode.coffeeAppHandshake.Models.User;
import org.launchcode.coffeeAppHandshake.Models.EmailAndPasswordVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("api/v2/User") //localhost8080/api/User
public class UsersAuthenticationController {

    @Autowired
    UserRepository userrepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userrepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, User.getId());
    }

    //Registration Form Mapping

    @GetMapping("register/User")
    public String displayRegistrationForm() {
        new User();
        return "register/User";
    }

    @PostMapping("register/User")
    public String processRegistrationForm(
                                          Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "register/User";
        }

        User existingUser = userrepository.findByEmail(User.getEmail()); //This isn't being picked up

        if (existingUser != null) {
            errors.rejectValue("email", "email.alreadyexists", "A user with that email already exists");
            return "register/User";
        }

        String password = User.getPassword();
        String verifyPassword = EmailAndPasswordVerification.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return "register/User";
        }

        User newUser = new User(User.getUsername(), User.getEmail(), User.getPassword(), User.getFirstname(), User.getAboutMe()); //I don't know why this is being weird.
        userrepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        return "redirect: User";
    }

    //Login Mapping

    @GetMapping("/login")
    public String displayLoginForm() {
        new User();
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(
                                   Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }

        User theUser = userrepository.findByEmail(User.getEmail());

        if (theUser == null) {
            errors.rejectValue("email", "user.invalid", "The given email does not appear in our database.");
            return "login";
        }

        String password = User.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect: Profile/Edit";
    }

    //Logout Mapping
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect: Home";
    }

}
