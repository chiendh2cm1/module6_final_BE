package com.codegym.module6_be.service.user;

import com.codegym.module6_be.model.User;
import com.codegym.module6_be.service.GeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends GeneralService<User>, UserDetailsService {
    User findByUsername(String username);
    User findByUsernameAndEmail(String username, String email);
    Iterable<User> findUserByKeyword(String keyword);
    User update(User user);
    Boolean existsByUsername(String username);
    Boolean existsByEmail (String email);
    Iterable<User> findByKeywordAndWorkspace(String keyword, Long workspaceId);
}
