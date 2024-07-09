package com.drack.MovieManagement.percistence.repository;

import com.drack.MovieManagement.percistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);
    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    int deleteByUsername(String username);
}
