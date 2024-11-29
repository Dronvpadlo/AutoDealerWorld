package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser, Long> {
     Optional<NewUser> findByUsername(String username);
}
