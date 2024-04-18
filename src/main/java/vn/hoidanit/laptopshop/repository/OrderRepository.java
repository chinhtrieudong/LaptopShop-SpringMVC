package vn.hoidanit.laptopshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.laptopshop.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order);

    Optional<Order> findById(long id);

    List<Order> findAll();
}
