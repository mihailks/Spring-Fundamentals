package softuni.battleships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.battleships.model.entity.ShipEntity;

import java.util.Collection;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
    Collection<Object> findFirstByName(String value);

    Collection<Object> findByName(String value);

    Collection<ShipEntity> findAllByUser_Id(Long id);

    Collection<ShipEntity> findAllByUser_IdIsNot(Long id);

    @Query(value = "select * from battleships.ships " +
            "order by name, health, power",nativeQuery = true)
    Collection<ShipEntity> findAllByoOrderByNameHealthPower();


}
