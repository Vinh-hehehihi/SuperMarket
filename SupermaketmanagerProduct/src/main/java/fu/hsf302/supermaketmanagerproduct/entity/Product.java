package fu.hsf302.supermaketmanagerproduct.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    private String productCode;
    private String productName;
    private String unit;
    private Double unitPrice;
    private Double costPrice;
    private Integer stockQuantity;
    private Date expiryDate;
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "stallID")
    private Stall stall;

    public Product() {
    }

    public Product(String productCode, String productName, String unit, Double unitPrice, Double costPrice, Integer stockQuantity, Date expiryDate, Boolean isActive, Category category, Supplier supplier, Stall stall) {
        this.productCode = productCode;
        this.productName = productName;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.costPrice = costPrice;
        this.stockQuantity = stockQuantity;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
        this.category = category;
        this.supplier = supplier;
        this.stall = stall;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean isActive() { // Renamed from getActive()
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }
}