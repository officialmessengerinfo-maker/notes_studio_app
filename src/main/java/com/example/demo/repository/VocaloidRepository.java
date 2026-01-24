package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VocaloidEntity;

@Repository
public interface VocaloidRepository extends JpaRepository<VocaloidEntity, Integer> {

	//idに一致したボーカロイドを取得(複数の場合有)
	@Query(value = "SELECT v.vocaloid FROM songbank_vocaloid sv " +
			"INNER JOIN vocaloid v ON v.id = sv.vocaloid_id " +
			"WHERE sv.song_id = :id", nativeQuery = true)
	List<String> getVocaloids(@Param("id") Integer id);

	//検索用のボーカロイドを取得
	@Query(value = "SELECT v.vocaloid FROM songbank_vocaloid sv " +
			"INNER JOIN vocaloid v ON v.id = sv.vocaloid_id " +
			"GROUP BY v.vocaloid ORDER BY v.vocaloid", nativeQuery = true)
	List<String> getVocaloidGroup();
}
