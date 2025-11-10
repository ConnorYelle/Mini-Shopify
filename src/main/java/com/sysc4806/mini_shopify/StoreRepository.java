package com.sysc4806.mini_shopify;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, Long> {
	Iterable<Store> findByNameContainingIgnoreCase(String name);
}
