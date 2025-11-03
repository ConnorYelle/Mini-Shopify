package com.sysc4806.mini_shopify;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    Optional<Order> findById(Long id);
}
