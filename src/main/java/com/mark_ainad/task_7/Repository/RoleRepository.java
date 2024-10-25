package com.mark_ainad.task_7.Repository;

import com.mark_ainad.task_7.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRolename(String rolename);
}
