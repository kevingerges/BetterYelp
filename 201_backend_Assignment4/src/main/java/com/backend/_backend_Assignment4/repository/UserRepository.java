package com.backend._backend_Assignment4.repository;

import com.backend._backend_Assignment4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long>  {

    User findByEmailAndPassword(String email,String Password);

    Optional<User> findByEmail(String email);
    User findByUsernameAndPassword(String email,String Password);

}


