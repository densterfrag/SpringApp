package com.mark_ainad.task_7.Service;

import com.mark_ainad.task_7.DTO.UserDTO;
import com.mark_ainad.task_7.Repository.RoleRepository;
import com.mark_ainad.task_7.Repository.UserRepository;
import com.mark_ainad.task_7.entity.Role;
import com.mark_ainad.task_7.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository1;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User convertUserDTOToUser(UserDTO userDTO) {
        List<String> roles = userDTO.getRoles();
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String rolename : roles) {
                Role role = roleRepository.findByRolename(rolename);
                roleSet.add(role);
            }
        }
        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getLastName(), userDTO.getDickSize(), userDTO.getPassword(), roleSet);

    }

    public UserDTO convertUserToUserDTO(User user) {

        Set<Role> roles = user.getRoles();
        List<String> roleNames = new ArrayList<>();
        if (!roles.isEmpty()) {
            if (roles.contains(roleRepository.findByRolename("ROLE_ADMIN"))) {
                roleNames.add("ROLE_ADMIN");
            }
            if (roles.contains(roleRepository.findByRolename("ROLE_USER"))) {
                roleNames.add("ROLE_USER");
            }
        }
        UserDTO userDTO = new UserDTO(
                user.getId()
                , user.getUsername()
                , user.getLastName()
                , user.getDickSize()
                , user.getPassword()
                , roleNames);
        return userDTO;
    }


}


