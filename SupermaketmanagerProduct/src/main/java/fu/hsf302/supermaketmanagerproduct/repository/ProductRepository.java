package fu.hsf302.supermaketmanagerproduct.repository;

import fu.hsf302.supermaketmanagerproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStall_StallIDOrderByProductNameAsc(Integer stallId);
    List<Product> findAllByOrderByProductNameAsc();
    List<Product> findByStall_StallIDAndCategory_CategoryIDOrderByProductNameAsc(Integer stallId, Integer categoryId);
}