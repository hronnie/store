package com.roche.store.core.repository;

import com.roche.store.core.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select p from Product p " +
            " where p.archived = false")
    List<Product> findByArchived();

}
