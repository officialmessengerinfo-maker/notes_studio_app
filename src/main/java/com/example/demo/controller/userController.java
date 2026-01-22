package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @CrossOrigin(origins = "*")
    @GetMapping("/user")
    public UserResponse test() {
        List<User> userList = Arrays.asList(
                new User(1, "田中 太郎", "https://x.com/tanaka"),
                new User(2, "佐藤 次郎", "https://x.com/sato"),
                new User(3, "鈴木 花子", "https://x.com/suzuki"));
        
        // 直出しせず、UserResponseで包んで返す
        return new UserResponse(userList);
    }

    // --- Studioが読み取りやすいように「data」というキーで包むためのクラス ---
    public static class UserResponse {
        private List<User> data; // ここを "data" という名前にするのがコツです

        public UserResponse(List<User> data) {
            this.data = data;
        }

        public List<User> getData() {
            return data;
        }
    }

    public static class User {
        private int id;
        private String name;
        private String xUrl;

        public User(int id, String name, String xUrl) {
            this.id = id;
            this.name = name;
            this.xUrl = xUrl;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getXUrl() { return xUrl; }
    }
}