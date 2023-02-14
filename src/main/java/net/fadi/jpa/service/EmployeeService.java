package net.fadi.jpa.service;


import net.fadi.jpa.entity.Employee;
import net.fadi.jpa.projection.EmployeeProjection;
import net.fadi.jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository empRepo;

    @Autowired
    DepartmentService departmentService;

    public List<Employee> getEmployees(){
        return empRepo.findAll();
    }

    public Employee getEmployeeById(long id){
        return empRepo.findById(id).get();
    }

    public Employee insertEmployee(Employee emp){

      /*   if id of department is 0, that's mean this department is not exist,
      then I have to create this department and put it in this new employee
        */
        if(emp.getDepartment().getId() == 0){
            emp.setDepartment(departmentService.insertDepartment(emp.getDepartment()));
        }
        return empRepo.save(emp);
    }

    public Employee updateEmployee(long id, Employee emp){

        Employee oldEmp = empRepo.findById(id).get();
        oldEmp.setName(emp.getName());
        oldEmp.setSalary(emp.getSalary());
        oldEmp.setDepartment(emp.getDepartment());

        return empRepo.save(oldEmp);
    }

    public void deleteEmployee(long id){

        empRepo.deleteById(id);
    }

    public List<Employee> getEmployeeByDepartmentId(long deptId){

        return  empRepo.getEmployeeByDepartmentId(deptId);
    }

    // find employee like employeeName and department name
    public List<Employee> findEmpWithDept(String empName, String deptName){
        return empRepo.findByNameContainingAndDepartmentNameContaining(empName, deptName);
    }

    // delete Employee according his name and name of department
    public long deleteEmpAccordingNameAndDepratmentName(String empName, String deptName){
        return empRepo.deleteByNameAndDepartmentName(empName, deptName);
    }

    // get Employee where their salary greater than specific value
    public List<Employee> getEmpAccordingSalary(int salary){
        return empRepo.findBySalaryGreaterThan(salary);
    }

    // get Employees according salary (using native query)
    public List<EmployeeProjection> getEmployeeAccordingSalary(int salary){
        return empRepo.getEmployeeAccordingSalary(salary);
    }
}
