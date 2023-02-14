package net.fadi.jpa.service;


import net.fadi.jpa.entity.Department;
import net.fadi.jpa.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    public Department getDepartment(long id){
        return departmentRepository.findById(id).get();
    }
    public Department insertDepartment(Department department){
        return departmentRepository.save(department);
    }

    public Department updateDepartment(long id, Department department){

        Department olddepartment = departmentRepository.findById(id).get();
        olddepartment.setName(department.getName());
        return departmentRepository.save(olddepartment);
    }

    public void deleteDepartment(long id){
        departmentRepository.deleteById(id);
    }
}
