package fu.hsf302.supermaketmanagerproduct.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Stall")
public class Stall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stallID;

    private String stallCode;

    private String stallName;
    private String locationNote;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    public Stall() {
    }

    public Stall(String stallCode, String stallName, String locationNote, Category category) {
        this.stallCode = stallCode;
        this.stallName = stallName;
        this.locationNote = locationNote;
        this.category = category;
    }

    public Integer getStallID() {
        return stallID;
    }

    public void setStallID(Integer stallID) {
        this.stallID = stallID;
    }

    public String getStallCode() {
        return stallCode;
    }

    public void setStallCode(String stallCode) {
        this.stallCode = stallCode;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getLocationNote() {
        return locationNote;
    }

    public void setLocationNote(String locationNote) {
        this.locationNote = locationNote;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}