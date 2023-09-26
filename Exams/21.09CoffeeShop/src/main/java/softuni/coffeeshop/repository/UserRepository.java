package softuni.coffeeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.coffeeshop.model.entity.User;
import softuni.coffeeshop.model.service.UserServiceModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsernameAndPassword(String username, String password);

    @Query("select u from User as u order by size(u.orders) desc")
    List<User> findAllByOrderByOrderDesc();
}
