package softuni.coffeeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.coffeeshop.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
