package com.sysc4806.mini_shopify;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface productRepository extends CrudRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> id(Long id);
}
