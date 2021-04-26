package org.launchcode.coffeeAppHandshake.Controllers;



import org.launchcode.coffeeAppHandshake.Data.ReviewRepository;
import org.launchcode.coffeeAppHandshake.Models.Atmosphere;
import org.launchcode.coffeeAppHandshake.Models.BeverageName;
import org.launchcode.coffeeAppHandshake.Models.NewReview;
import org.launchcode.coffeeAppHandshake.Models.OverallRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/NewReview") //API URL for forms
public class FormController {

    @Autowired
    private ReviewRepository reviewrepository;

    //Display reviews saved in the database
    @GetMapping
    public String displayAllReviews(){
        reviewrepository.findAll();
        return "reviews";
    }

    //display Review form
    @GetMapping("create")
    public String displayReviewForm(@RequestParam String dateOfVisit, @RequestParam String nameOfBusiness, @RequestParam BeverageName beverageName, @RequestParam Atmosphere atmosphere, @RequestParam OverallRating overallRating, @RequestParam String summary){
        NewReview newReview = new NewReview(dateOfVisit, nameOfBusiness, beverageName, atmosphere, overallRating, summary );

        return "reviews/create";

    }

    //Process review form/redirect back to form if "errors"
    @PostMapping("create")
    public String processDisplayReviewForm(@Valid NewReview newReview, Errors errors){
        if (errors.hasErrors()){
            return "reviews/create";
        }

        reviewrepository.save(newReview);
        return "redirect";
    }

    //Mapping for delete Review within ReviewRepo
    @GetMapping("delete review")
    public String displayDeleteReview(){
        reviewrepository.findAll();
        return "reviews/delete";
    }

    //Mapping for processing request to delete Review saved within ReviewRepo
    @PostMapping("delete review")
    public String processDeleteReview(int id){
        reviewrepository.deleteById(id);

        return "redirect";

    }

    //display Reviews

}

