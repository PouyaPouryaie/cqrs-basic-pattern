package ir.bigz.microservice.cqrs.repository;

import ir.bigz.microservice.cqrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
