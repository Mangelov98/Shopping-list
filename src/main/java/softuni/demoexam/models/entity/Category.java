package softuni.demoexam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private NameCategory nameCategory;
    private String description;

    public Category() {
    }

    public Category(NameCategory nameCategory,String description) {
        this.nameCategory = nameCategory;
        this.description = description;
    }


    @Enumerated
    public NameCategory getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(NameCategory nameCategory) {
        this.nameCategory = nameCategory;
    }
    @Column(name = "description",nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
