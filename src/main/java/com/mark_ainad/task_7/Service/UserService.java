package com.mark_ainad.task_7.Service;



import com.mark_ainad.task_7.DTO.UserDTO;
import com.mark_ainad.task_7.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
    User getUserById(Long id);
    User getUserByUserName(String username);
    User convertUserDTOToUser(UserDTO userDTO);
}

