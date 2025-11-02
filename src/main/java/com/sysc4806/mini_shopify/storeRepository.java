package com.sysc4806.mini_shopify;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface storeRepository extends CrudRepository<Store, Integer> {
    Optional<Store> findById(Long id);
}