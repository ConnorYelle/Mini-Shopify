package com.sysc4806.mini_shopify;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface productRepository extends CrudRepository<Product, Integer> {

    Optional<Product> findById(Long id);
}
