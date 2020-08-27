package softuni.demoexam.models.view;

import softuni.demoexam.models.entity.NameCategory;

import java.math.BigDecimal;

public class ProductViewModel {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal totalPriceOfProducts;
    private String nameCategory;
    private String imgUrl;

    public ProductViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPriceOfProducts() {

        return totalPriceOfProducts;
    }

    public void setTotalPriceOfProducts(BigDecimal totalPriceOfProducts) {
        this.totalPriceOfProducts = totalPriceOfProducts;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
