package com.mark_ainad.task_7.Repository;

import com.mark_ainad.task_7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    void deleteById(Long id);

    User findById(Long id);
    User findByUsername(String username);
}

