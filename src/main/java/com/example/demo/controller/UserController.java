package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NeonDto;
import com.example.demo.entity.PlayingWithNeonEntity;
import com.example.demo.repository.PlayingWithNeonRepository;

@RestController
public class UserController {

	@Autowired
	public PlayingWithNeonRepository playwithneonrepository;

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

	@CrossOrigin(origins = "*")
    @GetMapping("/playingwithneon")
    public NeonDto playwithneon() {
        // DBから全件取得
        List<PlayingWithNeonEntity> allData = playwithneonrepository.findAll();
        
        // Response用の箱に入れて返す
        return new NeonDto(allData);
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

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getXUrl() {
			return xUrl;
		}
	}
}