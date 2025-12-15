package fu.hsf302.supermaketmanagerproduct.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer categoryID;

    @Column(nullable = false, unique = true)
    private String categoryCode;

    @Column(nullable = false)
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Stall> stalls;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(String categoryCode, String categoryName, String description) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.description = description;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Stall> getStalls() {
        return stalls;
    }

    public void setStalls(List<Stall> stalls) {
        this.stalls = stalls;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}