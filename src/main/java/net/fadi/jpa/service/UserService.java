package net.fadi.jpa.service;


import jakarta.transaction.Transactional;
import net.fadi.jpa.entity.Role;
import net.fadi.jpa.entity.User;
import net.fadi.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(long id){
        return userRepository.findById(id).get();
    }
    public User insertUser(User User){
        return userRepository.save(User);
    }

    public User updateUser(long id, User user){

        User oldUser = userRepository.findById(id).get();
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRoles(user.getRoles());

        return userRepository.save(oldUser);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    /**
     * method to insert a Role to all users in DB
     * here we used "@Transactional" annotation, because we have a lot of
       update operation, so that's better to add Transaction to make sure that
       all update operations is successfully updated
     */
    @Transactional
    public boolean addRoleToAllUsers(String roleName){
        // get this role from DB
        Role role = roleService.findRoleByName(roleName);

        // if rule not exist in db, create it in Db
        if(role == null)
            roleService.insertRole(new Role(0, roleName));

        // get all users from database and add this role to them and save this changes in DB
        this.getUsers().forEach(user ->{
            user.getRoles().add(role);
            this.updateUser(user.getId(), user);
        });

        return true;
    }
}
