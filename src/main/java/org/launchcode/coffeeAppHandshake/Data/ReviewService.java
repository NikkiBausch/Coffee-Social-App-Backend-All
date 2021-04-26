package org.launchcode.coffeeAppHandshake.Data;

import org.launchcode.coffeeAppHandshake.Models.NewReview;
import org.launchcode.coffeeAppHandshake.Models.User;

public interface ReviewService {
    public NewReview save(NewReview newReview);
    public NewReview findByUsername(String username);
}
