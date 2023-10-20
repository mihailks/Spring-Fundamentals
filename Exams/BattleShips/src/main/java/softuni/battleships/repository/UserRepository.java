package softuni.battleships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.battleships.model.bindingModel.UserLoginDTO;
import softuni.battleships.model.entity.UserEntity;
import softuni.battleships.util.CurrentUser;

import java.util.Collection;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Collection<UserEntity> findByEmail(String value);

    Collection<UserEntity> findByUsername(String value);

    Optional<UserEntity> findFirstByUsernameAndPassword(String username, String password);


}
