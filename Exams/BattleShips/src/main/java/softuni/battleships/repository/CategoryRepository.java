package softuni.battleships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.battleships.model.entity.CategoryEntity;
import softuni.battleships.model.entity.enums.CategoryNameEnum;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(CategoryNameEnum name);
}
