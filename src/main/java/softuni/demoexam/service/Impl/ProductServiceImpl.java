package softuni.demoexam.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.demoexam.models.entity.Product;
import softuni.demoexam.models.service.ProductServiceModel;
import softuni.demoexam.models.view.ProductViewModel;
import softuni.demoexam.repository.ProductRepository;
import softuni.demoexam.service.CategoryService;
import softuni.demoexam.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel,Product.class);

        product.setCategory(this.categoryService.findByNameCategory(productServiceModel.getCategory().getNameCategory()));

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductViewModel> findAllProducts() {
        return this.productRepository.findAll().stream().map(product -> {
            ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);


            productViewModel.setNameCategory(product.getCategory().getNameCategory().name());
            productViewModel.setImgUrl(String.format("/img/%s.png", product.getCategory().getNameCategory().name()));
            return productViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }
}
