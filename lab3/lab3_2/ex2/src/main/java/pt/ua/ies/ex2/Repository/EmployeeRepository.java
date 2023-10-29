package pt.ua.ies.ex2.Repository;
import pt.ua.ies.ex2.Model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    List<Employee> findByEmailId(String emailId);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
}