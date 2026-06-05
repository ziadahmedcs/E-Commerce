package com.Hr.Market.Repository;

import com.Hr.Market.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<Users, Long> {
    

   Optional<Users> findByUsername (String Username) ;

   Optional<Users> findByEmail (String email) ;

    boolean existsByUsername(String Username);

    boolean existsByEmail(String email);


}
