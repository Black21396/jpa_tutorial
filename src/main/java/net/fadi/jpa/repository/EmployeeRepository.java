package net.fadi.jpa.repository;


import jakarta.transaction.Transactional;
import net.fadi.jpa.entity.Employee;
import net.fadi.jpa.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

     //use method name (JPA) to create custom query (this method of query called "Derived Query"

     // Query1: get Employees according the department id
     public List<Employee> getEmployeeByDepartmentId(long deptId);

     // Query2: get Employees according their name and the name of department (here not equal, here like)
     public List<Employee>findByNameContainingAndDepartmentNameContaining(String empName, String depName);

     // Query3: Delete employee according his name and department name (here method return the id of deleted employee)
     // Note: by deleting and updating, we have to put two annotation ("@Modifying", "@Transactional")
     @Modifying
     @Transactional
     public Long deleteByNameAndDepartmentName(String emp_name, String deptName);


     // get Employees according salary (using derived query)
     public List<Employee> findBySalaryGreaterThan(int salary);

     // get Employees according salary (using native query)
     @Query(value = "SELECT e.name, e.salary, d.name deptName \n" +
             "FROM hr_db.employees e join hr_db.departments d  \n" +
             "on e.department_id = d.id\n" +
             "where salary > ?1", nativeQuery = true)
     public List<EmployeeProjection> getEmployeeAccordingSalary(int salary);

}
