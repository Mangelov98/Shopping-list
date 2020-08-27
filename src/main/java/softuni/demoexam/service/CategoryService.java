package softuni.demoexam.service;

import softuni.demoexam.models.entity.Category;
import softuni.demoexam.models.entity.NameCategory;

public interface CategoryService {
    Category findByNameCategory(NameCategory nameCategory);

    void initCategories();
}
