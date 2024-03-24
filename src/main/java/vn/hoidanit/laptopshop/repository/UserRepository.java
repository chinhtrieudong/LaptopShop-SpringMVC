package vn.hoidanit.laptopshop.repository;

import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User hoidanit);

    List<User> findByEmail(String email);

    List<User> findAll();

    User findUserById(long userId);

    void deleteById(long userId);

}
