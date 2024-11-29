package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.RolesPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
    Set<RolesPermission> findByNameIn(Set<String> names);
}
