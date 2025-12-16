package fu.hsf302.supermaketmanagerproduct.repository;
import fu.hsf302.supermaketmanagerproduct.entity.Stall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StallRepository extends JpaRepository<Stall, Integer> {
}