package softuni.coffeeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.coffeeshop.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
