package net.fadi.jpa.controller;


import net.fadi.jpa.entity.Department;
import net.fadi.jpa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping
    public List<Department> getAll(){
        return departmentService.getDepartments();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable long id){
        return departmentService.getDepartment(id);
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department){
        return departmentService.insertDepartment(department);
    }

    @PutMapping("/{id}")
    public Department editDepartment(@PathVariable long id,@RequestBody Department department){
        return  departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable long id){
        departmentService.deleteDepartment(id);
        return "Successfully deleted!";
    }
}