package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VocaloidEntity;

@Repository
public interface GenreRepository extends JpaRepository<VocaloidEntity, Integer> {

	//検索用のイベントを取得
	@Query(value = "SELECT DISTINCT genre FROM songbank " +
			"ORDER BY genre", nativeQuery = true)
	List<String> getGenreGroup();
}
 