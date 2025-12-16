package fu.hsf302.supermaketmanagerproduct.entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PurchaseOrder")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer poid;

    private String poNumber;
    private Date orderDate; // Renamed from poDate for consistency
    private Double totalAmount;
    private String status;

    @ManyToOne
    @JoinColumn(name = "supplierID")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private Employee employee;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> purchaseItems; // Renamed from items for clarity

    public PurchaseOrder() {
    }

    public PurchaseOrder(String poNumber, Date orderDate, Double totalAmount, String status, Supplier supplier, Employee employee) {
        this.poNumber = poNumber;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.supplier = supplier;
        this.employee = employee;
    }

    public Integer getPoid() {
        return poid;
    }

    public void setPoid(Integer poid) {
        this.poid = poid;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}
