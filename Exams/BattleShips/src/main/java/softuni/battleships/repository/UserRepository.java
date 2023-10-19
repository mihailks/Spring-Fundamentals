package softuni.battleships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.battleships.model.entity.UserEntity;

import java.util.Collection;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Collection<UserEntity> findByEmail(String value);

    Collection<UserEntity> findByUsername(String value);
}
