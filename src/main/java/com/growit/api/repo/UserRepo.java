package com.growit.api.repo;

import com.growit.api.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

//    @Query(value = "select * from usr where email = ? limit 1", nativeQuery = true)
    User findByEmail(String email);
}
