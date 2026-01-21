package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. @RestControllerを使うことで、戻り値が自動的にJSONになります
@RestController
public class userController {

	// 2. Studioからのアクセスを許可するためにCORS設定を追加
	@CrossOrigin(origins = "*")
	@GetMapping("/user")
	public List<User> test() {
		// 3. テスト用のデータをリストに詰める
		return Arrays.asList(
				new User(1, "田中 太郎", "https://x.com/tanaka"),
				new User(2, "佐藤 次郎", "https://x.com/sato"),
				new User(3, "鈴木 花子", "https://x.com/suzuki"));
	}

	// 内部クラスとしてUserモデルを定義（本来は別ファイルにするのが望ましい）
	public static class User {
		private int id;
		private String name;
		private String xUrl;

		public User(int id, String name, String xUrl) {
			this.id = id;
			this.name = name;
			this.xUrl = xUrl;
		}

		// Getter（これがないとJSONに出力されません）
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