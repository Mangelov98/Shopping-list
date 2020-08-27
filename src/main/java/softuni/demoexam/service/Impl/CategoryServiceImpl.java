package softuni.demoexam.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.demoexam.models.entity.Category;
import softuni.demoexam.models.entity.NameCategory;
import softuni.demoexam.repository.CategoryRepository;
import softuni.demoexam.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
   private final CategoryRepository categoryRepository;
   private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Category findByNameCategory(NameCategory nameCategory) {
        return this.categoryRepository.findByNameCategory(nameCategory).orElse(null);
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(NameCategory.values()).forEach(nameCategory -> {
                this.categoryRepository.save(new Category(nameCategory, String.format("Description for %s", nameCategory.name())));
            });
        }
    }
}
