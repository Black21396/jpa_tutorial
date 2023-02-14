package net.fadi.jpa.service;


import net.fadi.jpa.entity.Role;

import net.fadi.jpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getRole(long id){
        return roleRepository.findById(id).get();
    }
    public Role insertRole(Role Role){
        return roleRepository.save(Role);
    }

    public Role updateUser(long id, Role role){

        Role oldRole = roleRepository.findById(id).get();
        oldRole.setName(role.getName());
        return roleRepository.save(oldRole);
    }

    public void deleteRole(long id){

        roleRepository.deleteById(id);
    }

    // find role by name (using retrived query)
    public Role findRoleByName(String roleName){
        return roleRepository.findByName(roleName);
    }

}
