package com.delta.delta.controller;

import com.delta.delta.entity.User;
import com.delta.delta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/addFollower/{userId}/{followerId}")
    public void addFollower(@PathVariable Long userId, @PathVariable Long followerId) {
        userService.addFollower(userId, followerId);
    }

    @PostMapping("/deleteFollower/{userId}/{followerId}")
    public void deleteFollower(@PathVariable Long userId, @PathVariable Long followerId) {
        userService.deleteFollower(userId, followerId);
    }

    @PostMapping("/addFollowing/{userId}/{followingId}")
    public void addFollowing(@PathVariable Long userId, @PathVariable Long followingId) {
        userService.addFollowing(userId, followingId);
    }

    @PostMapping("/deleteFollowing/{userId}/{followingId}")
    public void deleteFollowing(@PathVariable Long userId, @PathVariable Long followingId) {
        userService.deleteFollowing(userId, followingId);
    }
}