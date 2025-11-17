package com.sysc4806.mini_shopify;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> findByStoreId(Long storeId);

    List<Product> id(Long id);
}
