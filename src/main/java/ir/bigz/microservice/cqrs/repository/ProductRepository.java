package ir.bigz.microservice.cqrs.repository;

import ir.bigz.microservice.cqrs.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
