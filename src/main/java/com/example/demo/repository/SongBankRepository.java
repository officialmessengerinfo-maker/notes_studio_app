package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SongBankEntity;

import jakarta.transaction.Transactional;

@Repository
public interface SongBankRepository extends JpaRepository<SongBankEntity, Integer> {
	@Query(value = "SELECT s.id , s.artist , s.cover_artist , s.title , s.url , s.genre , s.post_time, s.event_id ,s.thumbnail_url"
			+ "FROM songbank s ORDER BY s.artist", nativeQuery = true)
	List<SongBankEntity> getSongBankData();

	@Query(value = "SELECT s.id , s.artist , s.cover_artist,s.title,s.url,s.genre,s.post_time,s.event_id, event.event_name "
			+ "FROM songbank s inner join event on s.event_id = event.id "
			+ "where s.artist LIKE CONCAT('%', :keyword , '%') or "
			+ "s.cover_artist LIKE CONCAT('%', :keyword , '%') or "
			+ "s.title LIKE CONCAT('%', :keyword , '%') or "
			+ "s.genre LIKE CONCAT('%', :keyword , '%') or "
			+ "event.event_name LIKE CONCAT('%', :keyword , '%')"
			+ "ORDER BY s.artist", nativeQuery = true)
	List<SongBankEntity> getSongBankSearchData(@Param("keyword") String keyword);

	@Modifying
	@Transactional
	@Query(value = "UPDATE songbank SET thumbnail_url = :thumbnailUrl WHERE id = :id", nativeQuery = true)
	void updateThumbnail(@Param("id") Integer id, @Param("thumbnailUrl") String thumbnailUrl);
}
