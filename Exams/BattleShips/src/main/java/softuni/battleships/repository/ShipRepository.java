package softuni.battleships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.battleships.model.entity.ShipEntity;
@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
}
