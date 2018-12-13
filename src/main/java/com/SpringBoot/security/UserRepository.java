package com.SpringBoot.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserAuth,String> {

    UserAuth findByEmail(String email);
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);

}
