package com.example.task1.repository;

import com.example.task1.entity.Role;
import com.example.task1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    //some implementation method signatures here
    public Role findByName(String name);
}
