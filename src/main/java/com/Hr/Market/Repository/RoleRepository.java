package com.Hr.Market.Repository;

import com.Hr.Market.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles , Long> {

    Optional<Roles> findByName(String name);

    boolean existsByName(String name);
}
