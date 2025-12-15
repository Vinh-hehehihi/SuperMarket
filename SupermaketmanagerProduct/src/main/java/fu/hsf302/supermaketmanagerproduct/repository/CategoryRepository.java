package fu.hsf302.supermaketmanagerproduct.repository;

import fu.hsf302.supermaketmanagerproduct.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}