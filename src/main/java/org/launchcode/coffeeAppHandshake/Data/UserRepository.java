package org.launchcode.coffeeAppHandshake.Data;

import org.launchcode.coffeeAppHandshake.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);

    }

