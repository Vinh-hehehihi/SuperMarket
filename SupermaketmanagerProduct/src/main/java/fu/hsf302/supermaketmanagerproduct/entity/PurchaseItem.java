package fu.hsf302.supermaketmanagerproduct.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PurchaseItem")
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseItemID;

    private Integer quantity;
    private Double unitCost;

    @ManyToOne
    @JoinColumn(name = "poid")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    public PurchaseItem() {
    }

    public PurchaseItem(Integer quantity, Double unitCost, PurchaseOrder purchaseOrder, Product product) {
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.purchaseOrder = purchaseOrder;
        this.product = product;
    }

    public Integer getPurchaseItemID() {
        return purchaseItemID;
    }

    public void setPurchaseItemID(Integer purchaseItemID) {
        this.purchaseItemID = purchaseItemID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}