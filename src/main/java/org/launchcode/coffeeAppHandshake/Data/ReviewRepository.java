package org.launchcode.coffeeAppHandshake.Data;

import org.launchcode.coffeeAppHandshake.Models.NewReview;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<NewReview, Integer> {
    public NewReview findByUsername(String username);
}
