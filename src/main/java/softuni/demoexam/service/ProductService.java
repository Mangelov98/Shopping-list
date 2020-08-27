package softuni.demoexam.service;

import softuni.demoexam.models.service.ProductServiceModel;
import softuni.demoexam.models.view.ProductViewModel;

import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> findAllProducts();

    void delete(String id);

    void deleteAll();
}
