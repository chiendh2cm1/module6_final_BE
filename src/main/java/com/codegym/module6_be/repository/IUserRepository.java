package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);

    User findByUsernameAndAndEmail(String userName, String email);

    @Query(value = "select * from user u " +
            "where u.username " +
            "like ?1 or u.nickname like ?1", nativeQuery = true)
    Iterable<User> findUserByKeyword(String keyword);

}
