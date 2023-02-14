package net.fadi.jpa.controller;


import net.fadi.jpa.entity.Employee;
import net.fadi.jpa.projection.EmployeeProjection;
import net.fadi.jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getEmployees();
    }
    @GetMapping("/{id}")
    public Employee getById(@PathVariable long id){
       return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.insertEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable long id,@RequestBody Employee employee){
        return  employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    // get all employees according the department (using JPA method)
    @GetMapping("/department/{id}")
    public List<Employee> getEmployeesByDepartment(@PathVariable long id){
        return employeeService.getEmployeeByDepartmentId(id);
    }

    // get all employees according names and department name
    @GetMapping("/withDepartment")
    public List<Employee> getEmployeeByNameAndDepartment(@RequestParam String empName, @RequestParam String deptName){
        return employeeService.findEmpWithDept(empName, deptName);
    }

    // delete Employee according his name and name of department
    @DeleteMapping("/withDepartment")
    public long deleteEmpAccordingNameAndDeptName(@RequestParam String empName, @RequestParam String deptName){
        return employeeService.deleteEmpAccordingNameAndDepratmentName(empName, deptName);
    }

    // get Employee where their salary greater than specific value
    @GetMapping("/salary/{salary}")
    public List<Employee> getEmployeesAccordingSalary(@PathVariable int salary){
        return employeeService.getEmpAccordingSalary(salary);
    }

    // get Employees according salary (using native query)
    @GetMapping("salary/native/{salary}")
    public List<EmployeeProjection> getEmployeeAccordingSalary(@PathVariable int salary){
        return employeeService.getEmployeeAccordingSalary(salary);
    }


}
