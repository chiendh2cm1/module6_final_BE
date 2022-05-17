package com.codegym.module6_be.controller;

import com.codegym.module6_be.model.DTO.ChangePassword;
import com.codegym.module6_be.model.DTO.ChangePasswordForm;
import com.codegym.module6_be.model.SimpleBoard;
import com.codegym.module6_be.model.User;
import com.codegym.module6_be.model.UserPrincipal;
import com.codegym.module6_be.service.board.BoardService;
import com.codegym.module6_be.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/recoverpassword")
    public ResponseEntity<User> findByUserNameAndNickName(@RequestBody User user) {
        User userOptional = userService.findByUsernameAndEmail(user.getUsername(), user.getEmail());
        return new ResponseEntity<>(userOptional, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
//        user.setImage("");
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        user.setImage("https://vnn-imgs-a1.vgcloud.vn/image1.ictnews.vn/_Files/2020/03/17/trend-avatar-1.jpg");
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user) {
        User user1 = userService.update(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }


    @PutMapping("/{id}/recover")
    public ResponseEntity<User> updateRecoveredUser(@RequestBody User user) {
        User user1 = userService.save(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Iterable<User>> findUserByKeyword(@PathVariable String keyword) {
        Iterable<User> userIterable = userService.findUserByKeyword(keyword);
        return new ResponseEntity<>(userIterable, HttpStatus.OK);
    }

    @GetMapping("/{userId}/owned-boards")
    public ResponseEntity<Iterable<SimpleBoard>> findAllOwnedBoardsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(boardService.findAllOwnedBoardsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/shared-boards")
    public ResponseEntity<Iterable<SimpleBoard>> findAllSharedBoardsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(boardService.findAllSharedBoardsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("search/{keyword}/{workspaceId}")
    public ResponseEntity<Iterable<User>> showListMemberWorkspace(@PathVariable String keyword, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(userService.findByKeywordAndWorkspace(keyword, workspaceId), HttpStatus.OK);
    }

    @PostMapping("/set-password")
    public ResponseEntity<?> setPassword(@RequestBody ChangePasswordForm changePasswordForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(changePasswordForm.getUsername(), changePasswordForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.findByUsername(changePasswordForm.getUsername());
        if (changePasswordForm.getPassword().equals(changePasswordForm.getNewPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(changePasswordForm.getNewPassword());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

}
