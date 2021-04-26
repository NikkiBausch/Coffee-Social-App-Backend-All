package org.launchcode.coffeeAppHandshake.Controllers;

import org.launchcode.coffeeAppHandshake.Data.UserRepository;
import org.launchcode.coffeeAppHandshake.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v2/User")
public class CreateUsersController {


        @Autowired
        private UserRepository userrepository;

        @GetMapping("home")
        public String index(){
            return "home";
        }

        @GetMapping("User")
        public String displayUserSignUp(@RequestParam @Valid String email, @RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String aboutMe){
            User user = new User(email, username, password, firstName, aboutMe);

            return "register/User";

        }
        @PostMapping("User")
        public String processUserSignUp(@Valid User user, Errors errors){
            if (errors.hasErrors()){
                return "register/User";
            }

            userrepository.save(user);
            return "redirect: Profile/Edit";
        }



//Edit Profile Login View Controls



        @RequestMapping("Profile/Edit")
        @GetMapping
        public User displayEditUserInfo(User user, @RequestParam int id){
                userrepository.findById(id);
                return user;

             }


        @RequestMapping("Profile/Edit")
        @PostMapping
        public String changeUserInfo(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String aboutMe){
            User user = new User(email, username, password, firstName, aboutMe);
            return "Profile/Edit";

        }

        @GetMapping("User/delete")
        public String displayDeleteProfile(){
            userrepository.findAll();
            return "User/delete";
        }

        @PostMapping("delete/Home")
        public String processDeleteProfile(int id){
            userrepository.deleteById(id);

            return "redirect: Home";

        }





    }



