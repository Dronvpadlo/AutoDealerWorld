package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.RolesPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<RolesPermission, Long> {
    boolean existsByName(String name);
}
