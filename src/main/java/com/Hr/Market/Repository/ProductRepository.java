package com.Hr.Market.Repository;

import com.Hr.Market.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Products ,Long> {

    List<Products> findByNameContainingIgnoreCase (String NameProduct) ;

    boolean existsByName (String NameProudct) ;

}
