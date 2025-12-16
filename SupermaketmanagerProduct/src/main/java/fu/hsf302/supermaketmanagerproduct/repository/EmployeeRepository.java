package fu.hsf302.supermaketmanagerproduct.repository;

import fu.hsf302.supermaketmanagerproduct.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}