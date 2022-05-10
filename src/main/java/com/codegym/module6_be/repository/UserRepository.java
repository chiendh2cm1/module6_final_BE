package com.codegym.module6_be.repository;

import com.codegym.module6_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndAndEmail(String username, String email);
    @Query(value = "select * from user u " + "where u.username " + "like ?1 or u.nickname like ?1", nativeQuery = true)
    Iterable<User> findUserByKeyword(String keyword);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(nativeQuery = true,value = "select u.* " +
            "from member_workspace as mw " +
            "         join user u on u.id = mw.user_id " +
            "        join workspace_members wm on mw.id = wm.members_id " +
            "join workspace w on w.id = wm.workspace_id " +
            "where (u.username like ?1 or u.nickname like ?1) and w.id =?2")
    Iterable<User> findByKeyword(String keyword, Long workspaceId);
}
