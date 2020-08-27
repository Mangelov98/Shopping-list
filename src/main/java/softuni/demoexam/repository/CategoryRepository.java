package softuni.demoexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.demoexam.models.entity.Category;
import softuni.demoexam.models.entity.NameCategory;

import java.util.Optional;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,String> {
    Optional<Category> findByNameCategory(NameCategory nameCategory);
}
